package com.bitpace.coincalculator.fault;

import com.bitpace.coincalculator.ApplicationConstants;
import com.bitpace.coincalculator.controller.CoinCalculatorController;
import com.bitpace.coincalculator.controller.dto.ConversionRestRequest;
import com.bitpace.coincalculator.controller.dto.TransactionRestRequest;
import org.hibernate.exception.JDBCConnectionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ApplicationExceptionHandlerTest {

    private MockMvc mockMvcCoinCalculatorController;

    @Mock
    CoinCalculatorController coinCalculatorController;

    @BeforeEach
    public void setup() {
        this.mockMvcCoinCalculatorController = MockMvcBuilders.standaloneSetup(coinCalculatorController)
                .setControllerAdvice(new ApplicationExceptionHandler())
                .build();
    }

    @Test
    void handleApplicationExceptionForCurrencyConversion() throws Exception {
        doThrow(ApplicationException.class).when(coinCalculatorController).convertFiatCurrency(any(ConversionRestRequest.class));
        mockMvcCoinCalculatorController.perform(post("/api/calculator/convert")
                        .contentType(ApplicationConstants.APPLICATION_JSON_UTF_8)
                        .content("{\n" +
                                "    \"fiatCurrency\": \"EUR\",\n" +
                                "    \"fiatAmount\": 500,\n" +
                                "    \"coinType\": \"BTC\"\n" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void handleApplicationExceptionForTransactionStorage() throws Exception {
        doThrow(ApplicationException.class).when(coinCalculatorController).storeTransaction(any(TransactionRestRequest.class));
        mockMvcCoinCalculatorController.perform(post("/api/calculator/store")
                        .contentType(ApplicationConstants.APPLICATION_JSON_UTF_8)
                        .content("{\n" +
                                "    \"lastUpdatedAt\": \"2022-01-06T22:00:21.362+00:00\",\n" +
                                "    \"coinAmount\": 0.00394129,\n" +
                                "    \"coinType\": \"BTC\",\n" +
                                "    \"fiatCurrency\": \"EUR\",\n" +
                                "    \"fiatAmount\": 150.523\n" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void handleAccessDeniedException() throws Exception {
        doThrow(AccessDeniedException.class).when(coinCalculatorController).convertFiatCurrency(any(ConversionRestRequest.class));
        mockMvcCoinCalculatorController.perform(post("/api/calculator/convert")
                        .contentType(ApplicationConstants.APPLICATION_JSON_UTF_8)
                        .content("{\n" +
                                "    \"fiatCurrency\": \"EUR\",\n" +
                                "    \"fiatAmount\": 500,\n" +
                                "    \"coinType\": \"BTC\"\n" +
                                "}"))
                .andExpect(status().isForbidden());
    }

    @Test
    void handleOtherException() throws Exception {
        doThrow(JDBCConnectionException.class).when(coinCalculatorController).convertFiatCurrency(any(ConversionRestRequest.class));
        mockMvcCoinCalculatorController.perform(post("/api/calculator/convert")
                        .contentType(ApplicationConstants.APPLICATION_JSON_UTF_8)
                        .content("{\n" +
                                "    \"fiatCurrency\": \"EUR\",\n" +
                                "    \"fiatAmount\": 500,\n" +
                                "    \"coinType\": \"BTC\"\n" +
                                "}"))
                .andExpect(status().isInternalServerError());
    }
}
