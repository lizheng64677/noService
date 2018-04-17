package com.suyin.system.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.suyin.system.model.LoginUser;
import com.suyin.system.service.PermissionService;
import com.suyin.system.service.RoleService;
import com.suyin.system.util.Tools;


@Controller
@RequestMapping(value = "/layout")
public class LayoutController {
	@Autowired
	private RoleService roleService;
	@Autowired
	PermissionService permissionService;
	
	/**
	 * 默认框架页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "")
	public ModelAndView index(HttpServletRequest request) {
		ModelMap map=new ModelMap();
		if(request.getSession().getAttribute("loginUser")!=null){
			LoginUser loginUser=(LoginUser)request.getSession().getAttribute("loginUser");
			List<Map<String, Object>> list=roleService.findRoleByUserId(loginUser.getUserId());
			map.put("roleList", list);
			if(Tools.notEmpty(request.getParameter("userRoleId"))){
				
				loginUser.setUserRoleId(Integer.parseInt(request.getParameter("userRoleId")));
				
				//当前角色对应权限设置
				loginUser.setMap(permissionService.findMenuByUserId(loginUser.getUserRoleId()));
				
				//切换角色应用id重新设置到loginUser中
				for(Map<String, Object> map_:list){
					if(String.valueOf(loginUser.getUserRoleId()).equals(String.valueOf(map_.get("user_role_id")))){
						loginUser.setApplicationId(map_.get("applicationId").toString());
						break;
					}
				}
				
				request.getSession().setAttribute("loginUser", loginUser);
			}
		}
		return new ModelAndView("system/layout/layout",map);
	}
	
	@RequestMapping(value = "/menu")
	public ModelAndView menu(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("system/layout/menu");
		return mv;
	}
	
	
	
	@RequestMapping(value = "/north")
	public ModelAndView north(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("system/layout/north");
		return mv;
	}
		
	
	@RequestMapping(value = "/south")
	public ModelAndView south(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("system/layout/south");
		return mv;
	}
}
