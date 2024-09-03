package com.bob.ktssts.entity.ktss;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * tms注册账号
 * @TableName tss_tms_register
 */
@TableName(value ="tss_tms_register")
@Data
public class TssTmsRegister implements Serializable {
    /**
     * 主键
     */
    @TableId
    private String id;

    /**
     * 创建人
     */
    private String whoCreated;

    /**
     * 创建时间
     */
    private String whenCreated;

    /**
     * 更新人
     */
    private String whoModified;

    /**
     * 更新时间
     */
    private String whenModified;

    /**
     * 所属站点(tss_user_site.user_id)
     */
    private String siteId;

    /**
     * 所属TMS(tss_TMS.id)
     */
    private String tmsId;

    /**
     * 账号名称
     */
    private String name;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态：0暂停，1启用
     */
    private String status;

    /**
     * 错误描述
     */
    private String describes;

    @TableField(exist = false)
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
        TssTmsRegister other = (TssTmsRegister) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getWhoCreated() == null ? other.getWhoCreated() == null : this.getWhoCreated().equals(other.getWhoCreated()))
            && (this.getWhenCreated() == null ? other.getWhenCreated() == null : this.getWhenCreated().equals(other.getWhenCreated()))
            && (this.getWhoModified() == null ? other.getWhoModified() == null : this.getWhoModified().equals(other.getWhoModified()))
            && (this.getWhenModified() == null ? other.getWhenModified() == null : this.getWhenModified().equals(other.getWhenModified()))
            && (this.getSiteId() == null ? other.getSiteId() == null : this.getSiteId().equals(other.getSiteId()))
            && (this.getTmsId() == null ? other.getTmsId() == null : this.getTmsId().equals(other.getTmsId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getDescribes() == null ? other.getDescribes() == null : this.getDescribes().equals(other.getDescribes()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getWhoCreated() == null) ? 0 : getWhoCreated().hashCode());
        result = prime * result + ((getWhenCreated() == null) ? 0 : getWhenCreated().hashCode());
        result = prime * result + ((getWhoModified() == null) ? 0 : getWhoModified().hashCode());
        result = prime * result + ((getWhenModified() == null) ? 0 : getWhenModified().hashCode());
        result = prime * result + ((getSiteId() == null) ? 0 : getSiteId().hashCode());
        result = prime * result + ((getTmsId() == null) ? 0 : getTmsId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getDescribes() == null) ? 0 : getDescribes().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", whoCreated=").append(whoCreated);
        sb.append(", whenCreated=").append(whenCreated);
        sb.append(", whoModified=").append(whoModified);
        sb.append(", whenModified=").append(whenModified);
        sb.append(", siteId=").append(siteId);
        sb.append(", tmsId=").append(tmsId);
        sb.append(", name=").append(name);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", status=").append(status);
        sb.append(", describes=").append(describes);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}