package com.spring.handler.datahandlerspring11.services;

import com.spring.handler.datahandlerspring11.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getAllUsers();

    User getUserById(String userId, int type) throws Exception;

    User getUserByUsername(String userName);

    void addSingleUser(User user, int type);

    void deleteSingleUser(String userId);
}
