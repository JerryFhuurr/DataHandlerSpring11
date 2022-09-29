package com.spring.handler.datahandlerspring11.sqlservice.impl;

import com.spring.handler.datahandlerspring11.model.User;
import com.spring.handler.datahandlerspring11.sqlmapper.UserMapper;
import com.spring.handler.datahandlerspring11.sqlservice.UserService;
import com.spring.handler.datahandlerspring11.utils.AESUtils;
import com.spring.handler.datahandlerspring11.utils.GuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    private static String AESPassword = "kuitkpy8nzpkyaax";

    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public User getUserById(String userId) {
        User userGet = userMapper.getUserById(userId);
        String passwordAfterDecrypt = AESUtils.decrypt(userGet.getUserPassword(), AESPassword);
        userGet.setUserPassword(passwordAfterDecrypt);
        return userGet;
    }

    @Override
    public User getUserByUsername(String userName) {
        return userMapper.getUserByUsername(userName);
    }

    @Override
    public void addSingleUser(User user) {
            String passwordAfterEncrypt = AESUtils.encrypt(user.getUserPassword(), AESPassword);
            user.setUserId(GuidUtil.getGuid());
            user.setUserPermission(3);
            user.setUserName(user.getUserName());
            user.setUserEmail(user.getUserEmail());
            user.setUserPassword(passwordAfterEncrypt);
            user.setUserPhone(user.getUserPhone());
            userMapper.addSingleUser(user);
    }

    @Override
    public void deleteSingleUser(String userId) {
        userMapper.deleteSingleUser(userId);
    }
}
