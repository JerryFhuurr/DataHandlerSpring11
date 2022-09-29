package com.spring.handler.datahandlerspring11.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.handler.datahandlerspring11.model.User;
import com.spring.handler.datahandlerspring11.sqlmapper.UserMapper;
import com.spring.handler.datahandlerspring11.services.UserService;
import com.spring.handler.datahandlerspring11.utils.AESUtils;
import com.spring.handler.datahandlerspring11.utils.GuidUtil;
import com.spring.handler.datahandlerspring11.utils.MapToObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    private static String AESPassword = "kuitkpy8nzpkyaax";

    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    /**
     * Get a user from database by id
     *
     * @param userId
     * @param type   0 -> MYSQL, 1 -> Redis
     * @return
     * @throws Exception
     */
    @Override
    public User getUserById(String userId, int type) throws Exception {
        User userGet = null;
        switch (type) {
            case 0:
                userGet = userMapper.getUserById(userId);
                String passwordAfterDecrypt = AESUtils.decrypt(userGet.getUserPassword(), AESPassword);
                userGet.setUserPassword(passwordAfterDecrypt);
            case 1:
                Map o = redisTemplate.opsForHash().entries(userId);
                userGet = MapToObj.mapToObj(o, User.class);
        }

        return userGet;
    }

    @Override
    public User getUserByUsername(String userName) {
        return userMapper.getUserByUsername(userName);
    }

    /**
     * Add user into database
     *
     * @param user User item
     * @param type 0 -> SQL, 1 -> Redis
     */
    @Override
    public void addSingleUser(User user, int type) {
        String passwordAfterEncrypt = AESUtils.encrypt(user.getUserPassword(), AESPassword);
        String id = GuidUtil.getZcbhid();
        user.setUserId(id);
        user.setUserPermission(3);
        user.setUserName(user.getUserName());
        user.setUserEmail(user.getUserEmail());
        user.setUserPassword(passwordAfterEncrypt);
        user.setUserPhone(user.getUserPhone());

        switch (type) {
            case 0:
                sqlMapperAdd(user);
            case 1:
                redisAdd(id, passwordAfterEncrypt, user);
        }
    }

    private void sqlMapperAdd(User user) {
        userMapper.addSingleUser(user);
    }

    private void redisAdd(String id, String passwordAfterEncrypt, User user) {
        Map<String, String> values = new HashMap<>();
        values.put("userId", id);
        values.put("userName", user.getUserName());
        values.put("userPassword", passwordAfterEncrypt);
        values.put("userPermission", "3");
        values.put("userEmail", user.getUserEmail());
        values.put("userPhone", user.getUserPhone());
        redisTemplate.opsForHash().putAll(id, values);
    }

    @Override
    public void deleteSingleUser(String userId) {
        userMapper.deleteSingleUser(userId);
    }
}
