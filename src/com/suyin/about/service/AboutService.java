package com.suyin.about.service;

import java.util.*;

import com.suyin.about.model.*;




public interface AboutService{
    /**
     * 修改信息
     * @param entity
     * @return
     */
    public Integer updateAbout(About entity);
    
    /**
     * 添加信息
     * @param entity
     * @return
     */
    public Integer addAbout(About entity);
    
    /**
     * 删除信息
     * @param entity
     * @return
     */
    
    public Integer deleteAbout(String id);
    
    /**
     * 批量删除
     */
    public Integer deleteAboutByBatch(String[] id); 
    
    
    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    public About findAboutById(About entity);
    
    /**
     * 分页查询
     * @param entity
     * @return
     */
    public List<About> findAboutBypage(About entity);
    
    /**
     * 按type查询
     * @param entity
     * @return
     */
    public List<About> findAboutByType(About entity);
}
