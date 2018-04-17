package com.suyin.weChat.model.db;

import java.io.Serializable;
import java.util.Date;

import com.suyin.system.model.Page;



public class TextAndImageMessage implements Serializable{

	private static final long serialVersionUID = 1894107876764484323L;

	private Integer messageId;// '主键',
	private String title;// '标题',
	private Integer messageType;//'信息类型0文本类型,消息类型',
	private String url;// '点击链接url',
	private String pic_url;// '图片地址',
	private String description;// '说明',
	private Date createtime;// '创建时间',
	private Date updatetime;// '更新时间',
	private Integer createUser;// '创建人',
	private Integer reply_is_use;//是否是关注回复
	
	
	
	public Integer getReply_is_use() {
		return reply_is_use;
	}
	public void setReply_is_use(Integer reply_is_use) {
		this.reply_is_use = reply_is_use;
	}
	private String keywords;
	private Integer matchType;
	
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public Integer getMatchType() {
		return matchType;
	}
	public void setMatchType(Integer matchType) {
		this.matchType = matchType;
	}
	private String removeIds;
	
	public String getRemoveIds() {
		return removeIds;
	}
	public void setRemoveIds(String removeIds) {
		this.removeIds = removeIds;
	}
	private Page page;
	
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public Integer getMessageId() {
		return messageId;
	}
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getMessageType() {
		return messageType;
	}
	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPic_url() {
		return pic_url;
	}
	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public Integer getCreateUser() {
		return createUser;
	}
	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}
	
}
