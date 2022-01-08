package com.bitpace.coincalculator.controller;

import com.bitpace.coincalculator.controller.dto.ConversionRestRequest;
import com.bitpace.coincalculator.controller.dto.ConversionRestResponse;
import com.bitpace.coincalculator.controller.dto.TransactionRestRequest;
import com.bitpace.coincalculator.controller.dto.TransactionRestResponse;
import com.bitpace.coincalculator.service.CoinCalculatorService;
import com.bitpace.coincalculator.service.model.ConversionServiceRequest;
import com.bitpace.coincalculator.service.model.TransactionServiceRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Mert Cotuk
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/calculator")
@CrossOrigin(origins = "http://localhost:8080")
public class CoinCalculatorController {

    private final CoinCalculatorService coinCalculatorService;

    @PostMapping("/convert")
    public ResponseEntity<ConversionRestResponse> convertFiatCurrency(@Valid @RequestBody ConversionRestRequest restRequest) {
        log.debug("convertFiatCurrency::restRequest={}", restRequest);
        final var serviceRequest = ConversionServiceRequest.fromRestRequest(restRequest);
        final var serviceResponse = coinCalculatorService.convertFiatCurrency(serviceRequest);
        log.debug("convertFiatCurrency::serviceResponse={}", serviceResponse);
        return new ResponseEntity<>(ConversionRestResponse.fromServiceResponse(serviceResponse), HttpStatus.OK);
    }

    @PostMapping("/store")
    public ResponseEntity<TransactionRestResponse> storeTransaction(@Valid @RequestBody TransactionRestRequest restRequest) {
        log.debug("storeTransaction::request={}", restRequest);
        final var serviceRequest = TransactionServiceRequest.fromRestRequest(restRequest);
        final var serviceResponse = coinCalculatorService.storeTransaction(serviceRequest);
        log.debug("storeTransaction::serviceResponse={}", serviceResponse);
        return new ResponseEntity<>(TransactionRestResponse.fromServiceResponse(serviceResponse), HttpStatus.OK);
    }
}
