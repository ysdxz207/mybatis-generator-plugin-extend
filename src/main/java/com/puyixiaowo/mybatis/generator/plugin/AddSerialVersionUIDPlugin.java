package com.puyixiaowo.mybatis.generator.plugin;

import java.util.List;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * add serial version UID without extend interface plugin
 * 
 * @author weishaoqiang
 * @date 2016-11-23
 */
public class AddSerialVersionUIDPlugin extends PluginAdapter {

	private static final String SERIAL_VERSION_UID = "serialVersionUID";

	@Override
	public boolean validate(List<String> warning) {

		return true;
	}
	
	@Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {
		addSerialVersionUID(topLevelClass, introspectedTable);
        return true;
    }
	@Override
	public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		addSerialVersionUID(topLevelClass, introspectedTable);
		return true;
	}

	/**
	 * add serial version UID
	 * 
	 * @param topLevelClass
	 * @param introspectedTable
	 */
	protected void addSerialVersionUID(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {

		Field field = new Field();
		field.setFinal(true);
		field.setInitializationString("1L");
		field.setName(SERIAL_VERSION_UID);
		field.setStatic(true);
		field.setType(new FullyQualifiedJavaType("long"));
		field.setVisibility(JavaVisibility.PRIVATE);
		context.getCommentGenerator().addFieldComment(field, introspectedTable);

		topLevelClass.addField(field);
	}
}
