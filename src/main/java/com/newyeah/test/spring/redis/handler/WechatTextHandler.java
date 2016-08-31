package com.newyeah.test.spring.redis.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.stereotype.Component;

import com.newyeah.test.spring.redis.co.WechatNewArticles;
import com.newyeah.test.spring.redis.co.WechatRequest;
import com.newyeah.test.spring.redis.service.RedisWebService;
import com.newyeah.test.spring.redis.utils.RedisCacheKey;
import com.newyeah.test.spring.redis.utils.WechatResponseUtil;

/**
 * 接收普通消息 - 文字消息 - 处理器
 * @Author LiuYang
 * @Date 2016年8月25日
 * @Description : 
 *
 */
@Component("wechathandler_text")
public class WechatTextHandler implements IWechatHandler {
	
	@Autowired(required=false)
	private RedisWebService service ;

	public String handle(WechatRequest weReq) {
		
		if(weReq.getContent().equals("美女图片")) {
			
			List<WechatNewArticles> articles = new ArrayList<WechatNewArticles>(2) ;
			
			WechatNewArticles article1 = new WechatNewArticles();
			article1.setTitle("美女图片（一）");
			article1.setDescription("这是从百度搜集到的美女图片（一）");
			article1.setPicUrl("http://b.hiphotos.baidu.com/image/pic/item/f703738da9773912825f6388fc198618377ae2da.jpg");
			article1.setUrl("http://b.hiphotos.baidu.com/image/pic/item/f703738da9773912825f6388fc198618377ae2da.jpg");
			articles.add(article1) ;
			
			WechatNewArticles article2 = new WechatNewArticles();
			article2.setTitle("美女图片（二）");
			article2.setDescription("这是从百度搜集到的美女图片（二）");
			article2.setPicUrl("http://a.hiphotos.baidu.com/image/pic/item/43a7d933c895d1438d0b16fc77f082025baf07eb.jpg");
			article2.setUrl("http://a.hiphotos.baidu.com/image/pic/item/43a7d933c895d1438d0b16fc77f082025baf07eb.jpg");
			articles.add(article2) ;
			
			WechatNewArticles article3 = new WechatNewArticles();
			article3.setTitle("美女图片（三）");
			article3.setDescription("这是从百度搜集到的美女图片（三）");
			article3.setPicUrl("http://g.hiphotos.baidu.com/image/pic/item/bd315c6034a85edf4b3018ca4d540923dc54759f.jpg");
			article3.setUrl("http://g.hiphotos.baidu.com/image/pic/item/bd315c6034a85edf4b3018ca4d540923dc54759f.jpg");
			articles.add(article3) ;
			
			WechatNewArticles article4 = new WechatNewArticles();
			article4.setTitle("美女图片（四）");
			article4.setDescription("这是从百度搜集到的美女图片（四）");
			article4.setPicUrl("http://f.hiphotos.baidu.com/image/pic/item/242dd42a2834349b7eaf886ccdea15ce37d3beaa.jpg");
			article4.setUrl("http://f.hiphotos.baidu.com/image/pic/item/242dd42a2834349b7eaf886ccdea15ce37d3beaa.jpg");
			articles.add(article4) ;
			
			WechatNewArticles article5 = new WechatNewArticles();
			article5.setTitle("美女图片（五）");
			article5.setDescription("这是从百度搜集到的美女图片（五）");
			article5.setPicUrl("http://b.hiphotos.baidu.com/image/pic/item/ac345982b2b7d0a29559e274cfef76094a369a43.jpg");
			article5.setUrl("http://b.hiphotos.baidu.com/image/pic/item/ac345982b2b7d0a29559e274cfef76094a369a43.jpg");
			articles.add(article5) ;
			
			return WechatResponseUtil.getNewsRespXmlStr(weReq, articles);
			
		}else if(weReq.getContent().equals("获取历史聊天记录")){
			StringBuilder hisMsg = new StringBuilder() ;
			for(String msg : service.lget(RedisCacheKey.getKey(RedisCacheKey.L_USER_MSG, weReq.getFromUserName()))) {
				hisMsg.append(msg).append("\n") ;
			}
			return WechatResponseUtil.getTextRespXmlStr(weReq, hisMsg.toString());
		}else{
			service.llset(RedisCacheKey.getKey(RedisCacheKey.L_USER_MSG, weReq.getFromUserName()), weReq.getContent());
			return WechatResponseUtil.getTextRespXmlStr(weReq, "你说了："+weReq.getContent());
		}
	}

}
