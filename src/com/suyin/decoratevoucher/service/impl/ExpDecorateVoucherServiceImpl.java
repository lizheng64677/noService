package com.suyin.decoratevoucher.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.suyin.decoratevoucher.mapper.ExpDecorateVoucherMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.suyin.system.util.Md5Util;

import java.util.*;
import com.suyin.decoratevoucher.model.*;
import com.suyin.decoratevoucher.service.*;



@Transactional
@Service("ExpDecorateVoucherService")
public class ExpDecorateVoucherServiceImpl implements ExpDecorateVoucherService{

    private final static Logger log=Logger.getLogger(ExpDecorateVoucherServiceImpl.class);
    
    @Autowired
    private ExpDecorateVoucherMapper ExpDecorateVoucherMapper; 

    /**
     * 新增信息
     * @param entity
     * @return
     */
    @Override
    public Integer addExpDecorateVoucher(ExpDecorateVoucher entity){
        Integer result=0;
        try {

            if(entity==null){
                return result;
            }else{
                result = ExpDecorateVoucherMapper.addExpDecorateVoucher(entity);
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
    public Integer updateExpDecorateVoucher(ExpDecorateVoucher entity){

        Integer result=0;
        try {
            if(entity==null){

                return result;
            }else{

                result = ExpDecorateVoucherMapper.updateExpDecorateVoucher(entity);
            }
        } catch (Exception e) {
            
            log.error("ExpDecorateVoucher信息修改异常"+e.getMessage());
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
    public Integer deleteExpDecorateVoucher(String id){
        
        
        return ExpDecorateVoucherMapper.deleteExpDecorateVoucher(id);
    }

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    @Override
    public List<ExpDecorateVoucher> findExpDecorateVoucher(ExpDecorateVoucher entity){
        
        
        return ExpDecorateVoucherMapper.findExpDecorateVoucher(entity);
    }

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    @Override
    public List<ExpDecorateVoucher> findExpDecorateVoucherByPage(ExpDecorateVoucher entity){
        
        
        return ExpDecorateVoucherMapper.findExpDecorateVoucherByPage(entity);
    }

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    @Override
    public ExpDecorateVoucher findExpDecorateVoucherById(ExpDecorateVoucher entity){
        
        List<ExpDecorateVoucher> list=ExpDecorateVoucherMapper.findExpDecorateVoucher(entity);
        return list!=null&&!list.isEmpty()?list.get(0):null;
    }
}
