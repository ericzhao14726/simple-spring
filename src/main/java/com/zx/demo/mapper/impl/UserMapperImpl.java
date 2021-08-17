package com.zx.demo.mapper.impl;

import com.zx.demo.mapper.UserMapper;
import com.zx.demo.domain.User;

public class UserMapperImpl implements UserMapper {
    @Override
    public User get(Integer id) {
        return new User(1,"张三","男","程序员","天津");
    }

    @Override
    public User list() {
        return new User(1, "张三", "男", "程序员", "天津");
    }
}
