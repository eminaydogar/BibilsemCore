package com.project.utility;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import com.project.annotation.Mandatory;
import com.project.cache.BBConstant.TIME_TYPE;
import com.project.exception.EntityValidationException;
import com.project.exception.RequirementFieldException;

public class ObjectUtilty {

	@SuppressWarnings("deprecation")
	public static boolean JSONValidation(Object o) throws RequirementFieldException {
		Class<?> c = o.getClass();
		Field[] fields = c.getDeclaredFields();
		for (Field field : fields) {
			Mandatory val = field.getAnnotation(Mandatory.class);
			if (val != null) {
				try {

					if (!field.isAccessible()) {
						field.setAccessible(true);
					}

					if (field.get(o) == null) {
						throw new RequirementFieldException("Request does not have enough values " + field.getName());
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
					return false;
				}
			}
		}

		return true;
	}

	public static <T> T forceSingleEntity(Set<T> entities) throws EntityValidationException {
		if (entities.size() != 1) {
			throw new EntityValidationException("An exception occurred in the request");
		}
		return entities.iterator().next();
	}

	public static Date createNextDate(int time, int increment) {
		Calendar calendar = Calendar.getInstance();
		switch (time) {
		case TIME_TYPE.SECOND:
			calendar.add(Calendar.SECOND, increment);
			break;
		case TIME_TYPE.MINUTE:
			calendar.add(Calendar.MINUTE, increment);
			break;
		case TIME_TYPE.HOUR:
			calendar.add(Calendar.HOUR, increment);
			break;
		default:
			break;
		}
		return calendar.getTime();
	}

}
