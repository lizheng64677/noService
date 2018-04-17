package com.suyin.system.mapper;

import java.util.List;

import com.suyin.system.model.SystemDictionary;

public interface SystemDictionaryMapper {

	public Integer addSystemDictionary(SystemDictionary systemDictionary);

	public Integer deleteSystemDictionary(SystemDictionary systemDictionary);

	public Integer updateSystemDictionary(SystemDictionary systemDictionary);

	public List<SystemDictionary> findSystemDictionaryByPage(
			SystemDictionary systemDictionary);
	
	public List<SystemDictionary> findSystemDictionary(SystemDictionary systemDictionary);
}
