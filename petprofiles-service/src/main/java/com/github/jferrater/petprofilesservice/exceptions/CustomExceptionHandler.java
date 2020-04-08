package com.github.jferrater.petprofilesservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(PetProfileNotFoundException.class)
    public ResponseEntity<ApiError> handlePetProfileNotFoundException(PetProfileNotFoundException e) {
        ApiError apiError = new ApiError(404, "Not Found", e.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
}
