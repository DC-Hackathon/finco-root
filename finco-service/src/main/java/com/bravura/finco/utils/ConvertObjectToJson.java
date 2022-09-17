package com.bravura.finco.utils;

import com.google.gson.Gson;

public class ConvertObjectToJson {
    static Gson convertToJson = new Gson();
    public static String convertToJson(Object data) {
        return convertToJson.toJson(data);
    }
}
