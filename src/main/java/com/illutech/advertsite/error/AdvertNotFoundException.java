package com.illutech.advertsite.error;

public class AdvertNotFoundException extends Exception{

    public AdvertNotFoundException() {
        super();
    }

    public AdvertNotFoundException(String message) {
        super(message);
    }

    public AdvertNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdvertNotFoundException(Throwable cause) {
        super(cause);
    }

    protected AdvertNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
