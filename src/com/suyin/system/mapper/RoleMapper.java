package com.suyin.system.mapper;

import java.util.List;
import java.util.Map;

import com.suyin.system.model.SystemRole;


/**   
 * @Title: RoleMapper.java 
 * @Package com.suyin.system.commonConfig.mapper 
 * @Description:角色管理
 * @author yyy   
 * @date 2015年7月8日 上午11:46:29 
 * @version V1.0   
 */
public interface RoleMapper {

	public Integer addRole(SystemRole systemRole);
	
	public Integer updateRole(SystemRole systemRole);
	
	public Integer deleteRole(String[] id);
	
	public List<SystemRole> findRole(SystemRole systemRole);
	
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
}
