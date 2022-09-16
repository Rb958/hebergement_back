package com.lsd.logement.exception;

public enum ConstraintsMessage implements BaseExceptionMessage {
    LOCAL_IS_NOT_FREE(409101, "Ce local n'est pas libre pour l'instant"),
    MISMATCH_STATUS(409102, "Impossible de proceder a ce paiement");
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
