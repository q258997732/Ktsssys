package com.bob.ktssts.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TmsTaskBean {

	public TmsTaskBean() {
	}

	// 113.140.71.252|6006|光大外事|84500080@XCI96716|45b730fe344940e68997559d6881f6dc|d3f36acc71154ad186ff05de809dfbe8
	public TmsTaskBean(String ip,String port,String username,String password,String tmsId,String siteId){
		this.ip = ip;
		this.port = port;
		this.username = username;
		this.password = password;
		this.tmsId = tmsId;
		this.siteId = siteId;
	}

	public TmsTaskBean(String tmsName, String ip, String port, String siteId, String tmsId, String username, String password) {
		this.tmsName = tmsName;
		this.ip = ip;
		this.port = port;
		this.siteId = siteId;
		this.tmsId = tmsId;
		this.username = username;
		this.password = password;
	}

	private String tmsName;    // tms_name

	private String ip;         // ip

	private String port;       // port

	private String siteId;     // site_id

	private String tmsId;      // tms_id

	private String username;   // username

	private String password;  // password

	public String toKRpaString(){
		// 根据格式 113.140.71.252|6006|翔瑞运输1|XRYS1XCI96716|45b730fe344940e68997559d6881f6dc|f56a30d9878a42caa4f03edda7bac77a
		return String.format("%s|%s|%s|%s|%s|%s",ip,port,username,password,tmsId,siteId);
	}

	public String toString(){
		return "TmsTaskBean{tmsName="+tmsName+",ip="+ip+",port="+port+",siteId="+siteId+",tmsId="+tmsId+",username="+username+",password="+password+"}";
	}

	// 转为Json字符串
	public String toJsonString(){
		return "{\"rpa_task_ID\":\"" + UUID.randomUUID().toString().replace("-", "") + "\",\"系统名称\":\""+tmsName+"\",\"服务器ip\":\""+ip+"\",\"端口\":\""+port+"\",\"siteId\":\""+siteId+"\",\"tmsId\":\""+tmsId+"\",\"用户名\":\""+username+"\",\"密码\":\""+password+"\"}";
	}

}
