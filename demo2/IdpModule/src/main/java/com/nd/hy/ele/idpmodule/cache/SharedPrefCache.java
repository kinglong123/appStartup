package com.nd.hy.ele.idpmodule.cache;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.IOException;

/**
 * SharedPrefCache
 * User: Twinkle
 * Date: 13-4-7
 */
public class SharedPrefCache<K, V> implements ICache<K, V> {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private static final String TAG = "SharedPrefCache";
    private SharedPreferences mSharedPref;
    private Class<? extends V> mClass;
    private static ObjectMapper sObjectMapper = OBJECT_MAPPER;
    private final Object mSync = new Object();

    public static synchronized void setObjectMapper(ObjectMapper mapper) {
        if (mapper == null) {
            return;
        }
        sObjectMapper = mapper;
    }

    public SharedPrefCache(Context context, String identifier, Class<? extends V> clazz) {
        mSharedPref = context.getSharedPreferences(identifier, 0);
        this.mClass = clazz;
    }

    @Override
    public V put(K key, V value) {
        synchronized (mSync) {
            SharedPreferences.Editor editor = getEditor();
            try {
                String content = value == null ? null : sObjectMapper.writeValueAsString(value);
                editor.putString(key.toString(), content);
                editor.commit();
            } catch (JsonProcessingException e) {
                Log.e(TAG, "put error", e);
                return null;
            }
            return value;
        }
    }

    @Override
    public V get(K key) {
        synchronized (mSync) {
            String value = mSharedPref.getString(key.toString(), null);
            if (value != null) {
                try {
                    return sObjectMapper.readValue(value, mClass);
                } catch (IOException e) {
                    Log.e(TAG, "readValue error", e);
                }
            }
            return null;
        }
    }

    @Override
    public V get(K key, V defaultValue) {
        V result = get(key);
        return null == result ? defaultValue : result;
    }

    @Override
    public V remove(K key) {
        synchronized (mSync) {
            V value = get(key);
            SharedPreferences.Editor editor = getEditor();
            editor.remove(key.toString());
            editor.commit();
            return value;
        }
    }

    @Override
    public void clear() {
        synchronized (mSync) {
            SharedPreferences.Editor editor = getEditor();
            editor.clear();
            editor.commit();
        }
    }

    @Override
    @Deprecated
    public boolean isOutOfDate(K key, long interval) {
        return false;
    }

    private SharedPreferences.Editor getEditor() {
        return mSharedPref.edit();
    }



}
