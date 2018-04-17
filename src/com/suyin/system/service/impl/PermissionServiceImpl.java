package com.suyin.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suyin.system.mapper.PermissionMapper;
import com.suyin.system.service.PermissionService;


/**   
 * @Title: PermissionServiceImpl.java 
 * @Package com.suyin.commonConfig.service.impl 
 * @Description:
 * @author yyy   
 * @date 2015年7月10日 下午1:52:14 
 * @version V1.0   
 */
@Transactional
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

	private static final Logger log=Logger.getLogger(PermissionServiceImpl.class);
	@Autowired
	private PermissionMapper permissionMapper;
	/* (non-Javadoc)
	 * @see com.suyin.commonConfig.service.impl.PermissionService#addPermission(java.util.List)
	 */
	@Override
	public  Integer addPermission(Map<String, Object> map){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Integer result=0;
		try {
			//先删除原先配置的菜单权限
			permissionMapper.deletePermission(String.valueOf(map.get("roleId")));

			//添加新的菜单权限
			if(map.get("ids")==null){
				return 0;
			}
			Map<String, Object> dataMap=null;
			String [] idArr=String.valueOf(map.get("ids")).split(",");
			for(int i=0;i<idArr.length;i++){
				dataMap=new HashMap<String, Object>();
				dataMap.put("rescId", idArr[i]);
				dataMap.put("roleId", String.valueOf(map.get("roleId")));
				list.add(dataMap);
			}
			result=permissionMapper.addPermission(list);
		} catch (Exception e) {
			log.error("PermissionServiceImpl-->addPermission保存角色权限失败"+e.getMessage());
			e.printStackTrace();
		}
		return result;
		
	}

	/* (non-Javadoc)
	 * @see com.suyin.commonConfig.service.impl.PermissionService#deletePermission(java.lang.String)
	 */
	@Override
	public  Integer deletePermission(String id){
		return permissionMapper.deletePermission(id);
	}

	/* (non-Javadoc)
	 * @see com.suyin.commonConfig.service.impl.PermissionService#findPermission(java.lang.Integer)
	 */
	@Override
	public  List<Map<String, Object>> findPermission(Integer roleId){
		return permissionMapper.findPermission(roleId);
	}
	
	public  List<Map<String, Object>> findMenuByUserId(Integer userId){
		return permissionMapper.findMenuByUserRoleId(userId);
	}
}
