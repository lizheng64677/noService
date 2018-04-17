package com.suyin.thememonth.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.suyin.thememonth.mapper.ThemeMonthMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suyin.system.mapper.AttachmentMapper;
import com.suyin.system.model.Attachment;
import com.suyin.system.util.Md5Util;

import java.util.*;
import com.suyin.thememonth.model.*;
import com.suyin.thememonth.service.*;



@Transactional
@Service("ThemeMonthService")
public class ThemeMonthServiceImpl implements ThemeMonthService{

    private final static Logger log=Logger.getLogger(ThemeMonthServiceImpl.class);

    @Autowired
    private ThemeMonthMapper ThemeMonthMapper; 
    @Autowired
    private AttachmentMapper attachmentMapper;//附件表操作

    /**
     * 新增信息
     * @param entity
     * @return
     */
    @Override
    public Integer addThemeMonth(ThemeMonth entity){
        Integer result=0;
        try {

            if(entity==null){
                return result;
            }else{
                result = ThemeMonthMapper.addThemeMonth(entity);
                if(entity.getAttachments().size()>0) {
                    for(Attachment a:entity.getAttachments())
                        a.setEntity(entity.getThemeId());
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
    public Integer updateThemeMonth(ThemeMonth entity){

        Integer result=0;
        try {
            if(entity==null){

                return result;
            }else{

                result = ThemeMonthMapper.updateThemeMonth(entity);
                if(entity.getAttachments().size()>0) {
                    for(Attachment a:entity.getAttachments())
                        a.setEntity(entity.getThemeId());
                        this.attachmentMapper.addAttachments(entity.getAttachments());
                }
            }
        } catch (Exception e) {

            log.error("ThemeMonth信息修改异常"+e.getMessage());
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
    public Integer deleteThemeMonth(String id){


        return ThemeMonthMapper.deleteThemeMonth(id);
    }

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    @Override
    public List<ThemeMonth> findThemeMonth(ThemeMonth entity){


        return ThemeMonthMapper.findThemeMonth(entity);
    }

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    @Override
    public List<ThemeMonth> findThemeMonthByPage(ThemeMonth entity){


        return ThemeMonthMapper.findThemeMonthByPage(entity);
    }

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    @Override
    public ThemeMonth findThemeMonthById(ThemeMonth entity){

        List<ThemeMonth> list=ThemeMonthMapper.findThemeMonth(entity);
        return list!=null&&!list.isEmpty()?list.get(0):null;
    }
}
