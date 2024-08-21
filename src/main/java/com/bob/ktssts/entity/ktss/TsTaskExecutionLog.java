package com.bob.ktssts.entity.ktss;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName ts_task_execution_log
 */
@Data
public class TsTaskExecutionLog implements Serializable {

    public TsTaskExecutionLog() {
        super();
    }

    /**
     * 新执行任务只需要插入以下数据
     * @param id 日志记录ID
     * @param taskId 任务ID
     * @param startTime 开始时间
     * @param status 状态
     */
    public TsTaskExecutionLog(String id,String taskId,Date startTime,String status) {
        this.taskId = taskId;
        this.startTime = startTime;
        this.status = status;
        this.id = id;
    }

    public TsTaskExecutionLog(String id, String taskId, Date startTime, Date endTime, String status, String resultData, String errorMessage, Integer duration, Integer retryCount, String executerId) {
        this.id = id;
        this.taskId = taskId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.resultData = resultData;
        this.errorMessage = errorMessage;
        this.duration = duration;
        this.retryCount = retryCount;
        this.executerId = executerId;
    }

    /**
     * 日志记录ID
     */
    private String id;

    /**
     * 任务ID，与ts_task表的id字段对应
     */
    private String taskId;

    /**
     * 任务开始执行时间
     */
    private Date startTime;

    /**
     * 任务结束执行时间
     */
    private Date endTime;

    /**
     * 任务执行状态，如：成功、失败、进行中等
     */
    private String status;

    /**
     * 任务执行结果数据
     */
    private String resultData;

    /**
     * 错误信息，任务失败时记录
     */
    private String errorMessage;

    /**
     * 任务执行耗时，单位为毫秒
     */
    private Integer duration;

    /**
     * 任务重试次数
     */
    private Integer retryCount;

    /**
     * 执行器ID，任务实际使用的执行器
     */
    private String executerId;

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
        TsTaskExecutionLog other = (TsTaskExecutionLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTaskId() == null ? other.getTaskId() == null : this.getTaskId().equals(other.getTaskId()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getResultData() == null ? other.getResultData() == null : this.getResultData().equals(other.getResultData()))
            && (this.getErrorMessage() == null ? other.getErrorMessage() == null : this.getErrorMessage().equals(other.getErrorMessage()))
            && (this.getDuration() == null ? other.getDuration() == null : this.getDuration().equals(other.getDuration()))
            && (this.getRetryCount() == null ? other.getRetryCount() == null : this.getRetryCount().equals(other.getRetryCount()))
            && (this.getExecuterId() == null ? other.getExecuterId() == null : this.getExecuterId().equals(other.getExecuterId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTaskId() == null) ? 0 : getTaskId().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getResultData() == null) ? 0 : getResultData().hashCode());
        result = prime * result + ((getErrorMessage() == null) ? 0 : getErrorMessage().hashCode());
        result = prime * result + ((getDuration() == null) ? 0 : getDuration().hashCode());
        result = prime * result + ((getRetryCount() == null) ? 0 : getRetryCount().hashCode());
        result = prime * result + ((getExecuterId() == null) ? 0 : getExecuterId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", taskId=").append(taskId);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", status=").append(status);
        sb.append(", resultData=").append(resultData);
        sb.append(", errorMessage=").append(errorMessage);
        sb.append(", duration=").append(duration);
        sb.append(", retryCount=").append(retryCount);
        sb.append(", executerId=").append(executerId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}