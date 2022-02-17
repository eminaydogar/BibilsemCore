package com.project.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.project.entity.QuestionDefinition;

public class QuestionCache {

	private static QuestionCache SINGLETON = null;
	private Map<Long, QuestionDefinition> questionMap = null;

	public static QuestionCache getContext() {
		if (SINGLETON == null) {
			SINGLETON = new QuestionCache();
		}
		return SINGLETON;
	}

	private QuestionCache() {
		questionMap = new HashMap<Long, QuestionDefinition>();
	}

	public QuestionDefinition getById(Long id) {
		return questionMap.get(id);
	}

	public void remove(QuestionDefinition val) {
		questionMap.remove(val.getId());
	}
	
	public void remove(Long val) {
		questionMap.remove(val);
	}

	public void set(List<QuestionDefinition> qList) {
		qList.forEach(val -> {
			if (val.getStatus().equalsIgnoreCase("Y")) {
				questionMap.put(val.getId(), val);
			}
		});
	}

	public void set(QuestionDefinition question) {
		questionMap.put(question.getId(), question);
	}

	public void clear() {
		questionMap.clear();
	}

}
