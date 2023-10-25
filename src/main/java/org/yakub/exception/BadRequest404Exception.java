package org.yakub.exception;

public class BadRequest404Exception extends RuntimeException {
    public BadRequest404Exception(String message) {
        super(message);
    }
}
