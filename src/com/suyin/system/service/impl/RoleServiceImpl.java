package com.suyin.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suyin.system.mapper.RoleMapper;
import com.suyin.system.model.SystemRole;
import com.suyin.system.service.RoleService;

/**   
 * @Title: ServiceImpl.java 
 * @Package com.suyin.commonConfig.service.impl 
 * @Description:角色管理实现类
 * @author yyy   
 * @date 2015年7月9日 下午3:50:26 
 * @version V1.0   
 */
@Transactional
@Service("roleService")
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleMapper roleMapper;
	/* (non-Javadoc)
	 * @see com.suyin.commonConfig.service.impl.RoleService#addRole(com.suyin.commonConfig.model.SystemRole)
	 */
	@Override
	public  Integer addRole(SystemRole systemRole){
		return roleMapper.addRole(systemRole);
	}

	/* (non-Javadoc)
	 * @see com.suyin.commonConfig.service.impl.RoleService#updateRole(com.suyin.commonConfig.model.SystemRole)
	 */
	@Override
	public  Integer updateRole(SystemRole systemRole){
		return roleMapper.updateRole(systemRole);
	}

	/* (non-Javadoc)
	 * @see com.suyin.commonConfig.service.impl.RoleService#deleteRole(java.lang.Integer)
	 */
	@Override
	public  Integer deleteRole(String id){
		return roleMapper.deleteRole(id.split(","));
	}

	/* (non-Javadoc)
	 * @see com.suyin.commonConfig.service.impl.RoleService#findRole(com.suyin.commonConfig.model.SystemRole)
	 */
	@Override
	public  List<SystemRole> findRole(SystemRole systemRole){
		return roleMapper.findRole(systemRole);
	}
	
	/* (non-Javadoc)
	 * @see com.suyin.commonConfig.service.impl.RoleService#findRoleById(com.suyin.commonConfig.model.SystemRole)
	 */
	@Override
	public  SystemRole findRoleById(SystemRole systemRole){
		List<SystemRole> list=roleMapper.findRole(systemRole);
		return (list!=null&&list.size()>0)?list.get(0):null;
	}
	
	/**
	 * 查询角色列表,用户已绑定的state为0
	 * @param userId
	 * @return
	 */
	public List<Map<String, Object>> findRoleForUser(Integer userId){
		return roleMapper.findRoleForUser(userId);
	}
	
	/**
	 * 查询用户对应的角色列表
	 * @param userId
	 * @return
	 */
	public List<Map<String, Object>> findRoleByUserId(Integer userId){
		return roleMapper.findRoleByUserId(userId);
	}
	
	/**
	 * 查询用户默认的角色信息
	 * @param userId
	 * @return
	 */
	public Map<String, Object> getUserDeaultRole(Integer userId){
		List<Map<String, Object>> list=roleMapper.findRoleByUserId(userId);
		for(Map<String, Object> map:list){
			if("1".equals(String.valueOf(map.get("is_default")))){
				return map;
			}
		}
		return null;
	}
}
