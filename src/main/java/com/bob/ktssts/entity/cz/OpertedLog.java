package com.bob.ktssts.entity.cz;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @TableName operted_log
 */
@TableName(value ="operted_log")
@Data
public class OpertedLog implements Serializable {
    /**
     *
     */
    @TableId
    private String id;

    /**
     *
     */
    private String operateUser;

    /**
     *
     */
    private Date operateTime;

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
    private String operateDetail;

    @Serial
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
        OpertedLog other = (OpertedLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getOperateUser() == null ? other.getOperateUser() == null : this.getOperateUser().equals(other.getOperateUser()))
                && (this.getOperateTime() == null ? other.getOperateTime() == null : this.getOperateTime().equals(other.getOperateTime()))
                && (this.getSealfixTradeno() == null ? other.getSealfixTradeno() == null : this.getSealfixTradeno().equals(other.getSealfixTradeno()))
                && (this.getOaBillcode() == null ? other.getOaBillcode() == null : this.getOaBillcode().equals(other.getOaBillcode()))
                && (this.getOperateDetail() == null ? other.getOperateDetail() == null : this.getOperateDetail().equals(other.getOperateDetail()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOperateUser() == null) ? 0 : getOperateUser().hashCode());
        result = prime * result + ((getOperateTime() == null) ? 0 : getOperateTime().hashCode());
        result = prime * result + ((getSealfixTradeno() == null) ? 0 : getSealfixTradeno().hashCode());
        result = prime * result + ((getOaBillcode() == null) ? 0 : getOaBillcode().hashCode());
        result = prime * result + ((getOperateDetail() == null) ? 0 : getOperateDetail().hashCode());
        return result;
    }

    @Override
    public String toString() {
		return getClass().getSimpleName() +
				" [" +
				"Hash = " + hashCode() +
				", id=" + id +
				", operateUser=" + operateUser +
				", operateTime=" + operateTime +
				", sealfixTradeno=" + sealfixTradeno +
				", oaBillcode=" + oaBillcode +
				", operateDetail=" + operateDetail +
				", serialVersionUID=" + serialVersionUID +
				"]";
    }
}