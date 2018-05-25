package com.suyin.decoratebuyorder.mapper;


import java.util.List;

import java.util.*;
import com.suyin.decoratebuyorder.model.*;
import com.suyin.decoratebuyorder.service.*;




public interface DecorateBuyOrderMapper {

    /**
     * 新增信息
     */
    public Integer addDecorateBuyOrder(DecorateBuyOrder entity);

    /**
     * 修改信息
     */
    public Integer updateDecorateBuyOrder(DecorateBuyOrder entity);
    /**
     * 根据id删除单条信息
     * 
     */
    public Integer deleteDecorateBuyOrder(String id);
    /**
     * 批量删除
     */
    public Integer deleteDecorateBuyOrder(String[] id); 

    /**
     * 查询列表
     */
    public List<DecorateBuyOrder> findDecorateBuyOrder(DecorateBuyOrder entity);

    /**
     * 查询列表分页  
     */
    public List<DecorateBuyOrder> findDecorateBuyOrderByPage(DecorateBuyOrder entity);

}
