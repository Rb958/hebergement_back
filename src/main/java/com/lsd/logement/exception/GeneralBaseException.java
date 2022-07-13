package com.lsd.logement.exception;

public class GeneralBaseException extends RuntimeException implements BaseExceptionMessage {
    private final int code;

    public GeneralBaseException(BaseExceptionMessage exceptionMessage) {
        super(exceptionMessage.getMessage());
        this.code = exceptionMessage.getCode();
    }

    public GeneralBaseException(int code, String message) {
        super(message);
        this.code = code;
    }

    @Override
    public int getCode() {
        return code;
    }
}
