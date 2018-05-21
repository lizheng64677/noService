package com.suyin.decoratevoucher.service;

import java.util.List;

import java.util.*;
import com.suyin.decoratevoucher.model.*;
import com.suyin.decoratevoucher.service.*;




public interface ExpDecorateVoucherService{

    /**
     * 新增信息
     * @param entity
     * @return
     */
    public Integer addExpDecorateVoucher(ExpDecorateVoucher entity);

    /**
     * 修改信息
     * @param entity
     * @return
     */
    public Integer updateExpDecorateVoucher(ExpDecorateVoucher entity);

    /**
     * 删除信息
     * @param id
     * @return
     */
    public Integer deleteExpDecorateVoucher(String id);

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    public List<ExpDecorateVoucher> findExpDecorateVoucher(ExpDecorateVoucher entity);

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    public List<ExpDecorateVoucher> findExpDecorateVoucherByPage(ExpDecorateVoucher entity);

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    public ExpDecorateVoucher findExpDecorateVoucherById(ExpDecorateVoucher entity);
}
