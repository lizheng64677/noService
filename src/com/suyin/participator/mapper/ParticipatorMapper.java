package com.suyin.participator.mapper;


import java.util.List;

import com.suyin.participator.model.Participator;

public interface ParticipatorMapper {

    /**
     * 新增信息
     */
    public Integer addParticipator(Participator entity);

    /**
     * 修改信息
     */
    public Integer updateParticipator(Participator entity);
    /**
     * 根据id删除单条信息
     * 
     */
    public Integer deleteParticipator(String id);
    /**
     * 批量删除
     */
    public Integer deleteParticipator(String[] id); 

    /**
     * 查询列表
     */
    public List<Participator> findParticipator(Participator entity);

    /**
     * 查询列表分页  
     */
    public List<Participator> findParticipatorByPage(Participator entity);
    
    public Participator findParticipatorById(Participator entity);

}
