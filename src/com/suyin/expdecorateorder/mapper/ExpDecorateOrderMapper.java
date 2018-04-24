package com.suyin.expdecorateorder.mapper;


import java.util.List;

import com.suyin.expdecorateorder.model.DecorateOrderDTO;
import com.suyin.expdecorateorder.model.ExpDecorateOrder;




public interface ExpDecorateOrderMapper {
	
	/**
	 * 通过openid修改用户的钱包余额
	 * @param entity
	 * @return
	 */
	 public Integer updateUserBalancePriceByOpenId(ExpDecorateOrder entity);
	/**
	 * 通过订单ID查询订单信息
	 * @param id
	 * @return
	 */
	 public ExpDecorateOrder findExpDecorateOrderByOrderId(String orderId);

    /**
     * 新增信息
     */
    public Integer addExpDecorateOrder(ExpDecorateOrder entity);

    /**
     * 修改信息
     */
    public Integer updateExpDecorateOrder(ExpDecorateOrder entity);
    /**
     * 
     * @param entity
     * @return
     */
    public Integer reviewExpDecorateOrderById(ExpDecorateOrder entity);
    /**
     * 根据id删除单条信息
     * 
     */
    public Integer deleteExpDecorateOrder(String id);
    /**
     * 批量删除
     */
    public Integer deleteExpDecorateOrder(String[] id); 

    /**
     * 查询列表
     */
    public List<ExpDecorateOrder> findExpDecorateOrder(ExpDecorateOrder entity);

    /**
     * 查询列表分页  
     */
    public List<DecorateOrderDTO> findExpDecorateOrderByPage(ExpDecorateOrder entity);

}
