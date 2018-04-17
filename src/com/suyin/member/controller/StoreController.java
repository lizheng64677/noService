
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
 * 商家管理 》门店管理 
 * @author lz
 * @version 2015-8-24
 * @see StoreController
 * @since
 */
@Controller
@RequestMapping("/store")
public class StoreController{

    private final static Logger log=Logger.getLogger(StoreController.class);
    @Autowired
    private StoreService storeService;
    @Autowired
    private CategoryService categoryService;//品类
    @Autowired
    private CityService cityService;//城市
    /**
     * 首页
     * @return 
     * @see
     */
    @RequestMapping(value="/index")
    public ModelAndView index() {

        return new ModelAndView("member/store/index");
    }


    /**
     * 读取列表
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value = "/list")
    public @ResponseBody Map<String, Object> findForStoreAll(HttpServletRequest request) {
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

            Store  entityInfo=new Store ();
            entityInfo.setPage(page);
            List<Store > list=storeService.findStoreByPage(entityInfo);
            map.put("rows",list); 
            map.put("total",entityInfo.getPage().getTotalResult()); 

        }
        catch (Exception e)
        {
            log.error("Controller Error StoreController-> findStoreByWhere  " + e.getMessage());
        }

        return map;
    }




    /**
     * 跳转添加页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpAdd")
    public ModelAndView jumpStoreAdd(HttpServletRequest request) {
        ModelMap map=new ModelMap();
        City  city=new City();
        city.setUpid(0);
        List<City>list=cityService.findCity(city);
        List<Category>cateList=categoryService.findCategory(null);
        map.put("cityList",list);
        map.put("cateList", cateList);
        return new ModelAndView("member/store/save",map);
    }

    /**
     * 跳转修改页面 
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpEdit")
    public ModelAndView jumpStoreEdit(HttpServletRequest request) {
        ModelMap map=new ModelMap();
        try
        {

            if(Tools.notEmpty(request.getParameter("id"))){  
                City  city=new City();
                city.setUpid(0);
                List<City>list=cityService.findCity(city);
                List<Category>cateList=categoryService.findCategory(null);
                map.put("cityList",list);
                map.put("cateList", cateList);
                
                Store entity=new Store();
                entity.setStoreId(Integer.parseInt(request.getParameter("id")));
                entity=storeService.findStoreById(entity);
                map.put("store",entity);

            }
        }
        catch (Exception e)
        {

            log.error("Controller Error StoreController-> jumpStoreEdit  " + e.getMessage());
        }
        return new ModelAndView("member/store/edit",map);
    }

    /**
     * 信息保存
     * Description: <br>
     * @param 
     * @return 
     * @see
     */
    @RequestMapping(value = "/add")
    public @ResponseBody Map<String, Object> saveStoreInfo(Store entity) {
        ModelMap map=new ModelMap();
        try
        {
            
            map.put("result",storeService.addStore(entity));
        }
        catch (Exception e)
        {
            log.error("Controller Error StoreController-> saveStoreInfo " + e.getMessage());
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
    public @ResponseBody Map<String, Object> updateStoreById(Store entity) {
        ModelMap map=new ModelMap();
        try
        {            
            map.put("result",storeService.updateStore(entity));
        }
        catch (Exception e)
        {
            log.error("Controller Error StoreController-> updateStoreById  " + e.getMessage());
        }
        return map;
    }

    /**
     * 信息删除
     * @param 
     * @return
     */
    @RequestMapping(value = "/delete")
    public @ResponseBody Map<String, Object> deleteStoreById(String id) {

        ModelMap map=new ModelMap();
        try
        {
            if(Tools.notEmpty(id)){
                
                map.put("result",storeService.deleteStore(id));
            }  
        }
        catch (Exception e)
        {
            log.error("Controller Error StoreController-> deleteStoreById  " + e.getMessage());
        }
 
        return map;
    }


}

