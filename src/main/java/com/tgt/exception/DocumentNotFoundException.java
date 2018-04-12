package com.tgt.exception;

public class DocumentNotFoundException extends Exception {

    public DocumentNotFoundException() {
    }

    public DocumentNotFoundException(String s) {
        super(s);
    }

    public DocumentNotFoundException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public DocumentNotFoundException(Throwable throwable) {
        super(throwable);
    }

    public DocumentNotFoundException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
