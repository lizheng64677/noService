
package com.suyin.experience.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.json.JSONException;
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

import com.suyin.member.model.Member;
import com.suyin.member.service.MemberService;
import com.suyin.product.model.Product;
import com.suyin.product.service.ProductService;
import com.suyin.system.model.Page;
import com.suyin.system.model.SystemRole;
import com.suyin.system.model.SystemUser;
import com.suyin.system.util.Tools;

import java.util.*;
import com.suyin.experience.model.*;
import com.suyin.experience.service.*;


/**
 * 
 * 免费活动详情综合处理类 
 * 0人气式，1抽奖式，2兑换式，3试用式
 * @author lz
 * @version 2015-9-1
 * @see ExpDetailController
 * @since
 */
@Controller
@RequestMapping("/expdetail")
public class ExpDetailController{

    private final static Logger log=Logger.getLogger(ExpDetailController.class);
    @Autowired
    private ExpDetailService expDetailService; //活动详情
    @Autowired
    private MemberService memberService; //商家调用 
    @Autowired
    private ProductService productService; //产品信息 

    /**
     * 首页
     * @return 
     * @see
     */
    @RequestMapping(value="/index")
    public ModelAndView index(HttpServletRequest request,ExpDetail detail) {
        Map<String, Object>map=new HashMap<String, Object>();
        map.put("expId", detail.getExpId());
        return new ModelAndView("experience/detail/index",map);
    }


    /**
     * 读取列表
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value = "/list")
    public @ResponseBody Map<String, Object> findForExpDetailAll(HttpServletRequest request) {
        ModelMap map=new ModelMap();

        String pag = request.getParameter("page");
        String showCount = request.getParameter("rows");
        String expId=request.getParameter("expId");
        Page page = new Page();
        try
        {      
            if (null != pag && null != showCount) {
                page.setCurrentPage(Integer.parseInt(pag));
                page.setShowCount(Integer.parseInt(showCount));
            }

            ExpDetail  entityInfo=new ExpDetail ();
            entityInfo.setPage(page);
            entityInfo.setExpId(Integer.parseInt(expId));
            List<ExpDetail > list=expDetailService.findExpDetailByPage(entityInfo);
            map.put("rows",list); 
            map.put("total",entityInfo.getPage().getTotalResult()); 

        }
        catch (Exception e)
        {
            log.error("Controller Error ExpDetailController-> findExpDetailByWhere  " + e.getMessage());
        }

        return map;
    }



    /**
     * 
     * 根据商家id查询所含产品 
     * @param request
     * @param pro
     * @return 
     * @see
     */
    @RequestMapping(value="/findProductByMember")
    public @ResponseBody Map<String, Object> findProductByMember(HttpServletRequest request,Product pro){

        Map<String, Object>map=new HashMap<String, Object>();
        try
        {
            List list=productService.findProductByMemberId(pro);
            map.put("result",list);
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            log.error("Controller Error ExpDetailController-> findProductByMember  " + e.getMessage());
        }
        return map;

    }
    /**
     * 跳转添加页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpAdd")
    public ModelAndView jumpExpDetailAdd(HttpServletRequest request) {
        ModelMap map=new ModelMap();
        String expId=request.getParameter("expId");
        Member m=new Member();
        List list=memberService.findMemberByMapPage(m);
        map.put("memberList",list);
        map.put("expId", expId);
        return new ModelAndView("experience/detail/save",map);
    }


    /**
     * 跳转修改页面 
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpEdit")
    public ModelAndView jumpExpDetailEdit(HttpServletRequest request) {
        ModelMap map=new ModelMap();
        ExpDetail entity=new ExpDetail();
        Member m=new Member();
        try
        { 
            List list=memberService.findMemberByMapPage(m);
            map.put("memberList",list);
            if(Tools.notEmpty(request.getParameter("expId"))){ 

                entity.setExpId(Integer.parseInt(request.getParameter("expId")));               
                entity=expDetailService.findExpDetailWhereByExpId(entity);
                map.put("expdetail",entity);
            }else{                
                if(Tools.notEmpty(request.getParameter("id"))){  


                    entity.setExpDetailId(Integer.parseInt(request.getParameter("id")));               
                    entity=expDetailService.findExpDetailById(entity);
                    map.put("expdetail",entity);


                }
            }
        }
        catch (Exception e)
        {

            log.error("Controller Error ExpDetailController-> jumpExpDetailEdit  " + e.getMessage());
        }
        return new ModelAndView("experience/detail/edit",map);
    }

    /**
     * 信息保存
     * Description: <br>
     * @param 
     * @return 
     * @see
     */
    @RequestMapping(value = "/add")
    public @ResponseBody Map<String, Object> saveExpDetailInfo(ExpDetail entity) {
        ModelMap map=new ModelMap();
        try
        {

            map.put("result",expDetailService.addExpDetail(entity));
            //此处调用券的操作
        }
        catch (Exception e)
        {
            log.error("Controller Error ExpDetailController-> saveExpDetailInfo " + e.getMessage());
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
    public @ResponseBody Map<String, Object> updateExpDetailById(ExpDetail entity) {
        ModelMap map=new ModelMap();
        try
        {            
            map.put("result",expDetailService.updateExpDetail(entity));
        }
        catch (Exception e)
        {
            log.error("Controller Error ExpDetailController-> updateExpDetailById  " + e.getMessage());
        }
        return map;
    }

    /**
     * 信息删除
     * @param 
     * @return
     */
    @RequestMapping(value = "/delete")
    public @ResponseBody Map<String, Object> deleteExpDetailById(String id) {

        ModelMap map=new ModelMap();
        try
        {
            if(Tools.notEmpty(id)){

                map.put("result",expDetailService.deleteExpDetail(id));
            }  
        }
        catch (Exception e)
        {
            log.error("Controller Error ExpDetailController-> deleteExpDetailById  " + e.getMessage());
        }

        return map;
    }


    /**兑换式**/



    /**
     * 兑换式详情首页 
     * @return 
     * @see
     */
    @RequestMapping(value="/duiHuanIndex")
    public ModelAndView duiHuanIndex(HttpServletRequest request,ExpDetail detail) {
        Map<String, Object>map=new HashMap<String, Object>();
        map.put("expId", detail.getExpId());
        return new ModelAndView("experience/detail/index_duihuan",map);
    }

    /**
     * 兑换式添加页面跳转 
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpAddDuihuan")
    public ModelAndView jumpExpDuihuanDetailAdd(HttpServletRequest request) {
        ModelMap map=new ModelMap();
        String expId=request.getParameter("expId");
        Member m=new Member();
        List list=memberService.findMemberByPage(m);
        map.put("memberList",list);
        map.put("expId", expId);
        return new ModelAndView("experience/detail/save_duihuan",map);
    }
    /**
     * 跳转修改页面 
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpEditDuihuan")
    public ModelAndView jumpExpDuihuanDetailEdit(HttpServletRequest request) {
        ModelMap map=new ModelMap();
        ExpDetail entity=new ExpDetail();
        Member m=new Member();
        try
        {            
            List list=memberService.findMemberByMapPage(m);
            map.put("memberList",list);
            if(Tools.notEmpty(request.getParameter("expId"))){ 

                entity.setExpId(Integer.parseInt(request.getParameter("expId")));               
                entity=expDetailService.findExpDetailWhereByExpId(entity);
                map.put("expdetail",entity);
            }else{                
                if(Tools.notEmpty(request.getParameter("id"))){  


                    entity.setExpDetailId(Integer.parseInt(request.getParameter("id")));               
                    entity=expDetailService.findExpDetailById(entity);
                    map.put("expdetail",entity);


                }
            }
        }
        catch (Exception e)
        {

            log.error("Controller Error ExpDetailController-> jumpExpDuihuanDetailEdit  " + e.getMessage());
        }
        return new ModelAndView("experience/detail/edit_duihuan",map);
    }

}

