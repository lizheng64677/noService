package com.suyin.actinfo.service;

import java.util.List;

import java.util.*;
import com.suyin.actinfo.model.*;
import com.suyin.actinfo.service.*;




public interface ActIntroService{

    /**
     * 新增信息
     * @param entity
     * @return
     */
    public Integer addActIntro(ActIntro entity);

    /**
     * 修改信息
     * @param entity
     * @return
     */
    public Integer updateActIntro(ActIntro entity);

    /**
     * 删除信息
     * @param id
     * @return
     */
    public Integer deleteActIntro(String id);

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    public List<ActIntro> findActIntro(ActIntro entity);

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    public List<ActIntro> findActIntroByPage(ActIntro entity);

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    public ActIntro findActIntroById(ActIntro entity);
}
