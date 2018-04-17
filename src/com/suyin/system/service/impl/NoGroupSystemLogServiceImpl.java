/*
 * 文件名：NoGroupSystemLogServiceImpl.java
 * 版权：Copyright by www.isure.net
 * 描述：
 * 修改人：windows7
 * 修改时间：2015-12-9
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.suyin.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suyin.system.mapper.NoGroupSystemLogMapper;
import com.suyin.system.model.BaseExpLogModel;
import com.suyin.system.model.BaseLogModel;
import com.suyin.system.model.CapitalCount;
import com.suyin.system.service.NoGroupSystemLogService;
import com.suyin.system.util.Tools;

/**
 * 
 * NO团日志记录控制
 * @author lz
 * @version 2015-12-9
 * @see NoGroupSystemLogServiceImpl
 * @since
 */
@Transactional
@Service("noGroupSystemLogService")
public class NoGroupSystemLogServiceImpl implements NoGroupSystemLogService
{

    @Autowired
    private NoGroupSystemLogMapper groupSystemLogMapper;
    /* (non-Javadoc)
     * @see com.suyin.system.service.NoGroupSystemLogService#inSertDbLogs(java.util.List)
     */
    @Override
    public Integer inSertDbLogs(List<BaseLogModel> model)
    {
        // TODO Auto-generated method stub
        return groupSystemLogMapper.inSertDbLogs(model);
    }
    /* (non-Javadoc)
     * @see com.suyin.system.service.NoGroupSystemLogService#findNoGroupInfo()
     */
    @Override
    public Integer findNoGroupInfo()
    {
        // TODO Auto-generated method stub
        return groupSystemLogMapper.findNoGroupInfo();
    }
    /* (non-Javadoc)
     * @see com.suyin.system.service.NoGroupSystemLogService#initData(com.suyin.system.model.BaseLogModel)
     */
    @Override
    public List<Map<String,Object>> initData(BaseLogModel model)
    {
        // TODO Auto-generated method stub
        return groupSystemLogMapper.initData(model);
    }
    /* (non-Javadoc)
     * @see com.suyin.system.service.NoGroupSystemLogService#initAllData(com.suyin.system.model.BaseLogModel)
     */
    @Override
    public List<Map<String,Object>> initAllData(BaseLogModel model)
    {
        // TODO Auto-generated method stub
        return groupSystemLogMapper.initAllData(model);
    }
    /* (non-Javadoc)
     * @see com.suyin.system.service.NoGroupSystemLogService#initAllDataUv(com.suyin.system.model.BaseLogModel)
     */
    @Override
    public List<Map<String, Object>> initAllDataUv(BaseLogModel model)
    {
        // TODO Auto-generated method stub
        return groupSystemLogMapper.initAllDataUv(model);
    }
    /* (non-Javadoc)
     * @see com.suyin.system.service.NoGroupSystemLogService#findExpSystemLogByPage(com.suyin.system.model.BaseLogModel)
     */
    @Override
    public List<BaseLogModel> findExpSystemLogByPage(BaseLogModel entity)
    {
        // TODO Auto-generated method stub
        return groupSystemLogMapper.findExpSystemLogByPage(entity);
    }
    /* (non-Javadoc)
     * @see com.suyin.system.service.NoGroupSystemLogService#inSertExpDbLogs(java.util.List)
     */
    @Override
    public Integer inSertExpDbLogs(List<BaseExpLogModel> model)
    {
        // TODO Auto-generated method stub
        return groupSystemLogMapper.inSertExpDbLogs(model);
    }
    /* (non-Javadoc)
     * @see com.suyin.system.service.NoGroupSystemLogService#findNoGroupExpInfo()
     */
    @Override
    public Integer findNoGroupExpInfo()
    {
        // TODO Auto-generated method stub
        return groupSystemLogMapper.findNoGroupExpInfo();
    }
    /* (non-Javadoc)
     * @see com.suyin.system.service.NoGroupSystemLogService#findExpCharsInfo(java.util.Map)
     */
    @Override
    public List<Map<String, Object>> findExpCharsInfo(BaseExpLogModel model)
    {
        // TODO Auto-generated method stub
        return groupSystemLogMapper.findExpCharsInfo(model);
    }
    /* (non-Javadoc)
     * @see com.suyin.system.service.NoGroupSystemLogService#initExpAllData(com.suyin.system.model.BaseExpLogModel)
     */
    @Override
    public List<Map<String, Object>> initExpAllData(BaseExpLogModel model)
    {
        // TODO Auto-generated method stub
        return groupSystemLogMapper.initExpAllData(model);
    }
    /* (non-Javadoc)
     * @see com.suyin.system.service.NoGroupSystemLogService#initExpAllDataUv(com.suyin.system.model.BaseExpLogModel)
     */
    @Override
    public List<Map<String, Object>> initExpAllDataUv(BaseExpLogModel model)
    {
        // TODO Auto-generated method stub
        return groupSystemLogMapper.initExpAllDataUv(model);
    }
	@Override
	public CapitalCount findCapitalCountByPage(Map<String, Object> map1) {
		String conditions=" 1=1";
		String conditions1=" 1=1";
		if(Tools.notEmpty(String.valueOf(map1.get("startTime")))){
			conditions+=" and create_time>='"+map1.get("startTime")+"'";
		}
		if(Tools.notEmpty(String.valueOf(map1.get("endTime")))){
			conditions+=" and create_time<='"+map1.get("endTime")+"'";
			conditions1+=" and create_time<='"+map1.get("endTime")+"'";
		}
		map1.put("conditons", conditions);
		map1.put("conditons1", conditions1);
		return groupSystemLogMapper.findCapitalCountByPage(map1);
	}
    /* (non-Javadoc)
     * @see com.suyin.system.service.NoGroupSystemLogService#findExpZhuanCharsInfo(com.suyin.system.model.BaseExpLogModel)
     */
    @Override
    public List<Map<String, Object>> findExpZhuanCharsInfo(BaseExpLogModel model)
    {
        // TODO Auto-generated method stub
        return groupSystemLogMapper.findExpZhuanCharsInfo(model);
    }
    /* (non-Javadoc)
     * @see com.suyin.system.service.NoGroupSystemLogService#findExpSystemLogMapInfo(com.suyin.system.model.BaseLogModel)
     */
    @Override
    public List<Map<String, Object>> findExpSystemLogMapInfo(BaseLogModel entity)
    {
        // TODO Auto-generated method stub
        return groupSystemLogMapper.findExpSystemLogMapInfo(entity);
    }

}
