package com.suyin.advs.mapper;


import java.util.List;

import java.util.*;
import com.suyin.advs.model.*;
import com.suyin.advs.service.*;




public interface AdvsMapper {

    /**
     * 新增信息
     */
    public Integer addAdvs(Advs entity);

    /**
     * 修改信息
     */
    public Integer updateAdvs(Advs entity);
    /**
     * 根据id删除单条信息
     * 
     */
    public Integer deleteAdvs(String id);
    /**
     * 批量删除
     */
    public Integer deleteAdvsByBatch(String[] id); 

    /**
     * 查询列表
     */
    public List<Advs> findAdvsList(Advs entity);

    /**
     * 查询列表分页  
     */
    public List<Advs> findAdvsByPage(Advs entity);
    
    /**
     * 根据主键查找
     */
    public Advs findAdvsById(Advs entity);

}
