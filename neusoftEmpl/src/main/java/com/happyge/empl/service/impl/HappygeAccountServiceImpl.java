package com.happyge.empl.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.happyge.empl.constant.DateFormatConstant;
import com.happyge.empl.constant.MethodType;
import com.happyge.empl.constant.PageConstant;
import com.happyge.empl.constant.SessionKey;
import com.happyge.empl.constant.db.HappygeAccountState;
import com.happyge.empl.model.*;
import com.happyge.empl.repository.HappygeAccountMapper;
import com.happyge.empl.repository.HappygeAccountRoleMapper;
import com.happyge.empl.repository.HappygeRoleMapper;
import com.happyge.empl.repository.HappygeRoleModuleMapper;
import com.happyge.empl.response.HappygeAccountResponse;
import com.happyge.empl.response.HappygeSelectResponse;
import com.happyge.empl.service.HappygeAccountService;
import com.happyge.empl.support.JSONReturn;
import com.happyge.empl.utils.DateTimeUtil;
import com.happyge.empl.utils.EncryptUtil;
import com.happyge.empl.utils.Logable;
import com.te5l.common.support.TePageDate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class HappygeAccountServiceImpl extends Logable implements HappygeAccountService {

	private static final String DEFAULT_PASSWORD = "happygeniubi";

	@Autowired
	private HappygeAccountMapper happygeAccountMapper;
	@Autowired
	private HappygeRoleMapper happygeRoleMapper;
	@Autowired
	private HappygeRoleModuleMapper happygeRoleModuleMapper;
	@Autowired
	private HappygeAccountRoleMapper happygeAccountRoleMapper;

	@Override
	public List<HappygeAccount> findAllAccount() {
		return null;
	}

	@Override
	public JSONReturn login(String username, String password, HttpServletRequest request) {
		// 通过用户名到数据库中检测用户是否存在
		info("{}登录了系统,密码为{}", username, password);

		if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
			error("\t登录失败,用户名或密码为空");
			return JSONReturn.buildFailure("非法操作!");
		}

		HappygeAccountExample ex = new HappygeAccountExample();
		ex.or().andUsernameEqualTo(username);
		List<HappygeAccount> users = happygeAccountMapper.selectByExample(ex);
		//select * from happyge_account where username = username;
		if(CollectionUtils.isEmpty(users)){
			//数据不存在，说明用户名不存在
			warning("\t登录失败,用户名不存在; {}", username);
			return JSONReturn.buildFailure("帐号或密码不正确!");
		}
		// 通过密码检测传入的密码是否正确
		HappygeAccount account = users.get(0);
		if(!account.getPassword().equals(EncryptUtil.md5(password))){
			warning("\t登录失败,密码不正确;username : {}, password : {}",username ,password);
			return JSONReturn.buildFailure("帐号或密码不正确!");
		}
		request.getSession().setAttribute(SessionKey.LOGIN_USER_INFO, account);
		info("\t登录成功; {}",username);
		return JSONReturn.buildSuccessWithEmptyBody();
	}

	@Override
	public JSONReturn addAccount(String username, String nickname, String password, String acctName) {
		return null;
	}

	@Override
	public JSONReturn saveRole(long accountId, long roleId, String acctName) {
		HappygeAccount account = happygeAccountMapper.selectByPrimaryKey(accountId);
		if(account == null || account.getState() == HappygeAccountState.delete.getState()) {
			return JSONReturn.buildFailure("帐号不存在!");
		}
		HappygeRole role = happygeRoleMapper.selectByPrimaryKey(roleId);
		if(role == null){
			return JSONReturn.buildFailure("角色不存在!");
		}
		HappygeAccountRoleExample exx = new HappygeAccountRoleExample();
		HappygeAccountRoleExample.Criteria criteria1 = exx.createCriteria();
		criteria1.andUsernameEqualTo(account.getUsername()).andLabelEqualTo(role.getRolelabel());
		List<HappygeAccountRole> roles = happygeAccountRoleMapper.selectByExample(exx);
		if(CollectionUtils.isEmpty(roles)) {
			happygeAccountRoleMapper.insert(new HappygeAccountRole(account.getUsername(), role.getRolelabel()));
			return JSONReturn.buildSuccessWithEmptyBody();
		}
		happygeAccountRoleMapper.deleteByExample(exx);
		return JSONReturn.buildSuccessWithEmptyBody();
	}

	@Override
	public JSONReturn deleteAccount(long id, String acctName) {
		return null;
	}

	@Override
	public JSONReturn modifyAccount(long id, String nickname, String acctName) {
		return null;
	}

	@Override
	public JSONReturn resetPassword(long id, String acctName) {
		return null;
	}

	@Override
	public JSONReturn findAccountList(String search, int page, String acctName) {
		//List<HappygeAccountListDto> dataList = happygeAccountDao.findAccountList(search, page, PageConstant.DEFAULT_LINE);
		HappygeAccountExample ex = new HappygeAccountExample();
		HappygeAccountExample.Criteria criteria = ex.createCriteria();
		criteria.andStateNotEqualTo(HappygeAccountState.delete.getState());
		if(StringUtils.isNotEmpty(search)){
			criteria.andUsernameLike("%"+ search +"%");
		}
		Page pg = PageHelper.startPage(page, PageConstant.DEFAULT_LINE).setOrderBy("id desc");
		List<HappygeAccount> dataList = happygeAccountMapper.selectByExample(ex);
		if(CollectionUtils.isEmpty(dataList)){
			return JSONReturn.buildFailure("未获取到相关数据!");
		}
		HappygeAccountResponse resp = null;
		List<HappygeAccountResponse> responses = new ArrayList<HappygeAccountResponse>();
		for(HappygeAccount data : dataList) {
					resp = new HappygeAccountResponse(data.getId(),data.getCreation(),data.getType(),data.getUsername(),data.getNickname(),data.getCreator());
					resp.setTime(DateTimeUtil.dateToString(data.getCreation(), DateFormatConstant.YYYY_MM_DD));
					responses.add(resp);
		}
		return JSONReturn.buildSuccess(TePageDate.pack(responses, pg, page));
	}

	@Override
	public JSONReturn findAccountPage(String search, int page, String acctName) {
		return null;
	}

	@Override
	public JSONReturn findAccountRole(long id, String acctName) {
		HappygeAccount account = happygeAccountMapper.selectByPrimaryKey(id);
		if(account == null || account.getState() == HappygeAccountState.delete.getState()) {
			return JSONReturn.buildFailure("帐号不存在!");
		}
		HappygeRoleExample ex = new HappygeRoleExample();
		HappygeRoleExample.Criteria criteria = ex.createCriteria();
		criteria.andCreationIsNotNull();
		List<HappygeRole> roles = happygeRoleMapper.selectByExample(ex);
		if(CollectionUtils.isEmpty(roles)){
			return JSONReturn.buildFailure("角色不存在!");
		}
		List<HappygeSelectResponse> result = new ArrayList<HappygeSelectResponse>();
		boolean selected = false;
		for(HappygeRole role : roles) {
			HappygeAccountRoleExample exx = new HappygeAccountRoleExample();
			HappygeAccountRoleExample.Criteria criteria1 = exx.createCriteria();
			criteria1.andUsernameEqualTo(account.getUsername()).andLabelLike(role.getRolelabel());
			List<HappygeAccountRole> happygeAccountRoles = happygeAccountRoleMapper.selectByExample(exx);
			selected = CollectionUtils.isNotEmpty(happygeAccountRoles);
			result.add(new HappygeSelectResponse(role.getId(), role.getRolename(), selected));
		}
			return JSONReturn.buildSuccess(result);
	}

	@Override
	public boolean secureValid(String[] code, String username, MethodType type) {
		if(ArrayUtils.isEmpty(code)){
			return false;
		}
		String codes = analysisModuleArray(code);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("codes",codes);
		map.put("username",username);
		List<HappygeRoleModule> modules = happygeRoleModuleMapper.secureValid(map);
		if(CollectionUtils.isEmpty(modules)){
			return false;
		}
		for(HappygeRoleModule rm : modules) {
			if(type == MethodType.FIND && rm.getFinds()) {
				return true;
			} else if(type == MethodType.ADD && rm.getAdds()) {
				return true;
			} else if(type == MethodType.MODIFY && rm.getModifys()) {
				return true;
			} else if(type == MethodType.DELETE && rm.getDeletes()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int updataFaceUrlByName(String username, String faceUrl, String facepath) {
		return happygeAccountMapper.updataFaceUrlByName(username, faceUrl,facepath);
	}

	public String analysisModuleArray(String[] code) {
		if(ArrayUtils.isEmpty(code)){
			return null;
		}
		StringBuffer buffer = new StringBuffer();
		for(String cd : code) {
			buffer.append(cd).append(",");
		}
		//'a','b','c',长度-1是为了将最后一个,号去掉
		return buffer.toString().substring(0, buffer.toString().length() - 1);
	}

}
  