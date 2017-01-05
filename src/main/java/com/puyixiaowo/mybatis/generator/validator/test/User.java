package com.puyixiaowo.mybatis.generator.validator.test;

import com.puyixiaowo.mybatis.generator.validator.MV;

public class User {
	@MV(message="用户姓名")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
