package com.tak.article.domain.exception.signup;

public class NotUniqueUsernameException extends NotUniqueException {
    public NotUniqueUsernameException() {
    }

    public NotUniqueUsernameException(String message) {
        super(message);
    }

    public NotUniqueUsernameException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotUniqueUsernameException(Throwable cause) {
        super(cause);
    }

    public NotUniqueUsernameException(String message, Throwable cause, boolean enableSuppression,
                                      boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
