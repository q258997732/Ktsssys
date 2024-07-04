package com.bob.ktssts.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;


@Data
public class TsTask implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
	/**
	 * 租户号;唯一标识
	 */
	private String id;
	/**
	 * 更新人;更新人
	 */
	private String updateBy;
	/**
	 * 更新时间;更新时间
	 */
	private Date updateTime;
	/**
	 * 新增人;新增人
	 */
	private String addBy;
	/**
	 * 新增时间;新增时间
	 */
	private Date addTime;
	/**
	 * 任务名称;任务名称
	 */
	private String taskName;
	/**
	 * 任务描述;任务描述
	 */
	private String taskDesc;
	/**
	 * 执行周期类型;执行周期类型:
	 * CRON：cron表达式
	 * CYCLE：循环执行周期
	 */
	private String scheduleType;
	/**
	 * 执行周期配置;执行周期配置
	 */
	private String scheduleConf;
	/**
	 * 执行时参数;执行时参数
	 */
	private String execParam;
	/**
	 * 执行超时时间;执行超时时间
	 */
	private Integer execTimeout;
	/**
	 * 重试次数;重试次数
	 */
	private Integer execRetry;
	/**
	 * 是否独占;任务是否独占一个执行器或执行器组
	 */
	private String execMonopoly;
	/**
	 * 所需执行器类型;所需执行器类型
	 */
	private String execType;
	/**
	 * 指定执行器;任务指定的执行器，为空时随机指定
	 */
	private String execAppoint;
	/**
	 * 是否主模板;false为具体任务，true为任务模板
	 */
	private String clone;
	private String executerId;
	private String available;

	public TsTask() {
	}

	public TsTask(String id, String updateBy, Date updateTime, String addBy, Date addTime, String taskName, String taskDesc, String scheduleType, String scheduleConf, String execParam, Integer execTimeout, Integer execRetry, String execMonopoly, String execType, String execAppoint, String clone, String executerId,String available) {
		this.id = id;
		this.updateBy = updateBy;
		this.updateTime = updateTime;
		this.addBy = addBy;
		this.addTime = addTime;
		this.taskName = taskName;
		this.taskDesc = taskDesc;
		this.scheduleType = scheduleType;
		this.scheduleConf = scheduleConf;
		this.execParam = execParam;
		this.execTimeout = execTimeout;
		this.execRetry = execRetry;
		this.execMonopoly = execMonopoly;
		this.execType = execType;
		this.execAppoint = execAppoint;
		this.clone = clone;
		this.executerId = executerId;
		this.available = available;
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
		TsTask other = (TsTask) that;
		return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
				&& (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
				&& (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
				&& (this.getAddBy() == null ? other.getAddBy() == null : this.getAddBy().equals(other.getAddBy()))
				&& (this.getAddTime() == null ? other.getAddTime() == null : this.getAddTime().equals(other.getAddTime()))
				&& (this.getTaskName() == null ? other.getTaskName() == null : this.getTaskName().equals(other.getTaskName()))
				&& (this.getTaskDesc() == null ? other.getTaskDesc() == null : this.getTaskDesc().equals(other.getTaskDesc()))
				&& (this.getScheduleType() == null ? other.getScheduleType() == null : this.getScheduleType().equals(other.getScheduleType()))
				&& (this.getScheduleConf() == null ? other.getScheduleConf() == null : this.getScheduleConf().equals(other.getScheduleConf()))
				&& (this.getExecParam() == null ? other.getExecParam() == null : this.getExecParam().equals(other.getExecParam()))
				&& (this.getExecTimeout() == null ? other.getExecTimeout() == null : this.getExecTimeout().equals(other.getExecTimeout()))
				&& (this.getExecRetry() == null ? other.getExecRetry() == null : this.getExecRetry().equals(other.getExecRetry()))
				&& (this.getExecMonopoly() == null ? other.getExecMonopoly() == null : this.getExecMonopoly().equals(other.getExecMonopoly()))
				&& (this.getExecType() == null ? other.getExecType() == null : this.getExecType().equals(other.getExecType()))
				&& (this.getExecAppoint() == null ? other.getExecAppoint() == null : this.getExecAppoint().equals(other.getExecAppoint()))
				&& (this.getClone() == null ? other.getClone() == null : this.getClone().equals(other.getClone()))
				&& (this.getAvailable() == null ? other.getAvailable() == null : this.getAvailable().equals(other.getAvailable()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
		result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
		result = prime * result + ((getAddBy() == null) ? 0 : getAddBy().hashCode());
		result = prime * result + ((getAddTime() == null) ? 0 : getAddTime().hashCode());
		result = prime * result + ((getTaskName() == null) ? 0 : getTaskName().hashCode());
		result = prime * result + ((getTaskDesc() == null) ? 0 : getTaskDesc().hashCode());
		result = prime * result + ((getScheduleType() == null) ? 0 : getScheduleType().hashCode());
		result = prime * result + ((getScheduleConf() == null) ? 0 : getScheduleConf().hashCode());
		result = prime * result + ((getExecParam() == null) ? 0 : getExecParam().hashCode());
		result = prime * result + ((getExecTimeout() == null) ? 0 : getExecTimeout().hashCode());
		result = prime * result + ((getExecRetry() == null) ? 0 : getExecRetry().hashCode());
		result = prime * result + ((getExecMonopoly() == null) ? 0 : getExecMonopoly().hashCode());
		result = prime * result + ((getExecType() == null) ? 0 : getExecType().hashCode());
		result = prime * result + ((getExecAppoint() == null) ? 0 : getExecAppoint().hashCode());
		result = prime * result + ((getClone() == null) ? 0 : getClone().hashCode());
        result = prime * result + ((getAvailable() == null) ? 0 : getAvailable().hashCode());
		return result;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", updateBy=").append(updateBy);
		sb.append(", updateTime=").append(updateTime);
		sb.append(", addBy=").append(addBy);
		sb.append(", addTime=").append(addTime);
		sb.append(", taskName=").append(taskName);
		sb.append(", taskDesc=").append(taskDesc);
		sb.append(", scheduleType=").append(scheduleType);
		sb.append(", scheduleConf=").append(scheduleConf);
		sb.append(", execParam=").append(execParam);
		sb.append(", execTimeout=").append(execTimeout);
		sb.append(", execRetry=").append(execRetry);
		sb.append(", execMonopoly=").append(execMonopoly);
		sb.append(", execType=").append(execType);
		sb.append(", execAppoint=").append(execAppoint);
		sb.append(", clone=").append(clone);
		sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append(", available=").append(available);
		sb.append("]");
		return sb.toString();
	}
}