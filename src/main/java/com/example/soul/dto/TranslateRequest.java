package com.example.soul.dto;

public class TranslateRequest {

    private String text;
    private String targetLang;

    public String getText() {
        return text == null ? "" : text.trim();
    }

    public String getTargetLang() {

        if (targetLang == null) {
            return "English";
        }

        switch (targetLang.toLowerCase()) {

            case "zh":
            case "zh-cn":
            case "中文":
                return "Chinese";

            case "en":
            case "english":
                return "English";

            case "ko":
            case "kr":
            case "한국어":
            case "korean":
                return "Korean";

            case "ja":
            case "jp":
            case "日本語":
                return "Japanese";

            case "fr":
            case "français":
                return "French";

            case "de":
            case "deutsch":
                return "German";

            case "es":
            case "español":
                return "Spanish";

            case "ar":
            case "العربية":
                return "Arabic";

            default:
                return "English";
        }
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTargetLang(String targetLang) {
        this.targetLang = targetLang;
    }
}