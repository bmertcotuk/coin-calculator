package com.bitpace.coincalculator.service.model;

import com.bitpace.coincalculator.controller.dto.ConversionRestRequest;
import com.bitpace.coincalculator.model.CryptoCurrency;
import com.bitpace.coincalculator.model.FiatCurrency;
import lombok.*;

/**
 * @author Mert Cotuk
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ConversionServiceRequest {
    private FiatCurrency fiatCurrency;
    private Double fiatAmount;
    private CryptoCurrency coinType;

    public static ConversionServiceRequest fromRestRequest(ConversionRestRequest restRequest) {
        return ConversionServiceRequest.builder()
                .fiatCurrency(restRequest.getFiatCurrency())
                .fiatAmount(restRequest.getFiatAmount())
                .coinType(restRequest.getCoinType())
                .build();
    }
}
