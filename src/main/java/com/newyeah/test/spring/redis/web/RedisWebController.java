package com.newyeah.test.spring.redis.web;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.newyeah.test.spring.redis.Application;
import com.newyeah.test.spring.redis.co.WechatRequest;
import com.newyeah.test.spring.redis.co.WechatRequestParams;
import com.newyeah.test.spring.redis.handler.IWechatHandler;
import com.newyeah.test.spring.redis.service.RedisWebService;
import com.newyeah.test.spring.redis.utils.WechatResponseUtil;


@Configuration
@RestController
@EnableAutoConfiguration
public class RedisWebController {
	
	@Autowired(required=false)
	private RedisWebService service ;
	
	@RequestMapping("/")
	ModelAndView go2Index(ModelAndView mv) {
		mv.setViewName("redirect:/index.html");
        return mv;
	}
	
	@RequestMapping("/wechat")
	void wechat(WechatRequestParams wechatRequestParams,HttpServletRequest request,OutputStream output) {
		String rspContent = "";
		String charset = request.getCharacterEncoding() ;
		WechatRequest weReq = new WechatRequest() ;
		try{
			weReq.setWechatRequestParams(wechatRequestParams);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance() ;
			DocumentBuilder docBuilder = factory.newDocumentBuilder() ;
			Document doc = docBuilder.parse(request.getInputStream()) ;
			Element root = doc.getDocumentElement() ;
			if(root.hasChildNodes()){
				NodeList nl = root.getChildNodes();
				for (int i = 0; i < nl.getLength(); i++) {
					Node node = nl.item(i);
					if (node instanceof Element) {
						Element ele = (Element) node;
						Method method = ReflectionUtils.findMethod(WechatRequest.class, "set"+ele.getTagName(),new Class[]{String.class}) ;
						if(method != null){
							method.invoke(weReq, ele.getTextContent()) ;
						}
					}
				}
			}
			IWechatHandler wHandler = (IWechatHandler)Application.ctx.getBean("wechathandler_"+weReq.getMsgType()) ;
			rspContent = wHandler.handle(weReq) ;
		}catch(NoSuchBeanDefinitionException ex){
			rspContent = WechatResponseUtil.getTextRespXmlStr(weReq, "您发送的消息类型我们正在开发中："+weReq.getMsgType()) ;
		}catch(Exception ex){
			rspContent = WechatResponseUtil.getTextRespXmlStr(weReq, "") ;
		}finally{
			try {
				output.write(rspContent.getBytes(charset));
				output.flush();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	@RequestMapping("/redirectByOpenId")
	String redirectByOpenId(String code,String state,HttpServletRequest request) {
		System.out.println(String.format("code=[%s],state=[%s]",code,state));
        return "redirectByOpenId";
	}
	
	
	@RequestMapping("/get")
	String get(@RequestParam(name="key",required=false) String key) {
        return service.get(key);
	}
	
	@RequestMapping("/set")
	String set(@RequestParam(name="key",required=false) String key,
			@RequestParam(name="val",required=false) String val) {
		service.set(key,val) ;
        return "ok";
	}
	
}
