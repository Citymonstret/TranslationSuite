package com.intellectualsites.translation.test;

import com.intellectualsites.translation.Translation;

/**
 * Created by Citymonstret on 2014-11-05.
 */
public class TestAnnotations {

    @Translation(description = "Potatos are bosses!", creationDescription = "potato more")
    public static final String key = "hello";

    @Translation(description = "This is a test of the description thingy", creationDescription = "intellectualsites:test")
    public static final String potatoes = "potatisar";

    @Translation
    private static final String NULL_POINTER_EXCEPTION = "nånting är fel...";
}
