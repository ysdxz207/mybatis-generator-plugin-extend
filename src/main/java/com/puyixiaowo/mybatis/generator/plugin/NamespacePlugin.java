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
	
	private String packageName = "";
	private String classNameAppend = "DaoImpl";
	
	@Override
	public boolean validate(List<String> warning) {
		String packageNameString = properties.getProperty(PACKAGE_NAME);
		if (!StringUtility.stringHasValue(packageNameString)) {
			warning.add(String.format(PACKAGE_NAME, this.getClass().getSimpleName()));
		}
		packageName = packageNameString;
		
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
		String replaceStrings [] = {"com.edu.shop.entity.Edu", "com.edu.member.entity.Edu"};
		for (String replaceStr : replaceStrings) {
			if (introspectedTable.getBaseRecordType().indexOf(replaceStr) != -1){
				return introspectedTable.getBaseRecordType().replace(replaceStr, "") + classNameAppend;
			}
		}
		return "/";
	}
}
