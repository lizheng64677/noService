package com.suyin.cash.mapper;


import java.util.*;

import com.suyin.cash.model.*;
import com.suyin.cash.service.*;




public interface NouserCashLogMapper {

    /**
     * 新增信息
     */
    public Integer addNouserCashLog(NouserCashTeller entity);

    /**
     * 修改信息
     */
    public Integer updateNouserCashLog(NouserCashTeller entity);
    /**
     * 根据id删除单条信息
     * 
     */
    public Integer deleteNouserCashLog(String id);
    /**
     * 批量删除
     */
    public Integer deleteNouserCashLogByBatch(String[] id); 

    /**
     * 查询列表
     */
    public List<Map<String,String>> findNouserCashLogList(NouserCashTeller entity);

    /**
     * 查询列表分页  
     */
    public List<Map<String,String>> findNouserCashLogByPage(NouserCashTeller entity);
    /**
     * 提现次数统计查询
     * @param entity
     * @return 
     * @see
     */
    public List<Map<String,Object>>cashTellerRecordList(NouserCashTeller entity);
    /**
     * 根据主键查找
     */
    public NouserCashTeller findNouserCashLogById(NouserCashTeller entity);

	public void updateNouserCashLogStatus(Map<String, String> entity);

	public void updateNouserCash(Map<String, String> entity);
	
	public Integer updataNouserCashLogoInfo(Map<String,String>entity);
	
	/**
	 * 查询用户钱包金额变化记录
	 * @param coinCashLog
	 * @return
	 */
	public List<CoinCashLog> queryCoinCashLogByPage(CoinCashLog coinCashLog);

	/**
	 * 根据用户id修改用户静态表支付宝账号
	 */
	public Integer updateNum(Map<String, Object> map);
	/**
	 * 根据用户id修改用户日志表支付宝账号
	 */
	public Integer updateNumLog(Map<String, Object> map);

}
