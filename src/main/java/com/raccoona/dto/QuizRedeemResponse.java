package com.raccoona.dto;


public class QuizRedeemResponse {
    private Currency currency;
    private String txid;

    public QuizRedeemResponse() {
    }

    public QuizRedeemResponse(Currency currency, String txid) {
        this.currency = currency;
        this.txid = txid;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }
}


