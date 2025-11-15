package com.tharthara.Controller;

import com.tharthara.Entity.User;
import com.tharthara.Service.UserService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController() {
    }

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public void register(@RequestBody User user){
        userService.register(user);
    }
    @PostMapping("/{username}/online")
    public void online( @PathVariable String username){
        userService.setOnline(username);
    }
    @PostMapping("/{username}/offline")
    public void offline( @PathVariable String username){
        userService.setOffline(username);
    }
}
