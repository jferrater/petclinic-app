package com.github.jferrater.petprofilesservice.exceptions;

/**
 * @author joffryferrater
 */
public class PetProfileNotFoundException extends RuntimeException{

    public PetProfileNotFoundException(String message) {
        super(message);
    }
}
