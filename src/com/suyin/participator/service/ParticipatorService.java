package com.suyin.participator.service;

import java.util.List;

import com.suyin.participator.model.Participator;

public interface ParticipatorService{

    /**
     * 新增信息
     * @param entity
     * @return
     */
    public Integer addParticipator(Participator entity);

    /**
     * 修改信息
     * @param entity
     * @return
     */
    public Integer updateParticipator(Participator entity);

    /**
     * 删除信息
     * @param id
     * @return
     */
    public Integer deleteParticipator(String id);

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    public List<Participator> findParticipator(Participator entity);

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    public List<Participator> findParticipatorByPage(Participator entity);

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    public Participator findParticipatorById(Participator entity);
}
