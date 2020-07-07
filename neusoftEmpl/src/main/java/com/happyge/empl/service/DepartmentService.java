package com.happyge.empl.service;

import com.github.pagehelper.PageInfo;
import com.happyge.empl.model.HllcDepartment;
import com.happyge.empl.model.HllcPost;

import java.util.List;
import java.util.Map;

public interface DepartmentService {


    //所有部门
    List<HllcDepartment> list();

    //删除部门
    boolean deletePosition(String deid);

    //所有部门
    PageInfo<HllcDepartment> list(int curPage, int pageNum);

    //通过名字查找部门
    PageInfo<HllcDepartment> findPosition(String content,int curPage,int pageNum);

    //通过名字查找部门的总数
    int findPositionCount(String content);

    //增加部门信息
    void savaPosition(HllcDepartment hllcDepartment );

    //查找所有部门的名称
    List<HllcDepartment> findPositionName();

    //通过部门查找职位
    List<HllcPost> findPositionByDepartment(String deId);

    //获得一个部门全部信息
    List<HllcDepartment> updateDeapartment(String deId);

    //修改部门信息
    void updateDe(HllcDepartment hllcDepartment);

    //批量删除
    boolean deleteManyDepartment(List<String> idslist);
}
