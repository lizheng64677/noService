package com.suyin.decorateuser.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.suyin.decorateuser.mapper.ExpDecorateUserMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.suyin.system.util.Md5Util;

import java.util.*;
import com.suyin.decorateuser.model.*;
import com.suyin.decorateuser.service.*;



@Transactional
@Service("ExpDecorateUserService")
public class ExpDecorateUserServiceImpl implements ExpDecorateUserService{

    private final static Logger log=Logger.getLogger(ExpDecorateUserServiceImpl.class);
    
    @Autowired
    private ExpDecorateUserMapper ExpDecorateUserMapper; 

   

    /**
     * 修改信息
     * @param entity
     * @return
     */
    @Override
    public Integer updateExpDecorateUser(ExpDecorateUser entity){

        Integer result=0;
        try {
            if(entity==null){

                return result;
            }else{

                result = ExpDecorateUserMapper.updateExpDecorateUser(entity);
            }
        } catch (Exception e) {
            
            log.error("ExpDecorateUser信息修改异常"+e.getMessage());
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
    public List<ExpDecorateUser> findExpDecorateUser(ExpDecorateUser entity){
        
        
        return ExpDecorateUserMapper.findExpDecorateUser(entity);
    }

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    @Override
    public List<ExpDecorateUser> findExpDecorateUserByPage(ExpDecorateUser entity){
        
        
        return ExpDecorateUserMapper.findExpDecorateUserByPage(entity);
    }

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    @Override
    public ExpDecorateUser findExpDecorateUserById(ExpDecorateUser entity){
        
        List<ExpDecorateUser> list=ExpDecorateUserMapper.findExpDecorateUser(entity);
        return list!=null&&!list.isEmpty()?list.get(0):null;
    }
}
