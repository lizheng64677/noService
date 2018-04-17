package com.suyin.system.mapper;

import java.util.List;
import java.util.Map;

/**   
 * @Title: ResourceRoleMapper.java 
 * @Package com.suyin.commonConfig.mapper 
 * @Description:权限管理mapper
 * @author yyy   
 * @date 2015年7月10日 下午1:49:23 
 * @version V1.0   
 */
public interface PermissionMapper {

	/**
	 * 新增角色权限
	 * @param Resource
	 * @return
	 */
	public Integer addPermission(List<Map<String, Object>> list);
	
	/**
	 * 删除角色权限
	 * @param Resource
	 * @return
	 */
	public Integer deletePermission(String id);
	
	/**
	 * 查找菜单列表，角色有对应的菜单对象返回state 1,否则0
	 * @param Resource
	 * @return
	 */
	public List<Map<String, Object>> findPermission(Integer roleId);
	
	/**
	 * 根据用户角色关联对象id获取对应的权限菜单列表
	 * @param roleId
	 * @return
	 */
	public List<Map<String, Object>> findMenuByUserRoleId(Integer userRoleId);
}
