package com.project;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.project.cache.BBConstant.TIME_TYPE;
import com.project.utility.ObjectUtilty;
import com.project.utility.QueryBuilder;

public class MainTest {
	//private static final SimpleDateFormat DF_withNotSecondAndMillisecond =new SimpleDateFormat("ddMMyyyyhhmm");
	public static void main(String[] args) {
        
		QueryBuilder builder = new QueryBuilder("Select * from question_definition where id=?");
		Long[] values = {2L,3L,5L,55L,90L};
		builder.setParams(values);
		System.out.println(builder.toString());
		
	}

}
