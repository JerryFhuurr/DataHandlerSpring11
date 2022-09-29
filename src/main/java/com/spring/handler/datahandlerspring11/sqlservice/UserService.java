package com.spring.handler.datahandlerspring11.sqlservice;

import com.spring.handler.datahandlerspring11.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getAllUsers();

    User getUserById(String userId);

    User getUserByUsername(String userName);

    void addSingleUser(User user);

    void deleteSingleUser(String userId);
}
