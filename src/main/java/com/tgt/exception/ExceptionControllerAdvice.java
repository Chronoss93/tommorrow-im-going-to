package com.tgt.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    @ExceptionHandler(DocumentNotFoundException.class)
    public ResponseEntity<ApiError> handleDatabaseExceptions(DocumentNotFoundException exc) {
        ApiError error = new ApiError(HttpStatus.NOT_FOUND, "Document not found", exc.getMessage());
        log.debug("Document not found: %s", exc.getMessage());
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler({ProcessingException.class, IOException.class})
    public ResponseEntity<ApiError> handleImageProcessingExceptions(Exception exc) {
        ApiError error = new ApiError(HttpStatus.UNPROCESSABLE_ENTITY, "Processing error", exc.getMessage());
        log.debug("Processing error: %s", exc.getMessage());
        return new ResponseEntity<>(error, error.getStatus());
    }
}
