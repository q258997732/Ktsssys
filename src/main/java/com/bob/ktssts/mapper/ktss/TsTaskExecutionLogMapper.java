package com.bob.ktssts.mapper.ktss;

import com.bob.ktssts.entity.ktss.TsTaskExecutionLog;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Huang
* @description 针对表【ts_task_execution_log】的数据库操作Mapper
* @createDate 2024-07-16 16:58:17
* @Entity generator.domain.TsTaskExecutionLog
*/
public interface TsTaskExecutionLogMapper {

    int deleteByPrimaryKey(String id);

    int insert(TsTaskExecutionLog record);

    int insertSelective(TsTaskExecutionLog record);

    TsTaskExecutionLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TsTaskExecutionLog record);

    int updateByPrimaryKey(TsTaskExecutionLog record);

	int replaceInto(TsTaskExecutionLog tsTaskExecutionLog);

    int updateTaskExecLog(String id,String endTime, String status, String data, String errorMessage);
}
