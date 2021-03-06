package com.bitpace.coincalculator.controller.dto;

import com.bitpace.coincalculator.ApplicationStatusCodes;
import com.bitpace.coincalculator.model.CryptoCurrency;
import com.bitpace.coincalculator.model.FiatCurrency;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Mert Cotuk
 */
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRestRequest {

    @NotNull(message = ApplicationStatusCodes.ERR_REQUIRED_FIELD)
    private Date lastUpdatedAt;

    @NotNull(message = ApplicationStatusCodes.ERR_REQUIRED_FIELD)
    private Double coinAmount;

    @NotNull(message = ApplicationStatusCodes.ERR_REQUIRED_FIELD)
    private CryptoCurrency coinType;

    @NotNull(message = ApplicationStatusCodes.ERR_REQUIRED_FIELD)
    private FiatCurrency fiatCurrency;

    @NotNull(message = ApplicationStatusCodes.ERR_REQUIRED_FIELD)
    private Double fiatAmount;
}
