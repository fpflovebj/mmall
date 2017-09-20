package com.zl.springboot.security.dao;

import com.zl.springboot.security.bean.Users;

import java.util.List;

public interface UsersMapper {
    List<Users> queryAll();

    Users queryUser(Users users);
}
