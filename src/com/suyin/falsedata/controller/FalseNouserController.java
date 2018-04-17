
package com.suyin.falsedata.controller;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.suyin.system.model.Page;
import com.suyin.system.util.Tools;
import com.suyin.common.ExcelHelper;
import com.suyin.falsedata.model.*;
import com.suyin.falsedata.service.*;


/**
 * 
 * 虚拟用户处理
 * @author qzz
 * @version 2016-1-4
 * @see FalseNouserController
 * @since
 */
@Controller
@RequestMapping("/falsenouser")
public class FalseNouserController{

    private final static Logger log=Logger.getLogger(FalseNouserController.class);

    @Autowired
    private FalseNouserService falseNouserService;

    /**
     * 首页
     * @return 
     * @see
     */
    @RequestMapping(value="/index")
    public ModelAndView index() {

        return new ModelAndView("falsenouser/index");
    }

    /**
     * 读取列表
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value = "/list")
    public @ResponseBody Map<String, Object> findForFalseNouserAll(HttpServletRequest request) {
        ModelMap map=new ModelMap();

        String pag = request.getParameter("page");
        String showCount = request.getParameter("rows");
        Page page = new Page();
        try
        {      
            if (null != pag && null != showCount) {
                page.setCurrentPage(Integer.parseInt(pag));
                page.setShowCount(Integer.parseInt(showCount));
            }

            FalseNouser  entityInfo=new FalseNouser ();
            entityInfo.setPage(page);
            List<FalseNouser > list=falseNouserService.findFalseNouserByPage(entityInfo);
            map.put("rows",list); 
            map.put("total",entityInfo.getPage().getTotalResult()); 

        }
        catch (Exception e)
        {
            log.error("Controller Error FalseNouserController-> findFalseNouserByWhere  " + e.getMessage());
        }

        return map;
    }




    /**
     * 跳转添加页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpAdd")
    public ModelAndView jumpFalseNouserAdd(HttpServletRequest request) {
        ModelMap map=new ModelMap();

        return new ModelAndView("falsenouser/save",map);
    }

    /**
     * 跳转批量添加页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpAddBatch")
    public ModelAndView jumpAddBatch(HttpServletRequest request) {
        ModelMap map=new ModelMap();

        return new ModelAndView("falsenouser/addBatch",map);
    }
    /**
     * 跳转修改页面 
     * @param request
     * @return
     */
    @RequestMapping(value = "/jumpEdit")
    public ModelAndView jumpFalseNouserEdit(HttpServletRequest request) {
        ModelMap map=new ModelMap();
        try
        {

            if(Tools.notEmpty(request.getParameter("id"))){  

                FalseNouser entity=new FalseNouser();
                entity.setUserId(Integer.parseInt(request.getParameter("id")));
                entity=falseNouserService.findFalseNouserById(entity);
                map.put("falsenouser",entity);

            }
        }
        catch (Exception e)
        {

            log.error("Controller Error FalseNouserController-> jumpFalseNouserEdit  " + e.getMessage());
        }
        return new ModelAndView("falsenouser/edit",map);
    }

    /**
     * 信息保存
     * Description: <br>
     * @param 
     * @return 
     * @see
     */
    @RequestMapping(value = "/add")
    public @ResponseBody Map<String, Object> saveFalseNouserInfo(FalseNouser entity) {
        ModelMap map=new ModelMap();
        try
        { 
            map.put("result",falseNouserService.addFalseNouser(entity));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            log.error("Controller Error FalseNouserController-> saveFalseNouserInfo " + e.getMessage());
        }
        return map;
    }

    /**
     * 批量插入
     * Description: <br>
     * @param 
     * @return 
     * @throws FileNotFoundException 
     * @see
     */
    @RequestMapping(value = "/addByBatch")
    public @ResponseBody String addBatchFalseNouser(HttpServletRequest request,HttpServletResponse response) throws FileNotFoundException {
        ModelMap map=new ModelMap();
        ExcelHelper h=new ExcelHelper();
        String url=request.getParameter("url");
        File file=new File(url);
        JSONObject js=new JSONObject();
        if(!file.exists()){
            //文件不存在
            js.put("message", "nofiles");
        }else{

            Map<String,String>mapper=new HashMap<String, String>();
            mapper.put("a","a");
            mapper.put("b","b");
            mapper.put("c","c");
//            mapper.put("helpNum","helpNum");
            List<Map<String,String>> excelResultList=h.readNormalFile(file, mapper);
            if(excelResultList.size()>0){
                try{

//                    falseNouserService.addFalseNouserByBatch(excelResultList);
                    List<Map<String,Object>>add=new ArrayList<Map<String,Object>>();
                    for (int i = 0; i < excelResultList.size(); i++ )
                    {
                        Map<String,Object> a=new HashMap<String, Object>();
                        if(excelResultList.get(i).get("c").equals(excelResultList.get(i).get("c"))){
                            a.put("code", excelResultList.get(i).get("c"));
                        }
                        
                        if(excelResultList.get(i).get("a").equals(excelResultList.get(i).get("a"))){
                            a.put("id", excelResultList.get(i).get("a"));
                        }
                        add.add(a);
                        
                    }
                    System.out.println(JSONArray.fromObject(add));
                    js.put("message", "success");
//                    file.delete();
                }catch (Exception e){
                    // TODO Auto-generated catch block
                    log.error("Controller Error FalseNouserController-> addBatchFalseNouser " + e.getMessage());
                    js.put("message", "error");
                }finally{

//                    file.delete();
                }
            }else{
                //上传文件中没有数据
                js.put("message", "nodata");
            }
        }
        return js.toString();
    }




    /**
     * 信息修改
     * Description: <br>
     * @param 
     * @return 
     * @see
     */
    @RequestMapping(value = "/update")
    public @ResponseBody Map<String, Object> updateFalseNouserById(FalseNouser entity) {
        ModelMap map=new ModelMap();
        try
        {            
            map.put("result",falseNouserService.updateFalseNouser(entity));
        }
        catch (Exception e)
        {
            log.error("Controller Error FalseNouserController-> updateFalseNouserById  " + e.getMessage());
        }
        return map;
    }


    /**
     * 信息删除
     * @param 
     * @return
     */
    @RequestMapping(value = "/delete")
    public @ResponseBody Map<String, Object> deleteFalseNouserById(String id) {

        ModelMap map=new ModelMap();
        try
        {
            if(Tools.notEmpty(id)){

                map.put("result",falseNouserService.deleteFalseNouser(id));
            }  
        }
        catch (Exception e)
        {
            log.error("Controller Error FalseNouserController-> deleteFalseNouserById  " + e.getMessage());
        }

        return map;
    }


}

