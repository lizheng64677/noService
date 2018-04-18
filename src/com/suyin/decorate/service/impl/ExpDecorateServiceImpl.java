package com.suyin.decorate.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.suyin.decorate.mapper.ExpDecorateMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.suyin.system.util.Md5Util;

import java.util.*;
import com.suyin.decorate.model.*;
import com.suyin.decorate.service.*;



@Transactional
@Service("ExpDecorateService")
public class ExpDecorateServiceImpl implements ExpDecorateService{

    private final static Logger log=Logger.getLogger(ExpDecorateServiceImpl.class);
    
    @Autowired
    private ExpDecorateMapper ExpDecorateMapper; 

    /**
     * 新增信息
     * @param entity
     * @return
     */
    @Override
    public Integer addExpDecorate(ExpDecorate entity){
        Integer result=0;
        try {

            if(entity==null){
                return result;
            }else{
                result = ExpDecorateMapper.addExpDecorate(entity);
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
    public Integer updateExpDecorate(ExpDecorate entity){

        Integer result=0;
        try {
            if(entity==null){

                return result;
            }else{

                result = ExpDecorateMapper.updateExpDecorate(entity);
            }
        } catch (Exception e) {
            
            log.error("ExpDecorate信息修改异常"+e.getMessage());
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
    public Integer deleteExpDecorate(String id){
        
        
        return ExpDecorateMapper.deleteExpDecorate(id);
    }

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    @Override
    public List<ExpDecorate> findExpDecorate(ExpDecorate entity){
        
        
        return ExpDecorateMapper.findExpDecorate(entity);
    }

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    @Override
    public List<ExpDecorate> findExpDecorateByPage(ExpDecorate entity){
        
        
        return ExpDecorateMapper.findExpDecorateByPage(entity);
    }

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    @Override
    public ExpDecorate findExpDecorateById(ExpDecorate entity){
        
        List<ExpDecorate> list=ExpDecorateMapper.findExpDecorate(entity);
        return list!=null&&!list.isEmpty()?list.get(0):null;
    }
}
