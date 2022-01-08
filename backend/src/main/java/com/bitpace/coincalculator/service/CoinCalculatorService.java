package com.bitpace.coincalculator.service;

import com.bitpace.coincalculator.service.model.ConversionServiceRequest;
import com.bitpace.coincalculator.service.model.ConversionServiceResponse;
import com.bitpace.coincalculator.service.model.TransactionServiceRequest;
import com.bitpace.coincalculator.service.model.TransactionServiceResponse;

/**
 * @author Mert Cotuk
 */
public interface CoinCalculatorService {
    ConversionServiceResponse convertFiatCurrency(ConversionServiceRequest conversionServiceRequest);

    TransactionServiceResponse store(TransactionServiceRequest request);
}
