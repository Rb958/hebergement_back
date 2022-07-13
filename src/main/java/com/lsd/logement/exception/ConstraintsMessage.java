package com.lsd.logement.exception;

public enum ConstraintsMessage implements BaseExceptionMessage {
    SCHOOL_APPROVAL_NUM_EXISTS(409101, "School approval number already exists"),
    SCHOOL_NAME_EXISTS(409102, "School name already exists"),
    SCHOOL_ID_EXISTS(409100, "School id already exists")
    ;
    private final int code;
    private final String message;

    ConstraintsMessage(int code, String message) {
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
