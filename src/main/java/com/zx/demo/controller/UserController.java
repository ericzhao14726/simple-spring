package com.zx.demo.controller;

import com.zx.demo.domain.User;
import com.zx.simpleSpring.annotation.ioc.Autowired;
import com.zx.demo.service.UserService;
import com.zx.simpleSpring.annotation.mvc.GetMapping;
import com.zx.simpleSpring.annotation.mvc.PostMapping;
import com.zx.simpleSpring.annotation.mvc.RestController;

@RestController("/test")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public User get(Integer id) {
        return userService.get(id);
    }

    @GetMapping("/list")
    public User list() {
        return userService.list();
    }

    @PostMapping("/insert")
    public User insert(User user){
        return userService.insert(user);
    }
}
