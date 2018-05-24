package com.suyin.decoraterecord.service;

import java.util.List;

import java.util.*;
import com.suyin.decoraterecord.model.*;
import com.suyin.decoraterecord.service.*;




public interface DecorateRecordService{

    /**
     * 新增信息
     * @param entity
     * @return
     */
    public Integer addDecorateRecord(DecorateRecord entity);

    /**
     * 修改信息
     * @param entity
     * @return
     */
    public Integer updateDecorateRecord(DecorateRecord entity);

 

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    public List<DecorateRecord> findDecorateRecord(DecorateRecord entity);

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    public List<DecorateRecord> findDecorateRecordByPage(DecorateRecord entity);

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    public DecorateRecord findDecorateRecordById(DecorateRecord entity);
}
