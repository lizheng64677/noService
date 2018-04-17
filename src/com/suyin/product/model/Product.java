package com.suyin.product.model;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.suyin.system.model.Attachment;
import com.suyin.system.model.Page;

public class Product  implements java.io.Serializable{

    private static final long serialVersionUID = 5454155825314635342L;
    public static final String TABLE_ALIAS = "Product";
    public static final String ALIAS_PRO_ID = "主键id";
    public static final String ALIAS_MEMBER_ID = "产品所属商家id";
    public static final String ALIAS_PRO_NAME = "产品名称";
    public static final String ALIAS_PRICE = "产品价格";
    public static final String ALIAS_TITLE = "产品标题";
    public static final String ALIAS_PRO_IMG = "产品图片 多张 ，隔开";
    public static final String ALIAS_UNIT = "产品单位,如：份，个等";
    public static final String ALIAS_ENVI_SCORE = "环境评分";
    public static final String ALIAS_SERVE_SCORE = "服务评分";
    public static final String ALIAS_QCD_SCORE = "质量评分";
    public static final String ALIAS_SEQ_NO = "序号 越大排在越前面";
    public static final String ALIAS_CREATE_TIME = "创建时间";
    public static final String ALIAS_UPDATE_TIME = "更新时间";
    public static final String ALIAS_PRO_INFO = "产品说明，套餐详情";
    public static final String ALIAS_ACTIVITY_INFO = "活动简介";
    public static final String ALIAS_USE_INFO = "使用须知";

    private Page page;//分页插件


    //columns start

    /**
     * 主键id       db_column: pro_id 
     */ 	
    private java.lang.Integer proId;
    /**
     * 产品所属商家id       db_column: member_id 
     */ 	
    private java.lang.Integer memberId;
    /**
     * 产品名称       db_column: pro_name 
     */ 	
    private java.lang.String proName;
    /**
     * 产品价格       db_column: price 
     */ 	
    private BigDecimal price;
    /**
     * 产品标题       db_column: title 
     */ 	
    private java.lang.String title;
    /**
     * 产品图片 多张 ，隔开       db_column: pro_img 
     */ 	
    private java.lang.String proImg;
    /**
     * 产品单位,如：份，个等       db_column: unit 
     */ 	
    private java.lang.String unit;
    /**
     * 环境评分       db_column: envi_score 
     */ 	
    private java.lang.Double enviScore;
    /**
     * 服务评分       db_column: serve_score 
     */ 	
    private java.lang.Double serveScore;
    /**
     * 质量评分       db_column: qcd_score 
     */ 	
    private java.lang.Double qcdScore;
    /**
     * 序号 越大排在越前面       db_column: seq_no 
     */ 	
    private java.lang.Integer seqNo;
    /**
     * 创建时间       db_column: create_time 
     */ 	
    private java.lang.String createTime;
    /**
     * 更新时间       db_column: update_time 
     */ 	
    private java.lang.String updateTime;
    /**
     * 产品说明，套餐详情       db_column: pro_info 
     */ 	
    private java.lang.String proInfo;
    /**
     * 活动简介       db_column: activity_info 
     */ 	
    private java.lang.String activityInfo;
    /**
     * 使用须知       db_column: use_info 
     */ 	
    private java.lang.String useInfo;
    /**
     * 上传图片应用
     */
    private List<Attachment> attachments;
    //columns end

    private java.lang.String busname;//商家名

    public Product(){
    }

    public Product(
                   java.lang.Integer proId
        ){
        this.proId = proId;
    }



    public void setProId(java.lang.Integer value) {
        this.proId = value;
    }


    public java.lang.Integer getProId() {
        return this.proId;
    }

    public java.lang.Integer getMemberId() {
        return this.memberId;
    }

    public void setMemberId(java.lang.Integer value) {
        this.memberId = value;
    }

    public java.lang.String getProName() {
        return this.proName;
    }

    public void setProName(java.lang.String value) {
        this.proName = value;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal value) {
        this.price = value;
    }

    public java.lang.String getTitle() {
        return this.title;
    }

    public void setTitle(java.lang.String value) {
        this.title = value;
    }

    public java.lang.String getProImg() {
        return this.proImg;
    }

    public void setProImg(java.lang.String value) {
        this.proImg = value;
    }

    public java.lang.String getUnit() {
        return this.unit;
    }

    public void setUnit(java.lang.String value) {
        this.unit = value;
    }

    public java.lang.Double getEnviScore() {
        return this.enviScore;
    }

    public void setEnviScore(java.lang.Double value) {
        this.enviScore = value;
    }

    public java.lang.Double getServeScore() {
        return this.serveScore;
    }

    public void setServeScore(java.lang.Double value) {
        this.serveScore = value;
    }

    public java.lang.Double getQcdScore() {
        return this.qcdScore;
    }

    public void setQcdScore(java.lang.Double value) {
        this.qcdScore = value;
    }

    public java.lang.Integer getSeqNo() {
        return this.seqNo;
    }

    public void setSeqNo(java.lang.Integer value) {
        this.seqNo = value;
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

    public java.lang.String getProInfo() {
        return this.proInfo;
    }

    public void setProInfo(java.lang.String value) {
        this.proInfo = value;
    }

    public java.lang.String getActivityInfo() {
        return this.activityInfo;
    }

    public void setActivityInfo(java.lang.String value) {
        this.activityInfo = value;
    }

    public java.lang.String getUseInfo() {
        return this.useInfo;
    }

    public void setUseInfo(java.lang.String value) {
        this.useInfo = value;
    }

    public java.lang.String getBusname()
    {
        return busname;
    }

    public void setBusname(java.lang.String busname)
    {
        this.busname = busname;
    }  
    public List<Attachment> getAttachments()
    {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments)
    {
        this.attachments = attachments;
    }

    public Page getPage() {
        return page;
    }
    public void setPage(Page page) {
        this.page = page;
    }
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
        .append("ProId",getProId())
        .append("MemberId",getMemberId())
        .append("ProName",getProName())
        .append("Price",getPrice())
        .append("Title",getTitle())
        .append("ProImg",getProImg())
        .append("Unit",getUnit())
        .append("EnviScore",getEnviScore())
        .append("ServeScore",getServeScore())
        .append("QcdScore",getQcdScore())
        .append("SeqNo",getSeqNo())
        .append("CreateTime",getCreateTime())
        .append("UpdateTime",getUpdateTime())
        .append("ProInfo",getProInfo())
        .append("ActivityInfo",getActivityInfo())
        .append("UseInfo",getUseInfo())
        .toString();
    }

    public int hashCode() {
        return new HashCodeBuilder()
        .append(getProId())
        .toHashCode();
    }

    public boolean equals(Object obj) {
        if(obj instanceof Product == false) return false;
        if(this == obj) return true;
        Product other = (Product)obj;
        return new EqualsBuilder()
        .append(getProId(),other.getProId())
        .isEquals();
    }
}

