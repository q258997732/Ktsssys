package com.bob.ktssts.util;

import com.bob.ktssts.entity.ktss.TmsTaskBean;
import com.bob.ktssts.entity.ktss.TsTask;

public class TaskUtil {

	public static boolean compareTsTaskTmsTask(TsTask tsTask, TmsTaskBean tmsTaskBean) {
		if (tsTask == null || tmsTaskBean == null) {
			return false;
		}
		return tsTask.getExecParam().contains(tmsTaskBean.getUsername()) && tsTask.getExecParam().contains(tmsTaskBean.getPassword()) && tsTask.getExecParam().contains(tmsTaskBean.getIp()) && tsTask.getExecParam().contains(tmsTaskBean.getPort()) && tsTask.getExecParam().contains(tmsTaskBean.getSiteId()) && tsTask.getExecParam().contains(tmsTaskBean.getTmsId());
	}

	public static boolean isDistributeRpaTask(TsTask tsTask) {
		if (tsTask == null) {
			return false;
		}
		return tsTask.getExecType().equals("K-RPA") && tsTask.getClone().equals("1");
	}

	public static String xciParam2json(String param){
		String[] paramList = param.split("\\|");
		// 113.140.71.252|6006|光大外事|84500080@XCI96716|45b730fe344940e68997559d6881f6dc|d3f36acc71154ad186ff05de809dfbe8
		TmsTaskBean tmsTaskBean = new TmsTaskBean(paramList[0], paramList[1], paramList[2], paramList[3], paramList[4], paramList[5]);
		return tmsTaskBean.toJsonString();
	}

}

