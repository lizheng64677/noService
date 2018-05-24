package com.suyin.decoraterecord.mapper;


import java.util.List;

import java.util.*;
import com.suyin.decoraterecord.model.*;
import com.suyin.decoraterecord.service.*;




public interface DecorateRecordMapper {

    /**
     * 新增信息
     */
    public Integer addDecorateRecord(DecorateRecord entity);

    /**
     * 修改信息
     */
    public Integer updateDecorateRecord(DecorateRecord entity);

    /**
     * 查询列表
     */
    public List<DecorateRecord> findDecorateRecord(DecorateRecord entity);

    /**
     * 查询列表分页  
     */
    public List<DecorateRecord> findDecorateRecordByPage(DecorateRecord entity);

}
