package com.suyin.coin.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.suyin.system.model.Page;

public class NouserCoinTeller  implements java.io.Serializable{

    private static final long serialVersionUID = 5454155825314635342L;
    public static final String TABLE_ALIAS = "NouserCoinTeller";
    private Page page;//分页插件


    //columns start

    /**
     * logId       db_column: log_id 
     */ 	
    private java.lang.Integer logId;
    /**
     * userId       db_column: user_id 
     */ 	
    private java.lang.Integer userId;

    private String userPhone;


    public final String getUserPhone() {
        return userPhone;
    }

    public final void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    /**
     * coin       db_column: coin 
     */ 	
    private java.lang.Integer coin;
    /**
     * money       db_column: money 
     */ 	
    private BigDecimal money;
    /**
     * 0表示新建，1表示同意，2表示拒绝       db_column: status 
     */ 	
    private java.lang.Integer status;
    /**
     * createTime       db_column: create_time 
     */ 	
    private String createTime;
    /**
     * updateTime       db_column: update_time 
     */ 	
    private String updateTime;


    //columns end
    private String userName;
    private int count;

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public NouserCoinTeller(){
    }

    public NouserCoinTeller(java.lang.Integer logId){

        this.logId = logId;
    }

    public void setLogId(java.lang.Integer value) {
        this.logId = value;
    }


    public java.lang.Integer getLogId() {
        return this.logId;
    }

    public java.lang.Integer getUserId() {
        return this.userId;
    }

    public void setUserId(java.lang.Integer value) {
        this.userId = value;
    }

    public java.lang.Integer getCoin() {
        return this.coin;
    }

    public void setCoin(java.lang.Integer value) {
        this.coin = value;
    }

    public BigDecimal getMoney() {
        return this.money;
    }

    public void setMoney(BigDecimal value) {
        this.money = value;
    }

    public java.lang.Integer getStatus() {
        return this.status;
    }

    public void setStatus(java.lang.Integer value) {
        this.status = value;
    }
    public final String getCreateTime() {
        return createTime;
    }

    public final void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public final String getUpdateTime() {
        return updateTime;
    }

    public final void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
    public Page getPage() {
        return page;
    }
    public void setPage(Page page) {
        this.page = page;
    }
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
        .append("LogId",getLogId())
        .append("UserId",getUserId())
        .append("Coin",getCoin())
        .append("Money",getMoney())
        .append("Status",getStatus())
        .append("CreateTime",getCreateTime())
        .append("UpdateTime",getUpdateTime())
        .toString();
    }

    public int hashCode() {
        return new HashCodeBuilder()
        .append(getLogId())
        .toHashCode();
    }

    public boolean equals(Object obj) {
        if(obj instanceof NouserCoinTeller == false) return false;
        if(this == obj) return true;
        NouserCoinTeller other = (NouserCoinTeller)obj;
        return new EqualsBuilder()
        .append(getLogId(),other.getLogId())
        .isEquals();
    }
}

