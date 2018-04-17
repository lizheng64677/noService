package com.suyin.experience.mapper;


import java.util.*;

import com.suyin.experience.model.*;
import com.suyin.experience.service.*;




public interface ExpDetailMapper {

    /**
     * 新增信息
     */
    public Integer addExpDetail(ExpDetail entity);

    /**
     * 修改信息
     */
    public Integer updateExpDetail(ExpDetail entity);
    /**
     * 根据id删除单条信息
     * 
     */
    public Integer deleteExpDetail(String id);
    /**
     * 删除活动id信息
     * @param id
     * @return
     */
    public Integer deleteExpDetailByExpId(String expId);
    /**
     * 批量删除
     */
    public Integer deleteExpDetail(String[] id); 

    /**
     * 查询列表
     */
    public List<ExpDetail> findExpDetail(ExpDetail entity);

    /**
     * 查询列表分页  
     */
    public List<ExpDetail> findExpDetailByPage(ExpDetail entity);
    /**
     * 
     * 根据活动id查询 
     * @param entity
     * @return 
     * @see
     */
    public List<ExpDetail>findExpDetailByExpId(ExpDetail entity);
    /**
     * 根据活动id查询对应时间段
     * @param mapInfo
     * @return 
     * @see
     */
    public List<Map<String, Object>> findForExpTime(Map<String, Object> mapInfo);
    /**
     * 选择时间段查询人气用户时，
     * 当前选择的查询时间段是否在合法的条件内
     * 条件合法进入下步操作，不合法做出相应提示
     * @param request
     * @return 
     * @see
     */
    public Map<String,Object>findForIsExpTimeStatus(Map<String,Object>mapInfo);
    /**
     * 
     * 查询时间段内符合条件的定时触发条件
     * @param mapInfo
     * @return 
     * @see
     */
    public List<Map<String, Object>> findForExpInfo(Map<String,Object> mapInfo);

    /**
     * 查找发券用户信息列表(分页)
     * @param entity
     * @return
     */
    public List<ExpTime> findExpVoucherByPage(ExpTime entity);

    /**
     * 人气发券
     * @param mapInfo
     * @return 
     * @see
     */
    public String sendUserPopInfo(Map<String, Object> mapInfo);
    /**
     * 根据活动id查询活动详情的基本信息用于短信发送内容的填充
     * @param mapInfo
     * @return 
     * @see
     */
    public Map<String,Object>findExpPopDetialInfo(Map<String, Object> mapInfo);
    /**
     * 发券时根据 userId 活动id 查询待发券用户的基本信息
     * @param mapInfo
     * @return 
     * @see
     */
    public Map<String,Object>findVoucherDetialInfo(Map<String,Object> mapInfo);
    /**
     * 人气发券保存操作
     * @param mapInfo
     * @return 
     * @see
     */
    public Integer addExpVoucher(Map<String, Object> mapInfo);
    /**
     * 更新订单表中发券的状态
     * 
     * @param orderId
     * @return 
     * @see
     */
    public Integer updateUserOrderVoucherStatus(String orderId);
    /**
     * 没有发券的用户给人家个失败状态不过分吧、
     * @param timeId
     * @return 
     * @see
     */
    public Integer udpateUserLoserVoucherStatus(String timeId);

    /**
     * 更新时间段内的是否发券标示
     * @param timeId
     * @return 
     * @see
     */
    public Integer udpateExpTimeVoucherStatus(String timeId);
    /**
     * 查询时间段内符合条件的分享用户信息
     * 这个TMD 是人气式的
     * @param mapInfo
     * @return 
     * @see
     */
    public List<Map<String, Object>>findOrderShareTaskInfo(Map<String,Object>mapInfo);

    /**
     * 查询可以参加活动的用户
     */
	public List<ExpTime> queryForUserListByPage(ExpTime entityInfo);

	/**
	 * 新增用户参与活动
	 */
	public Integer userPart(List<ExpTime> list);

	/**
	 * 查询虚拟用户参与活动订单列表
	 */
	public List<ExpTime> queryUserPartListByPage(ExpTime entityInfo);

	/**
	 * 查询单条订单信息
	 */
	public ExpTime queryOneOrder(ExpTime entityInfo);

	/**
	 * 修改订单信息
	 */
	public Integer updateOrder(ExpTime entityInfo);

}
