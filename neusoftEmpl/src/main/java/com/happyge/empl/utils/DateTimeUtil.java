package com.happyge.empl.utils;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.happyge.empl.constant.DateFormatConstant;


//时间工具类
public class DateTimeUtil implements Serializable{

	private static final long serialVersionUID = 2792868984689167427L;
	
	//获取当前时间
	public static Date getCurrentTime() {
		return new Date();
	}
	
	//将Date转换为字符串
	public static String dateToString(Date date,String format) {
		return new SimpleDateFormat(format).format(date);
	}
	
	//将日期格式的字符串转换为时间
	public static Date stringToDate(String dateStr, String format) {
		try {
			return new SimpleDateFormat(format).parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getCurrentTime();
	}
	
//	@Test
//	public void test() {
//		System.out.println(dateToString(getCurrentTime(), DateFormatConstant.MM_DD));
//		System.out.println(stringToDate("2015-01-01 00:00:00", DateFormatConstant.Y_M_D_H_M_S));
//	}
}
