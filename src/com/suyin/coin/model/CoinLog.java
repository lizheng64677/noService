package com.suyin.coin.model;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.suyin.system.model.Page;
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class CoinLog {

    private int logId;
    private int userId;
    private String content;
    private int goldCoin;
    private String direction;
    private String createTime;
    private int status;
    private int coinTellerId;
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
    public final CoinLog setLogId(int logId) {
        this.logId = logId;
        return this;
    }
    public final int getUserId() {
        return userId;
    }
    public final CoinLog setUserId(int userId) {
        this.userId = userId;
        return this;
    }
    public final String getContent() {
        return content;
    }
    public final CoinLog setContent(String content) {
        this.content = content;
        return this;
    }
    public final int getGoldCoin() {
        return goldCoin;
    }
    public final CoinLog setGoldCoin(int goldCoin) {
        this.goldCoin = goldCoin;
        return this;
    }
    public final String getDirection() {
        return direction;
    }
    public final CoinLog setDirection(String direction) {
        this.direction = direction;
        return this;
    }
    public final String getCreateTime() {
        return createTime;
    }
    public final void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public int getStatus()
    {
        return status;
    }
    public void setStatus(int status)
    {
        this.status = status;
    }
    public int getCoinTellerId()
    {
        return coinTellerId;
    }
    public void setCoinTellerId(int coinTellerId)
    {
        this.coinTellerId = coinTellerId;
    }



}
