package com.bob.ktssts.domain;


import java.io.Serializable;
import java.util.Date;

public class ApiUser implements Serializable {

    private String id;

    private String user;

    private String pass;

    private String token;

    private Date expire;

    public void setId(String id){
    this.id = id;
    }

    public void setUser(String user){
    this.user = user;
    }

    public void setPass(String pass){
    this.pass = pass;
    }

    public void setToken(String token){
    this.token = token;
    }

    public void setExpire(Date expire){
    this.expire = expire;
    }

    public String getId(){
    return this.id;
    }

    public String getUser(){
    return this.user;
    }

    public String getPass(){
    return this.pass;
    }

    public String getToken(){
    return this.token;
    }

    public Date getExpire(){
    return this.expire;
    }

}
