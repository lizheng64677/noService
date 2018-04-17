/*
 * 文件名：NoGroupSystemLog.java
 * 版权：Copyright by www.isure.net
 * 描述：
 * 修改人：windows7
 * 修改时间：2015-12-9
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.suyin.system.mapper;

import java.util.List;
import java.util.Map;

import com.suyin.system.model.BaseExpLogModel;
import com.suyin.system.model.BaseLogModel;
import com.suyin.system.model.CapitalCount;

/**
 * NO团日志记录
 * @author lz
 * @version 2015-12-9
 * @see NoGroupSystemLogMapper
 * @since
 */

public interface NoGroupSystemLogMapper
{

    /**
     * 
     * NO团操作日志记录
     * @param model
     * @return 
     * @see
     */
    public Integer inSertDbLogs(List<BaseLogModel> model);

    /**
     * 查询NO团信息
     * @return 
     * @see
     */
    public Integer findNoGroupInfo();

    /**
     * 查询各终端数据
     * @param request
     * @return 
     * @see
     */
    public List<Map<String,Object>> initData(BaseLogModel model);
    /**
     * 查询全端数据
     * 
     * @return 
     * @see
     */
    public List<Map<String,Object>>  initAllData(BaseLogModel model);
    /**初始化模块所有数据 统计
     * @param model
     * @return 
     * @see
     */
    public List<Map<String,Object>>initAllDataUv(BaseLogModel model);
    /**
     * 查找信息列表(分页)
     * @param entity
     * @return
     */
    public List<BaseLogModel> findExpSystemLogByPage(BaseLogModel entity);

    /**
     * 查找信息列表
     * @param entity
     * @return
     */
    public List<Map<String,Object>> findExpSystemLogMapInfo(BaseLogModel entity);
    /**********活动日志统计**********/
    /**
     * 
     * 记录活动日志记录入库
     * @param model
     * @return 
     * @see
     */
    public Integer inSertExpDbLogs(List<BaseExpLogModel> model);
    /**
     * 查询NO团活动信息
     * @return 
     * @see
     */
    public Integer findNoGroupExpInfo();
    /**
     * 初始化各终端图表数据
     * @param model
     * @return 
     * @see
     */
    public List<Map<String,Object>>findExpCharsInfo(BaseExpLogModel model);
    /**
     * 初始化各终端图表数据
     * 赚金币
     * @param model
     * @return 
     * @see
     */
    public List<Map<String,Object>>findExpZhuanCharsInfo(BaseExpLogModel model);

    /**
     * 查询活动全端数据
     * 
     * @return 
     * @see
     */
    public List<Map<String,Object>> initExpAllData(BaseExpLogModel model);
    /**
     * 
     * 查询UV
     * @param model
     * @return 
     * @see
     */
    public List<Map<String,Object>>initExpAllDataUv(BaseExpLogModel model);

    
    /**
     * 资金统计
     */
	public CapitalCount findCapitalCountByPage(Map<String, Object> map1);
}
