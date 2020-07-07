package com.happyge.empl.service;


import com.github.pagehelper.PageInfo;
import com.happyge.empl.model.HllcUser;
import com.happyge.empl.model.HllcUser_roles;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserService {

    HllcUser checklogin(String username, String password);

    HllcUser findUserbyUsername(String username);

    void register(HllcUser hllcUser);

    int findAllCount();

    List<HllcUser> list(Map<String, Object> parm);

    List<HllcUser> list();

    void addUser(HllcUser hllcuser);

    List<HllcUser> findStatusName();

    int findUserCount(String content);

    List<HllcUser> findUserName(Map<String, Object> map);

    Set<String> listRoles(String username);

    Set<String> listPermissions(String username);

    void addUser_Roles(HllcUser_roles hllcUser_roles);

    //所有部门
    PageInfo<HllcUser> list(int curPage, int pageNum);

    //通过名字查找部门
    PageInfo<HllcUser> findUser(String content,int curPage,int pageNum);

    //删除部门
    boolean deleteUser(String uid);

    //修改
    void updateUser(HllcUser hllcUser);

    //获得一个部门全部信息
    List<HllcUser> updateUr(String uid);
}
