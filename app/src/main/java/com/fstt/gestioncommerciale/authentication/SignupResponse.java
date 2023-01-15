package com.fstt.gestioncommerciale.authentication;

public class SignupResponse {
    private boolean success;
    private String message;

    public SignupResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    // Getters et setters

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
