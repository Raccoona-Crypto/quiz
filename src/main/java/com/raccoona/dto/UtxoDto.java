package com.raccoona.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UtxoDto {
    private String txid;
    private Integer vout;
    private BigDecimal value;
    private Integer confirmations;

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public Integer getVout() {
        return vout;
    }

    public void setVout(Integer vout) {
        this.vout = vout;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Integer getConfirmations() {
        return confirmations;
    }

    public void setConfirmations(Integer confirmations) {
        this.confirmations = confirmations;
    }

    @Override
    public String toString() {
        return "UtxoDto{" +
                "txid='" + txid + '\'' +
                ", vout=" + vout +
                ", value=" + value +
                ", confirmations=" + confirmations +
                '}';
    }
}
