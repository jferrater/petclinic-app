package com.github.jferrater.petprofilesservice.exceptions;

import com.github.jferrater.petprofilesservice.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author joffryferrater
 */
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(PetProfileNotFoundException.class)
    public ResponseEntity<ApiError> handlePetProfileNotFoundException(PetProfileNotFoundException e) {
        ApiError apiError = new ApiError(404, "Not Found", e.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception e) {
        ApiError apiError = new ApiError(500, "Internal Server Error", e.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
