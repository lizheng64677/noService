package com.suyin.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.suyin.system.mapper.SystemDictionaryMapper;
import com.suyin.system.model.SystemDictionary;
import com.suyin.system.service.SystemDictionaryService;

@Transactional
@Service("systemDictionaryService")
public class SystemDictionaryServiceImpl implements SystemDictionaryService {

	@Autowired
	SystemDictionaryMapper systemDictionaryMapper;

	public Integer addSystemDictionary(SystemDictionary systemDictionary) {
		return systemDictionaryMapper.addSystemDictionary(systemDictionary);
	}

	@Override
	public Integer deleteSystemDictionary(SystemDictionary systemDictionary) {

		return systemDictionaryMapper.deleteSystemDictionary(systemDictionary);
	}

	@Override
	public Integer updateSystemDictionary(SystemDictionary systemDictionary) {
		
		return systemDictionaryMapper.updateSystemDictionary(systemDictionary);
	}

	@Override
	public List<SystemDictionary> findSystemDictionaryByPage(
			SystemDictionary systemDictionary) {

		return this.checkTree( systemDictionaryMapper
				.findSystemDictionaryByPage(systemDictionary));
	}

	@Override
	public SystemDictionary getSystemDictionary(
			SystemDictionary systemDictionary) {
		if (!this.findSystemDictionary(systemDictionary).isEmpty()
				&& 1 == this.findSystemDictionary(systemDictionary)
						.size()) {
			return this.findSystemDictionary(systemDictionary).get(0);
		}
		return null;
	}

	@Override
	public List<SystemDictionary> findSystemDictionary(
			SystemDictionary systemDictionary) {

		return checkTree(systemDictionaryMapper.findSystemDictionary(systemDictionary));
	}

	private List<SystemDictionary> checkTree(List<SystemDictionary> list) {
		for (SystemDictionary dic : list) {
			if (dic.getSonTrees() > 0) {
				dic.setState("closed");
			} else {
				dic.setState("open");
			}
			if(1==dic.getDictionary_type()){
				dic.setIconCls("icon-more");
			}if(2==dic.getDictionary_type()){
				dic.setIconCls("icon-lock");
			}if(3==dic.getDictionary_type()){
				dic.setIconCls("icon-sum");
			}if(4==dic.getDictionary_type()){
				dic.setIconCls("icon-man");
			}if(5==dic.getDictionary_type()){
				dic.setIconCls("icon-undo");
			}
		}
		return list;
	}
}
