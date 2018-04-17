package com.suyin.member.service;

import java.util.List;

import java.util.*;
import com.suyin.member.model.*;
import com.suyin.member.service.*;




public interface CategoryService{

    /**
     * 新增信息
     * @param entity
     * @return
     */
    public Integer addCategory(Category entity);

    /**
     * 修改信息
     * @param entity
     * @return
     */
    public Integer updateCategory(Category entity);

    /**
     * 删除信息
     * @param id
     * @return
     */
    public Integer deleteCategory(String id);

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    public List<Category> findCategory(Category entity);

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    public List<Category> findCategoryByPage(Category entity);

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    public Category findCategoryById(Category entity);
}
