package com.suyin.common.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.suyin.common.FileUploadHelper;
import com.suyin.common.service.ModuleNameService;
import com.suyin.system.model.Attachment;
/**
 * 文件上传，
 * @author Administrator
 *
 */

@Controller
@RequestMapping("/file")
public class FileController extends FileUploadHelper {
    private static Logger log = Logger.getLogger(FileController.class);

    //浏览文件夹， 为了避免 attachment的多重引用问题， 关闭 fileManager 
    /*@RequestMapping(value="/fileManager")
	public @ResponseBody  String fileManager(HttpServletRequest request) {
		return super.fileManager(request);
	}*/

    /**
     * 文件上传
     */
    @RequestMapping(value="/uploadFiles")
    public @ResponseBody String uploadFiles(@RequestParam("imgFile")MultipartFile[] imgFile,HttpServletRequest request){

        return super.uploadFiles(imgFile, request);
    }
    /**
     * 每次添加一个图片，返回的url的格式是：url;savepath;filename;size, 所以前台要处理一下(image.js中已经添加了处理)
     * @param attach
     * @param imgFile
     * @param request
     * @return
     */
    @RequestMapping(value="/upload")
    public @ResponseBody String upload(Attachment attach, @RequestParam("imgFile")MultipartFile[] imgFile,HttpServletRequest request) {
        return super.upload(imgFile, request);
    }

    @Override
    protected String getRootPath(HttpServletRequest request) {
        //test
    	String url=request.getSession().getServletContext().getRealPath("/WEB-INF/resources/outImages")+"/"+this.getParentPath(request);
//    	System.out.println(url);
        return url;
//        正式
//        return "D:/hongmen/imgages/resources/outImages/"+this.getParentPath(request);
    }
    @Override
    protected String getRootUrl(HttpServletRequest request) {
    	String url=getLocalURL(request)+"/resources/outImages/"+this.getParentPath(request);
    	System.out.println(url);
        return url;
    }

    private String getLocalURL(HttpServletRequest request) {
        //test
//        return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
        //正式
        return request.getContextPath();
    }

    private String getParentPath(HttpServletRequest request) {
        return request.getParameter("module")+"/"+getDateString();
    }

    private String getDateString() {
        return new SimpleDateFormat("yyyyMMdd/").format(new Date());
    }
}
