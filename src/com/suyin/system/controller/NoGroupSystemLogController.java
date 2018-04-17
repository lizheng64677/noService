/*
 * 文件名：NoGroupSystemLogController.java
 * 版权：Copyright by www.isure.net
 * 描述：
 * 修改人：windows7
 * 修改时间：2015-12-10
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.suyin.system.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;










import com.suyin.cash.model.NouserCashTeller;
import com.suyin.common.ReportToExcel;
import com.suyin.common.SystemPropertiesHolder;
import com.suyin.experience.controller.ExpDetailController;
import com.suyin.experience.model.ExpDetail;
import com.suyin.experience.model.Experience;
import com.suyin.system.model.BaseExpLogModel;
import com.suyin.system.model.BaseLogModel;
import com.suyin.system.model.CapitalCount;
import com.suyin.system.model.Page;
import com.suyin.system.service.NoGroupSystemLogService;

/**
 * 
 * NO团操作记录统计
 * @author lz
 * @version 2015-12-10
 * @see NoGroupSystemLogController
 * @since
 */
@Controller
@RequestMapping(value="/sysLog")
public class NoGroupSystemLogController
{
    private final static Logger log=Logger.getLogger(NoGroupSystemLogController.class);
    @Autowired
    private NoGroupSystemLogService groupSystemLogService;

    /****************************资金统计开始*****************************************/

    /**
     * 
     * 资金统计
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value="/capital")
    public @ResponseBody ModelAndView capital(HttpServletRequest request){
        ModelMap model=new ModelMap();
        return new ModelAndView("system/capital/index",model);
    }

    /**
     * 读取列表
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value = "/capitalCount")
    public @ResponseBody Map<String, Object> capitalCount(HttpServletRequest request) {
        ModelMap map=new ModelMap();

        String pag = request.getParameter("page");
        String showCount = request.getParameter("rows");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        Page page = new Page();
        try
        {      
            if (null != pag && null != showCount) {
                page.setCurrentPage(Integer.parseInt(pag));
                page.setShowCount(Integer.parseInt(showCount));
            }

            Map<String, Object> map1 = new HashMap<String, Object>();
            map1.put("startTime",startTime);
            map1.put("endTime",endTime);
            map1.put("page",page);
            CapitalCount  caCount=groupSystemLogService.findCapitalCountByPage(map1);
            List<CapitalCount> list = new ArrayList<CapitalCount>();
            list.add(caCount);
            //            map.put("", "");
            map.put("rows",list); 
            map.put("total",list.size()); 

        }
        catch (Exception e)
        {
            e.printStackTrace();
            log.error("Controller Error ExperienceController-> findExperienceByWhere  " + e.getMessage());
        }

        return map;
    }


    /**
     * 导出excel
     */
    @RequestMapping("/dExcel")
    public void downExcelThrough(HttpServletRequest request,HttpServletResponse response) throws Exception {
        response.setContentType("application/vnd.ms-excel");  
        response.setHeader("content-disposition", "attachment;filename=" +new SimpleDateFormat("yyyyMMddHHmm").format(new Date())+".xls");  

        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        Page page = new Page();
        List<Map<String, String>> list=new ArrayList<Map<String,String>>();
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("startTime",startTime);
        map1.put("endTime",endTime);
        map1.put("page",page);
        CapitalCount  caCount=groupSystemLogService.findCapitalCountByPage(map1);
        Map<String, String> map = new HashMap<String, String>();
        map.put("userCount", String.valueOf(caCount.getUserCount()));
        map.put("goldCount", String.valueOf(caCount.getGoldCount()));
        map.put("goldSignCount", String.valueOf(caCount.getGoldSignCount()));
        map.put("registerGoldCount", String.valueOf(caCount.getRegisterGoldCount()));
        map.put("dataGoldCount", String.valueOf(caCount.getDataGoldCount()));
        map.put("qisongTaskGoldCount", String.valueOf(caCount.getQisongTaskGoldCount()));
        map.put("qmTaskGoldCount", String.valueOf(caCount.getQmTaskGoldCount()));
        map.put("walletGoldCount", String.valueOf(caCount.getWalletGoldCount()));
        map.put("walletUserCount", String.valueOf(caCount.getWalletUserCount()));
        map.put("waletMoneyCount", String.valueOf(caCount.getWaletMoneyCount()));
        map.put("applyMoneyCount", String.valueOf(caCount.getApplyMoneyCount()));
        map.put("applyUserCount", String.valueOf(caCount.getApplyUserCount()));
        map.put("moneyCount", String.valueOf(caCount.getMoneyCount()));
        list.add(map);
        ReportToExcel.createDataExcel(response.getOutputStream(), 
            "资金统计", 
            //"分账报表", 
            new String[] {"当日平台总人数", "平台金币总数", "当日签到获得总金币数", "当日注册获得总金币数", 
            "完善资料获得总金币数","轻松赚任务总金币数","帮我赚任务总金币数","金币兑换钱包总金币","金币兑换钱包总人数",
            "新申请兑换金额(元)","新申请提现金额(元)","新申请提现人数","钱包提现支付宝总数"}, 
            null, 
            list, 
            new String[] {"userCount","goldCount","goldSignCount","registerGoldCount","dataGoldCount"
            ,"qisongTaskGoldCount","qmTaskGoldCount","walletGoldCount","walletUserCount","waletMoneyCount"
            ,"applyMoneyCount","applyUserCount","moneyCount"}, 
            null, 
            null, 0 );

    }


    /****************************资金统计结束*****************************************/


    /***********************NO团活动数据统计start******************************/


    /**
     * 
     * 赚金币流量UV
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value="/initExpZhuanDataUv",produces = "application/json; charset=utf-8")
    public @ResponseBody String initExpZhuanDataUv(HttpServletRequest request,BaseExpLogModel model){
        String result="";
        List<Map<String,Object>> resultList=groupSystemLogService.findExpZhuanCharsInfo(model);
        result=this.pottingExpZhuanUvInitXml(resultList);
        return result;

    }
    /**
     * 
     * 赚金币流量PV
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value="/initExpZhuanData",produces = "application/json; charset=utf-8")
    public @ResponseBody String initExpZhuanData(HttpServletRequest request,BaseExpLogModel model){
        String result="";
        List<Map<String,Object>> resultList=groupSystemLogService.findExpZhuanCharsInfo(model);
        result=this.pottingExpZhuanInitXml(resultList);
        return result;

    }
    /**
     * 统计UV数据各终端总数_赚金币
     * @param request
     * @param model
     * @return 
     * @see
     */
    @RequestMapping(value="/initExpZhuanAllDataUv",produces = "application/json; charset=utf-8")
    public @ResponseBody String initExpZhuanAllDataUv(HttpServletRequest request,BaseExpLogModel model){
        String result="";
        List<Map<String,Object>> resultList=groupSystemLogService.initExpAllDataUv(model);        
        result=this.pottingInitXmlUv(resultList);
        return result;
    }
    /**
     * 统计PV数据各终端总数_赚金币
     * @param request
     * @param model
     * @return 
     * @see
     */
    @RequestMapping(value="/initExpZhuanAllData",produces = "application/json; charset=utf-8")
    public @ResponseBody String initExpZhuanAllData(HttpServletRequest request,BaseExpLogModel model){
        String result="";
        List<Map<String,Object>> resultList=groupSystemLogService.initExpAllData(model);        
        result=this.pottingAllInitXml(resultList);
        return result;
    }


    /**
     * 
     * 统计各终端数据流量 UV
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value="/initExpDataUv",produces = "application/json; charset=utf-8")
    public @ResponseBody String initExpDataUv(HttpServletRequest request,BaseExpLogModel model){
        String result="";
        List<Map<String,Object>> resultList=groupSystemLogService.findExpCharsInfo(model);
        result=this.pottingExpUvInitXml(resultList);
        return result;

    }
    /**
     * 
     * 统计各终端数据流量PV
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value="/initExpData",produces = "application/json; charset=utf-8")
    public @ResponseBody String initExpData(HttpServletRequest request,BaseExpLogModel model){
        String result="";
        List<Map<String,Object>> resultList=groupSystemLogService.findExpCharsInfo(model);
        result=this.pottingExpInitXml(resultList);
        return result;

    }

    /**
     * 统计UV数据各终端总数
     * @param request
     * @param model
     * @return 
     * @see
     */
    @RequestMapping(value="/initExpAllDataUv",produces = "application/json; charset=utf-8")
    public @ResponseBody String initExpAllDataUv(HttpServletRequest request,BaseExpLogModel model){
        String result="";
        List<Map<String,Object>> resultList=groupSystemLogService.initExpAllDataUv(model);        
        result=this.pottingAllInitXml(resultList);
        return result;
    }

    /**
     * 
     * 统计各终端数据流量
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value="/initExpAllData",produces = "application/json; charset=utf-8")
    public @ResponseBody String initExpAllData(HttpServletRequest request,BaseExpLogModel model){
        String result="";
        List<Map<String,Object>> resultList=groupSystemLogService.initExpAllData(model);
        result=this.pottingAllInitXml(resultList);
        return result;

    }

    private String pottingExpZhuanInitXml(List<Map<String,Object>> list){
        String resXml = "" ;
        if(list.size()>0){
            for(int i = 0 ; i < list.size() ; i ++)
            {
                resXml += "<set name='"+list.get(i).get("title")+"' value='"+list.get(i).get("t_pv")+"' />";
            }
        }else{

            resXml += "<set name='暂无数据' value='0' />";
        }
        return resXml ;

    }

    private String pottingExpZhuanUvInitXml(List<Map<String,Object>> list){
        String resXml = "" ;
        if(list.size()>0){
            for(int i = 0 ; i < list.size() ; i ++)
            {
                resXml += "<set name='"+list.get(i).get("title")+"' value='"+list.get(i).get("t_uv")+"' />";
            }
        }else{

            resXml += "<set name='暂无数据' value='0' />";
        }
        return resXml ;

    }

    private String pottingExpInitXml(List<Map<String,Object>> list){
        String resXml = "" ;
        if(list.size()>0){
            for(int i = 0 ; i < list.size() ; i ++)
            {
                resXml += "<set name='"+list.get(i).get("title")+"' value='"+list.get(i).get("t_pv")+"' />";
            }
        }else{

            resXml += "<set name='暂无数据' value='0' />";
        }
        return resXml ;

    }

    private String pottingExpUvInitXml(List<Map<String,Object>> list){
        String resXml = "" ;
        if(list.size()>0){
            for(int i = 0 ; i < list.size() ; i ++)
            {
                resXml += "<set name='"+list.get(i).get("pro_name")+"' value='"+list.get(i).get("t_uv")+"' />";
            }
        }else{

            resXml += "<set name='暂无数据' value='0' />";
        }
        return resXml ;

    }


    /**
     * 跳转至帮我赚PV图表页面
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value="/toBw")
    public ModelAndView toBw(HttpServletRequest request){
        ModelMap model=new ModelMap();

        return new ModelAndView("nogrouplog/bw_index",model);
    }

    /**
     * 跳转至帮我赚UV图表页面
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value="/toBwU")
    public ModelAndView toBwU(HttpServletRequest request){
        ModelMap model=new ModelMap();

        return new ModelAndView("nogrouplog/bw_u_index",model);
    }

    /**
     * 跳转至轻松赚PV图表页面
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value="/toQs")
    public ModelAndView toQs(HttpServletRequest request){
        ModelMap model=new ModelMap();

        return new ModelAndView("nogrouplog/qs_index",model);
    }

    /**
     * 跳转至轻松赚UV图表页面
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value="/toQsU")
    public ModelAndView toQsU(HttpServletRequest request){
        ModelMap model=new ModelMap();

        return new ModelAndView("nogrouplog/qs_u_index",model);
    }

    /**
     * 跳转人气式数据统计页面PV
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value="/toPop")
    public ModelAndView toPop(HttpServletRequest request){
        ModelMap model=new ModelMap();

        return new ModelAndView("nogrouplog/pop_index",model);
    }

    /**
     * 跳转人气式数据统计页面Uv
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value="/toPopU")
    public ModelAndView toPopU(HttpServletRequest request){
        ModelMap model=new ModelMap();

        return new ModelAndView("nogrouplog/pop_u_index",model);
    }

    /**
     * 跳转抽奖式数据统计页面pv
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value="/toPrize")
    public ModelAndView toPrize(HttpServletRequest request){
        ModelMap model=new ModelMap();

        return new ModelAndView("nogrouplog/prize_index",model);
    }
    /**
     * 跳转抽奖式数据统计页面UV
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value="/toPrizeU")
    public ModelAndView toPrizeU(HttpServletRequest request){
        ModelMap model=new ModelMap();

        return new ModelAndView("nogrouplog/prize_u_index",model);
    }

    /**
     * 跳转兑换式数据统计页面
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value="/toEchage")
    public ModelAndView toEchage(HttpServletRequest request){
        ModelMap model=new ModelMap();

        return new ModelAndView("nogrouplog/chage_index",model);
    }
    /**
     * 跳转兑换式数据统计页面
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value="/toEchageu")
    public ModelAndView toEchageu(HttpServletRequest request){
        ModelMap model=new ModelMap();

        return new ModelAndView("nogrouplog/chage_u_index",model);
    }

    /**
     * 跳转试用式数据统计页面
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value="/toTry")
    public ModelAndView toTry(HttpServletRequest request){
        ModelMap model=new ModelMap();

        return new ModelAndView("nogrouplog/try_index",model);
    }


    /**
     * 跳转试用式数据统计页面
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value="/toTryu")
    public ModelAndView toTryu(HttpServletRequest request){
        ModelMap model=new ModelMap();

        return new ModelAndView("nogrouplog/try_u_index",model);
    }



    /***********************NO团活动数据统计stop******************************/








    /***********************系统模块数据统计start******************************/

    /**
     * 读取系统模块日志列表
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value = "/list")
    public @ResponseBody Map<String, Object> findForSystemAll(HttpServletRequest request) {
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
            String clicentType=request.getParameter("clicentType");
            String beginTime=request.getParameter("beginTime");
            String endTime=request.getParameter("endTime");
            BaseLogModel  entityInfo=new BaseLogModel();
            entityInfo.setPage(page);
            if(!"-1".equals(clicentType) && null!=clicentType){
                entityInfo.setClicentType(clicentType);
            }
            if(null!=beginTime && !"".equals(beginTime)){

                entityInfo.setBegTime(beginTime);
            }
            if(null!=endTime && !"".equals(endTime)){

                entityInfo.setEndTime(endTime);
            }
            List<BaseLogModel > list=groupSystemLogService.findExpSystemLogByPage(entityInfo);
            map.put("rows",list); 
            map.put("total",entityInfo.getPage().getTotalResult()); 

        }
        catch (Exception e)
        {
            log.error("Controller Error NoGroupSystemLogController-> findForSystemAll  " + e.getMessage());
        }

        return map;
    }
    /**
     * 
     * 跳转系统功能模块日志记录列表
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value="/toSystemLog")
    public ModelAndView toSystemLog(HttpServletRequest request){
        ModelMap model=new ModelMap();

        return new ModelAndView("nogrouplog/index",model);
    }


    /**
     * 
     *跳转UV展示页面 
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value="/index")
    public ModelAndView toCharsJsp(HttpServletRequest request){

        return new ModelAndView("/system/layout/chars");
    }



    /**
     * 
     * 统计各终端数据流量
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value="/initData",produces = "application/json; charset=utf-8")
    public @ResponseBody String initData(HttpServletRequest request,BaseLogModel model){
        String result="";
        List<Map<String,Object>> resultList=groupSystemLogService.initData(model);
        result=this.pottingInitXml(resultList);
        return result;

    }

    /**
     * 
     * 全端数据统计
     * @return 
     * @see
     */
    @RequestMapping(value="/initAllData",produces = "application/json; charset=utf-8")
    public @ResponseBody String initAllData(HttpServletRequest request,BaseLogModel model){
        String result="";       
        List<Map<String,Object>> resultList=groupSystemLogService.initAllData(model);
        result=this.pottingAllInitXml(resultList);
        return result;

    }


    /**
     * 
     * 统计各终端数据流量
     * @param request
     * @return 
     * @see
     */
    @RequestMapping(value="/initDataUv",produces = "application/json; charset=utf-8")
    public @ResponseBody String initDataUv(HttpServletRequest request,BaseLogModel model){
        String result="";
        List<Map<String,Object>> resultList=groupSystemLogService.initData(model);
        result=this.pottingInitXmlUv(resultList);
        return result;

    }

    /**
     * 
     * 全端数据统计
     * @return 
     * @see
     */
    @RequestMapping(value="/initAllDataUv",produces = "application/json; charset=utf-8")
    public @ResponseBody String initAllDataUv(HttpServletRequest request,BaseLogModel model){
        String result="";       
        List<Map<String,Object>> resultList=groupSystemLogService.initAllDataUv(model);
        result=this.pottingAllInitXml(resultList);
        return result;

    }
    private String pottingInitXml(List<Map<String,Object>> list){
        String resXml = "" ;
        if(list.size()>0){
            for(int i = 0 ; i < list.size() ; i ++)
            {
                resXml += "<set name='"+list.get(i).get("t_mode_name")+"' value='"+list.get(i).get("t_pv")+"' />";
            }
        }else{

            resXml += "<set name='暂无数据' value='0' />";
        }
        return resXml ;

    }
    private String pottingInitXmlUv(List<Map<String,Object>> list){
        String resXml = "" ;
        if(list.size()>0){
            for(int i = 0 ; i < list.size() ; i ++)
            {
                resXml += "<set name='"+list.get(i).get("t_mode_name")+"' value='"+list.get(i).get("t_uv")+"' />";
            }
        }else{

            resXml += "<set name='暂无数据' value='0' />";
        }
        return resXml ;

    }

    private String pottingAllInitXml(List<Map<String,Object>> list){
        String resXml = "" ;
        if(!list.contains(null)){

            resXml += "<set name='微信' value='"+list.get(0).get("wx")+"' />";
            resXml += "<set name='安卓' value='"+list.get(0).get("andriod")+"' />";
            resXml += "<set name='苹果' value='"+list.get(0).get("ios")+"' />";
        }else{

            resXml += "<set name='微信' value='0' />";
            resXml += "<set name='安卓' value='0' />";
            resXml += "<set name='苹果' value='0' />";
        }
        return resXml ;

    }

    /***********************系统模块数据统计stop******************************/

}
