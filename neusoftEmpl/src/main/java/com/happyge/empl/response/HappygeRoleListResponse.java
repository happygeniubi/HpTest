package com.happyge.empl.response;

import java.io.Serializable;

public class HappygeRoleListResponse implements Serializable{
	
	private static final long serialVersionUID = 1612098631024453954L;
	
	private long id;
	private String name;
	private String description;
	private String time;
	private String creator;

	public HappygeRoleListResponse() {
		// TODO Auto-generated constructor stub
	}

	public HappygeRoleListResponse(long id, String name, String description,
																 String time, String creator) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.time = time;
		this.creator = creator;
	}

	@Override
	public String toString() {
		return "HappygeRoleListDto [id=" + id + ", name=" + name
				+ ", description=" + description + ", time=" + time
				+ ", creator=" + creator + "]";
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
	
}
