package com.project.job;

import java.math.BigInteger;

import lombok.Data;

@Data
public class JobBean {

	private final int COLUMN_ID = 0;
	private final int COLUMN_NAME = 1;
	private final int COLUMN_FLAG = 2;
	private final int COLUMN_DELAY = 3;

	private Long id;
	private String name;
	private String flag;
	private Integer delay;
	private boolean isShootDown;

	public JobBean(Object[] rows) {
		id = ((BigInteger) rows[COLUMN_ID]).longValue();
		name = rows[COLUMN_NAME].toString();
		flag = rows[COLUMN_FLAG].toString();
		delay = ((BigInteger) rows[COLUMN_DELAY]).intValue();
	}

	public JobBean() {

	}

}
