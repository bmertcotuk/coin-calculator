package com.bitpace.coincalculator.fault;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * @author Mert Cotuk
 */
@Getter
@AllArgsConstructor
@ToString
public class ValidationErrorRestResponse {
    private final List<ErrorRestResponse> globalErrors;
    private final Map<String, ErrorRestResponse> fieldErrors;
}
