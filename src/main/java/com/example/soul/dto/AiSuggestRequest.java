package com.example.soul.dto;

public class AiSuggestRequest {

    private String message;
    private String partner;

    public String getMessage() {
        return message;
    }

    public String getPartner() {
        return partner;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }
}