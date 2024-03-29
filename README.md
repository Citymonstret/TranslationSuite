TranslationSuite
================

Maven/Building
--
To build the program using maven, use
```
mvn package
```

ProGuard
--
If you are obfuscating your plugin using ProGuard, then this is an important step:

```
-keepclassmembers class com.intellectualsites.prison.Configuration {
    private static <fields>;
}
```
Replace the class name with your own class name (every class that contains @Translation)

Desc
--
Java translation file implementation
made easy.

JSON
--
The json parser json-io and json-simple (included in the pom.xml)

Yaml
--
The YAML parser requires SnakeYaml (included in the pom.xml) 

@Translation
--
The most efficient way to create translations is by using the @Translation annotation.
To load the translations use TranslationManager#scan()

To create a translation do:

```java
@Translation( description = "optional comment (will show up in YAML)" )
public static TRANSLATION_KEY = "default value";

// you can also use private fields

@Translation
private static other_translation = "another default value";
```
The key will be converted to lowercase, so make sure to not make duplicate keys.

Examples
--

Bukkit example: https://github.com/Sauilitired/TranslationSuite/blob/master/src/main/java/com/intellectualsites/translation/bukkit/TranslationPlugin.java

Java Example

```java
// This will create a new manager
TranslationManager manager = new TranslationManager();

// This will scan a file for translatable objects
try {
  TranslationManager.scan(TestAnnotations.class, manager);
 } catch(Exception e) {
   e.printStackTrace();
}

// Do this before you load the translations, else you'll have some problems :D
manager.addTranslation("potatoes", new TranslationAsset(null, "potatooooes", TranslationLanguage.swedishSwedish));

// Create the file, read from it, and add the stuff to the manager
TranslationFile file = new YamlTranslationFile(new File("C:\\Users\\Citymonstret\\Pictures\\IntellectualChat\\profiles\\debug"), TranslationLanguage.swedishSwedish, "potatoes", manager).read().header("This is the header", "another line");

// This will save all translations to the file, then save the file to the disk
manager.debug(System.out).saveAll(file).saveFile(file);
```
