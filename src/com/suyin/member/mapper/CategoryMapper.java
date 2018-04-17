package com.suyin.member.mapper;


import java.util.List;

import java.util.*;
import com.suyin.member.model.*;
import com.suyin.member.service.*;




public interface CategoryMapper {

    /**
     * 新增信息
     */
    public Integer addCategory(Category entity);

    /**
     * 修改信息
     */
    public Integer updateCategory(Category entity);
    /**
     * 根据id删除单条信息
     * 
     */
    public Integer deleteCategory(String id);
    /**
     * 批量删除
     */
    public Integer deleteCategory(String[] id); 

    /**
     * 查询列表
     */
    public List<Category> findCategory(Category entity);

    /**
     * 查询列表分页  
     */
    public List<Category> findCategoryByPage(Category entity);

}
