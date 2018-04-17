
package com.suyin.theme.controller;

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

import com.suyin.common.service.ModuleNameService;
import com.suyin.system.model.Page;
import com.suyin.system.util.Tools;
import com.suyin.theme.model.Theme;
import com.suyin.theme.service.ThemeService;



@Controller
@RequestMapping("/theme")
public class ThemeController{


    @Autowired
    private ThemeService themeService;
    
    @Autowired
    private ModuleNameService moduleNameService;
    
    //重定向地址
    private final String LIST_ACTION = "redirect:/theme";
    
    private Logger log=Logger.getLogger(ThemeController.class);
    /**
     * 首页
     * @return 
     * @see
     */
    @RequestMapping(value="/index")
    public ModelAndView index() {
        return new ModelAndView("theme/index");
    }


    /**
     * 读取列表
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value = "/list")
    public @ResponseBody Map<String, Object> synThemeList(HttpServletRequest request) {
        ModelMap map=new ModelMap();

        String pag = request.getParameter("page");
        String showCount = request.getParameter("rows");
        Page page = new Page();
        if (null != pag && null != showCount) {
            page.setCurrentPage(Integer.parseInt(pag));
            page.setShowCount(Integer.parseInt(showCount));
        }
        
        Theme  entityInfo=new Theme ();
        entityInfo.setPage(page);
        List<Theme > list=themeService.findThemeByPage(entityInfo);
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
            if(StringUtils.isNotEmpty(request.getParameter("id"))){
                Theme entity=new Theme();
                entity.setId(Integer.parseInt(request.getParameter("id")));
                request.setAttribute("entity",this.themeService.findThemeById(entity));
            }
        } catch (NumberFormatException e) {
            log.error("ThemeController ->根据id查询"+Theme.TABLE_ALIAS+"信息失败"+e.getMessage());
        }     
        return new ModelAndView("theme/addOrEdit");
    }
    
    /**
     * 添加
     */
    @RequestMapping(value = "/add")
    public @ResponseBody Map<String, Object> addTheme(Theme entity,HttpServletRequest request) {
        ModelMap map=new ModelMap();
        entity.setModule(this.moduleNameService.getModuleName(ModuleNameService.THEME_DISCOUNT));
        entity.setAttachments(Tools.getAttachements(request.getParameter("allImages"), this.moduleNameService.getModuleName(ModuleNameService.THEME_DISCOUNT)));
        map.put("result",themeService.addTheme(entity));
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
    public @ResponseBody Map<String, Object> updateTheme(Theme entity,HttpServletRequest request) {
        ModelMap map=new ModelMap();
        entity.setAttachments(Tools.getAttachements(request.getParameter("allImages"), this.moduleNameService.getModuleName(ModuleNameService.THEME_DISCOUNT)));
        map.put("result",themeService.updateTheme(entity));
        return map;
    }

    /**
     * 信息删除
     * @param 
     * @return
     */
    @RequestMapping(value = "/delete")
    public @ResponseBody Map<String, Object> deleteTheme(String id) {

        ModelMap map=new ModelMap();
        if(StringUtils.isNotEmpty(id)){

            map.put("result",themeService.deleteTheme(id));
        }
        return map;
    }

}

