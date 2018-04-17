package com.suyin.weChat.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suyin.weChat.mapper.WeChatReplyMapper;
import com.suyin.weChat.model.db.TextAndImageMessage;
import com.suyin.weChat.service.WeChatReplyService;

@Transactional
@Service("weChatReplyService")
public class WeChatReplyServiceImpl implements WeChatReplyService {
    private   Logger log = Logger.getLogger(this.getClass());

    @Autowired
    WeChatReplyMapper weChatReplyMapper;


    @Override
    public Integer addTextAndImageMessage(
                                          TextAndImageMessage textAndImageMessage) {
        try {
            return weChatReplyMapper.addTextAndImageMessage(textAndImageMessage);
        } catch (Exception e) {
            log.error("Service Error WeChatReplyServiceImpl -> addTextAndImageMessage"
                + e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public Integer deleteTextAndImageMessage(
                                             TextAndImageMessage textAndImageMessage) {
        try {
            return weChatReplyMapper.deleteTextAndImageMessage(textAndImageMessage);
        } catch (Exception e) {
            log.error("Service Error WeChatReplyServiceImpl -> deleteTextAndImageMessage"
                + e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public Integer updateTextAndImageMessage(
                                             TextAndImageMessage textAndImageMessage) {
        try {
            return weChatReplyMapper.updateTextAndImageMessage(textAndImageMessage);
        } catch (Exception e) {
            log.error("Service Error WeChatReplyServiceImpl -> updateTextAndImageMessage"
                + e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public List<TextAndImageMessage> findTextAndImageMessageByPage(
        TextAndImageMessage textAndImageMessage) {
        try {
            return weChatReplyMapper.findTextAndImageMessageByPage(textAndImageMessage);
        } catch (Exception e) {
            log.error("Service Error WeChatReplyServiceImpl -> findTextAndImageMessageByPage"
                + e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public List<TextAndImageMessage> findTextAndImageMessageByKeywordsOrIsReply(
        TextAndImageMessage textAndImageMessage) {
        try {
            return weChatReplyMapper.findTextAndImageMessageByKeywordsOrIsReply(textAndImageMessage);
        } catch (Exception e) {
            log.error("Service Error WeChatReplyServiceImpl -> findTextAndImageMessageByKeywords"
                + e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public TextAndImageMessage getTextAndImageMessage(
                                                      TextAndImageMessage textAndImageMessage) {
        try {
            if(null!=weChatReplyMapper.findTextAndImageMessageByPage(textAndImageMessage) && 1==weChatReplyMapper.findTextAndImageMessageByPage(textAndImageMessage).size()){
                return weChatReplyMapper.findTextAndImageMessageByPage(textAndImageMessage).get(0);
            }

        } catch (Exception e) {
            log.error("Service Error WeChatReplyServiceImpl -> getTextAndImageMessage"
                + e.getMessage());
            throw new RuntimeException();
        }
        return null;
    }

    @Override
    public Integer reply(TextAndImageMessage textAndImageMessage) {
        TextAndImageMessage t;
        try {

            for(String messageId:textAndImageMessage.getRemoveIds().split(",")){
                t=new TextAndImageMessage();
                t.setMessageId(Integer.parseInt(messageId));
                t.setReply_is_use(textAndImageMessage.getMessageType());
                weChatReplyMapper.updateTextAndImageMessageByIsReply(t);
            }
        } catch (Exception e) {
            log.error("Service Error WeChatReplyServiceImpl -> reply"
                + e.getMessage());
            throw new RuntimeException();
        }
        return 1;
    }

    /**
     * 图文成群显示
     */
    @Override
    public List<TextAndImageMessage> findImagesReply(TextAndImageMessage textAndImageMessage)
    {
        // TODO Auto-generated method stub
        return weChatReplyMapper.findImagesReply(textAndImageMessage);
    }

}
