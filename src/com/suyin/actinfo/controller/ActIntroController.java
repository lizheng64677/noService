package com.suyin.actinfo.controller;


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

import com.suyin.actinfo.model.ActIntro;
import com.suyin.actinfo.service.ActIntroService;
import com.suyin.system.model.Page;
import com.suyin.system.util.Tools;


@Controller
@RequestMapping("/actintro")
public class ActIntroController
{

    private final static Logger log = Logger.getLogger(ActIntroController.class);

    @Autowired
    private ActIntroService actIntroService;

    /**
     * 首页
     * @return 
     * @see
     */
    @RequestMapping(value = "/index")
    public ModelAndView index()
    {

        return new ModelAndView("actintro/index");
    }

    /**
     * 读取列表
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value = "/list")
    public @ResponseBody
    Map<String, Object> findForActIntroAll(HttpServletRequest request)
    {
        ModelMap map = new ModelMap();

        String pag = request.getParameter("page");
        String showCount = request.getParameter("rows");
        Page page = new Page();
        try
        {
            if (null != pag && null != showCount)
            {
                page.setCurrentPage(Integer.parseInt(pag));
                page.setShowCount(Integer.parseInt(showCount));
            }

            ActIntro entityInfo = new ActIntro();
            entityInfo.setPage(page);
            List<ActIntro> list = actIntroService.findActIntroByPage(entityInfo);
            map.put("rows", list);
            map.put("total", entityInfo.getPage().getTotalResult());

        }
        catch (Exception e)
        {
            log.error("Controller Error ActIntroController-> findActIntroByWhere  "
                      + e.getMessage());
        }

        return map;
    }

    /**
     * 跳转添加页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpAdd")
    public ModelAndView jumpActIntroAdd(HttpServletRequest request)
    {
        ModelMap map = new ModelMap();

        return new ModelAndView("actintro/save", map);
    }

    /**
     * 跳转修改页面 
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpEdit")
    public ModelAndView jumpActIntroEdit(HttpServletRequest request)
    {
        ModelMap map = new ModelMap();
        try
        {

            if (Tools.notEmpty(request.getParameter("id")))
            {

                ActIntro entity = new ActIntro();
                entity.setActIntroId(Integer.parseInt(request.getParameter("id")));
                entity = actIntroService.findActIntroById(entity);
                map.put("entity", entity);

            }
        }
        catch (Exception e)
        {

            log.error("Controller Error ActIntroController-> jumpActIntroEdit  " + e.getMessage());
        }
        return new ModelAndView("actintro/edit", map);
    }

    /**
     * 信息保存
     * Description: <br>
     * @param 
     * @return 
     * @see
     */
    @RequestMapping(value = "/add")
    public @ResponseBody
    Map<String, Object> saveActIntroInfo(ActIntro entity)
    {
        ModelMap map = new ModelMap();
        try
        {

            map.put("result", actIntroService.addActIntro(entity));
        }
        catch (Exception e)
        {
            log.error("Controller Error ActIntroController-> saveActIntroInfo " + e.getMessage());
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
    public @ResponseBody
    Map<String, Object> updateActIntroById(ActIntro entity)
    {
        ModelMap map = new ModelMap();
        try
        {
            map.put("result", actIntroService.updateActIntro(entity));
        }
        catch (Exception e)
        {
            log.error("Controller Error ActIntroController-> updateActIntroById  "
                      + e.getMessage());
        }
        return map;
    }

    /**
     * 信息删除
     * @param 
     * @return
     */
    @RequestMapping(value = "/delete")
    public @ResponseBody
    Map<String, Object> deleteActIntroById(String id)
    {

        ModelMap map = new ModelMap();
        try
        {
            if (Tools.notEmpty(id))
            {

                map.put("result", actIntroService.deleteActIntro(id));
            }
        }
        catch (Exception e)
        {
            log.error("Controller Error ActIntroController-> deleteActIntroById  "
                      + e.getMessage());
        }

        return map;
    }

}
