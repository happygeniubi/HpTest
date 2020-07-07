package com.happyge.empl.service;

import com.happyge.empl.support.JSONReturn;

public abstract interface HappygeRoleServer {

	//新增角色信息
	JSONReturn addRole(String roleName, String roleDescription, String acctName);

	//删除角色
	JSONReturn deleteRole(long id, String acctName);
	
	//修改角色信息
	JSONReturn modifyRole(long id, String name, String description, String acctName);
	
	//获取角色数据列表
	JSONReturn findRoleList(String search, int page, String acctName);
	
	//获取角色数据列表分页
	JSONReturn findRolePage(String search, int page, String acctName);
		
	//获取模块信息
	JSONReturn findModuleList(long id, String acctName);
	
	//修改角色与模块之间的关联数据表
	JSONReturn modifyRoleModule(long roleId, String moduleCode, int type, String acctName);

}
