package com.happyge.empl.service;

import javax.servlet.http.HttpServletRequest;

import com.happyge.empl.constant.MethodType;
import com.happyge.empl.model.HappygeAccount;
import com.happyge.empl.support.JSONReturn;

import java.util.List;

public abstract interface HappygeAccountService {

	public List<HappygeAccount> findAllAccount();

	//登录
	public abstract JSONReturn login(String username, String password, HttpServletRequest request);

	//新增或者保存帐号
	public JSONReturn addAccount(String username, String nickname, String password, String acctName);

	//保存帐号与角色之间的关联
	public JSONReturn saveRole(long accountId, long roleId, String acctName);

	//删除帐号
	public JSONReturn deleteAccount(long id, String acctName);

	//修改帐号信息
	public JSONReturn modifyAccount(long id, String nickname, String acctName);

	//重置帐号密码
	public JSONReturn resetPassword(long id, String acctName);

	//获取帐号数据列表
	public JSONReturn findAccountList(String search, int page, String acctName);

	//获取帐号分页数据
	public JSONReturn findAccountPage(String search, int page, String acctName);

	//获取帐号与角色关联的数据
	public JSONReturn findAccountRole(long id, String acctName);

	//判断用户此次请求是否有权限
	public boolean secureValid(String[] code, String username, MethodType type);

	//更新用户人脸信息
	public int updataFaceUrlByName(String username, String faceUrl, String facepath);
}
