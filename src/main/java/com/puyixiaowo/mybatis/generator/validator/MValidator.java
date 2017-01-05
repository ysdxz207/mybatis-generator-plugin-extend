package com.puyixiaowo.mybatis.generator.validator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.puyixiaowo.mybatis.generator.validator.test.User;
/**
 * auto validate based on mybatis-generator-plugin-extend
 * @author weishaoqiang
 * @date 2016年11月28日
 */
public class MValidator {

	private static Object obj;
	private static List<String> errorList = new ArrayList<String>();

	public static void main(String[] args) {
		User user = new User();
		user.setName("张三");
		obj = user;
		validate(user, User.class);
	}
	
	public static void validate(Object object, Class clazz) {
		if (object == null) {
			throw new RuntimeException("entity  is null");
		}
		obj = object;
		readFieldAnnotation(clazz.getDeclaredFields());
		if (errorList.size() > 0) {
			throw new IllegalArgumentException(errorList.get(0));
		}

	}
	/**
	 * read field annotation
	 * @param fields
	 */
	private static void readFieldAnnotation(Field[] fields) {
		try {
			for (Field field : fields) {
				if (field.isAnnotationPresent(MV.class)) {
					// getAnnotation returns Annotation type
					Annotation singleAnnotation = field.getAnnotation(MV.class);
					MV mv = (MV) singleAnnotation;
					doValidate(field, mv);
				}

			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	/**
	 * do validate and calculate errors
	 * @param field
	 * @param mv
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	private static void doValidate(Field field, MV mv)
			throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		String fieldType = field.getType().getSimpleName();
		String fieldName = field.getName();
		String message = mv.message();
		field.setAccessible(true);//
		Object value = field.get(obj);

		switch (fieldType) {
		case "String":
			if (value == null || StringUtils.isBlank((String) value)) {
				addError(message, fieldName);
			}
			break;
		case "Long":
			if (value == null) {
				addError(message, fieldName);
			}
			break;
		case "Integer":
			if (value == null) {
				addError(message, fieldName);
			}
			break;
		case "Boolean":
			if (value == null) {
				addError(message, fieldName);
			}
			break;
		default:
			break;
		}

	}
	/**
	 * add error to errorList
	 * @param message
	 * @param fieldName
	 */
	private static void addError(String message, String fieldName) {
		errorList.add("需要" + message + "[" + fieldName + "]");
	}
}
