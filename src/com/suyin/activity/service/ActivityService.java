package com.suyin.activity.service;

import java.util.List;

import com.suyin.activity.model.Activity;




public interface ActivityService{

    /**
     * 新增信息
     * @param entity
     * @return
     */
    public Integer addActivity(Activity entity);

    /**
     * 修改信息
     * @param entity
     * @return
     */
    public Integer updateActivity(Activity entity);

    /**
     * 删除信息
     * @param id
     * @return
     */
    public Integer deleteActivity(String id);

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    public List<Activity> findActivity(Activity entity);

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    public List<Activity> findActivityByPage(Activity entity);

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    public Activity findActivityById(Activity entity);
}
