package com.suyin.system.controller;

import java.util.HashMap;
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

import com.suyin.system.model.LoginUser;
import com.suyin.system.model.SystemRole;
import com.suyin.system.service.MenuService;
import com.suyin.system.service.PermissionService;
import com.suyin.system.service.RoleService;
import com.suyin.system.util.Tools;

/**   
 * @Title: PermissionController.java 
 * @Package com.suyin.commonConfig.controller 
 * @Description:
 * @author yyy   
 * @date 2015年7月10日 上午11:02:03 
 * @version V1.0   
 */
@Controller
@RequestMapping(value = "permission")
public class PermissionController {
	@SuppressWarnings("unused")
	private final Logger log=Logger.getLogger(PermissionController.class); 
	@Autowired
	private MenuService menuService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private PermissionService permissionService;

	@RequestMapping(value = "")
	public ModelAndView index() {
		return new ModelAndView("system/commonConfig/permission_Index");
	}
	/**
	 * 获取角色列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/synRoleList")
	public @ResponseBody Map<String, Object> synRoleList(HttpServletRequest request) {
		ModelMap map=new ModelMap();
		SystemRole systemRole=new SystemRole();
		List<SystemRole> list=roleService.findRole(systemRole);
		map.put("jsonData",list);
		return map;
	}
	
	/**
	 * 获取权限菜单列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/synMenuList")
	public @ResponseBody Map<String, Object> synMenuList(String roleId) {
		ModelMap map=new ModelMap();
		if(Tools.notEmpty(roleId)){
			List<Map<String,Object>> list=permissionService.findPermission(Integer.parseInt(roleId));
			map.put("jsonData",list);
		}
		return map;
	}
	
	/**
	 * 获取当前用户所选择的角色关联对象id获取对应的权限菜单列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getPerMenuList")
	public @ResponseBody Map<String, Object> getPerMenuList(HttpServletRequest request) {
		ModelMap map=new ModelMap();
		if(request.getSession().getAttribute("loginUser")!=null){
			LoginUser loginUser=(LoginUser)request.getSession().getAttribute("loginUser");
			if(Tools.notEmpty(request.getParameter("userRoleId"))){
				loginUser.setUserRoleId(Integer.parseInt(request.getParameter("userRoleId")));
			}
			map.put("jsonData1",permissionService.findMenuByUserId(loginUser.getUserRoleId()));
		}
		return map;
	}
	
	/**
	 * 新增角色权限
	 * @param systemResource
	 * @return
	 */
	@RequestMapping(value = "/addPermission")
	public @ResponseBody Map<String, Object> addPermission(String ids,String roleId) {
		ModelMap map=new ModelMap();
		if(Tools.isEmpty(ids)||Tools.isEmpty(roleId)){
			return map;
		}
		Map<String, Object> dataMap=new HashMap<String, Object>();
		dataMap.put("ids", ids);
		dataMap.put("roleId", roleId);
		map.put("result",permissionService.addPermission(dataMap));
		return map;
	}
	
}