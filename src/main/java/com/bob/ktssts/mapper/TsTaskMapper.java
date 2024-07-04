package com.bob.ktssts.mapper;

import com.bob.ktssts.entity.TmsTaskBean;
import com.bob.ktssts.entity.TsTask;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
* @author Huang
* @description 针对表【ts_task(ts_task)】的数据库操作Mapper
* @createDate 2024-07-03 10:20:31
* @Entity com.bob.ktssts.entity.TsTask
*/

@Mapper
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

    int clearExecTaskByTaskId(String id);

}
