package com.project.utility;

public class LoggerUtility {

	private final static String LOG_INSERT_SQL = "INSERT INTO LOG_DEFINITION(CLASS_NAME,METHOD_NAME,ERROR_TYPE,ERROR_MESSAGE,MESSAGE,CDATE) VALUES(?,?,?,?,?,NOW())";

	public static String createLoggerSQL(Class<?> clazz, String methodName, String errorType, Exception exception,
			String message) {
		QueryBuilder queryBuilder = new QueryBuilder();
		queryBuilder.append(LOG_INSERT_SQL);

		if (clazz != null) {
			queryBuilder.setParam(clazz.getSimpleName());
		} else {
			queryBuilder.setParam(null);
		}

		if (methodName != null) {
			queryBuilder.setParam(methodName);
		} else {
			queryBuilder.setParam(null);
		}

		if (errorType != null) {
			queryBuilder.setParam(errorType);
		} else {
			queryBuilder.setParam(null);
		}

		if (exception != null) {
			queryBuilder.setParam(exception.getMessage());
		} else {
			queryBuilder.setParam(null);
		}

		if (message != null) {
			queryBuilder.setParam(message);
		} else {
			queryBuilder.setParam(null);
		}

		return queryBuilder.toString();
	}

	public static String createLoggerSQL(String message) {
		return createLoggerSQL(null, null, null, null, message);
	}

	public static String createLoggerSQL(Class<?> clazz, String message) {
		return createLoggerSQL(clazz, null, null, null, message);
	}

	public static String createLoggerSQL(Class<?> clazz, String methodName, String message) {
		return createLoggerSQL(clazz, methodName, null, null, message);
	}

	public static String createLoggerSQL(Class<?> clazz, Exception exception) {
		return createLoggerSQL(clazz, null, null, exception, null);
	}

	public static String createLoggerSQL(Class<?> clazz, String methodName, Exception exception) {
		return createLoggerSQL(clazz, methodName, null, exception, null);
	}

	public static String createLoggerSQL(Class<?> clazz, Exception exception,String errorType) {
		return createLoggerSQL(clazz, null, errorType, exception, null);
	}

	public static void main(String[] args) {
		System.out.println(LoggerUtility.createLoggerSQL(LoggerUtility.class, "test", new Exception("lol")));
	}

}
