package com.suyin.simple.service;

import java.util.List;

import com.suyin.simple.model.EasyUITree;

public interface TreeService {
	public List<EasyUITree> findEasyUITreeSynByTreeIdByPage(EasyUITree easyUITree);
	
	public List<EasyUITree> findEasyUITreeSynByTreeId(EasyUITree easyUITree);
}
