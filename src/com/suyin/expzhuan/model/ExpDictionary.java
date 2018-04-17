package com.suyin.expzhuan.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.suyin.system.model.BaseTree;
import com.suyin.system.model.Page;

public class ExpDictionary  extends BaseTree{

    private static final long serialVersionUID = 5454155825314635342L;
    public static final String TABLE_ALIAS = "ExpDictionary";
    public static final String ALIAS_DICTIONARY_ID = "主键";
    public static final String ALIAS_PARENT_ID = "父节点编号";
    public static final String ALIAS_DICTIONARY_NAME = "字典名称/选项名称";
    public static final String ALIAS_DICTIONARY_VALUE = "字典选项值";
    public static final String ALIAS_DICTIONARY_TYPE = "字典类型 1-文本框 2-单选按钮 3-复选框 4字典 5选项";
    public static final String ALIAS_MODULE_TYPE = "模块类型 1-个人资料动态属性 2个人中心完善资料 (ps:可以继续添加相应模块)";
    public static final String ALIAS_DICTIONARY_EXPLAIN = "描述说明";
    public static final String ALIAS_DICTIONARY_CODE = "英文code";
    public static final String ALIAS_EXP_ID = "活动id";
    private Page page;//分页插件


    //columns start
    /**
     * 主键       db_column: dictionary_id 
     */ 	
    private java.lang.Integer dictionaryId;
    /**
     * 字典名称/选项名称       db_column: dictionary_name 
     */ 	
    private java.lang.String dictionaryName;
    /**
     * 字典选项值       db_column: dictionary_value 
     */ 	
    private java.lang.String dictionaryValue;
    /**
     * 字典类型 1-文本框 2-单选按钮 3-复选框 4字典 5选项       db_column: dictionary_type 
     */ 	
    private java.lang.Integer dictionaryType;
    /**
     * 模块类型 1-个人资料动态属性 2个人中心完善资料 (ps:可以继续添加相应模块)       db_column: module_type 
     */ 	
    private java.lang.Integer moduleType;
    /**
     * 描述说明       db_column: dictionary_explain 
     */ 	
    private java.lang.String dictionaryExplain;
    /**
     * 英文code       db_column: dictionary_code 
     */ 	
    private java.lang.String dictionaryCode;

    /**
     * 活动id       db_column: exp_id 
     */     
    private java.lang.Integer expId;
    //columns end


    public ExpDictionary(){
    }

    public ExpDictionary(
                         java.lang.Integer dictionaryId
        ){
        this.dictionaryId = dictionaryId;
    }



    public void setDictionaryId(java.lang.Integer value) {
        this.dictionaryId = value;
    }


    public java.lang.Integer getDictionaryId() {
        return this.dictionaryId;
    }



    public java.lang.String getDictionaryName() {
        return this.dictionaryName;
    }

    public void setDictionaryName(java.lang.String value) {
        this.dictionaryName = value;
    }

    public java.lang.String getDictionaryValue() {
        return this.dictionaryValue;
    }

    public void setDictionaryValue(java.lang.String value) {
        this.dictionaryValue = value;
    }

    public java.lang.Integer getDictionaryType() {
        return this.dictionaryType;
    }

    public void setDictionaryType(java.lang.Integer value) {
        this.dictionaryType = value;
    }

    public java.lang.Integer getModuleType() {
        return this.moduleType;
    }

    public void setModuleType(java.lang.Integer value) {
        this.moduleType = value;
    }

    public java.lang.String getDictionaryExplain() {
        return this.dictionaryExplain;
    }

    public void setDictionaryExplain(java.lang.String value) {
        this.dictionaryExplain = value;
    }

    public java.lang.String getDictionaryCode() {
        return this.dictionaryCode;
    }

    public void setDictionaryCode(java.lang.String value) {
        this.dictionaryCode = value;
    }

    public Page getPage() {
        return page;
    }
    public void setPage(Page page) {
        this.page = page;
    }

    public java.lang.Integer getExpId()
    {
        return expId;
    }

    public void setExpId(java.lang.Integer expId)
    {
        this.expId = expId;
    }

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
        .append("DictionaryId",getDictionaryId())
        .append("ParentId",getParentId())
        .append("DictionaryName",getDictionaryName())
        .append("DictionaryValue",getDictionaryValue())
        .append("DictionaryType",getDictionaryType())
        .append("ModuleType",getModuleType())
        .append("DictionaryExplain",getDictionaryExplain())
        .append("DictionaryCode",getDictionaryCode())
        .toString();
    }

    public int hashCode() {
        return new HashCodeBuilder()
        .append(getDictionaryId())
        .toHashCode();
    }

    public boolean equals(Object obj) {
        if(obj instanceof ExpDictionary == false) return false;
        if(this == obj) return true;
        ExpDictionary other = (ExpDictionary)obj;
        return new EqualsBuilder()
        .append(getDictionaryId(),other.getDictionaryId())
        .isEquals();
    }
}

