package com.suyin.system.model;



public class SystemDictionary extends BaseTree {
	private static final long serialVersionUID = 772220930568660064L;

	private Integer dictionary_id;// '主键',

	private String dictionary_name;// '字典名称/选项名称',
	private String dictionary_value;// '字典选项值',
	private Integer dictionary_type;// '字典类型 1-文本框 2-单选按钮 3-复选框',
	private Integer module_type;// '模块类型 1-用户管理 (ps:可以继续添加相应模块)',
	private String dictionary_explain;// '描述说明',
	private String dictionary_code;// '英文code',
	
	
	private Page page;// 分页插件
	
	public Integer getDictionary_id() {
		return dictionary_id;
	}
	public void setDictionary_id(Integer dictionary_id) {
		this.dictionary_id = dictionary_id;
	}
	public String getDictionary_name() {
		return dictionary_name;
	}
	public void setDictionary_name(String dictionary_name) {
		this.dictionary_name = dictionary_name;
	}
	public String getDictionary_value() {
		return dictionary_value;
	}
	public void setDictionary_value(String dictionary_value) {
		this.dictionary_value = dictionary_value;
	}
	public Integer getDictionary_type() {
		return dictionary_type;
	}
	public void setDictionary_type(Integer dictionary_type) {
		this.dictionary_type = dictionary_type;
	}
	public Integer getModule_type() {
		return module_type;
	}
	public void setModule_type(Integer module_type) {
		this.module_type = module_type;
	}
	public String getDictionary_explain() {
		return dictionary_explain;
	}
	public void setDictionary_explain(String dictionary_explain) {
		this.dictionary_explain = dictionary_explain;
	}
	public String getDictionary_code() {
		return dictionary_code;
	}
	public void setDictionary_code(String dictionary_code) {
		this.dictionary_code = dictionary_code;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}

}
