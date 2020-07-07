package com.happyge.empl.repository;

import com.happyge.empl.model.HllcEmployee;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface HllcEmployeeMapper {
    //增加员工
    void saveEmployee(HllcEmployee hllcEmployee);

    //实习员工回显
    List<HllcEmployee> internList();


    //离职员工的总数
    int quitCount();

    //离职员工的回显
    List<HllcEmployee> quitList();

    //把实习员工淘汰成离职员工
    void eliminateEmployee(String enId);

    //删除离职员工
    void deleteEmployee(String enId);

    //把离职员工重新录取为实习员工
    void ReAdmissionEmployee(String enId);

    //正式员工列表
    List<HllcEmployee> formalList();


    //把实习员工正式录取
    void admissionEmployee(String enId);

    //查看部门下有无员工
    int selectPositionEmployee(String poid);


    //通过搜索条件搜索员工
    List<HllcEmployee> findFomalCondition(HllcEmployee hllcEmployee);

    //通过id获得员工信息
    List<HllcEmployee> findEmployeeMessage(String enId);


    //修改员工信息
    void updateEmployee(HllcEmployee hllcEmployee);

    //看职位下是否亦员工
    int findEmployeeBypoId(List<String> idslist);

    //批量删除员工
    void deleteAllEmployee(List<String> list);
}
