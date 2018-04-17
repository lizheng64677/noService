
package com.suyin.expzhuan.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
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
import com.suyin.expzhuan.model.*;
import com.suyin.expzhuan.service.*;


/**
 * 
 * 全民赚_问卷调查_结果定义配置综合处理类
 * @author lz
 * @version 2015-9-7
 * @see ExpTaskPrototypeController
 * @since
 */
@Controller
@RequestMapping("/expzhuanprototype")
public class ExpTaskPrototypeController{

    private final static Logger log=Logger.getLogger(ExpTaskPrototypeController.class);
    @Autowired
    private ExpTaskPrototypeService expZhuanPrototypeService;

    /**
     * 跳转至活动结果配置页面
     * @return 
     * @see
     */
    @RequestMapping(value="/setting")
    public ModelAndView index(HttpServletRequest request) {
        ModelMap map=new ModelMap();
        String expId=request.getParameter("id");
        String moduleType=request.getParameter("moduleType");
        String showJsp=request.getParameter("showJsp");
        map.put("expId", expId);
        map.put("moduleType",moduleType);
        map.put("showJsp",showJsp);
        return new ModelAndView("exptask/prototype/wjdc_setting",map);
    }



    /**
     * 活动动态属性加载
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findExpZhuanPrototype")
    public @ResponseBody String findExpZhuanPrototype(HttpServletRequest request,String expId) throws Exception {
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        JSONObject j=new JSONObject();

        String id=(null==expId?"0":expId);
        String  muduleType=request.getParameter("moduleType");
        list=expZhuanPrototypeService.findExpDictionaryPrototype(id,muduleType);
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
    @RequestMapping(value = "/addExpZhuanPrototype")
    public @ResponseBody String addExpZhuanPrototype(HttpServletRequest request,String expId) throws Exception {
        JSONObject j=new JSONObject();

        try
        {
            expZhuanPrototypeService.deleteExpZhuanPrototype(expId);
            String jsonData=request.getParameter("paramdata");
            int result=expZhuanPrototypeService.addExpZhuanPrototype(jsonData);
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
    public @ResponseBody Map<String, Object> deleteExpZhuanPrototypeById(String id) {

        ModelMap map=new ModelMap();
        try
        {
            if(Tools.notEmpty(id)){

                map.put("result",expZhuanPrototypeService.deleteExpZhuanPrototype(id));
            }  
        }
        catch (Exception e)
        {
            log.error("Controller Error ExpPrototypeController-> deleteExpPrototypeById  " + e.getMessage());
        }

        return map;
    }

}

