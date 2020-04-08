package com.github.jferrater.petprofilesservice.exceptions;

public class PetProfileNotFoundException extends RuntimeException{

    public PetProfileNotFoundException(String message) {
        super(message);
    }
}
