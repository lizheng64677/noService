/*
 * 文件名：ExpExcelAllController.java
 * 版权：Copyright by www.isure.net
 * 描述：
 * 修改人：windows7
 * 修改时间：2015-12-28
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.suyin.experience.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.suyin.common.ReportToExcel;
import com.suyin.experience.model.ExpChars;
import com.suyin.experience.service.ExpCharsService;
import com.suyin.system.model.BaseLogModel;
import com.suyin.system.service.NoGroupSystemLogService;

/**
 * 
 * 活动统计数据导出
 * 数据导出
 * 人气王，抽奖乐，金币兑，试用式，  
 * 帮我赚，轻松赚 等活动
 * 其中包含 资产统计数据导出，及系统模块数据导出
 * @author lz
 * @version 2015-12-28
 * @see ExpExcelAllController
 * @since
 */
@Controller
@RequestMapping("/expExcel")
public class ExpExcelAllController
{
    private final static Logger log=Logger.getLogger(ExpExcelAllController.class);

    private final static String SYSTEM_DATA_NAMES="系统模块统计";
    private final static String[] SYSTEM_ARRY_DATA={"t_clicent_type", "t_pv","t_uv","t_logtype","t_mode_name"};
    private final static String[] SYSTEM_ARRY_NAMES={"客户端类型","页面点击量","用户访问量","模块编号","模块名称"};
    private final static String EXP_PRIZE_DATA="抽奖乐数据统计";
    private final static String[] EXP_ARRY_PRIZE_DATA={"t_exp_id","t_clicent_type","title","pro_name","price","begin_time","end_time","probability","add_day","validity","t_pv","t_uv","pro_num","exp_num","rnum","xqs"};
    private final static String[] EXP_ARRY_PRIZE_NAMES={"活动Id","客户端类型","活动标题","产品名称","市场价格","开始时间","结束时间","中奖率","X天后过期","到期日","页面点击量","用户访问量","产品数量","中奖数量","参与人数","消券数"};
    private final static String EXP_POP_DATA="人气王数据统计";
    private final static String[] EXP_POP_ARRY_DATA={"t_exp_id","t_clicent_type","title","pro_name","price","begin_time","end_time","add_day","validity","t_pv","t_uv","pro_num","popnum","rnum","xqs"};
    private final static String[] EXP_POP_ARRY_NAMES={"活动Id","客户端类型","活动标题","产品名称","市场价格","开始时间","结束时间","X天后过期","到期日","页面点击量","用户访问量","产品数量","总人气","参与人数","消券数"};
    private final static String EXP_CHAGE_DATA="金币兑数据统计";
    private final static String[] EXP_CHAGE_ARRY_DATA={};
    private final static String[] EXP_CHAGE_ARRY_NAMES={};
    private final static String EXP_TRY_DATA="试用式数据统计";
    private final static String[] EXP_TRY_ARRY_DATA={};
    private final static String[] EXP_TRY_ARRY_NAMES={};
    private final static String EXP_QS_DATA="轻松赚数据统计";

    private final static String[] EXP_QS_ARRY_DATA={"exp_id","title","t_pv","t_uv","begin_time","end_time","exp_user_gold","tjnum","shnum","ordernum","zs"};
    private final static String[] EXP_QS_ARRY_NAMES={"活动Id","活动标题","页面点击量","页面访问量","开始时间","结束时间","收益","提交人数","审核通过人数","领取人数","发放总收益"};
    private final static String EXP_BW_DATA="帮我赚数据统计";
    private final static String[] EXP_BW_ARRY_DATA={"exp_id","title","t_pv","t_uv","begin_time","end_time","exp_gold_min","exp_gold_max","ordernum","zs"};
    private final static String[] EXP_BW_ARRY_NAMES={"活动Id","活动标题","页面点击量","页面访问量","开始时间","结束时间","最小收益》","《最大收益","领取人数","发放总收益"};

    @Autowired
    private NoGroupSystemLogService groupSystemLogService;
    @Autowired
    private ExpCharsService expCharsService;

    /**
     * 
     * 导出赚金币任务数据
     * @param request
     * @param response 
     * @throws Exception 
     * @throws IOException 
     * @see
     */
    @RequestMapping(value="/expTaskDataExport",produces="application/vnd.ms-excel")
    public void expTaskDataExport(HttpServletRequest request,HttpServletResponse response) throws IOException, Exception{
        response.setHeader("content-disposition", "attachment;filename=" +new SimpleDateFormat("yyyyMMddHHmm").format(new Date())+".xls");  
        String clicentType=request.getParameter("clicentType");
        String beginTime=request.getParameter("beginTime");
        String endTime=request.getParameter("endTime");
        String expType=request.getParameter("expType");
        ExpChars  entityInfo=new ExpChars();
        if(null!=expType && !"".equals(expType)){

            entityInfo.setExpType(Integer.parseInt(expType));
        }
        if(!"-1".equals(clicentType) && null!=clicentType){
            entityInfo.setClicentType(clicentType);
        }
        if(null!=beginTime && !"".equals(beginTime)){

            entityInfo.setBengTime(beginTime);
        }
        if(null!=endTime && !"".equals(endTime)){

            entityInfo.setEndTime(endTime);
        }
        if("0".equals(expType)){//帮我赚

            int[] columnWidths={};
            List<Map<String,Object>>taskBwList=expCharsService.findQzhuanCharsMapInfo(entityInfo);
            ReportToExcel.createDataExcela(response.getOutputStream(),EXP_BW_DATA, EXP_BW_ARRY_NAMES, columnWidths, taskBwList,EXP_BW_ARRY_DATA);
        }else if("1".equals(expType)){//轻松赚

            int[] columnWidths={};
            List<Map<String,Object>>taskBwList=expCharsService.findZhuanCharsMapInfo(entityInfo);
            ReportToExcel.createDataExcela(response.getOutputStream(),EXP_QS_DATA, EXP_QS_ARRY_NAMES, columnWidths, taskBwList,EXP_QS_ARRY_DATA);
        }


    }

    /**
     * 导出抽奖乐数据
     * @param request
     * @param response 
     * @throws Exception 
     * @throws IOException 
     * @see
     */
    @RequestMapping(value="/expPrizeDataExport",produces="application/vnd.ms-excel")
    public void expPrizeDataExport(HttpServletRequest request,HttpServletResponse response) throws IOException, Exception{
        response.setHeader("content-disposition", "attachment;filename=" +new SimpleDateFormat("yyyyMMddHHmm").format(new Date())+".xls");  

        String clicentType=request.getParameter("clicentType");
        String beginTime=request.getParameter("beginTime");
        String endTime=request.getParameter("endTime");
        String expType=request.getParameter("expType");
        ExpChars  entityInfo=new ExpChars();
        if(null!=expType && !"".equals(expType)){

            entityInfo.setExpType(Integer.parseInt(expType));
        }
        if(!"-1".equals(clicentType) && null!=clicentType){
            entityInfo.setClicentType(clicentType);
        }
        if(null!=beginTime && !"".equals(beginTime)){

            entityInfo.setBengTime(beginTime);
        }
        if(null!=endTime && !"".equals(endTime)){

            entityInfo.setEndTime(endTime);
        }
        if("0".equals(expType)){ //抽奖乐

            List<Map<String,Object>> expPrizeDataList=expCharsService.findPrizeCharsMapInfo(entityInfo);
            int[] columnWidths={};
            ReportToExcel.createDataExcela(response.getOutputStream(),EXP_PRIZE_DATA, EXP_ARRY_PRIZE_NAMES, columnWidths, expPrizeDataList,EXP_ARRY_PRIZE_DATA);
        }else if("1".equals(expType)){ //人气王

            List<Map<String,Object>>expPopDataList=expCharsService.findPopCharsMapList(entityInfo);
            int[] columnWidths={};           
            ReportToExcel.createDataExcela(response.getOutputStream(),EXP_POP_DATA, EXP_POP_ARRY_NAMES, columnWidths, expPopDataList,EXP_POP_ARRY_DATA);

        }else if("2".equals(expType)){//金币兑

        }else if("3".equals(expType)){//试用式


        }   



    }

    /**
     * 导出系统模块数据信息
     * @param request
     * @param response 
     * @throws Exception 
     * @throws IOException 
     * @see
     */
    @RequestMapping(value="/systemModelDataExport",produces="application/vnd.ms-excel")
    public void sysTemModelDataExport(HttpServletRequest request,HttpServletResponse response) throws IOException, Exception{

        response.setHeader("content-disposition", "attachment;filename=" +new SimpleDateFormat("yyyyMMddHHmm").format(new Date())+".xls");          
        String clicentType=request.getParameter("clicentType");
        String beginTime=request.getParameter("beginTime");
        String endTime=request.getParameter("endTime");
        BaseLogModel  entityInfo=new BaseLogModel();
        if(!"-1".equals(clicentType) && null!=clicentType){
            entityInfo.setClicentType(clicentType);
        }
        if(null!=beginTime && !"".equals(beginTime)){

            entityInfo.setBegTime(beginTime);
        }
        if(null!=endTime && !"".equals(endTime)){

            entityInfo.setEndTime(endTime);
        }
        List<Map<String,Object>> systemDatalist=groupSystemLogService.findExpSystemLogMapInfo(entityInfo);

        int[] columnWidths={};
        ReportToExcel.createDataExcela(response.getOutputStream(),SYSTEM_DATA_NAMES, SYSTEM_ARRY_NAMES, columnWidths, systemDatalist,SYSTEM_ARRY_DATA);

    }

}
