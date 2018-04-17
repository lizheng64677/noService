/*
 * 文件名：ExpCharsController.java
 * 版权：Copyright by www.isure.net
 * 描述：
 * 修改人：windows7
 * 修改时间：2015-12-17
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.suyin.experience.controller;

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
import com.suyin.experience.model.ExpChars;
import com.suyin.experience.service.ExpCharsService;
import com.suyin.system.model.Page;

/**
 * 免费活动的数据详情统计
 * @author lz
 * @version 2015-12-17
 * @see ExpCharsController
 * @since
 */
@Controller
@RequestMapping("/expChars")
public class ExpCharsController{
    private final static Logger log=Logger.getLogger(ExpCharsController.class);
    @Autowired
    private ExpCharsService expCharsService;

    /**
     * 
     * 轻松赚数据统计页面跳转
     * @param request
     * @return 
     * @see
     */
    @RequestMapping("/toQs")
    public ModelAndView toQs(HttpServletRequest request){
        ModelMap map=new ModelMap();

        return new ModelAndView("expchars/zhuan/qs_index",map);
    }
    /**
     * 
     * 帮我赚数据统计页面跳转
     * @param request
     * @return 
     * @see
     */
    @RequestMapping("/toBw")
    public ModelAndView toBw(HttpServletRequest request){
        ModelMap map=new ModelMap();

        return new ModelAndView("expchars/zhuan/bw_index",map);
    }


    /**
     * 
     * 查询轻松赚数据信息
     * @param request
     * @param expChars
     * @return 
     * @see
     */
    @RequestMapping(value="/findQZhuanList")
    public @ResponseBody Map<String,Object> findQZhuanList(HttpServletRequest request){
        ModelMap model=new ModelMap();
        String pag = request.getParameter("page");
        String showCount = request.getParameter("rows");
        Page page = new Page();
        try
        {      
            if (null != pag && null != showCount) {
                page.setCurrentPage(Integer.parseInt(pag));
                page.setShowCount(Integer.parseInt(showCount));
            }
            String clicentType=request.getParameter("clicentType");
            String expType=request.getParameter("expType");
            String beginTime=request.getParameter("beginTime");
            String endTime=request.getParameter("endTime");

            ExpChars  entityInfo=new ExpChars();
            entityInfo.setPage(page);    
            if(!"-1".equals(clicentType) && null!=clicentType){
                entityInfo.setClicentType(clicentType);
            }  
            if(!"".equals(expType) && null!=expType){
                entityInfo.setExpType(Integer.parseInt(expType));
            }
            if(null!=beginTime && !"".equals(beginTime)){

                entityInfo.setBengTime(beginTime);
            }
            if(null!=endTime && !"".equals(endTime)){

                entityInfo.setEndTime(endTime);
            }
            
            List<ExpChars> list=expCharsService.findQzhuanCharsByPage(entityInfo);
            model.put("rows",list); 
            model.put("total",entityInfo.getPage().getTotalResult()); 

        }
        catch (Exception e)
        {
            log.error("Controller Error ExpCharsController-> toPrizeList  " + e.getMessage());
        }

        return model;

    }
    /**
     * 
     * 查询全民赚信息
     * @param request
     * @param expChars
     * @return 
     * @see
     */
    @RequestMapping(value="/findZhuanList")
    public @ResponseBody Map<String,Object> findZhuanList(HttpServletRequest request){
        ModelMap model=new ModelMap();
        String pag = request.getParameter("page");
        String showCount = request.getParameter("rows");
        Page page = new Page();
        try
        {      
            if (null != pag && null != showCount) {
                page.setCurrentPage(Integer.parseInt(pag));
                page.setShowCount(Integer.parseInt(showCount));
            }
            String clicentType=request.getParameter("clicentType");
            String expType=request.getParameter("expType");
            String beginTime=request.getParameter("beginTime");
            String endTime=request.getParameter("endTime");
            ExpChars  entityInfo=new ExpChars();
            entityInfo.setPage(page);    
            if(!"-1".equals(clicentType) && null!=clicentType){
                entityInfo.setClicentType(clicentType);
            }  
            if(!"".equals(expType) && null!=expType){
                entityInfo.setExpType(Integer.parseInt(expType));
            }
            if(null!=beginTime && !"".equals(beginTime)){

                entityInfo.setBengTime(beginTime);
            }
            if(null!=endTime && !"".equals(endTime)){

                entityInfo.setEndTime(endTime);
            }
            List<ExpChars> list=expCharsService.findZhuanCharsByPage(entityInfo);
            model.put("rows",list); 
            model.put("total",entityInfo.getPage().getTotalResult()); 

        }
        catch (Exception e)
        {
            log.error("Controller Error ExpCharsController-> toPrizeList  " + e.getMessage());
        }

        return model;

    }
    /**
     * 抽奖数据统计页面跳转
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value="/toPrize")
    public ModelAndView toPrize(HttpServletRequest request){
        ModelMap model=new ModelMap();

        return new ModelAndView("expchars/prize/index",model);
    }

    /**
     * 
     * 查询抽奖数据信息
     * @param request
     * @param expChars
     * @return 
     * @see
     */
    @RequestMapping(value="/findPrizeList")
    public @ResponseBody Map<String,Object> findPrizeList(HttpServletRequest request){
        ModelMap model=new ModelMap();
        String pag = request.getParameter("page");
        String showCount = request.getParameter("rows");
        Page page = new Page();
        try
        {      
            if (null != pag && null != showCount) {
                page.setCurrentPage(Integer.parseInt(pag));
                page.setShowCount(Integer.parseInt(showCount));
            }
            String clicentType=request.getParameter("clicentType");
            String beginTime=request.getParameter("beginTime");
            String endTime=request.getParameter("endTime");
            ExpChars  entityInfo=new ExpChars();
            entityInfo.setPage(page);    
            if(!"-1".equals(clicentType) && null!=clicentType){
                entityInfo.setClicentType(clicentType);
            }        
            if(null!=beginTime && !"".equals(beginTime)){

                entityInfo.setBengTime(beginTime);
            }
            if(null!=endTime && !"".equals(endTime)){

                entityInfo.setEndTime(endTime);
            }
            List<ExpChars> list=expCharsService.findPrizeCharsByPage(entityInfo);
            model.put("rows",list); 
            model.put("total",entityInfo.getPage().getTotalResult()); 

        }
        catch (Exception e)
        {
            log.error("Controller Error ExpCharsController-> toPrizeList  " + e.getMessage());
        }

        return model;

    }

    /**
     * 人气数据统计页面跳转
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value="/toPop")
    public ModelAndView toPop(HttpServletRequest request){
        ModelMap model=new ModelMap();

        return new ModelAndView("expchars/popularity/index",model);
    }

    /**
     * 
     * 查询人气数据信息
     * @param request
     * @param expChars
     * @return 
     * @see
     */
    @RequestMapping(value="/findPopList")
    public @ResponseBody Map<String,Object> findPopList(HttpServletRequest request){
        ModelMap model=new ModelMap();
        String pag = request.getParameter("page");
        String showCount = request.getParameter("rows");
        Page page = new Page();
        try
        {      
            if (null != pag && null != showCount) {
                page.setCurrentPage(Integer.parseInt(pag));
                page.setShowCount(Integer.parseInt(showCount));
            }
            String clicentType=request.getParameter("clicentType");
            String beginTime=request.getParameter("beginTime");
            String endTime=request.getParameter("endTime");
            ExpChars  entityInfo=new ExpChars();
            entityInfo.setPage(page);    
            if(!"-1".equals(clicentType) && null!=clicentType){
                entityInfo.setClicentType(clicentType);
            }        
            if(null!=beginTime && !"".equals(beginTime)){

                entityInfo.setBengTime(beginTime);
            }
            if(null!=endTime && !"".equals(endTime)){

                entityInfo.setEndTime(endTime);
            }
            List<ExpChars> list=expCharsService.findPopCharsListByPage(entityInfo);
            model.put("rows",list); 
            model.put("total",entityInfo.getPage().getTotalResult()); 

        }
        catch (Exception e)
        {
            log.error("Controller Error ExpCharsController-> toPrizeList  " + e.getMessage());
        }

        return model;

    }

}
