package com.bitpace.coincalculator.service;

import com.bitpace.coincalculator.client.ExchangeRatesApiFeignClient;
import com.bitpace.coincalculator.model.ConversionTransaction;
import com.bitpace.coincalculator.model.CryptoCurrency;
import com.bitpace.coincalculator.model.FiatCurrency;
import com.bitpace.coincalculator.repository.ConversionTransactionRepository;
import com.bitpace.coincalculator.service.model.ConversionServiceRequest;
import com.bitpace.coincalculator.service.model.ConversionServiceResponse;
import com.bitpace.coincalculator.service.model.TransactionServiceRequest;
import com.bitpace.coincalculator.service.model.TransactionServiceResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * @author Mert Cotuk
 */
@ExtendWith(MockitoExtension.class)
class CoinCalculatorServiceImplTest {

    private static final Date TEST_DATE = new Date();

    @InjectMocks
    private CoinCalculatorServiceImpl coinCalculatorService;

    @Mock
    private ConversionTransactionRepository transactionRepository;

    @Mock
    private ExchangeRatesApiFeignClient exchangeRatesApiFeignClient;

    @Test
    void shouldConvertFiatCurrencySuccessfully() {
        final var expected = buildConversionServiceResponse();
        final var serviceRequest = buildConversionServiceRequest();
        final var feignResponse = "0.01360857";
        when(exchangeRatesApiFeignClient.convertToBtc(anyString(), anyDouble())).thenReturn(feignResponse);

        final var actual = coinCalculatorService.convertFiatCurrency(serviceRequest);

        assertEquals(expected.getCoinAmount(), actual.getCoinAmount());
        assertEquals(expected.getFiatAmount(), actual.getFiatAmount());
        assertEquals(expected.getCoinType(), actual.getCoinType());
        assertEquals(expected.getFiatCurrency(), actual.getFiatCurrency());
    }

    @Test
    void shouldNotConvertFiatCurrencyOnException() {
        final var serviceRequest = buildConversionServiceRequest();
        doThrow(RuntimeException.class).when(exchangeRatesApiFeignClient).convertToBtc(anyString(), anyDouble());

        assertThrows(RuntimeException.class, () -> coinCalculatorService.convertFiatCurrency(serviceRequest));
    }

    @Test
    void shouldStoreTransactionSuccessfully() {
        final var expected = buildTransactionServiceRequest();
        final var serviceRequest = buildTransactionServiceRequest();
        final var persistedEntity = buildConversionTransaction();
        when(transactionRepository.save(any(ConversionTransaction.class))).thenReturn(persistedEntity);

        final var actual = coinCalculatorService.storeTransaction(serviceRequest);

        assertEquals(expected.getCoinAmount(), actual.getCoinAmount());
        assertEquals(expected.getFiatAmount(), actual.getFiatAmount());
        assertEquals(expected.getCoinType().name(), actual.getCoinType());
        assertEquals(expected.getFiatCurrency().name(), actual.getFiatCurrency());
        assertEquals(expected.getLastUpdatedAt(), actual.getLastUpdatedAt());
    }

    @Test
    void shouldNotStoreTransactionOnException() {
        final var serviceRequest = buildTransactionServiceRequest();
        doThrow(RuntimeException.class).when(transactionRepository).save(any(ConversionTransaction.class));

        assertThrows(RuntimeException.class, () -> coinCalculatorService.storeTransaction(serviceRequest));
    }

    private ConversionServiceRequest buildConversionServiceRequest() {
        return ConversionServiceRequest.builder()
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

    private TransactionServiceRequest buildTransactionServiceRequest() {
        return TransactionServiceRequest.builder()
                .lastUpdatedAt(TEST_DATE)
                .coinAmount(0.01360857)
                .coinType(CryptoCurrency.BTC)
                .fiatCurrency(FiatCurrency.EUR)
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

    private ConversionTransaction buildConversionTransaction() {
        return new ConversionTransaction(
                TEST_DATE,
                0.01360857,
                CryptoCurrency.BTC.name(),
                FiatCurrency.EUR.name(),
                500.00);
    }
}
