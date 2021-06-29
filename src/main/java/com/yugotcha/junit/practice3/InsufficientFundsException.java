package com.yugotcha.junit.practice3;

class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String message) {
        super(message);
    }

    private static final long serialVersionUID = 1L;
}