package com.newyeah.test.spring.redis.handler;

import org.springframework.stereotype.Component;

import com.newyeah.test.spring.redis.co.WechatRequest;
import com.newyeah.test.spring.redis.utils.WechatResponseUtil;

/**
 * 接收普通消息 - 语音消息 - 处理器
 * @Author LiuYang
 * @Date 2016年8月25日
 * @Description : 
 *
 */
@Component("wechathandler_voice")
public class WechatVoiceHandler implements IWechatHandler {

	public String handle(WechatRequest weReq) {
		System.out.println("你的多媒体ID是："+weReq.getMediaId());
		return WechatResponseUtil.getTextRespXmlStr(weReq, "你的多媒体ID是："+weReq.getMediaId());
	}

}
