package com.suyin.system.listener;

import java.util.HashMap;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.suyin.system.model.LoginUser;

public class SessionListener implements HttpSessionListener{

	@SuppressWarnings("rawtypes")
	public static HashMap sessionUserMap=new HashMap();
	
	public void sessionCreated(HttpSessionEvent arg0) {
		System.out.println("session创建  "+arg0.getSession().getId());
		
	}

	@SuppressWarnings("static-access")
	public void sessionDestroyed(HttpSessionEvent arg0) {
		System.out.println("session销毁  "+arg0.getSession().getId());
		this.DelSession(arg0.getSession());
	}
	
	public static synchronized void DelSession(HttpSession session) {
		if (session != null) {
			if (session.getAttribute("loginUser") != null) {
				LoginUser loginUser = (LoginUser) session.getAttribute("loginUser");
				SessionListener.sessionUserMap.remove(loginUser.getUserId());
				session.invalidate(); 
			}
		}
	}
}
