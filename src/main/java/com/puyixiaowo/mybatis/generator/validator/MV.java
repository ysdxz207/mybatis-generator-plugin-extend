package com.puyixiaowo.mybatis.generator.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * @author weishaoqiang
 * @date 2016年11月28日
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD) //can use in field only.
public abstract @interface MV {
	
	String message() default "";
	
}