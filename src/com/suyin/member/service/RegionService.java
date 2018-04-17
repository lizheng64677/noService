package com.suyin.member.service;

import java.util.List;

import java.util.*;
import com.suyin.member.model.*;
import com.suyin.member.service.*;




public interface RegionService{

    /**
     * 新增信息
     * @param entity
     * @return
     */
    public Integer addRegion(Region entity);

    /**
     * 修改信息
     * @param entity
     * @return
     */
    public Integer updateRegion(Region entity);

    /**
     * 删除信息
     * @param id
     * @return
     */
    public Integer deleteRegion(String id);

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    public List<Region> findRegion(Region entity);

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    public List<Region> findRegionByPage(Region entity);

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    public Region findRegionById(Region entity);
}
