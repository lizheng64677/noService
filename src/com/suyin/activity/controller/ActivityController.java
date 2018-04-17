
package com.suyin.activity.controller;

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

import com.sun.xml.internal.bind.v2.model.core.ID;
import com.suyin.activity.model.Activity;
import com.suyin.activity.service.ActivityService;
import com.suyin.system.model.Page;
import com.suyin.system.util.Tools;



@Controller
@RequestMapping("/adminactivity")
public class ActivityController{

    private final static Logger log=Logger.getLogger(ActivityController.class);
    @Autowired
    private ActivityService activityService;

    /**
     * 首页
     * @return 
     * @see
     */
    @RequestMapping(value="/index")
    public ModelAndView index() {

        return new ModelAndView("activity/index");
    }


    /**
     * 读取列表
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value = "/list")
    public @ResponseBody Map<String, Object> findForActivityAll(HttpServletRequest request) {
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

            Activity  entityInfo=new Activity ();
            entityInfo.setPage(page);
            List<Activity > list=activityService.findActivityByPage(entityInfo);
            map.put("rows",list); 
            map.put("total",entityInfo.getPage().getTotalResult()); 

        }
        catch (Exception e)
        {
            log.error("Controller Error ActivityController-> findActivityByWhere  " + e.getMessage());
        }

        return map;
    }




    /**
     * 跳转添加页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpAdd")
    public ModelAndView jumpActivityAdd(HttpServletRequest request) {
        ModelMap map=new ModelMap();

        return new ModelAndView("activity/save",map);
    }

    /**
     * 跳转修改页面 
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpEdit")
    public ModelAndView jumpActivityEdit(HttpServletRequest request) {
        ModelMap map=new ModelMap();
        try
        {
        	String acticvityId = request.getParameter("id");
            if(Tools.notEmpty(acticvityId)){  
                
                Activity entity=new Activity();
                entity.setId(Integer.parseInt(request.getParameter("id")));
                entity=activityService.findActivityById(entity);
                map.put("activity",entity);

            }
        }
        catch (Exception e)
        {

            log.error("Controller Error ActivityController-> jumpActivityEdit  " + e.getMessage());
        }
        return new ModelAndView("activity/edit",map);
    }

    /**
     * 信息保存
     * Description: <br>
     * @param 
     * @return 
     * @see
     */
    @RequestMapping(value = "/add")
    public @ResponseBody Map<String, Object> saveActivityInfo(Activity entity) {
        ModelMap map=new ModelMap();
        try
        {
            
            map.put("result",activityService.addActivity(entity));
        }
        catch (Exception e)
        {
            log.error("Controller Error ActivityController-> saveActivityInfo " + e.getMessage());
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
    public @ResponseBody Map<String, Object> updateActivityById(Activity entity) {
        ModelMap map=new ModelMap();
        try
        {            
            map.put("result",activityService.updateActivity(entity));
        }
        catch (Exception e)
        {
            log.error("Controller Error ActivityController-> updateActivityById  " + e.getMessage());
        }
        return map;
    }

    /**
     * 信息删除
     * @param 
     * @return
     */
    @RequestMapping(value = "/delete")
    public @ResponseBody Map<String, Object> deleteActivityById(String id) {

        ModelMap map=new ModelMap();
        try
        {
        	 String[] idArr = id.split(",");
        	 for (String ids:idArr) {
                map.put("result",activityService.deleteActivity(ids));
            }  
        }
        catch (Exception e)
        {
            log.error("Controller Error ActivityController-> deleteActivityById  " + e.getMessage());
        }
 
        return map;
    }


}

