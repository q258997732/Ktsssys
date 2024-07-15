package com.bob.ktssts.entity;

import lombok.Data;

import java.util.Map;

@Data
public class KAgentThreadBean {

	public KAgentThreadBean() {
	}

	public KAgentThreadBean(String ID, String IP, String name, String online, String threadCount, String execCount, String waitCount) {
		this.ID = ID;
		this.IP = IP;
		Name = name;
		Online = online;
		ThreadCount = threadCount;
		ExecCount = execCount;
		WaitCount = waitCount;
	}

	public KAgentThreadBean(Map<String,Object> beanParamsMap){
		this.ID = (String) beanParamsMap.get("ID");
		this.IP = (String) beanParamsMap.get("IP");
		this.Name = (String) beanParamsMap.get("Name");
		this.Online = (String) beanParamsMap.get("Online");
		this.ThreadCount = (String) beanParamsMap.get("ThreadCount");
		this.ExecCount = (String) beanParamsMap.get("ExecCount");
		this.WaitCount = (String) beanParamsMap.get("WaitCount");
	}

	private String ID;
	private String IP;
	private String Name;
	private String Online;
	private String ThreadCount;
	private String ExecCount;
	private String WaitCount;

	public String toString(){
		return "ID:"+ID+" IP:"+IP+" Name:"+Name+" Online:"+Online+" ThreadCount:"+ThreadCount+" ExecCount:"+ExecCount+" WaitCount:"+WaitCount;
	}

	public String toJsonString(){
		return "{\"ID\":\""+ID+"\",\"IP\":\""+IP+"\",\"Name\":\""+Name+"\",\"Online\":\""+Online+"\",\"ThreadCount\":\""+ThreadCount+"\",\"ExecCount\":\""+ExecCount+"\",\"WaitCount\":\""+WaitCount+"\"}";
	}

}
