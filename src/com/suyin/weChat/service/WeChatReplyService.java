package com.suyin.weChat.service;

import java.util.List;

import com.suyin.weChat.model.db.TextAndImageMessage;

public interface WeChatReplyService {
	public Integer addTextAndImageMessage(TextAndImageMessage textAndImageMessage);
	
	public Integer deleteTextAndImageMessage(TextAndImageMessage textAndImageMessage);

	public Integer updateTextAndImageMessage(TextAndImageMessage textAndImageMessage);
	
	public List<TextAndImageMessage> findTextAndImageMessageByPage(TextAndImageMessage textAndImageMessage);
	
	public List<TextAndImageMessage> findTextAndImageMessageByKeywordsOrIsReply(TextAndImageMessage textAndImageMessage);
	
	public TextAndImageMessage getTextAndImageMessage(TextAndImageMessage textAndImageMessage);
	
	public Integer reply(TextAndImageMessage textAndImageMessage);
	
    /**
     * 图文信息返回
     * @param textAndImageMessage
     * @return 
     * @see
     */
    public List<TextAndImageMessage> findImagesReply(TextAndImageMessage textAndImageMessage);
	
		
}
