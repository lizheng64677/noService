package com.suyin.decoratemessage.service;

import java.util.List;

import java.util.*;
import com.suyin.decoratemessage.model.*;
import com.suyin.decoratemessage.service.*;




public interface DecorateMessageService{

    /**
     * 新增信息
     * @param entity
     * @return
     */
    public Integer addDecorateMessage(DecorateMessage entity);

    /**
     * 修改信息
     * @param entity
     * @return
     */
    public Integer updateDecorateMessage(DecorateMessage entity);

    /**
     * 删除信息
     * @param id
     * @return
     */
    public Integer deleteDecorateMessage(String id);

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    public List<DecorateMessage> findDecorateMessage(DecorateMessage entity);

    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    public List<DecorateMessage> findDecorateMessageByPage(DecorateMessage entity);

    /**
     * 根据id查询对应的信息
     * @param entity
     * @return
     */
    public DecorateMessage findDecorateMessageById(DecorateMessage entity);
}
