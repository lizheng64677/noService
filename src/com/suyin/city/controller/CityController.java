
package com.suyin.city.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.suyin.system.model.Page;
import com.suyin.system.util.Tools;


import com.suyin.city.model.*;
import com.suyin.city.service.*;


/**
 * 城市管理实现  
 * @author lz
 * @version 2015-8-25
 * @see CityController
 * @since
 */
@Controller
@RequestMapping("/city")
public class CityController{
    @Autowired
    private final static Logger log=Logger.getLogger(CityController.class);
    @Autowired
    private CityService cityService;

    @RequestMapping(value="/toPageTestJsp")
    public ModelAndView  toPageTestJsp(HttpServletRequest request){
        ModelMap model=new ModelMap();
        return new ModelAndView("city/test",model);
    }
    /**
     * 
     * 城市json数据制作
     * 
     * @param request
     * @param response 
     * @see
     */
    @RequestMapping(value="/responseJson")
    public void responseJson(HttpServletRequest request,HttpServletResponse response){
        Map<String,Object>mapInfo=new HashMap<String, Object>();

        //        List<Map<String,Object>>mapList=cityService.findUpdateCityInfo(mapInfo);
        List<Map<String,Object>>isoList=cityService.findForCityListInfo();
        List<Map<String,Object>> result=new LinkedList<Map<String,Object>>();
        System.out.println(JSONArray.fromObject(isoList));
        String[] str={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        Map<String,Object> map=new HashMap<String, Object>();
        Map<String,Object>mapadd=new HashMap<String,Object>();
        for (int i = 0; i < str.length; i++ )
        {
            map.put("name", str[i]);
            List<Map<String,Object>>jsonList=cityService.findForCityListInfoa(map);
            if(jsonList.size()>0){
                mapadd.put(str[i], JSONArray.fromObject(jsonList));
                result.add(mapadd);
            }
        }
        //        System.out.println(result);
        System.out.println(JSONArray.fromObject(result));
        //        if(mapList.size()>1){ 
        //
        //            for (int i = 0; i < mapList.size(); i++ ){
        //
        //                mapInfo.put("jpname", Tools.converterToFirstSpell(mapList.get(i).get("name").toString()));
        //                mapInfo.put("qpname", Tools.converterToSpell(mapList.get(i).get("name").toString()));
        //                mapInfo.put("id", mapList.get(i).get("id"));
        //                cityService.updateCityInfoName(mapInfo);
        //
        //            }
        //        }


    }
    /**
     * 首页
     * @return 
     * @see
     */
    @RequestMapping(value="/index")
    public ModelAndView index() {
        return new ModelAndView("city/index");
    }

    @RequestMapping(value = "/hotCityIndex")
    public String hotCityIndex(HttpServletRequest request,HttpServletResponse response){
        try {

        } catch (Exception e) {
            log.error("Controller Error DataDictionaryController-> index  "+ e.getMessage());
        }
        return "city/IsHotCityIndex";
    }


    @RequestMapping(value="/hotCity")
    public @ResponseBody Map<String, Object> hotCity(City city,HttpServletRequest request){
        ModelMap map=new ModelMap();
        int i=0;
        try{
            i=cityService.hotCity(city);
            map.put("message", i);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("Controller Error CityController-> hotCity " + e.getMessage());
        }
        return map;
    }


    /**
     * 热门城市分页查询
     * @return
     */
    @RequestMapping(value="/findHotCityByPage")
    @ResponseBody
    public  Map<String,Object> findHotCityByPage(HttpServletRequest request){
        Map<String,Object> map=new HashMap<String, Object>();
        String pag=request.getParameter("page");
        String showCount=request.getParameter("rows");
        Page page=new Page();
        if(null!=page&&null!=showCount){
            page.setCurrentPage(Integer.parseInt(pag));
            page.setShowCount(Integer.parseInt(showCount));
        }
        City city=new City();
        city.setPage(page);
        if(Tools.notEmpty(request.getParameter("name"))){
            city.setName(request.getParameter("name"));
        }
        List<City> list=cityService.findHotCityByPage(city);
        map.put("rows", list);
        map.put("total", city.getPage().getTotalResult());
        return map;
    } 

    @RequestMapping(value = "/treeIndex")
    public String treeIndex(HttpServletRequest request,HttpServletResponse response){
        try {

        } catch (Exception e) {
            log.error("Controller Error DataDictionaryController-> index  "+ e.getMessage());
        }
        return "city/TreeIndex";
    }

    /**
     * 获取树查看省列表
     * @param request
     * @return
     */
    @RequestMapping(value="/doTreeCity")
    @ResponseBody
    public Map<String, Object> doTreeCity(HttpServletRequest request){
        Map<String,Object> map=new HashMap<String, Object>();
        String pag=request.getParameter("page");
        String showCount=request.getParameter("rows");
        Page page=new Page();
        if(null!=page&&null!=showCount){
            page.setCurrentPage(Integer.parseInt(pag));
            page.setShowCount(Integer.parseInt(showCount));
        }
        City city=new City();
        city.setParentId(Integer.parseInt(request.getParameter("parentId")));
        city.setPage(page);
        map.put("rows", cityService.findCityTreeByPage(city));
        map.put("total", city.getPage().getTotalResult());
        return map;
    }

    /**
     * 更具Upid级别属性树查看省市
     * @param request
     * @return
     */
    @RequestMapping(value="/findCityTreeByUpid")
    @ResponseBody
    public List<City> findCityTreeByUpid(HttpServletRequest request){
        City city=new City();
        city.setParentId(Integer.parseInt(request.getParameter("parentId")));
        return cityService.findCityTreeByUpid(city);
    }

    /**
     * 分页查询
     * @param request
     * @return
     */
    @RequestMapping(value="/findCity")
    public @ResponseBody Map<String,Object> findCity(HttpServletRequest request){
        Map<String,Object> map=new HashMap<String, Object>();
        String pag=request.getParameter("page");
        String showCount=request.getParameter("rows");
        Page page=new Page();
        if(null!=page&&null!=showCount){
            page.setCurrentPage(Integer.parseInt(pag));
            page.setShowCount(Integer.parseInt(showCount));
        }
        City city=new City();
        city.setPage(page);
        if(Tools.notEmpty(request.getParameter("name"))){
            city.setName(request.getParameter("name"));
        }
        map.put("rows", cityService.findCityByPage(city));
        map.put("total", city.getPage().getTotalResult());
        return map;
    } 


    /**
     * 条件查询 
     * Description: <br>
     * @param 
     * @return 
     * @see
     */
    @RequestMapping(value = "/findForCityByUpid")
    public @ResponseBody Map<String, Object> findForCityById(HttpServletRequest request) {
        ModelMap map=new ModelMap();
        try
        {
            String upid=request.getParameter("upid");
            City entity=new City();
            entity.setUpid(Integer.parseInt(upid));           
            map.put("result",cityService.findCity(entity));
        }
        catch (Exception e)
        {
            log.error("Controller Error CityController-> saveCityInfo " + e.getMessage());
        }
        return map;
    }
}

