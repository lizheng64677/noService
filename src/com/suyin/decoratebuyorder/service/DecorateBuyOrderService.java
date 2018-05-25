package com.suyin.decoratebuyorder.service;

import java.util.List;

import java.util.*;
import com.suyin.decoratebuyorder.model.*;
import com.suyin.decoratebuyorder.service.*;




public interface DecorateBuyOrderService{

    /**
     * 新增信息
     * @param entity
     * @return
     */
    public Integer addDecorateBuyOrder(DecorateBuyOrder entity);

    /**
     * 修改信息
     * @param entity
     * @return
     */
    public Integer updateDecorateBuyOrder(DecorateBuyOrder entity);

    /**
     * 删除信息
     * @param id
     * @return
     */
    public Integer deleteDecorateBuyOrder(String id);

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    public List<DecorateBuyOrder> findDecorateBuyOrder(DecorateBuyOrder entity);

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    public List<DecorateBuyOrder> findDecorateBuyOrderByPage(DecorateBuyOrder entity);

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    public DecorateBuyOrder findDecorateBuyOrderById(DecorateBuyOrder entity);
}
