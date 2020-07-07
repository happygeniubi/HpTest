package com.happyge.empl.service;

import com.happyge.empl.support.JSONReturn;

public abstract interface HappygeModuleService {
	
	//获取导航菜单
	public abstract JSONReturn findMenu(String acctName);

	//获取权限
	public abstract JSONReturn findModuleParameter(String moduleCode, String acctName);
}
