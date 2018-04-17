
package com.suyin.advs.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.suyin.advs.model.Advs;
import com.suyin.advs.service.AdvsService;
import com.suyin.common.service.ModuleNameService;
import com.suyin.system.model.Attachment;
import com.suyin.system.model.Page;
import com.suyin.system.util.Tools;



@Controller
@RequestMapping("/advs")
public class AdvsController{


    @Autowired
    private AdvsService advsService;
    
    @Autowired
    private ModuleNameService moduleNameService;
    
    
    
    
    //重定向地址
    private final String LIST_ACTION = "redirect:/advs";
    
    private Logger log=Logger.getLogger(AdvsController.class);
    /**
     * 首页
     * @return 
     * @see
     */
    @RequestMapping(value="/index")
    public ModelAndView index() {
        return new ModelAndView("advs/index");
    }


    /**
     * 读取列表
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value = "/list")
    public @ResponseBody Map<String, Object> synAdvsList(HttpServletRequest request) {
        ModelMap map=new ModelMap();

        String pag = request.getParameter("page");
        String showCount = request.getParameter("rows");
        Page page = new Page();
        if (null != pag && null != showCount) {
            page.setCurrentPage(Integer.parseInt(pag));
            page.setShowCount(Integer.parseInt(showCount));
        }
        
        Advs  entityInfo=new Advs ();
        entityInfo.setPage(page);
        List<Advs > list=advsService.findAdvsByPage(entityInfo);
        map.put("rows",list); 
        map.put("total",entityInfo.getPage().getTotalResult()); 
        return map;
    }


    /**
     * save or edit
     * @param request
     * @return
     */
    @RequestMapping(value = "/addOrEdit")
    public ModelAndView queryUserInfo(HttpServletRequest request) {
        try {
            if(StringUtils.isNotEmpty(request.getParameter("advId"))){
                Advs entity=new Advs();
                entity.setAdvId(Integer.parseInt(request.getParameter("advId")));
                request.setAttribute("entity",this.advsService.findAdvsById(entity));
            }
        } catch (NumberFormatException e) {
            log.error("AdvsController ->根据id查询"+Advs.TABLE_ALIAS+"信息失败"+e.getMessage());
        }     
        return new ModelAndView("advs/addOrEdit");
    }
    
    /**
     * 添加
     */
    @RequestMapping(value = "/add")
    public @ResponseBody Map<String, Object> addAdvs(Advs entity, HttpServletRequest request) {
        ModelMap map=new ModelMap();
        entity.setModule(this.moduleNameService.getModuleName(ModuleNameService.ADV));
        entity.setAttachments(Tools.getAttachements(request.getParameter("allImages"), this.moduleNameService.getModuleName(ModuleNameService.ADV)));
        map.put("result",advsService.addAdvs(entity));
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
    public @ResponseBody Map<String, Object> updateAdvs(Advs entity, HttpServletRequest request) {
        ModelMap map=new ModelMap();
        entity.setAttachments(Tools.getAttachements(request.getParameter("allImages"), this.moduleNameService.getModuleName(ModuleNameService.ADV)));
        map.put("result",advsService.updateAdvs(entity));
        return map;
    }

    /**
     * 信息删除
     * @param 
     * @return
     */
    @RequestMapping(value = "/delete")
    public @ResponseBody Map<String, Object> deleteAdvs(String id) {

        ModelMap map=new ModelMap();
        if(StringUtils.isNotEmpty(id)){

            map.put("result",advsService.deleteAdvs(id));
        }
        return map;
    }


}

