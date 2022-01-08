package com.bitpace.coincalculator.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Mert Cotuk
 */
@Entity
@Table(name = "conversion_transactions")
public class ConversionTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "lastUpdatedAt")
    private Date lastUpdatedAt;

    @Column(name = "coinAmount")
    private Double coinAmount;

    @Column(name = "coinType")
    private String coinType;

    @Column(name = "fiatCurrency")
    private String fiatCurrency;

    @Column(name = "fiatAmount")
    private Double fiatAmount;

    public ConversionTransaction() {

    }

    public ConversionTransaction(Date lastUpdatedAt, Double coinAmount, String coinType,
                                 String fiatCurrency, Double fiatAmount) {
        this.lastUpdatedAt = lastUpdatedAt;
        this.coinAmount = coinAmount;
        this.coinType = coinType;
        this.fiatCurrency = fiatCurrency;
        this.fiatAmount = fiatAmount;
    }

    public long getId() {
        return id;
    }

    public Date getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(Date lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public Double getCoinAmount() {
        return coinAmount;
    }

    public void setCoinAmount(Double coinAmount) {
        this.coinAmount = coinAmount;
    }

    public String getCoinType() {
        return coinType;
    }

    public void setCoinType(String coinType) {
        this.coinType = coinType;
    }

    public String getFiatCurrency() {
        return fiatCurrency;
    }

    public void setFiatCurrency(String fiatCurrency) {
        this.fiatCurrency = fiatCurrency;
    }

    public Double getFiatAmount() {
        return fiatAmount;
    }

    public void setFiatAmount(Double fiatAmount) {
        this.fiatAmount = fiatAmount;
    }

    @Override
    public String toString() {
        return "ConversionTransaction{" +
                "id=" + id +
                ", lastUpdatedAt=" + lastUpdatedAt +
                ", coinAmount=" + coinAmount +
                ", coinType=" + coinType +
                ", fiatCurrency=" + fiatCurrency +
                ", fiatAmount=" + fiatAmount +
                '}';
    }
}
