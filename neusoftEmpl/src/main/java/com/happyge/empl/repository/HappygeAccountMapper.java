package com.happyge.empl.repository;

import com.happyge.empl.model.HappygeAccount;
import com.happyge.empl.model.HappygeAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HappygeAccountMapper {
    long countByExample(HappygeAccountExample example);

    int deleteByExample(HappygeAccountExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HappygeAccount record);

    int insertSelective(HappygeAccount record);

    List<HappygeAccount> selectByExample(HappygeAccountExample example);

    HappygeAccount selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HappygeAccount record, @Param("example") HappygeAccountExample example);

    int updateByExample(@Param("record") HappygeAccount record, @Param("example") HappygeAccountExample example);

    int updateByPrimaryKeySelective(HappygeAccount record);

    int updateByPrimaryKey(HappygeAccount record);

    int updataFaceUrlByName(@Param("username") String username, @Param("faceUrl") String faceUrl, @Param("facepath") String facepath);

    List<HappygeAccount> findAll();

}