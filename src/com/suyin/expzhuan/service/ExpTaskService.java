package com.suyin.expzhuan.service;

import java.util.List;
import java.util.Map;

import java.util.*;
import com.suyin.expzhuan.model.*;
import com.suyin.expzhuan.service.*;
import com.suyin.system.model.Attachment;




public interface ExpTaskService{

    /**
     * 新增信息
     * @param entity
     * @return
     */
    public Integer addExpZhuan(ExpTask entity);

    /**
     * 修改信息
     * @param entity
     * @return
     */
    public Integer updateExpZhuan(ExpTask entity);
    /**
     * 修改活动状态
     * @param entity
     * @return 
     * @see
     */
    public Integer updateExpIsBegin(ExpTask entity);
    /**
     * 置顶序号
     * @param map
     * @return 
     * @see
     */
    public Integer updataSeqNum(Map<String,Object> map);
    /**
     * 删除信息
     * @param id
     * @return
     */
    public Integer deleteExpZhuan(String id);

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    public List<ExpTask> findExpZhuan(ExpTask entity);

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    public List<ExpTask> findExpZhuanByPage(ExpTask entity);

    /**
     * 查询附件 
     * @param entity
     * @return
     */
    public List<Attachment> findExpAttachmentByEntityExpId(Attachment entity);

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    public ExpTask findExpZhuanById(ExpTask entity);
    /**
     * 更新全民赚app下载 图片示例及app描述信息
     * @param entity
     * @return 
     * @see
     */
    public Integer updateExpAppImagesInfo(ExpTask entity);


}
