package com.suyin.decorateuser.service;

import java.util.List;

import java.util.*;
import com.suyin.decorateuser.model.*;
import com.suyin.decorateuser.service.*;




public interface ExpDecorateUserService{

    /**
     * 新增信息
     * @param entity
     * @return
     */
    public Integer addExpDecorateUser(ExpDecorateUser entity);

    /**
     * 修改信息
     * @param entity
     * @return
     */
    public Integer updateExpDecorateUser(ExpDecorateUser entity);

    /**
     * 删除信息
     * @param id
     * @return
     */
    public Integer deleteExpDecorateUser(String id);

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    public List<ExpDecorateUser> findExpDecorateUser(ExpDecorateUser entity);

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    public List<ExpDecorateUser> findExpDecorateUserByPage(ExpDecorateUser entity);

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    public ExpDecorateUser findExpDecorateUserById(ExpDecorateUser entity);
}
