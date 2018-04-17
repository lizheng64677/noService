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
import com.suyin.system.model.Page;
import com.suyin.system.model.SystemRole;
import com.suyin.system.model.SystemUser;
import com.suyin.system.service.RoleService;
import com.suyin.system.service.UserService;
import com.suyin.system.util.Md5Util;
import com.suyin.system.util.Tools;

/**   
 * @Title: UserController.java 
 * @Package com.suyin.commonConfig.controller 
 * @Description:用户管理controller
 * @author yyy   
 * @date 2015年7月9日 下午4:27:44 
 * @version V1.0   
 */
@Controller
@RequestMapping(value="sysUser")
public class UserController {

	private final Logger log=Logger.getLogger(UserController.class); 
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/index")
	public ModelAndView index() {
		return new ModelAndView("system/commonConfig/user/user_Index");
	}
	/**
	 * 获取用户列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/synUserList")
	public @ResponseBody Map<String, Object> synUserList(HttpServletRequest request) {
		ModelMap map=new ModelMap();
		String pag = request.getParameter("page");
		String showCount = request.getParameter("rows");
		Page page = new Page();
		if (null != pag && null != showCount) {
			page.setCurrentPage(Integer.parseInt(pag));
			page.setShowCount(Integer.parseInt(showCount));
		}
		SystemUser systemUser=new SystemUser();
		systemUser.setPage(page);
		List<SystemUser> list=userService.findUserByPage(systemUser);
		map.put("rows",list); 
		map.put("total",systemUser.getPage().getTotalResult()); 
		return map;
	}
	
	/**
	 * 进入新增或修改用户页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/gotoAddOrEditUserPage")
	public ModelAndView queryUserInfo(HttpServletRequest request) {
		ModelMap map=new ModelMap();
		try {
			if(Tools.notEmpty(request.getParameter("id"))){
				SystemUser systemUser=new SystemUser();
				systemUser.setId(Integer.parseInt(request.getParameter("id")));
				map.put("user",userService.findUserById(systemUser));
			
				map.put("roleList", roleService.findRoleForUser(Integer.parseInt(request.getParameter("id"))));
			}
			else{
				map.put("roleList", roleService.findRole(new SystemRole()));
			}
		} catch (NumberFormatException e) {
			log.error("UserController ->根据id查询用户信息失败"+e.getMessage());
		}
		return new ModelAndView("system/commonConfig/user/addOrEditUser",map);
	}
	
	/**
	 * 查看新增账号是否已存在
	 * @param SystemUser
	 * @return
	 */
	@RequestMapping(value = "/isExistLoginName")
	public @ResponseBody Map<String, Object> isExistLoginName(String loginName) {
		ModelMap map=new ModelMap();
		if(Tools.notEmpty(loginName)){
			SystemUser systemUser=new SystemUser();
			systemUser.setLoginName(loginName);
			SystemUser user=userService.findUserById(systemUser);
			map.put("isExist",user!=null?1:0);
		}
		return map;
	}
	/**
	 * 新增用户
	 * @param SystemUser
	 * @return
	 */
	@RequestMapping(value = "/addUser")
	public @ResponseBody Map<String, Object> addUser(SystemUser systemUser) {
		ModelMap map=new ModelMap();
		map.put("result",userService.addUser(systemUser));
		return map;
	}
	
	/**
	 * 修改用户
	 * @param SystemUser
	 * @return
	 */
	@RequestMapping(value = "/updateUser")
	public @ResponseBody Map<String, Object> updateUser(SystemUser systemUser) {
		ModelMap map=new ModelMap();
		map.put("result",userService.updateUser(systemUser));
		return map;
	}
	
	/**
	 * 删除用户
	 * @param SystemUser
	 * @return
	 */
	@RequestMapping(value = "/deleteUser")
	public @ResponseBody Map<String, Object> deleteUser(String id) {
		ModelMap map=new ModelMap();
		if(Tools.notEmpty(id)){
			map.put("result",userService.deleteUser(id));
		}
		return map;
	}
	/**
	 * 进入密码修改页面
	 * @return
	 */
	@RequestMapping(value = "/gotoEditPwd")
	public ModelAndView gotoEditPwd() {
		return new ModelAndView("system/commonConfig/user/update_pwd");
	}
	/**
	 * 修改用户密码
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/editPwd")
	public @ResponseBody Map<String, Object> editPwd(HttpServletRequest request,String pwd,String npwd) {
		ModelMap map=new ModelMap();
		Integer result=0;
		if(Tools.notEmpty(pwd)&&Tools.notEmpty(npwd)){
			SystemUser param=new SystemUser();
			if(request.getSession().getAttribute("loginUser")!=null){
				LoginUser loginUser=(LoginUser)request.getSession().getAttribute("loginUser");
				param.setId(loginUser.getUserId());
				SystemUser user=userService.findUserById(param);
				if(user!=null&&Md5Util.toMD5(pwd).equals(user.getLoginPwd())){
					user.setLoginPwd(Md5Util.toMD5(npwd));
					result=userService.updateUserPwd(user);
				}else{
					result=-1;//旧密码输入错误
				}
			}
		}
		map.put("result", result);
		return map;
	}
	/**
	 * 用户默认角色设置
	 * @param SystemUser
	 * @return
	 */
	@RequestMapping(value = "/setDefaultRole")
	public @ResponseBody Map<String, Object> setDefaultRole(HttpServletRequest request,String id) {
		ModelMap map=new ModelMap();
		if(request.getSession().getAttribute("loginUser")!=null){
			LoginUser loginUser=(LoginUser)request.getSession().getAttribute("loginUser");
			Map<String, Object> paramMap=new HashMap<String, Object>();
			paramMap.put("curUserRoleId", loginUser.getUserRoleId());
			Map<String, Object> roleMap=roleService.getUserDeaultRole(loginUser.getUserId());
			if(roleMap!=null){
				paramMap.put("defaultUserRoleId", roleMap.get("user_role_id"));
				map.put("result", userService.upadteUserDefaultRole(paramMap));
			}
		}
		return map;
	}
	
}
