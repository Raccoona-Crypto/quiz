package com.raccoona.entity;

import com.raccoona.dto.Currency;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "Rewards")
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Column(precision = 18, scale = 8)
    private BigDecimal amount;

    @Column(name = "funding_address")
    private String fundingAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getFundingAddress() {
        return fundingAddress;
    }

    public void setFundingAddress(String fundingAddress) {
        this.fundingAddress = fundingAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reward reward = (Reward) o;
        return Objects.equals(getId(), reward.getId()) &&
                Objects.equals(getCurrency(), reward.getCurrency()) &&
                Objects.equals(getAmount(), reward.getAmount()) &&
                Objects.equals(getFundingAddress(), reward.getFundingAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCurrency(), getAmount(), getFundingAddress());
    }
}
