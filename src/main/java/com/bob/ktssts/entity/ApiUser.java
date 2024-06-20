package com.bob.ktssts.entity;

import java.util.Date;

public class ApiUser {

    public ApiUser() {
        super();
    }

    public ApiUser(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public ApiUser(String user, String pass, Date expire, String token) {
        this.user = user;
        this.pass = pass;
        this.expire = expire;
        this.token = token;
    }

    /**
    * 唯一标识
    */
    private String id;

    /**
    * 接口账号
    */
    private String user;

    /**
    * 接口密码
    */
    private String pass;

    /**
    * 授权信息
    */
    private String token;

    /**
    * 过期时间
    */
    private Date expire;

    /**
    * 角色
    */
    private String role;

    private String permission;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", user=").append(user);
        sb.append(", pass=").append(pass);
        sb.append(", token=").append(token);
        sb.append(", expire=").append(expire);
        sb.append(", role=").append(role);
        sb.append(", permission=").append(permission);
        sb.append("]");
        return sb.toString();
    }
}