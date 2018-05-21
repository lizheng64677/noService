package com.suyin.uservoucher.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.suyin.uservoucher.mapper.ExpDecorateUserVoucherMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.suyin.system.util.Md5Util;

import java.util.*;
import com.suyin.uservoucher.model.*;
import com.suyin.uservoucher.service.*;



@Transactional
@Service("ExpDecorateUserVoucherService")
public class ExpDecorateUserVoucherServiceImpl implements ExpDecorateUserVoucherService{

    private final static Logger log=Logger.getLogger(ExpDecorateUserVoucherServiceImpl.class);
    
    @Autowired
    private ExpDecorateUserVoucherMapper ExpDecorateUserVoucherMapper; 

    /**
     * 新增信息
     * @param entity
     * @return
     */
    @Override
    public Integer addExpDecorateUserVoucher(ExpDecorateUserVoucher entity){
        Integer result=0;
        try {

            if(entity==null){
                return result;
            }else{
                result = ExpDecorateUserVoucherMapper.addExpDecorateUserVoucher(entity);
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
    public Integer updateExpDecorateUserVoucher(ExpDecorateUserVoucher entity){

        Integer result=0;
        try {
            if(entity==null){

                return result;
            }else{

                result = ExpDecorateUserVoucherMapper.updateExpDecorateUserVoucher(entity);
            }
        } catch (Exception e) {
            
            log.error("ExpDecorateUserVoucher信息修改异常"+e.getMessage());
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
    public Integer deleteExpDecorateUserVoucher(String id){
        
        
        return ExpDecorateUserVoucherMapper.deleteExpDecorateUserVoucher(id);
    }

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    @Override
    public List<ExpDecorateUserVoucher> findExpDecorateUserVoucher(ExpDecorateUserVoucher entity){
        
        
        return ExpDecorateUserVoucherMapper.findExpDecorateUserVoucher(entity);
    }

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    @Override
    public List<ExpDecorateUserVoucher> findExpDecorateUserVoucherByPage(ExpDecorateUserVoucher entity){
        
        
        return ExpDecorateUserVoucherMapper.findExpDecorateUserVoucherByPage(entity);
    }

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    @Override
    public ExpDecorateUserVoucher findExpDecorateUserVoucherById(ExpDecorateUserVoucher entity){
        
        List<ExpDecorateUserVoucher> list=ExpDecorateUserVoucherMapper.findExpDecorateUserVoucher(entity);
        return list!=null&&!list.isEmpty()?list.get(0):null;
    }
}
