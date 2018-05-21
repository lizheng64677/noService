
package com.suyin.decoratevoucher.controller;

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
import com.suyin.decoratevoucher.model.*;
import com.suyin.decoratevoucher.service.*;


/**
 * 	禧居家居营销福利券
 *  福利券设置
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/expdecoratevoucher")
public class ExpDecorateVoucherController{

    private final static Logger log=Logger.getLogger(ExpDecorateVoucherController.class);
    @Autowired
    private ExpDecorateVoucherService expDecorateVoucherService;

    /**
     * 首页
     * @return 
     * @see
     */
    @RequestMapping(value="/index")
    public ModelAndView index() {

        return new ModelAndView("expdecoratevoucher/index");
    }


    /**
     * 读取列表
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value = "/list")
    public @ResponseBody Map<String, Object> findForExpDecorateVoucherAll(HttpServletRequest request) {
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

            ExpDecorateVoucher  entityInfo=new ExpDecorateVoucher ();
            entityInfo.setPage(page);
            List<ExpDecorateVoucher > list=expDecorateVoucherService.findExpDecorateVoucherByPage(entityInfo);
            map.put("rows",list); 
            map.put("total",entityInfo.getPage().getTotalResult()); 

        }
        catch (Exception e)
        {
            log.error("Controller Error ExpDecorateVoucherController-> findExpDecorateVoucherByWhere  " + e.getMessage());
        }

        return map;
    }




    /**
     * 跳转添加页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpAdd")
    public ModelAndView jumpExpDecorateVoucherAdd(HttpServletRequest request) {
        ModelMap map=new ModelMap();

        return new ModelAndView("expdecoratevoucher/save",map);
    }

    /**
     * 跳转修改页面 
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpEdit")
    public ModelAndView jumpExpDecorateVoucherEdit(HttpServletRequest request) {
        ModelMap map=new ModelMap();
        try
        {

            if(Tools.notEmpty(request.getParameter("id"))){  
                
                ExpDecorateVoucher entity=new ExpDecorateVoucher();
                entity.setId(Integer.parseInt(request.getParameter("id")));
                entity=expDecorateVoucherService.findExpDecorateVoucherById(entity);
                map.put("expdecoratevoucher",entity);

            }
        }
        catch (Exception e)
        {

            log.error("Controller Error ExpDecorateVoucherController-> jumpExpDecorateVoucherEdit  " + e.getMessage());
        }
        return new ModelAndView("expdecoratevoucher/edit",map);
    }

    /**
     * 信息保存
     * Description: <br>
     * @param 
     * @return 
     * @see
     */
    @RequestMapping(value = "/add")
    public @ResponseBody Map<String, Object> saveExpDecorateVoucherInfo(ExpDecorateVoucher entity) {
        ModelMap map=new ModelMap();
        try
        {
            
            map.put("result",expDecorateVoucherService.addExpDecorateVoucher(entity));
        }
        catch (Exception e)
        {
            log.error("Controller Error ExpDecorateVoucherController-> saveExpDecorateVoucherInfo " + e.getMessage());
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
    public @ResponseBody Map<String, Object> updateExpDecorateVoucherById(ExpDecorateVoucher entity) {
        ModelMap map=new ModelMap();
        try
        {            
            map.put("result",expDecorateVoucherService.updateExpDecorateVoucher(entity));
        }
        catch (Exception e)
        {
            log.error("Controller Error ExpDecorateVoucherController-> updateExpDecorateVoucherById  " + e.getMessage());
        }
        return map;
    }

    /**
     * 信息删除
     * @param 
     * @return
     */
    @RequestMapping(value = "/delete")
    public @ResponseBody Map<String, Object> deleteExpDecorateVoucherById(String id) {

        ModelMap map=new ModelMap();
        try
        {
            if(Tools.notEmpty(id)){
                
                map.put("result",expDecorateVoucherService.deleteExpDecorateVoucher(id));
            }  
        }
        catch (Exception e)
        {
            log.error("Controller Error ExpDecorateVoucherController-> deleteExpDecorateVoucherById  " + e.getMessage());
        }
 
        return map;
    }


}

