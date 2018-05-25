package com.suyin.decoratebuyorder.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.suyin.decoratebuyorder.mapper.DecorateBuyOrderMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.suyin.system.util.Md5Util;

import java.util.*;
import com.suyin.decoratebuyorder.model.*;
import com.suyin.decoratebuyorder.service.*;



@Transactional
@Service("DecorateBuyOrderService")
public class DecorateBuyOrderServiceImpl implements DecorateBuyOrderService{

    private final static Logger log=Logger.getLogger(DecorateBuyOrderServiceImpl.class);
    
    @Autowired
    private DecorateBuyOrderMapper DecorateBuyOrderMapper; 

    /**
     * 新增信息
     * @param entity
     * @return
     */
    @Override
    public Integer addDecorateBuyOrder(DecorateBuyOrder entity){
        Integer result=0;
        try {

            if(entity==null){
                return result;
            }else{
                result = DecorateBuyOrderMapper.addDecorateBuyOrder(entity);
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
    public Integer updateDecorateBuyOrder(DecorateBuyOrder entity){

        Integer result=0;
        try {
            if(entity==null){

                return result;
            }else{

                result = DecorateBuyOrderMapper.updateDecorateBuyOrder(entity);
            }
        } catch (Exception e) {
            
            log.error("DecorateBuyOrder信息修改异常"+e.getMessage());
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
    public Integer deleteDecorateBuyOrder(String id){
        
        
        return DecorateBuyOrderMapper.deleteDecorateBuyOrder(id);
    }

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    @Override
    public List<DecorateBuyOrder> findDecorateBuyOrder(DecorateBuyOrder entity){
        
        
        return DecorateBuyOrderMapper.findDecorateBuyOrder(entity);
    }

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    @Override
    public List<DecorateBuyOrder> findDecorateBuyOrderByPage(DecorateBuyOrder entity){
        
        
        return DecorateBuyOrderMapper.findDecorateBuyOrderByPage(entity);
    }

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    @Override
    public DecorateBuyOrder findDecorateBuyOrderById(DecorateBuyOrder entity){
        
        List<DecorateBuyOrder> list=DecorateBuyOrderMapper.findDecorateBuyOrder(entity);
        return list!=null&&!list.isEmpty()?list.get(0):null;
    }
}
