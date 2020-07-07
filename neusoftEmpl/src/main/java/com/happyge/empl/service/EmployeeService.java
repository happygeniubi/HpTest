package com.happyge.empl.service;


import com.github.pagehelper.PageInfo;
import com.happyge.empl.model.HllcEmployee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    List<String> CountryList();

    //增加员工
    void addEmployee(HllcEmployee hllcEmployee);

    //实习员工回显
    PageInfo<HllcEmployee> internList(int curPage, int pageNum);



    //离职员工回显
    PageInfo<HllcEmployee> quitList(int curPage,int pageNum);

    //实习员工淘汰成离职员工
    void eliminateEmployee(String enId);

    //删除离职员工
    void deleteEmployee(String enId);

    //把离职员工重新录取为实习员工
    void ReAdmissionEmployee(String enId);

    //正式员工回显
    PageInfo<HllcEmployee> formalList(int curPage,int pageNum);



    //把实习员工录取为在职
    void admissionEmployee(String enId);



    //通过搜索条件查找正式员工
    PageInfo<HllcEmployee> findFomalEmployee(int curPage,int pageNum,HllcEmployee hllcEmployee);

    //通过id获得员工信息
    List<HllcEmployee> selectEmployeeMessage(String enId);

    //修改员工信息
    void updateEmployee(HllcEmployee hllcEmployee);

    //批量删除员工
    void deleteAllEmployee(List<String> idslist);
}
