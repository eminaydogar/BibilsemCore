package com.project.job;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.job.scheduledTask.CacheRefreshJob;
import com.project.job.scheduledTask.CouponValidatorJob;
import com.project.job.scheduledTask.TestJob;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BaseTaskDefinitions {

	/*
	 * ############################### jobs created should be defined here
	 * #################################
	 * 
	 */
	@Autowired
	@Setter
	private CouponValidatorJob couponValidatorJob;
	@Autowired
	@Setter
	private TestJob testJob;
	@Autowired
	@Setter
	private CacheRefreshJob cacheRefreshJob;

	/*
	 * #############################################################################
	 * ########################
	 *
	 */

	private boolean isReflected = false;

	@Getter
	private List<AJob> task1Min = new ArrayList<AJob>();
	@Getter
	private List<AJob> task30Min = new ArrayList<AJob>();
	@Getter
	private List<AJob> task60Min = new ArrayList<AJob>();

	public BaseTaskDefinitions getAllTask() throws IllegalArgumentException, IllegalAccessException {
		if (!isReflected) {
			log.info("Task Definitions will add list !");

			for (AJob job : reflectTasks()) {

				job.initilazeBean();

				if (job.getDelay() <= 30) {
					task1Min.add(job);

				} else if (job.getDelay() > 30 && job.getDelay() < 60) {
					task30Min.add(job);

				} else {
					task60Min.add(job);

				}
			}
		}

		return this;
	}

	private List<AJob> reflectTasks() throws IllegalAccessException {
		List<AJob> list = new ArrayList<AJob>();
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (field.getAnnotation(Autowired.class) != null) {
				AJob ref = (AJob) field.get(this);
				list.add(ref);
			}
		}
		isReflected = true;
		return list;
	}

}
