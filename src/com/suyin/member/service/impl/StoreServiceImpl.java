package com.suyin.member.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.suyin.member.mapper.StoreMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.suyin.system.util.Md5Util;

import java.util.*;
import com.suyin.member.model.*;
import com.suyin.member.service.*;



@Transactional
@Service("StoreService")
public class StoreServiceImpl implements StoreService{

    private final static Logger log=Logger.getLogger(StoreServiceImpl.class);
    
    @Autowired
    private StoreMapper StoreMapper; 

    /**
     * 新增信息
     * @param entity
     * @return
     */
    @Override
    public Integer addStore(Store entity){
        Integer result=0;
        try {

            if(entity==null){
                return result;
            }else{
                result = StoreMapper.addStore(entity);
            }

        } catch (Exception e) {

            new RuntimeException();
        }
        return result;

    }

    /**
     * 修改信息
     * @param entity
     * @return
     */
    @Override
    public Integer updateStore(Store entity){

        Integer result=0;
        try {
            if(entity==null){

                return result;
            }else{

                result = StoreMapper.updateStore(entity);
            }
        } catch (Exception e) {
            
            log.error("Store信息修改异常"+e.getMessage());
            new RuntimeException();
            e.printStackTrace();
        }
        return result;

    }

    /**
     * 删除信息
     * @param id
     * @return
     */
    @Override
    public Integer deleteStore(String id){
        
        
        return StoreMapper.deleteStore(id);
    }

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    @Override
    public List<Store> findStore(Store entity){
        
        
        return StoreMapper.findStore(entity);
    }

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    @Override
    public List<Store> findStoreByPage(Store entity){
        
        
        return StoreMapper.findStoreByPage(entity);
    }

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    @Override
    public Store findStoreById(Store entity){
        
        List<Store> list=StoreMapper.findStore(entity);
        return list!=null&&!list.isEmpty()?list.get(0):null;
    }
}
