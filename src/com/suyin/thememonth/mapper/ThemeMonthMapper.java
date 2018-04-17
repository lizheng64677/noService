package com.suyin.thememonth.mapper;


import java.util.List;

import java.util.*;
import com.suyin.thememonth.model.*;
import com.suyin.thememonth.service.*;




public interface ThemeMonthMapper {

    /**
     * 新增信息
     */
    public Integer addThemeMonth(ThemeMonth entity);

    /**
     * 修改信息
     */
    public Integer updateThemeMonth(ThemeMonth entity);
    /**
     * 根据id删除单条信息
     * 
     */
    public Integer deleteThemeMonth(String id);
    /**
     * 批量删除
     */
    public Integer deleteThemeMonth(String[] id); 

    /**
     * 查询列表
     */
    public List<ThemeMonth> findThemeMonth(ThemeMonth entity);

    /**
     * 查询列表分页  
     */
    public List<ThemeMonth> findThemeMonthByPage(ThemeMonth entity);

}
