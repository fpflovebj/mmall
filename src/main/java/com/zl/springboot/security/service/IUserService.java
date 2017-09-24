package com.zl.springboot.security.service;

import com.zl.springboot.security.common.ServerResponse;
import com.zl.springboot.security.pojo.User;


public interface IUserService {
    //使用泛型做一個通用的數據相應對象
    ServerResponse<User> login(String username, String password);
    ServerResponse<String> register(User user);
    public ServerResponse<String> checkValid(String str,String type);
}
