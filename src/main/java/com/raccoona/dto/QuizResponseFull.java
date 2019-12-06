package com.raccoona.dto;

import com.raccoona.entity.Redeem;
import com.raccoona.entity.Reward;

import java.math.BigDecimal;
import java.util.Objects;

public class QuizResponseFull {
    private Currency currency;
    private BigDecimal amount;
    private String status;
    private Redeem redeem;
    private Reward reward;

    public QuizResponseFull(Currency currency, BigDecimal amount, String status, Redeem redeem, Reward reward) {
        this.currency = currency;
        this.amount = amount;
        this.status = status;
        this.redeem = redeem;
        this.reward = reward;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Redeem getRedeem() {
        return redeem;
    }

    public void setRedeem(Redeem redeem) {
        this.redeem = redeem;
    }

    public Reward getReward() {
        return reward;
    }

    public void setReward(Reward reward) {
        this.reward = reward;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuizResponseFull that = (QuizResponseFull) o;
        return getCurrency() == that.getCurrency() &&
                Objects.equals(getAmount(), that.getAmount()) &&
                Objects.equals(getStatus(), that.getStatus()) &&
                Objects.equals(getRedeem(), that.getRedeem()) &&
                Objects.equals(getReward(), that.getReward());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCurrency(), getAmount(), getStatus(), getRedeem(), getReward());
    }
}

