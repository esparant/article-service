package com.tak.article.domain.exception.signup;

public class NotUniqueNicknameException extends NotUniqueException {
    public NotUniqueNicknameException() {
    }

    public NotUniqueNicknameException(String message) {
        super(message);
    }

    public NotUniqueNicknameException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotUniqueNicknameException(Throwable cause) {
        super(cause);
    }

    public NotUniqueNicknameException(String message, Throwable cause, boolean enableSuppression,
                                      boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
