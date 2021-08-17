package com.zx.demo.controller;

import com.zx.simpleSpring.annotation.ioc.Autowired;
import com.zx.demo.service.UserService;
import com.zx.simpleSpring.annotation.mvc.GetMapping;
import com.zx.simpleSpring.annotation.mvc.RestController;

@RestController("/test")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String get(Integer id) {
        return userService.get(id).toString();
    }

    @GetMapping("/list")
    public String list() {
        return userService.list().toString();
    }
}
