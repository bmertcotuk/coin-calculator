package com.bitpace.coincalculator.service;

import com.bitpace.coincalculator.service.model.ConversionServiceRequest;
import com.bitpace.coincalculator.service.model.ConversionServiceResponse;

/**
 * @author Mert Cotuk
 */
public interface CoinCalculatorService {
    ConversionServiceResponse convert(ConversionServiceRequest conversionServiceRequest);
}
