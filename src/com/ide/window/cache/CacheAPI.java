package com.ide.window.cache;

public interface CacheAPI {

    void add(String key, Object value, long periodInMillis);

    void remove(String key);

    Object get(String key);

    void clear();

    long size();
}
