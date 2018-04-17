/*
 * 文件名：ExperiencepopularityController.java
 * 版权：Copyright by www.isure.net
 * 描述：
 * 修改人：windows7
 * 修改时间：2015-9-6
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.suyin.experience.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

import com.suyin.city.model.City;
import com.suyin.city.service.CityService;
import com.suyin.common.service.ModuleNameService;
import com.suyin.experience.model.ExpDetail;
import com.suyin.experience.model.ExpPrototype;
import com.suyin.experience.model.ExpTime;
import com.suyin.experience.model.Experience;
import com.suyin.experience.service.ExpDetailService;
import com.suyin.experience.service.ExpPrototypeService;
import com.suyin.experience.service.ExperienceService;
import com.suyin.system.model.Page;
import com.suyin.system.util.DateUtil;
import com.suyin.system.util.SendMessage;
import com.suyin.system.util.Tools;
import com.suyin.system.util.VoucherUtil;

/**
 * 
 * 活动管理综合处理类_人气式_分享人气排名
 * @author lz
 * @version 2015-8-31
 * @see ExperiencePrizeController
 * @since
 */
@Controller
@RequestMapping("/exprenqi")
public class ExperiencePopularityController
{
    private final static Logger log=Logger.getLogger(ExperiencePopularityController.class);
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

        return new ModelAndView("experience/popularity/index");
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
     * 
     * 跳转查询人气式用户列表页面
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value="/findForUserPopListIndex")
    public ModelAndView findForUserPopListIndex(HttpServletRequest request){
        ModelMap model=new ModelMap();
        String expId=request.getParameter("expId");
        String timeId=request.getParameter("timeId");
        model.put("expId", expId);
        model.put("timeId", timeId);
        return new ModelAndView("experience/popularity/voucherList",model);
    }
    /**
     * 
     * 跳转查询人气式查询可以参与活动人员列表
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value="/queryUserList")
    public ModelAndView queryUserList(HttpServletRequest request){
    	ModelMap model=new ModelMap();
    	String expId=request.getParameter("expId");
    	String timeId=request.getParameter("timeId");
    	String expType=request.getParameter("expType");
    	model.put("expId", expId);
    	model.put("timeId", timeId);
    	model.put("expType", expType);
    	return new ModelAndView("experience/popularity/userList",model);
    }
    /**
     * 
     * 跳转查询人气式查询可以参与活动人员列表
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value="/userPartList")
    public ModelAndView userPartList(HttpServletRequest request){
    	ModelMap model=new ModelMap();
    	String expId=request.getParameter("expId");
    	String timeId=request.getParameter("timeId");
    	String expType=request.getParameter("expType");
    	model.put("expId", expId);
    	model.put("timeId", timeId);
    	model.put("expType", expType);
    	return new ModelAndView("experience/popularity/userPartList",model);
    }
    
    /**
     * 查询单条订单信息
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value = "/oneOrder")
    public @ResponseBody ModelAndView oneOrder(HttpServletRequest request) {
    	ModelMap map=new ModelMap();
    	
    	String expId=request.getParameter("expId");
    	String timeId=request.getParameter("timeId");
    	String orderId=request.getParameter("orderId");
    	
    	try
    	{      
    		ExpTime entityInfo=new ExpTime();
    		entityInfo.setExpId(expId);
    		entityInfo.setTimeId(timeId);
    		entityInfo.setOrderId(orderId);
    		ExpTime expTime=expDetailService.queryOneOrder(entityInfo);
    		map.put("expTime",expTime); 
    		
    	}
    	catch (Exception e)
    	{
    		log.error("Controller Error ExperienceController-> findExperienceByWhere  " + e.getMessage());
    	}
    	
    	return new ModelAndView("experience/popularity/orderEdit",map);
    }
    /**
     * 修改虚拟订单信息
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value = "/updateOrder")
    public @ResponseBody Map<String, Object> updateOrder(HttpServletRequest request) {
    	ModelMap map=new ModelMap();
    	
    	String orderId=request.getParameter("orderId");
    	String shareNum=request.getParameter("shareNum");
    	String prizeStatus=request.getParameter("prizeStatus");
    	String popStatus=request.getParameter("popStatus");
    	try
    	{      
    		ExpTime entityInfo=new ExpTime();
    		entityInfo.setOrderId(orderId);
    		entityInfo.setShareNum(shareNum);
    		entityInfo.setPopStatus(popStatus);
    		entityInfo.setPrizeStatus(prizeStatus);
    		Integer result=expDetailService.updateOrder(entityInfo);
    		map.put("result",result); 
    		
    	}
    	catch (Exception e)
    	{
    		log.error("Controller Error ExperienceController-> findExperienceByWhere  " + e.getMessage());
    	}
    	
    	return map;
    }
    /**
     * 读取用户列表
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value = "/queryForUserList")
    public @ResponseBody Map<String, Object> queryForUserList(HttpServletRequest request) {
    	ModelMap map=new ModelMap();
    	
    	String pag = request.getParameter("page");
    	String showCount = request.getParameter("rows");
    	String expId=request.getParameter("expId");
    	String timeId=request.getParameter("timeId");
    	String expType=request.getParameter("expType");
    	Page page = new Page();
    	
    	try
    	{      
    		if (null != pag && null != showCount) {
    			page.setCurrentPage(Integer.parseInt(pag));
    			page.setShowCount(Integer.parseInt(showCount));
    		}
    		
    		ExpTime entityInfo=new ExpTime();
    		entityInfo.setPage(page);
    		entityInfo.setExpId(expId);
    		entityInfo.setTimeId(timeId);
    		List<ExpTime> list=expDetailService.queryForUserListByPage(entityInfo);
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
     * 查询虚拟用户参与订单列表
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value = "/queryUserPartList")
    public @ResponseBody Map<String, Object> queryUserPartList(HttpServletRequest request) {
    	ModelMap map=new ModelMap();
    	
    	String pag = request.getParameter("page");
    	String showCount = request.getParameter("rows");
    	String expId=request.getParameter("expId");
    	String timeId=request.getParameter("timeId");
    	String expType=request.getParameter("expType");
    	Page page = new Page();
    	
    	try
    	{      
    		if (null != pag && null != showCount) {
    			page.setCurrentPage(Integer.parseInt(pag));
    			page.setShowCount(Integer.parseInt(showCount));
    		}
    		
    		ExpTime entityInfo=new ExpTime();
    		entityInfo.setPage(page);
    		entityInfo.setExpId(expId);
    		entityInfo.setTimeId(timeId);
    		List<ExpTime> list=expDetailService.queryUserPartListByPage(entityInfo);
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
     * 读选择用户参与活动
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value = "/userPart")
    public @ResponseBody Map<String, Object> userPart(HttpServletRequest request) {
    	ModelMap map=new ModelMap();
    	String expId=request.getParameter("expId");
    	String timeId=request.getParameter("timeId");
    	String expType=request.getParameter("expType");
    	//String id1=request.getParameter("ids");
    	String[] ids=request.getParameter("userId").split(",");
    	try
    	{   
    		List<ExpTime> list = new ArrayList<ExpTime>();
    		for(String id : ids){
    			ExpTime exp = new ExpTime();
    			exp.setUserId(id);
    			exp.setExpId(expId);
    			exp.setTimeId(timeId);
    			exp.setExpType(expType);
    			list.add(exp);
    		}
    		Integer result= expDetailService.userPart(list);
    		if(result>0){
    			map.put("result", "success");
    		}
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    		map.put("result", "error");
    		log.error("Controller Error ExperienceController-> userPart  " + e.getMessage());
    	}
    	
    	return map;
    }
    
    
    
    /**
     * 选择时间段查询人气用户时，
     * 当前选择的查询时间段是否在合法的条件内
     * 条件合法进入下步操作，不合法做出相应提示
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value="/findForIsExpTimeStatus")
    public @ResponseBody Map<String,Object>findForIsExpTimeStatus(HttpServletRequest request){
        Map<String,Object> mapInfo=new HashMap<String, Object>();
        Map<String,Object>map=new HashMap<String,Object>();
        String timeId=request.getParameter("timeId");
        mapInfo.put("expTimeId",timeId);
        map.put("result",expDetailService.findForIsExpTimeStatus(mapInfo));
        return  map;
    }
    /**
     * 根据活动id查询对应时间段
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value = "/findForExpTime")
    public @ResponseBody Map<String, Object> findForExpTime(HttpServletRequest request) {
        ModelMap map=new ModelMap();
        Map<String, Object> mapInfo=new HashMap<String, Object>();
        try
        {
            String expId=request.getParameter("expId");
            mapInfo.put("expId", expId);
            map.put("result",expDetailService.findForExpTime(mapInfo));
        }
        catch (Exception e)
        {
            log.error("Controller Error CityController-> saveCityInfo " + e.getMessage());
        }
        return map;
    }

    /**
     * 读取列表
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value = "/voucherUserPoplist")
    public @ResponseBody Map<String, Object> voucherUserPoplist(HttpServletRequest request) {
        ModelMap map=new ModelMap();

        String pag = request.getParameter("page");
        String showCount = request.getParameter("rows");
        String expId=request.getParameter("expId");
        String timeId=request.getParameter("timeId");
        Page page = new Page();

        try
        {      
            if (null != pag && null != showCount) {
                page.setCurrentPage(Integer.parseInt(pag));
                page.setShowCount(Integer.parseInt(showCount));
            }

            ExpTime entityInfo=new ExpTime();
            entityInfo.setPage(page);
            entityInfo.setExpId(expId);
            entityInfo.setTimeId(timeId);
            List<ExpTime > list=expDetailService.findExpVoucherByPage(entityInfo);
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
     * 
     * 人气发券
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value="/sendUserPopInfo")
    public @ResponseBody Map<String, Object>sendUserPopInfo(HttpServletRequest request){
        Map<String, Object>map=new HashMap<String, Object>();
        String userIdArray=request.getParameter("userId");
        String expId=request.getParameter("expId");
        String timeId=request.getParameter("timeId");
        Map<String, Object>params=new HashMap<String, Object>();
        params.put("userIdArray", userIdArray);
        params.put("expId", expId);
        params.put("timeId", timeId);
        map.put("result",expDetailService.sendUserPopInfo(params));

        return map;
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
            entityInfo.setExpType(1); //0抽奖式，1人气式
            List<Experience > list=experienceService.findExperienceByPage(entityInfo);
            map.put("rows",list); 
            map.put("total",entityInfo.getPage().getTotalResult()); 

        }
        catch (Exception e)
        {
        	e.printStackTrace();
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
        return new ModelAndView("experience/popularity/setting",map);
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
    public int isSetting(int expId){
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
        return new ModelAndView("experience/popularity/save",map);
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

            exper.setExpType(1);
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
                entity.setExpType(1); //0抽奖式，1人气式
                entity=experienceService.findExperienceById(entity);
                map.put("experience",entity);

            }
        }
        catch (Exception e)
        {

            log.error("Controller Error ExperienceController-> jumpExperienceEdit  " + e.getMessage());
        }
        return new ModelAndView("experience/popularity/edit",map);
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
                exper.setExpType(1);//人气式
                exper.setExpId(Integer.parseInt(id));
                exper= experienceService.findExperienceById(exper);
                if(1==exper.getIsBegin()){
                    map.put("result","0");

                }else{
                    //                    expPrototypeService.deleteExpPrototype(id); //删除动态属性
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
