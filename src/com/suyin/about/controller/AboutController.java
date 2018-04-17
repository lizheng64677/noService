
package com.suyin.about.controller;

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

import com.suyin.about.model.About;
import com.suyin.about.service.AboutService;
import com.suyin.common.service.ModuleNameService;
import com.suyin.system.model.Page;
import com.suyin.system.util.Tools;


@Controller
@RequestMapping("/about")
public class AboutController{

    @Autowired
    private AboutService aboutService;
    
    @Autowired
    private ModuleNameService moduleNameService;
    
    private String moduleAlias=ModuleNameService.ABOUT;
     
    private Logger log=Logger.getLogger(AboutController.class);
    
    /**
     * 首页
     * @return 
     * @see
     */
    @RequestMapping(value="/toindex")
    public ModelAndView toindex() {
        return new ModelAndView("about/index");
    }
    /**
     *  修改
     * @param entity
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateAbout")
    public @ResponseBody Map<String, Object> updateAbout(About entity,HttpServletRequest request) {
        ModelMap map=new ModelMap();
        entity.setAttachments(Tools.getAttachements(request.getParameter("allImages"), this.moduleNameService.getModuleName(moduleAlias)));
        map.put("result",aboutService.updateAbout(entity));
        return map;
    }
    
    /**
     * 添加
     * @param entity
     * @param request
     * @return
     */
    @RequestMapping(value = "/addAbout")
    public @ResponseBody Map<String, Object> addAbout(About entity, HttpServletRequest request) {	
        ModelMap map=new ModelMap();
       List<About> typeList = aboutService.findAboutByType(entity);
       if(null == typeList || typeList.size() ==0){
    	   entity.setModule(this.moduleNameService.getModuleName(moduleAlias));
           entity.setAttachments(Tools.getAttachements(request.getParameter("allImages"), this.moduleNameService.getModuleName(moduleAlias)));
           map.put("result",aboutService.addAbout(entity));
         
       }
       return map;
    }
    
    /**
     * 删除
     * @param id
     * @return
     */
       @RequestMapping(value = "/deleteAbout")
       public @ResponseBody Map<String, Object> deleteAbout(String id) {
           ModelMap map=new ModelMap();
           if(StringUtils.isNotEmpty(id)){   	   
               map.put("result",aboutService.deleteAbout(id));            
           }       
           return map;
       }
         
    /**
     * 读取列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/findAboutBypage")
    public @ResponseBody Map<String, Object> findAboutBypage(HttpServletRequest request) {
        ModelMap map=new ModelMap();

        String pag = request.getParameter("page");
        String showCount = request.getParameter("rows");
        Page page = new Page();
        if (null != pag && null != showCount) {
            page.setCurrentPage(Integer.parseInt(pag));
            page.setShowCount(Integer.parseInt(showCount));
        }
        About  entityInfo=new About ();
        entityInfo.setPage(page);
        if(StringUtils.isNotBlank(request.getParameter("type")))
        	entityInfo.setType(Integer.parseInt(request.getParameter("type")));
        List<About > list=aboutService.findAboutBypage(entityInfo);
        map.put("rows",list); 
        map.put("total",entityInfo.getPage().getTotalResult()); 
        return map;
    }
    
    /**
     * 按type搜索
     * @param request
     * @return
     */
	@RequestMapping(value = "/findAboutByType")
	public @ResponseBody List<About> findAboutByType(
			HttpServletRequest request) {
		try{
		     About entityInfo=new About ();
		     entityInfo.setType(Integer.parseInt(request.getParameter("type")));
		     return aboutService.findAboutByType(entityInfo);   
		}catch(Exception e){
			log.error("Controller Error AboutController ->findAboutByType "+e.getMessage());
	        e.printStackTrace();
	        throw new RuntimeException(e);
		}
		
	}
 
    /**
     * 根据id查找
     * @param request
     * @return
     */
    @RequestMapping(value = "/findAboutById")
    public ModelAndView findAboutById(HttpServletRequest request) {
    	 try {
             if(StringUtils.isNotEmpty(request.getParameter("aboutId"))){
                 About entity=new About();
                 entity.setAboutId(Integer.parseInt(request.getParameter("aboutId")));
                 request.setAttribute("entity",this.aboutService.findAboutById(entity));
             }
        } catch (Exception e) {
            log.error("Controller Error AboutController ->findAboutById "+e.getMessage());
        	e.printStackTrace();
			throw new RuntimeException(e);
        }     
        return new ModelAndView("about/addOrEdit");
    }
  
}

