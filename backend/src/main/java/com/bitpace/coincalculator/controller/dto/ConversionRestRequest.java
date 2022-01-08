package com.bitpace.coincalculator.controller.dto;

import com.bitpace.coincalculator.ApplicationStatusCodes;
import com.bitpace.coincalculator.model.CryptoCurrency;
import com.bitpace.coincalculator.model.FiatCurrency;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author Mert Cotuk
 */
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ConversionRestRequest {

    @NotNull(message = ApplicationStatusCodes.ERR_REQUIRED_FIELD)
    private FiatCurrency fiatCurrency;

    @Min(value = 25, message = ApplicationStatusCodes.ERR_INVALID_FIAT_AMOUNT)
    @Max(value = 5000, message = ApplicationStatusCodes.ERR_INVALID_FIAT_AMOUNT)
    @NotNull(message = ApplicationStatusCodes.ERR_REQUIRED_FIELD)
    private Double fiatAmount;

    @NotNull(message = ApplicationStatusCodes.ERR_REQUIRED_FIELD)
    private CryptoCurrency coinType;
}
