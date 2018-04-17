package com.suyin.expzhuan.model;

import java.util.List;

import com.suyin.system.model.Attachment;
import com.suyin.system.model.Page;

public class ExpTask  implements java.io.Serializable{

    private static final long serialVersionUID = 5454155825314635342L;
    public static final String TABLE_ALIAS = "ExpZhuan";
    public static final String ALIAS_EXP_ID = "活动主键id";
    public static final String ALIAS_EXP_TYPE = "0齐心赚，1全民赚";
    public static final String ALIAS_SHOW_TYPE = "0,app下载，1问卷调查(全民赚)";
    public static final String ALIAS_MEMBER_NAME = "商家名称";
    public static final String ALIAS_TITLE = "活动标题";
    public static final String ALIAS_IS_BEGIN = "0未开始,1开始";
    public static final String ALIAS_EXP_COUNT_GOLD = "该活动总资产";
    public static final String ALIAS_EXP_REMAIN_GOLD = "该活动还剩多少金币资产";
    public static final String ALIAS_USER_TOTAL = "用户参与次数";
    public static final String ALIAS_USER_BROWSE_GOLD = "浏览一次得到的金币数";
    public static final String ALIAS_USER_MAX_GOLD = "个人应得金币上限";
    public static final String ALIAS_EXP_USER_GOLD = "活动固定金币收益";
    public static final String ALIAS_EXP_GOLD_MAX = "活动随机金币上限";
    public static final String ALIAS_EXP_GOLD_MIN = "活动浏览金币随机下限";
    public static final String ALIAS_MEMBER_INFO = "memberInfo";
    public static final String ALIAS_EXP_INFO = "活动简介";
    public static final String ALIAS_PROVIN_ID = "省份";
    public static final String ALIAS_CITY_ID = "市区";
    public static final String ALIAS_BEGIN_TIME = "活动开始";
    public static final String ALIAS_END_TIME = "活动结束时间";
    public static final String ALIAS_CREATE_TIME = "活动创建时间";
    public static final String ALIAS_UPDATE_TIME = "更新时间";
    public static final String ALIAS_USER_SHAR_GOLD = "用户分享所得金币";
    public static final String ALIAS_EXP_APP_URL = "活动应用ur地址";
    public static final String ALIAS_SHARE_TITLE = "分享标题";
    public static final String ALIAS_SHARE_URL = "齐心赚分享地址";

    private Page page;//分页插件


    //columns start

    /**
     * 活动主键id       db_column: exp_id 
     */ 	
    private java.lang.Integer expId;
    /**
     * 0齐心赚，1全民赚       db_column: exp_type 
     */ 	
    private java.lang.Integer expType;
    /**
     * 商家名称       db_column: member_name 
     */     
    private java.lang.String memberName;
    /**
     * memberInfo       db_column: member_info 
     */     
    private java.lang.String memberInfo;
    /**
     * 活动标题       db_column: title 
     */ 	
    private java.lang.String title;
    /**
     * 0未开始,1开始       db_column: is_begin 
     */ 	
    private java.lang.Integer isBegin;
    /**
     * 该活动总资产       db_column: exp_count_gold 
     */ 	
    private java.lang.Integer expCountGold;
    /**
     * 该活动还剩多少金币资产       db_column: exp_remain_gold 
     */     
    private java.lang.Integer expRemainGold;
    /**
     * 浏览一次得到的金币数       db_column: user_browse_gold 
     */ 	
    private java.lang.Integer userBrowseGold;
    /**
     * 个人应得金币上限       db_column: user_max_gold 
     */ 	
    private java.lang.Integer userMaxGold;
    /**
     * 用户参与次数       db_column: user_total 
     */     
    private java.lang.Integer userTotal;
    /**
     * 活动随机金币上限       db_column: exp_gold_max 
     */ 	
    private java.lang.Integer expGoldMax;
    /**
     * 活动固定金币收益       db_column: exp_user_gold 
     */ 	
    private java.lang.Integer expUserGold;
    /**
     * 活动浏览金币随机下限       db_column: exp_gold_min 
     */ 	
    private java.lang.Integer expGoldMin;
    /**
     * 活动简介       db_column: exp_info 
     */ 	
    private java.lang.String expInfo;
    /**
     * 省份       db_column: provin_id 
     */     
    private java.lang.Integer provinId;
    /**
     * 市区       db_column: city_id 
     */     
    private java.lang.Integer cityId;
    /**
     * 活动开始       db_column: begin_time 
     */ 	
    private java.lang.String beginTime;
    /**
     * 活动结束时间       db_column: end_time 
     */ 	
    private java.lang.String endTime;
    /**
     * 活动创建时间       db_column: create_time 
     */ 	
    private java.util.Date createTime;
    /**
     * 更新时间       db_column: update_time 
     */ 	
    private java.util.Date updateTime;
    /**
     * 用户分享所得金币       db_column: user_share_gold 
     */     
    private java.lang.Integer userShareGold;
    /**
     * 活动应用ur地址       db_column: exp_app_url 
     */     
    private java.lang.String expAppUrl;
    /**
     * 0,app下载，1问卷调查(全民赚)       db_column: show_type 
     */     
    private java.lang.Integer showType;

    /**
     * 分享标题       db_column: share_title 
     */     
    private java.lang.String shareTitle;
    /**
     * 齐心赚分享地址       db_column: share_url 
     */     
    private java.lang.String shareUrl;

    /**
     * 分享图片       db_column: share_img_url 
     */     
    private java.lang.String shareImgUrl;
    /**
     * 活动图片       db_column: exp_img_url 
     */     
    private java.lang.String expImgUrl;
    
    private String tips;
    
    /**
     * 是否有下载地址
     */
    private Integer isDown;
    
    /**
     * 参与该活动人数
     */
    private String userJoin;
    
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
    
    
    
    
    public Integer getIsDown() {
		return isDown;
	}

	public void setIsDown(Integer isDown) {
		this.isDown = isDown;
	}

	public String getUserJoin() {
		return userJoin;
	}

	public void setUserJoin(String userJoin) {
		this.userJoin = userJoin;
	}

	public final String getTips() {
		return tips;
	}

	public final void setTips(String tips) {
		this.tips = tips;
	}

	//columns end
    /**
     * 上传图片应用
     */
    private List<Attachment> attachments;

    //app 图片示例 及图文描述 
    private String appInfo;
    private String dowImgUrl;
    private String upImgUrl;
    private String seqNum;

    public ExpTask(){
    }

    public ExpTask(java.lang.Integer expId){
        this.expId = expId;
    }



    public void setExpId(java.lang.Integer value) {
        this.expId = value;
    }


    public java.lang.Integer getExpId() {
        return this.expId;
    }

    public java.lang.Integer getExpType() {
        return this.expType;
    }

    public void setExpType(java.lang.Integer value) {
        this.expType = value;
    }



    public java.lang.String getTitle() {
        return this.title;
    }

    public void setTitle(java.lang.String value) {
        this.title = value;
    }

    public java.lang.Integer getIsBegin() {
        return this.isBegin;
    }

    public void setIsBegin(java.lang.Integer value) {
        this.isBegin = value;
    }

    public java.lang.Integer getExpCountGold() {
        return this.expCountGold;
    }

    public void setExpCountGold(java.lang.Integer value) {
        this.expCountGold = value;
    }

    public java.lang.Integer getExpRemainGold()
    {
        return expRemainGold;
    }

    public void setExpRemainGold(java.lang.Integer expRemainGold)
    {
        this.expRemainGold = expRemainGold;
    }

    public java.lang.Integer getUserBrowseGold() {
        return this.userBrowseGold;
    }

    public void setUserBrowseGold(java.lang.Integer value) {
        this.userBrowseGold = value;
    }

    public java.lang.Integer getUserMaxGold() {
        return this.userMaxGold;
    }

    public void setUserMaxGold(java.lang.Integer value) {
        this.userMaxGold = value;
    }

    public java.lang.Integer getExpGoldMax() {
        return this.expGoldMax;
    }

    public void setExpGoldMax(java.lang.Integer value) {
        this.expGoldMax = value;
    }

    public java.lang.Integer getExpUserGold() {
        return this.expUserGold;
    }

    public void setExpUserGold(java.lang.Integer value) {
        this.expUserGold = value;
    }

    public java.lang.Integer getExpGoldMin() {
        return this.expGoldMin;
    }

    public void setExpGoldMin(java.lang.Integer value) {
        this.expGoldMin = value;
    }

    public java.lang.String getExpInfo() {
        return this.expInfo;
    }

    public void setExpInfo(java.lang.String value) {
        this.expInfo = value;
    }

    public java.lang.Integer getProvinId()
    {
        return provinId;
    }

    public void setProvinId(java.lang.Integer provinId)
    {
        this.provinId = provinId;
    }

    public java.lang.Integer getCityId()
    {
        return cityId;
    }

    public void setCityId(java.lang.Integer cityId)
    {
        this.cityId = cityId;
    }

    public java.lang.String getBeginTime() {
        return this.beginTime;
    }

    public void setBeginTime(java.lang.String value) {
        this.beginTime = value;
    }

    public  java.lang.String getEndTime() {
        return this.endTime;
    }

    public void setEndTime( java.lang.String value) {
        this.endTime = value;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(java.util.Date value) {
        this.createTime = value;
    }

    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(java.util.Date value) {
        this.updateTime = value;
    }

    public String getSeqNum()
    {
        return seqNum;
    }

    public void setSeqNum(String seqNum)
    {
        this.seqNum = seqNum;
    }

    public Page getPage() {
        return page;
    }
    public void setPage(Page page) {
        this.page = page;
    }

    public java.lang.Integer getUserShareGold()
    {
        return userShareGold;
    }

    public void setUserShareGold(java.lang.Integer userShareGold)
    {
        this.userShareGold = userShareGold;
    }

    public java.lang.String getExpAppUrl()
    {
        return expAppUrl;
    }

    public void setExpAppUrl(java.lang.String expAppUrl)
    {
        this.expAppUrl = expAppUrl;
    }

    public java.lang.Integer getUserTotal()
    {
        return userTotal;
    }

    public void setUserTotal(java.lang.Integer userTotal)
    {
        this.userTotal = userTotal;
    }

    public java.lang.Integer getShowType()
    {
        return showType;
    }

    public void setShowType(java.lang.Integer showType)
    {
        this.showType = showType;
    }

    public java.lang.String getMemberName()
    {
        return memberName;
    }

    public void setMemberName(java.lang.String memberName)
    {
        this.memberName = memberName;
    }

    public java.lang.String getMemberInfo()
    {
        return memberInfo;
    }

    public void setMemberInfo(java.lang.String memberInfo)
    {
        this.memberInfo = memberInfo;
    }

    public java.lang.String getShareTitle()
    {
        return shareTitle;
    }

    public void setShareTitle(java.lang.String shareTitle)
    {
        this.shareTitle = shareTitle;
    }

    public java.lang.String getShareUrl()
    {
        return shareUrl;
    }

    public void setShareUrl(java.lang.String shareUrl)
    {
        this.shareUrl = shareUrl;
    }

    public List<Attachment> getAttachments()
    {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments)
    {
        this.attachments = attachments;
    }

    public java.lang.String getShareImgUrl()
    {
        return shareImgUrl;
    }

    public void setShareImgUrl(java.lang.String shareImgUrl)
    {
        this.shareImgUrl = shareImgUrl;
    }

    public java.lang.String getExpImgUrl()
    {
        return expImgUrl;
    }

    public void setExpImgUrl(java.lang.String expImgUrl)
    {
        this.expImgUrl = expImgUrl;
    }

    public String getAppInfo()
    {
        return appInfo;
    }

    public void setAppInfo(String appInfo)
    {
        this.appInfo = appInfo;
    }

    public String getDowImgUrl()
    {
        return dowImgUrl;
    }

    public void setDowImgUrl(String dowImgUrl)
    {
        this.dowImgUrl = dowImgUrl;
    }

    public String getUpImgUrl()
    {
        return upImgUrl;
    }

    public void setUpImgUrl(String upImgUrl)
    {
        this.upImgUrl = upImgUrl;
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


}

