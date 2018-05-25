
package com.suyin.decorateuser.controller;

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
import com.suyin.decorateuser.model.*;
import com.suyin.decorateuser.service.*;


/**
 * 签单管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/expdecorateuser")
public class ExpDecorateUserController{

    private final static Logger log=Logger.getLogger(ExpDecorateUserController.class);
    @Autowired
    private ExpDecorateUserService expDecorateUserService;

    /**
     * 首页
     * @return 
     * @see
     */
    @RequestMapping(value="/index")
    public ModelAndView index() {

        return new ModelAndView("decorateuser/index");
    }


    /**
     * 读取列表
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value = "/list")
    public @ResponseBody Map<String, Object> findForExpDecorateUserAll(HttpServletRequest request) {
        ModelMap map=new ModelMap();

        String pag = request.getParameter("page");
        String showCount = request.getParameter("rows");
        String type=request.getParameter("type");
        String text=request.getParameter("text");
        Page page = new Page();
        try
        {      
            if (null != pag && null != showCount) {
                page.setCurrentPage(Integer.parseInt(pag));
                page.setShowCount(Integer.parseInt(showCount));
            }

            ExpDecorateUser  entityInfo=new ExpDecorateUser ();
            entityInfo.setPage(page);
            if(!"-1".equals(type)){
            		entityInfo.setSaerchType(1);
            	if("0".equals(type)){
                	if(!"".equals(text) && null!=text){
                		entityInfo.setUserName(text);
                	}
            	}else if("1".equals(type)){
            		if(!"".equals(text) && null!=text){
            			entityInfo.setUserPhone(text);
            		}
            	}else if("2".equals(type)){
            		if(!"".equals(text) && null!=text){
            			entityInfo.setNickName(text);
            		}
            	}
            }else{
            	if(!"".equals(text) && null!=text){
                	entityInfo.setText(text);
            	}
        		entityInfo.setSaerchType(-1);
            }
            List<ExpDecorateUser > list=expDecorateUserService.findExpDecorateUserByPage(entityInfo);
            map.put("rows",list); 
            map.put("total",entityInfo.getPage().getTotalResult()); 

        }
        catch (Exception e)
        {
            log.error("Controller Error ExpDecorateUserController-> findExpDecorateUserByWhere  " + e.getMessage());
        }

        return map;
    }




    /**
     * 跳转添加页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpAdd")
    public ModelAndView jumpExpDecorateUserAdd(HttpServletRequest request) {
        ModelMap map=new ModelMap();

        return new ModelAndView("decorateuser/save",map);
    }

    /**
     * 跳转修改页面 
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpEdit")
    public ModelAndView jumpExpDecorateUserEdit(HttpServletRequest request) {
        ModelMap map=new ModelMap();
        try
        {

            if(Tools.notEmpty(request.getParameter("id"))){  
                
                ExpDecorateUser entity=new ExpDecorateUser();
                entity.setUserId(Integer.parseInt(request.getParameter("id")));
                entity=expDecorateUserService.findExpDecorateUserById(entity);
                map.put("expdecorateuser",entity);

            }
        }
        catch (Exception e)
        {

            log.error("Controller Error ExpDecorateUserController-> jumpExpDecorateUserEdit  " + e.getMessage());
        }
        return new ModelAndView("decorateuser/edit",map);
    }

  
    /**
     * 签单用户状态修改
     * 用户签单成功后，需要向推荐人，返现佣金
     * 并新增推荐人金额变动记录
     *  消息提醒
     * Description: <br>
     * @param 
     * @return 
     * @see
     */
    @RequestMapping(value = "/update")
    public @ResponseBody Map<String, Object> updateExpDecorateUserById(ExpDecorateUser entity) {
        ModelMap map=new ModelMap();
        try
        {           
        	//签单返现佣金
        	//获取推荐人信息
        	//增加推荐人金额变动记录
        	//增加推荐人金额消息记录
            map.put("result",expDecorateUserService.updateExpDecorateUser(entity));
        }
        catch (Exception e)
        {
            log.error("Controller Error ExpDecorateUserController-> updateExpDecorateUserById  " + e.getMessage());
        }
        return map;
    }

}

