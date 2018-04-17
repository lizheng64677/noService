
package com.suyin.participator.controller;

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

import com.suyin.participator.model.Participator;
import com.suyin.participator.service.ParticipatorService;
import com.suyin.system.model.Page;
import com.suyin.system.util.Tools;



@Controller
@RequestMapping("/adminparticipator")
public class ParticipatorController{

    private final static Logger log=Logger.getLogger(ParticipatorController.class);
    @Autowired
    private ParticipatorService participatorService;

    /**
     * 首页
     * @return 
     * @see
     */
    @RequestMapping(value="/index")
    public ModelAndView index() {

        return new ModelAndView("participator/index");
    }


    /**
     * 读取列表
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value = "/list")
    public @ResponseBody Map<String, Object> findForParticipatorAll(HttpServletRequest request) {
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

            Participator  entityInfo=new Participator ();
            entityInfo.setPage(page);
            List<Participator > list=participatorService.findParticipatorByPage(entityInfo);
            map.put("rows",list); 
            map.put("total",entityInfo.getPage().getTotalResult()); 

        }
        catch (Exception e)
        {
            log.error("Controller Error ParticipatorController-> findParticipatorByWhere  " + e.getMessage());
        }

        return map;
    }




    /**
     * 跳转添加页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpAdd")
    public ModelAndView jumpParticipatorAdd(HttpServletRequest request) {
        ModelMap map=new ModelMap();

        return new ModelAndView("participator/save",map);
    }

    /**
     * 跳转修改页面 
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpEdit")
    public ModelAndView jumpParticipatorEdit(HttpServletRequest request) {
        ModelMap map=new ModelMap();
        try
        {

            if(Tools.notEmpty(request.getParameter("id"))){  
                
                Participator entity=new Participator();
                entity.setId(Integer.parseInt(request.getParameter("id")));
                entity=participatorService.findParticipatorById(entity);
                map.put("participator",entity);

            }
        }
        catch (Exception e)
        {

            log.error("Controller Error ParticipatorController-> jumpParticipatorEdit  " + e.getMessage());
        }
        return new ModelAndView("participator/edit",map);
    }

    /**
     * 信息保存
     * Description: <br>
     * @param 
     * @return 
     * @see
     */
    @RequestMapping(value = "/add")
    public @ResponseBody Map<String, Object> saveParticipatorInfo(Participator entity) {
        ModelMap map=new ModelMap();
        try
        {
            map.put("result",participatorService.addParticipator(entity));
        }
        catch (Exception e)
        {
            log.error("Controller Error ParticipatorController-> saveParticipatorInfo " + e.getMessage());
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
    public @ResponseBody Map<String, Object> updateParticipatorById(Participator entity) {
        ModelMap map=new ModelMap();
        try
        {            
            map.put("result",participatorService.updateParticipator(entity));
        }
        catch (Exception e)
        {
            log.error("Controller Error ParticipatorController-> updateParticipatorById  " + e.getMessage());
        }
        return map;
    }

    /**
     * 信息删除
     * @param 
     * @return
     */
    @RequestMapping(value = "/delete")
    public @ResponseBody Map<String, Object> deleteParticipatorById(String id) {

        ModelMap map=new ModelMap();
        try
        {
        	 String[] idArr = id.split(",");
        	 for (String ids:idArr) {
                 map.put("result",participatorService.deleteParticipator(ids));
			}
        }
        catch (Exception e)
        {
            log.error("Controller Error ParticipatorController-> deleteParticipatorById  " + e.getMessage());
        }
 
        return map;
    }


}

