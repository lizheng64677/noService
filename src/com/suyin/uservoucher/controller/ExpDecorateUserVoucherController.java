
package com.suyin.uservoucher.controller;

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
import com.suyin.uservoucher.model.*;
import com.suyin.uservoucher.service.*;



@Controller
@RequestMapping("/expdecorateuservoucher")
public class ExpDecorateUserVoucherController{

    private final static Logger log=Logger.getLogger(ExpDecorateUserVoucherController.class);
    @Autowired
    private ExpDecorateUserVoucherService expDecorateUserVoucherService;

    /**
     * 首页
     * @return 
     * @see
     */
    @RequestMapping(value="/index")
    public ModelAndView index() {

        return new ModelAndView("uservoucher/index");
    }


    /**
     * 读取列表
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value = "/list")
    public @ResponseBody Map<String, Object> findForExpDecorateUserVoucherAll(HttpServletRequest request) {
        ModelMap map=new ModelMap();

        String pag = request.getParameter("page");
        String showCount = request.getParameter("rows");
        String type = request.getParameter("type");
        String vourcheCode = request.getParameter("vourcheCode");

        Page page = new Page();
        try
        {      
            if (null != pag && null != showCount) {
                page.setCurrentPage(Integer.parseInt(pag));
                page.setShowCount(Integer.parseInt(showCount));
            }
            
            ExpDecorateUserVoucher  entityInfo=new ExpDecorateUserVoucher ();
            if(null!=type  &&!"-1".equals(type)){
            entityInfo.setType(type);	
            }
            if(null!=vourcheCode  &&!"-1".equals(vourcheCode)){
                entityInfo.setVourcheCode(vourcheCode);	
                }
            entityInfo.setPage(page);
            List<ExpDecorateUserVoucher > list=expDecorateUserVoucherService.findExpDecorateUserVoucherByPage(entityInfo);
            map.put("rows",list); 
            map.put("total",entityInfo.getPage().getTotalResult()); 

        }
        catch (Exception e)
        {
            log.error("Controller Error ExpDecorateUserVoucherController-> findExpDecorateUserVoucherByWhere  " + e.getMessage());
        }

        return map;
    }




    /**
     * 跳转添加页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpAdd")
    public ModelAndView jumpExpDecorateUserVoucherAdd(HttpServletRequest request) {
        ModelMap map=new ModelMap();

        return new ModelAndView("uservoucher/save",map);
    }

    /**
     * 跳转修改页面 
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpEdit")
    public ModelAndView jumpExpDecorateUserVoucherEdit(HttpServletRequest request) {
        ModelMap map=new ModelMap();
        try
        {

            if(Tools.notEmpty(request.getParameter("id"))){  
                
                ExpDecorateUserVoucher entity=new ExpDecorateUserVoucher();
                entity.setId(Integer.parseInt(request.getParameter("id")));
                entity=expDecorateUserVoucherService.findExpDecorateUserVoucherById(entity);
                map.put("voucher",entity);

            }
        }
        catch (Exception e)
        {

            log.error("Controller Error ExpDecorateUserVoucherController-> jumpExpDecorateUserVoucherEdit  " + e.getMessage());
        }
        return new ModelAndView("uservoucher/edit",map);
    }

    /**
     * 信息保存
     * Description: <br>
     * @param 
     * @return 
     * @see
     */
    @RequestMapping(value = "/add")
    public @ResponseBody Map<String, Object> saveExpDecorateUserVoucherInfo(ExpDecorateUserVoucher entity) {
        ModelMap map=new ModelMap();
        try
        {
            
            map.put("result",expDecorateUserVoucherService.addExpDecorateUserVoucher(entity));
        }
        catch (Exception e)
        {
            log.error("Controller Error ExpDecorateUserVoucherController-> saveExpDecorateUserVoucherInfo " + e.getMessage());
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
    public @ResponseBody Map<String, Object> updateExpDecorateUserVoucherById(ExpDecorateUserVoucher entity) {
        ModelMap map=new ModelMap();
        try
        {            
            map.put("result",expDecorateUserVoucherService.updateExpDecorateUserVoucher(entity));
        }
        catch (Exception e)
        {
            log.error("Controller Error ExpDecorateUserVoucherController-> updateExpDecorateUserVoucherById  " + e.getMessage());
        }
        return map;
    }

    /**
     * 信息删除
     * @param 
     * @return
     */
    @RequestMapping(value = "/delete")
    public @ResponseBody Map<String, Object> deleteExpDecorateUserVoucherById(String id) {

        ModelMap map=new ModelMap();
        try
        {
            if(Tools.notEmpty(id)){
                
                map.put("result",expDecorateUserVoucherService.deleteExpDecorateUserVoucher(id));
            }  
        }
        catch (Exception e)
        {
            log.error("Controller Error ExpDecorateUserVoucherController-> deleteExpDecorateUserVoucherById  " + e.getMessage());
        }
 
        return map;
    }


}

