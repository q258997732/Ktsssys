package com.bob.ktssts.entity;

import lombok.Data;

import java.util.Map;

@Data
public class KFlowBean {

	public KFlowBean() {
	}

	public KFlowBean(String flowID, String PID, String flowName, String modifyTime, String createTime, String isDisable, String version, String audit, String isPassed, String flowVer, String remark, String bsID, String bsFlow, String tagID, String tagColor, String tagName, String importance) {
		FlowID = flowID;
		this.PID = PID;
		FlowName = flowName;
		ModifyTime = modifyTime;
		CreateTime = createTime;
		IsDisable = isDisable;
		Version = version;
		Audit = audit;
		IsPassed = isPassed;
		FlowVer = flowVer;
		Remark = remark;
		BsID = bsID;
		BsFlow = bsFlow;
		TagID = tagID;
		TagColor = tagColor;
		TagName = tagName;
		Importance = importance;
	}

	public KFlowBean(Map<String,Object> beanParamsMap) {
		this.FlowID = (String) beanParamsMap.get("FlowID");
		this.PID = (String) beanParamsMap.get("PID");
		this.FlowName = (String) beanParamsMap.get("FlowName");
		this.ModifyTime = (String) beanParamsMap.get("ModifyTime");
		this.CreateTime = (String) beanParamsMap.get("CreateTime");
		this.IsDisable = (String) beanParamsMap.get("IsDisable");
		this.Version = (String) beanParamsMap.get("Version");
		this.Audit = (String) beanParamsMap.get("Audit");
		this.IsPassed = (String) beanParamsMap.get("IsPassed");
		this.FlowVer = (String) beanParamsMap.get("FlowVer");
		this.Remark = (String) beanParamsMap.get("Remark");
		this.BsID = (String) beanParamsMap.get("BsID");
		this.BsFlow = (String) beanParamsMap.get("BsFlow");
		this.TagID = (String) beanParamsMap.get("TagID");
		this.TagColor = (String) beanParamsMap.get("TagColor");
		this.TagName = (String) beanParamsMap.get("TagName");
		this.Importance = (String) beanParamsMap.get("Importance");
	}

	private String FlowID;
	private String PID;
	private String FlowName;
	private String ModifyTime;
	private String CreateTime;
	private String IsDisable;
	private String Version;
	private String Audit;
	private String IsPassed;
	private String FlowVer;
	private String Remark;
	private String BsID;
	private String BsFlow;
	private String TagID;
	private String TagColor;
	private String TagName;
	private String Importance;

	// 以Json格式拼接
	public String toString(){
		return "{\"FlowID\":\""+FlowID+"\",\"PID\":\""+PID+"\",\"FlowName\":\""+FlowName+"\",\"ModifyTime\":\""+ModifyTime+"\",\"CreateTime\":\""+CreateTime+"\",\"IsDisable\":\""+IsDisable+"\",\"Version\":\""+Version+"\",\"Audit\":\""+Audit+"\",\"IsPassed\":\""+IsPassed+"\",\"FlowVer\":\""+FlowVer+"\",\"Remark\":\""+Remark+"\",\"BsID\":\""+BsID+"\",\"BsFlow\":\""+BsFlow+"\",\"TagID\":\""+TagID+"\",\"TagColor\":\""+TagColor+"\",\"TagName\":\""+TagName+"\",\"Importance\":\""+Importance+"\"}";
	}


}
