package com.newyeah.test.spring.redis.utils;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.newyeah.test.spring.redis.co.WechatNewArticles;
import com.newyeah.test.spring.redis.co.WechatRequest;
import com.newyeah.test.spring.redis.co.WechatResponseList;

public class WechatResponseUtil {
	
	@SuppressWarnings("unchecked")
	public static String getNodeXml(String key,Object value){
		
		StringBuffer str = new StringBuffer() ;
		
		//基本类型
		if(value.getClass().isPrimitive()){
			
			str.append(String.format("<%1$s><![CDATA[%2$s]]></%1$s>", key,value)) ;
			
		}else if(value instanceof Map){
			
			Map<String,Object> valueMap = (Map<String,Object>)value ;
			str.append(String.format("<%s>", key)) ;
			for(String valueMap_key : valueMap.keySet()) {
				Object valueMap_value = valueMap.get(valueMap_key) ;
				str.append(getNodeXml(valueMap_key,valueMap_value)) ;
			}
			str.append(String.format("</%s>", key)) ;
			
			
		}else if(value instanceof WechatResponseList){
			WechatResponseList<?> valueList = (WechatResponseList<?>)value ;
			str.append(String.format("<%s>", key)) ;
			for(Object valueListItem : valueList.getList()) {
				str.append(getNodeXml(valueList.getAlias(),valueListItem)) ;
			}
			str.append(String.format("</%s>", key)) ;
			
		}else{
			str.append(String.format("<%1$s>%2$s</%1$s>", key,value)) ;
		}
		return str.toString();
	}
	
	private static String getRespXmlStr(Map<String,Object> msg) {
		StringBuffer xmlStr = new StringBuffer() ;
		xmlStr.append("<xml>")  ;
		if(msg!=null && !msg.isEmpty()) {
			for(String key : msg.keySet()) {
				Object value = msg.get(key) ;
				xmlStr.append(getNodeXml(key,value)) ;
			}
		}
		xmlStr.append("</xml>") ;
		System.out.println(xmlStr);
		return xmlStr.toString() ;
	}
	
	/**
	 * 得到文本回复XML信息
	 * @param weReq
	 * @param content
	 * @return
	 */
	public static String getTextRespXmlStr(WechatRequest weReq,String content) {
		Map<String,Object> request = new LinkedHashMap<String,Object>() ;
		Collections.synchronizedMap(request) ;
		request.put("ToUserName", weReq.getFromUserName()) ;
		request.put("FromUserName", weReq.getToUserName()) ;
		request.put("CreateTime", System.currentTimeMillis()) ;
		request.put("MsgType", "text") ;
		request.put("Content", content) ;
		return getRespXmlStr(request) ;
	}
	
	/**
	 * 得到图文XML信息
	 * @param weReq
	 * @param content
	 * @return
	 */
	public static String getNewsRespXmlStr(WechatRequest weReq, List<WechatNewArticles> articles) {
		if(articles==null || articles.isEmpty()){
			return getTextRespXmlStr(weReq,"") ;
		}
		Map<String,Object> request = new LinkedHashMap<String,Object>() ;
		Collections.synchronizedMap(request) ;
		request.put("ToUserName", weReq.getFromUserName()) ;
		request.put("FromUserName", weReq.getToUserName()) ;
		request.put("CreateTime", System.currentTimeMillis()) ;
		request.put("MsgType", "news") ;
		request.put("ArticleCount", articles.size()) ;
		request.put("Articles", new WechatResponseList<WechatNewArticles>("item", articles)) ;
		Map<String,Object> TestMap = new LinkedHashMap<String,Object>() ;
		TestMap.put("a", "a") ;
		TestMap.put("b", "b") ;
		request.put("TestMap", TestMap) ;
		return getRespXmlStr(request) ;
	}
	
}
