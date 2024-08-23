package com.bob.ktssts.entity.cz;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName sealfix_oa_mapping
 */
@TableName(value ="sealfix_oa_mapping")
@Data
public class SealfixOaMapping implements Serializable {
    /**
     * 
     */
    @TableId
    private String id;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 
     */
    private String sealfixTradeno;

    /**
     * 
     */
    private String oaBillcode;

    /**
     * 
     */
    private Integer sucess;

    /**
     * 
     */
    private String err;

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
        SealfixOaMapping other = (SealfixOaMapping) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getSealfixTradeno() == null ? other.getSealfixTradeno() == null : this.getSealfixTradeno().equals(other.getSealfixTradeno()))
            && (this.getOaBillcode() == null ? other.getOaBillcode() == null : this.getOaBillcode().equals(other.getOaBillcode()))
            && (this.getSucess() == null ? other.getSucess() == null : this.getSucess().equals(other.getSucess()))
            && (this.getErr() == null ? other.getErr() == null : this.getErr().equals(other.getErr()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getSealfixTradeno() == null) ? 0 : getSealfixTradeno().hashCode());
        result = prime * result + ((getOaBillcode() == null) ? 0 : getOaBillcode().hashCode());
        result = prime * result + ((getSucess() == null) ? 0 : getSucess().hashCode());
        result = prime * result + ((getErr() == null) ? 0 : getErr().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", sealfixTradeno=").append(sealfixTradeno);
        sb.append(", oaBillcode=").append(oaBillcode);
        sb.append(", sucess=").append(sucess);
        sb.append(", err=").append(err);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}