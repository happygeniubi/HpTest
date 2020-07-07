package com.happyge.empl.constant.db;

public enum HappygeEmployeesInfoState {
	
	internship(0, "实习员工"),formal(1, "正式员工"),quit(2, "离职员工");
	
	private int state;
	private String name;
	
	private HappygeEmployeesInfoState() {
		// TODO Auto-generated constructor stub
	}

	private HappygeEmployeesInfoState(int state, String name) {
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
