package com.spring.handler.datahandlerspring11.sqlmapper;

import com.spring.handler.datahandlerspring11.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> getAllUsers();

    User getUserById(String userId);

    User getUserByUsername(String userName);

    void addSingleUser(User user);

    void deleteSingleUser(String userId, int currentUserPermission);

    void updateSingleUser(User user);

    void updateSingleUserPassword(User user);

}
