
package com.suyin.experience.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.util.JSONPObject;
import org.json.JSONObject;
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
import com.suyin.experience.model.*;
import com.suyin.experience.service.*;


/**
 * 
 * 活动动态属性配置操作类
 * @author lz
 * @version 2015-8-31
 * @see ExpPrototypeController
 * @since
 */
@Controller
@RequestMapping("/expprototype")
public class ExpPrototypeController{

    private final static Logger log=Logger.getLogger(ExpPrototypeController.class);
    @Autowired
    private ExpPrototypeService expPrototypeService;


    /**
     * 活动动态属性加载
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findExpPrototype")
    public @ResponseBody String findUserPrototype(HttpServletRequest request,String expId) throws Exception {
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        JSONObject j=new JSONObject();

        list=expPrototypeService.findUserPrototype(null==expId?"0":expId);
        if(list.size()>0){

            j.put("message", "success");
            j.put("result", list);

        }else{

            j.put("message", "invalidSize");

        }
        return j.toString();
    }
    @RequestMapping(value = "/findExpTaskPrototype")
    public @ResponseBody String findExpTaskPrototype(HttpServletRequest request,String expId) throws Exception {
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	JSONObject j=new JSONObject();
    	
    	list=expPrototypeService.findExpTaskPrototype(null==expId?"0":expId);
    	if(list.size()>0){
    		
    		j.put("message", "success");
    		j.put("result", list);
    		
    	}else{
    		
    		j.put("message", "invalidSize");
    		
    	}
    	return j.toString();
    }

    /**
     * 动态活动属性添加
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addExpPrototype")
    public @ResponseBody String addUserPrototype(HttpServletRequest request,String expId) throws Exception {
        JSONObject j=new JSONObject();


        try
        {
            expPrototypeService.deleteExpPrototype(expId);
            String jsonData=request.getParameter("paramdata");
            int result=expPrototypeService.addExpPrototype(jsonData);
            if(result>0){

                j.put("message", "success"); 
            }else{

                j.put("message", "invalidAddInfo"); 
            }
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            j.put("message", "invalidDelInfo");
        }



        return j.toString();
    }
    @RequestMapping(value = "/addExpTaskPrototype")
    public @ResponseBody String addExpTaskPrototype(HttpServletRequest request,String expId) throws Exception {
    	JSONObject j=new JSONObject();
    	
    	
    	try
    	{
    		expPrototypeService.deleteExpTaskPrototype(expId);
    		String jsonData=request.getParameter("paramdata");
    		int result=expPrototypeService.addExpTaskPrototype(jsonData);
    		if(result>0){
    			
    			j.put("message", "success"); 
    		}else{
    			
    			j.put("message", "invalidAddInfo"); 
    		}
    	}
    	catch (Exception e)
    	{
    		// TODO Auto-generated catch block
    		j.put("message", "invalidDelInfo");
    	}
    	
    	
    	
    	return j.toString();
    }

    /**
     * 信息删除
     * @param 
     * @return
     */
    @RequestMapping(value = "/delete")
    public @ResponseBody Map<String, Object> deleteExpPrototypeById(String id) {

        ModelMap map=new ModelMap();
        try
        {
            if(Tools.notEmpty(id)){

                map.put("result",expPrototypeService.deleteExpPrototype(id));
            }  
        }
        catch (Exception e)
        {
            log.error("Controller Error ExpPrototypeController-> deleteExpPrototypeById  " + e.getMessage());
        }

        return map;
    }


}

