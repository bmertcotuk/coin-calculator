package com.bitpace.coincalculator.fault;

import com.bitpace.coincalculator.ApplicationStatusCodes;
import lombok.*;

/**
 * @author Mert Cotuk
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ErrorRestResponse {

    private String errorCode;
    private String errorDescription;

    public ErrorRestResponse(String errorCode) {
        this(errorCode, ApplicationStatusCodes.getDescription(errorCode));
    }
}
