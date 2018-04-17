package com.suyin.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suyin.system.mapper.UserMapper;
import com.suyin.system.model.SystemUser;
import com.suyin.system.service.UserService;
import com.suyin.system.util.Md5Util;

/**   
 * @Title: ServiceImpl.java 
 * @Package com.suyin.commonConfig.service.impl 
 * @Description:用户管理实现类
 * @author yyy   
 * @date 2015年7月9日 下午3:50:26 
 * @version V1.0   
 */
@Transactional
@Service("userService")
public class UserServiceImpl implements UserService{
	private final static Logger log=Logger.getLogger(UserServiceImpl.class);
	@Autowired
	private UserMapper userMapper;
	/* (non-Javadoc)
	 * @see com.suyin.commonConfig.service.impl.UserService#addUser(com.suyin.commonConfig.model.SystemUser)
	 */
	@Override
	public Integer addUser(SystemUser systemUser){
		Integer result=0;
		try {
			if(systemUser==null){
				return result;
			}
			systemUser.setLoginPwd(Md5Util.toMD5(systemUser.getLoginPwd()));
			result = userMapper.addUser(systemUser);
			if(result>0){
				String[] roleArr=systemUser.getRole().split(",");
				List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
				Map<String, Object>	map=null;
				for(int i=0;i<roleArr.length;i++){
					map=new HashMap<String, Object>();
					map.put("userId", systemUser.getId());
					map.put("roleId", roleArr[i]);
					map.put("isDefault", i==0?1:0);
					list.add(map);
				}
				result=userMapper.addUserRefRole(list);
			}
		} catch (Exception e) {
			new RuntimeException();
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.suyin.commonConfig.service.impl.UserService#updateUser(com.suyin.commonConfig.model.SystemUser)
	 */
	@Override
	public Integer updateUser(SystemUser systemUser){
		Integer result=0;
		try {
			if(systemUser==null){
				return result;
			}
			result = userMapper.updateUser(systemUser);
			if(result>0){
				userMapper.deleteUserRefRole(systemUser.getId());
				String[] roleArr=systemUser.getRole().split(",");
				List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
				Map<String, Object>	map=null;
				for(int i=0;i<roleArr.length;i++){
					map=new HashMap<String, Object>();
					map.put("userId", systemUser.getId());
					map.put("roleId", roleArr[i]);
					map.put("isDefault", i==0?1:0);
					list.add(map);
				}
				result=userMapper.addUserRefRole(list);
			}
		} catch (Exception e) {
			log.error("用户信息修改异常"+e.getMessage());
			new RuntimeException();
			e.printStackTrace();
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.suyin.commonConfig.service.impl.UserService#deleteUser(java.lang.String)
	 */
	@Override
	public Integer deleteUser(String  id){
		return userMapper.deleteUser(id);
	}

	/* (non-Javadoc)
	 * @see com.suyin.commonConfig.service.impl.UserService#findUser(com.suyin.commonConfig.model.SystemUser)
	 */
	@Override
	public List<SystemUser> findUser(SystemUser systemUser){
		return userMapper.findUser(systemUser);
	}
	
	/* (non-Javadoc)
	 * @see com.suyin.commonConfig.service.impl.UserService#findUserByPage(com.suyin.commonConfig.model.SystemUser)
	 */
	@Override
	public List<SystemUser> findUserByPage(SystemUser systemUser){
		return userMapper.findUserByPage(systemUser);
	}

	/* (non-Javadoc)
	 * @see com.suyin.commonConfig.service.impl.UserService#findUserById(com.suyin.commonConfig.model.SystemUser)
	 */
	@Override
	public SystemUser findUserById(SystemUser systemUser){
		if(systemUser!=null&&systemUser.getLoginPwd()!=null){
			systemUser.setLoginPwd(Md5Util.toMD5(systemUser.getLoginPwd()));
		}
		List<SystemUser> list=userMapper.findUser(systemUser);
		return list!=null&&!list.isEmpty()?list.get(0):null;
	}
	
	/**
	 * 修改用户密码
	 * @param systemUser
	 * @return
	 */
	public Integer updateUserPwd(SystemUser systemUser){
		return userMapper.updateUserPwd(systemUser);
	}
	
	/**
	 * 用户默认角色设置
	 * @param map
	 * @return
	 */
	public Integer upadteUserDefaultRole(Map<String, Object> map){
		if(map!=null){
			Map<String, Object> paraMap=new HashMap<String, Object>();
			paraMap.put("isDefault", 1);
			paraMap.put("userRoleId", map.get("curUserRoleId"));
			userMapper.upadteUserDefaultRole(paraMap);
			paraMap.put("isDefault", 0);
			paraMap.put("userRoleId", map.get("defaultUserRoleId"));
			return userMapper.upadteUserDefaultRole(paraMap);
		}
		return 0;
	}
}
