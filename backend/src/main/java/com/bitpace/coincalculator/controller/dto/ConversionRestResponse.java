package com.bitpace.coincalculator.controller.dto;

import com.bitpace.coincalculator.model.CryptoCurrency;
import com.bitpace.coincalculator.model.FiatCurrency;
import com.bitpace.coincalculator.service.model.ConversionServiceResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author Mert Cotuk
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ConversionRestResponse {
    private Date lastUpdatedAt;
    private Double coinAmount;
    private CryptoCurrency coinType;
    private FiatCurrency fiatCurrency;
    private Double fiatAmount;

    public ConversionRestResponse(ConversionServiceResponse serviceResponse) {
        this.lastUpdatedAt = serviceResponse.getLastUpdatedAt();
        this.coinAmount = serviceResponse.getCoinAmount();
        this.coinType = serviceResponse.getCoinType();
        this.fiatCurrency = serviceResponse.getFiatCurrency();
        this.fiatAmount = serviceResponse.getFiatAmount();
    }
}
