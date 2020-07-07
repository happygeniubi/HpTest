package com.happyge.empl.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.happyge.empl.annotation.SecureValid;
import com.happyge.empl.constant.MethodType;
import com.happyge.empl.service.HappygeAccountService;
import com.happyge.empl.support.JSONReturn;

@Controller
@RequestMapping(value = "/account")
public class HappygeAccountController extends AbstractController {
	/**
	 * 李裕程的杰作：
	 * public static void
	 */
	private static final long serialVersionUID = -7225519515533744336L;

	@Autowired
	private HappygeAccountService happygeAccountService;

	@ResponseBody
	@RequestMapping(value = "addAccount")
	@SecureValid(code = "04001", desc = "添加或者保存帐号信息", type = MethodType.ADD)
	public JSONReturn addAccout(@RequestParam String username, @RequestParam String nickname, @RequestParam String password, HttpServletRequest request) {
		return happygeAccountService.addAccount(username, nickname, password, acctName(request));
	}

	@ResponseBody
	@RequestMapping(value = "saveRole")
	@SecureValid(code = "04001", desc = "添加或者保存角色信息", type = MethodType.ADD)
	public JSONReturn saveRole(@RequestParam long accountId, @RequestParam long roleId, HttpServletRequest request) {
		return happygeAccountService.saveRole(accountId, roleId, acctName(request));				
	}
	
	@ResponseBody
	@RequestMapping(value = "deleteAccount")
	@SecureValid(code = "04001", desc = "删除帐号", type = MethodType.DELETE)
	public JSONReturn deleteAccout(@RequestParam long id, HttpServletRequest request) {
		return happygeAccountService.deleteAccount(id, acctName(request));
	}
	
	@ResponseBody
	@RequestMapping(value = "modifyAccount")
	@SecureValid(code = "04001", desc = "修改帐号", type = MethodType.MODIFY)
	public JSONReturn modifyAccount(@RequestParam long id, @RequestParam String nickname, HttpServletRequest request) {
		return happygeAccountService.modifyAccount(id, nickname, acctName(request));
	}

	@ResponseBody
	@RequestMapping(value = "resetPassword")
	@SecureValid(code = "04001", desc = "重置密码", type = MethodType.MODIFY)
	public JSONReturn resetPassword(@RequestParam long id, HttpServletRequest request) {
		return happygeAccountService.resetPassword(id, acctName(request));
	}
	
	@ResponseBody
	@RequestMapping(value = "findAccountList")
	@SecureValid(code = "04001", desc = "获取帐号数据列表", type = MethodType.FIND)
	public JSONReturn findAccountList(@RequestParam String search, @RequestParam int page, HttpServletRequest request) {
		return happygeAccountService.findAccountList(search, page, acctName(request));
	}
	
	@ResponseBody
	@RequestMapping(value = "findAccountPage")
	@SecureValid(code = "04001", desc = "获取帐号分页信息", type = MethodType.FIND)
	public JSONReturn findAccountPage(@RequestParam String search, @RequestParam int page, HttpServletRequest request) {
		return happygeAccountService.findAccountPage(search, page, acctName(request));
	}

	@ResponseBody
	@RequestMapping(value = "findAccountRole")
	@SecureValid(code = "04001", desc = "获取帐号关联的角色", type = MethodType.FIND)
	public JSONReturn findAccountRole(@RequestParam long id, HttpServletRequest request) {
		return happygeAccountService.findAccountRole(id, acctName(request));
	}
}
