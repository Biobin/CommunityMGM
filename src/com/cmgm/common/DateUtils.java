package com.cmgm.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 用于Date日期类型的转换
 * @author kww
 * 2017年3月29日
 */
public class DateUtils {

	/**
	 * 
	 * @param dateFormat 日期格式，例如 yyyy-MM-dd HH:mm:ss
	 * @param dateStr 需要转换的日期字符串，例如 2017-03-29 12:23:54
	 * @return
	 */
//	public static final Date getDate(String dateFormat,String dateStr) {
//		Date date=null;
//		if(dateStr==null || dateFormat==null || dateFormat.equals("") || dateStr.equals("")){
//			return null;
//		} else {
//			SimpleDateFormat sdf=new SimpleDateFormat(dateFormat);
//			try {
//			    //转换会抛出异常
//			    date=sdf.parse(dateStr);
//			} catch (ParseException e) {
//			    // TODO Auto-generated catch block
//			    e.printStackTrace();
//			}
//		}
//		return date;
//	}
	
	/**
	 * 把日期转换为字符串
	 * @param dateFormat 日期格式，例如 yyyy-MM-dd HH:mm:ss
	 * @param date 需要转换的日期
	 * @return
	 */
	public static final String getDateString(String dateFormat,Date date) {
		String dateStr=null;
		if(date==null || dateFormat==null || dateFormat.equals("")){
			return null;
		} else {
			SimpleDateFormat sdf=new SimpleDateFormat(dateFormat);
			dateStr=sdf.format(date);
		}
		return dateStr;
	}
	
	/**
	 * 主要用于从数据库查出数据之后直接调用转换
	 * @param dateFormat 日期格式，例如 yyyy-MM-dd HH:mm:ss
	 * @param objDateStr 需要转换的日期Object类型，例如 2017-03-29 12:23:54
	 * @return
	 */
	public static final Date getDate(String dateFormat,Object objDateStr) {
		Date date=null;
		if(objDateStr==null || dateFormat==null || dateFormat.equals("") || objDateStr.equals("")){
			return null;
		} else {
			SimpleDateFormat sdf=new SimpleDateFormat(dateFormat);
			try {
			    //转换会抛出异常
			    date=sdf.parse(String.valueOf(objDateStr));
			} catch (ParseException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			}
		}
		return date;
	}
	
	/**
	 * 主要用于从数据库查出数据之后直接调用转换
	 * @param dateFormat 日期格式，例如 yyyy-MM-dd HH:mm:ss
	 * @param objDateStr 需要转换的日期Object类型，例如 2017-03-29 12:23:54
	 * @return
	 */
	public static final LocalDateTime getLocalDateTime(String dateFormat,Object objDateStr) {
		LocalDateTime localDateTime=null;
		if(objDateStr==null || dateFormat==null || dateFormat.equals("") || objDateStr.equals("")){
			return null;
		} else {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormat);
			localDateTime = LocalDateTime.parse(String.valueOf(objDateStr), dtf);
		}
		return localDateTime;
	}
	
	/**
	 * 
	 * @param dateTimeFormat 输出的格式，例如 yyyy-MM-dd HH:mm:ss
	 * @param dateTime LocalDateTime类型，需要转换的日期带时间
	 * @return
	 */
	public static final String getLocalDateTimeString(String dateFormat,LocalDateTime dateTime) {
		String dateTimeStr=null;
		if(dateTime==null || dateFormat==null || dateFormat.equals("")){
			return null;
		} else {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormat);
			dateTimeStr=dateTime.format(dtf);
		}
		return dateTimeStr;
	}
	
	/**
	 * 主要用于从数据库查出数据之后直接调用转换
	 * @param dateFormat 日期格式，例如 yyyy-MM-dd
	 * @param objDateStr 需要转换的日期Object类型，例如 2017-03-29
	 * @return
	 */
	public static final LocalDate getLocalDate(String dateFormat,Object objDateStr) {
		LocalDate localDate=null;
		if(objDateStr==null || dateFormat==null || dateFormat.equals("") || objDateStr.equals("")){
			return null;
		} else {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormat);
			localDate = LocalDate.parse(String.valueOf(objDateStr), dtf);
		}
		return localDate;
	}
	
	/**
	 * 
	 * @param dateFormat 输出的格式，例如 yyyy-MM-dd
	 * @param dateTime LocalDate类型，需要转换的日期带时间
	 * @return
	 */
	public static final String getLocalDateString(String dateFormat,LocalDate date) {
		String dateTimeStr=null;
		if(date==null || dateFormat==null || dateFormat.equals("")){
			return null;
		} else {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormat);
			dateTimeStr=date.format(dtf);
		}
		return dateTimeStr;
	}
}
