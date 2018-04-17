
package com.suyin.expzhuan.controller;

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

import com.suyin.expzhuan.model.ExpDictionary;
import com.suyin.expzhuan.service.ExpDictionaryService;
import com.suyin.system.model.Page;
import com.suyin.system.util.Tools;


/**
 * 活动管理_问卷调查_字典配置综合处理
 * @author lz
 * @version 2015-9-6
 * @see ExpDictionaryController
 * @since
 */
@Controller
@RequestMapping("/expdictionary")
public class ExpDictionaryController{

    private final static Logger log=Logger.getLogger(ExpDictionaryController.class);
    @Autowired
    private ExpDictionaryService expDictionaryService;

    /**
     * 首页
     * @return 
     * @see
     */
    @RequestMapping(value="/index")
    public ModelAndView index(HttpServletRequest request) {
        ModelMap map=new  ModelMap();
        String expId=request.getParameter("id");
        map.put("expId", expId);
        return new ModelAndView("exptask/dictionary/index",map);
    }


    /**
     * 读取列表
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value = "/list")
    public @ResponseBody Map<String, Object> findForExpDictionaryAll(HttpServletRequest request) {
        ModelMap map=new ModelMap();

        String pag = request.getParameter("page");
        String showCount = request.getParameter("rows");
        Page page = new Page();
        ExpDictionary  entityInfo=new ExpDictionary ();
        String expId=request.getParameter("expId");
        try
        {      
            if (null != pag && null != showCount) {
                page.setCurrentPage(Integer.parseInt(pag));
                page.setShowCount(Integer.parseInt(showCount));
            }
            if(null!=request.getParameter("dictionaryName")){
                entityInfo.setDictionaryName(request.getParameter("dictionaryName"));
            }
            if(null!=request.getParameter("dictionaryCode")){
                entityInfo.setDictionaryCode(request.getParameter("dictionaryCode"));
            }           
            entityInfo.setExpId(Integer.parseInt(expId));
            entityInfo.setPage(page);
            List<ExpDictionary > list=expDictionaryService.findExpDictionaryByPage(entityInfo);
            map.put("rows",list); 
            map.put("total",entityInfo.getPage().getTotalResult()); 

        }
        catch (Exception e)
        {
            log.error("Controller Error ExpDictionaryController-> findExpDictionaryByWhere  " + e.getMessage());
        }

        return map;
    }


    /**
     * 
     * 向下查询 _分层查询
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value = "/synTreeList")
    public @ResponseBody List<ExpDictionary> synTreeList(HttpServletRequest request) {

        List<ExpDictionary> list = null;
        ExpDictionary expDictionary=new ExpDictionary();
        try
        {
            String parentId=request.getParameter("parentId");
            expDictionary.setParentId(Integer.parseInt(parentId));
            list = expDictionaryService.findExpDictionary(expDictionary);
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            log.error("Controller Error ExpDictionaryController-> synTreeList " + e.getMessage());
        }
        return list;
    }



    /**
     * 跳转添加页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpAdd")
    public ModelAndView jumpExpDictionaryAdd(HttpServletRequest request) {

        ModelMap map=new ModelMap();
        try {
            String parentId=request.getParameter("parentId");
            String expId=request.getParameter("expId");
            map.put("expId",expId);
            map.put("parentId", parentId);
        } catch (Exception e) {
            log.error("Controller Error ExpDictionaryController-> jumpExpDictionaryAdd  "+ e.getMessage());
        }
        return new ModelAndView("exptask/dictionary/save",map);
    }



    /**
     * 跳转修改页面 
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpEdit")
    public ModelAndView jumpExpDictionaryEdit(HttpServletRequest request) {
        ModelMap map=new ModelMap();
        try
        {

            if(Tools.notEmpty(request.getParameter("id"))){  

                String expId=request.getParameter("expId");
                map.put("expId",expId);
                ExpDictionary entity=new ExpDictionary();
                entity.setDictionaryId(Integer.parseInt(request.getParameter("id")));
                map.put("dic",expDictionaryService.findExpDictionaryById(entity));
            }
        }
        catch (Exception e)
        {

            log.error("Controller Error ExpDictionaryController-> jumpExpDictionaryEdit  " + e.getMessage());
        }
        return new ModelAndView("exptask/dictionary/edit",map);
    }


    /**
     * 信息保存
     * Description: <br>
     * @param 
     * @return 
     * @see
     */
    @RequestMapping(value = "/add")
    public @ResponseBody Map<String, Object> saveExpDictionaryInfo(ExpDictionary entity) {
        ModelMap map=new ModelMap();

        try
        {
            Integer i=expDictionaryService.addExpDictionary(entity);
            map.put("message","success");
        }
        catch (Exception e)
        {
            log.error("Controller Error DataDictionaryController-> addDataDictionary  " + e.getMessage());
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
    public @ResponseBody Map<String, Object> updateExpDictionaryById(ExpDictionary entity) {
        ModelMap map=new ModelMap();
        try
        {            
            map.put("result",expDictionaryService.updateExpDictionary(entity));
            map.put("message","success");
        }
        catch (Exception e)
        {
            log.error("Controller Error ExpDictionaryController-> updateExpDictionaryById  " + e.getMessage());
        }
        return map;
    }


    /**
     * 信息删除
     * @param 
     * @return
     */
    @RequestMapping(value = "/delete")
    public @ResponseBody Map<String, Object> deleteExpDictionaryById(String id) {

        ModelMap map=new ModelMap();
        try
        {
            if(Tools.notEmpty(id)){

                map.put("message",expDictionaryService.deleteExpDictionary(id));
            }  
        }
        catch (Exception e)
        {
            log.error("Controller Error ExpDictionaryController-> deleteExpDictionaryById  " + e.getMessage());
        }

        return map;
    }


}

