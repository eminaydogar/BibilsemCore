package com.project.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.project.entity.PrizeDefinition;

public class PrizeCache {

	private static PrizeCache SINGLETON = null;
	private Map<Long, PrizeDefinition> prizeMap = null;

	public static PrizeCache getContext() {
		if (SINGLETON == null) {
			SINGLETON = new PrizeCache();
		}
		return SINGLETON;
	}

	private PrizeCache() {
		prizeMap = new HashMap<Long, PrizeDefinition>();
	}

	public PrizeDefinition getById(Long id) {
		return prizeMap.get(id);
	}

	public void remove(Long id) {
		prizeMap.remove(id);
	}

	public void remove(PrizeDefinition val) {
		prizeMap.remove(val.getId());
	}

	public void set(List<PrizeDefinition> qList) {
		qList.forEach(val -> prizeMap.put(val.getId(), val));
	}

	public void set(PrizeDefinition prize) {
		prizeMap.put(prize.getId(), prize);
	}

	public void clear() {
		prizeMap.clear();
	}

}
