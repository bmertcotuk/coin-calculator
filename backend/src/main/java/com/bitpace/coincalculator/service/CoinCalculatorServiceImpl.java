package com.bitpace.coincalculator.service;

import com.bitpace.coincalculator.client.ExchangeRatesApiFeignClient;
import com.bitpace.coincalculator.model.CryptoCurrency;
import com.bitpace.coincalculator.service.model.ConversionServiceRequest;
import com.bitpace.coincalculator.service.model.ConversionServiceResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Mert Cotuk
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CoinCalculatorServiceImpl implements CoinCalculatorService {

    private final ExchangeRatesApiFeignClient exchangeRatesApiFeignClient;

    public ConversionServiceResponse convert(ConversionServiceRequest serviceRequest) {
        final var feignResponse = exchangeRatesApiFeignClient.convertToBtc(serviceRequest.getFiatCurrency().name(),
                serviceRequest.getFiatAmount());
        final var conversionDate = new Date();

        final var btcAmount = Double.valueOf(feignResponse);
        log.debug("BTC to be received: {}", btcAmount);

        return ConversionServiceResponse.builder()
                .lastUpdatedAt(conversionDate)
                .coinAmount(btcAmount)
                .coinType(CryptoCurrency.BTC)
                .fiatAmount(serviceRequest.getFiatAmount())
                .fiatCurrency(serviceRequest.getFiatCurrency())
                .build();
    }
}
