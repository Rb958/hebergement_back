package com.lsd.logement.exception;

public enum ForbiddenMessage implements BaseExceptionMessage {
    SCHOOL_ID_CHANGE(403100, "The School id can't be changed")
    ;
    private final int code;
    private final String message;

    ForbiddenMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
