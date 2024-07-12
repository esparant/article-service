package com.tak.article.domain.member.exception;


public class NotEqualMemberException extends RuntimeException {

    public NotEqualMemberException() {
        super();
    }

    public NotEqualMemberException(String message) {
        super(message);
    }

    public NotEqualMemberException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEqualMemberException(Throwable cause) {
        super(cause);
    }

    protected NotEqualMemberException(String message, Throwable cause, boolean enableSuppression,
                                      boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
