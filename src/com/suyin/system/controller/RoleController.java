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

import com.suyin.system.model.SystemRole;
import com.suyin.system.service.RoleService;
import com.suyin.system.util.Tools;

/**   
 * @Title: RoleController.java 
 * @Package com.suyin.commonConfig.controller 
 * @Description:角色管理controller
 * @author yyy   
 * @date 2015年7月9日 下午4:27:44 
 * @version V1.0   
 */
@Controller
@RequestMapping(value="sysRole")
public class RoleController {

	private final Logger log=Logger.getLogger(RoleController.class); 
	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "")
	public ModelAndView index() {
		return new ModelAndView("system/commonConfig/role/role_Index");
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
		map.put("rows",list); 
		return map;
	}
	
	/**
	 * 进入新增或修改角色页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/gotoAddOrEditRolePage")
	public ModelAndView queryRoleInfo(HttpServletRequest request) {
		ModelMap map=new ModelMap();
		try {
			if(Tools.notEmpty(request.getParameter("id"))){
				SystemRole systemRole=new SystemRole();
				systemRole.setId(Integer.parseInt(request.getParameter("id")));
				map.put("role",roleService.findRoleById(systemRole));
			}
//			map.put("wxAppList", weixinAppService.findWeixinApp(new WxParam()));
		} catch (NumberFormatException e) {
			log.error("RoleController ->根据id查询角色信息失败"+e.getMessage());
		}
		return new ModelAndView("system/commonConfig/role/addOrEditRole",map);
	}
	
	/**
	 * 新增角色
	 * @param SystemRole
	 * @return
	 */
	@RequestMapping(value = "/addRole")
	public @ResponseBody Map<String, Object> addRole(SystemRole systemRole) {
		ModelMap map=new ModelMap();
		map.put("result",roleService.addRole(systemRole));
		return map;
	}
	
	/**
	 * 修改角色
	 * @param SystemRole
	 * @return
	 */
	@RequestMapping(value = "/updateRole")
	public @ResponseBody Map<String, Object> updateRole(SystemRole systemRole) {
		ModelMap map=new ModelMap();
		map.put("result",roleService.updateRole(systemRole));
		return map;
	}
	
	/**
	 * 删除角色
	 * @param SystemRole
	 * @return
	 */
	@RequestMapping(value = "/deleteRole")
	public @ResponseBody Map<String, Object> deleteRole(String id) {
		ModelMap map=new ModelMap();
		if(Tools.notEmpty(id)){
			map.put("result",roleService.deleteRole(id));
		}
		return map;
	}
}
