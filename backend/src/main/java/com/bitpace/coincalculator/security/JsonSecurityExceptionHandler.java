/*
package com.bitpace.coincalculator.security;

import com.bitpace.coincalculator.fault.ErrorRestResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RequiredArgsConstructor
public class JsonSecurityExceptionHandler implements AccessDeniedHandler, AuthenticationEntryPoint {
    private static final Logger LOG = LoggerFactory.getLogger(JsonSecurityExceptionHandler.class);

    private final String applicationName;
    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        LOG.info("AuthenticationException: {}, {}", authException.getMessage(), authException.getClass());
        response.addHeader("WWW-Authenticate", "Basic realm=\"" + applicationName + "\"");
        response.setHeader("Content-Type", "application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        objectMapper.writeValue(response.getOutputStream(), new ErrorRestResponse(ERR_UNAUTHORIZED));
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        LOG.info("AccessDeniedException: {}, {}", accessDeniedException.getMessage(), accessDeniedException.getClass());
        response.setHeader("Content-Type", "application/json");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        objectMapper.writeValue(response.getOutputStream(), new ErrorRestResponse(ERR_ACCESS_DENIED));
    }
}
*/
