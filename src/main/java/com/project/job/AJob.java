package com.project.job;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.cache.BBConstant.TIME_TYPE;
import com.project.utility.ObjectUtilty;

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
			if (!isShootDown() && getFlag().equalsIgnoreCase("Y") && isExecutable()) {
				LOGGER.info(getName() + " JOB start");
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
			executableDate = ObjectUtilty.createNextDate(TIME_TYPE.MINUTE, getDelay());
		} else {
			Date now = new Date();
			if (ObjectUtilty.compareDate_ddMMyyyyhhmm(now, executableDate)) {
				executableDate = ObjectUtilty.createNextDate(TIME_TYPE.MINUTE, getDelay());
				return true;
			}
		}
		return false;
	}

}
