
package com.suyin.member.controller;

import java.util.HashMap;
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

import com.suyin.city.model.City;
import com.suyin.city.service.CityService;
import com.suyin.common.service.ModuleNameService;
import com.suyin.member.model.*;
import com.suyin.member.service.*;


/**
 * 商家业务管理
 * @author 
 * @version 2015-8-22
 * @see MemberController
 * @since
 */
@Controller
@RequestMapping("/member")
public class MemberController{

    private final static Logger log=Logger.getLogger(MemberController.class);
    @Autowired
    private MemberService memberService;
    @Autowired
    private CityService cityService;//城市
    @Autowired
    private RegionService regionService;//商圈
    @Autowired
    private CategoryService categoryService;//品类
    @Autowired
    private ModuleNameService moduleNameService; //图片静态类型
    /**
     * 首页
     * @return 
     * @see
     */
    @RequestMapping(value="/index")
    public ModelAndView index() {

        return new ModelAndView("member/member/index");
    }

    /**
     * 
     * 判断当前用户名是否已经存在
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value="/isUserName")
    public @ResponseBody Map<String,Object> isUserName(HttpServletRequest request){
        
        Map<String, Object> map=new HashMap<String, Object>();
        Map<String,Object> result=new HashMap<String, Object>();
        String userName=request.getParameter("value");
        map.put("userName", userName);
        map=memberService.isUserName(map);
        if(null!=map){
            result.put("message","invalidNumber");

        }else{

            result.put("message","success");
        }

        return result;

    }

    /**
     * 读取列表
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value = "/list")
    public @ResponseBody Map<String, Object> findForMemberAll(HttpServletRequest request) {
        ModelMap map=new ModelMap();

        String pag = request.getParameter("page");
        String showCount = request.getParameter("rows");
        String memberId=request.getParameter("memberId");
        Page page = new Page();
        try
        {
            if (null != pag && null != showCount) {
                page.setCurrentPage(Integer.parseInt(pag));
                page.setShowCount(Integer.parseInt(showCount));
            }

            Member  entityInfo=new Member ();
            entityInfo.setPage(page);
            if(memberId !=null && null!=memberId){
                entityInfo.setMemberId(Integer.parseInt(memberId));
            }
            List<Member > list=memberService.findMemberByPage(entityInfo);
            map.put("rows",list); 
            map.put("total",entityInfo.getPage().getTotalResult());
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            log.error("Controller Error CategoryController-> findForMemberAll  " + e.getMessage());
        } 
        return map;
    }


    /**
     * 跳转添加页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpAdd")
    public ModelAndView jumpMemberAdd(HttpServletRequest request) {
        ModelMap map=new ModelMap();
        City  city=new City();
        city.setUpid(0);
        List<City>list=cityService.findCity(city);
        List<Region>regList=regionService.findRegion(null);
        List<Category>cateList=categoryService.findCategory(null);
        map.put("cateList", cateList);
        map.put("regList", regList);
        map.put("cityList",list);
        map.put("parentId", request.getParameter("parentId"));
        return new ModelAndView("member/member/save",map);
    }

    /**
     * 跳转修改页面 
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpEdit")
    public ModelAndView jumpMemberEdit(ModelMap map,HttpServletRequest request) {

        try
        {
            if(Tools.notEmpty(request.getParameter("id"))){

                Member entity=new Member();
                entity.setMemberId(Integer.parseInt(request.getParameter("id")));
                entity=memberService.findMemberById(entity);
                map.put("member", entity);

                List<Region>regList=regionService.findRegion(null);
                List<Category>cateList=categoryService.findCategory(null);
                map.put("cateList", cateList);
                map.put("regList", regList);

                City  city=new City();
                city.setUpid(0);
                List<City>list=cityService.findCity(city);
                map.put("cityList",list);
            }

        }
        catch (Exception e)
        {
            log.error("Controller Error CategoryController-> jumpMemberEdit  " + e.getMessage());
        }

        return new ModelAndView("member/member/edit",map);
    }


    /**
     * 信息保存
     * Description: <br>
     * @param 
     * @return 
     * @see
     */
    @RequestMapping(value = "/add")
    public @ResponseBody Map<String, Object> saveCategoryInfo(Member entity,HttpServletRequest request) {
        ModelMap map=new ModelMap();
        try
        { 
            entity.setAttachments(Tools.getAttachements(request.getParameter("allImages"), this.moduleNameService.MEMBER));
            map.put("result",memberService.addMember(entity));
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
    public @ResponseBody Map<String, Object> updateMemberById(Member entity) {
        ModelMap map=new ModelMap();
        map.put("result",memberService.updateMember(entity));
        return map;
    }

    /**
     * 信息删除
     * @param 
     * @return
     */
    @RequestMapping(value = "/delete")
    public @ResponseBody Map<String, Object> deleteMemberById(String id) {

        ModelMap map=new ModelMap();
        if(Tools.notEmpty(id)){

            map.put("result",memberService.deleteMember(id));
        }
        return map;
    }


}

