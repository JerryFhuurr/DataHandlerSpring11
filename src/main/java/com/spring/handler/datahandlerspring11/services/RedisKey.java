package com.spring.handler.datahandlerspring11.services;

public interface RedisKey {
    void expire(String key, long time);

    long getExpire(String key);

    boolean hasKey(String key);
}
