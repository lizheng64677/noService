package com.suyin.system.service;

import java.util.List;
import java.util.Map;

import com.suyin.system.model.SystemRole;

/**   
 * @Title: RoleService.java 
 * @Package com.suyin.commonConfig.service.impl 
 * @Description:
 * @author yyy   
 * @date 2015年7月9日 下午3:54:27 
 * @version V1.0   
 */
public interface RoleService {

	/**
	 * 新增角色
	 * @param SystemRole
	 * @return
	 */
	public Integer addRole(SystemRole systemRole);

	/**
	 * 修改角色
	 * @param SystemRole
	 * @return
	 */
	public Integer updateRole(SystemRole systemRole);

	/**
	 * 删除角色
	 * @param SystemRole
	 * @return
	 */
	public Integer deleteRole(String  id);

	/**
	 * 查找角色列表
	 * @param SystemRole
	 * @return
	 */
	public List<SystemRole> findRole(SystemRole systemRole);

	/**
	 * 根据id查询对应的角色信息
	 * @param systemRole
	 * @return
	 */
	public SystemRole findRoleById(SystemRole systemRole);
	
	/**
	 * 查询角色列表,用户已绑定的state为0
	 * @param userId
	 * @return
	 */
	public List<Map<String, Object>> findRoleForUser(Integer userId);
	
	/**
	 * 查询用户对应的角色列表
	 * @param userId
	 * @return
	 */
	public List<Map<String, Object>> findRoleByUserId(Integer userId);
	
	/**
	 * 查询用户默认的角色信息
	 * @param userId
	 * @return
	 */
	public Map<String, Object> getUserDeaultRole(Integer userId);

}