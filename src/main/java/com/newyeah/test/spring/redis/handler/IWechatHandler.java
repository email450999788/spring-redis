package com.newyeah.test.spring.redis.handler;

import com.newyeah.test.spring.redis.co.WechatRequest;

public interface IWechatHandler {
	
	public String handle(WechatRequest weReq) ;
	
}
