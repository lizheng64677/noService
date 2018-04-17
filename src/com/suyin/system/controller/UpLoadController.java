package com.suyin.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.suyin.system.service.AttachmentService;

/**
 * 文件上传
 * 
 * @author madara
 *
 */
@Controller
@RequestMapping(value = "upLoad")
public class UpLoadController {
	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private AttachmentService attachmentService;

	@RequestMapping(value = "")
	public String upLoad(HttpServletRequest request,HttpServletResponse response){
		try {
			
		} catch (Exception e) {
			log.error("Controller Error UpLoadController-> upLoad  "+ e.getMessage());
		}
		return null;
	}
	
}
