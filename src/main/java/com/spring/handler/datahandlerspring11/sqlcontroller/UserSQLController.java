package com.spring.handler.datahandlerspring11.sqlcontroller;

import com.spring.handler.datahandlerspring11.model.User;
import com.spring.handler.datahandlerspring11.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users/sql")
public class UserSQLController {
    @Autowired
    UserService userServiceSQL;

    @GetMapping("get/allList")
    public List<User> getAllUsersSQL() {
        return userServiceSQL.getAllUsers();
    }

    @GetMapping("get/byId")
    public User getUserById(String id) throws Exception {
        return userServiceSQL.getUserById(id, 0);
    }

    @PostMapping("addUser")
    public String addSingleUser(@Validated @RequestBody User user) {
        userServiceSQL.addSingleUser(user, 0);
        return user.getUserId() + " is added";
    }

    @DeleteMapping("delete/byId")
    public String removeSingleUser(String id) {
        userServiceSQL.deleteSingleUser(id);
        return id + " is removed";
    }
}
