
<#assign className = table.className>   
<#assign classNameFirstLower = className?uncap_first>   
<#assign classNameLowerCase = className?lower_case>   
<#assign pkJavaType = table.idColumn.javaType>   

package ${basepackage}.controller;

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

import com.suyin.system.model.Page;
import com.suyin.system.model.SystemRole;
import com.suyin.system.model.SystemUser;
import com.suyin.system.util.Tools;

<#include "/java_imports.include">
@Controller
@RequestMapping("/${classNameLowerCase}")
public class ${className}Controller{

    private final static Logger log=Logger.getLogger(${className}Controller.class);
    @Autowired
    private ${className}Service ${classNameFirstLower}Service;

    /**
     * 首页
     * @return 
     * @see
     */
    @RequestMapping(value="/index")
    public ModelAndView index() {

        return new ModelAndView("${classNameLowerCase}/index");
    }


    /**
     * 读取列表
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value = "/list")
    public @ResponseBody Map<String, Object> findFor${className}All(HttpServletRequest request) {
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

            ${className}  entityInfo=new ${className} ();
            entityInfo.setPage(page);
            List<${className} > list=${classNameFirstLower}Service.find${className}ByPage(entityInfo);
            map.put("rows",list); 
            map.put("total",entityInfo.getPage().getTotalResult()); 

        }
        catch (Exception e)
        {
            log.error("Controller Error ${className}Controller-> find${className}ByWhere  " + e.getMessage());
        }

        return map;
    }




    /**
     * 跳转添加页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpAdd")
    public ModelAndView jump${className}Add(HttpServletRequest request) {
        ModelMap map=new ModelMap();

        return new ModelAndView("${classNameLowerCase}/save",map);
    }

    /**
     * 跳转修改页面 
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpEdit")
    public ModelAndView jump${className}Edit(HttpServletRequest request) {
        ModelMap map=new ModelMap();
        try
        {

            if(Tools.notEmpty(request.getParameter("id"))){  
                
                ${className} entity=new ${className}();
                <#list table.columns as column>
                <#if column.pk>
                entity.set${column.columnName}(Integer.parseInt(request.getParameter("id")));
                </#if>
                </#list>
                entity=${classNameFirstLower}Service.find${className}ById(entity);
                map.put("${classNameLowerCase}",entity);

            }
        }
        catch (Exception e)
        {

            log.error("Controller Error ${className}Controller-> jump${className}Edit  " + e.getMessage());
        }
        return new ModelAndView("${classNameLowerCase}/edit",map);
    }

    /**
     * 信息保存
     * Description: <br>
     * @param 
     * @return 
     * @see
     */
    @RequestMapping(value = "/add")
    public @ResponseBody Map<String, Object> save${className}Info(${className} entity) {
        ModelMap map=new ModelMap();
        try
        {
            
            map.put("result",${classNameFirstLower}Service.add${className}(entity));
        }
        catch (Exception e)
        {
            log.error("Controller Error ${className}Controller-> save${className}Info " + e.getMessage());
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
    public @ResponseBody Map<String, Object> update${className}ById(${className} entity) {
        ModelMap map=new ModelMap();
        try
        {            
            map.put("result",${classNameFirstLower}Service.update${className}(entity));
        }
        catch (Exception e)
        {
            log.error("Controller Error ${className}Controller-> update${className}ById  " + e.getMessage());
        }
        return map;
    }

    /**
     * 信息删除
     * @param 
     * @return
     */
    @RequestMapping(value = "/delete")
    public @ResponseBody Map<String, Object> delete${className}ById(String id) {

        ModelMap map=new ModelMap();
        try
        {
            if(Tools.notEmpty(id)){
                
                map.put("result",${classNameFirstLower}Service.delete${className}(id));
            }  
        }
        catch (Exception e)
        {
            log.error("Controller Error ${className}Controller-> delete${className}ById  " + e.getMessage());
        }
 
        return map;
    }


}

