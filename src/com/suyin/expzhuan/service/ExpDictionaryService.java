package com.suyin.expzhuan.service;

import java.util.List;

import java.util.*;
import com.suyin.expzhuan.model.ExpDictionary;




public interface ExpDictionaryService{

    /**
     * 新增信息
     * @param entity
     * @return
     */
    public Integer addExpDictionary(ExpDictionary entity);

    /**
     * 修改信息
     * @param entity
     * @return
     */
    public Integer updateExpDictionary(ExpDictionary entity);

    /**
     * 删除信息
     * @param id
     * @return
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
     * 查找信息列表
     * @param entity
     * @return
     */
    public List<ExpDictionary> findExpDictionary(ExpDictionary entity);

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    public List<ExpDictionary> findExpDictionaryByPage(ExpDictionary entity);

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    public ExpDictionary findExpDictionaryById(ExpDictionary entity);
}
