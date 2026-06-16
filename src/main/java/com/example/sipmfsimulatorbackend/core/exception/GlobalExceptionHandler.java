package com.example.sipmfsimulatorbackend.core.exception;

import com.example.sipmfsimulatorbackend.core.utils.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(
            ResourceNotFoundException.class
    )
    public ResponseEntity<ApiResponse<Object>>
    handleNotFound(
            ResourceNotFoundException ex
    ) {

        return ResponseEntity.status(
                        HttpStatus.NOT_FOUND
                )
                .body(
                        ApiResponse.error(
                                ex.getMessage()
                        )
                );
    }
}
