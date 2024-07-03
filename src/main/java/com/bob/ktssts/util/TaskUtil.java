package com.bob.ktssts.util;

import com.bob.ktssts.entity.TmsTaskBean;
import com.bob.ktssts.entity.TsTask;
import org.springframework.stereotype.Component;

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

}

