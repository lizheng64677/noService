package com.suyin.member.mapper;


import java.util.List;

import java.util.*;
import com.suyin.member.model.*;
import com.suyin.member.service.*;




public interface RegionMapper {

    /**
     * 新增信息
     */
    public Integer addRegion(Region entity);

    /**
     * 修改信息
     */
    public Integer updateRegion(Region entity);
    /**
     * 根据id删除单条信息
     * 
     */
    public Integer deleteRegion(String id);
    /**
     * 批量删除
     */
    public Integer deleteRegion(String[] id); 

    /**
     * 查询列表
     */
    public List<Region> findRegion(Region entity);

    /**
     * 查询列表分页  
     */
    public List<Region> findRegionByPage(Region entity);

}
