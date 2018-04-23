package com.suyin.expdecorateorder.service;

import java.util.List;

import com.suyin.expdecorateorder.model.DecorateOrderDTO;
import com.suyin.expdecorateorder.model.ExpDecorateOrder;




public interface ExpDecorateOrderService{

    /**
     * 新增信息
     * @param entity
     * @return
     */
    public Integer addExpDecorateOrder(ExpDecorateOrder entity);

    /**
     * 修改信息
     * @param entity
     * @return
     */
    public Integer updateExpDecorateOrder(ExpDecorateOrder entity);
    /**
     * 
     * @param entity
     * @return
     */
    public Integer reviewExpDecorateOrderById(ExpDecorateOrder entity);

    /**
     * 删除信息
     * @param id
     * @return
     */
    public Integer deleteExpDecorateOrder(String id);

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    public List<ExpDecorateOrder> findExpDecorateOrder(ExpDecorateOrder entity);

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    public List<DecorateOrderDTO> findExpDecorateOrderByPage(ExpDecorateOrder entity);

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    public ExpDecorateOrder findExpDecorateOrderById(ExpDecorateOrder entity);
}
