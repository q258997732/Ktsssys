package com.bob.ktssts.mapper.ktss;

import com.bob.ktssts.entity.ktss.TmsTaskBean;
import com.bob.ktssts.entity.ktss.TsTask;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
* @author Huang
* @description 针对表【ts_task(ts_task)】的数据库操作Mapper
* @createDate 2024-07-03 10:20:31
* @Entity com.bob.ktssts.entity.ktss.TsTask
*/

public interface TsTaskMapper {

    int deleteByPrimaryKey(String id);

    int insert(TsTask record);

    int insertSelective(TsTask record);

    TsTask selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TsTask record);

    int updateByPrimaryKey(TsTask record);

    List<TmsTaskBean> selectAllKtssTmsTask();

    List<TsTask> selectAllTsTask();

    List<TsTask> selectTsTaskByExecType(String execType);

    int deleteTaskByAddBy(String add_by);

    int countExecTask(String id);

    int insertExecuterTask(Map<String,Object> map);

    int deleteExecTaskByTaskId(String id);

    int deleteAutoRpaTask();

    // 获取所有K-RPA待执行的任务
    @MapKey("id")
    List<Map<String,Object>> selectKRpaExecutedTsTask();

    @MapKey("id")
    List<Map<String,Object>> selectKRpaExecutedTsTaskByFlowName(String flowName);

    // 根据taskid删除ts_executer_task内容
    int deleteExecuterTaskByTaskId(String taskId);

    int insertTaskHistory(String taskId);
}
