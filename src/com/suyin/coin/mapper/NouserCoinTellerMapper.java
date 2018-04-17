package com.suyin.coin.mapper;


import java.util.List;
import java.util.Map;

import com.suyin.coin.model.NouserCoinTeller;
import com.suyin.coin.model.CoinLog;




public interface NouserCoinTellerMapper {

    /**
     * 修改信息
     */
    public Integer updateNouserCoinTellerStatus(Map<String, String> entity);
    public Integer updateNouserCoinOrMoney(Map<String, String> entity);
    public Integer addCashLog(Map<String, String> entity);
    /**
     * 根据id删除单条信息
     * 
     */
    public Integer deleteNouserCoinTeller(String id);
    /**
     * 批量删除
     */
    public Integer deleteNouserCoinTellerByBatch(String[] id); 

    /**
     * 查询列表
     */
    public List<NouserCoinTeller> findNouserCoinTellerList(NouserCoinTeller entity);

    /**
     * 查询列表分页  
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
     * 根据主键查找
     */
    public NouserCoinTeller findNouserCoinTellerById(NouserCoinTeller entity);
    /**
     * 添加金币记录
     * @param entity 
     * @see
     */
    public void addCoinLog(Map<String, String> entity);
    /**
     * 更新金币操作记录
     * @param entity 
     * @see
     */
    public void updateCoinLog(Map<String, String> entity);
    
    /**
     * 查询用户的金币变化的log
     * @param log
     * @return
     */
	public List<CoinLog> findCoinLogByUserByPage(CoinLog log);

}
