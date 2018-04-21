package com.suyin.decorate.mapper;


import java.util.List;

import com.suyin.decorate.model.ExpDecorate;




public interface ExpDecorateMapper {
	/**
	 * 修改活动状态
	 * @param entity
	 * @return
	 */
	public Integer updateExpStatus(ExpDecorate entity);
    /**
     * 新增信息
     */
    public Integer addExpDecorate(ExpDecorate entity);

    /**
     * 修改信息
     */
    public Integer updateExpDecorate(ExpDecorate entity);
    /**
     * 根据id删除单条信息
     * 
     */
    public Integer deleteExpDecorate(String id);
    /**
     * 批量删除
     */
    public Integer deleteExpDecorate(String[] id); 

    /**
     * 查询列表
     */
    public List<ExpDecorate> findExpDecorate(ExpDecorate entity);

    /**
     * 查询列表分页  
     */
    public List<ExpDecorate> findExpDecorateByPage(ExpDecorate entity);
    
    public ExpDecorate findExpInfoById(String id);

}
