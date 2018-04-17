package com.suyin.system.controller;

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

import com.suyin.system.model.SystemResource;
import com.suyin.system.service.MenuService;
import com.suyin.system.util.Tools;

/**   
 * @Title: menuController.java 
 * @Package com.suyin.system.system.controller 
 * @Description:菜单配置管理
 * @author yyy   
 * @date 2015年7月8日 上午10:12:40 
 * @version V1.0   
 */
@Controller
@RequestMapping(value = "menu")
public class MenuController {
	private final Logger log=Logger.getLogger(MenuController.class); 
	@Autowired
	private MenuService menuService;

	@RequestMapping(value = "")
	public ModelAndView index() {
		return new ModelAndView("system/commonConfig/menu_Index");
	}
	/**
	 * 获取菜单列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/synMenuList")
	public @ResponseBody Map<String, Object> synMenuList(HttpServletRequest request) {
		ModelMap map=new ModelMap();
		SystemResource systemResource=new SystemResource();
		List<SystemResource> list=menuService.findMenu(systemResource);
		map.put("jsonData",list);
		return map;
	}
	
	/**
	 * 根据id查询菜单信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryMenuInfo")
	public @ResponseBody Map<String, Object> queryMenuInfo(HttpServletRequest request) {
		ModelMap map=new ModelMap();
		try {
			if(Tools.notEmpty(request.getParameter("id"))){
				SystemResource systemResource=new SystemResource();
				systemResource.setId(Integer.parseInt(request.getParameter("id")));
				map.put("menuInfo",menuService.findMenuById(systemResource));
			}
		} catch (NumberFormatException e) {
			log.error("menuController ->根据id查询菜单信息失败"+e.getMessage());
		}
		return map;
	}
	
	/**
	 * 新增菜单
	 * @param systemResource
	 * @return
	 */
	@RequestMapping(value = "/addMenu")
	public @ResponseBody Map<String, Object> addMenu(HttpServletRequest request,SystemResource systemResource) {
		ModelMap map=new ModelMap();
		systemResource.setNodeName(request.getParameter("tname"));
		map.put("result",menuService.addMenu(systemResource));
		return map;
	}
	
	/**
	 * 修改菜单
	 * @param systemResource
	 * @return
	 */
	@RequestMapping(value = "/updateMenu")
	public @ResponseBody Map<String, Object> updateMenu(HttpServletRequest request,SystemResource systemResource) {
		ModelMap map=new ModelMap();
		systemResource.setNodeName(request.getParameter("tname"));
		map.put("result",menuService.updateMenu(systemResource));
		return map;
	}
	
	/**
	 * 删除菜单
	 * @param systemResource
	 * @return
	 */
	@RequestMapping(value = "/deleteMenu")
	public @ResponseBody Map<String, Object> deleteMenu(String id) {
		ModelMap map=new ModelMap();
		if(Tools.notEmpty(id)){
			map.put("result",menuService.deleteMenu(Integer.parseInt(id)));
		}
		return map;
	}
}