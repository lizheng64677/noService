/*
 * 文件名：ExpCharsMapper.java
 * 版权：Copyright by www.isure.net
 * 描述：
 * 修改人：windows7
 * 修改时间：2015-12-17
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.suyin.experience.mapper;

import java.util.List;
import java.util.Map;

import com.suyin.experience.model.ExpChars;

/**
 * 免费活动的数据详情统计
 * sql 对应映射
 * @author lz
 * @version 2015-12-17
 * @see ExpCharsMapper
 * @since
 */

public interface ExpCharsMapper
{

    /**
     * 
     * 抽奖数据查询
     * @param expChars
     * @return 
     * @see
     */
    public List<ExpChars> findPrizeCharsByPage(ExpChars expChars);
    /**
     * 
     * 抽奖数据查询
     * @param expChars
     * @return 
     * @see
     */
    public List<Map<String,Object>> findPrizeCharsMapInfo(ExpChars expChars);
    /**
     * 
     * 人气数据查询
     * @param expChars
     * @return 
     * @see
     */
    public List<ExpChars>findPopCharsListByPage(ExpChars expChars);
    /**
     * 
     * 人气数据查询
     * @param expChars
     * @return 
     * @see
     */
    public List<Map<String,Object>>findPopCharsMapList(ExpChars expChars);
    /**
     * 
     * 赚金币数据查询
     * @param expChars
     * @return 
     * @see
     */
    public List<ExpChars> findZhuanCharsByPage(ExpChars expChars);
    /**
     * 
     * 赚金币数据查询
     * @param expChars
     * @return 
     * @see
     */
    public List<Map<String,Object>> findZhuanCharsMapInfo(ExpChars expChars);
    /**
     * 
     * 赚金币数据查询
     * @param expChars
     * @return 
     * @see
     */
    public List<Map<String,Object>>findQzhuanCharsMapInfo(ExpChars expChars);
    /**
     * 
     * 赚金币数据查询
     * @param expChars
     * @return 
     * @see
     */
    public List<ExpChars> findQzhuanCharsByPage(ExpChars expChars);
}
