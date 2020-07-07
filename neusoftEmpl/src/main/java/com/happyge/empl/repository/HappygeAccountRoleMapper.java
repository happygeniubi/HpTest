package com.happyge.empl.repository;

import com.happyge.empl.model.HappygeAccountRole;
import com.happyge.empl.model.HappygeAccountRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HappygeAccountRoleMapper {
    long countByExample(HappygeAccountRoleExample example);

    int deleteByExample(HappygeAccountRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HappygeAccountRole record);

    int insertSelective(HappygeAccountRole record);

    List<HappygeAccountRole> selectByExample(HappygeAccountRoleExample example);

    HappygeAccountRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HappygeAccountRole record, @Param("example") HappygeAccountRoleExample example);

    int updateByExample(@Param("record") HappygeAccountRole record, @Param("example") HappygeAccountRoleExample example);

    int updateByPrimaryKeySelective(HappygeAccountRole record);

    int updateByPrimaryKey(HappygeAccountRole record);
}