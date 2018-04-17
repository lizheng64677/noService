package com.suyin.system.mapper;

import java.util.List;

import com.suyin.system.model.SystemResource;


/**   
 * @Title: menuMapper.java 
 * @Package com.suyin.system.commonConfig.mapper 
 * @Description:菜单管理
 * @author yyy   
 * @date 2015年7月8日 上午11:46:29 
 * @version V1.0   
 */
public interface MenuMapper {

	public Integer addMenu(SystemResource systemResource);
	
	public Integer updateMenu(SystemResource systemResource);
	
	public Integer deleteMenu(Integer id);
	
	public List<SystemResource> findMenu(SystemResource systemResource);
}
