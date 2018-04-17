
package com.suyin.member.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.suyin.system.model.Page;

import com.suyin.system.util.Tools;


import com.suyin.member.model.*;
import com.suyin.member.service.*;


/**
 * 商家所属品类管理 
 * @author lz
 * @version 2015-8-22
 * @see CategoryController
 * @since
 */
@Controller
@RequestMapping("/category")
public class CategoryController{


    private final static Logger log=Logger.getLogger(CategoryController.class);
    @Autowired
    private CategoryService categoryService;


    /**
     * 首页
     * @return 
     * @see
     */
    @RequestMapping(value="/index")
    public ModelAndView index() {

        return new ModelAndView("member/category/index");
    }


    /**
     * 读取列表
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value = "/list")
    public @ResponseBody Map<String, Object> findForCategoryAll(HttpServletRequest request) {
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

            Category  entityInfo=new Category ();
            entityInfo.setPage(page);
            List<Category > list=categoryService.findCategoryByPage(entityInfo);
            map.put("rows",list); 
            map.put("total",entityInfo.getPage().getTotalResult());  
        }
        catch (Exception e)
        {
            // TODO: handle exception
            log.error("Controller Error CategoryController-> findForCategoryAll  " + e.getMessage());
        }

        return map;
    }


    /**
     * 跳转添加页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpAdd")
    public ModelAndView jumpCategoryAdd(HttpServletRequest request) {
        ModelMap map=new ModelMap();

        return new ModelAndView("member/category/save",map);
    }

    /**
     * 跳转修改页面 
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpEdit")
    public ModelAndView jumpCategoryEdit(ModelMap map,HttpServletRequest request) {

        try
        {
            if(Tools.notEmpty(request.getParameter("id"))){

                Category entity=new Category();
                entity.setCgId(Integer.parseInt(request.getParameter("id")));
                entity=categoryService.findCategoryById(entity);
                map.put("category", entity);
            }

        }
        catch (Exception e)
        {
            log.error("Controller Error CategoryController-> jumpEdit  " + e.getMessage());
        }

        return new ModelAndView("member/category/edit",map);
    }


    /**
     * 信息保存
     * Description: <br>
     * @param 
     * @return 
     * @see
     */
    @RequestMapping(value = "/add")
    public @ResponseBody Map<String, Object> saveCategoryInfo(Category entity) {
        ModelMap map=new ModelMap();
        try
        {
            map.put("result",categoryService.addCategory(entity));
        }
        catch (Exception e)
        {
            log.error("Controller Error CategoryController-> saveCategoryInfo  " + e.getMessage());
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
    public @ResponseBody Map<String, Object> updateCategoryById(Category entity) {
        ModelMap map=new ModelMap();
        try
        {
            map.put("result",categoryService.updateCategory(entity));
        }
        catch (Exception e)
        {
            log.error("Controller Error CategoryController-> updateCategoryById  " + e.getMessage());
        }
        return map;
    }

    /**
     * 信息删除
     * @param 
     * @return
     */
    @RequestMapping(value = "/delete")
    public @ResponseBody Map<String, Object> deleteCategoryById(String id) {

        ModelMap map=new ModelMap();
        try
        {
            if(Tools.notEmpty(id)){

                map.put("result",categoryService.deleteCategory(id));
            }
        }
        catch (Exception e)
        {
            log.error("Controller Error CategoryController-> deleteCategoryById  " + e.getMessage());
        }
        return map;
    }


}

