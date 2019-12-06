package com.raccoona.entity;

import com.raccoona.dto.Currency;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "Redeems")
public class Redeem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Column(precision = 18, scale = 8)
    private BigDecimal amount;
    private String address;
    private String txid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Redeem redeem = (Redeem) o;
        return Objects.equals(getId(), redeem.getId()) &&
                Objects.equals(getCreatedAt(), redeem.getCreatedAt()) &&
                Objects.equals(getCurrency(), redeem.getCurrency()) &&
                Objects.equals(getAmount(), redeem.getAmount()) &&
                Objects.equals(getAddress(), redeem.getAddress()) &&
                Objects.equals(getTxid(), redeem.getTxid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCreatedAt(), getCurrency(), getAmount(), getAddress(), getTxid());
    }
}
