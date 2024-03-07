package com.edmart.client.exceptions;

public class DiscountNotFoundException extends Exception{
    public DiscountNotFoundException() {
        super();
    }

    public DiscountNotFoundException(String message) {
        super(message);
    }

    public DiscountNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DiscountNotFoundException(Throwable cause) {
        super(cause);
    }

    protected DiscountNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
