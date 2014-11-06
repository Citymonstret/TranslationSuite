package com.intellectualsites.translation;

import com.intellectualsites.translation.test.TestAnnotations;

import java.io.File;

public class TranslationMain {

    public static boolean staticAccess = true;
    public static void main(String[] args) {
        assert staticAccess;

        // This will create a new manager
        TranslationManager manager = TranslationManager.instance();

        // This will scan a file for translatable objects
        try {
            TranslationManager.scan(TestAnnotations.class, manager);
        } catch(Exception e) {
            e.printStackTrace();
        }

        // Do this before you load the translations, else you'll have some problems :D
        manager.addTranslation("potatoes", new TranslationAsset(null, "potatooooes", TranslationLanguage.swedishSwedish));

        // Create the file, read from it, and add the stuff to the manager
        TranslationFile swedish = new YamlTranslationFile(new File("C:\\Users\\Citymonstret\\Pictures\\IntellectualChat\\profiles\\debug"), TranslationLanguage.swedishSwedish, "potatoes")
        .read().fancyHeader("Hello", "my", "name", "is", "alex");

        TranslationFile english = new YamlTranslationFile(new File("C:\\Users\\Citymonstret\\Pictures\\IntellectualChat\\profiles\\debug"), TranslationLanguage.englishAmerican, "potatoes")
        .read().fancyHeader("This", "is", "in", "english!");

        // This will save all translations to the file, then save the file to the disk
        manager.debug(System.out).saveAll(swedish).saveFile(swedish).saveAll(english).saveFile(swedish);

        String str = manager.getTranslation("potatoes", TranslationLanguage.swedishSwedish);
        System.out.println(str);
    }
}
