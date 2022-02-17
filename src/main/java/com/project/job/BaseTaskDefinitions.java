package com.project.job;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.project.job.scheduledTask.CouponValidatorJob;
import com.project.job.scheduledTask.TestJob;

@Component
public class BaseTaskDefinitions {

	/*############################### jobs created should be defined here #################################*/
	@Autowired
	private CouponValidatorJob couponValidatorJob;
	@Autowired
	private TestJob testJob;
	
	
	/*##################################################################################################### */

	private List<AJob> tasks;

	@Bean(name = "allTaskBean")
	public List<AJob> getAllTask() throws IllegalArgumentException, IllegalAccessException {
		if (tasks != null) {
			return tasks;
		}
		tasks = reflectTasks();
		return tasks;
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
		return list;
	}

}
