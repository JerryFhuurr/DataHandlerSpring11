package com.spring.handler.datahandlerspring11.services.impl;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.spring.handler.datahandlerspring11.model.User;
import com.spring.handler.datahandlerspring11.services.RedisKey;
import com.spring.handler.datahandlerspring11.services.UserServiceV2;
import com.spring.handler.datahandlerspring11.sqlmapper.UserMapper;
import com.spring.handler.datahandlerspring11.utils.AESUtils;
import com.spring.handler.datahandlerspring11.utils.GuidUtil;
import com.spring.handler.datahandlerspring11.utils.MapToObj;
import com.spring.handler.datahandlerspring11.utils.RedisKeyHandler;
import com.spring.handler.datahandlerspring11.utils.exceptions.ReqExceptions;
import com.spring.handler.datahandlerspring11.utils.exceptions.common.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
     * @param currentId
     * @return
     * @throws NullPointerException
     */
    @Override
    public User getUserById(String userId, String currentId) {
        User user;
        String userGet;
        try {
            userGet = redisTemplate.opsForValue().get(userId).toString();
            user = JSON.parseObject(userGet, User.class);
            if (userId.equals(currentId)) {
                user.setUserPassword(AESUtils.decrypt(user.getUserPassword(), AESPassword));
            }
        } catch (NullPointerException e) {
            // find user in mysql and add it to redis
            user = userMapper.getUserById(userId);
            String passwordAfterDecrypt = AESUtils.decrypt(user.getUserPassword(), AESPassword);
            user.setUserPassword(passwordAfterDecrypt);

            addToRedis(user);
            getUserById(user.getUserId(), currentId);
        }

        return user;
    }

    private void addToRedis(User user) {
        Map<String, String> userMap = new HashMap<>();
        userMap.put("userId", user.getUserId());
        userMap.put("userName", user.getUserName());
        userMap.put("userPassword", user.getUserPassword());
        userMap.put("userPermission", "3");
        userMap.put("userEmail", user.getUserEmail());
        userMap.put("userPhone", user.getUserPhone());
        userMap.put("userDesc", user.getUserDesc());
        redisTemplate.opsForValue().set(user.getUserId(), userMap);
        // All the user keys in redis will be expired after 2 days
        redisTemplate.expire(user.getUserId(), 2880, TimeUnit.MINUTES);
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
        user.setUserDesc(user.getUserDesc());
        userMapper.addSingleUser(user);
        addToRedis(user);
    }

    @Override
    public String deleteSingleUser(String userId, int currentUserPermission) {
        try {
            User user = getUserById(userId, "0");
            if (currentUserPermission < 3) {
                userMapper.deleteSingleUser(userId, currentUserPermission);
                redisTemplate.delete(userId);
                return userId + " is removed";
            } else {
                throw new ReqExceptions(ErrorCode.User.USER_PERMISSION_INVALID, "Insufficient privileges, administrator privileges are required");
            }
        } catch (NullPointerException e) {
            throw new ReqExceptions(ErrorCode.User.USER_NOT_FOUND, "Cannot find this user!");
        }
    }

    @Override
    public String updateSingleUser(User user) {
        userMapper.updateSingleUser(user);
        putInfoForRedis(user);
        redisTemplate.opsForValue().set(user.getUserId(), putInfoForRedis(user));
        // All the user keys in redis will be expired after 2 days
        redisTemplate.expire(user.getUserId(), 2880, TimeUnit.MINUTES);
        return user.getUserId() + " updated";
    }

    private Map<String, String> putInfoForRedis(User user) {
        Map<String, String> userMap = new HashMap<>();
        userMap.put("userId", user.getUserId());
        userMap.put("userName", user.getUserName());
        userMap.put("userPassword", user.getUserPassword());
        userMap.put("userPermission", "3");
        userMap.put("userEmail", user.getUserEmail());
        userMap.put("userPhone", user.getUserPhone());
        userMap.put("userDesc", user.getUserDesc());
        return userMap;
    }

    @Override
    public String updateSingleUserPassword(User user) {
        String newPasswordEncrypt = AESUtils.encrypt(user.getUserPassword(), AESPassword);
        user.setUserPassword(newPasswordEncrypt);
        userMapper.updateSingleUserPassword(user);
        putInfoForRedis(user);
        redisTemplate.opsForValue().set(user.getUserId(), putInfoForRedis(user));
        return "Password is updated for " + user.getUserId();
    }

}
