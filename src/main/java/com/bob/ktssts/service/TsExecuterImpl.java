package com.bob.ktssts.service;

import com.bob.ktssts.entity.ktss.KAgentBean;
import com.bob.ktssts.entity.ktss.TsExecuter;
import com.bob.ktssts.mapper.ktss.TsExecuterMapper;
import com.bob.ktssts.util.RpaExecuter;
import jakarta.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.ListIterator;

@Component
public class TsExecuterImpl implements TsExecuterService {

	private static final Logger LOGGER = LogManager.getLogger(RpaExecuter.class);

	@Resource
	private RpaExecuter rpaExecuter;

	@Resource
	private TsExecuterMapper tsExecuterMapper;

	@Override
	public int cleanKRpaAgent() {
		int effectNum = 0;
		List<TsExecuter> tsExecuterList = tsExecuterMapper.selectAll();
		if (tsExecuterList.isEmpty()) {
			LOGGER.info("执行器列表为空");
			return 0;
		}
		for (TsExecuter tsExecuter : tsExecuterList) {
			if ("K-RPA".equals(tsExecuter.getExec_type()) && "ktssts".equals(tsExecuter.getUpdate_by())) {
				tsExecuterMapper.deleteTaskByExecuterId(tsExecuter.getId());
				effectNum += tsExecuterMapper.deleteByPrimaryKey(tsExecuter.getId());
			}
		}
		return effectNum;
	}

	/* 同步K-RPA执行器（机器人）信息 */
	@Override
	public boolean syncKRpaAgent() {
		List<KAgentBean> kAgentBeanList = rpaExecuter.getKRpaAgentList();
		List<TsExecuter> tsExecuterList = tsExecuterMapper.selectAll();

		/* 将所有状态设置为不可用 */
		tsExecuterList.forEach(tsExecuter -> tsExecuter.setExec_available("0"));
		if (!kAgentBeanList.isEmpty()) {
			boolean targetIsFind;
			for (KAgentBean kAgentBean : kAgentBeanList) {
				ListIterator<TsExecuter> tsExecuterListIterator = tsExecuterList.listIterator();
				targetIsFind = false;
				/* 遍历目标列表 */
				while (tsExecuterListIterator.hasNext()) {
					TsExecuter tsExecuter = tsExecuterListIterator.next();
					// 比较
					if (kAgentBean.getId().equals(tsExecuter.getId())) {
						tsExecuter.setExec_online(kAgentBean.getOnline() == 1 ? "1" : "0");
						tsExecuter.setExec_available(kAgentBean.getIPause() == 1 ? "0" : "1");
						tsExecuter.setUpdate_by("ktssts");
						tsExecuter.setUpdate_time(new Date());
						tsExecuter.setExec_type("K-RPA");
						tsExecuter.setExec_version(kAgentBean.getVersion());
						tsExecuter.setId(kAgentBean.getId());
						tsExecuter.setExec_name(kAgentBean.getName());
						tsExecuter.setExec_addr(kAgentBean.getIp());
						tsExecuterListIterator.set(tsExecuter);
						targetIsFind = true;
						break;
					}
				}
				// 目标未找到时插入
				if (!targetIsFind) {
					LOGGER.debug("K-RPA执行器未找到，插入");
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

//		LOGGER.info("K-RPA执行器总数: {}", kAgentBeanList.size());
//		LOGGER.info(tsExecuterList.toString());

		// 遍历tsExecuterList，若数据库中存在记录则更新，不存在则插入
		try {
//			LOGGER.info("update tsExecuterList start");
			for (TsExecuter tsExecuter : tsExecuterList) {
				if (tsExecuter.getExec_type().equals("K-RPA")) {
					if (tsExecuter.getId() != null) {
						if (tsExecuterMapper.selectByPrimaryKey(tsExecuter.getId()) != null) {
							LOGGER.debug("更新K-Agent:{}",tsExecuter.getExec_name());
							tsExecuterMapper.updateByPrimaryKeySelective(tsExecuter);
						} else {
							LOGGER.debug("插入K-Agent:{}",tsExecuter.getExec_name());
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

	@Override
	public List<TsExecuter> getFreeExecuter(String execType, int num) {
		return tsExecuterMapper.selectFreeExecuter(execType, num);
	}


}
