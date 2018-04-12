package com.tgt.exception;

public class ProcessingException extends Exception {

    public ProcessingException() {
    }

    public ProcessingException(String s) {
        super(s);
    }

    public ProcessingException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ProcessingException(Throwable throwable) {
        super(throwable);
    }

    public ProcessingException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
