package com.spring.handler.datahandlerspring11.services;

import com.spring.handler.datahandlerspring11.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserServiceV2 {
    List<User> getAllUsers();

    User getUserById(String userId) throws Exception;

    User getUserByUsername(String userName);

    void addSingleUser(User user);

    void deleteSingleUser(String userId);
}
