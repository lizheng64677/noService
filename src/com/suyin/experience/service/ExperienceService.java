package com.suyin.experience.service;

import java.util.List;
import java.util.Map;

import java.util.*;
import com.suyin.experience.model.*;
import com.suyin.experience.service.*;




public interface ExperienceService{

    /**
     * 新增信息
     * @param entity
     * @return
     */
    public Integer addExperience(Experience entity);
    /**
     * 时间段
     */
    public Integer addExperienceDetailTime(Map<String,Object> map);

    /**
     * 修改信息
     * @param entity
     * @return
     */
    public Integer updateExperience(Experience entity);
    /**
     * 置顶序号
     * @param map
     * @return 
     * @see
     */
    public Integer updataSeqNum(Map<String,Object> map);

    /**
     * 修改活动状态
     * @param entity
     * @return
     */
    public Integer updateExpIsBegin(Experience entity);

    /**
     * 删除信息
     * @param id
     * @return
     */
    public Integer deleteExperience(String id);

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    public List<Experience> findExperience(Experience entity);

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    public List<Experience> findExperienceByPage(Experience entity);


    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    public Experience findExperienceById(Experience entity);
}
