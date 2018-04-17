package com.suyin.experience.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.suyin.experience.mapper.ExpDetailMapper;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suyin.system.util.Md5Util;
import com.suyin.system.util.SendMessage;
import com.suyin.system.util.Tools;
import com.suyin.system.util.VoucherUtil;

import java.util.*;

import com.suyin.experience.model.*;
import com.suyin.experience.service.*;



@Transactional
@Service("ExpDetailService")
public class ExpDetailServiceImpl implements ExpDetailService{

    private final static Logger log=Logger.getLogger(ExpDetailServiceImpl.class);

    @Autowired
    private ExpDetailMapper ExpDetailMapper; 

    /**
     * 新增信息
     * @param entity
     * @return
     */
    @Override
    public Integer addExpDetail(ExpDetail entity){
        Integer result=0;
        try {

            if(entity==null){
                return result;
            }else{
                result = ExpDetailMapper.addExpDetail(entity);
            }

        } catch (Exception e) {

            new RuntimeException();
        }
        return result;

    }

    /**
     * 修改信息
     * @param entity
     * @return
     */
    @Override
    public Integer updateExpDetail(ExpDetail entity){

        Integer result=0;
        try {
            if(entity==null){

                return result;
            }else{

                result = ExpDetailMapper.updateExpDetail(entity);
            }
        } catch (Exception e) {

            log.error("ExpDetail信息修改异常"+e.getMessage());
            new RuntimeException();
            e.printStackTrace();
        }
        return result;

    }

    /**
     * 删除信息
     * @param id
     * @return
     */
    @Override
    public Integer deleteExpDetail(String id){


        return ExpDetailMapper.deleteExpDetail(id);
    }

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    @Override
    public List<ExpDetail> findExpDetail(ExpDetail entity){


        return ExpDetailMapper.findExpDetail(entity);
    }

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    @Override
    public List<ExpDetail> findExpDetailByPage(ExpDetail entity){


        return ExpDetailMapper.findExpDetailByPage(entity);
    }

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    @Override
    public ExpDetail findExpDetailById(ExpDetail entity){

        List<ExpDetail> list=ExpDetailMapper.findExpDetail(entity);
        return list!=null&&!list.isEmpty()?list.get(0):null;
    }

    /**
     * 根据活动详情查询，是否已经添加活动产品
     */
    @Override
    public List<ExpDetail> findExpDetailByExpId(ExpDetail entity)
    {
        // TODO Auto-generated method stub
        return ExpDetailMapper.findExpDetailByExpId(entity);
    }

    /**
     * 根据活动id查询 
     */
    @Override
    public ExpDetail findExpDetailWhereByExpId(ExpDetail entity)
    {
        // TODO Auto-generated method stub
        List<ExpDetail> list= ExpDetailMapper.findExpDetailByExpId(entity);
        return list!=null&&!list.isEmpty()?list.get(0):null;
    }


    /**
     * 根据活动id删除
     */
    @Override
    public Integer deleteExpDetailByExpId(String expId)
    {
        // TODO Auto-generated method stub
        return ExpDetailMapper.deleteExpDetailByExpId(expId);
    }

    /**
     * 根据活动id查询对应时间段
     */
    @Override
    public List<Map<String, Object>> findForExpTime(Map<String, Object> mapInfo)
    {
        // TODO Auto-generated method stub
        return ExpDetailMapper.findForExpTime(mapInfo);
    }

    /**
     * 选择时间段查询人气用户时，
     * 当前选择的查询时间段是否在合法的条件内
     * 条件合法进入下步操作，不合法做出相应提示
     * @param request
     * @return 
     * @see
     */
    @Override
    public Map<String, Object> findForIsExpTimeStatus(Map<String, Object> mapInfo)
    {
        // TODO Auto-generated method stub
        return ExpDetailMapper.findForIsExpTimeStatus(mapInfo);
    }

    /**
     * 查找发券用户信息列表(分页)
     * @param entity
     * @return
     */
    @Override
    public List<ExpTime> findExpVoucherByPage(ExpTime entity)
    {
        // TODO Auto-generated method stub
        return ExpDetailMapper.findExpVoucherByPage(entity);
    }

    /**
     * 人气发券
     * 测试
     */
    @Override
    public String sendUserPopInfo(Map<String, Object> mapInfo)
    {
        // TODO Auto-generated method stub
        String result="";
        String params=mapInfo.get("userIdArray").toString();
        String expId=mapInfo.get("expId").toString();
        String timeId= mapInfo.get("timeId").toString();

        if(null!=mapInfo){
            Map<String,Object>submitVouchInfo=new HashMap<String, Object>();
            //根据活动id查询活动详情的基本信息用于短信发送内容的填充
            Map<String,Object> resultInfo=ExpDetailMapper.findExpPopDetialInfo(mapInfo);
            if(null!=resultInfo.get("add_day").toString() && !"0".equals(resultInfo.get("add_day").toString())){

                int addDay=Integer.parseInt(resultInfo.get("add_day").toString());
                String validTime=Tools.getValidTime(addDay);
                //几天后失效日期
                submitVouchInfo.put("validity",validTime);
            }else {
                //自然失效日期
                submitVouchInfo.put("validity",resultInfo.get("validity").toString());

            }
            String[] resultParams=params.split(",");
            String detailId=resultInfo.get("exp_detail_id").toString();
            String proName=resultInfo.get("pro_name").toString();
            String memberName=resultInfo.get("busname").toString();
            String address=resultInfo.get("address").toString();
            String expType="1";//人气式
            Map<String,Object>findUserInfo=new HashMap<String, Object>();
            for (int i = 0; i < resultParams.length; i++ )
            {
                String userId=resultParams[i].toString();
                findUserInfo.put("userId", userId);
                findUserInfo.put("expId", expId);
                findUserInfo.put("timeId", timeId);
                findUserInfo.put("expType", expType);
                Map<String,Object> voucherUser=ExpDetailMapper.findVoucherDetialInfo(findUserInfo);
                if(0==Integer.parseInt(voucherUser.get("voucher_status").toString())){

                    //save t_exp_voucher table
                    submitVouchInfo.put("userId", userId);
                    submitVouchInfo.put("orderId", voucherUser.get("order_id"));
                    String vouCode=VoucherUtil.getRandomString(12);
                    submitVouchInfo.put("vouCode",vouCode);
                    Integer n= ExpDetailMapper.addExpVoucher(submitVouchInfo);
                    if(n>0){
                        //update t_exp_order voucher_status
                        Integer order_status=ExpDetailMapper.updateUserOrderVoucherStatus(voucherUser.get("order_id").toString());
                        if(order_status>0){
                            //ms
                            SendMessage.orderVoucherMessage(voucherUser.get("user_phone").toString(),vouCode,proName);

                        }

                    }
                }

            }

            //更新时间段发券标示状态
            ExpDetailMapper.udpateExpTimeVoucherStatus(timeId);
            //更新没有发券的用户为失败状态
            ExpDetailMapper.udpateUserLoserVoucherStatus(timeId);
            result="success";
        }else{

            result="error";
        }


        return  result;
    }


    /**
     * 查询符合定时触发任务数据
     */
    @Override
    public List<Map<String, Object>> findForExpInfo(Map<String, Object> mapInfo)
    {
        // TODO Auto-generated method stub
        return ExpDetailMapper.findForExpInfo(mapInfo);
    }

    /**
     * 发券补充信息
     */
    @Override
    public Map<String, Object> findExpPopDetialInfo(Map<String, Object> mapInfo)
    {
        // TODO Auto-generated method stub
        return ExpDetailMapper.findExpPopDetialInfo(mapInfo);
    }


    /**
     * 人气式——定时任务数据查询
     */
    @Override
    public List<Map<String, Object>> findOrderShareTaskInfo(Map<String, Object> mapInfo)
    {
        // TODO Auto-generated method stub
        return ExpDetailMapper.findOrderShareTaskInfo(mapInfo);
    }

    /**
     * 发券时根据 userId 活动id 查询待发券用户的基本信息
     * @param mapInfo
     * @return 
     * @see
     */
    @Override
    public Map<String, Object> findVoucherDetialInfo(Map<String, Object> mapInfo)
    {
        // TODO Auto-generated method stub
        return ExpDetailMapper.findVoucherDetialInfo(mapInfo);
    }

    /* (non-Javadoc)
     * @see com.suyin.experience.service.ExpDetailService#addExpVoucher(java.util.Map)
     */
    @Override
    public Integer addExpVoucher(Map<String, Object> mapInfo)
    {
        // TODO Auto-generated method stub
        return ExpDetailMapper.addExpVoucher(mapInfo);
    }

    /* (non-Javadoc)
     * @see com.suyin.experience.service.ExpDetailService#updateUserOrderVoucherStatus(java.lang.String)
     */
    @Override
    public Integer updateUserOrderVoucherStatus(String orderId)
    {
        // TODO Auto-generated method stub
        return ExpDetailMapper.updateUserOrderVoucherStatus(orderId);
    }

    /* (non-Javadoc)
     * @see com.suyin.experience.service.ExpDetailService#udpateExpTimeVoucherStatus(java.lang.String)
     */
    @Override
    public Integer udpateExpTimeVoucherStatus(String timeId)
    {
        // TODO Auto-generated method stub
        return ExpDetailMapper.udpateExpTimeVoucherStatus(timeId);
    }

    /* (non-Javadoc)
     * @see com.suyin.experience.service.ExpDetailService#udpateUserLoserVoucherStatus(java.lang.String)
     */
    @Override
    public Integer udpateUserLoserVoucherStatus(String timeId)
    {
        // TODO Auto-generated method stub
        return ExpDetailMapper.udpateUserLoserVoucherStatus(timeId);
    }

    
    
	@Override
	public List<ExpTime> queryForUserListByPage(ExpTime entityInfo) {
		return ExpDetailMapper.queryForUserListByPage(entityInfo);
	}

	/**
	 * 新增用户参与活动
	 */
	@Override
	public Integer userPart(List<ExpTime> list) {
		return ExpDetailMapper.userPart(list);
	}

	/**
	 * 根据活动id 期数id查询订单列表
	 */
	@Override
	public List<ExpTime> queryUserPartListByPage(ExpTime entityInfo) {
		return ExpDetailMapper.queryUserPartListByPage(entityInfo);
	}

	/**
	 * 查询单条订单信息
	 */
	@Override
	public ExpTime queryOneOrder(ExpTime entityInfo) {
		return ExpDetailMapper.queryOneOrder(entityInfo);
	}

	/**
	 * 修改订单信息
	 */
	@Override
	public Integer updateOrder(ExpTime entityInfo) {
		return ExpDetailMapper.updateOrder(entityInfo);
	}
}
