package com.project;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.project.cache.BBConstant.TIME_TYPE;
import com.project.utility.ObjectUtilty;

public class MainTest {
	private static final SimpleDateFormat DF_withNotSecondAndMillisecond =new SimpleDateFormat("ddMMyyyyhhmm");
	public static void main(String[] args) {
		Date date1 = new Date();
		Date date2 = new Date();
		
		Date date1n = ObjectUtilty.createNextDate(TIME_TYPE.MINUTE, 2);
		Date date2n = ObjectUtilty.createNextDate(TIME_TYPE.MINUTE, 2);
		
		
		String da1 = DF_withNotSecondAndMillisecond.format(date1);
		String da2 = DF_withNotSecondAndMillisecond.format(date2);
		
		String d1 = DF_withNotSecondAndMillisecond.format(date1n);
		String d2 = DF_withNotSecondAndMillisecond.format(date2n);
		
		System.out.println(ObjectUtilty.compareDate_ddMMyyyyhhmm(date1n, date1n));
		
		System.out.println("calisan date -- > "+da1+"   ileriki tarih --> "+d1);
		System.out.println("calisan date -- > "+da2+"   ileriki tarih --> "+d2);
	}

}
