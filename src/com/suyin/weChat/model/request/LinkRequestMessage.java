package com.suyin.weChat.model.request;

/**
 * 微信消息链接消息
 * 
 * @author madara
 *
 */
public class LinkRequestMessage extends BaseRequestMessage {
	private static final long serialVersionUID = 8911620090493609280L;

	// 消息标题
	private String Title;
	// 消息描述
	private String Description;
	// 消息链接
	private String Url;

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

}
