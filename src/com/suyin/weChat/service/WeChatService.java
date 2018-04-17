package com.suyin.weChat.service;

public interface WeChatService {
	/**
	    * 解析微信推送消息
	    * @param resultxml
	    * @return
	    */
	   public Object agentWebChatService(String resultxml);
}
