package com.intellectualsites.translation;

import java.io.PrintStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

public class TranslationManager {

    private static TranslationManager manager;

    public static TranslationManager instance(TranslationObject[] translationObjects) {
        if(manager == null) {
            manager = new TranslationManager(translationObjects);
        }
        return manager;
    }

    public static TranslationManager instance() {
        return instance(new TranslationObject[]{});
    }

    private LinkedList<TranslationObject> translationObjects;
    private LinkedHashMap<String, TranslationAsset> translatedObjects;

    public List<TranslationObject> translations() {
        return translationObjects;
    }

    public TranslationManager(TranslationObject[] translationObjects) {
        this.translationObjects
                = new LinkedList<TranslationObject>(Arrays.asList(translationObjects));
        this.translatedObjects
                = new LinkedHashMap<String, TranslationAsset>();
    }

    public TranslationManager addTranslationObject(TranslationObject t) {
        translationObjects.add(t);
        return instance();
    }

    public TranslationManager removeTranslationObject(TranslationObject t) {
        translationObjects.remove(t);
        return instance();
    }

    public String getDescription(String key) {
        for(TranslationObject o : translations()) {
            if(o.getKey().equals(key) && !o.getDescription().equals("")) {
                return "# " + o.getDescription();
            }
        }
        return "";
    }

    public TranslationManager addTranslation(TranslationObject t, TranslationAsset a) {
        return addTranslation(t.getKey(), a);
    }

    public TranslationManager addTranslation(String key, TranslationAsset a) {
        String eKey = key + "." + a.getLang().toString();
        if(translatedObjects.containsKey(eKey))
            translatedObjects.remove(eKey);
        translatedObjects.put(eKey, a);
        return instance();
    }

    public TranslationAsset getTranslated(String key, TranslationLanguage language) {
        String eKey = key + "." + language.toString();
        if(!translatedObjects.containsKey(eKey))
            return new TranslationAsset(getDefault(key), getDefault(key).getKey(), TranslationLanguage.englishAmerican);
        return translatedObjects.get(eKey);
    }

    public TranslationAsset getTranslated(TranslationObject t, TranslationLanguage l) {
        return getTranslated(t.getKey(), l);
    }

    public String getTranslation(String key, TranslationLanguage l) {
        return getTranslated(key, l).getTranslated();
    }

    public TranslationObject getDefault(String key) {
        for(TranslationObject o : translations())
            if(o.getKey().equals(key.toLowerCase()))
                return o;
        return null;
    }

    public TranslationManager saveAll(TranslationFile file) {
        for(TranslationObject object : translations()) {
            TranslationAsset o = getTranslated(object.getKey(), file.getLanguage());
            file.add(object.getKey(), o.getTranslated());
        }
        return instance();
    }

    public static void scan(Class c, TranslationManager manager) throws IllegalAccessException {
        Field[] fields = c.getDeclaredFields();
        Annotation annotation;
        for(Field field : fields) {
            if(field.getType() != String.class || (annotation = field.getAnnotation(Translation.class)) == null)
                continue;
            Translation t = (Translation) annotation;
            String key = field.getName();
            // Make sure we can get the value
            field.setAccessible(true);
            String defaultValue = (String) field.get(c);
            manager.addTranslationObject(
                    new TranslationObject(
                            key,
                            defaultValue,
                            t.description(),
                            t.creationDescription()
                    )
            );
        }
    }

    public TranslationManager debug(PrintStream out) {
        for(TranslationObject object : manager.translations()) {
            out.println(object.getKey() + ":");
            for(TranslationLanguage language : TranslationLanguage.values()) {
                out.println(language.toString() + ": " + manager.getTranslated(object.getKey(), language).getTranslated());
            }
        }
        return instance();
    }

    public TranslationManager saveFile(TranslationFile file) {
        file.saveFile();
        return instance();
    }
}