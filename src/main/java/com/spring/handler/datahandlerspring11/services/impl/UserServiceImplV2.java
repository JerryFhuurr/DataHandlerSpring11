package com.spring.handler.datahandlerspring11.services.impl;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.spring.handler.datahandlerspring11.model.User;
import com.spring.handler.datahandlerspring11.services.UserServiceV2;
import com.spring.handler.datahandlerspring11.sqlmapper.UserMapper;
import com.spring.handler.datahandlerspring11.utils.AESUtils;
import com.spring.handler.datahandlerspring11.utils.GuidUtil;
import com.spring.handler.datahandlerspring11.utils.MapToObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userServices")
public class UserServiceImplV2 implements UserServiceV2 {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RedisTemplate redisTemplate;

    private static final String AESPassword = "kuitkpy8nzpkyaax";

    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    /**
     * Looking for a user by id
     * Steps:
     * 1. Find redis db first, if you find the user and return
     * 2. If not go to MYSQL, if you find the user add it to redis and return it
     *
     * @param userId
     * @return User object
     */
    @Override
    public User getUserById(String userId) {
        User user;
        String userGet = redisTemplate.opsForValue().get(userId).toString();
        if (!userGet.equals(null)) {
            user = JSON.parseObject(userGet, User.class);
            user.setUserPassword(AESUtils.decrypt(user.getUserPassword(), AESPassword));
        } else {
            // find user in mysql and add it to redis
            user = userMapper.getUserById(userId);
            String passwordAfterDecrypt = AESUtils.decrypt(user.getUserPassword(), AESPassword);
            user.setUserPassword(passwordAfterDecrypt);

            addToRedis(user);
        }
        return user;
    }

    private void addToRedis(User user) {
        user.setUserPassword(AESUtils.encrypt(user.getUserPassword(), AESPassword));
        Map<String, String> userMap = new HashMap<>();
        userMap.put("userId", user.getUserId());
        userMap.put("userName", user.getUserName());
        userMap.put("userPassword", user.getUserPassword());
        userMap.put("userPermission", "3");
        userMap.put("userEmail", user.getUserEmail());
        userMap.put("userPhone", user.getUserPhone());
        redisTemplate.opsForValue().set(user.getUserId(), userMap);
    }

    @Override
    public User getUserByUsername(String userName) {
        return userMapper.getUserByUsername(userName);
    }

    @Override
    public void addSingleUser(User user) {
        String passwordAfterEncrypt = AESUtils.encrypt(user.getUserPassword(), AESPassword);
        String id = GuidUtil.getZcbhid();
        user.setUserId(id);
        user.setUserPermission(3);
        user.setUserName(user.getUserName());
        user.setUserEmail(user.getUserEmail());
        user.setUserPassword(passwordAfterEncrypt);
        user.setUserPhone(user.getUserPhone());
        userMapper.addSingleUser(user);
        addToRedis(user);
    }

    @Override
    public void deleteSingleUser(String userId) {
        userMapper.deleteSingleUser(userId);
        redisTemplate.delete(userId);
    }
}