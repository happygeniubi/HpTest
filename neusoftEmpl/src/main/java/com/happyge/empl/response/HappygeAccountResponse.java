package com.happyge.empl.response;

import java.io.Serializable;
import java.util.Date;

//封装帐号列表实体类
public class HappygeAccountResponse implements Serializable  {

	private static final long serialVersionUID = 8801364357238228052L;
	
	private long id;
	private Date creation;
	private int type;
	private String username;
	private String nickname;
	private String creator;
	private String time; //封装转换过后的时间
	
	public HappygeAccountResponse() {

	}

	public HappygeAccountResponse(long id, Date creation, int type, String username, String nickname, String creator) {
		super();
		this.id = id;
		this.creation = creation;
		this.type = type;
		this.username = username;
		this.nickname = nickname;
		this.creator = creator;
	}
	
	@Override
	public String toString() {
		return "HappygeAccountListDto [id=" + id + ", creation=" + creation
				+ ", type=" + type + ", username=" + username + ", nickname="
				+ nickname + ", creator=" + creator + ", time=" + time + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreation() {
		return creation;
	}

	public void setCreation(Date creation) {
		this.creation = creation;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
