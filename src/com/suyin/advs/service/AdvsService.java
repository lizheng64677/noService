package com.suyin.advs.service;

import java.util.List;

import java.util.*;
import com.suyin.advs.model.*;
import com.suyin.advs.service.*;




public interface AdvsService{

    /**
     * 新增信息
     * @param entity
     * @return
     */
    public Integer addAdvs(Advs entity);

    /**
     * 修改信息
     * @param entity
     * @return
     */
    public Integer updateAdvs(Advs entity);

    /**
     * 删除信息
     * @param id
     * @return
     */
    public Integer deleteAdvs(String id);
    
    
    public Integer deleteAdvsByBatch(String[] id);

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    public List<Advs> findAdvs(Advs entity);

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    public List<Advs> findAdvsByPage(Advs entity);

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    public Advs findAdvsById(Advs entity);
}
