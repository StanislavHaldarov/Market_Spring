package com.market.util.exception;

import org.springframework.security.core.AuthenticationException;

public class PasswordVerificationException extends AuthenticationException {
    public PasswordVerificationException(String msg) {
        super(msg);
    }

    public PasswordVerificationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
