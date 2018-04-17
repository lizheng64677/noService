/*
 * 文件名：ExpCharsServiceImpl.java
 * 版权：Copyright by www.isure.net
 * 描述：
 * 修改人：windows7
 * 修改时间：2015-12-17
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.suyin.experience.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suyin.experience.mapper.ExpCharsMapper;
import com.suyin.experience.model.ExpChars;
import com.suyin.experience.service.ExpCharsService;

/**
 * 
 * 免费活动的数据详情统计
 * @author lz
 * @version 2015-12-17
 * @see ExpCharsServiceImpl
 * @since
 */
@Transactional
@Service("expCharsService")
public class ExpCharsServiceImpl implements ExpCharsService
{

    @Autowired
    private ExpCharsMapper expCharsMapper;
    /* (non-Javadoc)
     * @see com.suyin.experience.service.ExpCharsService#findExpCharsByPage(com.suyin.experience.model.ExpChars)
     */
    @Override
    public List<ExpChars> findPrizeCharsByPage(ExpChars expChars)
    {
        // TODO Auto-generated method stub
        return expCharsMapper.findPrizeCharsByPage(expChars);
    }
    /* (non-Javadoc)
     * @see com.suyin.experience.service.ExpCharsService#findPopCharsList(com.suyin.experience.model.ExpChars)
     */
    @Override
    public List<ExpChars> findPopCharsListByPage(ExpChars expChars)
    {
        // TODO Auto-generated method stub
        return expCharsMapper.findPopCharsListByPage(expChars);
    }
    /* (non-Javadoc)
     * @see com.suyin.experience.service.ExpCharsService#findZhuanCharsByPage(com.suyin.experience.model.ExpChars)
     */
    @Override
    public List<ExpChars> findZhuanCharsByPage(ExpChars expChars)
    {
        // TODO Auto-generated method stub
        return expCharsMapper.findZhuanCharsByPage(expChars);
    }
    /* (non-Javadoc)
     * @see com.suyin.experience.service.ExpCharsService#findQzhuanCharsByPage(com.suyin.experience.model.ExpChars)
     */
    @Override
    public List<ExpChars> findQzhuanCharsByPage(ExpChars expChars)
    {
        // TODO Auto-generated method stub
        return expCharsMapper.findQzhuanCharsByPage(expChars);
    }
    /* (non-Javadoc)
     * @see com.suyin.experience.service.ExpCharsService#findPrizeCharsMapInfo(com.suyin.experience.model.ExpChars)
     */
    @Override
    public List<Map<String, Object>> findPrizeCharsMapInfo(ExpChars expChars)
    {
        // TODO Auto-generated method stub
        return expCharsMapper.findPrizeCharsMapInfo(expChars);
    }
    /* (non-Javadoc)
     * @see com.suyin.experience.service.ExpCharsService#findPopCharsMapList(com.suyin.experience.model.ExpChars)
     */
    @Override
    public List<Map<String, Object>> findPopCharsMapList(ExpChars expChars)
    {
        // TODO Auto-generated method stub
        return expCharsMapper.findPopCharsMapList(expChars);
    }
    /* (non-Javadoc)
     * @see com.suyin.experience.service.ExpCharsService#findQzhuanCharsMapInfo(com.suyin.experience.model.ExpChars)
     */
    @Override
    public List<Map<String, Object>> findQzhuanCharsMapInfo(ExpChars expChars)
    {
        // TODO Auto-generated method stub
        return expCharsMapper.findQzhuanCharsMapInfo(expChars);
    }
    /* (non-Javadoc)
     * @see com.suyin.experience.service.ExpCharsService#findZhuanCharsMapInfo(com.suyin.experience.model.ExpChars)
     */
    @Override
    public List<Map<String,Object>> findZhuanCharsMapInfo(ExpChars expChars)
    {
        // TODO Auto-generated method stub
        return expCharsMapper.findZhuanCharsMapInfo(expChars);
    }

}
