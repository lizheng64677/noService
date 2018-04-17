package com.suyin.coin.service;

import java.util.*;

import com.suyin.coin.model.*;




public interface NouserCoinTellerService{

    /**
     * 新增信息
     * @param entity
     * @return
     */
    public Integer addNouserCoinTeller(NouserCoinTeller entity);

    /**
     * 修改信息
     * @param entity
     * @return
     */
    public Integer updateNouserCoinTeller(Map<String, String> entity);

    /**
     * 删除信息
     * @param id
     * @return
     */
    public Integer deleteNouserCoinTeller(String id);


    public Integer deleteNouserCoinTellerByBatch(String[] id);

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    public List<NouserCoinTeller> findNouserCoinTeller(NouserCoinTeller entity);

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    public List<NouserCoinTeller> findNouserCoinTellerByPage(NouserCoinTeller entity);
    /**
     * 查询累积提取金币记录
     * @param entity
     * @return 
     * @see
     */
    public List<NouserCoinTeller> coinTellerRecordList(NouserCoinTeller entity);

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    public NouserCoinTeller findNouserCoinTellerById(NouserCoinTeller entity);
    
    /**
     * 查询用户的金币变化的log
     * @param log
     * @return
     */
	public List<CoinLog> findCoinLogByUserByPage(CoinLog log);
}
