package com.bitpace.coincalculator.controller.dto;

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
public class ConversionRestRequest {
    private FiatCurrency fiatCurrency;
    private Double fiatAmount;
    private CryptoCurrency coinType;
}
