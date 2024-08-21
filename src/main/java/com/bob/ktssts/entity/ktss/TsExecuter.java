package com.bob.ktssts.entity.ktss;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import lombok.Data;

/**
 * ts_executer
 *
 * @TableName ts_executer
 */
@Data
public class TsExecuter implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
	/**
	 * 租户号;唯一标识
	 */
	private String id;
	/**
	 * 更新人;更新人
	 */
	private String update_by;
	/**
	 * 更新时间;更新时间
	 */
	private Date update_time;
	/**
	 * 执行器名称;执行器名称
	 */
	private String exec_name;
	/**
	 * 执行器地址;执行器所在机器地址（通常为IP）
	 */
	private String exec_addr;
	/**
	 * 执行器类型;执行器类型
	 */
	private String exec_type;
	/**
	 * 执行器可用状态;执行器是否可用
	 */
	private String exec_available;
	/**
	 * 是否独占;当执行器为独占执行器时不参与任务分配
	 */
	private String exec_monopoly;
	/**
	 * 执行器注册时间;执行器注册进模块的时间
	 */
	private Date exec_register;
	/**
	 * 执行器版本信息;执行器版本
	 */
	private String exec_version;

	private String exec_online;

	public TsExecuter() {

	}

	public TsExecuter(String exec_name, String exec_addr, String exec_type, String exec_available, String exec_monopoly, Date exec_register, String exec_version) {
		this.id = UUID.randomUUID().toString().replace("-","").toLowerCase();
		this.exec_name = exec_name;
		this.exec_addr = exec_addr;
		this.exec_type = exec_type;
		this.exec_available = exec_available;
		this.exec_monopoly = exec_monopoly;
		this.exec_register = exec_register;
		this.exec_version = exec_version;
	}

	public TsExecuter(String id, String update_by, Date update_time, String exec_name, String exec_addr, String exec_type, String exec_available, String exec_monopoly, Date exec_register, String exec_version, String exec_online) {
		this.id = id;
		this.update_by = update_by;
		this.update_time = update_time;
		this.exec_name = exec_name;
		this.exec_addr = exec_addr;
		this.exec_type = exec_type;
		this.exec_available = exec_available;
		this.exec_monopoly = exec_monopoly;
		this.exec_register = exec_register;
		this.exec_version = exec_version;
        this.exec_online = exec_online;
	}

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
		TsExecuter other = (TsExecuter) that;
		return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
				&& (this.getUpdate_by() == null ? other.getUpdate_by() == null : this.getUpdate_by().equals(other.getUpdate_by()))
				&& (this.getUpdate_time() == null ? other.getUpdate_time() == null : this.getUpdate_time().equals(other.getUpdate_time()))
				&& (this.getExec_name() == null ? other.getExec_name() == null : this.getExec_name().equals(other.getExec_name()))
				&& (this.getExec_addr() == null ? other.getExec_addr() == null : this.getExec_addr().equals(other.getExec_addr()))
				&& (this.getExec_type() == null ? other.getExec_type() == null : this.getExec_type().equals(other.getExec_type()))
				&& (this.getExec_available() == null ? other.getExec_available() == null : this.getExec_available().equals(other.getExec_available()))
				&& (this.getExec_monopoly() == null ? other.getExec_monopoly() == null : this.getExec_monopoly().equals(other.getExec_monopoly()))
				&& (this.getExec_register() == null ? other.getExec_register() == null : this.getExec_register().equals(other.getExec_register()))
				&& (this.getExec_version() == null ? other.getExec_version() == null : this.getExec_version().equals(other.getExec_version()))
				&& (this.getExec_online() == null ? other.getExec_online() == null : this.getExec_online().equals(other.getExec_online()));

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((getUpdate_by() == null) ? 0 : getUpdate_by().hashCode());
		result = prime * result + ((getUpdate_time() == null) ? 0 : getUpdate_time().hashCode());
		result = prime * result + ((getExec_name() == null) ? 0 : getExec_name().hashCode());
		result = prime * result + ((getExec_addr() == null) ? 0 : getExec_addr().hashCode());
		result = prime * result + ((getExec_type() == null) ? 0 : getExec_type().hashCode());
		result = prime * result + ((getExec_available() == null) ? 0 : getExec_available().hashCode());
		result = prime * result + ((getExec_monopoly() == null) ? 0 : getExec_monopoly().hashCode());
		result = prime * result + ((getExec_register() == null) ? 0 : getExec_register().hashCode());
		result = prime * result + ((getExec_version() == null) ? 0 : getExec_version().hashCode());
		result = prime * result + ((getExec_online() == null) ? 0 : getExec_online().hashCode());
		return result;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", update_by=").append(update_by);
		sb.append(", update_time=").append(update_time);
		sb.append(", exec_name=").append(exec_name);
		sb.append(", exec_addr=").append(exec_addr);
		sb.append(", exec_type=").append(exec_type);
		sb.append(", exec_available=").append(exec_available);
		sb.append(", exec_monopoly=").append(exec_monopoly);
		sb.append(", exec_register=").append(exec_register);
		sb.append(", exec_version=").append(exec_version);
		sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", exec_online=").append(exec_online);
		sb.append("]");
		return sb.toString();
	}
}