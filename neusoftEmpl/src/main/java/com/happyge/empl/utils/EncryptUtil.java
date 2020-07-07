package com.happyge.empl.utils;

import java.io.Serializable;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;



public class EncryptUtil implements Serializable{

	private static final long serialVersionUID = 6664136746797358715L;
	
	//MD5加密字符串
	public static String md5(String str) {
		if(StringUtils.isEmpty(str)) {
			return "";
		}
		return DigestUtils.md5Hex(str);
	}
	
//	@Test
//	public void test(){
//		System.out.println(md5("happygeniubi"));
//	}
}
