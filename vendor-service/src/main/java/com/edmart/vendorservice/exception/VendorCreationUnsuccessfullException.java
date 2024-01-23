package com.edmart.vendorservice.exception;

public class VendorCreationUnsuccessfullException extends Exception{
    public VendorCreationUnsuccessfullException() {
        super();
    }

    public VendorCreationUnsuccessfullException(String message) {
        super(message);
    }

    public VendorCreationUnsuccessfullException(String message, Throwable cause) {
        super(message, cause);
    }

    public VendorCreationUnsuccessfullException(Throwable cause) {
        super(cause);
    }

    protected VendorCreationUnsuccessfullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
