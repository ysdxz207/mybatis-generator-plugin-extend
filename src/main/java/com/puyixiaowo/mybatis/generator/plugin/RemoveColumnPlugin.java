package com.puyixiaowo.mybatis.generator.plugin;

import java.util.Iterator;
import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.util.StringUtility;
/**
 * remove field plugin
 * @author weishaoqiang
 * @date 2016-11-22
 */
public class RemoveColumnPlugin extends PluginAdapter{
	
	private static final String FIELD_NAME = "fieldName";
	private static final String GET_SUB= "get";
	private static final String SET_SUB= "set";
	
	private String fieldName = "";//the field to remove
	
	@Override
	public boolean validate(List<String> warning) {
		
		String fieldNameString = properties.getProperty(FIELD_NAME);
		if (!StringUtility.stringHasValue(fieldNameString)) {
			warning.add(String.format(FIELD_NAME, this.getClass().getSimpleName()));
		}
		fieldName = fieldNameString;
		
		return StringUtility.stringHasValue(fieldNameString);
	}
	
	@Override
	public boolean modelFieldGenerated(Field field,
			TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
			IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		removeField(field, topLevelClass);
		return true;
	}

	@Override
	public boolean modelGetterMethodGenerated(Method method,
			TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
			IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		removeMethod(method, topLevelClass);
		return true;
	}

	@Override
	public boolean modelSetterMethodGenerated(Method method,
			TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
			IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		removeMethod(method, topLevelClass);
		return true;
	}
	/**
	 * remove field
	 * @param field
	 * @param topLevelClass
	 */
	private void removeField(Field field, TopLevelClass topLevelClass) {
		Iterator<Field> it = topLevelClass.getFields().iterator();
		while (it.hasNext()) {
			Field f = (Field)it.next();
			if (f.getName().equals(fieldName)) {
				it.remove();
			}
		}
	}
	/**
	 * remove method of field
	 * @param field
	 * @param topLevelClass
	 */
	private void removeMethod(Method method, TopLevelClass topLevelClass) {
		Iterator<Method> it = topLevelClass.getMethods().iterator();
		while (it.hasNext()) {
			Method m = it.next();
			String fNameString = getMethodName(fieldName);
			String [] fNames = fNameString.split(",");
			for (String fName : fNames) {
				if (m.getName().equals(fName)) {
					it.remove();
				}
			}
		}
	}
	/**
	 * get method of field
	 * getId,setId
	 * @param fName
	 * @return
	 */
	private String getMethodName(String fName) {
		fName = fName.replace(fName.substring(0, 1), fName.substring(0, 1).toUpperCase());
		return GET_SUB + fName + "," + SET_SUB + fName;
	}
}
