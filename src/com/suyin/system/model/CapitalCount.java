/*
 * 文件名：CapitalCount.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：Administrator
 * 修改时间：2015年12月22日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.suyin.system.model;

import java.io.Serializable;

public class CapitalCount implements Serializable{
	private Integer userCount;//当日平台总人数
	private Integer goldCount;//平台金币总数
	private Integer goldSignCount;//当日签到获得总金币数
	private Integer registerGoldCount;//当日注册获得总金币数
	private Integer dataGoldCount;//完善资料获得总金币数
	private Integer qisongTaskGoldCount;//轻松赚任务总金币数
	private Integer qmTaskGoldCount;//全名赚
	private Integer walletGoldCount;//金币兑换钱包总金币数
	private Integer walletUserCount;//金币兑换钱包总人数
	private Double waletMoneyCount;//新申请兑换金额
	private Double applyMoneyCount;//新申请提现金额（日）
	private Integer applyUserCount;//新申请提现人数（日）
	private Double moneyCount;//钱包提现支付宝总数
	
	Page page;

	public Integer getUserCount() {
		return userCount;
	}

	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}

	public Integer getGoldCount() {
		return goldCount;
	}

	public void setGoldCount(Integer goldCount) {
		this.goldCount = goldCount;
	}

	public Integer getGoldSignCount() {
		return goldSignCount;
	}

	public void setGoldSignCount(Integer goldSignCount) {
		this.goldSignCount = goldSignCount;
	}

	public Integer getRegisterGoldCount() {
		return registerGoldCount;
	}

	public void setRegisterGoldCount(Integer registerGoldCount) {
		this.registerGoldCount = registerGoldCount;
	}

	public Integer getDataGoldCount() {
		return dataGoldCount;
	}

	public void setDataGoldCount(Integer dataGoldCount) {
		this.dataGoldCount = dataGoldCount;
	}

	public Integer getQisongTaskGoldCount() {
		return qisongTaskGoldCount;
	}

	public void setQisongTaskGoldCount(Integer qisongTaskGoldCount) {
		this.qisongTaskGoldCount = qisongTaskGoldCount;
	}

	public Integer getQmTaskGoldCount() {
		return qmTaskGoldCount;
	}

	public void setQmTaskGoldCount(Integer qmTaskGoldCount) {
		this.qmTaskGoldCount = qmTaskGoldCount;
	}

	public Integer getWalletGoldCount() {
		return walletGoldCount;
	}

	public void setWalletGoldCount(Integer walletGoldCount) {
		this.walletGoldCount = walletGoldCount;
	}

	public Integer getWalletUserCount() {
		return walletUserCount;
	}

	public void setWalletUserCount(Integer walletUserCount) {
		this.walletUserCount = walletUserCount;
	}

	public Double getWaletMoneyCount() {
		return waletMoneyCount;
	}

	public void setWaletMoneyCount(Double waletMoneyCount) {
		this.waletMoneyCount = waletMoneyCount;
	}

	public Double getApplyMoneyCount() {
		return applyMoneyCount;
	}

	public void setApplyMoneyCount(Double applyMoneyCount) {
		this.applyMoneyCount = applyMoneyCount;
	}

	public Integer getApplyUserCount() {
		return applyUserCount;
	}

	public void setApplyUserCount(Integer applyUserCount) {
		this.applyUserCount = applyUserCount;
	}

	public Double getMoneyCount() {
		return moneyCount;
	}

	public void setMoneyCount(Double moneyCount) {
		this.moneyCount = moneyCount;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	
	
	
}
