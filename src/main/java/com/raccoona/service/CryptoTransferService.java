package com.raccoona.service;

import com.coinselection.CoinSelectionProvider;
import com.coinselection.dto.CoinSelectionResult;
import com.coinselection.dto.UnspentOutput;
import com.raccoona.dto.UtxoDto;
import com.raccoona.util.Converter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.params.MainNetParams;
import org.bouncycastle.util.encoders.Hex;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sd.fomin.gerbera.transaction.TransactionBuilder;
import sd.fomin.gerbera.util.HexUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.raccoona.util.Converter.convertToSatoshi;
import static sd.fomin.gerbera.types.Coin.BTC;

@Service
public class CryptoTransferService {
    public static final int BLOCKS = 6;
    private BlockbookService blockbookService;
    private CoinSelectionProvider coinSelectionProvider;
    private NetworkParameters networkParameters;
    private String hotWalletAddress;
    private String hotWalletPrivate;

    private static final Integer KB = 1000;

    public CryptoTransferService(BlockbookService blockbookService,
                                 NetworkParameters networkParameters,
                                 @Value("${btc.hotWallet.address}") String hotWalletAddress,
                                 @Value("${btc.hotWallet.private}") String hotWalletPrivate,
                                 CoinSelectionProvider coinSelectionProvider
    ) {
        this.blockbookService = blockbookService;
        this.networkParameters = networkParameters;
        this.hotWalletAddress = hotWalletAddress;
        this.hotWalletPrivate = hotWalletPrivate;
        this.coinSelectionProvider = coinSelectionProvider;
    }

    Logger logger = LogManager.getLogger(CryptoTransferService.class);


    private Boolean isMainnet() {
        return networkParameters == MainNetParams.get();
    }

    public String transfer(String addressTo, BigDecimal amount) {
        String rawTxHex = createTransaction(addressTo, amount);
        return sendTransaction(rawTxHex);
    }

    private String createTransaction(String addressTo, BigDecimal amount) {
        Set<UtxoDto> utxos = blockbookService.getUtxos(hotWalletAddress);
        BigDecimal fee = estimateSmartFeePerByte(BLOCKS);

        CoinSelectionResult coinSelectionResult = selectUtxos(amount, utxos, fee);

        List<UnspentOutput> selectedUtxos = coinSelectionResult.component1();
        Long accumulatedFee = convertToSatoshi(coinSelectionResult.component2());

        checkNotNull(coinSelectionResult);
        checkNotNull(selectedUtxos);
        checkNotNull(accumulatedFee);

        TransactionBuilder transactionBuilder = TransactionBuilder.create(isMainnet(), BTC);
        selectedUtxos.forEach(it -> addInput(transactionBuilder, it));

        transactionBuilder.to(addressTo, convertToSatoshi(amount));
        transactionBuilder.withFee(accumulatedFee);
        transactionBuilder.changeTo(hotWalletAddress);

        logger.info(transactionBuilder.toString());
        return transactionBuilder.build().getRawTransaction();
    }

    private void addInput(TransactionBuilder transactionBuilder, UnspentOutput it) {
        String txid = it.getTxid();
        Integer vout = it.getVout();
        checkNotNull(txid);
        checkNotNull(vout);
        String hex = blockbookService.getRawTransaction(txid).getHex();
        String scriptPubKey = getScriptPubKey(hex, networkParameters, vout);

        transactionBuilder.from(
                txid,
                vout,
                scriptPubKey,
                convertToSatoshi(it.getAmount()),
                hotWalletPrivate,
                true
        );
    }

    private String sendTransaction(String hex) {
        return blockbookService.postTransaction(hex);
    }

    private CoinSelectionResult selectUtxos(BigDecimal amount, Set<UtxoDto> utxos, BigDecimal fee) {
        List<UnspentOutput> unspentOutputsList = utxos.stream()
                .map(Converter::toUnspentOutput)
                .collect(Collectors.toList());

        return coinSelectionProvider.provide(unspentOutputsList, amount, fee, 1, null, false);
    }

    @NotNull
    private String getScriptPubKey(String hex, NetworkParameters params, int vout) {
        Transaction tx = new Transaction(params, Hex.decode(hex));

        return HexUtils.asString(tx.getOutput(vout).getScriptBytes());
    }

    private BigDecimal estimateSmartFeePerByte(int blocks) {
        BigDecimal fee = blockbookService.estimateFee(blocks);
        return fee.divide(BigDecimal.valueOf(KB));
    }

}
