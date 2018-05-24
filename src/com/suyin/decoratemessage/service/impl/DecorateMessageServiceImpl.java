package com.suyin.decoratemessage.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.suyin.decoratemessage.mapper.DecorateMessageMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.suyin.system.util.Md5Util;

import java.util.*;
import com.suyin.decoratemessage.model.*;
import com.suyin.decoratemessage.service.*;



@Transactional
@Service("DecorateMessageService")
public class DecorateMessageServiceImpl implements DecorateMessageService{

    private final static Logger log=Logger.getLogger(DecorateMessageServiceImpl.class);
    
    @Autowired
    private DecorateMessageMapper DecorateMessageMapper; 

    /**
     * 新增信息
     * @param entity
     * @return
     */
    @Override
    public Integer addDecorateMessage(DecorateMessage entity){
        Integer result=0;
        try {

            if(entity==null){
                return result;
            }else{
                result = DecorateMessageMapper.addDecorateMessage(entity);
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
    public Integer updateDecorateMessage(DecorateMessage entity){

        Integer result=0;
        try {
            if(entity==null){

                return result;
            }else{

                result = DecorateMessageMapper.updateDecorateMessage(entity);
            }
        } catch (Exception e) {
            
            log.error("DecorateMessage信息修改异常"+e.getMessage());
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
    public Integer deleteDecorateMessage(String id){
        
        
        return DecorateMessageMapper.deleteDecorateMessage(id);
    }

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    @Override
    public List<DecorateMessage> findDecorateMessage(DecorateMessage entity){
        
        
        return DecorateMessageMapper.findDecorateMessage(entity);
    }

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    @Override
    public List<DecorateMessage> findDecorateMessageByPage(DecorateMessage entity){
        
        
        return DecorateMessageMapper.findDecorateMessageByPage(entity);
    }

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    @Override
    public DecorateMessage findDecorateMessageById(DecorateMessage entity){
        
        List<DecorateMessage> list=DecorateMessageMapper.findDecorateMessage(entity);
        return list!=null&&!list.isEmpty()?list.get(0):null;
    }
}
