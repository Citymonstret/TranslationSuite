package com.intellectualsites.translation;

import com.cedarsoftware.util.io.JsonWriter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class JsonTranslationFile extends TranslationFile {

    private File path;
    private TranslationLanguage language;
    private String name;
    private File file = null;
    private HashMap<String, String> map;
    private String[] header;
    private boolean fancyHead = false;
    private JsonTranslationFile instance;

    /**
     * Constructor
     * @param path save path
     * @param language translation language
     * @param name project name
     */
    public JsonTranslationFile(File path, TranslationLanguage language, String name) {
        this.path = path;
        this.language = language;
        this.name = name;
        if(!path.exists()) {
            if(!path.mkdirs()) {
                throw new RuntimeException("Could not create: " + path.getAbsolutePath());
            }
        }
        this.file = new File(path + File.separator + name + "." + language.toString() + ".json");
        if(!file.exists()) {
            try {
                if(!file.createNewFile()) {
                    throw new RuntimeException("Could not create: " + file.getName());
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        map = new HashMap<String, String>();
        this.instance = this;
    }

    @Override
    public TranslationLanguage getLanguage() {
        return language;
    }

    @Override
    public void saveFile() {
        JSONObject object = new JSONObject();
        object.putAll(map);
        try {
            FileWriter f = new FileWriter(file);
            f.write(JsonWriter.formatJson(object.toJSONString()));
            f.flush();
            f.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public TranslationFile read() {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new BufferedReader(new FileReader(file)));
            JSONObject o = (JSONObject) obj;
            for(Object entry : o.entrySet()) {
                map.put(((Map.Entry) entry).getKey().toString(), ((Map.Entry) entry).getValue().toString());
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    @Override
    public void add(String key, String value) {
        map.put(
                key,
                value
        );
    }
}
