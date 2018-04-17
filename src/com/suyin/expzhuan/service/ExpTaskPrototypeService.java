package com.suyin.expzhuan.service;

import java.util.List;
import java.util.Map;

import java.util.*;

import com.suyin.experience.model.ExpPrototype;
import com.suyin.expzhuan.model.*;
import com.suyin.expzhuan.service.*;




public interface ExpTaskPrototypeService{

    /**
     * 删除信息
     * @param id
     * @return
     */
    public Integer deleteExpZhuanPrototype(String id);

    /**
     * 添加活动动态属性 
     * @param entity
     * @return 
     * @see
     */
    public Integer addExpZhuanPrototype(String json);
    /**
     * 
     * 根据活动id t_experience 主键查询 
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
     * 根据活动id查询动态属性值
     * @param expId
     * @return 
     * @see
     */
    public List<Map<String, Object>> findExpDictionaryPrototype(String expId,String moduleType);
}
