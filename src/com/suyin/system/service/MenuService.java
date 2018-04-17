package com.suyin.system.service;

import java.util.List;

import com.suyin.system.model.SystemResource;

/**   
 * @Title: MenuService.java 
 * @Package com.suyin.system.commonConfig.service.impl 
 * @Description:
 * @author yyy   
 * @date 2015年7月8日 下午2:18:41 
 * @version V1.0   
 */
public interface MenuService {

	/**
	 * 新增菜单
	 * @param SystemResource
	 * @return
	 */
	public abstract Integer addMenu(SystemResource systemResource);

	/**
	 * 修改菜单
	 * @param SystemResource
	 * @return
	 */
	public abstract Integer updateMenu(SystemResource systemResource);

	/**
	 * 删除菜单
	 * @param SystemResource
	 * @return
	 */
	public abstract Integer deleteMenu(Integer id);

	/**
	 * 查找菜单列表
	 * @param SystemResource
	 * @return
	 */
	public abstract List<SystemResource> findMenu(SystemResource systemResource);
	
	/**
	 * 根据id查询对应的菜单信息
	 * @param systemResource
	 * @return
	 */
	public  SystemResource findMenuById(SystemResource systemResource);

}