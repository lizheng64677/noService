package com.suyin.decorate.service;

import java.util.List;

import java.util.*;
import com.suyin.decorate.model.*;
import com.suyin.decorate.service.*;




public interface ExpDecorateService{

    /**
     * 新增信息
     * @param entity
     * @return
     */
    public Integer addExpDecorate(ExpDecorate entity);

    /**
     * 修改信息
     * @param entity
     * @return
     */
    public Integer updateExpDecorate(ExpDecorate entity);

    /**
     * 删除信息
     * @param id
     * @return
     */
    public Integer deleteExpDecorate(String id);

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    public List<ExpDecorate> findExpDecorate(ExpDecorate entity);

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    public List<ExpDecorate> findExpDecorateByPage(ExpDecorate entity);

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    public ExpDecorate findExpDecorateById(ExpDecorate entity);
}
