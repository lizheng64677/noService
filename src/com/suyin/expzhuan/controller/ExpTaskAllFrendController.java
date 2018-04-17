
package com.suyin.expzhuan.controller;

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

import com.suyin.member.model.Member;
import com.suyin.member.service.MemberService;
import com.suyin.system.model.Page;
import com.suyin.system.model.SystemRole;
import com.suyin.system.model.SystemUser;
import com.suyin.system.util.Tools;

import java.util.*;

import com.suyin.city.model.City;
import com.suyin.city.service.CityService;
import com.suyin.common.service.ModuleNameService;
import com.suyin.experience.model.ExpDetail;
import com.suyin.experience.model.ExpPrototype;
import com.suyin.experience.model.Experience;
import com.suyin.experience.service.ExpPrototypeService;
import com.suyin.expzhuan.model.*;
import com.suyin.expzhuan.service.*;


/**
 * 赚金币任务活动综合管理_全民赚
 * @author lz
 * @version 2015-9-6
 * @see ExpTaskAllFrendController
 * @since
 */
@Controller
@RequestMapping("/expquanminzhuan")
public class ExpTaskAllFrendController{

    private final static Logger log=Logger.getLogger(ExpTaskAllFrendController.class);
    @Autowired
    private ExpTaskService expZhuanService;//赚金币活动
    @Autowired
    private CityService cityService;//城市
    @Autowired
    private ExpPrototypeService expPrototypeService;//动态属性配合
    @Autowired
    private ExpDictionaryService expDictionaryService; //问卷配置 
    @Autowired
    private ExpTaskPrototypeService expZhuanPrototypeService;//问卷答案配置
    @Autowired
    private ModuleNameService moduleNameService; //图片静态类型
    /**
     * 首页
     * @return 
     * @see
     */
    @RequestMapping(value="/index")
    public ModelAndView index() {

        return new ModelAndView("exptask/allfrend/index");
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
        Integer n=expZhuanService.updataSeqNum(map);
        if(n>0){
            model.put("message", "1");
        }else{
            model.put("message", "2");
        }
        return model;

    }
    /**
     * 读取赚金币活动列表
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value = "/list")
    public @ResponseBody Map<String, Object> findForExpZhuanAll(HttpServletRequest request) {
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

            ExpTask  entityInfo=new ExpTask ();
            entityInfo.setExpType(1);
            entityInfo.setPage(page);
            List<ExpTask > list=expZhuanService.findExpZhuanByPage(entityInfo);
            map.put("rows",list); 
            map.put("total",entityInfo.getPage().getTotalResult()); 

        }
        catch (Exception e)
        {
            log.error("Controller Error ExpZhuanQuanMinController-> findExpZhuanByWhere  " + e.getMessage());
        }

        return map;
    }
    /**
     * 
     * 活动开始
     * @return 
     * @see
     */
    @RequestMapping(value="/startExp")
    public @ResponseBody Map<String, Object> startExp(HttpServletRequest request,ExpTask exper){
        Map<String, Object>map=new HashMap<String, Object>();
        ExpPrototype entity=new ExpPrototype();
        entity.setExpId(exper.getExpId());
        //问卷调查 
        if(exper.getShowType()==1){

            ExpDictionary dictionary=new ExpDictionary();
            dictionary.setModuleType(1);
            dictionary.setExpId(exper.getExpId());
            List dictionaryList=expDictionaryService.findExpDictionaryByPage(dictionary);
            if(dictionaryList.size()>0){

                int n=expZhuanService.updateExpIsBegin(exper);
                if(n>0){
                    map.put("message", "success");
                }else{

                    map.put("message", "error"); 
                }

            }else{

                map.put("message", "invalidCjdc"); 
            }

        }else{

            int n=expZhuanService.updateExpIsBegin(exper);
            if(n>0){
                map.put("message", "success");
            }else{

                map.put("message", "error"); 
            }
        }

        return map;
    }


    /**
     * 
     * 备用动态属性方式 
     * @return 
     * @see
     */
    @RequestMapping(value="/startExp1")
    public @ResponseBody Map<String, Object> startExp1(HttpServletRequest request,ExpTask exper){
        Map<String, Object>map=new HashMap<String, Object>();
        ExpPrototype entity=new ExpPrototype();
        entity.setExpId(exper.getExpId());
        //动态属性是否存在 
        List list=expPrototypeService.findExpByExpIdList(entity);
        if(list.size()>0){

            if(exper.getShowType()==1){

                ExpDictionary dictionary=new ExpDictionary();
                dictionary.setModuleType(1);
                dictionary.setExpId(exper.getExpId());
                List dictionaryList=expDictionaryService.findExpDictionaryByPage(dictionary);
                if(dictionaryList.size()>0){
                    /**以下注释为 检验问卷调查答案是否配置**/
                    ExpTaskPrototype zhuan=new ExpTaskPrototype();
                    zhuan.setModuleType(1);//全民赚问卷调查 
                    zhuan.setExpId(exper.getExpId());
                    List proList=expZhuanPrototypeService.findIsProtoTypeListByExpId(zhuan);
                    if(proList.size()>0){ 

                        int n=expZhuanService.updateExpIsBegin(exper);
                        if(n>0){
                            map.put("message", "success");
                        }else{

                            map.put("message", "error"); 
                        }
                    }else{

                        map.put("message", "invalidResult"); 
                    }
                }else{

                    map.put("message", "invalidCjdc"); 
                }

            }else{

                int n=expZhuanService.updateExpIsBegin(exper);
                if(n>0){
                    map.put("message", "success");
                }else{

                    map.put("message", "error"); 
                }
            }
        }else{

            map.put("message", "invalidSetting");
        } 
        return map;
    }

    /**
     * 
     * 活动停止
     * @return 
     * @see
     */
    @RequestMapping(value="/stopExp")
    public @ResponseBody  Map<String, Object>  stopExp(HttpServletRequest request,ExpTask exper){
        Map<String, Object>map=new HashMap<String, Object>();

        try
        {
            if(null!=exper){
                int n=expZhuanService.updateExpIsBegin(exper);
                if(n>0){
                    map.put("message", "success");
                }else{

                    map.put("message", "error"); 
                }
            }else{

                map.put("message", "invalidParam");
            }
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            log.error("Controller Error ExpZhuanQuanMinController-> stopExp  " + e.getMessage());
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
    public @ResponseBody Map<String,Object> isBeg(HttpServletRequest request,ExpTask exper){
        Map<String, Object>  map = new HashMap<String, Object>();
        try
        {

            exper.setExpType(1);
            exper= expZhuanService.findExpZhuanById(exper);
            if(1==exper.getIsBegin()){
                map.put("message","1");

            }else{

                map.put("message","0");
            }
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            log.error("Controller Error ExpZhuanQuanMinController-> isBeg  " + e.getMessage());
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
        String showJsp=request.getParameter("showJsp");
        map.put("showJsp",showJsp);
        map.put("expId", exper.getExpId());
        City  city=new City();
        city.setUpid(0);
        List<City>list=cityService.findCity(city);
        map.put("cityList",list);
        return new ModelAndView("exptask/setting",map);
    }

    /**
     * 更新全民赚app下载 图片示例及app描述信息
     * @param entity
     * @return 
     * @see
     */
    @RequestMapping(value="/updateExpAppImagesInfo")
    public @ResponseBody ModelMap updateExpAppImagesInfo(HttpServletRequest request,ExpTask exper){
        ModelMap map=new ModelMap();
        exper.setAttachments(Tools.getAttachements(request.getParameter("allImages"), this.moduleNameService.EXP_APP_IMG));
        exper.setExpType(1);//0齐心赚，1全民赚
        int result=expZhuanService.updateExpAppImagesInfo(exper);
        if(result>0){

            map.put("message","success");
        }else{

            map.put("message","error");
        }
        return map;
    }
    /**
     * 跳转图片添加页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpAddImages")
    public ModelAndView jumpAddImages(HttpServletRequest request,ExpTask exper) {
        ModelMap map=new ModelMap();
        String expId=request.getParameter("expId");
        exper.setExpType(1);//0齐心赚，1全民赚
        exper.setExpId(Integer.parseInt(expId));
        exper= expZhuanService.findExpZhuanById(exper);
        map.put("exper", exper);
        //查询当前是否已经有图片及介绍信息是否存在
        return new ModelAndView("exptask/allfrend/saveImage",map);
    }

    /**
     * 跳转添加页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpAdd")
    public ModelAndView jumpExpZhuanAdd(HttpServletRequest request) {
        ModelMap map=new ModelMap();
        City  city=new City();
        city.setUpid(0);
        List<City>list=cityService.findCity(city);
        map.put("cityList",list);

        return new ModelAndView("exptask/allfrend/save",map);
    }

    /**
     * 跳转修改页面 
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpEdit")
    public ModelAndView jumpExpZhuanEdit(HttpServletRequest request) {
        ModelMap map=new ModelMap();
        try
        {

            if(Tools.notEmpty(request.getParameter("id"))){  

                ExpTask entity=new ExpTask();
                entity.setExpType(1);//0齐心赚，1全民赚 
                entity.setExpId(Integer.parseInt(request.getParameter("id")));
                entity=expZhuanService.findExpZhuanById(entity);
                map.put("expzhuan",entity);

                City  city=new City();
                city.setUpid(0);
                List<City>list=cityService.findCity(city);
                map.put("cityList",list);

            }
        }
        catch (Exception e)
        {

            log.error("Controller Error ExpZhuanQuanMinController-> jumpExpZhuanEdit  " + e.getMessage());
        }
        return new ModelAndView("exptask/allfrend/edit",map);
    }

    /**
     * 信息保存
     * Description: <br>
     * @param 
     * @return 
     * @see
     */
    @RequestMapping(value = "/add")
    public @ResponseBody Map<String, Object> saveExpZhuanInfo(ExpTask entity,HttpServletRequest request) {
        ModelMap map=new ModelMap();
        try
        {
            entity.setAttachments(Tools.getAttachements(request.getParameter("allImages"), this.moduleNameService.USER_EXP_ZHUAN));
            map.put("result",expZhuanService.addExpZhuan(entity));
        }
        catch (Exception e)
        {
            log.error("Controller Error ExpZhuanQuanMinController-> saveExpZhuanInfo " + e.getMessage());
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
    public @ResponseBody Map<String, Object> updateExpZhuanById(ExpTask entity,HttpServletRequest request) {
        ModelMap map=new ModelMap();
        try
        {            
            entity.setAttachments(Tools.getAttachements(request.getParameter("allImages"), this.moduleNameService.USER_EXP_ZHUAN));
            map.put("result",expZhuanService.updateExpZhuan(entity));
        }
        catch (Exception e)
        {
            log.error("Controller Error ExpZhuanQuanMinController-> updateExpZhuanById  " + e.getMessage());
        }
        return map;
    }

    /**
     * 信息删除
     * @param 
     * @return
     */
    @RequestMapping(value = "/delete")
    public @ResponseBody Map<String, Object> deleteExpZhuanById(String id) {

        ModelMap map=new ModelMap();
        ExpTask exper=new ExpTask();
        ExpDictionary dictionary=new ExpDictionary();
        try
        {
            if(Tools.notEmpty(id)){
                exper.setExpType(1);
                exper.setExpId(Integer.parseInt(id));
                exper= expZhuanService.findExpZhuanById(exper);
                if(1==exper.getIsBegin()){
                    map.put("message","1");

                }else{

                    expPrototypeService.deleteExpPrototype(id);//删除对应动态用户信息匹配属性
                    dictionary.setModuleType(1);
                    dictionary.setExpId(Integer.parseInt(id));
                    map.put("result",expDictionaryService.deleteExpUnionByExpId(dictionary));//删除活动相关信息
                    map.put("result",expZhuanService.deleteExpZhuan(id));//删除活动

                }
            }  
        }
        catch (Exception e)
        {
            log.error("Controller Error ExpZhuanQuanMinController-> deleteExpZhuanById  " + e.getMessage());
        }

        return map;
    }


}

