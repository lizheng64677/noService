package com.suyin.system.service;

import java.util.List;
import java.util.Map;

import com.suyin.system.model.SystemUser;

/**   
 * @Title: UserService.java 
 * @Package com.suyin.commonConfig.service.impl 
 * @Description:
 * @author yyy   
 * @date 2015年7月14日 上午10:37:09 
 * @version V1.0   
 */
public interface UserService {

	/**
	 * 新增用户
	 * @param SystemUser
	 * @return
	 */
	public Integer addUser(SystemUser systemUser);

	/**
	 * 修改用户
	 * @param SystemUser
	 * @return
	 */
	public Integer updateUser(SystemUser systemUser);

	/**
	 * 删除用户
	 * @param SystemUser
	 * @return
	 */
	public Integer deleteUser(String id);

	/**
	 * 查找用户列表
	 * @param SystemUser
	 * @return
	 */
	public List<SystemUser> findUser(SystemUser systemUser);

	/**
	 * 查找用户列表(分页)
	 * @param SystemUser
	 * @return
	 */
	public List<SystemUser> findUserByPage(SystemUser systemUser);

	/**
	 * 根据id查询对应的用户信息
	 * @param systemUser
	 * @return
	 */
	public SystemUser findUserById(SystemUser systemUser);
	
	/**
	 * 修改用户密码
	 * @param systemUser
	 * @return
	 */
	public Integer updateUserPwd(SystemUser systemUser);
	
	/**
	 * 用户默认角色设置
	 * @param systemUser
	 * @return
	 */
	public Integer upadteUserDefaultRole(Map<String, Object> map);

}