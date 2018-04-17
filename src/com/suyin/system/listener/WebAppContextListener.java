package com.suyin.system.listener;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.support.WebApplicationContextUtils;




/**
 * 系统初始化监听
 * 
 * @author Yll
 * @version 2015-7-1
 * @see WebAppContextListener
 * @since
 */
public class WebAppContextListener implements ServletContextListener {

	

	public void contextDestroyed(ServletContextEvent arg0) {
	}

	public void contextInitialized(ServletContextEvent arg0) {
		
	}
	
	
}
