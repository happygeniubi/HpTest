package com.happyge.empl.controller;


import com.github.pagehelper.PageInfo;
import com.happyge.empl.model.HllcUser;
import com.happyge.empl.model.HllcUser_roles;
import com.happyge.empl.service.UserService;
import com.happyge.empl.utils.PageHolder;
import com.happyge.empl.utils.ResultData;
import com.happyge.empl.utils.UuidUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     *  刚进入用户管理页面
     *  param:curPage(curPage)
     */

    @RequestMapping("listUser")
    @ResponseBody
    public ResultData<Object> listUser(String curPage){
        int curPage1 = Integer.parseInt(curPage);
        int pageNum1 = 5;

            PageInfo<HllcUser> pageInfo = userService.list(curPage1, pageNum1);
        return ResultData.success(pageInfo);

    }

    //部门删除

    @ResponseBody
    @RequestMapping("deleteUser")
    @RequiresPermissions("user:delete")
    public ResultData<Object> deleteUser(String uid){

        boolean b = userService.deleteUser(uid);

        return ResultData.success(b);
    }

    @RequestMapping("updateUser")
    @ResponseBody
    public List<HllcUser> updateDeapartment(String uid){

        List<HllcUser> uList = userService.updateUr(uid);
        return uList;

    }


    @RequestMapping("/updateUr")
    @ResponseBody
    public ResultData<Object> updateDe(HllcUser user){
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        String s = dateFormat.format(date);
        user.setUcreatetime(s);
        userService.updateUser(user);
        return ResultData.success();
    }



    //增加用户
    @RequestMapping("addUser")
    @ResponseBody
    public ResultData<Object> addUser(HllcUser hllcUser,HllcUser_roles hllcUser_roles){
        //获取当前的时间
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        String s = dateFormat.format(date);
        hllcUser.setUcreatetime(s);
        String uuid = UuidUtil.getUUID();
        hllcUser.setUid(uuid);
        hllcUser_roles.setUser_id(uuid);
        if (hllcUser.getUstatus().equals("系统管理员")){
            hllcUser_roles.setRole_id(1);
        }else {
            hllcUser_roles.setRole_id(2);
        }
        userService.addUser(hllcUser);
        userService.addUser_Roles(hllcUser_roles);
        return ResultData.success();
    }



    /**
     * 按职位的名称搜索职位
     * @parm:content(搜索的内容)
     * @parm:curPage(当前页)
     * @parm:pageNum(每页显示多少)
     *
     **/
    @RequestMapping("findUserByName")
    @ResponseBody
    public ResultData<Object> findUserByName(@RequestParam(value = "content",required = false)String content,
                                              @RequestParam(value = "curPage",required = false) Integer curPage){

        System.out.println("curPage=="+curPage);
        int pageNum = 5;

        String s1 = "%"+content+"%";
        PageInfo<HllcUser> pageInfo = userService.findUser(content,curPage,pageNum);

        return ResultData.success1(pageInfo);





    }




}
