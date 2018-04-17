package com.suyin.expzhuan.mapper;


import java.util.List;

import java.util.*;
import com.suyin.expzhuan.model.ExpDictionary;




public interface ExpDictionaryMapper {

    /**
     * 新增信息
     */
    public Integer addExpDictionary(ExpDictionary entity);

    /**
     * 修改信息
     */
    public Integer updateExpDictionary(ExpDictionary entity);
    /**
     * 根据id删除单条信息
     * 
     */
    public Integer deleteExpDictionary(String id);
    /**
     * 根据活动id删除 
     * @param id
     * @return 
     * @see
     */
    public Integer deleteExpDictionaryWhereByExpId(String id);
    /**
     * 联合删除
     * @param entity
     * @return 
     * @see
     */
    public Integer  deleteExpUnionByExpId(ExpDictionary entity);
    /**
     * 批量删除
     */
    public Integer deleteExpDictionary(String[] id); 

    /**
     * 查询列表
     */
    public List<ExpDictionary> findExpDictionary(ExpDictionary entity);

    /**
     * 查询列表分页  
     */
    public List<ExpDictionary> findExpDictionaryByPage(ExpDictionary entity);

}
