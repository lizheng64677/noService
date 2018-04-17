package com.suyin.weChat.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.suyin.system.model.Page;
import com.suyin.weChat.model.db.TextAndImageMessage;
import com.suyin.weChat.service.WeChatReplyService;

@Controller
@RequestMapping("/weChatReply")
public class WeChatReplyController {
	private  Logger log=Logger.getLogger(this.getClass());
	
	
	@Autowired
	WeChatReplyService weChatReplyService;
	
	
    @RequestMapping(value="")
    public ModelAndView index() {
        return new ModelAndView("weChat/reply/index");
    }
    
    
    @RequestMapping(value="doAddWeChatReply")
    public ModelAndView doAddWeChatReply() {
        return new ModelAndView("weChat/reply/add");
    }
    
    @RequestMapping(value="doUpdateWeChatReply")
    public ModelAndView doUpdateWeChatReply(HttpServletRequest request, HttpServletResponse response,TextAndImageMessage textAndImageMessage) {
    	request.setAttribute("reply", weChatReplyService.getTextAndImageMessage(textAndImageMessage));
        return new ModelAndView("weChat/reply/update");
    }
    
    
    
    /**
     * 获取自动回复列表
     * @param request
     * @return
     */
	@RequestMapping(value = "/synChatReplyList")
	public @ResponseBody Map<String, Object> synChatReplyList(HttpServletRequest request) {
		ModelMap map=new ModelMap();
		String pag = request.getParameter("page");
		String showCount = request.getParameter("rows");
		Page page = new Page();
		if (null != pag && null != showCount) {
			page.setCurrentPage(Integer.parseInt(pag));
			page.setShowCount(Integer.parseInt(showCount));
		}
		TextAndImageMessage textAndImageMessage=new TextAndImageMessage();
		textAndImageMessage.setPage(page);
		List<TextAndImageMessage> list=weChatReplyService.findTextAndImageMessageByPage(textAndImageMessage);
		map.put("rows",list); 
		map.put("total",textAndImageMessage.getPage().getTotalResult()); 
		return map;
	}
    
    
    
    
    /**
     * 
     * @param textAndImageMessage
     * @param request
     * @return
     */
    @RequestMapping(value = "/addWeChatReply")
    public @ResponseBody Map<String, Object> addWeChatReply(TextAndImageMessage textAndImageMessage,HttpServletRequest request) {
        ModelMap map=new ModelMap();
        Integer i=0;
        try
        {
           i=weChatReplyService.addTextAndImageMessage(textAndImageMessage);
           map.put("message", i);
        }
        catch (Exception e)
        {
            log.error("Controller Error WeChatReplyController-> addWeChatReply " + e.getMessage());
        }
        return map;
    }
    
    /**
     * 
     * @param textAndImageMessage
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateWeChatReply")
    public @ResponseBody Map<String, Object> updateWeChatReply(TextAndImageMessage textAndImageMessage,HttpServletRequest request) {
        ModelMap map=new ModelMap();
        Integer i=0;
        try
        {
           i=weChatReplyService.updateTextAndImageMessage(textAndImageMessage);
           map.put("message", i);
        }
        catch (Exception e)
        {
            log.error("Controller Error WeChatReplyController-> addWeChatReply " + e.getMessage());
        }
        return map;
    }
    
    @RequestMapping(value = "/deleteWeChatReply")
    public @ResponseBody Map<String, Object> deleteWeChatReply(TextAndImageMessage textAndImageMessage,HttpServletRequest request) {
        ModelMap map=new ModelMap();
        Integer i=0;
        try
        {
           textAndImageMessage.setRemoveIds("("+textAndImageMessage.getRemoveIds()+")");
           i=weChatReplyService.deleteTextAndImageMessage(textAndImageMessage);
           map.put("message", i);
        }
        catch (Exception e)
        {
            log.error("Controller Error WeChatReplyController-> deleteWeChatReply " + e.getMessage());
        }
        return map;
    }
    
    @RequestMapping(value = "/reply")
    public @ResponseBody Map<String, Object> reply(TextAndImageMessage textAndImageMessage,HttpServletRequest request) {
        ModelMap map=new ModelMap();
        Integer i=0;
        try
        {
           i=weChatReplyService.reply(textAndImageMessage);
           map.put("message", i);
        }
        catch (Exception e)
        {
            log.error("Controller Error WeChatReplyController-> reply " + e.getMessage());
        }
        return map;
    }
}
