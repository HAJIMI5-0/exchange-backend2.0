package com.example.soul.dto;

public class AiSuggestRequest {

    private String message;  // 用户当前输入的问题，例如：useState가 뭔지 잘 모르겠어요

    private String partner;  // 当前聊天对象，例如：haoz / Alex

    private String context;  // 最近聊天记录，用来让 AI 理解两个人聊到哪里了

    public String getMessage() {
        return message;
    }

    public String getPartner() {
        return partner;
    }

    public String getContext() {
        return context;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public void setContext(String context) {
        this.context = context;
    }
}