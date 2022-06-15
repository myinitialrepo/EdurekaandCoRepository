package com.selenium.exceptions;

public class PropertyLoadException extends RuntimeException {

    public PropertyLoadException() {
        super();
    }

    public PropertyLoadException(String message) {
        super(message);
    }

    public PropertyLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public PropertyLoadException(Throwable cause) {
        super(cause);
    }

    protected PropertyLoadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
