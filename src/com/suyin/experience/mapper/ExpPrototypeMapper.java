package com.suyin.experience.mapper;


import java.util.*;

import com.suyin.experience.model.*;
import com.suyin.experience.service.*;




public interface ExpPrototypeMapper {



    /**
     * 根据id删除单条信息
     * 
     */
    public Integer deleteExpPrototype(String id);
    /**
     * 添加活动动态属性 
     * @param entity
     * @return 
     * @see
     */
    public Integer addExpPrototype(ExpPrototype entity);

    /**
     * 根据活动id查询是否配置 
     * @param entity
     * @return 
     * @see
     */
    public ExpPrototype findExpByExpId(ExpPrototype entity);

    /**
     * 根据活动id查询集合
     * @param entity
     * @return 
     * @see
     */
    public List<ExpPrototype> findExpByExpIdList(ExpPrototype entity);
    /**
     * 获取所有活动动态属性
     * @return
     */
    public List<Map<String,Object>> findExpPrototype();

    /**
     * 获取动态属性选项
     */
    public List<Map<String,Object>> findExpPrototypeOption(Map<String,Object> map);
	public void addExpTaskPrototype(ExpPrototype exp);
	public void deleteExpTaskPrototype(String expId);
	public List<Map<String, Object>> findExpTaskPrototype(String string);
	public List<Map<String,Object>>  findExpTaskPrototypeOption(Map<String, Object> param);

}
