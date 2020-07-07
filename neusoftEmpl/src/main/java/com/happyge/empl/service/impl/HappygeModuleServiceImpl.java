package com.happyge.empl.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.happyge.empl.model.HappygeAccount;
import com.happyge.empl.model.HappygeAccountExample;
import com.happyge.empl.model.HappygeModule;
import com.happyge.empl.model.HappygeModuleExample;
import com.happyge.empl.repository.HappygeAccountMapper;
import com.happyge.empl.repository.HappygeModuleMapper;
import com.happyge.empl.response.HappygeModuleResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.happyge.empl.constant.LoginState;
import com.happyge.empl.constant.db.HappygeAccountType;
import com.happyge.empl.service.HappygeModuleService;
import com.happyge.empl.support.JSONReturn;
import com.happyge.empl.utils.Logable;


@Service
@Transactional(readOnly = true)
public class HappygeModuleServiceImpl extends Logable implements HappygeModuleService {

//	@Autowired
//	private HappygeModuleDao happygeModuledao;
//	@Autowired
//	private HappygeRoleModuleDao happygeRoleModuleDao;
	@Autowired
	private HappygeAccountMapper happygeAccountMapper;
	@Autowired
	private HappygeModuleMapper happygeModuleMapper;

	@Override
	public JSONReturn findMenu(String acctName) {
		HappygeAccountExample ax = new HappygeAccountExample();
		ax.or().andUsernameEqualTo(acctName);
		List<HappygeAccount> users = happygeAccountMapper.selectByExample(ax);
		HappygeAccount account = users.get(0);
		if(account == null) {
			return JSONReturn.buildFailure(LoginState.UNLOGIN);
		}
		List<HappygeModule> modulesList = null;
		if(account.getType() == HappygeAccountType.superAdmin.getType()) {
			HappygeModuleExample mx = new HappygeModuleExample();
			modulesList = happygeModuleMapper.selectByExample(mx);
		}
//		} else {
//			modulesList = happygeModuledao.findMenuByUsername(account.getUsername());
//		}
		if(CollectionUtils.isEmpty(modulesList)) {
			error("未获取到系统菜单");
			return JSONReturn.buildFailure("未获取到系统菜单!");
		}
		List<HappygeModuleResponse> dataList = new ArrayList<HappygeModuleResponse>();
		for(HappygeModule mo : modulesList) {
			dataList.add(new HappygeModuleResponse(mo.getName(), mo.getCode(), mo.getSupercode(), mo.getPage(), mo.getLevel()));
			System.out.println(mo.toString());
		}
				return JSONReturn.buildSuccess(dataList);
	}

	@Override
	public JSONReturn findModuleParameter(String moduleCode, String acctName) {
		return null;
	}


}
