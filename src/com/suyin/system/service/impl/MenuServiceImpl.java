package com.suyin.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suyin.system.mapper.MenuMapper;
import com.suyin.system.model.SystemResource;
import com.suyin.system.service.MenuService;


/**   
 * @Title: ServiceImpl.java 
 * @Package com.suyin.system.commonConfig.service.impl 
 * @Description:
 * @author yyy   
 * @date 2015年7月8日 下午2:10:48 
 * @version V1.0   
 */
@Transactional
@Service("menuService")
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenuMapper menuMapper;

	/* (non-Javadoc)
	 * @see com.suyin.system.commonConfig.service.impl.MenuService#addMenu(com.suyin.system.commonConfig.model.SystemResource)
	 */
	@Override
	public  Integer addMenu(
			SystemResource systemResource){
		return menuMapper.addMenu(systemResource);
	}

	/* (non-Javadoc)
	 * @see com.suyin.system.commonConfig.service.impl.MenuService#updateMenu(com.suyin.system.commonConfig.model.SystemResource)
	 */
	@Override
	public  Integer updateMenu(
			SystemResource systemResource){
		return menuMapper.updateMenu(systemResource);
	}

	/* (non-Javadoc)
	 * @see com.suyin.system.commonConfig.service.impl.MenuService#deleteMenu(java.lang.Integer)
	 */
	@Override
	public  Integer deleteMenu(
			Integer id){
		return menuMapper.deleteMenu(id);
	}

	/* (non-Javadoc)
	 * @see com.suyin.system.commonConfig.service.impl.MenuService#findMenu(com.suyin.system.commonConfig.model.SystemResource)
	 */
	@Override
	public  List<SystemResource> findMenu(
			SystemResource systemResource){
		return menuMapper.findMenu(systemResource);
	}
	
	public  SystemResource findMenuById(
			SystemResource systemResource){
		List<SystemResource> list=menuMapper.findMenu(systemResource);
		return (list!=null&&list.size()>0)?list.get(0):null;
	}
}
