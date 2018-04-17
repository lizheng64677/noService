package com.suyin.experience.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.suyin.experience.mapper.ExperienceMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suyin.system.mapper.AttachmentMapper;
import com.suyin.system.model.Attachment;
import com.suyin.system.util.Md5Util;

import java.util.*;
import com.suyin.experience.model.*;
import com.suyin.experience.service.*;



@Transactional
@Service("ExperienceService")
public class ExperienceServiceImpl implements ExperienceService{

    private final static Logger log=Logger.getLogger(ExperienceServiceImpl.class);

    @Autowired
    private ExperienceMapper ExperienceMapper; 
    @Autowired
    private AttachmentMapper attachmentMapper;//附件表操作
    /**
     * 新增信息
     * @param entity
     * @return
     */
    @Override
    public Integer addExperience(Experience entity){
        Integer result=0;
        try {

            if(entity==null){
                return result;
            }else{
                result = ExperienceMapper.addExperience(entity);
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
    public Integer updateExperience(Experience entity){

        Integer result=0;
        try {
            if(entity==null){

                return result;
            }else{

                result = ExperienceMapper.updateExperience(entity);
                if(entity.getAttachments().size()>0) {
                    for(Attachment a:entity.getAttachments())
                        a.setEntity(entity.getExpId());
                        this.attachmentMapper.addAttachments(entity.getAttachments());
                }
            }
        } catch (Exception e) {

            log.error("Experience信息修改异常"+e.getMessage());
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
    public Integer deleteExperience(String id){


        return ExperienceMapper.deleteExperience(id);
    }

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    @Override
    public List<Experience> findExperience(Experience entity){


        return ExperienceMapper.findExperience(entity);
    }

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    @Override
    public List<Experience> findExperienceByPage(Experience entity){


        return ExperienceMapper.findExperienceByPage(entity);
    }

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    @Override
    public Experience findExperienceById(Experience entity){

        List<Experience> list=ExperienceMapper.findExperience(entity);
        return list!=null&&!list.isEmpty()?list.get(0):null;
    }


    /**
     * 修改活动状态
     */
    @Override
    public Integer updateExpIsBegin(Experience entity)
    {
        // TODO Auto-generated method stub
        return ExperienceMapper.updateExpIsBegin(entity);
    }

    /**
     * 增加时间段
     */
    @Override
    public Integer addExperienceDetailTime(Map<String, Object> map)
    {
        // TODO Auto-generated method stub
        return ExperienceMapper.addExperienceDetailTime(map);
    }

    /**
     * 置顶活动序号
     */
    @Override
    public Integer updataSeqNum(Map<String, Object> map)
    {
        // TODO Auto-generated method stub
        return ExperienceMapper.updataSeqNum(map);
    }
}
