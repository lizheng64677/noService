package com.suyin.experience.service;

import java.util.*;

import com.suyin.experience.model.*;
import com.suyin.experience.service.*;




public interface ExpPrototypeService{


    /**
     * 删除信息
     * @param id
     * @return
     */
    public Integer deleteExpPrototype(String id);
    
    /**
     * 添加活动动态属性 
     * @param entity
     * @return 
     * @see
     */
    public Integer addExpPrototype(String json);
    /**
     * 
     * 根据活动id t_experience 主键查询 
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
     * 根据活动id查询通透属性值
     * @param expId
     * @return 
     * @see
     */
    public List<Map<String, Object>> findUserPrototype(String expId);

    public Integer addExpTaskPrototype(String json);

	public void deleteExpTaskPrototype(String expId);

	public List<Map<String, Object>> findExpTaskPrototype(String string);
}
