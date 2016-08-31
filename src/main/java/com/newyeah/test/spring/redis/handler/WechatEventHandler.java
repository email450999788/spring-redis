package com.newyeah.test.spring.redis.handler;

import org.springframework.stereotype.Component;

import com.newyeah.test.spring.redis.co.WechatRequest;
import com.newyeah.test.spring.redis.utils.WechatResponseUtil;

/**
 * 接收事件推送处理器
 * @Author LiuYang
 * @Date 2016年8月25日
 * @Description : 
 *
 */
@Component("wechathandler_event")
public class WechatEventHandler implements IWechatHandler {
	
	/**
	 * 关注
	 */
	public static final String EVENT_SUBSCRIBE = "subscribe" ;
	/**
	 * 取消关注 
	 */
	public static final String EVENT_UNSUBSCRIBE = "unsubscribe" ;
	/**
	 * 上报地理位置 
	 */
	public static final String EVENT_LOCATION = "LOCATION" ;

	public String handle(WechatRequest weReq) {
		String content = "" ;
		if(weReq.getEvent().equals(EVENT_SUBSCRIBE)) {
			content = "[愉快]欢迎您关注我，我会让您的生活更加丰富多彩的！" ;
		}else if(weReq.getEvent().equals(EVENT_LOCATION)) {
			content = String.format("您当前的坐标位置为:[x=%s,y=%s]",weReq.getLocation_X(),weReq.getLocation_Y()) ;
		}
		return WechatResponseUtil.getTextRespXmlStr(weReq, content);
	}

}
