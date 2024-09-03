package com.bob.ktssts.entity.usrmgt;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName ts_apiuser
 */
@Data
public class TsApiuser implements Serializable {
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

    /**
     * 
     */
    private String permission;

    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TsApiuser other = (TsApiuser) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUser() == null ? other.getUser() == null : this.getUser().equals(other.getUser()))
            && (this.getPass() == null ? other.getPass() == null : this.getPass().equals(other.getPass()))
            && (this.getToken() == null ? other.getToken() == null : this.getToken().equals(other.getToken()))
            && (this.getExpire() == null ? other.getExpire() == null : this.getExpire().equals(other.getExpire()))
            && (this.getRole() == null ? other.getRole() == null : this.getRole().equals(other.getRole()))
            && (this.getPermission() == null ? other.getPermission() == null : this.getPermission().equals(other.getPermission()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUser() == null) ? 0 : getUser().hashCode());
        result = prime * result + ((getPass() == null) ? 0 : getPass().hashCode());
        result = prime * result + ((getToken() == null) ? 0 : getToken().hashCode());
        result = prime * result + ((getExpire() == null) ? 0 : getExpire().hashCode());
        result = prime * result + ((getRole() == null) ? 0 : getRole().hashCode());
        result = prime * result + ((getPermission() == null) ? 0 : getPermission().hashCode());
        return result;
    }

    @Override
    public String toString() {
		return getClass().getSimpleName() +
				" [" +
				"Hash = " + hashCode() +
				", id=" + id +
				", user=" + user +
				", pass=" + pass +
				", token=" + token +
				", expire=" + expire +
				", role=" + role +
				", permission=" + permission +
				", serialVersionUID=" + serialVersionUID +
				"]";
    }
}