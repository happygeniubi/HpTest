package com.happyge.empl.repository;

import com.happyge.empl.model.HllcDepartment;
import java.util.List;

import com.happyge.empl.model.HllcPost;
import org.apache.ibatis.annotations.Param;

public interface HllcDepartmentMapper {
    //查询部门信息
    List<HllcDepartment> listPosition();

    //删除部门
    void deletePosition(String deid);


    //所有部门
    List<HllcDepartment> list();

    //通过名字查找部门
    List<HllcDepartment> findPosition(String content);

    //通过名字查找部门的总数
    int findPositionCount(String content);

    //增加部门信息
    void savaPosition(HllcDepartment hllcDepartment);

    //查找部门的名称
    List<HllcDepartment> findPositionName();

    //通过部门查找职位
    List<HllcPost> findPositionByDepartment(String deId);

    //获得一个部门的全部信息
    List<HllcDepartment> updateDeapartment(String deId);

    //修改部门
    void updateDe(HllcDepartment hllcDepartment);

    void deleteAllDepartment(List<String> list);
}
