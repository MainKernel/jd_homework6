package com.goit.homwork6.feature.pref;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Preferences {
    private static final String PREFERENCES_FILE = "./preferences.json";
    public static final String DATABASE_URL = "databaseUrl";
    public static final String DATABASE_LOGIN = "databaseLogin";
    public static final String DATABASE_PASSWORD = "databasePassword";

    Map<String, Object> pref = new HashMap<>();

    public Preferences() {
        this(PREFERENCES_FILE);
    }

    private Preferences(String filename) {
        try {
            String join = String.join("\n", Files.readAllLines(Path.of(filename)));
            pref = new Gson().fromJson(join,
                    TypeToken.getParameterized(Map.class, String.class, Object.class).getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object getPref(String key) {
        return pref.get(key);
    }

    public String getString(String key) {
        return getPref(key).toString();
    }

}
