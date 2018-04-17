package com.suyin.member.mapper;


import java.util.List;

import java.util.*;

import com.suyin.member.model.Store;




public interface StoreMapper {

    /**
     * 新增信息
     */
    public Integer addStore(Store entity);

    /**
     * 修改信息
     */
    public Integer updateStore(Store entity);
    /**
     * 根据id删除单条信息
     * 
     */
    public Integer deleteStore(String id);
    /**
     * 批量删除
     */
    public Integer deleteStore(String[] id); 

    /**
     * 查询列表
     */
    public List<Store> findStore(Store entity);

    /**
     * 查询列表分页  
     */
    public List<Store> findStoreByPage(Store entity);

}
