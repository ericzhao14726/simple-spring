package com.zx.demo.service.impl;

import com.zx.simpleSpring.annotation.ioc.Autowired;
import com.zx.demo.domain.User;
import com.zx.demo.mapper.UserMapper;
import com.zx.demo.service.UserService;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User get(Integer id) {
        return userMapper.get(id);
    }

    @Override
    public User list() {
        return userMapper.list();
    }
}
