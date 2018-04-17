package com.suyin.advs.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.suyin.advs.mapper.AdvsMapper;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suyin.system.mapper.AttachmentMapper;
import com.suyin.system.model.Attachment;
import com.suyin.system.util.Md5Util;

import java.util.*;

import com.suyin.advs.model.*;
import com.suyin.advs.service.*;



@Transactional
@Service("AdvsService")
public class AdvsServiceImpl implements AdvsService{

    private final static Logger log=Logger.getLogger(AdvsServiceImpl.class);
    
    @Autowired
    private AdvsMapper AdvsMapper; 
    
    @Autowired
    private AttachmentMapper attachmentMapper;

    /**
     * 新增信息
     * @param entity
     * @return
     */
    @Override
    public Integer addAdvs(Advs entity){
        Integer result=0;
        try {
            if(entity==null){
                return result;
            }else{
                result = AdvsMapper.addAdvs(entity);
                if(entity.getAttachments().size()>0) {
                	for(Attachment a:entity.getAttachments())
                		a.setEntity(entity.getAdvId());
                	this.attachmentMapper.addAttachments(entity.getAttachments());
                }
            }
        } catch (Exception e) {
           log.error("Advs信息修改异常"+e.getMessage());
           e.printStackTrace();
           throw new RuntimeException(e);
        }
        return result;

    }

    /**
     * 修改信息
     * @param entity
     * @return
     */
    @Override
    public Integer updateAdvs(Advs entity){

        Integer result=0;
        try {
            if(entity==null){
                return result;
            }else{
                result = AdvsMapper.updateAdvs(entity);
                if(entity.getAttachments().size()>0) {
                	for(Attachment a:entity.getAttachments())
                		a.setEntity(entity.getAdvId());
                	this.attachmentMapper.addAttachments(entity.getAttachments());
                }
            }
        } catch (Exception e) {
            
            log.error("Advs信息修改异常"+e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
           
        }
        return result;

    }

    /**
     * 删除信息
     * @param id
     * @return
     */
    @Override
    public Integer deleteAdvs(String id){
        return AdvsMapper.deleteAdvs(id);
    }
    
    /**
     * 批量删除信息
     * @param id
     * @return
     */
    @Override
    public Integer deleteAdvsByBatch(String[] ids){
    	return AdvsMapper.deleteAdvsByBatch(ids);
    }

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    @Override
    public List<Advs> findAdvs(Advs entity){
        
        
        return AdvsMapper.findAdvsList(entity);
    }

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    @Override
    public List<Advs> findAdvsByPage(Advs entity){
        
        
        return AdvsMapper.findAdvsByPage(entity);
    }

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    @Override
    public Advs findAdvsById(Advs entity){
        return AdvsMapper.findAdvsById(entity);
    }
}
