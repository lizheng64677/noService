package com.suyin.common;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


/**
 * 文件上传，
 * 浏览文件夹，
 * 生成验证码
 * @author Administrator
 *
 */

public class FileUploadHelper {
    private static Logger log = Logger.getLogger(FileUploadHelper.class);

    /**
     * 这里是上传files 一些日常的文件 如xls等
     * @param imgFile
     * @param request
     * @return
     */
    public String uploadFiles(@RequestParam("imgFile")MultipartFile[] imgFile,HttpServletRequest request) {
        log.info("this is files upload begin");
        MultipartFile file=null;
        JSONObject jso = new JSONObject();
        if(imgFile.length==1) file=imgFile[0];
        if(imgFile.length>1) {
            log.info("sorry, but this controller only allow to upload one file one time"); 
            jso.put("error", 1);
            jso.put("msgId", "1");
            jso.put("msg", "sorry, but this controller only allow to upload one file one time");
            return jso.toString();
        }
        if(file.isEmpty()) {
            log.info("sorry, but you upload a empty file"); 
            jso.put("error", 1);
            jso.put("msgId", "2");
            jso.put("msg", "sorry, but you upload a empty file");
            return jso.toString();          
        }

        if(!this.checkFilesExtension(file.getOriginalFilename())) {
            log.info("sorry, but you upload a empty file"); 
            jso.put("error", 1);
            jso.put("msgId", "3");
            jso.put("msg", "sorry, but the extension of the file is invalid");
            return jso.toString();              
        }
        try {
            String saveUrlRoot =this.getRootUrl(request);
            String savePathRoot=this.getRootPath(request);
            log.info("root path is "+savePathRoot);
            File directory=new File(savePathRoot);
            if(!directory.exists())
                directory.mkdirs();
            String newName=this.getNewFile( file.getOriginalFilename());
            File newFile=new File(savePathRoot,newName);
            log.info("save the file in folder: "+savePathRoot);
            FileUtils.copyInputStreamToFile(file.getInputStream(),newFile);
            jso.put("error", 0);
            jso.put("url",newFile.toString());

        } catch (IOException e) {
            log.error("exception occurs when uploading");
            log.error(e.getMessage());
            jso.put("error", 1);
            jso.put("msgId", "4");
            jso.put("msg", "sorry, but some unknown exception happens");
        }
        log.info("this is file upload end");
        return jso.toString();
    }


    /**
     * 这里是一个特殊的图像文件上传的方法
     * 与其他的上传有返回区别
     * 差别与kindeditor 中 imgas.js 返回各参数相结合
     * @param imgFile
     * @param request
     * @return
     */
    public String upload(@RequestParam("imgFile")MultipartFile[] imgFile,HttpServletRequest request) {
        log.info("this is file upload begin");
        MultipartFile file=null;
        JSONObject jso = new JSONObject();
        if(imgFile.length==1) file=imgFile[0];
        if(imgFile.length>1) {
            log.info("sorry, but this controller only allow to upload one file one time"); 
            jso.put("error", 1);
            jso.put("msgId", "1");
            jso.put("msg", "sorry, but this controller only allow to upload one file one time");
            return jso.toString();
        }
        if(file.isEmpty()) {
            log.info("sorry, but you upload a empty file"); 
            jso.put("error", 1);
            jso.put("msgId", "2");
            jso.put("msg", "sorry, but you upload a empty file");
            return jso.toString();			
        }

        if(!this.checkExtension(file.getOriginalFilename())) {
            log.info("sorry, but you upload a empty file"); 
            jso.put("error", 1);
            jso.put("msgId", "3");
            jso.put("msg", "sorry, but the extension of the file is invalid");
            return jso.toString();				
        }
        try {
            String saveUrlRoot =this.getRootUrl(request);
            String savePathRoot=this.getRootPath(request);
            log.info("root path is "+savePathRoot);
            File directory=new File(savePathRoot);
            if(!directory.exists())
                directory.mkdirs();
            String newName=this.getNewFile( file.getOriginalFilename());
            File newFile=new File(savePathRoot,newName);
            log.info("save the file in folder: "+savePathRoot);
            FileUtils.copyInputStreamToFile(file.getInputStream(),newFile);
            jso.put("error", 0);
            jso.put("url", thumbNail(newFile,saveUrlRoot)+";"+savePathRoot+newName+";"+newName+";"+file.getSize());
        } catch (IOException e) {
            log.error("exception occurs when uploading");
            log.error(e.getMessage());
            jso.put("error", 1);
            jso.put("msgId", "4");
            jso.put("msg", "sorry, but some unknown exception happens");
        }
        log.info("this is file upload end");
        return jso.toString();
    }

    /**
     * 是否需要生成缩略图，如果要，就生成，并返回缩略图的文件名
     * 默认不需要生成缩略图，直接返回原来的文件名
     * @param image
     * @return
     */
    protected String thumbNail(File image,String saveUrlRoot) {
        return saveUrlRoot+image.getName();
    }

    /**
     * 获得文件的url，可以在子类中重写方法
     * @param request
     * @return
     */
    protected String getRootUrl(HttpServletRequest request) {
        return request.getContextPath()+"/outImages/";
    }

    /**
     * 获得文件存储的根目录，可以在子类中重写方法以获得不同的地址
     * @param request
     * @return
     */
    protected String getRootPath(HttpServletRequest request) {
        return request.getSession().getServletContext().getRealPath("/outImages");
    }

    /**
     * 改写文件名，默认使用时间加随机数的算法，
     * 可以在子类中重写方法以获得不同的文件名
     * @param oldFileName
     * @return
     */
    protected String getNewFile(String oldFileName) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        return df.format(new Date()) + "_" + new Random().nextInt(1000) + "."+this.getExtension(oldFileName);
    }

    /**
     * 判断后缀名是否合法，可以重写
     * @param fileName
     * @return
     */
    protected boolean checkExtension(String fileName) {
        return ArrayUtils.contains(this.getAllowedFileTypes(), this.getExtension(fileName));
    }

    /**
     * 判断后缀名是否合法，可以重写
     * @param fileName
     * @return
     */
    protected boolean checkFilesExtension(String fileName) {
        return ArrayUtils.contains(this.getAllowedFilesTypes(), this.getExtension(fileName));
    }

    /**
     * 获得文件后缀名
     * @param fileName
     * @return
     */
    protected String getExtension(String fileName) {
        int index=fileName.lastIndexOf(".");
        if(index<0)
            throw new RuntimeException("the extension of the file is invalid");
        return fileName.substring(index+1).toLowerCase();
    }


    /**
     * 
     * 图像后缀格式
     * @return 
     * @see
     */
    protected String[] getAllowedFileTypes() {
        return new String[] {"gif", "jpg", "jpeg", "bmp", "png"};  
    }

    /**
     * 
     * 文件后缀格式
     * @return 
     * @see
     */
    protected String[] getAllowedFilesTypes() {
        return new String[] {"xls", "xlsx"};  
    }

    public String fileManager(HttpServletRequest request)
    {
        String saveUrlRoot =this.getRootUrl(request);
        String savePathRoot=this.getRootPath(request);
        String[] fileTypes = new String[] {"gif", "jpg", "jpeg", "png", "bmp", "png"};


        // 排序形式，name or size or type
        String order = request.getParameter("order") != null ? request.getParameter("order").toLowerCase() : "name";

        File currentPathFile = new File(savePathRoot);
        if(!currentPathFile.exists())
            currentPathFile.mkdirs();
        List<Map<String, Object>> fileList = new ArrayList<Map<String, Object>>();
        for (File file : currentPathFile.listFiles())
        {
            Map<String, Object> hash = new HashMap<String, Object>();
            String fileName = file.getName();
            if (file.isDirectory())
            {
                hash.put("is_dir", true);
                hash.put("has_file", (file.listFiles() != null));
                hash.put("filesize", 0L);
                hash.put("is_photo", false);
                hash.put("filetype", "");
            }
            else if (file.isFile())
            {
                String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                hash.put("is_dir", false);
                hash.put("has_file", false);
                hash.put("filesize", file.length());
                hash.put("is_photo", Arrays.asList(fileTypes).contains(fileExt));
                hash.put("filetype", fileExt);
            }
            hash.put("filename", fileName);
            hash.put("datetime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
            fileList.add(hash);
        }
        if ("size".equals(order))
        {
            Collections.sort(fileList, new SizeComparator());
        }
        else if ("type".equals(order))
        {
            Collections.sort(fileList, new TypeComparator());
        }
        else
        {
            Collections.sort(fileList, new NameComparator());
        }
        JSONObject result = new JSONObject();
        result.put("moveup_dir_path", "");
        result.put("current_dir_path", "");
        result.put("current_url", saveUrlRoot);
        result.put("total_count", fileList.size());
        result.put("file_list", fileList);
        return result.toString();
    }

    class NameComparator implements Comparator<Map<String, Object>>
    {
        public int compare(Map<String, Object> hashA, Map<String, Object> hashB)
        {
            if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir")))
            {
                return -1;
            }
            else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir")))
            {
                return 1;
            }
            else
            {
                return ((String)hashA.get("filename")).compareTo((String)hashB.get("filename"));
            }
        }
    }

    class SizeComparator implements Comparator<Map<String, Object>>
    {
        public int compare(Map<String, Object> hashA, Map<String, Object> hashB)
        {
            if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir")))
            {
                return -1;
            }
            else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir")))
            {
                return 1;
            }
            else
            {
                if (((Long)hashA.get("filesize")) > ((Long)hashB.get("filesize")))
                {
                    return 1;
                }
                else if (((Long)hashA.get("filesize")) < ((Long)hashB.get("filesize")))
                {
                    return -1;
                }
                else
                {
                    return 0;
                }
            }
        }
    }

    class TypeComparator implements Comparator<Map<String, Object>>
    {
        public int compare(Map<String, Object> hashA, Map<String, Object> hashB)
        {
            if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir")))
            {
                return -1;
            }
            else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir")))
            {
                return 1;
            }
            else
            {
                return ((String)hashA.get("filetype")).compareTo((String)hashB.get("filetype"));
            }
        }
    }
}
