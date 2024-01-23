package com.edmart.client.exceptions;

public class VendorNotFoundException extends Exception{

    public VendorNotFoundException() {
        super();
    }

    public VendorNotFoundException(String message) {
        super(message);
    }

    public VendorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public VendorNotFoundException(Throwable cause) {
        super(cause);
    }

    protected VendorNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
