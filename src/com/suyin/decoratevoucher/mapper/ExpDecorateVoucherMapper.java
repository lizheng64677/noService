package com.suyin.decoratevoucher.mapper;


import java.util.List;

import java.util.*;
import com.suyin.decoratevoucher.model.*;
import com.suyin.decoratevoucher.service.*;




public interface ExpDecorateVoucherMapper {

    /**
     * 新增信息
     */
    public Integer addExpDecorateVoucher(ExpDecorateVoucher entity);

    /**
     * 修改信息
     */
    public Integer updateExpDecorateVoucher(ExpDecorateVoucher entity);
    /**
     * 根据id删除单条信息
     * 
     */
    public Integer deleteExpDecorateVoucher(String id);
    /**
     * 批量删除
     */
    public Integer deleteExpDecorateVoucher(String[] id); 

    /**
     * 查询列表
     */
    public List<ExpDecorateVoucher> findExpDecorateVoucher(ExpDecorateVoucher entity);

    /**
     * 查询列表分页  
     */
    public List<ExpDecorateVoucher> findExpDecorateVoucherByPage(ExpDecorateVoucher entity);

}
