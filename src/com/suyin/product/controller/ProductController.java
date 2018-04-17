
package com.suyin.product.controller;

import java.util.List;
import java.util.Map;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

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

import com.suyin.system.model.Attachment;
import com.suyin.system.model.Page;
import com.suyin.system.model.SystemRole;
import com.suyin.system.model.SystemUser;
import com.suyin.system.util.Tools;

import java.util.*;

import com.suyin.common.service.ModuleNameService;
import com.suyin.member.model.Member;
import com.suyin.member.service.MemberService;
import com.suyin.product.model.*;
import com.suyin.product.service.*;



@Controller
@RequestMapping("/product")
public class ProductController{

    private final static Logger log=Logger.getLogger(ProductController.class);
    @Autowired
    private ProductService productService;
    @Autowired
    private  MemberService memberService;//商家信息
    @Autowired
    private ModuleNameService moduleNameService; //图片静态类型
    /**
     * 首页
     * @return 
     * @see
     */
    @RequestMapping(value="/index")
    public ModelAndView index() {

        return new ModelAndView("product/index");
    }


    /**
     * 读取列表
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value = "/list")
    public @ResponseBody Map<String, Object> findForProductAll(HttpServletRequest request) {
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

            Product  entityInfo=new Product ();
            entityInfo.setPage(page);
            List<Product > list=productService.findProductByPage(entityInfo);
            map.put("rows",list); 
            map.put("total",entityInfo.getPage().getTotalResult()); 

        }
        catch (Exception e)
        {
            log.error("Controller Error ProductController-> findProductByWhere  " + e.getMessage());
        }

        return map;
    }


    /**
     * 跳转添加页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpAdda")
    public @ResponseBody JSONArray jumpProductAdda(HttpServletRequest request) {
        ModelMap map=new ModelMap();
        Member member=new Member();
        List memberList=memberService.findMemberByMapPage(member);
        return JSONArray.fromObject(memberList);
    }

    /**
     * 跳转添加页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpAdd")
    public ModelAndView jumpProductAdd(HttpServletRequest request) {
        ModelMap map=new ModelMap();
        Member member=new Member();
        List memberList=memberService.findMemberByMapPage(member);
        map.put("memberList", memberList);
        return new ModelAndView("product/save",map);
    }

    /**
     * 跳转修改页面 
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpEdit")
    public ModelAndView jumpProductEdit(HttpServletRequest request) {
        ModelMap map=new ModelMap();
        try
        {

            if(Tools.notEmpty(request.getParameter("id"))){  

                Product entity=new Product();
                entity.setProId(Integer.parseInt(request.getParameter("id")));
                entity=productService.findProductById(entity);
                map.put("product",entity);
                Member member=new Member();
                List memberList=memberService.findMemberByMapPage(member);
                map.put("memberList", memberList);
                //附件表图片查询 
                Attachment attachment=new Attachment();
                attachment.setEntity(entity.getProId());
                attachment.setEntity_attribute("pro");
                List<Attachment> mentList=productService.findProAttachmentByEntityId(attachment);
                map.put("mentList", mentList);
            }
        }
        catch (Exception e)
        {

            log.error("Controller Error ProductController-> jumpProductEdit  " + e.getMessage());
        }
        return new ModelAndView("product/edit",map);
    }

    /**
     * 信息保存
     * Description: <br>
     * @param 
     * @return 
     * @see
     */
    @RequestMapping(value = "/add")
    public @ResponseBody Map<String, Object> saveProductInfo(Product entity,HttpServletRequest request) {
        ModelMap map=new ModelMap();
        try
        {
            if(null!=request.getParameterValues("allImages")){

                entity.setAttachments(Tools.getAttachements_arry(request.getParameterValues("allImages"), this.moduleNameService.PRO));
            }
            map.put("result",productService.addProduct(entity));
        }
        catch (Exception e)
        {
            log.error("Controller Error ProductController-> saveProductInfo " + e.getMessage());
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
    public @ResponseBody Map<String, Object> updateProductById(Product entity,HttpServletRequest request) {
        ModelMap map=new ModelMap();
        try
        {            
            if(null!=request.getParameterValues("allImages")){
                entity.setAttachments(Tools.getAttachements_arry(request.getParameterValues("allImages"), this.moduleNameService.PRO));
            }
            map.put("result",productService.updateProduct(entity));
        }
        catch (Exception e)
        {
            log.error("Controller Error ProductController-> updateProductById  " + e.getMessage());
        }
        return map;
    }

    /**
     * 信息删除
     * @param 
     * @return
     */
    @RequestMapping(value = "/delete")
    public @ResponseBody Map<String, Object> deleteProductById(String id) {

        ModelMap map=new ModelMap();
        try
        {
            if(Tools.notEmpty(id)){

                map.put("result",productService.deleteProduct(id));
            }  
        }
        catch (Exception e)
        {
            log.error("Controller Error ProductController-> deleteProductById  " + e.getMessage());
        }

        return map;
    }


}

