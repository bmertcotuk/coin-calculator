package com.bitpace.coincalculator.fault;

import com.bitpace.coincalculator.ApplicationStatusCodes;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Mert Cotuk
 */
@Getter
@ToString
public class ValidationException extends ApplicationException {

    private final String fieldName;

    public ValidationException(String errorCode, String fieldName) {
        super(errorCode);
        this.fieldName = fieldName;
    }
}
