package com.zl.springboot.security.service.impl;

import com.zl.springboot.security.common.Const;
import com.zl.springboot.security.common.ServerResponse;
import com.zl.springboot.security.common.TokenCache;
import com.zl.springboot.security.dao.UserMapper;
import com.zl.springboot.security.pojo.User;
import com.zl.springboot.security.service.IUserService;
import com.zl.springboot.security.util.MD5Util;
import org.apache.catalina.Server;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("iUserService")
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public ServerResponse<User> login(String username, String password) {
    int resultCount = userMapper.checkUsername(username);
    if(resultCount == 0){
        return ServerResponse.createByErrorMessage("用戶名不存在");
    }
    //todo 密碼登錄,md5加密，為了在數據庫存儲的時候，密碼不能為明文的
       String md5Password = MD5Util.MD5EncodeUtf8(password);
        User user = userMapper.selectLogin(username,md5Password);
    if(user == null){
        return ServerResponse.createByErrorMessage("密碼錯誤");
    }//如果密碼錯誤，就把密碼滯空
        user.setPassword(StringUtils.EMPTY);
    return ServerResponse.createBySuccess("登錄成功",user);
    }
    public ServerResponse<String> register(User user) {
        ServerResponse valiResponse = this.checkValid(user.getUsername(),Const.USERNAME);
        if(!valiResponse.isSuccess()){
                return valiResponse;
            }
        valiResponse = this.checkValid(user.getEmail(),Const.EMAIL);
        if(!valiResponse.isSuccess()){
            return valiResponse;
        }

        user.setRole(Const.Role.ROLE_CUSTOMER);
        //MD5加密、即使扒庫也看不到密碼
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        int resultCount = userMapper.insert(user);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    }
    public ServerResponse<String> checkValid(String str,String type){
        //认为传过来的值为空格的时候返会为false
        if(StringUtils.isNotBlank(type)){
            //开始校验
            if(Const.USERNAME.equals(type)){
                int resultCount = userMapper.checkUsername(str);
                if (resultCount > 0) {
                    return ServerResponse.createByErrorMessage("用戶名已存在");
                }
            }
            if(Const.EMAIL.equals(type)){
               int resultCount = userMapper.checkEmail(str);
                if (resultCount > 0) {
                    return ServerResponse.createByErrorMessage("email已存在");
                }
            }
        }else{
            return ServerResponse.createByErrorMessage("参数错误");
        }
        return ServerResponse.createBySuccessMessage("检验成功");
    }
    public ServerResponse selectQuestion(String username){
        ServerResponse validResponse = this.checkValid(username,Const.USERNAME);
        if(validResponse.isSuccess()){
            //说明用户不存在
            return  ServerResponse.createByErrorMessage("用户存在");
        }
        String question = userMapper.selectQuestionByUsername(username);
        if(StringUtils.isNotBlank(question)){
        return ServerResponse.createBySuccess(question);
        }
        return ServerResponse.createByErrorMessage("找回密码的问题是空的");
    }

    public ServerResponse<String> checkAnswer(String username,String question,String answer){
        int resultCount = userMapper.checkAnswer(username,question,answer);
        if(resultCount>0){
            //说明问题及问题答案是这个用户的，并且是正确的,uuid生成不可重复的字符串
            //把forgetToken放到本地cache中，并设置有效期
            String forgetToken = UUID.randomUUID().toString();
            TokenCache.setKey("token_"+username,forgetToken);
            return ServerResponse.createBySuccess(forgetToken);


        }
        return ServerResponse.createByErrorMessage("问题答案错误");
    }
}
