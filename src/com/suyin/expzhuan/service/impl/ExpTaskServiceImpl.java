package com.suyin.expzhuan.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.suyin.expzhuan.mapper.ExpTaskMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suyin.system.mapper.AttachmentMapper;
import com.suyin.system.model.Attachment;
import com.suyin.system.util.Md5Util;

import java.util.*;
import com.suyin.expzhuan.model.*;
import com.suyin.expzhuan.service.*;



@Transactional
@Service("ExpZhuanService")
public class ExpTaskServiceImpl implements ExpTaskService{

    private final static Logger log=Logger.getLogger(ExpTaskServiceImpl.class);

    @Autowired
    private ExpTaskMapper ExpZhuanMapper; 
    @Autowired
    private AttachmentMapper attachmentMapper;//附件表操作

    /**
     * 新增信息
     * @param entity
     * @return
     */
    @Override
    public Integer addExpZhuan(ExpTask entity){
        Integer result=0;
        try {

            if(entity==null){
                return result;
            }else{
                result = ExpZhuanMapper.addExpZhuan(entity);
                if(entity.getAttachments().size()>0) {
                    for(Attachment a:entity.getAttachments())
                        a.setEntity(entity.getExpId());
                        this.attachmentMapper.addAttachments(entity.getAttachments());
                }
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
    public Integer updateExpZhuan(ExpTask entity){

        Integer result=0;
        try {
            if(entity==null){

                return result;
            }else{

                result = ExpZhuanMapper.updateExpZhuan(entity);
                if(entity.getAttachments().size()>0) {
                    for(Attachment a:entity.getAttachments())
                        a.setEntity(entity.getExpId());
                        this.attachmentMapper.addAttachments(entity.getAttachments());
                }
            }
        } catch (Exception e) {

            log.error("updateExpZhuan信息修改异常"+e.getMessage());
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
    public Integer deleteExpZhuan(String id){


        return ExpZhuanMapper.deleteExpZhuan(id);
    }

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    @Override
    public List<ExpTask> findExpZhuan(ExpTask entity){


        return ExpZhuanMapper.findExpZhuan(entity);
    }

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    @Override
    public List<ExpTask> findExpZhuanByPage(ExpTask entity){


        return ExpZhuanMapper.findExpZhuanByPage(entity);
    }

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    @Override
    public ExpTask findExpZhuanById(ExpTask entity){

        List<ExpTask> list=ExpZhuanMapper.findExpZhuan(entity);
        return list!=null&&!list.isEmpty()?list.get(0):null;
    }


    /**
     * 更新活动状态
     */
    @Override
    public Integer updateExpIsBegin(ExpTask entity)
    {
        // TODO Auto-generated method stub
        return ExpZhuanMapper.updateExpIsBegin(entity);
    }

    /**
     * 查询附件
     */
    @Override
    public List<Attachment> findExpAttachmentByEntityExpId(Attachment entity)
    {
        // TODO Auto-generated method stub
        return attachmentMapper.findAttachmentByExpId(entity);
    }

    /**
     * 更新全民赚app下载 图片示例及app描述信息
     * @param entity
     * @return 
     * @see
     */
    @Override
    public Integer updateExpAppImagesInfo(ExpTask entity)
    {
        // TODO Auto-generated method stub
 
        Integer result=0;
        try {
            if(entity==null){

                return result;
            }else{

                result = ExpZhuanMapper.updateExpAppImagesInfo(entity);
                if(entity.getAttachments().size()>0) {
                    for(Attachment a:entity.getAttachments())
                        a.setEntity(entity.getExpId());
                        this.attachmentMapper.addAttachments(entity.getAttachments());
                }
            }
        } catch (Exception e) {

            log.error("updateExpZhuan信息修改异常"+e.getMessage());
            new RuntimeException();
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 活动置顶
     */
    @Override
    public Integer updataSeqNum(Map<String, Object> map)
    {
        // TODO Auto-generated method stub
        return ExpZhuanMapper.updataSeqNum(map);
    }
}
