
package com.suyin.decorate.controller;

import java.util.HashMap;
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
import com.suyin.system.util.DateUtil;
import com.suyin.system.util.Tools;

import java.util.*;

import com.suyin.decorate.model.*;
import com.suyin.decorate.service.*;
import com.suyin.experience.model.Experience;



@Controller
@RequestMapping("/adminexpdecorate")
public class ExpDecorateController{

	private final static Logger log=Logger.getLogger(ExpDecorateController.class);
	@Autowired
	private ExpDecorateService expDecorateService;
	/**
	 * 信息删除
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/findExpInfoById")
	public @ResponseBody Map<String, Object> findExpInfoById(String id) {

		ModelMap map=new ModelMap();
		try
		{
			if(Tools.notEmpty(id)){
				ExpDecorate entity=expDecorateService.findExpInfoById(id);
				map.put("value",entity);
			}  
		}
		catch (Exception e)
		{
			log.error("Controller Error ExpDecorateController-> deleteExpDecorateById  " + e.getMessage());
		}

		return map;
	}
	/**
	 * 
	 * 活动开始
	 * @return 
	 * @see
	 */
	@RequestMapping(value="/stopExp")
	public @ResponseBody Map<String, Object> stopExp(HttpServletRequest request,ExpDecorate entity){
		Map<String, Object>map=new HashMap<String, Object>();
		int n=expDecorateService.updateExpStatus(entity);
		if(n>0){
			map.put("message", "success");
		}else{

			map.put("message", "error"); 
		}
		return map;
	}
	/**
	 * 
	 * 活动开始
	 * @return 
	 * @see
	 */
	@RequestMapping(value="/startExp")
	public @ResponseBody Map<String, Object> startExp(HttpServletRequest request,ExpDecorate entity){
		Map<String, Object>map=new HashMap<String, Object>();
		int n=expDecorateService.updateExpStatus(entity);
		if(n>0){
			map.put("message", "success");
		}else{

			map.put("message", "error"); 
		}
		return map;
	}
	/**
	 * 首页
	 * @return 
	 * @see
	 */
	@RequestMapping(value="/index")
	public ModelAndView index() {

		return new ModelAndView("expdecorate/index");
	}


	/**
	 * 读取列表
	 * @param request
	 * @return 
	 * @see
	 */
	@RequestMapping(value = "/list")
	public @ResponseBody Map<String, Object> findForExpDecorateAll(HttpServletRequest request) {
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

			ExpDecorate  entityInfo=new ExpDecorate ();
			entityInfo.setPage(page);
			List<ExpDecorate > list=expDecorateService.findExpDecorateByPage(entityInfo);
			map.put("rows",list); 
			map.put("total",entityInfo.getPage().getTotalResult()); 

		}
		catch (Exception e)
		{
			log.error("Controller Error ExpDecorateController-> findExpDecorateByWhere  " + e.getMessage());
		}

		return map;
	}




	/**
	 * 跳转添加页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/jumpAdd")
	public ModelAndView jumpExpDecorateAdd(HttpServletRequest request) {
		ModelMap map=new ModelMap();

		return new ModelAndView("expdecorate/save",map);
	}

	/**
	 * 跳转修改页面 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/jumpEdit")
	public ModelAndView jumpExpDecorateEdit(HttpServletRequest request) {
		ModelMap map=new ModelMap();
		try
		{

			if(Tools.notEmpty(request.getParameter("id"))){  

				ExpDecorate entity=new ExpDecorate();
				entity.setId(Integer.parseInt(request.getParameter("id")));
				entity=expDecorateService.findExpDecorateById(entity);
				map.put("expdecorate",entity);

			}
		}
		catch (Exception e)
		{

			log.error("Controller Error ExpDecorateController-> jumpExpDecorateEdit  " + e.getMessage());
		}
		return new ModelAndView("expdecorate/edit",map);
	}

	/**
	 * 信息保存
	 * Description: <br>
	 * @param 
	 * @return 
	 * @see
	 */
	@RequestMapping(value = "/add")
	public @ResponseBody Map<String, Object> saveExpDecorateInfo(ExpDecorate entity) {
		ModelMap map=new ModelMap();
		try
		{

			map.put("result",expDecorateService.addExpDecorate(entity));
		}
		catch (Exception e)
		{
			log.error("Controller Error ExpDecorateController-> saveExpDecorateInfo " + e.getMessage());
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
	public @ResponseBody Map<String, Object> updateExpDecorateById(ExpDecorate entity) {
		ModelMap map=new ModelMap();
		try
		{           
			map.put("result",expDecorateService.updateExpDecorate(entity));
		}
		catch (Exception e)
		{
			log.error("Controller Error ExpDecorateController-> updateExpDecorateById  " + e.getMessage());
		}
		return map;
	}

	/**
	 * 信息删除
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public @ResponseBody Map<String, Object> deleteExpDecorateById(String id) {

		ModelMap map=new ModelMap();
		try
		{
			if(Tools.notEmpty(id)){

				map.put("result",expDecorateService.deleteExpDecorate(id));
			}  
		}
		catch (Exception e)
		{
			log.error("Controller Error ExpDecorateController-> deleteExpDecorateById  " + e.getMessage());
		}

		return map;
	}


}

