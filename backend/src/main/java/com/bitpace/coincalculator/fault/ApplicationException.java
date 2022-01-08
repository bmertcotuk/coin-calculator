package com.bitpace.coincalculator.fault;

import com.bitpace.coincalculator.ApplicationStatusCodes;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Supplier;

/**
 * @author Mert Cotuk
 */
@Getter
@ToString
public class ApplicationException extends RuntimeException {

    private final String errorCode;

    public ApplicationException(String errorCode) {
        super("Error: " + errorCode + ", description: " + ApplicationStatusCodes.getDescription(errorCode));
        this.errorCode = errorCode;
    }

    public ApplicationException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public static Supplier<ApplicationException> factory(String errorCode) {
        return () -> new ApplicationException(errorCode);
    }
}
