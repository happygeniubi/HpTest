package com.happyge.empl.constant.db;

public enum HappygeOptionType {
	
	education(1, "文化程度"),marriage(2, "婚姻状况"),political(3, "政治面貌"),nation(4, "民族");
	
	private int type;
	private String values;
	
	private HappygeOptionType() {
		// TODO Auto-generated constructor stub
	}
	
	private HappygeOptionType(int type, String values) {
		this.type = type;
		this.values = values;
	}


	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}
	
}
