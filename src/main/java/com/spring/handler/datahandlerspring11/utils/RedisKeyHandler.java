package com.spring.handler.datahandlerspring11.utils;

import com.spring.handler.datahandlerspring11.services.RedisKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

public class RedisKeyHandler implements RedisKey {
    @Autowired
    static RedisTemplate redisTemplate;

    /**
     * 指定key的失效时间
     * Set expire time for the key
     *
     * @param key  Key name
     * @param time Time to expire
     */
    public void expire(String key, long time) {
        redisTemplate.expire(key, time, TimeUnit.MINUTES);
    }

    /**
     * 根据key获取过期时间
     * Get expire time for a key
     *
     * @param key Key name
     * @return Expire time
     */
    public long getExpire(String key) {
        Long expire = redisTemplate.getExpire(key);
        return expire;
    }

    /**
     * 判断key是否存在
     * Check whether a key is existed
     *
     * @param key Key name
     * @return Result
     */
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }


}
