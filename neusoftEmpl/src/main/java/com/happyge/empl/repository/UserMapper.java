package com.happyge.empl.repository;


import com.happyge.empl.model.HllcUser;
import com.happyge.empl.model.HllcUser_roles;

import java.util.List;
import java.util.Map;
import java.util.Set;


public interface UserMapper {

    HllcUser findByUsername(String username);

    void register(HllcUser hllcUser);

    int findAllCount();

    List<HllcUser> list(Map<String, Object> parm);

    List<HllcUser> listUser();

    void addUser(HllcUser hllcUser);

    void addUser_Roles(HllcUser_roles hllcUser_roles);

    List<HllcUser> findStatusName();

    void deleteUser(String uid);

    int findUserCount(String content);

    List<HllcUser> findUserName(Map<String, Object> map);

    Set<String> listRoles(String username);

    Set<String> listPermissions(String username);

    List<HllcUser> findUser(String content);

    //获得一个部门的全部信息
    List<HllcUser> updateUser(String deId);

    //修改部门
    void updateUr(HllcUser hllcUser);

}
