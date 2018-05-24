package com.suyin.decoratemessage.mapper;


import java.util.List;

import java.util.*;
import com.suyin.decoratemessage.model.*;
import com.suyin.decoratemessage.service.*;




public interface DecorateMessageMapper {

    /**
     * 新增信息
     */
    public Integer addDecorateMessage(DecorateMessage entity);

    /**
     * 修改信息
     */
    public Integer updateDecorateMessage(DecorateMessage entity);
    /**
     * 根据id删除单条信息
     * 
     */
    public Integer deleteDecorateMessage(String id);
    /**
     * 批量删除
     */
    public Integer deleteDecorateMessage(String[] id); 

    /**
     * 查询列表
     */
    public List<DecorateMessage> findDecorateMessage(DecorateMessage entity);

    /**
     * 查询列表分页  
     */
    public List<DecorateMessage> findDecorateMessageByPage(DecorateMessage entity);

}
