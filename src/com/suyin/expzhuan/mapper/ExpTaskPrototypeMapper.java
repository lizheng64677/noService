package com.suyin.expzhuan.mapper;


import java.util.List;
import java.util.Map;

import java.util.*;

import com.suyin.experience.model.ExpPrototype;
import com.suyin.expzhuan.model.*;
import com.suyin.expzhuan.service.*;




public interface ExpTaskPrototypeMapper {
    /**
     * 根据id删除单条信息
     * 
     */
    public Integer deleteExpZhuanPrototype(String id);
    /**
     * 添加活动动态属性 
     * @param entity
     * @return 
     * @see
     */
    public Integer addExpZhuanPrototype(ExpTaskPrototype entity);

    /**
     * 根据活动id查询是否配置 
     * @param entity
     * @return 
     * @see
     */
    public ExpTaskPrototype findExpZhuanByExpId(ExpTaskPrototype entity);

    /**
     * 根据活动id查询集合
     * @param entity
     * @return 
     * @see
     */
    public List<ExpTaskPrototype> findExpZhuanByExpIdList(ExpTaskPrototype entity);
    /**
     * 根据活动id查询集合
     * @param entity
     * @return 
     * @see
     */
    public List<ExpTaskPrototype> findIsProtoTypeListByExpId(ExpTaskPrototype entity);
    /**
     * 获取所有活动动态属性
     * @return
     */
    public List<Map<String,Object>> findExpZhuanPrototype(Map<String,Object> map);

    /**
     * 获取动态属性选项
     */
    public List<Map<String,Object>> findExpZhuanPrototypeOption(Map<String,Object> map);
    
 

}
