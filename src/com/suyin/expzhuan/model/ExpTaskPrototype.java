package com.suyin.expzhuan.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.suyin.system.model.Page;

public class ExpTaskPrototype  implements java.io.Serializable{

    private static final long serialVersionUID = 5454155825314635342L;
    public static final String TABLE_ALIAS = "ExpZhuanPrototype";
    public static final String ALIAS_PROTOTYPE_ID = "主键";
    public static final String ALIAS_EXP_ID = "活动表主键";
    public static final String ALIAS_DICTIONARY_ID = "字典表主键";
    public static final String ALIAS_TEXT_VALUE = "文本框类型的值";
    public static final String ALIAS_IS_SELECTED = "复选框/单选框(1选中,2未选中)";
    public static final String ALIAS_CREATE_TIME = "创建时间";

    private Page page;//分页插件


    //columns start

    /**
     * 主键       db_column: prototype_id 
     */ 	
    private java.lang.Integer prototypeId;
    /**
     * 活动表主键       db_column: exp_id 
     */ 	
    private java.lang.Integer expId;
    /**
     * 字典表主键       db_column: dictionary_id 
     */ 	
    private java.lang.Integer dictionaryId;
    /**
     * 文本框类型的值       db_column: text_value 
     */ 	
    private java.lang.String textValue;
    /**
     * 复选框/单选框(1选中,2未选中)       db_column: is_selected 
     */ 	
    private java.lang.Integer isSelected;
    /**
     * 创建时间       db_column: create_time 
     */ 	
    private java.util.Date createTime;

    private java.lang.Integer  moduleType;

    //columns end


    public ExpTaskPrototype(){
    }

    public ExpTaskPrototype(
                             java.lang.Integer prototypeId
        ){
        this.prototypeId = prototypeId;
    }



    public void setPrototypeId(java.lang.Integer value) {
        this.prototypeId = value;
    }


    public java.lang.Integer getPrototypeId() {
        return this.prototypeId;
    }

    public java.lang.Integer getExpId() {
        return this.expId;
    }

    public void setExpId(java.lang.Integer value) {
        this.expId = value;
    }

    public java.lang.Integer getDictionaryId() {
        return this.dictionaryId;
    }

    public void setDictionaryId(java.lang.Integer value) {
        this.dictionaryId = value;
    }

    public java.lang.String getTextValue() {
        return this.textValue;
    }

    public void setTextValue(java.lang.String value) {
        this.textValue = value;
    }

    public java.lang.Integer getIsSelected() {
        return this.isSelected;
    }

    public void setIsSelected(java.lang.Integer value) {
        this.isSelected = value;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(java.util.Date value) {
        this.createTime = value;
    }

    public java.lang.Integer  getModuleType()
    {
        return moduleType;
    }

    public void setModuleType(java.lang.Integer  moduleType)
    {
        this.moduleType = moduleType;
    }

    public Page getPage() {
        return page;
    }
    public void setPage(Page page) {
        this.page = page;
    }
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
        .append("PrototypeId",getPrototypeId())
        .append("ExpId",getExpId())
        .append("DictionaryId",getDictionaryId())
        .append("TextValue",getTextValue())
        .append("IsSelected",getIsSelected())
        .append("CreateTime",getCreateTime())
        .toString();
    }

    public int hashCode() {
        return new HashCodeBuilder()
        .append(getPrototypeId())
        .toHashCode();
    }

    public boolean equals(Object obj) {
        if(obj instanceof ExpTaskPrototype == false) return false;
        if(this == obj) return true;
        ExpTaskPrototype other = (ExpTaskPrototype)obj;
        return new EqualsBuilder()
        .append(getPrototypeId(),other.getPrototypeId())
        .isEquals();
    }
}

