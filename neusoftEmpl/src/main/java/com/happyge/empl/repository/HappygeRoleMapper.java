package com.happyge.empl.repository;

import com.happyge.empl.model.HappygeRole;
import com.happyge.empl.model.HappygeRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HappygeRoleMapper {
    long countByExample(HappygeRoleExample example);

    int deleteByExample(HappygeRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HappygeRole record);

    int insertSelective(HappygeRole record);

    List<HappygeRole> selectByExample(HappygeRoleExample example);

    HappygeRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HappygeRole record, @Param("example") HappygeRoleExample example);

    int updateByExample(@Param("record") HappygeRole record, @Param("example") HappygeRoleExample example);

    int updateByPrimaryKeySelective(HappygeRole record);

    int updateByPrimaryKey(HappygeRole record);
}