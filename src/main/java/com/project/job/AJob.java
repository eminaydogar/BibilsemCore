package com.project.job;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.cache.BBConstant.ERROR_TYPE;
import com.project.cache.BBConstant.TIME_TYPE;
import com.project.utility.LoggerUtility;
import com.project.utility.ObjectUtilty;

@Transactional
@Service
public abstract class AJob implements Runnable {

	@Autowired
	protected JobDao dao;

	final static Logger LOGGER = LoggerFactory.getLogger(AJob.class);

	protected Long identifier;

	protected final int SECOND_TIME = 1000;

	protected JobBean jobBean;

	protected Date executableDate;

	protected void initilazeBean() {
		this.jobBean = dao.initilazer(identifier);
	}

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
			dao.getCoreManager()
					.saveOrUpdate(LoggerUtility.createLoggerSQL(getClass(), getName(), ERROR_TYPE.Job, e, null));
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
