package com.suyin.system.mapper;

import java.util.List;
import java.util.Map;

import com.suyin.system.model.SystemUser;
/**
 * 用户mapper
* @Title: UserMapper.java 
* @Package com.suyin.commonConfig.mapper 
* @Description:
* @author yyy   
* @date 2015年7月14日 上午10:30:38 
* @version V1.0
 */
public interface UserMapper {

	public Integer addUser(SystemUser systemUser);
	
	/**
	 * 新增用户对应角色的关联信息
	 * @param baseModel
	 * @return
	 */
	public Integer addUserRefRole(List<Map<String, Object>> list);
	
	/**
	 * 删除用户对应角色的关联信息
	 * @param baseModel
	 * @return
	 */
	public Integer deleteUserRefRole(Integer userId);
	
	/**
	 * 用户默认角色设置
	 * @param baseModel
	 * @return
	 */
	public Integer upadteUserDefaultRole(Map<String, Object> map);
	
	public Integer updateUser(SystemUser systemUser);
	
	public Integer deleteUser(String id);
	
	public List<SystemUser> findUser(SystemUser systemUser);
	
	public List<SystemUser> findUserByPage(SystemUser systemUser);
	
	/**
	 * 修改用户密码
	 * @param systemUser
	 * @return
	 */
	public Integer updateUserPwd(SystemUser systemUser);
}
