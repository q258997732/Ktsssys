package com.bob.ktssts.mapper;

import com.bob.ktssts.entity.TsExecuter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author Huang
* @description 针对表【ts_executer(ts_executer)】的数据库操作Mapper
* @createDate 2024-06-27 16:17:09
* @Entity com.bob.ktssts.entity.TsExecuter
*/

@Mapper
public interface TsExecuterMapper {

    /**
     * 根据主键删除执行器记录。
     *
     * @param id 执行器记录的唯一标识符。
     * @return 受影响的行数。
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入一个新地执行器记录。
     *
     * @param record 要插入的执行器记录。
     * @return 受影响的行数。
     */
    int insert(TsExecuter record);

    /**
     * 选择性插入一个新地执行器记录。
     *
     * @param record 要插入的执行器记录。
     * @return 受影响的行数。
     */
    int insertSelective(TsExecuter record);

    /**
     * 根据主键查询执行器记录。
     *
     * @param id 执行器记录的唯一标识符。
     * @return 执行器记录实例。
     */
    TsExecuter selectByPrimaryKey(String id);

    /**
     * 根据主键选择性更新执行器记录。
     *
     * @param record 包含要更新信息的执行器记录。
     * @return 受影响的行数。
     */
    int updateByPrimaryKeySelective(TsExecuter record);

    /**
     * 根据主键更新执行器记录。
     *
     * @param record 包含要更新信息的执行器记录。
     * @return 受影响的行数。
     */
    int updateByPrimaryKey(TsExecuter record);

    // 返回所有记录
    List<TsExecuter> selectAll();

    List<TsExecuter> selectFreeExecuter(String execType,int num);

    String getExecuterId(String execType,String execAddr);



}
