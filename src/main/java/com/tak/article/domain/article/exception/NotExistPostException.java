package com.tak.article.domain.article.exception;

public class NotExistPostException extends RuntimeException {
    public NotExistPostException() {
        super();
    }

    public NotExistPostException(String message) {
        super(message);
    }

    public NotExistPostException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotExistPostException(Throwable cause) {
        super(cause);
    }

    protected NotExistPostException(String message, Throwable cause, boolean enableSuppression,
                                    boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
