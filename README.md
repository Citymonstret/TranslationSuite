TranslationSuite
================

Desc
--
Java translation file implementation
made easy.

Yaml
--
The YAML parser required SnakeYaml (included in the pom.xml) 

Examples
--
```java
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
YamlTranslationFile file = new YamlTranslationFile(new File("C:\\Users\\Citymonstret\\Pictures\\IntellectualChat\\profiles\\debug"), TranslationLanguage.swedishSwedish, "potatoes").read().header("This is the header", "another line");

// This will save all translations to the file, then save the file to the disk
manager.debug(System.out).saveAll(file).saveFile(file);
```
