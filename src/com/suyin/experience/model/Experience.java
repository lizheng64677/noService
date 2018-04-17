package com.suyin.experience.model;

import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.suyin.system.model.Attachment;
import com.suyin.system.model.Page;

public class Experience  implements java.io.Serializable{

    private static final long serialVersionUID = 5454155825314635342L;
    public static final String TABLE_ALIAS = "Experience";
    public static final String ALIAS_EXP_ID = "活动主键";
    public static final String ALIAS_MEMBER_ID = "商家id";
    public static final String ALIAS_TITLE = "活动标题";
    public static final String ALIAS_TYPE = "0:'产品抢购',1:'产品体验',2:'积分兑换',3:'名次倍数申请',4:'点击量达到固定数量申请(分享式申请)',5:'点击量排名申请（人气式申请）' 6:得分排行申请（得分式申请）7:抽奖8:问卷申请";
    public static final String ALIAS_IS_BEGIN = "活动是（0）否（1）开始";
    public static final String ALIAS_PROBABILITY = "活动概率";
    public static final String ALIAS_BEGIN_TIME = "活动开始时间";
    public static final String ALIAS_END_TIME = "活动结束时间";
    public static final String ALIAS_UNIT_DAY = "单位发券天数";
    public static final String ALIAS_installments = "期数";
    public static final String ALIAS_SHOW_TYPE = "0:普通类型1:拼图:2刮刮卡3抽签";
    public static final String ALIAS_ADD_ORDER_NUM = "增加订单数";
    public static final String ALIAS_CREATE_TIME = "createTime";
    public static final String ALIAS_UPDATE_TIME = "更新时间";
    public static final String ALIAS_INFO = "活动简介";
    public static final String ALIAS_EXP_TYPE = "活动类型";

    private Page page;//分页插件


    //columns start

    /**
     * 活动主键       db_column: exp_id 
     */ 	
    private java.lang.Integer expId;
    /**
     * 商家id       db_column: member_id 
     */ 	
    private java.lang.Integer memberId;
    /**
     * 活动标题       db_column: title 
     */ 	
    private java.lang.String title;
    /**
     * 0:'产品抢购',1:'产品体验',2:'积分兑换',3:'名次倍数申请',4:'点击量达到固定数量申请(分享式申请)',5:'点击量排名申请（人气式申请）' 6:得分排行申请（得分式申请）7:抽奖8:问卷申请       db_column: type 
     */ 	
    private java.lang.Integer type;
    /**
     * 活动类型 0抽奖式 1人气式
     */
    private java.lang.Integer expType;
    /**
     * 活动是（0）否（1）开始       db_column: is_begin 
     */ 	
    private java.lang.Integer isBegin;
    /**
     * 活动概率       db_column: probability 
     */ 	
    private java.lang.Integer probability;
    /**
     * 活动开始时间       db_column: begin_time 
     */ 	
    private java.lang.String beginTime;
    /**
     * 活动结束时间       db_column: end_time 
     */ 	
    private java.lang.String endTime;
    /**
     * 单位发券天数       db_column: unit_day 
     */ 	
    private java.lang.Integer unitDay;
    /**
     * 0:普通类型1:拼图:2刮刮卡3抽签       db_column: show_type 
     */ 	
    private java.lang.Integer showType;
    /**
     * 增加订单数       db_column: add_order_num 
     */ 	
    private java.lang.Integer addOrderNum;
    private java.lang.Integer seqNum;
    /**
     * createTime       db_column: create_time 
     */ 	
    private java.lang.String createTime;
    /**
     * 更新时间       db_column: update_time 
     */ 	
    private java.lang.String updateTime;
    /**
     * 省份    db_column: provin_id 
     */     
    private java.lang.String provinId;
    /**
     * 市区       db_column: city_id 
     */     
    private java.lang.String cityId;
    /**
     * 活动简介       db_column: info 
     */ 	
    private java.lang.String info;
    
    /**
     * 是否有标签
     */
    private String isLabel;
    /**
     * 标签内容
     */
    private String label;
    /**
     * 客户端类型
     */
    private String clientType;
    
    /**
     * 已参人数
     */
    private String expNum;
    
    /**
     * 活动详情id
     */
    private String expDetailId;
    
    
    
    
    /**
     * 活动默认图片
     */
    private java.lang.String expImgUrl;

    //columns end
    /**
     * 上传图片应用
     */
    private List<Attachment> attachments;
    
    /**
     * 活动期数
     */
    private String installments;
    

    public String getInstallments() {
		return installments;
	}

	public void setInstallments(String installments) {
		this.installments = installments;
	}

	public String getExpNum() {
		return expNum;
	}

	public void setExpNum(String expNum) {
		this.expNum = expNum;
	}

	public String getExpDetailId() {
		return expDetailId;
	}

	public void setExpDetailId(String expDetailId) {
		this.expDetailId = expDetailId;
	}

	public Experience(){
    }

    public Experience(
                      java.lang.Integer expId
        ){
        this.expId = expId;
    }



    public void setExpId(java.lang.Integer value) {
        this.expId = value;
    }


    public java.lang.Integer getExpId() {
        return this.expId;
    }

    public java.lang.Integer getMemberId() {
        return this.memberId;
    }

    public void setMemberId(java.lang.Integer value) {
        this.memberId = value;
    }

    public java.lang.String getTitle() {
        return this.title;
    }

    public void setTitle(java.lang.String value) {
        this.title = value;
    }

    public java.lang.Integer getType() {
        return this.type;
    }

    public void setType(java.lang.Integer value) {
        this.type = value;
    }

    public java.lang.Integer getExpType()
    {
        return expType;
    }

    public void setExpType(java.lang.Integer expType)
    {
        this.expType = expType;
    }

    public java.lang.Integer getIsBegin() {
        return this.isBegin;
    }

    public void setIsBegin(java.lang.Integer value) {
        this.isBegin = value;
    }

    public java.lang.Integer getProbability() {
        return this.probability;
    }

    public void setProbability(java.lang.Integer value) {
        this.probability = value;
    }

    public java.lang.String getBeginTime() {
        return this.beginTime;
    }

    public void setBeginTime(java.lang.String value) {
        this.beginTime = value;
    }

    public java.lang.String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(java.lang.String value) {
        this.endTime = value;
    }

    public java.lang.Integer getUnitDay() {
        return this.unitDay;
    }

    public void setUnitDay(java.lang.Integer value) {
        this.unitDay = value;
    }

    public java.lang.Integer getShowType() {
        return this.showType;
    }

    public void setShowType(java.lang.Integer value) {
        this.showType = value;
    }

    public java.lang.Integer getAddOrderNum() {
        return this.addOrderNum;
    }

    public void setAddOrderNum(java.lang.Integer value) {
        this.addOrderNum = value;
    }

    public java.lang.String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(java.lang.String value) {
        this.createTime = value;
    }

    public java.lang.String getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(java.lang.String value) {
        this.updateTime = value;
    }

    public java.lang.String getInfo() {
        return this.info;
    }

    public void setInfo(java.lang.String value) {
        this.info = value;
    }

    public Page getPage() {
        return page;
    }
    public void setPage(Page page) {
        this.page = page;
    }

    public java.lang.String getProvinId()
    {
        return provinId;
    }

    public void setProvinId(java.lang.String provinId)
    {
        this.provinId = provinId;
    }

    public java.lang.String getCityId()
    {
        return cityId;
    }

    public void setCityId(java.lang.String cityId)
    {
        this.cityId = cityId;
    }    

    public java.lang.String getExpImgUrl()
    {
        return expImgUrl;
    }

    public void setExpImgUrl(java.lang.String expImgUrl)
    {
        this.expImgUrl = expImgUrl;
    }
    

    public List<Attachment> getAttachments()
    {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments)
    {
        this.attachments = attachments;
    }

    public java.lang.Integer getSeqNum()
    {
        return seqNum;
    }

    public void setSeqNum(java.lang.Integer seqNum)
    {
        this.seqNum = seqNum;
    }

    
    
    public String getIsLabel() {
		return isLabel;
	}

	public void setIsLabel(String isLabel) {
		this.isLabel = isLabel;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
        .append("ExpId",getExpId())
        .append("MemberId",getMemberId())
        .append("Title",getTitle())
        .append("Type",getType())
        .append("IsBegin",getIsBegin())
        .append("Probability",getProbability())
        .append("BeginTime",getBeginTime())
        .append("EndTime",getEndTime())
        .append("UnitDay",getUnitDay())
        .append("ShowType",getShowType())
        .append("AddOrderNum",getAddOrderNum())
        .append("CreateTime",getCreateTime())
        .append("UpdateTime",getUpdateTime()).append("installments",getInstallments())
        .append("Info",getInfo())
        .toString();
    }

    public int hashCode() {
        return new HashCodeBuilder()
        .append(getExpId())
        .toHashCode();
    }

    public boolean equals(Object obj) {
        if(obj instanceof Experience == false) return false;
        if(this == obj) return true;
        Experience other = (Experience)obj;
        return new EqualsBuilder()
        .append(getExpId(),other.getExpId())
        .isEquals();
    }
}

