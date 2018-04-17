
package com.suyin.member.controller;

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

import com.suyin.city.model.City;
import com.suyin.city.service.CityService;
import com.suyin.member.model.Category;
import com.suyin.system.model.Page;
import com.suyin.system.model.SystemRole;
import com.suyin.system.model.SystemUser;
import com.suyin.system.util.Tools;

import java.util.*;
import com.suyin.member.model.*;
import com.suyin.member.service.*;


/**
 * 商家管理 》商圈管理 
 * @author lz
 * @version 2015-8-24
 * @see RegionController
 * @since
 */
@Controller
@RequestMapping("/region")
public class RegionController{

    private final static Logger log=Logger.getLogger(RegionController.class);
    @Autowired
    private RegionService regionService;//商圈
    
    @Autowired
    private CityService cityService;//城市

    /**
     * 首页
     * @return 
     * @see
     */
    @RequestMapping(value="/index")
    public ModelAndView index() {

        return new ModelAndView("member/region/index");
    }


    /**
     * 读取列表
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value = "/list")
    public @ResponseBody Map<String, Object> findForRegionAll(HttpServletRequest request) {
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

            Region  entityInfo=new Region ();
            entityInfo.setPage(page);
            List<Region > list=regionService.findRegionByPage(entityInfo);
            map.put("rows",list); 
            map.put("total",entityInfo.getPage().getTotalResult()); 

        }
        catch (Exception e)
        {
            log.error("Controller Error RegionController-> findRegionByWhere  " + e.getMessage());
        }

        return map;
    }




    /**
     * 跳转添加页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpAdd")
    public ModelAndView jumpRegionAdd(HttpServletRequest request) {
        ModelMap map=new ModelMap();
        City  entity=new City();
        entity.setUpid(0);
        List<City>list=cityService.findCity(entity);
        map.put("cityList",list);
        return new ModelAndView("member/region/save",map);
    }

    /**
     * 跳转修改页面 
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpEdit")
    public ModelAndView jumpRegionEdit(HttpServletRequest request) {
        ModelMap map=new ModelMap();
        try
        {

            if(Tools.notEmpty(request.getParameter("id"))){  
                
                Region entity=new Region();
                entity.setRegionId(Integer.parseInt(request.getParameter("id")));
                entity=regionService.findRegionById(entity);
                map.put("region",entity);
                City  city=new City();
                city.setUpid(0);
                List<City>list=cityService.findCity(city);
                map.put("cityList",list);
            }
        }
        catch (Exception e)
        {

            log.error("Controller Error RegionController-> jumpRegionEdit  " + e.getMessage());
        }
        return new ModelAndView("member/region/edit",map);
    }

    /**
     * 信息保存
     * Description: <br>
     * @param 
     * @return 
     * @see
     */
    @RequestMapping(value = "/add")
    public @ResponseBody Map<String, Object> saveRegionInfo(Region entity) {
        ModelMap map=new ModelMap();
        try
        {
            
            map.put("result",regionService.addRegion(entity));
        }
        catch (Exception e)
        {
            log.error("Controller Error RegionController-> saveRegionInfo " + e.getMessage());
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
    public @ResponseBody Map<String, Object> updateRegionById(Region entity) {
        ModelMap map=new ModelMap();
        try
        {            
            map.put("result",regionService.updateRegion(entity));
        }
        catch (Exception e)
        {
            log.error("Controller Error RegionController-> updateRegionById  " + e.getMessage());
        }
        return map;
    }

    /**
     * 信息删除
     * @param 
     * @return
     */
    @RequestMapping(value = "/delete")
    public @ResponseBody Map<String, Object> deleteRegionById(String id) {

        ModelMap map=new ModelMap();
        try
        {
            if(Tools.notEmpty(id)){
                
                map.put("result",regionService.deleteRegion(id));
            }  
        }
        catch (Exception e)
        {
            log.error("Controller Error RegionController-> deleteRegionById  " + e.getMessage());
        }
 
        return map;
    }


}

