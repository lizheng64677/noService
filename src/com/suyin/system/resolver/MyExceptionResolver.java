package com.suyin.system.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


public class MyExceptionResolver implements HandlerExceptionResolver{
    private Logger log = Logger.getLogger(this.getClass());
    
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
	    log.info("==============异常开始=============");
		ex.printStackTrace();
		log.info("==============异常结束=============");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("error");
		mv.addObject("exception", ex.toString().replaceAll("\n", "<br/>"));
		return mv;
	}

}
