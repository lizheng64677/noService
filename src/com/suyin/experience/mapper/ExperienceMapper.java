package com.suyin.experience.mapper;


import java.util.List;
import java.util.Map;

import java.util.*;
import com.suyin.experience.model.*;
import com.suyin.experience.service.*;




public interface ExperienceMapper {

    /**
     * 新增信息
     */
    public Integer addExperience(Experience entity);
    /**
     * 时间段
     */
    public Integer addExperienceDetailTime(Map<String,Object> map);

    /**
     * 修改信息
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
     * @see
     */
    public Integer updateExpIsBegin(Experience entity);
    /**
     * 根据id删除单条信息
     * 
     */
    public Integer deleteExperience(String id);
    /**
     * 批量删除
     */
    public Integer deleteExperience(String[] id); 

    /**
     * 查询列表
     */
    public List<Experience> findExperience(Experience entity);

    /**
     * 查询列表分页  
     */
    public List<Experience> findExperienceByPage(Experience entity);

}
