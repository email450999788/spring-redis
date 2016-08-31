package com.newyeah.test.spring.redis.co;

import org.apache.commons.beanutils.BeanUtils;

/**
 * @Author LiuYang
 * @Date 2016年8月25日
 * @Description  微信请求 BEAN
 *
 */
public class WechatRequest {
	
	private WechatRequestParams wechatRequestParams ;
	
	private String ToUserName ; //	开发者微信号
	private String FromUserName ; //	发送方帐号（一个OpenID）
	private String CreateTime ; //	消息创建时间 （整型）
	private String MsgType	; //text ; 
	private String MsgId	; //消息id，64位整型
	
	private String Content	; 
	private String PicUrl   ; 
	private String MediaId  ; 
	private String Format   ; 
	private String ThumbMediaId ;
	private String Location_X ;
	private String Location_Y ;
	private String Scale ;
	private String Label ;
	private String Title ;
	private String Description ;
	private String Url ;
	private String Event ;
	
	public WechatRequestParams getWechatRequestParams() {
		return wechatRequestParams;
	}
	public void setWechatRequestParams(WechatRequestParams wechatRequestParams) {
		this.wechatRequestParams = wechatRequestParams;
	}
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getFormat() {
		return Format;
	}
	public void setFormat(String format) {
		Format = format;
	}
	public String getThumbMediaId() {
		return ThumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
	public String getLocation_X() {
		return Location_X;
	}
	public void setLocation_X(String location_X) {
		Location_X = location_X;
	}
	public String getLocation_Y() {
		return Location_Y;
	}
	public void setLocation_Y(String location_Y) {
		Location_Y = location_Y;
	}
	public String getScale() {
		return Scale;
	}
	public void setScale(String scale) {
		Scale = scale;
	}
	public String getLabel() {
		return Label;
	}
	public void setLabel(String label) {
		Label = label;
	}
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
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	
	public String getEvent() {
		return Event;
	}
	public void setEvent(String event) {
		Event = event;
	}
	public String toString(){
		try {
			return String.format("WechatRequest:%s", BeanUtils.describe(this)) ;
		} catch (Exception e) {
			return "" ;
		}
	}
	
	
}
