package com.happyge.empl.controller;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;

import com.happyge.empl.constant.SessionKey;
import com.happyge.empl.model.HappygeAccount;

public class AbstractController implements Serializable{

	private static final long serialVersionUID = -4282647317070146973L;
	
	public String acctName(HttpServletRequest request) {
		try {
			HappygeAccount account = (HappygeAccount) request.getSession().getAttribute(SessionKey.LOGIN_USER_INFO);
			return account == null ? "" : account.getUsername();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ""; 
	}
}
