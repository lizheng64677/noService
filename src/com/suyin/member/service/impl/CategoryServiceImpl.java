package com.suyin.member.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.suyin.member.mapper.CategoryMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.suyin.system.util.Md5Util;

import java.util.*;
import com.suyin.member.model.*;
import com.suyin.member.service.*;



@Transactional
@Service("CategoryService")
public class CategoryServiceImpl implements CategoryService{

    private final static Logger log=Logger.getLogger(CategoryServiceImpl.class);
    
    @Autowired
    private CategoryMapper CategoryMapper; 

    /**
     * 新增信息
     * @param entity
     * @return
     */
    @Override
    public Integer addCategory(Category entity){
        Integer result=0;
        try {

            if(entity==null){
                return result;
            }else{
                result = CategoryMapper.addCategory(entity);
            }

        } catch (Exception e) {

            new RuntimeException();
        }
        return result;

    }

    /**
     * 修改信息
     * @param entity
     * @return
     */
    @Override
    public Integer updateCategory(Category entity){

        Integer result=0;
        try {
            if(entity==null){

                return result;
            }else{

                result = CategoryMapper.updateCategory(entity);
            }
        } catch (Exception e) {
            
            log.error("Category信息修改异常"+e.getMessage());
            new RuntimeException();
            e.printStackTrace();
        }
        return result;

    }

    /**
     * 删除信息
     * @param id
     * @return
     */
    @Override
    public Integer deleteCategory(String id){
        
        
        return CategoryMapper.deleteCategory(id);
    }

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    @Override
    public List<Category> findCategory(Category entity){
        
        
        return CategoryMapper.findCategory(entity);
    }

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    @Override
    public List<Category> findCategoryByPage(Category entity){
        
        
        return CategoryMapper.findCategoryByPage(entity);
    }

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    @Override
    public Category findCategoryById(Category entity){
        
        List<Category> list=CategoryMapper.findCategory(entity);
        return list!=null&&!list.isEmpty()?list.get(0):null;
    }
}
