package com.suyin.falsedata.mapper;


import java.util.*;

import com.suyin.falsedata.model.*;
import com.suyin.falsedata.service.*;




public interface FalseNouserMapper {

    /**
     * 新增信息
     */
    public Integer addFalseNouser(FalseNouser entity);

    /**
     * 批量插入数据(手机号)
     */
    public int addFalseNouserByBatch(List<Map<String, String>> entity);

    /**
     * 修改信息
     */
    public Integer updateFalseNouser(FalseNouser entity);
    /**
     * 根据id删除单条信息
     * 
     */
    public Integer deleteFalseNouser(String id);
    /**
     * 批量删除
     */
    public Integer deleteFalseNouser(String[] id); 

    /**
     * 查询列表
     */
    public List<FalseNouser> findFalseNouser(FalseNouser entity);

    /**
     * 查询列表分页  
     */
    public List<FalseNouser> findFalseNouserByPage(FalseNouser entity);

}
