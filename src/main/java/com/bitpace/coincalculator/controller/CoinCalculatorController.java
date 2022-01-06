package com.bitpace.coincalculator.controller;

import com.bitpace.coincalculator.controller.dto.ConversionRestRequest;
import com.bitpace.coincalculator.controller.dto.ConversionRestResponse;
import com.bitpace.coincalculator.service.CoinCalculatorService;
import com.bitpace.coincalculator.service.model.ConversionServiceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Mert Cotuk
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/calculator")
@CrossOrigin(origins = "http://localhost:8081")
public class CoinCalculatorController {

    private final CoinCalculatorService coinCalculatorService;

    @PostMapping("/convert")
    public ResponseEntity<ConversionRestResponse> convert(@RequestBody ConversionRestRequest request) {
        final var serviceResponse = coinCalculatorService.convert(new ConversionServiceRequest(request));
        return new ResponseEntity<>(new ConversionRestResponse(serviceResponse), HttpStatus.OK);
    }
}
