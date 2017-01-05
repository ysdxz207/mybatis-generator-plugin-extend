package com.puyixiaowo.mybatis.generator.plugin;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.internal.util.StringUtility;

public class GenerateDaoPlugin extends PluginAdapter {

	private static final String REPLACE_START = "replaceStart";
	private static final String REPLACE_END = "replaceEnd";
	private static final String REPLACE_STR = "replaceStr";
	private static final String END_SUFFIX = "endSuffix";

	private String entityName = "";
	private String daoFileName = "";
	private File daoFile;
	private File daoImplFile;
	private String endSuffix = "";

	private int replaceStart = 0;
	private int replaceEnd = 0;
	private String replaceStr = "";

	@Override
	public boolean validate(List<String> warnings) {
		String replaceStartString = properties.getProperty(REPLACE_START);
		if (!StringUtility.stringHasValue(replaceStartString)) {
			warnings.add(String.format(REPLACE_START, this.getClass()
					.getSimpleName()));
		}
		replaceStart = Integer.parseInt(replaceStartString);
		/****/
		String replaceEndString = properties.getProperty(REPLACE_END);
		if (!StringUtility.stringHasValue(replaceEndString)) {
			warnings.add(String.format(REPLACE_END, this.getClass()
					.getSimpleName()));
		}
		replaceEnd = Integer.parseInt(replaceEndString);
		/****/
		String replaceStrString = properties.getProperty(REPLACE_STR);
		if (replaceStrString == null) {
			warnings.add(String.format(REPLACE_STR, this.getClass()
					.getSimpleName()));
		}
		replaceStr = replaceStrString;
		/****/
		String endSuffixString = properties.getProperty(END_SUFFIX);
		if (!StringUtility.stringHasValue(endSuffixString)) {
			warnings.add(String.format(END_SUFFIX, this.getClass()
					.getSimpleName()));
		}
		endSuffix = endSuffixString;
		return StringUtility.stringHasValue(replaceStartString)
				&& StringUtility.stringHasValue(replaceEndString)
				&& replaceStrString != null
				&& StringUtility.stringHasValue(endSuffixString);
	}

	@Override
	public boolean clientGenerated(Interface interfaze,
			TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		interfaze.addSuperInterface(new FullyQualifiedJavaType("BaseDao<"
				+ entityName + ">"));
		interfaze.addImportedType(new FullyQualifiedJavaType(
				"com.edu.dao.impl.BaseDao"));

		List<Method> list = interfaze.getMethods();
		list.removeAll(list);

		return true;
	}

	@Override
	public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles() {
		createFile();
		return super.contextGenerateAdditionalJavaFiles();
	}

	@Override
	public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		this.entityName = topLevelClass.getType().getShortName();
		getDaoFileName(entityName);
		return true;
	}

	public void createFile() {
		try {
			System.out.println("=================" + daoFile.getAbsolutePath()
					+ "===" + daoFile.getName());
			daoFile.createNewFile();
			daoImplFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getDaoFileName(String entityName) {
		this.entityName = entityName.replace(
				entityName.substring(replaceStart, replaceEnd), replaceStr);
		this.daoFileName = this.entityName + this.endSuffix;
		getFiles();
	}

	public void getFiles() {
		Context context = getContext();
		String projectPath = context.getJavaClientGeneratorConfiguration()
				.getTargetProject();
		String packageName = context.getJavaClientGeneratorConfiguration()
				.getTargetPackage();
		String path = projectPath + "/" + packageName.replaceAll("\\.", "/");
		String daoImplFilePath = path + "/impl/" + this.daoFileName
				+ "Impl.java";
		String daoFilePath = path + "/" + this.daoFileName + ".java";
		daoImplFile = new File(daoImplFilePath);
		daoFile = new File(daoFilePath);
	}
}
