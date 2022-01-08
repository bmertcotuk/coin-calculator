package com.bitpace.coincalculator.controller.dto;

import com.bitpace.coincalculator.service.model.TransactionServiceResponse;
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
public class TransactionRestResponse {
    private Date lastUpdatedAt;
    private Double coinAmount;
    private String coinType;
    private String fiatCurrency;
    private Double fiatAmount;

    public static TransactionRestResponse fromServiceResponse(TransactionServiceResponse serviceResponse) {
        return TransactionRestResponse.builder()
                .lastUpdatedAt(serviceResponse.getLastUpdatedAt())
                .coinAmount(serviceResponse.getCoinAmount())
                .coinType(serviceResponse.getCoinType())
                .fiatCurrency(serviceResponse.getFiatCurrency())
                .fiatAmount(serviceResponse.getFiatAmount())
                .build();
    }
}
