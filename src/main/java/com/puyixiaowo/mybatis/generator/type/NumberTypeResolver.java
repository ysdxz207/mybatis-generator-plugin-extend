package com.puyixiaowo.mybatis.generator.type;

import java.math.BigDecimal;
import java.sql.Types;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

public class NumberTypeResolver extends JavaTypeResolverDefaultImpl {

	@Override
	public FullyQualifiedJavaType calculateJavaType(
			IntrospectedColumn introspectedColumn) {
		FullyQualifiedJavaType answer = null;
		switch (introspectedColumn.getJdbcType()) {
		case Types.INTEGER:
			if (introspectedColumn.getLength() > 24 || forceBigDecimals) {
				answer = new FullyQualifiedJavaType(BigDecimal.class.getName());
			} else if (introspectedColumn.getLength() >= 10) {
				answer = new FullyQualifiedJavaType(Long.class.getName());
			} else if (introspectedColumn.getLength() >= 4) {
				answer = new FullyQualifiedJavaType(Integer.class.getName());
			} else if (introspectedColumn.getLength() == 1) {
				answer = new FullyQualifiedJavaType(Integer.class.getName());
			} else {
				answer = new FullyQualifiedJavaType(Short.class.getName());
			}
			break;
		case Types.TINYINT:
			answer = new FullyQualifiedJavaType(Integer.class.getName());
			break;
		case Types.BIT:
				answer = new FullyQualifiedJavaType(Integer.class.getName());
			break;

		default:
			answer = null;
			break;
		}
		if (answer == null) {
			answer = super.calculateJavaType(introspectedColumn);
		}
		return answer;
	}

	@Override
	public String calculateJdbcTypeName(IntrospectedColumn introspectedColumn) {
		String answer = null;
		switch (introspectedColumn.getJdbcType()) {
		case Types.BIT:
				answer = "INTEGER";
			break;
		default:
			
		}
		if (answer == null ) {
			answer = super.calculateJdbcTypeName(introspectedColumn);
		}
		return answer;
	}
	
}
