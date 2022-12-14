package com.spring.handler.datahandlerspring11.controller;

import com.spring.handler.datahandlerspring11.model.User;
import com.spring.handler.datahandlerspring11.services.impl.UserServiceImplV2;
import com.spring.handler.datahandlerspring11.services.validateGroup.UserValidate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users/controller")
public class UserController {
    @Autowired
    UserServiceImplV2 userServices;

    @GetMapping("get/all")
    public List<User> getAllUsers() {
        return userServices.getAllUsers();
    }

    @GetMapping("get/single/id")
    public User getSingleUserById(String id, String currentId) {
        return userServices.getUserById(id, currentId);
    }

    @PostMapping("add/single")
    public String addSingleUser(@Validated(UserValidate.class) @RequestBody User user) {
        userServices.addSingleUser(user);
        return user.getUserId() + " is added";
    }

    @DeleteMapping("remove/single")
    public String removeSingleUser(String id, int currentUserPermission) {
        String r = userServices.deleteSingleUser(id, currentUserPermission);
        return r;
    }

    @PutMapping("update/single/profile")
    public String updateUserProfile(@Validated(UserValidate.class) @RequestBody User user) {
        return userServices.updateSingleUser(user);
    }

    @PutMapping("update/single/password")
    public String updateUserPassword(@Validated(UserValidate.class) @RequestBody User user) {
        return userServices.updateSingleUserPassword(user);
    }

}
