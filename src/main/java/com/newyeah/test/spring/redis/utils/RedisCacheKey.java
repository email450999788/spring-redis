package com.newyeah.test.spring.redis.utils;

public final class RedisCacheKey {
	
	/**
	 * 用户发送消息列表
	 */
	public final static String L_USER_MSG = "users:%s:msg" ;
	
	/**
	 * 
	 */
	public final static String L_USER_MSG_TYPE = "users:%s:msgtype" ;
	
	public static String getKey(String keyFormat,Object...args){
		return String.format(keyFormat, args) ;
	}
}
