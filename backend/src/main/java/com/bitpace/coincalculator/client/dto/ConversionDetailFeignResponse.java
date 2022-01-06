package com.bitpace.coincalculator.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class ConversionDetailFeignResponse {

    @JsonProperty("15m")
    private Double fifteenMinutesDelayedMarketPrice;

    @JsonProperty("last")
    private Double lastMarketPrice;

    @JsonProperty("buy")
    private Double amountBought;

    @JsonProperty("sell")
    private Double amountSold;

    @JsonProperty("symbol")
    private String currencySymbol;
}
