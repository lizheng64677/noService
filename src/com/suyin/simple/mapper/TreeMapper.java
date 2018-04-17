package com.suyin.simple.mapper;

import java.util.List;
import java.util.Map;

import com.suyin.simple.model.EasyUITree;

public interface TreeMapper {
	/**
	 * easyUI 异步树加载
	 * @return
	 */
	public List<EasyUITree> findEasyUITreeSynByTreeIdByPage(EasyUITree easyUITree);
	
	/**
	 * 检查菜单是否有子集
	 * @param treeId
	 * @return
	 */
	public Integer findEasyUITreeIsChildren(String treeId);
	
	public List<EasyUITree> findEasyUITreeSynByTreeId(EasyUITree easyUITree);
	
	
	/**
	 * 查询所有用户
	 * @return
	 */
	public List<Map<String,Object>> findUser();
	
	
	
}
