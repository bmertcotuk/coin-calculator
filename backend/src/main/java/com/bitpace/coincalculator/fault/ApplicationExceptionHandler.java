package com.bitpace.coincalculator.fault;

import com.bitpace.coincalculator.ApplicationStatusCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Mert Cotuk
 */
@Slf4j
@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorRestResponse> handleAppException(ApplicationException e) {
        final var errorCode = e.getErrorCode();
        final var errorDescription = ApplicationStatusCodes.getDescription(errorCode);
        log.info("handleApplicationException::errorCode={}::errorDescription={}", errorCode, errorDescription);
        final var errorResponse = new ErrorRestResponse(errorCode, errorDescription);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorRestResponse> handleAccessDeniedException(AccessDeniedException e) {
        final var errorCode = ApplicationStatusCodes.ERR_ACCESS_DENIED;
        final var errorDescription = ApplicationStatusCodes.getDescription(errorCode);
        log.info("handleAccessDeniedException: {}, {}", errorCode, errorDescription);
        final var errorResponse = new ErrorRestResponse(errorCode, errorDescription);
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorRestResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        final var errors = e.getBindingResult();
        final var errorResponse = convertErrors(errors, DefaultMessageSourceResolvable::getDefaultMessage);
        log.info("handleMethodArgumentNotValidException: {}", errorResponse);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorRestResponse> handleGlobalException(Exception e) {
        final var errorCode = ApplicationStatusCodes.ERR_GENERAL_ERROR;
        final var errorDescription = ApplicationStatusCodes.getDescription(errorCode);
        log.error("handleGlobalException::errorCode={}::errorDescription={}", errorCode, errorDescription, e);
        final var errorResponse = new ErrorRestResponse(errorCode, errorDescription);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ValidationErrorRestResponse convertErrors(
            Errors errors, Function<DefaultMessageSourceResolvable, String> errorCodeExtractor) {
        final var globalErrors = errors.getGlobalErrors().stream()
                .map(error -> new ErrorRestResponse(errorCodeExtractor.apply(error)))
                .collect(Collectors.toList());
        final var mappedFieldErrors = errors.getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField,
                        fieldError -> new ErrorRestResponse(errorCodeExtractor.apply(fieldError)), (lhs, rhs) -> rhs));
        return new ValidationErrorRestResponse(globalErrors, mappedFieldErrors);
    }
}
