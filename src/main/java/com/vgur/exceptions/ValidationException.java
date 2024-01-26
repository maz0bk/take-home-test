package com.vgur.exceptions;

import java.util.List;


public class ValidationException extends RuntimeException {
    private final List<String> errorFieldMessages;

    public ValidationException(List<String> errors) {
        super(String.join(", ", errors));
        this.errorFieldMessages = errors;
    }

    public List<String> getErrorFieldMessages() {
        return errorFieldMessages;
    }
}
