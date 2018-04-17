package com.suyin.member.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.suyin.member.mapper.RegionMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.suyin.system.util.Md5Util;

import java.util.*;
import com.suyin.member.model.*;
import com.suyin.member.service.*;



@Transactional
@Service("RegionService")
public class RegionServiceImpl implements RegionService{

    private final static Logger log=Logger.getLogger(RegionServiceImpl.class);
    
    @Autowired
    private RegionMapper RegionMapper; 

    /**
     * 新增信息
     * @param entity
     * @return
     */
    @Override
    public Integer addRegion(Region entity){
        Integer result=0;
        try {

            if(entity==null){
                return result;
            }else{
                result = RegionMapper.addRegion(entity);
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
    public Integer updateRegion(Region entity){

        Integer result=0;
        try {
            if(entity==null){

                return result;
            }else{

                result = RegionMapper.updateRegion(entity);
            }
        } catch (Exception e) {
            
            log.error("Region信息修改异常"+e.getMessage());
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
    public Integer deleteRegion(String id){
        
        
        return RegionMapper.deleteRegion(id);
    }

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    @Override
    public List<Region> findRegion(Region entity){
        
        
        return RegionMapper.findRegion(entity);
    }

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    @Override
    public List<Region> findRegionByPage(Region entity){
        
        
        return RegionMapper.findRegionByPage(entity);
    }

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    @Override
    public Region findRegionById(Region entity){
        
        List<Region> list=RegionMapper.findRegion(entity);
        return list!=null&&!list.isEmpty()?list.get(0):null;
    }
}
