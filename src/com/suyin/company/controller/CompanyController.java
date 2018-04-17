
package com.suyin.company.controller;

import java.util.ArrayList;
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
import com.suyin.system.model.Attachment;
import com.suyin.system.model.Page;
import com.suyin.system.model.SystemRole;
import com.suyin.system.model.SystemUser;
import com.suyin.system.util.Tools;

import org.apache.commons.lang.StringUtils;

import java.util.*;
import com.suyin.company.model.*;
import com.suyin.company.service.*;



@Controller
@RequestMapping("/company")
public class CompanyController{


    @Autowired
    private CompanyService companyService;
    
    @Autowired
    private ModuleNameService moduleNameService;
    
    private String moduleAlias=ModuleNameService.COMPANY;
    
    //重定向地址
    private final String LIST_ACTION = "redirect:/company";
    
    private Logger log=Logger.getLogger(CompanyController.class);
    /**
     * 首页
     * @return 
     * @see
     */
    @RequestMapping(value="/index")
    public ModelAndView index() {
        return new ModelAndView("company/index");
    }


    /**
     * 读取列表
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value = "/list")
    public @ResponseBody Map<String, Object> synCompanyList(HttpServletRequest request) {
        ModelMap map=new ModelMap();

        String pag = request.getParameter("page");
        String showCount = request.getParameter("rows");
        Page page = new Page();
        if (null != pag && null != showCount) {
            page.setCurrentPage(Integer.parseInt(pag));
            page.setShowCount(Integer.parseInt(showCount));
        }
        
        Company  entityInfo=new Company ();
        entityInfo.setPage(page);
        List<Company > list=companyService.findCompanyByPage(entityInfo);
        map.put("rows",list); 
        map.put("total",entityInfo.getPage().getTotalResult()); 
        return map;
    }


    /**
     * save or edit
     * @param request
     * @return
     */
    @RequestMapping(value = "/addOrEdit")
    public ModelAndView queryInfo(HttpServletRequest request) {
        try {
            if(StringUtils.isNotEmpty(request.getParameter("companyId"))){
                Company entity=new Company();
                entity.setCompanyId(Integer.parseInt(request.getParameter("companyId")));
                request.setAttribute("entity",this.companyService.findCompanyById(entity));
            }
        } catch (NumberFormatException e) {
            log.error("CompanyController ->根据id查询"+Company.TABLE_ALIAS+"信息失败"+e.getMessage());
        }     
        return new ModelAndView("company/addOrEdit");
    }
    
    /**
     * 添加
     */
    @RequestMapping(value = "/add")
    public @ResponseBody Map<String, Object> addCompany(Company entity,HttpServletRequest request) {
        ModelMap map=new ModelMap();
        entity.setModule(this.moduleNameService.getModuleName(moduleAlias));
        entity.setAttachments(Tools.getAttachements(request.getParameter("allImages"), this.moduleNameService.getModuleName(moduleAlias)));
        map.put("result",companyService.addCompany(entity));
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
    public @ResponseBody Map<String, Object> updateCompany(Company entity,HttpServletRequest request) {
        ModelMap map=new ModelMap();
        entity.setAttachments(Tools.getAttachements(request.getParameter("allImages"), this.moduleNameService.getModuleName(moduleAlias)));
        map.put("result",companyService.updateCompany(entity));
        return map;
    }

    /**
     * 信息删除
     * @param 
     * @return
     */
    @RequestMapping(value = "/delete")
    public @ResponseBody Map<String, Object> deleteCompany(String id) {

        ModelMap map=new ModelMap();
        if(StringUtils.isNotEmpty(id)){

            map.put("result",companyService.deleteCompany(id));
        }
        return map;
    }
}

