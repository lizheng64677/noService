package com.suyin.weChat.model.request;


/**
 * 微信消息文本类消息
 * 
 * @author madara
 *
 */
public class TextRequestMessage extends BaseRequestMessage {
	private static final long serialVersionUID = 3035581360742396379L;

	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

}
