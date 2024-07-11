package com.tak.article.domain.comment.exception;

public class NotExistCommentException extends RuntimeException {
    public NotExistCommentException() {
        super();
    }

    public NotExistCommentException(String message) {
        super(message);
    }

    public NotExistCommentException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotExistCommentException(Throwable cause) {
        super(cause);
    }

    protected NotExistCommentException(String message, Throwable cause, boolean enableSuppression,
                                       boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
