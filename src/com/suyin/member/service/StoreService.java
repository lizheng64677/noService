package com.suyin.member.service;

import java.util.List;

import java.util.*;

import com.suyin.member.model.Store;




public interface StoreService{

    /**
     * 新增信息
     * @param entity
     * @return
     */
    public Integer addStore(Store entity);

    /**
     * 修改信息
     * @param entity
     * @return
     */
    public Integer updateStore(Store entity);

    /**
     * 删除信息
     * @param id
     * @return
     */
    public Integer deleteStore(String id);

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    public List<Store> findStore(Store entity);

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    public List<Store> findStoreByPage(Store entity);

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    public Store findStoreById(Store entity);
}
