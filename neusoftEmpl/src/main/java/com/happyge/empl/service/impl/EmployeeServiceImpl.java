package com.happyge.empl.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.happyge.empl.repository.HllcEmployeeMapper;
import com.happyge.empl.model.HllcEmployee;
import com.happyge.empl.repository.HllcEmployeeMapper;
import com.happyge.empl.service.EmployeeService;
import com.happyge.empl.utils.LocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private HllcEmployeeMapper hllcEmployeeMapper;


    //获得城市列表
    @Override
    public List<String> CountryList() {
        LocalUtil lu = LocalUtil.getInstance();
        List<String> countryList = lu.getCountry();

        return countryList;
    }

    //增加员工
    @Override
    public void addEmployee(HllcEmployee hllcEmployee) {
        hllcEmployeeMapper.saveEmployee(hllcEmployee);
    }

    //实习员工回显
    @Override
    public PageInfo<HllcEmployee> internList(int curPage,int pageNum) {

        PageHelper.startPage(curPage, pageNum);
        List<HllcEmployee> blogs = hllcEmployeeMapper.internList();
        PageInfo<HllcEmployee> pageInfo = new PageInfo<HllcEmployee>(blogs);
        return pageInfo;
    }



    //离职员工回显
    @Override
    public PageInfo<HllcEmployee> quitList(int curPage,int pageNum) {

        PageHelper.startPage(curPage, pageNum);
        List<HllcEmployee> blogs = hllcEmployeeMapper.quitList();
        PageInfo<HllcEmployee> pageInfo = new PageInfo<HllcEmployee>(blogs);
        return pageInfo;
    }

    //实习员工淘汰为离职员工
    @Override
    public void eliminateEmployee(String enId) {
        hllcEmployeeMapper.eliminateEmployee(enId);
    }

    //删除离职员工
    @Override
    public void deleteEmployee(String enId) {
        hllcEmployeeMapper.deleteEmployee(enId);
    }

    //把离职员工重新录取为实习员工
    @Override
    public void ReAdmissionEmployee(String enId) {
        hllcEmployeeMapper.ReAdmissionEmployee(enId);
    }

    //正式员工的回显
    @Override
    public PageInfo<HllcEmployee> formalList(int curPage,int pageNum) {
        PageHelper.startPage(curPage, pageNum);
        List<HllcEmployee> blogs = hllcEmployeeMapper.formalList();
        PageInfo<HllcEmployee> pageInfo = new PageInfo<HllcEmployee>(blogs);
        return pageInfo;
    }


    //把实习员工录取为在职
    @Override
    public void admissionEmployee(String enId) {
        hllcEmployeeMapper.admissionEmployee(enId);
    }



    //通过搜索条件搜索员工
    @Override
    public PageInfo<HllcEmployee> findFomalEmployee(int curPage,int pageNum,HllcEmployee hllcEmployee) {
        PageHelper.startPage(curPage, pageNum);
        List<HllcEmployee> blogs = hllcEmployeeMapper.findFomalCondition(hllcEmployee);
        PageInfo<HllcEmployee> pageInfo = new PageInfo<HllcEmployee>(blogs);
        return pageInfo;
    }

    //通过id获得员工信息
    @Override
    public List<HllcEmployee> selectEmployeeMessage(String enId) {
        return hllcEmployeeMapper.findEmployeeMessage(enId);
    }


    //修改员工信息
    @Override
    public void updateEmployee(HllcEmployee hllcEmployee) {
        hllcEmployeeMapper.updateEmployee(hllcEmployee);
    }

    //批量删除员工
    @Override
    public void deleteAllEmployee(List<String> idslist) {
        hllcEmployeeMapper.deleteAllEmployee(idslist);
    }
}
