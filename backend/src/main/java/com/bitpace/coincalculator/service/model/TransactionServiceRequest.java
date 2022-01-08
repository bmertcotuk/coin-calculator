package com.bitpace.coincalculator.service.model;

import com.bitpace.coincalculator.controller.dto.TransactionRestRequest;
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
public class TransactionServiceRequest {
    private Date lastUpdatedAt;
    private Double coinAmount;
    private CryptoCurrency coinType;
    private FiatCurrency fiatCurrency;
    private Double fiatAmount;

    public static TransactionServiceRequest fromRestRequest(TransactionRestRequest restRequest) {
        return TransactionServiceRequest.builder()
                .lastUpdatedAt(restRequest.getLastUpdatedAt())
                .coinAmount(restRequest.getCoinAmount())
                .coinType(restRequest.getCoinType())
                .fiatCurrency(restRequest.getFiatCurrency())
                .fiatAmount(restRequest.getFiatAmount())
                .build();
    }
}
