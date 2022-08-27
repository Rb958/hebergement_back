package com.lsd.logement.exception;

public enum NotFoundMessage implements BaseExceptionMessage {
    RESOURCE_NOT_FOUND(404, "Resource not found"),
    BOOKING_NOTFOUND(404101, "Reservation introuvable"),
    LOCATAIRE_NOT_FOUND(404102, "Locataire Introuvable"),
    LOCAL_NOT_FOUND(404103, "Locale introuvable"),
    CAISSE_NOT_FOUND(404104, "Caisse introuvable"),
    BAIL_NOT_FOUND(404105, "Contract de bail introuvable");

    private final int code;
    private final String message;

    NotFoundMessage(int code, String message) {
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
