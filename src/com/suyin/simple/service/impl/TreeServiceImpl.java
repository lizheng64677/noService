package com.suyin.simple.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suyin.simple.mapper.TreeMapper;
import com.suyin.simple.model.EasyUITree;
import com.suyin.simple.service.TreeService;

@Transactional
@Service("treeService")
public class TreeServiceImpl implements TreeService {

	@Autowired
	private TreeMapper treeMapper;

	@Override
	public List<EasyUITree> findEasyUITreeSynByTreeIdByPage(EasyUITree easyUITree) {
		List<EasyUITree> list = treeMapper
				.findEasyUITreeSynByTreeIdByPage(easyUITree);
		int i = 0;
		for (EasyUITree tree : list) {
			i = treeMapper.findEasyUITreeIsChildren(tree.getId().toString());
			if (i > 0) {
				tree.setState("closed");
				tree.setIconCls("icon-save");
			} else {
				tree.setState("open");
				tree.setIconCls("icon-save");
			}
		}
		return list;
	}

	@Override
	public List<EasyUITree> findEasyUITreeSynByTreeId(EasyUITree easyUITree) {
		List<EasyUITree> list = treeMapper
				.findEasyUITreeSynByTreeId(easyUITree);
		int i = 0;
		for (EasyUITree tree : list) {
			i = treeMapper.findEasyUITreeIsChildren(tree.getId().toString());
			if (i > 0) {
				tree.setState("closed");
				tree.setIconCls("icon-save");
			} else {
				tree.setState("open");
				tree.setIconCls("icon-save");
			}
		}
		return list;
	}

}
