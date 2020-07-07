package com.happyge.empl.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.happyge.empl.repository.HllcDepartmentMapper;
import com.happyge.empl.repository.HllcPostMapper;
import com.happyge.empl.model.HllcDepartment;
import com.happyge.empl.model.HllcPost;
import com.happyge.empl.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {


    @Autowired
    private HllcDepartmentMapper hllcDepartmentMapper;

    @Autowired
    private HllcPostMapper hllcPostMapper;


    @Override
    public List<HllcDepartment> list() {
        List<HllcDepartment> po = hllcDepartmentMapper.listPosition();

        return po;
    }

    /**
     * 删除部门
     * child:看部门下是否还有职位，有就不能删除
     * */
    @Override
    public boolean deletePosition(String deid) {
        int child = hllcPostMapper.findPositionDeId(deid);
        boolean b = true;
        if(child<=0)
        {
            this.hllcDepartmentMapper.deletePosition(deid);
            return b;
        }
        else
        {
            b = false;
            return b;
        }
    }

    @Override
    public PageInfo<HllcDepartment> list(int curPage,int pageNum) {
        PageHelper.startPage(curPage, pageNum);
        List<HllcDepartment> blogs = hllcDepartmentMapper.list();
        PageInfo<HllcDepartment> pageInfo = new PageInfo<HllcDepartment>(blogs);
        return pageInfo;
    }

    @Override
    public PageInfo<HllcDepartment> findPosition(String content,int curPage,int pageNum) {
        PageHelper.startPage(curPage, pageNum);
        List<HllcDepartment> blogs = hllcDepartmentMapper.findPosition(content);
        PageInfo<HllcDepartment> pageInfo = new PageInfo<HllcDepartment>(blogs);
        return pageInfo;
    }

    @Override
    public int findPositionCount(String content) {
        return hllcDepartmentMapper.findPositionCount(content);
    }

    @Override
    public void savaPosition(HllcDepartment hllcDepartment) {
        hllcDepartmentMapper.savaPosition(hllcDepartment);
    }

    @Override
    public  List<HllcDepartment> findPositionName() {
        return hllcDepartmentMapper.findPositionName();
    }

    @Override
    public List<HllcPost> findPositionByDepartment(String deId) {
        return hllcDepartmentMapper.findPositionByDepartment(deId);
    }

    //获得一个部门全部的信息
    @Override
    public List<HllcDepartment> updateDeapartment(String deId) {
        return hllcDepartmentMapper.updateDeapartment(deId);
    }

    //修改部门信息
    @Override
    public void updateDe(HllcDepartment hllcDepartment) {
        hllcDepartmentMapper.updateDe(hllcDepartment);
    }


    //批量删除部门
    @Override
    public boolean deleteManyDepartment(List<String> idslist) {
        int posiCount = hllcPostMapper.findPositionByIDs(idslist);
        boolean regord = false;
        if(posiCount==0){
            regord = true;
            hllcDepartmentMapper.deleteAllDepartment(idslist);
            return regord;
        }else {
            return regord;
        }

    }
}
