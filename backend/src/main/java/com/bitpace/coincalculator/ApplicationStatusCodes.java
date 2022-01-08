package com.bitpace.coincalculator;

import com.google.common.collect.ImmutableMap;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author Mert Cotuk
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplicationStatusCodes {
    public static final String OK = "OK";

    public static final String ERR_GENERAL_ERROR = "ERR-000";
    public static final String ERR_ACCESS_DENIED = "ERR-001";
    public static final String ERR_UNAUTHORIZED = "ERR-002";
    public static final String ERR_REQUIRED_FIELD = "ERR-003";
    public static final String ERR_INVALID_FIAT_AMOUNT = "ERR-004";

    private static final Map<String, String> STATUS_DESCRIPTIONS = ImmutableMap.<String, String>builder()
            .put(OK, "No error.")
            .put(ERR_GENERAL_ERROR, "Unknown error occurred. Please contact to an administrator.")
            .put(ERR_ACCESS_DENIED, "Access denied.")
            .put(ERR_UNAUTHORIZED, "Unauthorized.")
            .put(ERR_REQUIRED_FIELD, "Missing required field.")
            .put(ERR_INVALID_FIAT_AMOUNT, "The fiat currency should be in range [25, 5000].")
            .build();

    public static String getDescription(String appStatusCode) {
        return STATUS_DESCRIPTIONS.getOrDefault(appStatusCode,
                "Description is not found for error code " + appStatusCode);
    }
}
