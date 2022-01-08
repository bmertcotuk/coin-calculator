package com.bitpace.coincalculator.service.model;

import com.bitpace.coincalculator.model.ConversionTransaction;
import com.bitpace.coincalculator.model.CryptoCurrency;
import com.bitpace.coincalculator.model.FiatCurrency;
import lombok.*;

import java.util.Date;

/**
 * @author Mert Cotuk
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TransactionServiceResponse {
    private Date lastUpdatedAt;
    private Double coinAmount;
    private CryptoCurrency coinType;
    private FiatCurrency fiatCurrency;
    private Double fiatAmount;

    public static TransactionServiceResponse fromEntity(ConversionTransaction conversionTransaction) {
        return TransactionServiceResponse.builder()
                .lastUpdatedAt(conversionTransaction.getLastUpdatedAt())
                .coinAmount(conversionTransaction.getCoinAmount())
                .coinType(conversionTransaction.getCoinType())
                .fiatCurrency(conversionTransaction.getFiatCurrency())
                .fiatAmount(conversionTransaction.getFiatAmount())
                .build();
    }
}
