
package com.suyin.decoraterecord.controller;

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
import com.suyin.decoraterecord.model.*;
import com.suyin.decoraterecord.service.*;


/**
 * 金额变动记录
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/decoraterecord")
public class DecorateRecordController{

    private final static Logger log=Logger.getLogger(DecorateRecordController.class);
    @Autowired
    private DecorateRecordService decorateRecordService;

    /**
     * 首页
     * @return 
     * @see
     */
    @RequestMapping(value="/index")
    public ModelAndView index() {

        return new ModelAndView("decoraterecord/index");
    }


    /**
     * 读取列表
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value = "/list")
    public @ResponseBody Map<String, Object> findForDecorateRecordAll(HttpServletRequest request) {
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

            DecorateRecord  entityInfo=new DecorateRecord ();
            entityInfo.setPage(page);
            List<DecorateRecord > list=decorateRecordService.findDecorateRecordByPage(entityInfo);
            map.put("rows",list); 
            map.put("total",entityInfo.getPage().getTotalResult()); 

        }
        catch (Exception e)
        {
            log.error("Controller Error DecorateRecordController-> findDecorateRecordByWhere  " + e.getMessage());
        }

        return map;
    }




    /**
     * 跳转添加页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpAdd")
    public ModelAndView jumpDecorateRecordAdd(HttpServletRequest request) {
        ModelMap map=new ModelMap();

        return new ModelAndView("decoraterecord/save",map);
    }

    /**
     * 跳转修改页面 
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpEdit")
    public ModelAndView jumpDecorateRecordEdit(HttpServletRequest request) {
        ModelMap map=new ModelMap();
        try
        {

            if(Tools.notEmpty(request.getParameter("id"))){  
                
                DecorateRecord entity=new DecorateRecord();
                entity.setRecordId(Integer.parseInt(request.getParameter("id")));
                entity=decorateRecordService.findDecorateRecordById(entity);
                map.put("decoraterecord",entity);

            }
        }
        catch (Exception e)
        {

            log.error("Controller Error DecorateRecordController-> jumpDecorateRecordEdit  " + e.getMessage());
        }
        return new ModelAndView("decoraterecord/edit",map);
    }

    /**
     * 信息保存
     * Description: <br>
     * @param 
     * @return 
     * @see
     */
    @RequestMapping(value = "/add")
    public @ResponseBody Map<String, Object> saveDecorateRecordInfo(DecorateRecord entity) {
        ModelMap map=new ModelMap();
        try
        {
            
            map.put("result",decorateRecordService.addDecorateRecord(entity));
        }
        catch (Exception e)
        {
            log.error("Controller Error DecorateRecordController-> saveDecorateRecordInfo " + e.getMessage());
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
    public @ResponseBody Map<String, Object> updateDecorateRecordById(DecorateRecord entity) {
        ModelMap map=new ModelMap();
        try
        {            
            map.put("result",decorateRecordService.updateDecorateRecord(entity));
        }
        catch (Exception e)
        {
            log.error("Controller Error DecorateRecordController-> updateDecorateRecordById  " + e.getMessage());
        }
        return map;
    }

}

