package com.suyin.common.service.impl;

import org.springframework.stereotype.Service;

import com.suyin.common.service.ModuleNameService;

@Service
public class ModuleNameServiceImpl implements ModuleNameService {

	@Override
	public String getModuleName(String alias) {
		return alias;
	}

}
