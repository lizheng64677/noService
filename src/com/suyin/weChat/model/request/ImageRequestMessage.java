package com.suyin.weChat.model.request;


/**
 * 微信消息图片消息
 * 
 * @author madara
 *
 */
public class ImageRequestMessage extends BaseRequestMessage {
	private static final long serialVersionUID = -1648882151637481881L;

	private String PicUrl;

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

}
