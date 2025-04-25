package com.michele.caniglia.Esame.Java.exception;

/**
 * Eccezione personalizzata per risorse non trovate (404).
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
