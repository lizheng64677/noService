package com.suyin.weChat.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suyin.common.SystemPropertiesHolder;
import com.suyin.weChat.model.db.TextAndImageMessage;
import com.suyin.weChat.model.response.Article;
import com.suyin.weChat.model.response.NewsResponseMessage;
import com.suyin.weChat.model.response.TextResponseMessage;
import com.suyin.weChat.service.WeChatReplyService;
import com.suyin.weChat.service.WeChatService;
import com.suyin.weChat.util.HttpClientUtils;
import com.suyin.weChat.util.RequestMessageUtil;

@Transactional
@Service("weChatService")
public class WeChatServiceImpl implements  WeChatService{
    private Logger log = Logger.getLogger(this.getClass());

    @Autowired
    WeChatReplyService weChatReplyService;

    /**
     * 微信统一回复主入口
     */
    public Object agentWebChatService(String resultxml) {
        Object message = null;
        TextAndImageMessage textAndImageMessage = null;
        String fromUserName = null;
        String toUserName=null;
        try {

            Document doc = DocumentHelper.parseText(resultxml);
            Element rootElement = doc.getRootElement();
            // 发送方帐号（open_id）
            fromUserName = rootElement.elementText("FromUserName");
            // 公众帐号
            toUserName = rootElement.elementText("ToUserName");
            // 消息类型
            String msgType = rootElement.elementText("MsgType");
            //回复内容
            String content=rootElement.elementText("Content");
            String resultStr = rootElement.elementText("ScanResult");

            if (RequestMessageUtil.REQ_MESSAGE_TYPE_TEXT.equals(msgType)) {  

                textAndImageMessage=new TextAndImageMessage();
                textAndImageMessage.setKeywords(content);
                //统一业务逻辑处理
                message=this.replyWeChat(textAndImageMessage, fromUserName, toUserName);            

            }else if(RequestMessageUtil.REQ_MESSAGE_TYPE_IMAGE.equals(msgType)){
                    log.info("您发送的是图片！");        
            }else if(RequestMessageUtil.REQ_MESSAGE_TYPE_LOCATION.equals(msgType)){
                log.info("您发送的是地理位置消息！");  
            }else if(RequestMessageUtil.REQ_MESSAGE_TYPE_LINK.equals(msgType)){
                log.info("您发送的是链接消息！"); 
            }else if(RequestMessageUtil.REQ_MESSAGE_TYPE_VOICE.equals(msgType)){
                log.info("您发送的是语音消息！"); 
            }else if(RequestMessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(msgType)){

                String eventType = rootElement.elementText("Event");
                // 订阅   
                if (eventType.equals(RequestMessageUtil.EVENT_TYPE_SUBSCRIBE)){

                    //设置查询条件
                    textAndImageMessage=new TextAndImageMessage();
                    textAndImageMessage.setReply_is_use(0);
                    //统一业务逻辑处理
                    message=this.replyWeChat(textAndImageMessage, fromUserName, toUserName);

                }else if(eventType.equals(RequestMessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {  
                    //用户已经取消关注了，在这里可以做一些限制下次类似的操作

                }else if(RequestMessageUtil.EVENT_TYPE_CLICK.equals(eventType)){

                    String EventKey=rootElement.elementText("EventKey");
                    textAndImageMessage=new TextAndImageMessage();
                    textAndImageMessage.setKeywords(EventKey);
                    //统一业务逻辑处理
                    message=this.replyWeChat(textAndImageMessage, fromUserName, toUserName);
                }else if(RequestMessageUtil.EVENT_TYPE_PUSH.equals(eventType)){
                    //扫一扫事件推送
                }else if(RequestMessageUtil.EVENT_TYPE_WAITMSG.equals(eventType)){                    
                    //扫一扫消息接受中
                }

            }

        } catch (Exception e) {
            log.error("Service Error WebChatServiceImpl -> agentWebChatService"+e.getMessage());
        }
        if(null==message){
            return "";
        }else{
            return message;
        }

    }


    /**
     * 处理业务逻辑
     * @param textAndImageMessage
     * @param fromUserName
     * @param toUserName
     * @return
     */
    private Object replyWeChat(TextAndImageMessage textAndImageMessage,String fromUserName,String toUserName ){
        List<TextAndImageMessage> list=weChatReplyService.findTextAndImageMessageByKeywordsOrIsReply(textAndImageMessage);
        Object message = null;
        if(!list.isEmpty()){
            if(list.size()<2){
                
                if(1==list.get(0).getMessageType()){

                    message= this.imageReply(textAndImageMessage, fromUserName, toUserName);
                }else{
                    message= this.textReply(list.get(0), fromUserName, toUserName);
                }
            }else{
                message= this.imageReply(textAndImageMessage, fromUserName, toUserName);
            }
        }
        return message;
    }

    /**
     * 返回文本信息
     * @param textAndImageMessage
     * @param fromUserName
     * @param toUserName
     * @return
     */
    private Object textReply(TextAndImageMessage textAndImageMessage,String fromUserName,String toUserName  ){
        TextResponseMessage trm=new TextResponseMessage();
        trm.setToUserName(fromUserName);
        trm.setFromUserName(toUserName);  
        trm.setCreateTime(new Date().getTime());  
        trm.setMsgType(RequestMessageUtil.RESP_MESSAGE_TYPE_TEXT);
        trm.setContent(textAndImageMessage.getTitle());
        return RequestMessageUtil.textMessageToXml(trm);
    }

    /**
     * 返回微信打印机文本信息
     * @param textAndImageMessage
     * @param fromUserName
     * @param toUserName
     * @return
     */
    private Object textReplyPrint(String title,String fromUserName,String toUserName  ){
        TextResponseMessage trm=new TextResponseMessage();
        trm.setToUserName(fromUserName);
        trm.setFromUserName(toUserName);  
        trm.setCreateTime(new Date().getTime());  
        trm.setMsgType(RequestMessageUtil.RESP_MESSAGE_TYPE_TEXT);
        trm.setContent(title);
        return RequestMessageUtil.textMessageToXml(trm);
    }
    
    /**
     * 返回图文信息
     * @param list
     * @param fromUserName
     * @param toUserName
     * @return
     */
    private Object imageReply(TextAndImageMessage textAndImageMessageParams ,String fromUserName,String toUserName ){
        NewsResponseMessage nrm=new NewsResponseMessage();
        nrm.setToUserName(fromUserName);
        nrm.setFromUserName(toUserName);  
        nrm.setCreateTime(new Date().getTime());  
        nrm.setMsgType(RequestMessageUtil.RESP_MESSAGE_TYPE_NEWS);
        List<Article> articles=new ArrayList<Article>();
        List<TextAndImageMessage> list=weChatReplyService.findImagesReply(textAndImageMessageParams);
        Article art;
        for(TextAndImageMessage textAndImageMessage:list){
            art=new Article();
            art.setTitle(textAndImageMessage.getTitle());
            art.setPicUrl(SystemPropertiesHolder.get("picUrl")+textAndImageMessage.getPic_url());
            art.setUrl(textAndImageMessage.getUrl());
            art.setDescription(textAndImageMessage.getDescription());
            articles.add(art);
        }
        nrm.setArticleCount(articles.size());
        nrm.setArticles(articles);
        return RequestMessageUtil.newsMessageToXml(nrm);
    }

    /**
     * 返回打印机图文信息
     * @param list
     * @param fromUserName
     * @param toUserName
     * @return
     */
    private  Object imageReplyPrint(String picUrl,String url,String title,String description,String fromUserName,String toUserName ){
        NewsResponseMessage nrm=new NewsResponseMessage();
        nrm.setToUserName(fromUserName);
        nrm.setFromUserName(toUserName);  
        nrm.setCreateTime(new Date().getTime());  
        nrm.setMsgType(RequestMessageUtil.RESP_MESSAGE_TYPE_NEWS);
        List<Article> articles=new ArrayList<Article>();
        
        Article art;
        art=new Article();
        art.setTitle(title);
        art.setPicUrl(picUrl);
        art.setUrl(url);
        art.setDescription(description);
        
        articles.add(art);
        
        nrm.setArticleCount(1);
        nrm.setArticles(articles);
        
        return RequestMessageUtil.newsMessageToXml(nrm);
    }
    
    
    /***
     * 打印机代码请在这个方法里面添加
     * @return
     */
    private Object printImage(String resultXml,String fromUserName,String toUserName){
        
        Object message = null;
        
        //打印机访问接口连接
        String printUrl= SystemPropertiesHolder.get("printUrl").toString();

        String printXml = HttpClientUtils.postPrint(printUrl, resultXml);
        
        //String printXml="<xml><ToUserName><![CDATA[otcs-s52obQKOolqF_a6LDRp04e4]]></ToUserName><FromUserName><![CDATA[gh_6a54ee5a95ab]]></FromUserName><CreateTime>1449562091</CreateTime><MsgType><![CDATA[news]]></MsgType><ArticleCount>2</ArticleCount><Articles><item><Title><![CDATA[1.触摸裁剪1764287]]></Title><Description><![CDATA[触摸此图片进行裁剪]]></Description><PicUrl><![CDATA[http://msmf.suyinx.com:80/noService/resources/outImages/advs/20151204/20151204172457_871.jpg]]></PicUrl><Url><![CDATA[http://58.63.226.54/Weixin-Dev/index.php/c_printClient/setCutimg?photoW=777&photoH=939&medialId=5ed8e905782a30d07eec964265af0895&mediaPath=http://msmf.suyinx.com:80/noService/resources/outImages/advs/20151204/20151204172457_871.jpg&printSize=777x939&wxAppID=yrKeT6T4]]></Url></item><item><Title><![CDATA[2.免费打印 2 次,请回复广告机上的验证码打印照片!]]></Title><Description><![CDATA[]]></Description><PicUrl><![CDATA[]]></PicUrl><Url><![CDATA[]]></Url></item></Articles></xml>";

        int length = printXml.split("Url").length;
        //处理微信打印机返回信息
        if (length > 1)
        {
            String printUrlXml = printXml.substring(printXml.indexOf("<Url>", 0), printXml.indexOf("</Url>", 0));
            
            String url = printUrlXml.substring(printUrlXml.indexOf("h", 0), printUrlXml.indexOf("]", 0));
            
            String printPicXml = printXml.substring(printXml.indexOf("<PicUrl>", 0), printXml.indexOf("</PicUrl>", 0));
            
            String picUrl = printPicXml.substring(printPicXml.indexOf("h", 0), printPicXml.indexOf("]", 0));
            //图片处理
            message = imageReplyPrint(picUrl,url,"微信打印机图片","裁剪结束请回复微信打印机上的验证码打印照片",fromUserName,toUserName);
            
        }
        else
        {
            //文本形式
            printXml = printXml.substring(printXml.indexOf("<Content>", 0), printXml.indexOf("</Content>", 0));
            String contxt = printXml.substring(printXml.indexOf("[", 0), printXml.indexOf("]", 0));

            if (contxt.contains("您的打印次数已使用完"))
            {
                textReplyPrint("抱歉，您的打印次数已使用完!",fromUserName,toUserName);             

            }
            else if(contxt.contains("您关注的公众平台,没有设置照片版面"))
            {
                textReplyPrint("抱歉， 您关注的公众平台,没有设置照片版面!", fromUserName,toUserName);
            }
        }
        
        return message;
    }

}