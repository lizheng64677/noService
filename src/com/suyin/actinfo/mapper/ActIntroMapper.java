package com.suyin.actinfo.mapper;


import java.util.List;

import com.suyin.actinfo.model.ActIntro;


public interface ActIntroMapper
{

    /**
     * 新增信息
     */
    public Integer addActIntro(ActIntro entity);

    /**
     * 修改信息
     */
    public Integer updateActIntro(ActIntro entity);

    /**
     * 根据id删除单条信息
     * 
     */
    public Integer deleteActIntro(String id);

    /**
     * 批量删除
     */
    public Integer deleteActIntro(String[] id);

    /**
     * 查询列表
     */
    public List<ActIntro> findActIntro(ActIntro entity);

    /**
     * 查询列表分页  
     */
    public List<ActIntro> findActIntroByPage(ActIntro entity);

    public List<ActIntro> findActIntroById(ActIntro entity);

}
