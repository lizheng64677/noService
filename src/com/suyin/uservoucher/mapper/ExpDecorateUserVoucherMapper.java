package com.suyin.uservoucher.mapper;


import java.util.List;

import java.util.*;
import com.suyin.uservoucher.model.*;
import com.suyin.uservoucher.service.*;




public interface ExpDecorateUserVoucherMapper {

    /**
     * 新增信息
     */
    public Integer addExpDecorateUserVoucher(ExpDecorateUserVoucher entity);

    /**
     * 修改信息
     */
    public Integer updateExpDecorateUserVoucher(ExpDecorateUserVoucher entity);
    /**
     * 根据id删除单条信息
     * 
     */
    public Integer deleteExpDecorateUserVoucher(String id);
    /**
     * 批量删除
     */
    public Integer deleteExpDecorateUserVoucher(String[] id); 

    /**
     * 查询列表
     */
    public List<ExpDecorateUserVoucher> findExpDecorateUserVoucher(ExpDecorateUserVoucher entity);

    /**
     * 查询列表分页  
     */
    public List<ExpDecorateUserVoucher> findExpDecorateUserVoucherByPage(ExpDecorateUserVoucher entity);

}
