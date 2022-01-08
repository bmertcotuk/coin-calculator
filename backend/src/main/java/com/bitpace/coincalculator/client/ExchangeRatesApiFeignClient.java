package com.bitpace.coincalculator.client;

import com.bitpace.coincalculator.configuration.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.bitpace.coincalculator.ApplicationConstants.APPLICATION_JSON_UTF_8;
import static com.bitpace.coincalculator.ApplicationConstants.TEXT_PLAIN_UTF_8;

/**
 * @author Mert Cotuk
 */

@FeignClient(value = "exchange-rates-api",
        url = "${external-api.exchange-rates.base-url}",
        configuration = FeignClientConfiguration.class)
public interface ExchangeRatesApiFeignClient {

    @GetMapping(value = "tobtc", consumes = APPLICATION_JSON_UTF_8, produces = TEXT_PLAIN_UTF_8)
    String convertToBtc(@RequestParam(name = "currency") String fiatCurrency,
                        @RequestParam(name = "value") Double fiatAmount);
}
