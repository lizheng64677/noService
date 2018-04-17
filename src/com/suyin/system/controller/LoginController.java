package com.suyin.system.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;




import com.suyin.system.listener.SessionListener;
import com.suyin.system.model.LoginUser;
import com.suyin.system.model.SystemUser;
import com.suyin.system.service.PermissionService;
import com.suyin.system.service.RoleService;
import com.suyin.system.service.UserService;
import com.suyin.system.util.Md5Util;



@Controller
@RequestMapping(value = "/login")
public class LoginController {
	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;
	
	@Autowired
	PermissionService permissionService;
	
	/**
	 * 登录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/doLogin")
	public ModelAndView doLogin(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;
	}
	
	@RequestMapping(value = "")
	public ModelAndView loginRedirect(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("loginRedirect");
		return mv;
	}

	/**
	 * 登出系统
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/loginOut")
	public ModelAndView loginOut(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		request.getSession().removeAttribute("loginUser");
		request.getSession().invalidate();
		mv.setViewName("login");
		return mv;
	}

	@SuppressWarnings({ "static-access"})
	@RequestMapping(value = "/checkLogin")
	public @ResponseBody Map<String, Object> checkLogin(
			HttpServletRequest request,HttpSession session, String loginName, String passWord) {
		ModelMap map = new ModelMap();
		SystemUser sysUser = new SystemUser();
		try {
			sysUser.setLoginName(loginName);
			sysUser = userService.findUserById(sysUser);
			if (null==sysUser || null == sysUser.getId()) {
				map.put("msg", "用户不存在!");
			} else {
				if (!Md5Util.toMD5(passWord).equals(sysUser.getLoginPwd())) {
					map.put("msg", "密码错误");
				} else {
					LoginUser loginUser=this.preservationLogin(request, sysUser);
					request.getSession().setAttribute("loginUser",loginUser);
					map.put("msg", "ok");
					this.forceLogoutUser(session,loginUser);
					
				}
			}
		} catch (Exception e) {
			log.error("Controller Error LoginController-> login  "
					+ e.getMessage());
		}

		return map;
	}

	/**
	 * 登录存放loginUser信息
	 * 
	 * @param request
	 * @param sysUser
	 */
	private LoginUser preservationLogin(HttpServletRequest request,
			SystemUser sysUser) {
		LoginUser loginUser = new LoginUser();
		try {
			Map<String,Object> map=roleService.getUserDeaultRole(sysUser.getId());
			loginUser.setUserId(sysUser.getId());
			loginUser.setLoginName(sysUser.getNickName());
			loginUser.setApplicationId(map.get("applicationId").toString());
			loginUser.setUserRoleId(Integer.parseInt(map.get("user_role_id").toString()));
			loginUser.setMap(permissionService.findMenuByUserId(sysUser.getId()));
		} catch (Exception e) {
			log.error("Controller Error LoginController-> preservationLogin  "
					+ e.getMessage());
		}
		return loginUser;
	}
	
	@SuppressWarnings({"unchecked" })
	public static void forceLogoutUser(HttpSession session,LoginUser loginUser) {
		if ( null != SessionListener.sessionUserMap.get(loginUser.getUserId())) { 
			
            //当前账号已登录的用户session销毁，用户的信息从map中移除
            HttpSession hs = (HttpSession) SessionListener.sessionUserMap.get(loginUser.getUserId());
            hs.invalidate();
            
            //本次登录用户添加到map中                                                                    
            SessionListener.sessionUserMap.put(loginUser.getUserId(), session);                                                                               
       } else{      
            //以用户id为key键存入map中，以判断下一次登录的人
            SessionListener.sessionUserMap.put(loginUser.getUserId(), session);
       }
	}
}
