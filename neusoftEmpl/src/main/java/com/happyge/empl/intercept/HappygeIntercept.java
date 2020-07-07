package com.happyge.empl.intercept;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.happyge.empl.annotation.SecureValid;
import com.happyge.empl.constant.LoginState;
import com.happyge.empl.constant.SessionKey;
import com.happyge.empl.constant.db.HappygeAccountType;
import com.happyge.empl.model.HappygeAccount;
import com.happyge.empl.service.HappygeAccountService;
import com.happyge.empl.support.JSONReturn;

public class HappygeIntercept extends HandlerInterceptorAdapter{

	/**
	 * 1.根据URI判断本次请求是否需要拦截
	 * 2.判断用户是否登录
	 * 3.判断当前请求是否需要拦截
	 * 4.判断当前帐号是否存在(去掉了)
	 * 5.是否为超级管理员
	 * 6.判断当前请求是否有权限
	 */

	@Autowired
	private HappygeAccountService happygeAccountService;

	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		if(request.getRequestURI().contains("/hzl/0")){
			return true;
		}
		//判断当前用户是否已经登录
		HappygeAccount account = (HappygeAccount) request.getSession().getAttribute(SessionKey.LOGIN_USER_INFO);
		if(account == null) {
			response.getOutputStream().print(JSONObject.fromObject(JSONReturn.buildFailure(LoginState.UNLOGIN)).toString());
			return false;
		}
		HandlerMethod method = (HandlerMethod) handler;
		SecureValid valid = method.getMethod().getAnnotation(SecureValid.class);
		if(valid == null) {
			return true;
		}
		//判断是否为超级管理员
		if(account.getType() == HappygeAccountType.superAdmin.getType()) {
			return true;
		}
		//判断当前用户是否有权限
		if(!happygeAccountService.secureValid(valid.code(), account.getUsername(), valid.type())){
			response.getOutputStream().print(JSONObject.fromObject(JSONReturn.buildFailure(LoginState.PERMISSION_DENIED)).toString());
			return false;
		}
		return true;
	}
}
