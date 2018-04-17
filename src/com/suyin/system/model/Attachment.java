package com.suyin.system.model;

import java.io.Serializable;
import java.util.Date;

public class Attachment implements Serializable {

	private static final long serialVersionUID = 4213667196861566590L;

	private Integer attachment_id;// '附件主键',
	private String module;// '业务模块',
	private Integer entity;// '业务实体',
	private String entity_attribute;// '业务实体属性',
	private String file_name;// '附件名称',
	private String file_type;// '附件类型',
	private Integer file_size;// '附件大小',
	private String file_path;// '附件物理路径',
	private String display_path;// '文件显示路径',
	private Integer deleted;// '删除标志',
	private Date create_time;// '创建时间',
	private Date update_time;// '修改时间'

	public Integer getAttachment_id() {
		return attachment_id;
	}

	public void setAttachment_id(Integer attachment_id) {
		this.attachment_id = attachment_id;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public Integer getEntity() {
		return entity;
	}

	public void setEntity(Integer entity) {
		this.entity = entity;
	}

	public String getEntity_attribute() {
		return entity_attribute;
	}

	public void setEntity_attribute(String entity_attribute) {
		this.entity_attribute = entity_attribute;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_type() {
		return file_type;
	}

	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}

	public Integer getFile_size() {
		return file_size;
	}

	public void setFile_size(Integer file_size) {
		this.file_size = file_size;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public String getDisplay_path() {
		return display_path;
	}

	public void setDisplay_path(String display_path) {
		this.display_path = display_path;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

}
