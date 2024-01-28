package com.edmart.client.exceptions;

public class InventoryNotFoundException extends Exception{

    public InventoryNotFoundException() {
        super();
    }

    public InventoryNotFoundException(String message) {
        super(message);
    }

    public InventoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public InventoryNotFoundException(Throwable cause) {
        super(cause);
    }

    protected InventoryNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
