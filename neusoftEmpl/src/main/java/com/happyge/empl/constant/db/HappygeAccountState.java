package com.happyge.empl.constant.db;

public enum HappygeAccountState {
	
	delete(-1,"删除"),normal(0,"正常");
	
	private int state;
	private String name;
	
	private HappygeAccountState() {
		// TODO Auto-generated constructor stub
	}

	private HappygeAccountState(int state, String name) {
		this.state = state;
		this.name = name;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
