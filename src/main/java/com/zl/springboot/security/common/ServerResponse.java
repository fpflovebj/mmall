package com.zl.springboot.security.common;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;
//通用的用泛型構造的服務端相應對象
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//保證序列化json的時候，如果是null的對象,key也會消失
public class ServerResponse<T> implements Serializable{
    private int status;
    private String msg;
    private T data;
    private ServerResponse(int status){
        this.status = status;
    }
    private  ServerResponse(int status,T data){
        this.status = status;
        this.data =data;
    }
    private  ServerResponse(int status,String msg,T data){
        this.status = status;
        this.data =data;
        this.msg = msg;
    }
    private  ServerResponse(int status,String msg){
        this.status = status;
        this.msg = msg;
    }
    @JsonIgnore//表示在序列化是不會出現在json裡邊
    public  boolean isSuccess(){
        return this.status==ResponseCode.SUCCESS.getCode();
    }
    public int getStatus(){
        return  status;
    }
    public  T getData(){
        return  data;
    }
    public  String getMsg(){
        return  msg;
    }
    public  static <T> ServerResponse createBySuccess(){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
    }
    public  static <T> ServerResponse createBySuccessMessage(String msg){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
    }
    public  static <T> ServerResponse<T> createBySuccess(T data){
        return  new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),data);
    }
    public  static <T> ServerResponse<T> createBySuccess(String msg,T data){
        return  new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg,data);
    }

    public static <T> ServerResponse<T> createByError(){
        return  new ServerResponse<T>(ResponseCode
        .ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }
    public  static <T> ServerResponse<T> createByErrorMessage(String errorMessage){
        return  new ServerResponse<T>(ResponseCode.ERROR.getCode(),errorMessage);
    }
    public static <T> ServerResponse<T> createByErrorCodeMessage(int errorCode,String errorMessage){
        return new ServerResponse<T>(errorCode,errorMessage);
    }
}
