package com.suyin.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suyin.simple.model.EasyUITree;
import com.suyin.system.model.Page;
import com.suyin.system.model.SystemDictionary;
import com.suyin.system.service.SystemDictionaryService;


@Controller
@RequestMapping(value = "dataDictionary")
public class DataDictionaryController {
	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	SystemDictionaryService systemDictionaryService;
	
	@RequestMapping(value = "")
	public String index(HttpServletRequest request,HttpServletResponse response){
		try {
			
		} catch (Exception e) {
			log.error("Controller Error DataDictionaryController-> index  "+ e.getMessage());
		}
		return "system/dataDictionary/data_dictionary_index";
	}
	
	
	@RequestMapping(value = "treeIndex")
	public String treeIndex(HttpServletRequest request,HttpServletResponse response){
		try {
			
		} catch (Exception e) {
			log.error("Controller Error DataDictionaryController-> index  "+ e.getMessage());
		}
		return "system/dataDictionary/data_dictionary_tree_index";
	}
	
	
	@RequestMapping(value = "/synTreeList")
	public @ResponseBody List<SystemDictionary> synTreeList(
			HttpServletRequest request) {
		SystemDictionary systemDictionary=new SystemDictionary();
		systemDictionary.setParentId(Integer.parseInt(request.getParameter("parentId")));
		return systemDictionaryService.findSystemDictionary(systemDictionary);
	}
	
	
	/**
	 * 异步查询列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "list")
	public @ResponseBody Map<String, Object> findSystemDictionaryByPage(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		String pag = request.getParameter("page");
		String showCount = request.getParameter("rows");
		SystemDictionary systemDictionary=new SystemDictionary();
		Page page = new Page();
		if (null != pag && null != showCount) {
			page.setCurrentPage(Integer.parseInt(pag));
			page.setShowCount(Integer.parseInt(showCount));
		}if(null!=request.getParameter("dictionary_name")){
			systemDictionary.setDictionary_name(request.getParameter("dictionary_name"));
		}if(null!=request.getParameter("dictionary_code")){
			systemDictionary.setDictionary_code(request.getParameter("dictionary_code"));
		}
		
		systemDictionary.setPage(page);
		List<SystemDictionary> list=systemDictionaryService.findSystemDictionaryByPage(systemDictionary);
		map.put("rows",list); 
	    map.put("total",systemDictionary.getPage().getTotalResult()); 
		return map;
	}
	
	
	
	
	@RequestMapping(value = "doAddDataDictionary")
	public String doAddDataDictionary(HttpServletRequest request,HttpServletResponse response){
		try {
			request.setAttribute("parentId", request.getParameter("parentId"));
		} catch (Exception e) {
			log.error("Controller Error DataDictionaryController-> doAddDataDictionary  "+ e.getMessage());
		}
		return "system/dataDictionary/data_dictionary_add";
	}
	
	@RequestMapping(value = "doAddDataDictionaryDeatil")
	public String doAddDataDictionaryDeatil(HttpServletRequest request,HttpServletResponse response){
		try {
			request.setAttribute("parentId", request.getParameter("parentId"));
		} catch (Exception e) {
			log.error("Controller Error DataDictionaryController-> doAddDataDictionaryDeatil  "+ e.getMessage());
		}
		return "system/dataDictionary/data_dictionary_add_deatil";
	}
	
	
	@RequestMapping(value = "addDataDictionary")
    public @ResponseBody Map<String,Object> addDataDictionary(HttpServletRequest request,SystemDictionary systemDictionary)
    {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("message", "0");
        try
        {
            Integer i=systemDictionaryService.addSystemDictionary(systemDictionary);
            map.put("message",1);
        }
        catch (Exception e)
        {
            log.error("Controller Error DataDictionaryController-> addDataDictionary  " + e.getMessage());
        }
        return map;
    }
	
	
	@RequestMapping(value = "deleteDataDictionary")
    public @ResponseBody Map<String,Object> deleteDataDictionary(HttpServletRequest request,SystemDictionary systemDictionary)
    {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("message", "0");
        try
        {
            Integer i=systemDictionaryService.deleteSystemDictionary(systemDictionary);
            map.put("message",i);
        }
        catch (Exception e)
        {
            log.error("Controller Error DataDictionaryController-> deleteDataDictionary  " + e.getMessage());
        }
        return map;
    }
	
	@RequestMapping(value = "doUpdateDataDictionary")
	public String doUpdateDataDictionary(HttpServletRequest request,HttpServletResponse response){
		try {
				SystemDictionary systemDictionary=new SystemDictionary();
				systemDictionary.setDictionary_id(Integer.parseInt(request.getParameter("id")));
				request.setAttribute("dic", systemDictionaryService.getSystemDictionary(systemDictionary));
		} catch (Exception e) {
			log.error("Controller Error DataDictionaryController-> doUpdateDataDictionary  "+ e.getMessage());
		}
		return "system/dataDictionary/data_dictionary_update";
	}
	
	
	@RequestMapping(value = "updateDataDictionary")
    public @ResponseBody Map<String,Object> updateDataDictionary(HttpServletRequest request,SystemDictionary systemDictionary)
    {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("message", "0");
        try
        {
            Integer i=systemDictionaryService.updateSystemDictionary(systemDictionary);
            map.put("message",i);
        }
        catch (Exception e)
        {
            log.error("Controller Error DataDictionaryController-> updateDataDictionary  " + e.getMessage());
        }
        return map;
    }
}
