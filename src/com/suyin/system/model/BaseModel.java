package com.suyin.system.model;

import java.io.Serializable;
import java.util.Map;

import com.suyin.system.model.Page;

public class BaseModel implements Serializable{

	private static final long serialVersionUID = -392046517173485581L;

	private Page page;//分页插件

	private Map<String,Object> params;//多表复杂参数

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	
}
