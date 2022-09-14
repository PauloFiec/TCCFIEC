package com.fiec.GSense.Utils;


import org.springframework.http.HttpStatus;

public enum ResultCodesException {

    USER_ALREADY_EXISTS(500, 48084, "The user is already present in system"),
    DEVICE_ALREADY_EXISTS(500,48085,"This device is already present in system");

    private final int id;
    private final int frontCode;
    private final String message;

    ResultCodesException(int id, int frontCode, String message) {
        this.id = id;
        this.frontCode = frontCode;
        this.message = message;
    }

    public HttpStatus getId() {
        return HttpStatus.valueOf(id);
    }

    public String getMessage() {
        return message;
    }

    public int getFrontCode() {
        return frontCode;
    }
}

