package com.project.utility;

import java.util.List;

// sadece ic sorgularda kullanılabilir. Dış input datası için sql injection problemi içerebilir. Gelistirme yapılmalıdır.
public class QueryBuilder {

	private String queryString = "";

	public void append(String query) {
		queryString += query;
	}
	
	public QueryBuilder() {};

	public void filter(String condition,Object value) {
		queryString += " AND "+condition + " = ";
		if(value instanceof String) {
			queryString += "'"+value+"'";
		}else if(value instanceof Long) {
			queryString += value.toString();
		}else if(value instanceof Integer) {
			queryString += value.toString();
		}
	}
	
	public void filterIN(String condition,Object...values) {
		String filterString = condition+" (";
		for(Object value : values) {
			if(value instanceof String) {
				filterString += "'"+value+"'";
			}else if(value instanceof Long) {
				filterString += value.toString();
			}else if(value instanceof Integer) {
				filterString += value.toString();
			}
			if(value!=values[values.length-1]) {
				filterString+=",";
			}
		}
		filterString=" )";
		queryString +=filterString;
	}
	
	public void filterIN(Object...values) {
		String filterString = " (";
		for(Object value : values) {
			if(value instanceof String) {
				filterString += "'"+value+"'";
			}else if(value instanceof Long) {
				filterString += value.toString();
			}else if(value instanceof Integer) {
				filterString += value.toString();
			}
			if(value!=values[values.length-1]) {
				filterString+=",";
			}
		}
		filterString=") ";
		queryString +=filterString;
	}
	
	public void filterIN(List<Object> values) {
		String filterString = " (";
		for(Object value : values) {
			if(value instanceof String) {
				filterString += "'"+value+"'";
			}else if(value instanceof Long) {
				filterString += value.toString();
			}else if(value instanceof Integer) {
				filterString += value.toString();
			}
			if(value!=values.get(values.size()-1)) {
				filterString+=",";
			}
		}
		filterString+=") ";
		queryString +=filterString;
	}
	
	public String toString() {
		return queryString;
	}
	
	public void clear() {
		this.queryString="";
	}
	
	public QueryBuilder getNew() {
		clear();
		return this;
	}
	

}
























/*
 * public static String selectQuery(Class<?> c) { StackTraceElement[] values =
 * Thread.currentThread().getStackTrace(); for(StackTraceElement element :
 * values) { System.out.println("trace element -- "+element.getMethodName()); }
 * Method[] methods = c.getMethods();
 * 
 * for(Method m : methods) {
 * System.out.println("method element -- "+m.getName()); }
 * 
 * //System.out.println("thread name :"+name +"method name"+ mName); return
 * "---";
 * 	private final int deep = 2;
 * 
 * }
 */