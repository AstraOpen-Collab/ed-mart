package com.edmart.vendorservice.exception;

public class VendorCreationSuccessException extends Exception{
    public VendorCreationSuccessException() {
        super();
    }

    public VendorCreationSuccessException(String message) {
        super(message);
    }

    public VendorCreationSuccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public VendorCreationSuccessException(Throwable cause) {
        super(cause);
    }

    protected VendorCreationSuccessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
