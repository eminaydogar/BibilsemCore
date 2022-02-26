package com.project.utility;

import java.util.Date;
import java.util.Map;

import com.project.cache.BBConstant;

public class HandleUtil {

	private static final String KEY_RESULT = "result";
	private static final String KEY_ERROR_MESSAGE = "errorMessage";
	private static final String KEY_RESULT_CODE = "resultCode";
	private static final String KEY_RESULT_TIME = "time";

	public static Map<String, Object> responseHandler(Map<String, Object> resultMap, String message, Throwable error) {
		loadMap(resultMap);
		resultMap.put(KEY_RESULT, message);
		if (error != null) {
			resultMap.put(KEY_ERROR_MESSAGE, error.getMessage());
			resultMap.put(KEY_RESULT_CODE, BBConstant.OperationStatus.ERROR);
		}
		return resultMap;
	}

	public static Map<String, Object> responseHandler(Map<String, Object> resultMap, String message) {
		return responseHandler(resultMap, message, null);
	}

	private static void loadMap(Map<String, Object> resultMap) {
		resultMap.put(KEY_RESULT, null);
		resultMap.put(KEY_ERROR_MESSAGE, null);
		resultMap.put(KEY_RESULT_CODE, BBConstant.OperationStatus.SUCCESS);
		resultMap.put(KEY_RESULT_TIME, ObjectUtilty.dateToString_ddMMyyyyhhmmss(new Date()));
	}

}
