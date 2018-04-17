package com.suyin.weChat.mapper;

import java.util.List;

import com.suyin.weChat.model.db.TextAndImageMessage;

public interface WeChatReplyMapper {
	
		public Integer addTextAndImageMessage(TextAndImageMessage textAndImageMessage);
		
		public Integer deleteTextAndImageMessage(TextAndImageMessage textAndImageMessage);
	
		public Integer updateTextAndImageMessage(TextAndImageMessage textAndImageMessage);
		
		public List<TextAndImageMessage> findTextAndImageMessageByPage(TextAndImageMessage textAndImageMessage);
		
		public List<TextAndImageMessage> findTextAndImageMessageByKeywordsOrIsReply(TextAndImageMessage textAndImageMessage);
		
		public Integer updateTextAndImageMessageByIsReply(TextAndImageMessage textAndImageMessage);
		/**
		 * 图文信息返回
		 * @param textAndImageMessage
		 * @return 
		 * @see
		 */
		public List<TextAndImageMessage> findImagesReply(TextAndImageMessage textAndImageMessage);
		
}
