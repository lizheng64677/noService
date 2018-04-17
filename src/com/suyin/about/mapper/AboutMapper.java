package com.suyin.about.mapper;


import java.util.*;

import com.suyin.about.model.*;





public interface AboutMapper {

    /**
     * 修改信息
     */
    public Integer updateAbout(About entity);
    
    /**
     * 根据主键查找
     */
    public About findAboutById(About entity);

    /**
     * 查询列表分页  
     */
    public List<About> findAboutByPage(About entity);
    
    /**
     *  根据类型查询
     */
    public List<About> findAboutByType(About entity);
    
    /**
     * 添加信息
     */
    public Integer addAbout(About entity);
    
    /**
     * 删除信息
     */
    public Integer deleteAbout(String id);
    
    /**
     * 批量删除
     */
    public Integer deleteAboutByBatch(String[] id); 
}
