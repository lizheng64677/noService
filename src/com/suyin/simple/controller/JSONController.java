package com.suyin.simple.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.suyin.system.service.SystemDictionaryService;

@Controller
@RequestMapping("/json")
public class JSONController {

	@Autowired
	SystemDictionaryService systemDictionaryService;
	
	/**
     * 首页
     * @return 
     * @see
     */
    @RequestMapping(value="")
    public ModelAndView index() {

        return new ModelAndView("simple/index");
    }
    
    
   
    @RequestMapping(value="data")
    public @ResponseBody String ianda(HttpServletRequest request) {
    	String jsonp=request.getParameter("callback");
    	System.out.println(request.getParameter("dada"));
    	//System.out.println(jsonp);
        return jsonp+"([{'id':'1','name':'测试1'},{'id':'2','name':'测试2'}])";
    }
}
