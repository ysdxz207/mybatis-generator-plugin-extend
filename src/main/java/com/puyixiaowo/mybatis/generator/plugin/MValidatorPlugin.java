package com.puyixiaowo.mybatis.generator.plugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;

public class MValidatorPlugin extends PluginAdapter {
	
	private Map<String, String> remarksMap = new HashMap<String, String>();
	

	@Override
	public boolean validate(List<String> warnings) {
		
		return true;
	}

	@Override
	public boolean modelFieldGenerated(Field field,
			TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
			IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		
		remarksMap.put(introspectedColumn.getActualColumnName(), introspectedColumn.getRemarks());
		field.addAnnotation("@MV");
		
		return true;
	}
	
	
	
}
