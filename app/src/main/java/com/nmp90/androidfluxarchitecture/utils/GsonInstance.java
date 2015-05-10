package com.nmp90.androidfluxarchitecture.utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.Serializable;
import java.lang.reflect.Type;

/**
 * Created by nmp on 15-5-10.
 */
public class GsonInstance {

    private static Gson sGson = new Gson();
    private static GsonInstance instance;
    private GsonInstance() {}

    public static GsonInstance getInstance() {
        if(instance == null) {
            instance = new GsonInstance();
        }

        return instance;
    }

    public <T> T fromJson(String json, Class<T> classOfT) {
        try {
            return sGson.fromJson(json, classOfT);
        } catch (JsonSyntaxException e) {
            return null;
        }
    }

    public <T> T fromJson(String json, Type typeOfT) {
        try {
            return sGson.fromJson(json, typeOfT);
        } catch (JsonSyntaxException e) {
            return null;
        }
    }

    public String toJson(Serializable dto) {
        return sGson.toJson(dto);
    }

    public String toJson(Object object) {
        return sGson.toJson(object);
    }
}
