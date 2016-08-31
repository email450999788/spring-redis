package com.newyeah.test.spring.redis.co;

import com.newyeah.test.spring.redis.utils.WechatResponseUtil;

/**
 * 
 * @Author LiuYang
 * @Date 2016年8月25日
 * @Description 图文信息明细
 *
 */
public class WechatNewArticles {
	
	private String Title ;
	private String Description ;
	private String PicUrl ;
	private String Url ;
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	
	public String toString(){
		return new StringBuffer().append(WechatResponseUtil.getNodeXml("Title", this.Title))
				.append(WechatResponseUtil.getNodeXml("Description", this.Description))
				.append(WechatResponseUtil.getNodeXml("PicUrl", this.PicUrl))
				.append(WechatResponseUtil.getNodeXml("Url", this.Url))
				.toString();
	}
	
	
}
