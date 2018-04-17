package com.suyin.system.service;

import java.util.List;
import java.util.Map;

/**   
 * @Title: PermissionService.java 
 * @Package com.suyin.commonConfig.service.impl 
 * @Description:
 * @author yyy   
 * @date 2015年7月10日 下午2:17:13 
 * @version V1.0   
 */
public interface PermissionService {

	/**
	 * 新增角色权限
	 * @param Resource
	 * @return
	 */
	public Integer addPermission(Map<String, Object> map);

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
	 * 根据用户id查询对应的菜单列表
	 * @param userId
	 * @return
	 */
	public List<Map<String, Object>> findMenuByUserId(Integer userId);

}