package com.zl.springboot.security.service;

import com.zl.springboot.security.bean.Users;

import java.util.List;

public interface UserService {
    List<Users> queryAll();

    Users queryUser(Users users);
}
