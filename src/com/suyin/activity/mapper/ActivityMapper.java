package com.suyin.activity.mapper;


import java.util.List;

import com.suyin.activity.model.Activity;




public interface ActivityMapper {

    /**
     * 新增信息
     */
    public Integer addActivity(Activity entity);

    /**
     * 修改信息
     */
    public Integer updateActivity(Activity entity);
    /**
     * 根据id删除单条信息
     * 
     */
    public Integer deleteActivity(String id);
    /**
     * 批量删除
     */
    public Integer deleteActivity(String[] id); 

    /**
     * 查询列表
     */
    public List<Activity> findActivity(Activity entity);

    /**
     * 查询列表分页  
     */
    public List<Activity> findActivityByPage(Activity entity);
    
    public Activity findActivityById(Activity entity);


}
