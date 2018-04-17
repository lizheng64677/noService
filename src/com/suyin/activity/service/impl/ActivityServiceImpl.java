package com.suyin.activity.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suyin.activity.mapper.ActivityMapper;
import com.suyin.activity.model.Activity;
import com.suyin.activity.service.ActivityService;



@Transactional
@Service("ActivityService")
public class ActivityServiceImpl implements ActivityService{

    private final static Logger log=Logger.getLogger(ActivityServiceImpl.class);
    
    @Autowired
    private ActivityMapper ActivityMapper; 

    /**
     * 新增信息
     * @param entity
     * @return
     */
    @Override
    public Integer addActivity(Activity entity){
        Integer result=0;
        try {

            if(entity==null){
                return result;
            }else{       
                result = ActivityMapper.addActivity(entity);
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
    public Integer updateActivity(Activity entity){

        Integer result=0;
        try {
            if(entity==null){

                return result;
            }else{
            	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            	entity.setCreateTime(sdf.format(new Date()));
            	entity.setUpdateTime(sdf.format(new Date()));
                result = ActivityMapper.updateActivity(entity);
            }
        } catch (Exception e) {
            
            log.error("Activity信息修改异常"+e.getMessage());
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
    public Integer deleteActivity(String id){
        
        
        return ActivityMapper.deleteActivity(id);
    }

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    @Override
    public List<Activity> findActivity(Activity entity){
        
        
        return ActivityMapper.findActivity(entity);
    }

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    @Override
    public List<Activity> findActivityByPage(Activity entity){
        
        
        return ActivityMapper.findActivityByPage(entity);
    }

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    @Override
    public Activity findActivityById(Activity entity){
        
        Activity activity=ActivityMapper.findActivityById(entity);
        return activity;
    }
}
