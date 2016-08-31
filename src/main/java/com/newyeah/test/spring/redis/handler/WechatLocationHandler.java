package com.newyeah.test.spring.redis.handler;

import org.springframework.stereotype.Component;

import com.newyeah.test.spring.redis.co.WechatRequest;
import com.newyeah.test.spring.redis.utils.WechatResponseUtil;

/**
 * 接收普通消息 - 地理位置消息 - 处理器
 * @Author LiuYang
 * @Date 2016年8月25日
 * @Description : 
 *
 */
@Component("wechathandler_location")
public class WechatLocationHandler implements IWechatHandler {
	

	public String handle(WechatRequest weReq) {
		String content = String.format("您当前的坐标位置为：[%s]\n坐标点为:[x=%s,y=%s]",weReq.getLabel(),weReq.getLocation_X(),weReq.getLocation_Y()) ;
		return WechatResponseUtil.getTextRespXmlStr(weReq, content);
	}

}
