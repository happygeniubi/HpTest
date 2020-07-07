package com.happyge.empl.utils;

import java.io.Serializable;

import com.happyge.empl.response.PageResponse;

//分页工具类
public class PageUtil implements Serializable{

	private static final long serialVersionUID = 4549552658487052829L;
	
	public static PageResponse pack(int size,int count,int page) {
		PageResponse dto = new PageResponse();
		dto.setSize(size);
		dto.setCurrent(page);
		dto.setCount(count);
		
		//计算总页数
		if(count % size > 0) {
			dto.setTotal((count / size) + 1);
		}else {
			dto.setTotal((count / size));
		}
		
		return dto;
	}
}
