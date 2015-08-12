package com.sapient.utils;

import java.util.HashMap;

public class SetupUtils {
    private static HashMap<String, Object> state = new HashMap<String, Object>();

    private SetupUtils() {}

    public static void saveState(String key, Object value) {
        state.put(key, value);
    }

    public static Object getState(String key) {
        return state.get(key);
    }
}
