package com.edmart.category.exception;

public class SubCategoryNotFoundException extends RuntimeException{
    public SubCategoryNotFoundException() {
        super("Sub category not found");
    }

    public SubCategoryNotFoundException(String message) {
        super(message);
    }

    public SubCategoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SubCategoryNotFoundException(Throwable cause) {
        super(cause);
    }

    protected SubCategoryNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
