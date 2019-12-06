package com.raccoona.service;

import com.raccoona.dto.RawTransactionDto;
import com.raccoona.dto.UtxoDto;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class BlockbookServiceTest {

    @Autowired
    private BlockbookService blockbookService;

    @Test
    public void shouldGetUtxo() {
        Set<UtxoDto> utxos = blockbookService.getUtxos("19mTqsV4SXrtBRCDF94N2rWAEmN3VMK3B9");
        System.out.println(utxos);
        assertNotNull(utxos);
    }

    @Ignore
    @Test
    public void shouldPostTx() {
        String txid = blockbookService.postTransaction("020000000001031b21ae884e158428d88031deb78fceeb81c22d0d430b5d69a46955d8cdf33ab20100000017160014d65c4b5a913bf1f2084667cdc950e2199f553767ffffffff2e3f2d6ab9a42701302d6609ba7d8c081635d0f9a6f409a38e18dc7fd564de730100000017160014c2429f9874f6b3367390b4385e54138dfce72a1bffffffff9095a1b2aec0e30da9c87ff853f31778a8379479c3faf3c02bde5f4c2eb4eda601000000171600143688b12af2a11648c91366bb1e8ccea97aa51fedffffffff02006a18000000000017a914611806e34901d16859511bb5412f7865c1bdab1087f10e03000000000017a914c2e64390ea1239b1a3c6f88504f3a795a43117a1870247304402201e8742d8da34e53d97786cb4fa99901c07c5f8dff2893cff4649fc34fdf3d5ff0220069881a322646a4dd1ab7ac5ed4650ba333d623c97af1cc8fa3fca8bc5d051f6012103d9694f64022e413278e67671ed5e82b97f16416c0461eb2f1391e42ffadc122b02473044022011a41a447d34a73aeb30bf8d998be50c3974dee244c86573694c5a1c7b713e84022078f226222d869aea57bb74d5a845db3565f7f0630affc514f28cff663d685355012102512c9432442e17fb599a295fe5a7d7cae0acfa5b74bab5c79dac02fa9a7d1e0902473044022079c4b3ff90dcef33ddf14a628c858c977e48710b18a8eb5d41b834237656993502206a25f0d2ac2ae8ac6f5af8bb494809041a5a0d311c922a321cb50e6a9650e05801210364a61074b6a2d7f800a0bdcedc5f2cf7380e67dcce7836861d3318e18ab3eddf00000000");
        System.out.println(txid);
        assertNotNull(txid);
    }

    @Test
    public void shouldEstimateFee() {
        BigDecimal fee = blockbookService.estimateFee(1);
        System.out.println(fee);
        assertNotNull(fee);
    }

    @Test
    public void shouldGetRawTransaction() {
        RawTransactionDto rawTransaction = blockbookService.getRawTransaction("caa37b4e19d53eb9fdbff0fc8d86496c3aadca466894cb6f627cc72adfe744c6");
        System.out.println(rawTransaction.getHex());
        assertNotNull(rawTransaction);
    }
}