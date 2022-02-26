package com.project.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicCache {

	private static DynamicCache instance = null;

	private static List<Map<String, List<Object>>> dynamicList = new ArrayList<>();

	private DynamicCache() {
	}

	public static DynamicCache getInstance() {
		if (instance == null) {
			instance = new DynamicCache();
		}
		return instance;
	}

	public DynamicCache createNewObjectCache(String type) {

		if (!isContains(type)) {
			Map<String, List<Object>> newInstanceMap = new HashMap<String, List<Object>>();
			dynamicList.add(newInstanceMap);
		}

		return instance;
	}
	
	public void set(String type,Object obj) {
		for (int i = 0; i < dynamicList.size(); i++) {
			Map<String, List<Object>> value = dynamicList.get(i);
			if (value.get(type) != null) {
				dynamicList.get(i).get(type).add(obj);
				break;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> get(String type) {
		for (int i = 0; i < dynamicList.size(); i++) {
			Map<String, List<Object>> value = dynamicList.get(i);
			if (value.get(type) != null) {
				return (List<T>) dynamicList.get(i).get(type);
		
			}
		}
		return null;
	}
	
	/*private boolean isContains(Object object) {
		for (int i = 0; i < dynamicList.size(); i++) {
			Map<String, List<Object>> map = dynamicList.get(i);
			
			for(String key : map.keySet()) {
				List<Object> list = map.get(key);
				if(list!=null && list.size()>0) {
					Object obj = list.get(0);
					if(object.getClass()==obj.getClass()) {
						return true;
					}
				}
			}
		}

		return false;

	} */

	private boolean isContains(String type) {

		for (int i = 0; i < dynamicList.size(); i++) {
			Map<String, List<Object>> value = dynamicList.get(i);
			if (value.get(type) != null) {
				return true;
			}
		}

		return false;
	}
}
