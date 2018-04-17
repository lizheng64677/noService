package com.suyin.weChat.model.response;

/**
 * 微信响应消息文本消息
 * @author madara
 *
 */
public class TextResponseMessage extends BaseResponseMessage {
	private static final long serialVersionUID = 6709725786729522731L;
	
	// 回复的消息内容   
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}  

}
