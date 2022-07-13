package com.lsd.logement.exception;

public enum UserExceptionMessage implements BaseExceptionMessage{
     USER_IS_DISABLED(801, "Cet utilisateur est desactivé");

     private final String message;
     private final int code;

    UserExceptionMessage(int code, String message) {
        this.message = message;
        this.code = code;
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
