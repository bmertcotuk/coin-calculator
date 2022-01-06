package com.bitpace.coincalculator.service.model;

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
public class ConversionServiceResponse {
    private Date lastUpdatedAt;
    private Double coinAmount;
    private CryptoCurrency coinType;
    private FiatCurrency fiatCurrency;
    private Double fiatAmount;
}
