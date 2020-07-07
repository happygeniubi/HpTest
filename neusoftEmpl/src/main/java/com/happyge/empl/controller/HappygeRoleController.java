package com.happyge.empl.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.happyge.empl.annotation.SecureValid;
import com.happyge.empl.constant.MethodType;
import com.happyge.empl.service.HappygeRoleServer;
import com.happyge.empl.support.JSONReturn;

@Controller
@RequestMapping(value = "/role")
public class HappygeRoleController extends AbstractController {

	private static final long serialVersionUID = 6345925112100950787L;
	
	@Autowired
	private HappygeRoleServer happygeRoleServer;
	
	@ResponseBody
	@RequestMapping(value = "/addRole")
	@SecureValid(code = "04002", desc = "新增角色", type = MethodType.ADD)
	public JSONReturn addRole(@RequestParam String name, @RequestParam String description, HttpServletRequest request){
		return happygeRoleServer.addRole(name, description, acctName(request));
	}
	
	@ResponseBody
	@RequestMapping(value = "/deleteRole")
	@SecureValid(code = "04002", desc = "删除角色", type = MethodType.DELETE)
	public JSONReturn deleteRole(@RequestParam long id, HttpServletRequest request){
		return happygeRoleServer.deleteRole(id, acctName(request));
	}
	
	@ResponseBody
	@RequestMapping(value = "/modifyRole")
	@SecureValid(code = "04002", desc = "修改角色", type = MethodType.MODIFY)
	public JSONReturn modifyRole(@RequestParam long id, @RequestParam String name, @RequestParam String description, HttpServletRequest request){
		return happygeRoleServer.modifyRole(id, name, description, acctName(request));
	}
	
	@ResponseBody
	@RequestMapping(value = "/findRoleList")
	@SecureValid(code = "04002", desc = "获取角色数据列表", type = MethodType.FIND)
	public JSONReturn findRoleList(@RequestParam String search, @RequestParam int page, HttpServletRequest request){
		return happygeRoleServer.findRoleList(search, page, acctName(request));
	}
	
	@ResponseBody
	@RequestMapping(value = "/findRolePage")
	@SecureValid(code = "04002", desc = "获取角色数据分页", type = MethodType.FIND)
	public JSONReturn findRolePage(@RequestParam String search, @RequestParam int page, HttpServletRequest request){
		return happygeRoleServer.findRolePage(search, page, acctName(request));
	}
	
	@ResponseBody
	@RequestMapping(value = "/findModuleList")
	@SecureValid(code = "04002", desc = "获取模块数据列表", type = MethodType.MODIFY)
	public JSONReturn findModuleList(@RequestParam long id, HttpServletRequest request){
		return happygeRoleServer.findModuleList(id, acctName(request));
	}
	
	@ResponseBody
	@RequestMapping(value = "/modifyRoleModule")
	@SecureValid(code = "04002", desc = "修改角色与模块之间的关联信息", type = MethodType.MODIFY)
	public JSONReturn modifyRoleModule(@RequestParam long roleId, @RequestParam String moduleCode, @RequestParam int type, HttpServletRequest request){
		return happygeRoleServer.modifyRoleModule(roleId, moduleCode, type, acctName(request));
	}
}
