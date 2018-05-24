package com.suyin.decoraterecord.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.suyin.decoraterecord.mapper.DecorateRecordMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.suyin.system.util.Md5Util;

import java.util.*;
import com.suyin.decoraterecord.model.*;
import com.suyin.decoraterecord.service.*;



@Transactional
@Service("DecorateRecordService")
public class DecorateRecordServiceImpl implements DecorateRecordService{

    private final static Logger log=Logger.getLogger(DecorateRecordServiceImpl.class);
    
    @Autowired
    private DecorateRecordMapper DecorateRecordMapper; 

    /**
     * 新增信息
     * @param entity
     * @return
     */
    @Override
    public Integer addDecorateRecord(DecorateRecord entity){
        Integer result=0;
        try {

            if(entity==null){
                return result;
            }else{
                result = DecorateRecordMapper.addDecorateRecord(entity);
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
    public Integer updateDecorateRecord(DecorateRecord entity){

        Integer result=0;
        try {
            if(entity==null){

                return result;
            }else{

                result = DecorateRecordMapper.updateDecorateRecord(entity);
            }
        } catch (Exception e) {
            
            log.error("DecorateRecord信息修改异常"+e.getMessage());
            new RuntimeException();
            e.printStackTrace();
        }
        return result;

    }

  

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    @Override
    public List<DecorateRecord> findDecorateRecord(DecorateRecord entity){
        
        
        return DecorateRecordMapper.findDecorateRecord(entity);
    }

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    @Override
    public List<DecorateRecord> findDecorateRecordByPage(DecorateRecord entity){
        
        
        return DecorateRecordMapper.findDecorateRecordByPage(entity);
    }

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    @Override
    public DecorateRecord findDecorateRecordById(DecorateRecord entity){
        
        List<DecorateRecord> list=DecorateRecordMapper.findDecorateRecord(entity);
        return list!=null&&!list.isEmpty()?list.get(0):null;
    }
}
