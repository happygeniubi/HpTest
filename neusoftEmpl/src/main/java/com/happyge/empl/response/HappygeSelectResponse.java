package com.happyge.empl.response;

import java.io.Serializable;

//封装下拉菜单数据列表
public class HappygeSelectResponse implements Serializable{

	private static final long serialVersionUID = -245771237888780067L;
	
	private long id; 
	private String name;
	private boolean selected;
	
	public HappygeSelectResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public HappygeSelectResponse(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public HappygeSelectResponse(long id, String name, boolean selected) {
		super();
		this.id = id;
		this.name = name;
		this.selected = selected;
	}
	

	@Override
	public String toString() {
		return "HappygeSelectDto [id=" + id + ", name=" + name + ", selected="
				+ selected + "]";
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

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
}
