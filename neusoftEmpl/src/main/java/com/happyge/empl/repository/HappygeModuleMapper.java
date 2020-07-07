package com.happyge.empl.repository;

import com.happyge.empl.model.HappygeModule;
import com.happyge.empl.model.HappygeModuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HappygeModuleMapper {
    long countByExample(HappygeModuleExample example);

    int deleteByExample(HappygeModuleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HappygeModule record);

    int insertSelective(HappygeModule record);

    List<HappygeModule> selectByExample(HappygeModuleExample example);

    HappygeModule selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HappygeModule record, @Param("example") HappygeModuleExample example);

    int updateByExample(@Param("record") HappygeModule record, @Param("example") HappygeModuleExample example);

    int updateByPrimaryKeySelective(HappygeModule record);

    int updateByPrimaryKey(HappygeModule record);
}