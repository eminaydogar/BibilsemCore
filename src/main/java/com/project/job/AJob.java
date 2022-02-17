package com.project.job;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AJob implements Runnable {

	final static Logger LOGGER = LoggerFactory.getLogger(AJob.class);

	protected Long identifier;

	protected final int SECOND_TIME = 1000;

	protected JobBean jobBean;

	protected Date executableDate;

	protected abstract void initilazeBean();

	protected abstract void execute();
	

	protected Integer sleepTime() {
		return (int) (identifier * SECOND_TIME) / 2;
	}

	protected String getFlag() {
		return jobBean.getFlag();
	}

	protected String getName() {
		return jobBean.getName();
	}

	protected Integer getDelay() {
		return jobBean.getDelay();
	}

	protected boolean isShootDown() {
		return jobBean.isShootDown();
	}

	@Override
	public void run() {
		try {
			initilazeBean();
			System.out.println("---------------------------------------------");
			if (!isShootDown() && getFlag().equalsIgnoreCase("Y") && isExecutable()) {
				execute();
			}
		} catch (Exception e) {
			LOGGER.error(getName() + " JOB'ında hata : [" + e + "]");
		} finally {
			try {
				Thread.sleep(sleepTime());
			} catch (InterruptedException e) {
				LOGGER.error(getName() + " thread error : [" + e + "]");
			}
		}

	}

	protected boolean isExecutable() {
		if (executableDate == null) {
			executableDate = nextDate(getDelay());
			return true;
		} else {
			Date now = new Date();
			if (now.after(executableDate)) {
				executableDate = nextDate(getDelay());
				return true;
			}
		}
		return false;
	}

	private Date nextDate(Integer delay) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, delay);
		return calendar.getTime();
	}

}
