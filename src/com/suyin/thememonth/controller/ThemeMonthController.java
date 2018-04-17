
package com.suyin.thememonth.controller;

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

import com.suyin.common.service.ModuleNameService;
import com.suyin.system.model.Page;
import com.suyin.system.model.SystemRole;
import com.suyin.system.model.SystemUser;
import com.suyin.system.util.Tools;

import java.util.*;
import com.suyin.thememonth.model.*;
import com.suyin.thememonth.service.*;



@Controller
@RequestMapping("/thememonth")
public class ThemeMonthController{

    private final static Logger log=Logger.getLogger(ThemeMonthController.class);
    @Autowired
    private ThemeMonthService themeMonthService;
    @Autowired
    private ModuleNameService moduleNameService; //图片静态类型
    /**
     * 首页
     * @return 
     * @see
     */
    @RequestMapping(value="/index")
    public ModelAndView index() {

        return new ModelAndView("thememonth/index");
    }


    /**
     * 读取列表
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value = "/list")
    public @ResponseBody Map<String, Object> findForThemeMonthAll(HttpServletRequest request) {
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

            ThemeMonth  entityInfo=new ThemeMonth ();
            entityInfo.setPage(page);
            List<ThemeMonth > list=themeMonthService.findThemeMonthByPage(entityInfo);
            map.put("rows",list); 
            map.put("total",entityInfo.getPage().getTotalResult()); 

        }
        catch (Exception e)
        {
            log.error("Controller Error ThemeMonthController-> findThemeMonthByWhere  " + e.getMessage());
        }

        return map;
    }




    /**
     * 跳转添加页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpAdd")
    public ModelAndView jumpThemeMonthAdd(HttpServletRequest request) {
        ModelMap map=new ModelMap();

        return new ModelAndView("thememonth/save",map);
    }

    /**
     * 跳转修改页面 
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpEdit")
    public ModelAndView jumpThemeMonthEdit(HttpServletRequest request) {
        ModelMap map=new ModelMap();
        try
        {

            if(Tools.notEmpty(request.getParameter("id"))){  

                ThemeMonth entity=new ThemeMonth();
                entity.setThemeId(Integer.parseInt(request.getParameter("id")));
                entity=themeMonthService.findThemeMonthById(entity);
                map.put("thememonth",entity);

            }
        }
        catch (Exception e)
        {

            log.error("Controller Error ThemeMonthController-> jumpThemeMonthEdit  " + e.getMessage());
        }
        return new ModelAndView("thememonth/edit",map);
    }

    /**
     * 信息保存
     * Description: <br>
     * @param 
     * @return 
     * @see
     */
    @RequestMapping(value = "/add")
    public @ResponseBody Map<String, Object> saveThemeMonthInfo(ThemeMonth entity,HttpServletRequest request) {
        ModelMap map=new ModelMap();
        try
        {
            entity.setAttachments(Tools.getAttachements(request.getParameter("allImages"), this.moduleNameService.THEME_MONTH));
            map.put("result",themeMonthService.addThemeMonth(entity));
        }
        catch (Exception e)
        {
            log.error("Controller Error ThemeMonthController-> saveThemeMonthInfo " + e.getMessage());
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
    public @ResponseBody Map<String, Object> updateThemeMonthById(ThemeMonth entity,HttpServletRequest request) {
        ModelMap map=new ModelMap();
        try
        {            
            entity.setAttachments(Tools.getAttachements(request.getParameter("allImages"), this.moduleNameService.THEME_MONTH));
            map.put("result",themeMonthService.updateThemeMonth(entity));
        }
        catch (Exception e)
        {
            log.error("Controller Error ThemeMonthController-> updateThemeMonthById  " + e.getMessage());
        }
        return map;
    }

    /**
     * 信息删除
     * @param 
     * @return
     */
    @RequestMapping(value = "/delete")
    public @ResponseBody Map<String, Object> deleteThemeMonthById(String id) {

        ModelMap map=new ModelMap();
        try
        {
            if(Tools.notEmpty(id)){

                map.put("result",themeMonthService.deleteThemeMonth(id));
            }  
        }
        catch (Exception e)
        {
            log.error("Controller Error ThemeMonthController-> deleteThemeMonthById  " + e.getMessage());
        }

        return map;
    }


}

