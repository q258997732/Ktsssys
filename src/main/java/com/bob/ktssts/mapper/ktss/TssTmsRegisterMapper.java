package com.bob.ktssts.mapper.ktss;

import com.bob.ktssts.entity.ktss.TssTmsRegister;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Huang
* @description 针对表【tss_tms_register(tms注册账号)】的数据库操作Mapper
* @createDate 2024-08-26 15:30:26
* @Entity com.bob.ktssts.entity.ktss.TssTmsRegister
*/
public interface TssTmsRegisterMapper {

    int deleteByPrimaryKey(String id);

    int insert(TssTmsRegister record);

    int insertSelective(TssTmsRegister record);

    TssTmsRegister selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TssTmsRegister record);

    int updateByPrimaryKey(TssTmsRegister record);

}
