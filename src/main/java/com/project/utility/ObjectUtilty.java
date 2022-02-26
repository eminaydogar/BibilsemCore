package com.project.utility;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.project.cache.BBConstant.TIME_TYPE;
import com.project.common.annotation.Mandatory;
import com.project.exception.EntityValidationException;
import com.project.exception.RequirementFieldException;

public class ObjectUtilty {

	private static final SimpleDateFormat DF_withNotSecondAndMillisecond = new SimpleDateFormat("ddMMyyyyhhmm");
	private static final SimpleDateFormat DF_ddMMyyyyhhmmss = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");

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
						throw new RequirementFieldException("Mandatory values ​​are not entered ");
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

	public static boolean isEmpty(Object object) {
		if (object == null) {
			return true;
		}
		if (object instanceof String) {
			String strObj = object.toString();
			return strObj.trim().length() == 0;
		} else if (object instanceof List<?>) {
			List<?> listObj = (List<?>) object;
			return listObj.size() == 0;
		} else if (object instanceof Map<?, ?>) {
			Map<?, ?> mapObj = (Map<?, ?>) object;
			return mapObj.size() == 0;
		}
		return false;
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

	public static boolean compareDate_ddMMyyyyhhmm(Date date1, Date date2) {
		String d1 = DF_withNotSecondAndMillisecond.format(date1);
		String d2 = DF_withNotSecondAndMillisecond.format(date2);
		if (d1.equals(d2)) {
			return true;
		}
		return false;
	}

	public static String dateToString_ddMMyyyyhhmmss(Date date) {
		String formatDate = null;
		if (date != null) {
			formatDate = DF_ddMMyyyyhhmmss.format(date);
		}
		return formatDate;
	}

}
