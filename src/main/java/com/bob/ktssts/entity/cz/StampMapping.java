package com.bob.ktssts.entity.cz;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName stamp_mapping
 */
@TableName(value ="stamp_mapping")
@Data
public class StampMapping implements Serializable {
    /**
     * 
     */
    @TableId
    private String id;

    /**
     * 
     */
    private String stampType;

    /**
     * 
     */
    private String stampFileDetail;

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
        StampMapping other = (StampMapping) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStampType() == null ? other.getStampType() == null : this.getStampType().equals(other.getStampType()))
            && (this.getStampFileDetail() == null ? other.getStampFileDetail() == null : this.getStampFileDetail().equals(other.getStampFileDetail()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStampType() == null) ? 0 : getStampType().hashCode());
        result = prime * result + ((getStampFileDetail() == null) ? 0 : getStampFileDetail().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", stampType=").append(stampType);
        sb.append(", stampFileDetail=").append(stampFileDetail);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}