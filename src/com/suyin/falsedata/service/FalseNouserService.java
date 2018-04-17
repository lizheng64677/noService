package com.suyin.falsedata.service;

import java.util.*;

import com.suyin.falsedata.model.*;
import com.suyin.falsedata.service.*;




public interface FalseNouserService{

    /**
     * 新增信息
     * @param entity
     * @return
     */
    public Integer addFalseNouser(FalseNouser entity);

    /**
     * 批量插入数据(手机号)
     */
    public int addFalseNouserByBatch(List<Map<String, String>> entity);
    
    /**
     * 修改信息
     * @param entity
     * @return
     */
    public Integer updateFalseNouser(FalseNouser entity);

    /**
     * 删除信息
     * @param id
     * @return
     */
    public Integer deleteFalseNouser(String id);

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    public List<FalseNouser> findFalseNouser(FalseNouser entity);

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    public List<FalseNouser> findFalseNouserByPage(FalseNouser entity);

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    public FalseNouser findFalseNouserById(FalseNouser entity);
}
