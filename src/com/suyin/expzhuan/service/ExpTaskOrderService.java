package com.suyin.expzhuan.service;

import java.util.*;

import com.suyin.expzhuan.model.*;

public interface ExpTaskOrderService{


    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    public List<ExpTaskOrder> findExpTaskOrderByPage(ExpTaskOrder entity);

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    public ExpTaskOrder findExpTaskOrderById(ExpTaskOrder entity);


	public void updateExpTaskOrder(String ids, String status);
	
	public List<Map<String,String>> findAllExpTaskA();

	/**找到APP下载的订单详情*/
	public List<Map<String,String>> findExpAppById(ExpTaskOrder order);
	/**找到表单提交的订单详情*/
	public List<Map<String,String>> findExpFormById(ExpTaskOrder order);

	/**
	 * 导出全民赚订单列表
	 * @param entity
	 * @return
	 */
	public List<Map<String, Object>> findNouserCoinTeller(ExpTaskOrder entity);
}
