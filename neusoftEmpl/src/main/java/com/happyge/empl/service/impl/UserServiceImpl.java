package com.happyge.empl.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.happyge.empl.model.HllcDepartment;
import com.happyge.empl.model.HllcUser;
import com.happyge.empl.model.HllcUser_roles;
import com.happyge.empl.repository.UserMapper;
import com.happyge.empl.service.UserService;
import com.happyge.empl.utils.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public HllcUser checklogin(String username, String password) {
        HllcUser user = userMapper.findByUsername(username);
        if(user!=null && user.getUpassword().equals(password)){
            return user;
        }
        return null;
    }

    @Override
    public HllcUser findUserbyUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public void register(HllcUser hllcUser) {
        userMapper.register(hllcUser);
    }

    @Override
    public int findAllCount() {
        return userMapper.findAllCount();
    }

    @Override
    public List<HllcUser> list(Map<String, Object> parm) {
        return userMapper.list(parm);
    }


    @Override
    public List<HllcUser> list() {
        List<HllcUser> user = userMapper.listUser();
        return user;
    }

    @Override
    public void addUser(HllcUser user) {

        this.userMapper.addUser(new PasswordHelper().encryptPassword(user));
    }

    @Override
    public List<HllcUser> findStatusName() {
        return userMapper.findStatusName();
    }


    @Override
    public int findUserCount(String content) {
        return userMapper.findUserCount(content);
    }

    @Override
    public List<HllcUser> findUserName(Map<String, Object> map) {
        return userMapper.findUserName(map);
    }

    @Override
    public Set<String> listRoles(String username) {
        return userMapper.listRoles(username);
    }

    @Override
    public Set<String> listPermissions(String username) {
        return userMapper.listPermissions(username);
    }

    @Override
    public void addUser_Roles(HllcUser_roles hllcUser_roles) {
         this.userMapper.addUser_Roles(hllcUser_roles);
    }


    @Override
    public PageInfo<HllcUser> list(int curPage, int pageNum) {
        PageHelper.startPage(curPage, pageNum);
        List<HllcUser> blogs = userMapper.listUser();
        PageInfo<HllcUser> pageInfo = new PageInfo<HllcUser>(blogs);
        return pageInfo;
    }

    @Override
    public PageInfo<HllcUser> findUser(String content, int curPage, int pageNum) {
        PageHelper.startPage(curPage, pageNum);
        List<HllcUser> blogs = userMapper.findUser(content);
        PageInfo<HllcUser> pageInfo = new PageInfo<HllcUser>(blogs);
        return pageInfo;
    }

    @Override
    public boolean deleteUser(String uid) {
        this.userMapper.deleteUser(uid);
        return true;
    }


    @Override
    public void updateUser(HllcUser hllcUser) {
        this.userMapper.updateUr(hllcUser);
    }

    @Override
    public List<HllcUser> updateUr(String uid) {
        return  userMapper.updateUser(uid);
    }
}
