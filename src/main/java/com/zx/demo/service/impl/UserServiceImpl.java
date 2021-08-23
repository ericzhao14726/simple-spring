package com.zx.demo.service.impl;

import com.zx.demo.domain.User;
import com.zx.demo.service.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public User get(Integer id) {
        return new User(1,"张三","男","程序员","天津");
    }

    @Override
    public User list() {
        return new User(1, "张三", "男", "程序员", "天津");
    }
}
