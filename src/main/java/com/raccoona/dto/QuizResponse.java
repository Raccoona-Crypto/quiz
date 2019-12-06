package com.raccoona.dto;

import java.math.BigDecimal;

public class QuizResponse {
    private Currency currency;
    private BigDecimal amount;


    public QuizResponse(Currency currency, BigDecimal amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

