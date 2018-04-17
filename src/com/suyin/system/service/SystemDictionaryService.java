package com.suyin.system.service;

import java.util.List;

import com.suyin.system.model.SystemDictionary;

public interface SystemDictionaryService {
	public Integer addSystemDictionary(SystemDictionary systemDictionary);

	public Integer deleteSystemDictionary(SystemDictionary systemDictionary);

	public Integer updateSystemDictionary(SystemDictionary systemDictionary);

	public List<SystemDictionary> findSystemDictionaryByPage(
			SystemDictionary systemDictionary);
	
	public SystemDictionary getSystemDictionary(SystemDictionary systemDictionary);
	
	public List<SystemDictionary> findSystemDictionary(SystemDictionary systemDictionary);
}
