package com.project.utility;

// sadece ic sorgularda kullanılabilir. Dış input datası için sql injection problemi içerebilir. 
public class QueryBuilder {

	private String queryString = "";
	int pointer = 1;

	public void append(String query) {
		queryString += query;
	}

	public QueryBuilder() {
	};

	public QueryBuilder filter(String condition, Object value) {
		queryString += " AND " + condition + " = ";
		if (value instanceof String) {
			queryString += "'" + value + "'";
		} else if (value instanceof Long) {
			queryString += value.toString();
		} else if (value instanceof Integer) {
			queryString += value.toString();
		}

		return this;
	}

	public QueryBuilder setParam(Object param) {

		char[] queryArray = queryString.toCharArray();
		for (int i = 0; i < queryArray.length; i++) {
			if (queryArray[i] == '?') {
				String pre = queryString.substring(0, i);
				pre = setValue(pre, param);
				String suf = queryString.substring(i + 1, queryString.length());
				queryString = pre + suf;
				break;
			}
		}
		return this;
	}

	public QueryBuilder setParams(Object... params) {

		char[] queryArray = queryString.toCharArray();
		for (int i = 0; i < queryArray.length; i++) {
			if (queryArray[i] == '?') {
				String pre = queryString.substring(0, i - 1);
				pre += "IN (";
				for (int j = 0; j < params.length; j++) {
					pre = setValue(pre, params[j]);
					if (j == params.length - 1) {
						pre = ")";
					} else {
						pre = ",";
					}
				}
				String suf = queryString.substring(i + 1, queryString.length());
				queryString = pre + suf;
				break;
			}
		}
		return this;
	}

	public String toString() {
		return queryString;
	}

	public void clear() {
		this.queryString = "";
	}

	public QueryBuilder newInstance() {
		clear();
		return this;
	}

	private String setValue(String filterString, Object value) {
		if (value == null) {
			filterString += "NULL";
		} else if (value instanceof String) {
			filterString += "'" + value + "'";
		} else if (value instanceof Long) {
			filterString += value.toString();
		} else if (value instanceof Integer) {
			filterString += value.toString();
		}
		return filterString;
	}

}
