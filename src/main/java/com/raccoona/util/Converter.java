package com.raccoona.util;

import com.coinselection.dto.UnspentOutput;
import com.raccoona.dto.UtxoDto;

import java.math.BigDecimal;

public class Converter {

    public static UnspentOutput toUnspentOutput(UtxoDto utxoDto) {
        return new UnspentOutput(utxoDto.getTxid(),
                utxoDto.getVout(),
                null,
                null,
                null,
                convertFromSatoshi(utxoDto.getValue()),
                null,
                null,
                true,
                true,
                true
        );
    }

    public static Long convertToSatoshi(BigDecimal amount) {
        return amount.movePointRight(8).longValue();
    }

    public static BigDecimal convertFromSatoshi(BigDecimal amount) {
        return amount.movePointLeft(8);
    }

}
