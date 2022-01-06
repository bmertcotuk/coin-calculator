package com.bitpace.coincalculator.service.model;

import com.bitpace.coincalculator.controller.dto.ConversionRestRequest;
import com.bitpace.coincalculator.model.CryptoCurrency;
import com.bitpace.coincalculator.model.FiatCurrency;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Mert Cotuk
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ConversionServiceRequest {
    private FiatCurrency fiatCurrency;
    private Double fiatAmount;
    private CryptoCurrency coinType;

    public ConversionServiceRequest(ConversionRestRequest restRequest) {
        this.fiatCurrency = restRequest.getFiatCurrency();
        this.fiatAmount = restRequest.getFiatAmount();
        this.coinType = restRequest.getCoinType();
    }
}
