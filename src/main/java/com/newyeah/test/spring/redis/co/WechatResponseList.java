package com.newyeah.test.spring.redis.co;

import java.util.List;

public class WechatResponseList<T> {
	
	private String alias ;
	private List<T> list ;
	
	public WechatResponseList(String alias,List<T> list){
		this.alias = alias ;
		this.list = list ;
	}

	public String getAlias() {
		return alias;
	}

	public List<T> getList() {
		return list;
	}
	
	
}