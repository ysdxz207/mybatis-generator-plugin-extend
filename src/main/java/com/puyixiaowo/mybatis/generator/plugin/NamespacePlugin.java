package com.puyixiaowo.mybatis.generator.plugin;

import java.util.List;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.internal.util.StringUtility;
/**
 * user mapper xml namespace plugin
 * @author weishaoqiang
 * @date 2016-11-23
 */
public class NamespacePlugin extends PluginAdapter{
	
	private static final String PACKAGE_NAME = "packageName";
	private static final String CLASS_NAME_APPEND = "classNameAppend";
	private static final String MINUS_PREFIX = "minusPrefix";
	
	private String packageName = "";
	private String classNameAppend = "Mapper";
	private int minusPrefix = 0;
	
	@Override
	public boolean validate(List<String> warning) {
		//package name
		String packageNameString = properties.getProperty(PACKAGE_NAME);
		if (!StringUtility.stringHasValue(packageNameString)) {
			warning.add(String.format(PACKAGE_NAME, this.getClass().getSimpleName()));
		}
		packageName = packageNameString;
		//class name append
		String classNameAppendString = properties.getProperty(CLASS_NAME_APPEND);
		if (StringUtility.stringHasValue(classNameAppendString)) {
			classNameAppend = classNameAppendString;
		}
		//minus prefix
		String minusPrefixString = properties.getProperty(MINUS_PREFIX);
		if (StringUtility.stringHasValue(minusPrefixString)) {
			minusPrefix = Integer.valueOf(minusPrefixString);
		}
		return StringUtility.stringHasValue(packageNameString);
	}

	@Override
	public void initialized(IntrospectedTable introspectedTable) {
		super.initialized(introspectedTable);
		
		
		setMapperNamespace(introspectedTable);
	}

	/**
	 * set mapper namespace
	 * @param introspectedTable
	 */
	public void setMapperNamespace(IntrospectedTable introspectedTable){
		introspectedTable.setMyBatis3JavaMapperType(packageName + "." + getMapperNamespace(introspectedTable));
	}
	/**
	 * get mapper namespace
	 * @param introspectedTable
	 * @return
	 */
	public String getMapperNamespace(IntrospectedTable introspectedTable){
		String entityFullName = introspectedTable.getBaseRecordType();
		String entityName = entityFullName.substring(entityFullName.lastIndexOf(".") + 1);
		
		return entityName.substring(minusPrefix) + classNameAppend;
	}
}
