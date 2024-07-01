package com.bob.ktssts.entity;

import java.util.Map;
import java.util.Objects;

public class KSxfAgentBean {

	private String FlowID;
	private String FlowName;
	private String Department;
	private String ScenesName;
	private String BeginTime;
	private String EndTime;
	private String Remark;
	// ExecState :执行状态（-1: 正在执行; 0: 手动停止; 1: 执行成功; 2: 执行超时）
	private String ExecState;
	private String IP;
	private String AgentName;
	private String ExecTime;

	public KSxfAgentBean() {
	}

	public KSxfAgentBean(Map<String,Object> beanParamsMap){
		this.FlowID = beanParamsMap.get("FlowID").toString();
		this.FlowName = beanParamsMap.get("FlowName").toString();
		this.Department = beanParamsMap.get("Department").toString();
		this.ScenesName = beanParamsMap.get("ScenesName").toString();
		this.BeginTime = beanParamsMap.get("BeginTime").toString();
		this.EndTime = beanParamsMap.get("EndTime").toString();
		this.Remark = beanParamsMap.get("Remark").toString();
		this.ExecState = beanParamsMap.get("ExecState").toString();
		this.IP = beanParamsMap.get("IP").toString();
		this.AgentName = beanParamsMap.get("AgentName").toString();
		this.ExecTime = beanParamsMap.get("ExecTime").toString();

	}

	public KSxfAgentBean(String flowID, String flowName, String department, String scenesName, String beginTime, String endTime, String remark, String execState, String IP, String agentName, String execTime) {
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
