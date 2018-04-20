
package com.suyin.common.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.suyin.common.SystemPropertiesHolder;
import com.suyin.experience.controller.ExperiencePopularityController;
import com.suyin.experience.service.ExpDetailService;
import com.suyin.system.model.BaseExpLogModel;
import com.suyin.system.model.BaseLogModel;
import com.suyin.system.model.ExpLog;
import com.suyin.system.model.UserLog;
import com.suyin.system.service.NoGroupSystemLogService;
import com.suyin.system.util.SendMessage;
import com.suyin.system.util.Tools;
import com.suyin.system.util.VoucherUtil;

/**
 * 
 * 动态自动执行的一些东西 
 * 悄悄的运行
 * @author lz
 * @version 2015-11-23
 * @see ExpTaskController
 * @since
 */
@Component
public class ExpTaskController
{
    private final static Logger log=Logger.getLogger(ExpTaskController.class);
    private final static String FILE_LOG_URL="file_log_url";
    private final static String FILE_EXP_LOG_URL="file_exp_log_url";
    private final static String YEAR_TYPE="yyyyMMdd";
    private final static String FILE_SUFFIX=".txt";
    @Autowired
    private ExpDetailService expDetailService; //活动详情
    @Autowired
    private NoGroupSystemLogService groupSystemLogService;

    /**
     * 人气式_执行发券功能
     * "0 48 15 * * ?"
     *  秒   分     时   日
     *  
     *  24小时制
     */
    //@Scheduled(cron = "0 20 0 * * ?")
    public void  userExpPopVouCher(){

        Map<String,Object>mapInfo=new HashMap<String, Object>();
        //查询符合添加的时间段数据
        List<Map<String,Object>> resultMap=expDetailService.findForExpInfo(mapInfo);
        if(resultMap.size()>0){

            for (int i = 0; i < resultMap.size(); i++ )
            {
                Map<String,Object>submitVouchInfo=new HashMap<String, Object>();
                //根据活动id查询对应的活动详情信息 ，产品数据，及券的有效期等、
                mapInfo.put("expId", resultMap.get(i).get("exp_id"));
                //获取产品名称等信息
                Map<String,Object> resultInfo=expDetailService.findExpPopDetialInfo(mapInfo);
                if(null!=resultInfo){
                    if(null!=resultInfo.get("add_day").toString() && !"0".equals(resultInfo.get("add_day").toString())){

                        int addDay=Integer.parseInt(resultInfo.get("add_day").toString());
                        String validTime=Tools.getValidTime(addDay);
                        //几天后失效日期
                        submitVouchInfo.put("validity",validTime);
                    }else {
                        //自然失效日期
                        submitVouchInfo.put("validity",resultInfo.get("validity").toString());

                    }

                    String detailId=resultInfo.get("exp_detail_id").toString();
                    String proName=resultInfo.get("pro_name").toString();
                    String proNum=resultInfo.get("pro_num").toString();
                    String memberName=resultInfo.get("busname").toString();
                    String address=resultInfo.get("address").toString();
                    String expType="1";//人气式

                    //根据时间段id查询对应的人气分享数
                    mapInfo.put("timeId", resultMap.get(i).get("exp_time_id"));
                    mapInfo.put("proNum", Integer.parseInt(proNum));
                    mapInfo.put("expType",expType);
                    List<Map<String,Object>> orderInfo=expDetailService.findOrderShareTaskInfo(mapInfo);
                    if(orderInfo.size()>0){

                        for (int j = 0; j < orderInfo.size(); j++ )
                        {
                            mapInfo.put("userId",orderInfo.get(j).get("user_id"));
                            Map<String,Object> voucherUser=expDetailService.findVoucherDetialInfo(mapInfo);
                            if(null!=voucherUser){
                                if(0==Integer.parseInt(voucherUser.get("voucher_status").toString())){

                                    //save t_exp_voucher table
                                    submitVouchInfo.put("userId", orderInfo.get(j).get("user_id"));
                                    submitVouchInfo.put("orderId", voucherUser.get("order_id"));
                                    String vouCode=VoucherUtil.getRandomString(12);
                                    submitVouchInfo.put("vouCode",vouCode);
                                    Integer n= expDetailService.addExpVoucher(submitVouchInfo);
                                    if(n>0){
                                        //update t_exp_order voucher_status
                                        Integer order_status=expDetailService.updateUserOrderVoucherStatus(voucherUser.get("order_id").toString());
                                        if(order_status>0){
                                            //ms
                                            SendMessage.orderVoucherMessage(voucherUser.get("user_phone").toString(),vouCode,proName);
                                        }

                                    }
                                }
                            }
                        }
                    }
                    //更新时间段发券标示状态
                    expDetailService.udpateExpTimeVoucherStatus(resultMap.get(i).get("exp_time_id").toString());
                    //更新没有发券的用户为失败状态
                    expDetailService.udpateUserLoserVoucherStatus(resultMap.get(i).get("exp_time_id").toString());
                }else{

                    continue;

                }

            }
        }


    }

    /**
     * 
     * 统计活动日志
     * @throws IOException 
     * @see
     */
   // @Scheduled(cron = "0 25 0 * * ?") 
    public void noGroupTaskExpReadLog() throws IOException{
        int LogNum=groupSystemLogService.findNoGroupExpInfo();
        if(LogNum<1){
            String fileUrl = SystemPropertiesHolder.get(FILE_EXP_LOG_URL);
            //获取前一天的文件
            String fileName =Tools.getDate(new Date(), YEAR_TYPE, -1);
            File file = new File(fileUrl+fileName+FILE_SUFFIX);

            if (!file.exists())
            {
                log.error(fileName+FILE_SUFFIX+"////"+"日志文件读取失败");
                return ;
            }

            String str="";
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
            List<BaseExpLogModel> baseDbLog=new ArrayList<BaseExpLogModel>();
            List<ExpLog> userDbLog=new  ArrayList<ExpLog>();
            while ((str = br.readLine()) != null && !str.equals(""))
            {
                String[]  strs = str.split(",");
                BaseExpLogModel baseLog=new BaseExpLogModel();
                baseLog.setUserId(strs[0]);
                baseLog.setExpId(strs[1]);
                baseLog.setDetaiId(strs[2]);
                baseLog.setClicentType(strs[3]);
                baseLog.setExpType(strs[4]);

                if(!baseDbLog.contains(baseLog)){

                    baseLog.setPv(1);
                    baseDbLog.add(baseLog);
                }else{

                    baseDbLog.get(baseDbLog.indexOf(baseLog)).setPv(baseDbLog.get(baseDbLog.indexOf(baseLog)).getPv()+1);

                }

                ExpLog user=new ExpLog();
                user.setUserId(strs[0]);
                user.setExpId(strs[1]);
                user.setClicentType(strs[3]);
                user.setExpType(strs[4]);
                if(!"-1".equals(user.getUserId())){
                    if(!userDbLog.contains(user)){

                        userDbLog.add(user);
                    }
                }
            }

            List<BaseExpLogModel>uLogs=new ArrayList<BaseExpLogModel>();
            for (ExpLog u:userDbLog){

                BaseExpLogModel log = new BaseExpLogModel();
                log.setExpId(u.getExpId());
                log.setClicentType(u.getClicentType());
                log.setExpType(u.getExpType());
                if (!uLogs.contains(log)){

                    log.setUv(1);
                    uLogs.add(log);
                }else{

                    uLogs.get(uLogs.indexOf(log)).setUv(uLogs.get(uLogs.indexOf(log)).getUv() + 1);
                }

            }
            for (int i = 0; i < baseDbLog.size(); i++ )
            {
                for (int j = 0; j < uLogs.size(); j++ )
                {
                    if(baseDbLog.get(i).getClicentType().equals(uLogs.get(j).getClicentType())){

                        if (baseDbLog.get(i).getExpId().equals(uLogs.get(j).getExpId()) && baseDbLog.get(i).getExpType().equals(uLogs.get(j).getExpType()))
                        {
                            baseDbLog.get(i).setUv(uLogs.get(j).getUv());
                        }
                    }
                }
            }
            try
            {
                groupSystemLogService.inSertExpDbLogs(baseDbLog);
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                log.error(fileName+FILE_SUFFIX+"////"+"活动日志文件插入数据失败");
            }
        }else{


            log.error("活动日志文件已经记录");
        }
    }



    /**
     * 
     * 统计PV UV 直接入库   
     * 写入权重A
     * @throws IOException 
     * @see
     */
    // @Scheduled(cron = "0 30 0 * * ?")   
    public void noGroupTaskReadLog() throws IOException{
        int LogNum=groupSystemLogService.findNoGroupInfo();
        if(LogNum<1){
            String fileUrl = SystemPropertiesHolder.get(FILE_LOG_URL);
            //获取前一天的文件
            String fileName =Tools.getDate(new Date(), YEAR_TYPE, -1);
            File file = new File(fileUrl+fileName+FILE_SUFFIX);

            if (!file.exists())
            {
                log.error(fileName+FILE_SUFFIX+"////"+"日志文件读取失败");
                return ;
            }

            String str="";
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            List<BaseLogModel> baseDbLog=new ArrayList<BaseLogModel>();
            List<UserLog> userDbLog=new  ArrayList<UserLog>();
            while ((str = br.readLine()) != null && !str.equals(""))
            {
                String[]  strs = str.split(",");
                BaseLogModel baseLog=new BaseLogModel();
                baseLog.setModelName(strs[1]);//模块名称           
                baseLog.setModelNumber(strs[2]);//模块编号
                baseLog.setLogType(strs[3]);//记录日志的类型
                baseLog.setClicentType(strs[4]);

                if(!baseDbLog.contains(baseLog)){

                    baseLog.setPv(1);
                    baseDbLog.add(baseLog);
                }else{

                    baseDbLog.get(baseDbLog.indexOf(baseLog)).setPv(baseDbLog.get(baseDbLog.indexOf(baseLog)).getPv()+1);

                }

                UserLog user=new UserLog();
                user.setLogType(strs[3]);
                user.setUserId(strs[0]);
                user.setClicentType(strs[4]);
                if(!"-1".equals(user.getUserId())){
                    if(!userDbLog.contains(user)){

                        userDbLog.add(user);
                    }
                }
            }

            List<BaseLogModel>uLogs=new ArrayList<BaseLogModel>();
            for (UserLog u:userDbLog){

                BaseLogModel log = new BaseLogModel();
                log.setLogType(u.getLogType());
                log.setClicentType(u.getClicentType());
                if (!uLogs.contains(log)){

                    log.setUv(1);
                    uLogs.add(log);
                }else{

                    uLogs.get(uLogs.indexOf(log)).setUv(uLogs.get(uLogs.indexOf(log)).getUv() + 1);
                }

            }
            for (int i = 0; i < baseDbLog.size(); i++ )
            {
                for (int j = 0; j < uLogs.size(); j++ )
                {
                    if(baseDbLog.get(i).getClicentType().equals(uLogs.get(j).getClicentType())){

                        if (baseDbLog.get(i).getLogType().equals(uLogs.get(j).getLogType()))
                        {
                            baseDbLog.get(i).setUv(uLogs.get(j).getUv());
                        }
                    }
                }
            }
            try
            {
                groupSystemLogService.inSertDbLogs(baseDbLog);
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                log.error(fileName+FILE_SUFFIX+"////"+"日志文件插入数据失败");
            }
        }else{


            log.error("日志文件已经记录");
        }
    }


    /**
     * 防止上面的方法，爆内存
     * 预留执行方法操作日志方法
     * 写入权重B
     * @throws IOException 
     * @see
     */
    public void noGroupTaskReadLogCopy() throws IOException{
        String fileUrl = SystemPropertiesHolder.get(FILE_LOG_URL);
        //获取前一天的文件
        String fileName =Tools.getDate(new Date(), YEAR_TYPE, -1);
        File file = new File(fileUrl+fileName+FILE_SUFFIX);

        if (!file.exists())
        {
            log.error(fileName+FILE_SUFFIX+"////"+"日志文件读取失败");
            return ;
        }

        String str="";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
        List<BaseLogModel> baseDbLog=new ArrayList<BaseLogModel>();
        while ((str = br.readLine()) != null && !str.equals(""))
        {
            String[]  strs = str.split(",");
            BaseLogModel baseLog=new BaseLogModel();
            baseLog.setUserId(strs[0]);//userId
            baseLog.setModelName(strs[1]);//模块名称           
            baseLog.setModelNumber(strs[2]);//模块编号
            baseLog.setLogType(strs[3]);//记录日志的类型
            baseLog.setClicentType(strs[4]);
            baseLog.setCreateTime(strs[5]);//日志记录时间
            baseDbLog.add(baseLog);

        }
        try
        {

            //整体循环插入 使用sql进行各项统计 
            groupSystemLogService.inSertDbLogs(baseDbLog);
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            log.error(fileName+FILE_SUFFIX+"////"+"日志文件插入数据失败");
        }
    }


}
