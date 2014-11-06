package com.intellectualsites.translation;

public class TranslationObject {

    // can include a suffix
    private String key;
    //The default ("no-translation" value)
    private String defaultValue;
    // ... "Join message" ...
    private String description;
    // Like a plugin name for example
    private String creationDescription;

    public TranslationObject(String key, String defaultValue, String description, String creationDescription) {
        for(char c : key.toCharArray()) {
            if(!Character.isDigit(c) && !Character.isAlphabetic(c) && c != '_' && c != '&' && c != '§' && c != ':') {
                throw new RuntimeException(
                        String.format("Translation: '%s' is invalid (Character: '%s') - Only alphanumeric + (\\, _, &, §, :) charcters are allowed",
                                key, c + ""
                        )
                );
            }
        }
        this.key = key.toLowerCase();
        this.defaultValue = defaultValue.replace("\n", "&-");;
        this.description = description;
        this.creationDescription = creationDescription;
    }

    public String getKey() {
        return key;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public String getDescription() {
        return description;
    }

    public String getCreationDescription() {
        return creationDescription;
    }

}
