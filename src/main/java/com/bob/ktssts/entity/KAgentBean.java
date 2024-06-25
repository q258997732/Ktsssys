package com.bob.ktssts.entity;

import java.awt.*;
import java.util.Objects;

public class KAgentBean {

	private String FlowID;
	private String FlowName;
	private String Department;
	private String ScenesName;
	private String BeginTime;
	private String EndTime;
	private String Remark;
	private String ExecState;
	private String IP;
	private String AgentName;
	private String ExecTime;

	public KAgentBean() {
	}

	public KAgentBean(String flowID, String flowName, String department, String scenesName, String beginTime, String endTime, String remark, String execState, String IP, String agentName, String execTime) {
		FlowID = flowID;
		FlowName = flowName;
		Department = department;
		ScenesName = scenesName;
		BeginTime = beginTime;
		EndTime = endTime;
		Remark = remark;
		ExecState = execState;
		this.IP = IP;
		AgentName = agentName;
		ExecTime = execTime;
	}

	public String getFlowID() {
		return FlowID;
	}

	public void setFlowID(String flowID) {
		FlowID = flowID;
	}

	public String getFlowName() {
		return FlowName;
	}

	public void setFlowName(String flowName) {
		FlowName = flowName;
	}

	public String getDepartment() {
		return Department;
	}

	public void setDepartment(String department) {
		Department = department;
	}

	public String getScenesName() {
		return ScenesName;
	}

	public void setScenesName(String scenesName) {
		ScenesName = scenesName;
	}

	public String getBeginTime() {
		return BeginTime;
	}

	public void setBeginTime(String beginTime) {
		BeginTime = beginTime;
	}

	public String getEndTime() {
		return EndTime;
	}

	public void setEndTime(String endTime) {
		EndTime = endTime;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public String getExecState() {
		return ExecState;
	}

	public void setExecState(String execState) {
		ExecState = execState;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String IP) {
		this.IP = IP;
	}

	public String getAgentName() {
		return AgentName;
	}

	public void setAgentName(String agentName) {
		AgentName = agentName;
	}

	public String getExecTime() {
		return ExecTime;
	}

	public void setExecTime(String execTime) {
		ExecTime = execTime;
	}

	@Override
	public String toString() {
		return String.format("flowID: %s, flowName: %s, department: %s, scenesName: %s, beginTime: %s, endTime: %s, remark: %s, execState: %s, IP: %s, agentName: %s, execTime: %s",
				getFlowID(),
				getFlowName(),
				getDepartment(),
				getScenesName(),
				getBeginTime(),
				getEndTime(),
				getRemark(),
				Objects.requireNonNull(ExecStateEnum.fromCode(getExecState())).getDescription(),
				getIP(),
				getAgentName(),
				getExecTime());
	}

}
