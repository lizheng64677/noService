package com.suyin.expzhuan.mapper;


import java.util.*;

import com.suyin.expzhuan.model.*;


public interface ExpTaskOrderMapper {


    /**
     * 批量修改全民赚的订单状态
     */
    public Integer updateExpTaskOrderStatus(Map<String,String> condition);

    /**
     * 同意的全民赚的订单就要给对应的用户金币，这里批量给金币
     * @param condition
     * @return
     */
    public Integer updateUserCoins(Map<String,String> condition);
    /**
     * 批量更新一下订单获得的金币数
     * @param condition
     * @return
     */
    public Integer updateOrderCoin(Map<String,String> condition);
    /**
     * 批量给用户加金币之后，还有给用户价格金币log
     * @param condition
     * @return
     */
    public Integer addUserCoinLog(Map<String,String> condition);
    /**同意或者拒绝之后要给用户发消息*/
    public Integer addUserMessage(Map<String,String> condition);
    
    public List<Map<String,String>> findAllExpTaskA();
    
    /**
     * 查询列表分页  
     */
    public List<ExpTaskOrder> findExpTaskOrderByPage(ExpTaskOrder entity);
    
    /**
     * 根据主键查找
     */
    public ExpTaskOrder findExpTaskOrderById(ExpTaskOrder entity);

    
	public Map<String, String> findExpAppById(ExpTaskOrder order);

	public List<Map<String, String>> findExpFormById(ExpTaskOrder order);

	/**
	 * 导出全民赚订单列表
	 * @param entity
	 * @return
	 */
	public List<Map<String,Object>> findNouserCoinTeller(ExpTaskOrder entity);

}
