package com.lsd.logement.security.payload.response;

import com.lsd.logement.exception.GeneralBaseException;

public class ResponseException {

    private String message;
    private int status;

    public ResponseException(GeneralBaseException e) {
        this.message = e.getMessage();
        this.status = e.getCode();
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
