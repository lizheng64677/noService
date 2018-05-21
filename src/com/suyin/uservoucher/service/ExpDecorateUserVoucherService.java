package com.suyin.uservoucher.service;

import java.util.List;

import java.util.*;
import com.suyin.uservoucher.model.*;
import com.suyin.uservoucher.service.*;




public interface ExpDecorateUserVoucherService{

    /**
     * 新增信息
     * @param entity
     * @return
     */
    public Integer addExpDecorateUserVoucher(ExpDecorateUserVoucher entity);

    /**
     * 修改信息
     * @param entity
     * @return
     */
    public Integer updateExpDecorateUserVoucher(ExpDecorateUserVoucher entity);

    /**
     * 删除信息
     * @param id
     * @return
     */
    public Integer deleteExpDecorateUserVoucher(String id);

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    public List<ExpDecorateUserVoucher> findExpDecorateUserVoucher(ExpDecorateUserVoucher entity);

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    public List<ExpDecorateUserVoucher> findExpDecorateUserVoucherByPage(ExpDecorateUserVoucher entity);

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    public ExpDecorateUserVoucher findExpDecorateUserVoucherById(ExpDecorateUserVoucher entity);
}
