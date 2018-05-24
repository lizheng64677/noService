
package com.suyin.decoratemessage.controller;

import java.util.List;
import java.util.Map;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.suyin.system.model.Page;
import com.suyin.system.model.SystemRole;
import com.suyin.system.model.SystemUser;
import com.suyin.system.util.Tools;

import java.util.*;
import com.suyin.decoratemessage.model.*;
import com.suyin.decoratemessage.service.*;


/**
 * 个人中心我的消息记录查询
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/decoratemessage")
public class DecorateMessageController{

    private final static Logger log=Logger.getLogger(DecorateMessageController.class);
    @Autowired
    private DecorateMessageService decorateMessageService;

    /**
     * 首页
     * @return 
     * @see
     */
    @RequestMapping(value="/index")
    public ModelAndView index() {

        return new ModelAndView("decoratemessage/index");
    }


    /**
     * 读取列表
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value = "/list")
    public @ResponseBody Map<String, Object> findForDecorateMessageAll(HttpServletRequest request) {
        ModelMap map=new ModelMap();

        String pag = request.getParameter("page");
        String showCount = request.getParameter("rows");
        Page page = new Page();
        try
        {      
            if (null != pag && null != showCount) {
                page.setCurrentPage(Integer.parseInt(pag));
                page.setShowCount(Integer.parseInt(showCount));
            }

            DecorateMessage  entityInfo=new DecorateMessage ();
            entityInfo.setPage(page);
            List<DecorateMessage > list=decorateMessageService.findDecorateMessageByPage(entityInfo);
            map.put("rows",list); 
            map.put("total",entityInfo.getPage().getTotalResult()); 

        }
        catch (Exception e)
        {
            log.error("Controller Error DecorateMessageController-> findDecorateMessageByWhere  " + e.getMessage());
        }

        return map;
    }




    /**
     * 跳转添加页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpAdd")
    public ModelAndView jumpDecorateMessageAdd(HttpServletRequest request) {
        ModelMap map=new ModelMap();

        return new ModelAndView("decoratemessage/save",map);
    }

    /**
     * 跳转修改页面 
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpEdit")
    public ModelAndView jumpDecorateMessageEdit(HttpServletRequest request) {
        ModelMap map=new ModelMap();
        try
        {

            if(Tools.notEmpty(request.getParameter("id"))){  
                
                DecorateMessage entity=new DecorateMessage();
                entity.setMessageId(Integer.parseInt(request.getParameter("id")));
                entity=decorateMessageService.findDecorateMessageById(entity);
                map.put("decoratemessage",entity);

            }
        }
        catch (Exception e)
        {

            log.error("Controller Error DecorateMessageController-> jumpDecorateMessageEdit  " + e.getMessage());
        }
        return new ModelAndView("decoratemessage/edit",map);
    }

    /**
     * 信息保存
     * Description: <br>
     * @param 
     * @return 
     * @see
     */
    @RequestMapping(value = "/add")
    public @ResponseBody Map<String, Object> saveDecorateMessageInfo(DecorateMessage entity) {
        ModelMap map=new ModelMap();
        try
        {
            
            map.put("result",decorateMessageService.addDecorateMessage(entity));
        }
        catch (Exception e)
        {
            log.error("Controller Error DecorateMessageController-> saveDecorateMessageInfo " + e.getMessage());
        }
        return map;
    }
    /**
     * 信息修改
     * Description: <br>
     * @param 
     * @return 
     * @see
     */
    @RequestMapping(value = "/update")
    public @ResponseBody Map<String, Object> updateDecorateMessageById(DecorateMessage entity) {
        ModelMap map=new ModelMap();
        try
        {            
            map.put("result",decorateMessageService.updateDecorateMessage(entity));
        }
        catch (Exception e)
        {
            log.error("Controller Error DecorateMessageController-> updateDecorateMessageById  " + e.getMessage());
        }
        return map;
    }

    /**
     * 信息删除
     * @param 
     * @return
     */
    @RequestMapping(value = "/delete")
    public @ResponseBody Map<String, Object> deleteDecorateMessageById(String id) {

        ModelMap map=new ModelMap();
        try
        {
            if(Tools.notEmpty(id)){
                
                map.put("result",decorateMessageService.deleteDecorateMessage(id));
            }  
        }
        catch (Exception e)
        {
            log.error("Controller Error DecorateMessageController-> deleteDecorateMessageById  " + e.getMessage());
        }
 
        return map;
    }


}

