package com.intellectualsites.translation;

public abstract class TranslationFile  {

    public abstract TranslationLanguage getLanguage();
    public abstract void saveFile();
    public abstract TranslationFile read();
    public abstract void add(String key, String value);

}
