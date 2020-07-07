package com.happyge.empl.response;

import java.io.Serializable;

//封装系统模块数据列表实体类
public class HappygeModuleListResponse implements Serializable{

	private static final long serialVersionUID = -447011661891707579L;
	
	private long id; // 模块ID号
	private String name; // 模块名称
	private String code; // 模块编号
	private String superCode; // 上级编号
	private boolean finds; // 查询权限
	private boolean adds; //增加权限
	private boolean deletes; //删除权限
	private boolean modifys; //修改权限

	public HappygeModuleListResponse() {
		// TODO Auto-generated constructor stub
	}

	public HappygeModuleListResponse(long id, String name, String code,
																	 String superCode, boolean finds, boolean adds, boolean deletes,
																	 boolean modifys) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.superCode = superCode;
		this.finds = finds;
		this.adds = adds;
		this.deletes = deletes;
		this.modifys = modifys;
	}
	

	public HappygeModuleListResponse(long id, String name, String code, String superCode) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.superCode = superCode;
	}

	@Override
	public String toString() {
		return "HappygeModuleListDto [id=" + id + ", name=" + name + ", code="
				+ code + ", superCode=" + superCode + ", finds=" + finds
				+ ", adds=" + adds + ", deletes=" + deletes + ", modifys="
				+ modifys + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSuperCode() {
		return superCode;
	}

	public void setSuperCode(String superCode) {
		this.superCode = superCode;
	}

	public boolean isFinds() {
		return finds;
	}

	public void setFinds(boolean finds) {
		this.finds = finds;
	}

	public boolean isAdds() {
		return adds;
	}

	public void setAdds(boolean adds) {
		this.adds = adds;
	}

	public boolean isDeletes() {
		return deletes;
	}

	public void setDeletes(boolean deletes) {
		this.deletes = deletes;
	}

	public boolean isModifys() {
		return modifys;
	}

	public void setModifys(boolean modifys) {
		this.modifys = modifys;
	}
	
	
}
