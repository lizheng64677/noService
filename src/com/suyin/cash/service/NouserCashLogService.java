package com.suyin.cash.service;

import java.util.*;

import com.suyin.cash.model.*;
import com.suyin.cash.service.*;




public interface NouserCashLogService{

    /**
     * 新增信息
     * @param entity
     * @return
     */
    public Integer addNouserCashLog(NouserCashTeller entity);

    /**
     * 修改信息
     * @param entity
     * @return
     */
    public Integer updateNouserCashLog(Map<String,String> entity);


    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    public List<Map<String,String>> findNouserCashLog(NouserCashTeller entity);

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
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
     * 查询用户钱包变化记录
     * @param coinCashLog
     * @return
     */
    public List<CoinCashLog> queryCoinCashLogByPage(CoinCashLog coinCashLog);

    /**
     * 修改用户支付宝账号
     */
	public Integer updateNum(Map<String, Object> map);

}
