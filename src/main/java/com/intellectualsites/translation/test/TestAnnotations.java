package com.intellectualsites.translation.test;

import com.intellectualsites.translation.Translation;

/**
 * Test class
 * @author Citymonstret
 */
public class TestAnnotations {

    /*
        This might now be as obvious as I first expected it to be xD

        Oh well.

        The @Translation annotation (com.intellectualsites.translation.Translation)
        will make a translationobject from the string (has to be a string btw) using
        the field name as the key, and the field value as the value.

        Declaring a description will create a comment above the string in the translation
        file (if the extension supports it - yaml is an example)

        The field can be private, as we are using reflection to make the fields accessible.

        Use TranslationManager.scan to scan the file (static method)
     */

    @Translation(description = "Potatos are bosses!", creationDescription = "potato more")
    public static final String key = "hello";

    @Translation(description = "This is a test of the description thingy", creationDescription = "intellectualsites:test")
    public static final String potatoes = "potatisar";

    @Translation
    private static final String NULL_POINTER_EXCEPTION = "nånting är fel...";
}
