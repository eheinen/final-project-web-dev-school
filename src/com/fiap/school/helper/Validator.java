package com.fiap.school.helper;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Validator {

	public static boolean hasRequiredField(String field) {
		return hasRequiredField(Arrays.asList(field));
	}

	public static boolean hasRequiredField(List<Object> fields) {
		if (fields == null)
			return true;

		for (Object field : fields) {
			if (field instanceof String) {
				if (field == null || field.toString().isEmpty()) {
					return true;
				}
			} else if (field instanceof Double || field instanceof Float || field instanceof Integer) {
				if (Double.parseDouble(field.toString()) == 0) {
					return true;
				}
			} else if (field instanceof Date) {
				if (field == null || field.toString().isEmpty()) {
					return true;
				}
			} else {
				if (field == null) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isGreaterOrLesserThan(double field, double minNumber, double maxNumber) {
		return (field > minNumber && field < maxNumber);
	}

}
