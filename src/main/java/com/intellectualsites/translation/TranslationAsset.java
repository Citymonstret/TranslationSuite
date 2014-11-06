package com.intellectualsites.translation;

public class TranslationAsset {

    private TranslationObject trans;
    private String translated;
    private TranslationLanguage lang;

    public TranslationAsset(TranslationObject trans, String translated, TranslationLanguage lang) {
        this.trans = trans;
        this.translated = translated;
        this.lang = lang;
    }

    public TranslationObject getObject() {
        return trans;
    }

    public String getTranslated() {
        return translated;
    }

    public TranslationLanguage getLang() {
        return lang;
    }
}
