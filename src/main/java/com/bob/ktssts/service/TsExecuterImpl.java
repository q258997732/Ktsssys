package com.bob.ktssts.service;

import com.bob.ktssts.entity.KAgentBean;
import com.bob.ktssts.entity.TsExecuter;
import com.bob.ktssts.mapper.TsExecuterMapper;
import com.bob.ktssts.util.RpaExecuter;
import com.bob.ktssts.util.RpaUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.ListIterator;

@Component
public class TsExecuterImpl implements TsExecuterService {

	private static final Logger LOGGER = LogManager.getLogger(RpaExecuter.class);

	@Autowired
	private RpaExecuter rpaExecuter;

	@Autowired
	private TsExecuterMapper tsExecuterMapper;

	@Override
	public int cleanKRpaAgent() {
		return 0;
	}

	/* 同步K-RPA执行器（机器人）信息 */
	@Override
	public boolean syncKRpaAgent() {
		List<KAgentBean> kAgentBeanList = rpaExecuter.getKRpaAgentList();
		LOGGER.info("K-Agent list size : {}", kAgentBeanList.size());
		List<TsExecuter> tsExecuterList = tsExecuterMapper.selectAll();
		ListIterator<TsExecuter> tsExecuterListIterator = tsExecuterList.listIterator();
		if (!kAgentBeanList.isEmpty() && !tsExecuterList.isEmpty()) {
			boolean targetIsFind;
			for (KAgentBean kAgentBean : kAgentBeanList) {
				targetIsFind = false;
				/* 遍历目标列表 */
				while (tsExecuterListIterator.hasNext()) {
					TsExecuter tsExecuter = tsExecuterListIterator.next();
					// 比较
					if (kAgentBean.getId().equals(tsExecuter.getId())) {
						LOGGER.info("k-agent 存在于 ts-executer 中");
						tsExecuter.setExec_online(kAgentBean.getOnline() == 1 ? "1" : "0");
						tsExecuter.setExec_available(kAgentBean.getIPause() == 1 ? "0" : "1");
						tsExecuter.setUpdate_by("ktssts");
						tsExecuter.setUpdate_time(new Date());
						tsExecuter.setExec_type("K-RPA");
						tsExecuter.setExec_version(kAgentBean.getVersion());
						tsExecuterListIterator.set(tsExecuter);
						targetIsFind = true;
						break;
					}
				}
				// 目标未找到时插入
				if (!targetIsFind) {
					TsExecuter toInsert = new TsExecuter();
					toInsert.setId(kAgentBean.getId());
					toInsert.setExec_addr(kAgentBean.getIp());
					toInsert.setExec_name(kAgentBean.getName());
					toInsert.setExec_type("K-RPA");
					toInsert.setExec_available(kAgentBean.getIPause() == 1 ? "0" : "1");
					toInsert.setExec_online(kAgentBean.getOnline() == 1 ? "1" : "0");
					toInsert.setExec_version(kAgentBean.getVersion());
					toInsert.setExec_register(new Date());
					toInsert.setUpdate_time(new Date());
					toInsert.setUpdate_by("ktssts");
					toInsert.setExec_monopoly("0");
					tsExecuterListIterator.add(toInsert);
				}
			}
		}

		LOGGER.info("update tsExecuterList size : {}", tsExecuterList.size());

		// 遍历tsExecuterList，若数据库中存在记录则更新，不存在则插入
		try {
			LOGGER.info("update tsExecuterList start");
			for (TsExecuter tsExecuter : tsExecuterList) {
				if (tsExecuter.getExec_type().equals("K-RPA")) {
					if (tsExecuter.getId() != null) {
						if (tsExecuterMapper.selectByPrimaryKey(tsExecuter.getId()) != null) {
							tsExecuterMapper.updateByPrimaryKeySelective(tsExecuter);
						} else {
							tsExecuterMapper.insert(tsExecuter);
						}
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.toString());
			e.printStackTrace();
			return false;
		}
		return true;
	}

}