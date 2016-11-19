package com.lenovo.push.data.serving.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public final static String DATE_PATTERN = "yyyyMMdd";
	
	public final static String MIN_PATTERN = "HHmm";
	public final static String HOUR_PATTERN = "HH";
	

	
	public static String dateToString(Date date, String pattern) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(date);
		} else {
			return null;
		}
	}

	public static Date stringToDate(String dateStr, String pattern) {
		if (dateStr != null) {			
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(pattern);
				return sdf.parse(dateStr);
			} catch (ParseException e) {				
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
	}
	
	public static boolean isEarlierThan(Date date1, Date date2){
		return date1.getTime() < date2.getTime();
	}
	
	public static boolean isEarlierThan(String theDate1, String theDate2){
		return Integer.parseInt(theDate1) < Integer.parseInt(theDate2);
	}
	
	public static boolean isToday(Date date) {
		Calendar today = new GregorianCalendar();
		today.set(Calendar.HOUR_OF_DAY, 0); //anything 0 - 23
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		return date.getTime() >= today.getTime().getTime();
	}
	
	public static String getToday() {
		return getNBeforeDate(0);
	}
	public static String getYesterday() {
		return getNBeforeDate(1);
	}
	
	public static String getNBeforeDate(int n) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, (0-n));
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
		return dateFormat.format(cal.getTime());
	}
	
	public static int getHourOfDay() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.HOUR_OF_DAY);
	}
	
	public static Date timestamp2Date(Timestamp tt) {
		return new Date(tt.getTime());
	}
	
	public static String timestamp2DateStr(Timestamp tt) {
		return dateToString(timestamp2Date(tt), DATE_PATTERN);
	}
	
	public static long getDateDiff(Date d1, Date d2){
		Calendar c = Calendar.getInstance(); 

		c.setTime(d1); 
		c.set(Calendar.MINUTE, 0); 
		c.set(Calendar.SECOND, 0); 
		c.set(Calendar.MILLISECOND, 0); 
		long l1 = c.getTimeInMillis(); 

		c.setTime(d2); 
		c.set(Calendar.MINUTE, 0); 
		c.set(Calendar.SECOND, 0); 
		c.set(Calendar.MILLISECOND, 0); 
		long l2 = c.getTimeInMillis(); 

		return (l1 - l2) / (1000 * 60 * 60 * 24); 
	}
	
	public static long getDateDiff(String s1, String s2, String pattern) throws ParseException{
		Date d1 = stringToDate(s1, pattern);
		Date d2 = stringToDate(s2, pattern);
		return getDateDiff(d1, d2); 
	}
	
	public static String dateCompletion(String dateStr) {
			Date date = stringToDate(dateStr, DATE_PATTERN);			
			return dateToString(date, DATE_TIME_PATTERN);		
	}
	
	public static String getEpochDate() {
		Date epoch = new Date(0);
		return dateToString(epoch, DATE_PATTERN);
	}
}
