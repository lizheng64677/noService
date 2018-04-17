
package com.suyin.experience.controller;

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

import com.suyin.product.service.ProductService;
import com.suyin.system.model.Page;
import com.suyin.system.model.SystemRole;
import com.suyin.system.model.SystemUser;
import com.suyin.system.util.Tools;

import java.util.*;

import com.suyin.city.model.City;
import com.suyin.city.service.CityService;
import com.suyin.common.service.ModuleNameService;
import com.suyin.experience.model.*;
import com.suyin.experience.service.*;


/**
 * 
 * 活动管理综合处理类_抽奖乐_抽奖式
 * @author lz
 * @version 2015-8-31
 * @see ExperiencePrizeController
 * @since
 */
@Controller
@RequestMapping("/experience")
public class ExperiencePrizeController{

    private final static Logger log=Logger.getLogger(ExperiencePrizeController.class);
    @Autowired
    private ExperienceService experienceService; //活动
    @Autowired
    private CityService cityService;//城市
    @Autowired
    private ExpPrototypeService expPrototypeService;//动态属性配合
    @Autowired
    private ExpDetailService expDetailService; //活动详情
    @Autowired
    private ModuleNameService moduleNameService; //图片静态类型
    /**
     * 首页
     * @return 
     * @see
     */
    @RequestMapping(value="/index")
    public ModelAndView index() {

        return new ModelAndView("experience/prize/index");
    }

    /**
     * 
     *  活动置顶操作
     * @return 
     * @see
     */
    @RequestMapping(value="/updataSeqNum")
    public @ResponseBody ModelMap updataSeqNum(HttpServletRequest request){
        ModelMap model=new ModelMap();
        String expId=request.getParameter("expId");
        String seqNum=request.getParameter("seq");
        Map<String,Object>map=new HashMap<String, Object>();
        map.put("expId", expId);
        map.put("seqNum", seqNum);
        Integer n=experienceService.updataSeqNum(map);
        if(n>0){
            model.put("message", "1");
        }else{
            model.put("message", "2");
        }
        return model;

    }
    /**
     * 读取列表
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value = "/list")
    public @ResponseBody Map<String, Object> findForExperienceAll(HttpServletRequest request) {
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

            Experience  entityInfo=new Experience ();
            entityInfo.setPage(page);
            entityInfo.setExpType(0); //0抽奖式，1人气式
            List<Experience > list=experienceService.findExperienceByPage(entityInfo);
            map.put("rows",list); 
            map.put("total",entityInfo.getPage().getTotalResult()); 

        }
        catch (Exception e)
        {
            log.error("Controller Error ExperienceController-> findExperienceByWhere  " + e.getMessage());
        }

        return map;
    }

    /**
     * 动态属性配置
     * @param request
     * @return
     */
    @RequestMapping(value = "/Setting")
    public ModelAndView jumpSetting(HttpServletRequest request,Experience exper) {
        ModelMap map=new ModelMap();
        String moduleType=request.getParameter("type");
        map.put("moduleType",moduleType);
        map.put("expId", exper.getExpId());
        City  city=new City();
        city.setUpid(0);
        List<City>list=cityService.findCity(city);
        map.put("cityList",list);
        return new ModelAndView("experience/prize/setting",map);
    }

    /**
     * 
     * 活动开始
     * @return 
     * @see
     */
    @RequestMapping(value="/startExp")
    public @ResponseBody Map<String, Object> startExp(HttpServletRequest request,Experience exper){
        Map<String, Object>map=new HashMap<String, Object>();

        ExpDetail detail=new ExpDetail();
        detail.setExpId(exper.getExpId());
        List detailList=expDetailService.findExpDetailByExpId(detail);
        if(detailList.size()>0){
            int n=experienceService.updateExpIsBegin(exper);
            if(n>0){
                map.put("message", "success");
            }else{

                map.put("message", "error"); 
            }
        }else{
            map.put("message", "invalidProNum");
        }

        return map;
    }

    /**
     * 
     * 判断是是否配置 动态属性
     * @param expId
     * @return 
     * @see
     */
    private int isSetting(int expId){
        String result="";
        ExpPrototype entity=new ExpPrototype();
        entity.setExpId(expId);
        List list=expPrototypeService.findExpByExpIdList(entity);//是否配置动态属性
        if(list.size()>0){

            result="success";
        }else{

            result="invalidSetting";
        }
        return list.size();
    }

    /**
     * 
     * 活动停止
     * @return 
     * @see
     */
    @RequestMapping(value="/stopExp")
    public @ResponseBody  Map<String, Object>  stopExp(HttpServletRequest request,Experience exper){
        Map<String, Object>map=new HashMap<String, Object>();

        if(null!=exper){
            int n=experienceService.updateExpIsBegin(exper);
            if(n>0){
                map.put("message", "success");
            }else{

                map.put("message", "error"); 
            }
        }else{

            map.put("message", "invalidParam");
        }
        return map;
    }

    /**
     * 跳转添加页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpAdd")
    public ModelAndView jumpExperienceAdd(HttpServletRequest request) {
        ModelMap map=new ModelMap();
        City  city=new City();
        city.setUpid(0);
        List<City>list=cityService.findCity(city);
        map.put("cityList",list);
        return new ModelAndView("experience/prize/save",map);
    }
    /**
     * 
     * 判断产品数量及活动状态 
     * @param request
     * @param exper
     * @return 
     * @see
     */
    @RequestMapping(value="/isBegProduct")
    public @ResponseBody Map<String,Object> isBegProduct(HttpServletRequest request,Experience exper){
        Map<String, Object>  map = new HashMap<String, Object>();
        ExpDetail detail=new ExpDetail();
        try
        {
            detail.setExpId(exper.getExpId());
            List detailList=expDetailService.findExpDetailByExpId(detail);
            if(detailList.size()>0){

                map.put("message","2"); //已经存在产品 ，请不要再次添加
            }else{

                exper.setExpType(2); //0抽奖式，1人气式,2兑换式
                exper= experienceService.findExperienceById(exper);
                if(1==exper.getIsBegin()){
                    map.put("message","1");

                }else{

                    map.put("message","0"); //可以修改
                }
            }
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            log.error("Controller Error ExperienceController-> isBeg  " + e.getMessage());
        }
        return map;

    }
    /**
     * 
     * 判断活动是否开始
     * @param request
     * @param exper
     * @return 
     * @see
     */
    @RequestMapping(value="/isBeg")
    public @ResponseBody Map<String,Object> isBeg(HttpServletRequest request,Experience exper){
        Map<String, Object>  map = new HashMap<String, Object>();
        try
        {
            exper.setExpType(0);//0抽奖式，1人气式 
            exper= experienceService.findExperienceById(exper);
            if(1==exper.getIsBegin()){
                map.put("message","1");

            }else{

                map.put("message","0");
            }
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            log.error("Controller Error ExperienceController-> isBeg  " + e.getMessage());
        }
        return map;

    }

    /**
     * 跳转修改页面 
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpEdit")
    public ModelAndView jumpExperienceEdit(HttpServletRequest request) {
        ModelMap map=new ModelMap();
        try
        {

            if(Tools.notEmpty(request.getParameter("id"))){  

                City  city=new City();
                city.setUpid(0);
                List<City>list=cityService.findCity(city);
                map.put("cityList",list);

                Experience entity=new Experience();
                entity.setExpId(Integer.parseInt(request.getParameter("id")));
                entity.setExpType(0); //0抽奖式，1人气式
                entity=experienceService.findExperienceById(entity);
                map.put("experience",entity);

            }
        }
        catch (Exception e)
        {

            log.error("Controller Error ExperienceController-> jumpExperienceEdit  " + e.getMessage());
        }
        return new ModelAndView("experience/prize/edit",map);
    }

    /**
     * 信息保存
     * Description: <br>
     * @param 
     * @return 
     * @see
     */
    @RequestMapping(value = "/add")
    public @ResponseBody Map<String, Object> saveExperienceInfo(Experience entity,HttpServletRequest request) {
        ModelMap map=new ModelMap();
        try
        {
            entity.setAttachments(Tools.getAttachements(request.getParameter("allImages"), this.moduleNameService.EXP));
            map.put("result",experienceService.addExperience(entity));
            //生成时间段
            List<Map<String,Object>>  times = Tools.getExpTime(entity);
            for(Map<String,Object> time:times){
                experienceService.addExperienceDetailTime(time);
            }
        }
        catch (Exception e)
        {
            log.error("Controller Error ExperienceController-> saveExperienceInfo " + e.getMessage());
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
    public @ResponseBody Map<String, Object> updateExperienceById(Experience entity,HttpServletRequest request) {
        ModelMap map=new ModelMap();
        try
        {            
            entity.setAttachments(Tools.getAttachements(request.getParameter("allImages"), this.moduleNameService.EXP));
            map.put("result",experienceService.updateExperience(entity));
        }
        catch (Exception e)
        {
            log.error("Controller Error ExperienceController-> updateExperienceById  " + e.getMessage());
        }
        return map;
    }

    /**
     * 信息删除
     * @param 
     * @return
     */
    @RequestMapping(value = "/delete")
    public @ResponseBody Map<String, Object> deleteExperienceById(String id) {

        ModelMap map=new ModelMap();
        try
        {
            if(Tools.notEmpty(id)){
                Experience exper=new Experience();
                exper.setExpType(0);//抽奖式
                exper.setExpId(Integer.parseInt(id));
                exper= experienceService.findExperienceById(exper);
                if(1==exper.getIsBegin()){
                    map.put("result","0");

                }else{
                    //expPrototypeService.deleteExpPrototype(id); //删除动态属性
                    expDetailService.deleteExpDetailByExpId(id);//删除活动详情 
                    map.put("result",experienceService.deleteExperience(id));
                }

            }  
        }
        catch (Exception e)
        {
            log.error("Controller Error ExperienceController-> deleteExperienceById  " + e.getMessage());
        }

        return map;
    }


}

