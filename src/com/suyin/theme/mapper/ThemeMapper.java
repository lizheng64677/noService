package com.suyin.theme.mapper;


import java.util.List;

import java.util.*;
import com.suyin.theme.model.*;
import com.suyin.theme.service.*;




public interface ThemeMapper {

    /**
     * 新增信息
     */
    public Integer addTheme(Theme entity);

    /**
     * 修改信息
     */
    public Integer updateTheme(Theme entity);
    /**
     * 根据id删除单条信息
     * 
     */
    public Integer deleteTheme(String id);
    /**
     * 批量删除
     */
    public Integer deleteThemeByBatch(String[] id); 

    /**
     * 查询列表
     */
    public List<Theme> findThemeList(Theme entity);

    /**
     * 查询列表分页  
     */
    public List<Theme> findThemeByPage(Theme entity);
    
    /**
     * 根据主键查找
     */
    public Theme findThemeById(Theme entity);

}
