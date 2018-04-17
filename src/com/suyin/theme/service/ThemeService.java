package com.suyin.theme.service;

import java.util.List;

import java.util.*;
import com.suyin.theme.model.*;
import com.suyin.theme.service.*;




public interface ThemeService{

    /**
     * 新增信息
     * @param entity
     * @return
     */
    public Integer addTheme(Theme entity);

    /**
     * 修改信息
     * @param entity
     * @return
     */
    public Integer updateTheme(Theme entity);

    /**
     * 删除信息
     * @param id
     * @return
     */
    public Integer deleteTheme(String id);
    
    
    public Integer deleteThemeByBatch(String[] id);

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    public List<Theme> findTheme(Theme entity);

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    public List<Theme> findThemeByPage(Theme entity);

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    public Theme findThemeById(Theme entity);
}
