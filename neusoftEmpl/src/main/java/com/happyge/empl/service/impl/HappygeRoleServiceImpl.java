package com.happyge.empl.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.management.relation.Relation;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.happyge.empl.model.*;
import com.happyge.empl.repository.HappygeModuleMapper;
import com.happyge.empl.repository.HappygeRoleMapper;
import com.happyge.empl.repository.HappygeRoleModuleMapper;
import com.happyge.empl.response.HappygeModuleListResponse;
import com.happyge.empl.response.HappygeRoleListResponse;
import com.te5l.common.support.TePageDate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.happyge.empl.constant.DateFormatConstant;
import com.happyge.empl.constant.PageConstant;
import com.happyge.empl.service.HappygeRoleServer;
import com.happyge.empl.support.JSONReturn;
import com.happyge.empl.utils.DateTimeUtil;
import com.happyge.empl.utils.Logable;
import com.happyge.empl.utils.PageUtil;

@Service
@Transactional
public class HappygeRoleServiceImpl extends Logable implements HappygeRoleServer {

	@Autowired
	HappygeRoleMapper happygeRoleMapper;

	@Autowired
	HappygeModuleMapper happygeModuleMapper;

	@Autowired
	HappygeRoleModuleMapper happygeRoleModuleMapper;

	@Override
	public JSONReturn addRole(String roleName, String roleDescription, String acctName) {
		return null;
	}

	@Override
	public JSONReturn deleteRole(long id, String acctName) {
		return null;
	}

	@Override
	public JSONReturn modifyRole(long id, String name, String description, String acctName) {
		return null;
	}

	@Override
	public JSONReturn findRoleList(String search, int page, String acctName) {
		HappygeRoleExample ex = new HappygeRoleExample();
		HappygeRoleExample.Criteria criteria = ex.createCriteria();
		if(StringUtils.isNotEmpty((search))) {
			criteria.andRolenameLike("%" + search + "%");
		}
		Page pg = PageHelper.startPage(page, PageConstant.DEFAULT_LINE).setOrderBy("id desc");
		List<HappygeRole> roles = happygeRoleMapper.selectByExample(ex);
		if(CollectionUtils.isEmpty(roles)) {
			return JSONReturn.buildFailure("没有相关数据!");
		}
		List<HappygeRoleListResponse> responses = new ArrayList<HappygeRoleListResponse>();
		HappygeRoleListResponse res = null;
		for (HappygeRole role : roles) {
			res = new HappygeRoleListResponse(role.getId(), role.getRolename(), role.getRoledescription(), DateTimeUtil.dateToString(role.getCreation(), DateFormatConstant.YYYY_MM_DD) , role.getCreator());
			responses.add(res);
		}
		return JSONReturn.buildSuccess(TePageDate.pack(responses, pg, page));
	}

	@Override
	public JSONReturn findRolePage(String search, int page, String acctName) {
		return null;
	}

	@Override
	public JSONReturn findModuleList(long id, String acctName) {
		HappygeRole role = happygeRoleMapper.selectByPrimaryKey(id);
		if(role == null){
			return JSONReturn.buildFailure("角色不存在!");
		}
		HappygeModuleExample ex = new HappygeModuleExample();
		HappygeModuleExample.Criteria criteria = ex.createCriteria();
		List<HappygeModule> modules = happygeModuleMapper.selectByExample(ex);
		if(CollectionUtils.isEmpty(modules)){
			return JSONReturn.buildFailure("没有相关数据!");
		}
		List<HappygeModuleListResponse> result = new ArrayList<HappygeModuleListResponse>();
		HappygeModuleListResponse data = null;
		HappygeRoleModule roleModule = null;
		List<HappygeRoleModule> roleModules = null;
		for(HappygeModule mo : modules) {
			HappygeRoleModuleExample exx = new HappygeRoleModuleExample();
			HappygeRoleModuleExample.Criteria criteria1 = exx.createCriteria();
			criteria1.andLabelEqualTo(role.getRolelabel()).andModulecodeEqualTo(mo.getCode());
			data = new HappygeModuleListResponse(mo.getId(),mo.getName(),mo.getCode(),mo.getSupercode());
			roleModules = happygeRoleModuleMapper.selectByExample(exx);
			if(CollectionUtils.isNotEmpty(roleModules)) {
				roleModule = roleModules.get(0);
				data.setFinds(roleModule.getFinds());
				data.setAdds(roleModule.getAdds());
				data.setDeletes(roleModule.getDeletes());
				data.setModifys(roleModule.getModifys());
			}
			result.add(data);
		}
		return JSONReturn.buildSuccess(result);
	}

	@Override
	public JSONReturn modifyRoleModule(long roleId, String moduleCode, int type, String acctName) {
		HappygeRole role = happygeRoleMapper.selectByPrimaryKey(roleId);
		if(role == null) {
			return JSONReturn.buildFailure("角色不存在!");
		}
		HappygeModuleExample ex = new HappygeModuleExample();
		HappygeModuleExample.Criteria criteria = ex.createCriteria();
		criteria.andCodeEqualTo(moduleCode);
		List<HappygeModule> modules = happygeModuleMapper.selectByExample(ex);
		if(CollectionUtils.isEmpty(modules)){
			return JSONReturn.buildFailure("模块不存在");
		}
		HappygeModule happygeModule = modules.get(0);

		HappygeRoleModuleExample exx = new HappygeRoleModuleExample();
		HappygeRoleModuleExample.Criteria criterial = exx.createCriteria();
		criterial.andLabelEqualTo(role.getRolelabel()).andModulecodeEqualTo(moduleCode);
		List<HappygeRoleModule> happygeRoleModules = happygeRoleModuleMapper.selectByExample(exx);
		HappygeRoleModule module = null;
		if(CollectionUtils.isEmpty(happygeRoleModules)) {
			module = new HappygeRoleModule(DateTimeUtil.getCurrentTime(), role.getRolelabel(), happygeModule.getCode(), false, false, false, false, happygeModule.getSupercode());
			happygeRoleModuleMapper.insert(module);
		} else {
			module = happygeRoleModules.get(0);
		}
		switch(type) {
			case 0 :
				module.setFinds(!module.getFinds());
				break;
			case 1 :
				module.setAdds(!module.getAdds());
				break;
			case 2:
				module.setModifys(!module.getModifys());
				break;
			case 3:
				module.setDeletes(!module.getDeletes());
				break;
		}
		happygeRoleModuleMapper.updateByPrimaryKey(module);
		return JSONReturn.buildSuccessWithEmptyBody();
	}
}
