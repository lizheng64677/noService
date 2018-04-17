package com.suyin.cash.model;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.suyin.system.model.Page;
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class CoinCashLog {

    private int logId;
    private int userId;
    private String content;
    private double money;
    private String direction;
    private String createTime;
    private String updateTime;
    private int tellerId;
    private int status;
    private Page page;
    public final Page getPage() {
        return page;
    }
    public final void setPage(Page page) {
        this.page = page;
    }
    public final int getLogId() {
        return logId;
    }
    public final CoinCashLog setLogId(int logId) {
        this.logId = logId;
        return this;
    }
    public final int getUserId() {
        return userId;
    }
    public final CoinCashLog setUserId(int userId) {
        this.userId = userId;
        return this;
    }
    public final String getContent() {
        return content;
    }
    public final CoinCashLog setContent(String content) {
        this.content = content;
        return this;
    }
    public final double getMoney() {
        return money;
    }
    public final CoinCashLog setMoney(double money) {
        this.money = money;
        return this;
    }
    public final String getDirection() {
        return direction;
    }
    public final CoinCashLog setDirection(String direction) {
        this.direction = direction;
        return this;
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
    public int getStatus()
    {
        return status;
    }
    public void setStatus(int status)
    {
        this.status = status;
    }
    public int getTellerId()
    {
        return tellerId;
    }
    public void setTellerId(int tellerId)
    {
        this.tellerId = tellerId;
    }



}
