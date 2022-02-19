package com.project.utility;

import java.util.HashMap;
import java.util.Map;

public class HandleUtil {

	private static final String KEY_RESULT = "result";
	private static final String KEY_ERROR_MESSAGE = "errorMessage";

	public static Map<String, String> responseHandler(String message, Throwable error) {
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put(KEY_RESULT, message);
		if (error != null) {
			resultMap.put(KEY_ERROR_MESSAGE, error.getMessage());
		}
		return resultMap;
	}

	public static Map<String, String> responseHandler(String message) {
		return responseHandler(message, null);
	}

}
