package com.happyge.empl.constant.db;

public enum HappygeEmployeesAddressType {
	
	register(0, "户籍地址"), current(1, "现居住地址");
	
	private int type;
	private String name;
	
	private HappygeEmployeesAddressType() {
		// TODO Auto-generated constructor stub
	}

	private HappygeEmployeesAddressType(int type, String name) {
		this.type = type;
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int state) {
		this.type = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
