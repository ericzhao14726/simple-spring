package com.zx.demo.mapper;

import com.zx.demo.domain.User;
import com.zx.simpleSpring.annotation.ioc.Component;

@Component
public interface UserMapper {


    User get(Integer id);

    User list();
}
