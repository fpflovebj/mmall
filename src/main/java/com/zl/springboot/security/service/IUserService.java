package com.zl.springboot.security.service;

import com.zl.springboot.security.common.ServerResponse;
import com.zl.springboot.security.pojo.User;


public interface IUserService {
    //使用泛型做一個通用的數據相應對象
    ServerResponse<User> login(String username, String password);
    ServerResponse<String> register(User user);
    public ServerResponse<String> checkValid(String str,String type);
    public ServerResponse selectQuestion(String username);
    public ServerResponse<String> checkAnswer(String username,String question,String answer);
    public ServerResponse<String> forgetRestPassword(String username, String PasswordNew, String forgetToken);
    public  ServerResponse<String> resetPassword(String passwordOld,String passwordNew,User user);
    public ServerResponse<User> updateInfomation(User user);
    public ServerResponse<User> get_infomation(Integer userId);
}
