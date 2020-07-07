package com.happyge.empl.repository;

import com.happyge.empl.model.HappygeRoleModule;
import com.happyge.empl.model.HappygeRoleModuleExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface HappygeRoleModuleMapper {
    long countByExample(HappygeRoleModuleExample example);

    int deleteByExample(HappygeRoleModuleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HappygeRoleModule record);

    int insertSelective(HappygeRoleModule record);

    List<HappygeRoleModule> selectByExample(HappygeRoleModuleExample example);

    HappygeRoleModule selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HappygeRoleModule record, @Param("example") HappygeRoleModuleExample example);

    int updateByExample(@Param("record") HappygeRoleModule record, @Param("example") HappygeRoleModuleExample example);

    int updateByPrimaryKeySelective(HappygeRoleModule record);

    int updateByPrimaryKey(HappygeRoleModule record);

    List<HappygeRoleModule> secureValid(Map<String,Object> map);
}