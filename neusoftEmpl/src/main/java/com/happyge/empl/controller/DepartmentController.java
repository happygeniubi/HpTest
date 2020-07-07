package com.happyge.empl.controller;


import com.github.pagehelper.PageInfo;
import com.happyge.empl.model.HllcDepartment;
import com.happyge.empl.service.DepartmentService;
import com.happyge.empl.utils.ResultData;
import com.happyge.empl.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.text.SimpleDateFormat;
import java.util.*;

/*
 * @author lyc
 * @time 2019/7/7
 *
 *
 *
 * */


/**
 * @author spinach
 */
@Controller
@RequestMapping("")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;


    /*
     * 刚进入部门页面的信息
     * param:curPage(curPage)
     *
     *
     *
     *
     * */
    @RequestMapping("listPosition")
    @ResponseBody
    public ResultData<Object> listPosition(String curPage){

        int curPage1 = Integer.parseInt(curPage);
        int pageNum1 = 5;


        PageInfo<HllcDepartment> pageInfo = departmentService.list(curPage1,pageNum1);

        return ResultData.success(pageInfo);

    }



    //部门删除
    @ResponseBody
    @RequestMapping("deletePosition")
    public ResultData<Object> deletePostion(String deId){

        boolean b = departmentService.deletePosition(deId);

        return ResultData.success(b);
    }

    //部门查询
    @RequestMapping("selectPosition")
    @ResponseBody
    public ResultData<Object> selectPosition(String content, String curPage){

        int curPage1 = Integer.parseInt(curPage);
        int pageNum1 = 5;
        String s1 = "%"+content+"%";
        PageInfo<HllcDepartment> pageInfo = departmentService.findPosition(s1,curPage1,pageNum1);

        return ResultData.success1(pageInfo);

    }

    //增加部门
    @RequestMapping("savePosition")
    @ResponseBody
    public ResultData<Object> savePosition(HllcDepartment hllcDepartment){

        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        String s = dateFormat.format(date);
        String deid = UuidUtil.getUUID();

        hllcDepartment.setDeId(deid);
        hllcDepartment.setDeDate(s);
        departmentService.savaPosition(hllcDepartment);


        return ResultData.success();
    }

    /**
     * 通过部门Id找到部门全部信息
     * parm：deId
     *
     **/
    @RequestMapping("updateDeapartment")
    @ResponseBody
    public List<HllcDepartment> updateDeapartment(String deId){

        List<HllcDepartment> deList = departmentService.updateDeapartment(deId);
        return deList;

    }

    /**
     * 修改部门信息
     * parm:deId
     *
     * */
    @RequestMapping("/updateDe")
    @ResponseBody
    public ResultData<Object> updateDe(HllcDepartment hllcDepartment){
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        String s = dateFormat.format(date);
        hllcDepartment.setDeDate(s);
        departmentService.updateDe(hllcDepartment);
        return ResultData.success();
    }

    /**
     * 批量删除
     * @param :ids
     *
     * */
    @RequestMapping("deleteAllDeapartment")
    @ResponseBody
    public boolean  deleteAllDeapartment(String ids){


        String[] array = ids.split(",");
        List<String> idslist = new ArrayList();
        for (String s: array) {
            idslist.add(s);
        }
        boolean b = departmentService.deleteManyDepartment(idslist);
        return b;
    }

}