package com.zx.demo.service;

import com.zx.simpleSpring.annotation.ioc.Service;
import com.zx.demo.domain.User;


@Service
public interface UserService {

    User get(Integer id);

    User list();

    User insert(User user);
}
