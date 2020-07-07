package com.happyge.empl.repository;

import com.happyge.empl.model.HllcPost;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HllcPostMapper {
    //所有职位
    List<HllcPost> list();

    //删除职位
    void deleteClass(String poId);

    //增加职位
    void savaClass(HllcPost hllcPost);


    //通过名字查找职位
    List<HllcPost> findClassByName(String content);

    //通过部门id查找这个部门下是否含有职位
    int findPositionDeId(String deid);

    //通过id找到一个职位的全部信息
    List<HllcPost> findPositionById(String poId);

    //修改职位信息
    void updatePosition(HllcPost hllcPost);

    //找到部门下在职位
    int findPositionByIDs(List<String> list);

    //批量删除职位
    void deleteAllPosition(List<String> idslist);

    //所有職位
    List<HllcPost> positionList();
}
