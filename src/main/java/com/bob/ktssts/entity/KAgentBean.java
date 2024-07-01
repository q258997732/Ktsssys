package com.bob.ktssts.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.UUID;

@Setter
@Getter
public class KAgentBean {

	public KAgentBean() {
	}

	public KAgentBean(int online, String id, String pid, String ip, String name, String version, String system, int offNotice, String noticeUser, int emptyNotify, int synTime, int music, String remark, String sysUser, String muTagId, String biosSN, String serverInstance, int iPause, int iUpdate) {
		this.online = online;
		this.id = id;
		this.pid = pid;
		this.ip = ip;
		this.name = name;
		this.version = version;
		this.system = system;
		this.offNotice = offNotice;
		this.noticeUser = noticeUser;
		this.emptyNotify = emptyNotify;
		this.synTime = synTime;
		this.music = music;
		this.remark = remark;
		this.sysUser = sysUser;
		this.muTagId = muTagId;
		this.biosSN = biosSN;
		this.serverInstance = serverInstance;
		this.iPause = iPause;
		this.iUpdate = iUpdate;
	}

	public KAgentBean(Map<String,Object> beanParamsMap) {
		this.id = beanParamsMap.get("ID").toString();
		this.pid = beanParamsMap.get("PID").toString();
		this.ip = beanParamsMap.get("IP").toString();
		this.name = beanParamsMap.get("Name").toString();
		this.online = Integer.parseInt(beanParamsMap.get("Online").toString());
		this.version = beanParamsMap.get("Version").toString();
		this.system = beanParamsMap.get("System").toString();
		this.offNotice = Integer.parseInt(beanParamsMap.get("OffNotice").toString());
		this.noticeUser = beanParamsMap.get("NoticeUser").toString();
		this.emptyNotify = Integer.parseInt(beanParamsMap.get("EmptyNotify").toString());
		this.synTime = Integer.parseInt(beanParamsMap.get("SynTime").toString());
		this.music = Integer.parseInt(beanParamsMap.get("Music").toString());
		this.remark = beanParamsMap.get("Remark").toString();
		this.sysUser = beanParamsMap.get("sysUser").toString();
		this.muTagId = beanParamsMap.get("MUTagID").toString();
		this.biosSN = beanParamsMap.get("BiosSN").toString();
		this.serverInstance = beanParamsMap.get("ServerInstance").toString();
		this.iPause = Integer.parseInt(beanParamsMap.get("iPause").toString());
		this.iUpdate = Integer.parseInt(beanParamsMap.get("iUpdate").toString());
}

	private String id; // 代理端标识

	private String pid; // 父级 ID

	private String ip; // 代理端 IP

	private String name; // 机器名称

	private int online; // 是否在线

	private String version; // 版本号

	private String system; // 操作系统

	private int offNotice; // 离线通知

	private String noticeUser; // 通知人员(LoginID)

	private int emptyNotify; // 不通知人员

	private int synTime; // 同步时间

	private int music; // 声音禁用

	private String remark; // 备注

	private String sysUser; // 系统用户

	private String muTagId; // 机器补丁标签 ID

	private String biosSN; // Bios 序列号

	private String serverInstance; // 服务器实例

	private int iPause; // 是否停用

	private int iUpdate; // 需要更新

	public String toString() {
		return "KAgentBean [id=" + id + ", pid=" + pid + ", ip=" + ip + ", name=" + name + ", online=" + online
				+ ", version=" + version + ", system=" + system + ", offNotice=" + offNotice + ", noticeUser="
				+ noticeUser + ", emptyNotify=" + emptyNotify + ", synTime=" + synTime + ", music=" + music + ", remark="
				+ remark + ", sysUser=" + sysUser + ", muTagId=" + muTagId + ", biosSN=" + biosSN + ", serverInstance="
				+ serverInstance + ", iPause=" + iPause + ", iUpdate=" + iUpdate + "]";
	}

}
