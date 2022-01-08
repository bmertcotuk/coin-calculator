package com.bitpace.coincalculator.controller;

import com.bitpace.coincalculator.controller.dto.ConversionRestRequest;
import com.bitpace.coincalculator.controller.dto.ConversionRestResponse;
import com.bitpace.coincalculator.controller.dto.TransactionRestRequest;
import com.bitpace.coincalculator.controller.dto.TransactionRestResponse;
import com.bitpace.coincalculator.model.CryptoCurrency;
import com.bitpace.coincalculator.model.FiatCurrency;
import com.bitpace.coincalculator.service.CoinCalculatorService;
import com.bitpace.coincalculator.service.model.ConversionServiceRequest;
import com.bitpace.coincalculator.service.model.ConversionServiceResponse;
import com.bitpace.coincalculator.service.model.TransactionServiceRequest;
import com.bitpace.coincalculator.service.model.TransactionServiceResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * @author Mert Cotuk
 */
@ExtendWith(MockitoExtension.class)
class CoinCalculatorControllerTest {

    private static final Date TEST_DATE = new Date();

    @InjectMocks
    private CoinCalculatorController coinCalculatorController;

    @Mock
    private CoinCalculatorService coinCalculatorService;

    @Test
    void shouldConvertFiatCurrencySuccessfully() {
        final var expected = buildConversionRestResponse();
        final var restRequest = buildConversionRestRequest();
        final var serviceResponse = buildConversionServiceResponse();
        when(coinCalculatorService.convertFiatCurrency(any(ConversionServiceRequest.class))).thenReturn(serviceResponse);

        final var actual = coinCalculatorController.convertFiatCurrency(restRequest);

        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertEquals(expected.getCoinAmount(), Objects.requireNonNull(actual.getBody()).getCoinAmount());
        assertEquals(expected.getCoinType(), actual.getBody().getCoinType());
        assertEquals(expected.getFiatAmount(), actual.getBody().getFiatAmount());
        assertEquals(expected.getFiatCurrency(), actual.getBody().getFiatCurrency());
        assertEquals(expected.getLastUpdatedAt(), actual.getBody().getLastUpdatedAt());
    }

    @Test
    void shouldNotConvertFiatCurrencyOnException() {
        final var restRequest = buildConversionRestRequest();
        doThrow(RuntimeException.class).when(coinCalculatorService).convertFiatCurrency(any(ConversionServiceRequest.class));

        assertThrows(RuntimeException.class, () -> coinCalculatorController.convertFiatCurrency(restRequest));
    }

    @Test
    void shouldStoreTransactionSuccessfully() {
        final var expected = buildTransactionRestResponse();
        final var restRequest = buildTransactionRestRequest();
        final var serviceResponse = buildTransactionServiceResponse();
        when(coinCalculatorService.storeTransaction(any(TransactionServiceRequest.class)))
                .thenReturn(serviceResponse);

        final var actual = coinCalculatorController.storeTransaction(restRequest);

        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertEquals(expected.getCoinAmount(), Objects.requireNonNull(actual.getBody()).getCoinAmount());
        assertEquals(expected.getCoinType(), actual.getBody().getCoinType());
        assertEquals(expected.getFiatAmount(), actual.getBody().getFiatAmount());
        assertEquals(expected.getFiatCurrency(), actual.getBody().getFiatCurrency());
        assertEquals(expected.getLastUpdatedAt(), actual.getBody().getLastUpdatedAt());
    }

    @Test
    void shouldNotStoreTransactionOnException() {
        final var restRequest = buildTransactionRestRequest();
        doThrow(RuntimeException.class).when(coinCalculatorService).storeTransaction(any(TransactionServiceRequest.class));

        assertThrows(RuntimeException.class, () -> coinCalculatorController.storeTransaction(restRequest));
    }

    private ConversionRestRequest buildConversionRestRequest() {
        return ConversionRestRequest.builder()
                .fiatCurrency(FiatCurrency.EUR)
                .fiatAmount(500.0)
                .coinType(CryptoCurrency.BTC)
                .build();
    }

    private ConversionRestResponse buildConversionRestResponse() {
        return ConversionRestResponse.builder()
                .lastUpdatedAt(TEST_DATE)
                .coinAmount(0.01360857)
                .coinType(CryptoCurrency.BTC)
                .fiatCurrency(FiatCurrency.EUR)
                .fiatAmount(500.0)
                .build();
    }

    private ConversionServiceResponse buildConversionServiceResponse() {
        return ConversionServiceResponse.builder()
                .lastUpdatedAt(TEST_DATE)
                .coinAmount(0.01360857)
                .coinType(CryptoCurrency.BTC)
                .fiatCurrency(FiatCurrency.EUR)
                .fiatAmount(500.0)
                .build();
    }

    private TransactionRestRequest buildTransactionRestRequest() {
        return TransactionRestRequest.builder()
                .lastUpdatedAt(TEST_DATE)
                .coinAmount(0.01360857)
                .coinType(CryptoCurrency.BTC)
                .fiatCurrency(FiatCurrency.EUR)
                .fiatAmount(500.0)
                .build();
    }

    private TransactionRestResponse buildTransactionRestResponse() {
        return TransactionRestResponse.builder()
                .lastUpdatedAt(TEST_DATE)
                .coinAmount(0.01360857)
                .coinType(CryptoCurrency.BTC.name())
                .fiatCurrency(FiatCurrency.EUR.name())
                .fiatAmount(500.0)
                .build();
    }

    private TransactionServiceResponse buildTransactionServiceResponse() {
        return TransactionServiceResponse.builder()
                .lastUpdatedAt(TEST_DATE)
                .coinAmount(0.01360857)
                .coinType(CryptoCurrency.BTC.name())
                .fiatCurrency(FiatCurrency.EUR.name())
                .fiatAmount(500.0)
                .build();
    }
}
