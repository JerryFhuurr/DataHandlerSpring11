package com.spring.handler.datahandlerspring11.controller.rediscontroller;

import com.spring.handler.datahandlerspring11.model.User;
import com.spring.handler.datahandlerspring11.services.UserService;
import com.spring.handler.datahandlerspring11.services.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/users/redis")
public class UserRedisController {
    @Autowired
    UserService userService;

    @PostMapping("addUser")
    public String addUser(@Validated @RequestBody User user) {
        userService.addSingleUser(user, 1);
        return user.getUserId() + " is added";
    }

    @GetMapping("get/one/byId")
    public User getUserById(String id) throws Exception {
        return userService.getUserById(id, 1);
    }

}
