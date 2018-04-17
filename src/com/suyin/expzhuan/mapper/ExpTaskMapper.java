package com.suyin.expzhuan.mapper;


import java.util.List;
import java.util.Map;

import java.util.*;
import com.suyin.expzhuan.model.*;
import com.suyin.expzhuan.service.*;




public interface ExpTaskMapper {

    /**
     * 新增信息
     */
    public Integer addExpZhuan(ExpTask entity);

    /**
     * 修改信息
     */
    public Integer updateExpZhuan(ExpTask entity);
    /**
     * 置顶序号
     * @param map
     * @return 
     * @see
     */
    public Integer updataSeqNum(Map<String,Object> map);
    /**
     * 修改活动状态
     * @param entity
     * @return 
     * @see
     */
    public Integer updateExpIsBegin(ExpTask entity);
    /**
     * 根据id删除单条信息
     * 
     */
    public Integer deleteExpZhuan(String id);
    /**
     * 批量删除
     */
    public Integer deleteExpZhuan(String[] id); 

    /**
     * 查询列表
     */
    public List<ExpTask> findExpZhuan(ExpTask entity);

    /**
     * 查询列表分页  
     */
    public List<ExpTask> findExpZhuanByPage(ExpTask entity);
    /**
     * 更新全民赚app下载 图片示例及app描述信息
     * @param entity
     * @return 
     * @see
     */
    public Integer updateExpAppImagesInfo(ExpTask entity);
}
