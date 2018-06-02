
package com.suyin.decoratebuyorder.controller;

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
import com.suyin.decoratebuyorder.model.*;
import com.suyin.decoratebuyorder.service.*;


/**
 * 微信订单查询
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/decoratebuyorder")
public class DecorateBuyOrderController{

    private final static Logger log=Logger.getLogger(DecorateBuyOrderController.class);
    @Autowired
    private DecorateBuyOrderService decorateBuyOrderService;

    /**
     * 首页
     * @return 
     * @see
     */
    @RequestMapping(value="/index")
    public ModelAndView index() {

        return new ModelAndView("decoratebuyorder/index");
    }


    /**
     * 读取列表
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value = "/list")
    public @ResponseBody Map<String, Object> findForDecorateBuyOrderAll(HttpServletRequest request) {
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

            DecorateBuyOrder  entityInfo=new DecorateBuyOrder ();
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
            List<DecorateBuyOrder > list=decorateBuyOrderService.findDecorateBuyOrderByPage(entityInfo);
            map.put("rows",list); 
            map.put("total",entityInfo.getPage().getTotalResult()); 

        }
        catch (Exception e)
        {
            log.error("Controller Error DecorateBuyOrderController-> findDecorateBuyOrderByWhere  " + e.getMessage());
        }

        return map;
    }




    /**
     * 跳转添加页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpAdd")
    public ModelAndView jumpDecorateBuyOrderAdd(HttpServletRequest request) {
        ModelMap map=new ModelMap();

        return new ModelAndView("decoratebuyorder/save",map);
    }

    /**
     * 跳转修改页面 
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpEdit")
    public ModelAndView jumpDecorateBuyOrderEdit(HttpServletRequest request) {
        ModelMap map=new ModelMap();
        try
        {

            if(Tools.notEmpty(request.getParameter("id"))){  
                
                DecorateBuyOrder entity=new DecorateBuyOrder();
                entity.setOrderId(Integer.parseInt(request.getParameter("id")));
                entity=decorateBuyOrderService.findDecorateBuyOrderById(entity);
                map.put("decoratebuyorder",entity);

            }
        }
        catch (Exception e)
        {

            log.error("Controller Error DecorateBuyOrderController-> jumpDecorateBuyOrderEdit  " + e.getMessage());
        }
        return new ModelAndView("decoratebuyorder/edit",map);
    }

    /**
     * 信息保存
     * Description: <br>
     * @param 
     * @return 
     * @see
     */
    @RequestMapping(value = "/add")
    public @ResponseBody Map<String, Object> saveDecorateBuyOrderInfo(DecorateBuyOrder entity) {
        ModelMap map=new ModelMap();
        try
        {
            
            map.put("result",decorateBuyOrderService.addDecorateBuyOrder(entity));
        }
        catch (Exception e)
        {
            log.error("Controller Error DecorateBuyOrderController-> saveDecorateBuyOrderInfo " + e.getMessage());
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
    public @ResponseBody Map<String, Object> updateDecorateBuyOrderById(DecorateBuyOrder entity) {
        ModelMap map=new ModelMap();
        try
        {            
            map.put("result",decorateBuyOrderService.updateDecorateBuyOrder(entity));
        }
        catch (Exception e)
        {
            log.error("Controller Error DecorateBuyOrderController-> updateDecorateBuyOrderById  " + e.getMessage());
        }
        return map;
    }

    /**
     * 信息删除
     * @param 
     * @return
     */
    @RequestMapping(value = "/delete")
    public @ResponseBody Map<String, Object> deleteDecorateBuyOrderById(String id) {

        ModelMap map=new ModelMap();
        try
        {
            if(Tools.notEmpty(id)){
                
                map.put("result",decorateBuyOrderService.deleteDecorateBuyOrder(id));
            }  
        }
        catch (Exception e)
        {
            log.error("Controller Error DecorateBuyOrderController-> deleteDecorateBuyOrderById  " + e.getMessage());
        }
 
        return map;
    }


}

