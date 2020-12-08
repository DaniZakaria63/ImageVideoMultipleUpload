package com.daniza.easymultipleuploadimages;

public class MultipleSelectException extends Exception{
    public MultipleSelectException() {
    }

    public MultipleSelectException(String message) {
        super(message);
    }

    public MultipleSelectException(String message, Throwable cause) {
        super(message, cause);
    }

    public MultipleSelectException(Throwable cause) {
        super(cause);
    }
}
