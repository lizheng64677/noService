package com.suyin.thememonth.service;

import java.util.List;

import java.util.*;
import com.suyin.thememonth.model.*;
import com.suyin.thememonth.service.*;




public interface ThemeMonthService{

    /**
     * 新增信息
     * @param entity
     * @return
     */
    public Integer addThemeMonth(ThemeMonth entity);

    /**
     * 修改信息
     * @param entity
     * @return
     */
    public Integer updateThemeMonth(ThemeMonth entity);

    /**
     * 删除信息
     * @param id
     * @return
     */
    public Integer deleteThemeMonth(String id);

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    public List<ThemeMonth> findThemeMonth(ThemeMonth entity);

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    public List<ThemeMonth> findThemeMonthByPage(ThemeMonth entity);

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    public ThemeMonth findThemeMonthById(ThemeMonth entity);
}
