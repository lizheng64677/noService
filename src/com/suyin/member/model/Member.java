package com.suyin.member.model;

import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang.time.DateFormatUtils;

import com.suyin.system.model.Attachment;
import com.suyin.system.model.Page;

public class Member  implements java.io.Serializable{

    private static final long serialVersionUID = 5454155825314635342L;
    public static final String TABLE_ALIAS = "Member";
    public static final String ALIAS_MEMBER_ID = "主键ID";
    public static final String ALIAS_BUSNAME = "商家名";
    public static final String ALIAS_TITLE = "标题(商家卖点)";
    public static final String ALIAS_USERNAME = "用户名";
    public static final String ALIAS_PARENT_ID = "总店parent_id=0";
    public static final String ALIAS_PWD = "密码";
    public static final String ALIAS_ADDRESS = "地址";
    public static final String ALIAS_ADDRESS_URL = "商家坐标url";
    public static final String ALIAS_WIDE_PIC = "宽图";
    public static final String ALIAS_PIC_URL = "长图";
    public static final String ALIAS_BG_PIC_URL = "商家背景模糊图片";
    public static final String ALIAS_ATTENTION_URL = "attentionUrl";
    public static final String ALIAS_TELEPHONE = "telephone";
    public static final String ALIAS_EMAIL = "邮箱";
    public static final String ALIAS_QQ = "qq号码";
    public static final String ALIAS_CITY_ID = "城市";
    public static final String ALIAS_STATION_ID = "stationId";
    public static final String ALIAS_REGION_ID = "圈商";
    public static final String ALIAS_CG_ID = "品类";
    public static final String ALIAS_FCODE = "F码";
    public static final String ALIAS_PRAISE_NUM = "赞数";
    public static final String ALIAS_COLLECT_NUM = "收藏数";
    public static final String ALIAS_DISCUSS_NUM = "评论数";
    public static final String ALIAS_COM_SCORE = "总评分";
    public static final String ALIAS_ENVI_SCORE = "enviScore";
    public static final String ALIAS_SERVE_SCORE = "服务评分";
    public static final String ALIAS_QCD_SCORE = "qcdScore";
    public static final String ALIAS_CODE = "消券效验码";
    public static final String ALIAS_IS_SELF = "0:否 （我们运营）1：是（商家自己维护）";
    public static final String ALIAS_IS_CHAIN = "是否连锁：0：连锁 1：单店";
    public static final String ALIAS_SEQ_NO = "推荐序号";
    public static final String ALIAS_CREATE_TIME = "创建时间";
    public static final String ALIAS_POINT = "商家积分";
    public static final String ALIAS_LNG = "经度";
    public static final String ALIAS_LAT = "纬度";
    public static final String ALIAS_UPDATE_TIME = "更新时间";
    public static final String ALIAS_LOGO_PIC_URL = "商家logo图片";
    public static final String ALIAS_DESCRIPTION = "商家简介";
    public static final String ALIAS_IS_ATTENTION = "是否一键关注  0：否 1：是";
    public static final String ALIAS_ANDROID_URL = "is_attention=1,android关注url";
    public static final String ALIAS_IPHONE_URL = "is_attention=1,iphone关注url";
    private Page page;//分页插件


    //columns start

    /**
     * 主键ID       db_column: member_id 
     */ 	
    private java.lang.Integer memberId;
    /**
     * 商家名       db_column: busname 
     */ 	
    private java.lang.String busname;
    /**
     * 标题(商家卖点)       db_column: title 
     */ 	
    private java.lang.String title;
    /**
     * 用户名       db_column: username 
     */ 	
    private java.lang.String username;
    /**
     * 总店parent_id=0       db_column: parent_id 
     */ 	
    private java.lang.Integer parentId;
    /**
     * 密码       db_column: pwd 
     */ 	
    private java.lang.String pwd;
    /**
     * 地址       db_column: address 
     */ 	
    private java.lang.String address;
    /**
     * 商家坐标url       db_column: address_url 
     */ 	
    private java.lang.String addressUrl;
    /**
     * 宽图       db_column: wide_pic 
     */ 	
    private java.lang.String widePic;
    /**
     * 长图       db_column: pic_url 
     */ 	
    private java.lang.String picUrl;
    /**
     * 商家背景模糊图片       db_column: bg_pic_url 
     */ 	
    private java.lang.String bgPicUrl;
    /**
     * attentionUrl       db_column: attention_url 
     */ 	
    private java.lang.String attentionUrl;
    /**
     * telephone       db_column: telephone 
     */ 	
    private java.lang.String telephone;
    /**
     * 邮箱       db_column: email 
     */ 	
    private java.lang.String email;
    /**
     * qq号码       db_column: qq 
     */ 	
    private java.lang.String qq;
    /**
     * 城市       db_column: city_id 
     */ 	
    private java.lang.Integer cityId;
    /**
     * 省id      db_column: provin_id 
     */     
    private java.lang.Integer provinId;
    /**
     * stationId       db_column: station_id 
     */ 	
    private java.lang.Integer stationId;
    /**
     * 圈商       db_column: region_id 
     */ 	
    private java.lang.Integer regionId;
    /**
     * 品类       db_column: cg_id 
     */ 	
    private java.lang.Integer cgId;
    /**
     * F码       db_column: f_code 
     */ 	
    private java.lang.String fcode;
    /**
     * 赞数       db_column: praise_num 
     */ 	
    private java.lang.Integer praiseNum;
    /**
     * 收藏数       db_column: collect_num 
     */ 	
    private java.lang.Integer collectNum;
    /**
     * 评论数       db_column: discuss_num 
     */ 	
    private java.lang.Integer discussNum;
    /**
     * 总评分       db_column: com_score 
     */ 	
    private java.lang.Double comScore;
    /**
     * enviScore       db_column: envi_score 
     */ 	
    private java.lang.Double enviScore;
    /**
     * 服务评分       db_column: serve_score 
     */ 	
    private java.lang.Double serveScore;
    /**
     * qcdScore       db_column: qcd_score 
     */ 	
    private java.lang.Double qcdScore;
    /**
     * 消券效验码       db_column: code 
     */ 	
    private java.lang.String code;
    /**
     * 0:否 （我们运营）1：是（商家自己维护）       db_column: is_self 
     */ 	
    private java.lang.Integer isSelf;
    /**
     * 是否连锁：0：连锁 1：单店       db_column: is_chain 
     */ 	
    private java.lang.Integer isChain;
    /**
     * 推荐序号吧       db_column: seq_no 
     */ 	
    private java.lang.Integer seqNo;
    /**
     * 创建时间       db_column: create_time 
     */ 	
    private java.lang.String createTime;
    /**
     * 商家积分       db_column: point 
     */ 	
    private java.lang.Integer point;
    /**
     * 经度       db_column: lng 
     */ 	
    private java.lang.String lng;
    /**
     * 纬度       db_column: lat 
     */ 	
    private java.lang.String lat;
    /**
     * 更新时间       db_column: update_time 
     */ 	
    private java.lang.String updateTime;
    /**
     * 商家logo图片       db_column: logo_pic_url 
     */ 	
    private java.lang.String logoPicUrl;
    /**
     * 商家简介       db_column: description 
     */ 	
    private java.lang.String description;
    /**
     * 是否一键关注  0：否 1：是       db_column: is_attention 
     */ 	
    private java.lang.Integer isAttention;
    /**
     * is_attention=1,android关注url       db_column: android_url 
     */ 	
    private java.lang.String androidUrl;
    /**
     * is_attention=1,iphone关注url       db_column: iphone_url 
     */ 	
    private java.lang.String iphoneUrl;

    //columns end

    /**
     * 上传图片应用
     */
    private List<Attachment> attachments;
    public Member(){
    }

    public Member(
                  java.lang.Integer memberId
        ){
        this.memberId = memberId;
    }



    public void setMemberId(java.lang.Integer value) {
        this.memberId = value;
    }


    public java.lang.Integer getMemberId() {
        return this.memberId;
    }

    public java.lang.String getBusname() {
        return this.busname;
    }

    public void setBusname(java.lang.String value) {
        this.busname = value;
    }

    public java.lang.String getTitle() {
        return this.title;
    }

    public void setTitle(java.lang.String value) {
        this.title = value;
    }

    public java.lang.String getUsername() {
        return this.username;
    }

    public void setUsername(java.lang.String value) {
        this.username = value;
    }

    public java.lang.Integer getParentId() {
        return this.parentId;
    }

    public void setParentId(java.lang.Integer value) {
        this.parentId = value;
    }

    public java.lang.String getPwd() {
        return this.pwd;
    }

    public void setPwd(java.lang.String value) {
        this.pwd = value;
    }

    public java.lang.String getAddress() {
        return this.address;
    }

    public void setAddress(java.lang.String value) {
        this.address = value;
    }

    public java.lang.String getAddressUrl() {
        return this.addressUrl;
    }

    public void setAddressUrl(java.lang.String value) {
        this.addressUrl = value;
    }

    public java.lang.String getWidePic() {
        return this.widePic;
    }

    public void setWidePic(java.lang.String value) {
        this.widePic = value;
    }

    public java.lang.String getPicUrl() {
        return this.picUrl;
    }

    public void setPicUrl(java.lang.String value) {
        this.picUrl = value;
    }

    public java.lang.String getBgPicUrl() {
        return this.bgPicUrl;
    }

    public void setBgPicUrl(java.lang.String value) {
        this.bgPicUrl = value;
    }

    public java.lang.String getAttentionUrl() {
        return this.attentionUrl;
    }

    public void setAttentionUrl(java.lang.String value) {
        this.attentionUrl = value;
    }

    public java.lang.String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(java.lang.String value) {
        this.telephone = value;
    }

    public java.lang.String getEmail() {
        return this.email;
    }

    public void setEmail(java.lang.String value) {
        this.email = value;
    }

    public java.lang.String getQq() {
        return this.qq;
    }

    public void setQq(java.lang.String value) {
        this.qq = value;
    }

    public java.lang.Integer getCityId() {
        return this.cityId;
    }

    public void setCityId(java.lang.Integer value) {
        this.cityId = value;
    }

    public java.lang.Integer getStationId() {
        return this.stationId;
    }

    public void setStationId(java.lang.Integer value) {
        this.stationId = value;
    }

    public java.lang.Integer getRegionId() {
        return this.regionId;
    }

    public void setRegionId(java.lang.Integer value) {
        this.regionId = value;
    }

    public java.lang.Integer getCgId() {
        return this.cgId;
    }

    public void setCgId(java.lang.Integer value) {
        this.cgId = value;
    }

    public java.lang.String getFcode() {
        return this.fcode;
    }

    public void setFcode(java.lang.String value) {
        this.fcode = value;
    }

    public java.lang.Integer getPraiseNum() {
        return this.praiseNum;
    }

    public void setPraiseNum(java.lang.Integer value) {
        this.praiseNum = value;
    }

    public java.lang.Integer getCollectNum() {
        return this.collectNum;
    }

    public void setCollectNum(java.lang.Integer value) {
        this.collectNum = value;
    }

    public java.lang.Integer getDiscussNum() {
        return this.discussNum;
    }

    public void setDiscussNum(java.lang.Integer value) {
        this.discussNum = value;
    }

    public java.lang.Double getComScore() {
        return this.comScore;
    }

    public void setComScore(java.lang.Double value) {
        this.comScore = value;
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

    public java.lang.String getCode() {
        return this.code;
    }

    public void setCode(java.lang.String value) {
        this.code = value;
    }

    public java.lang.Integer getIsSelf() {
        return this.isSelf;
    }

    public void setIsSelf(java.lang.Integer value) {
        this.isSelf = value;
    }

    public java.lang.Integer getIsChain() {
        return this.isChain;
    }

    public void setIsChain(java.lang.Integer value) {
        this.isChain = value;
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

    public java.lang.Integer getPoint() {
        return this.point;
    }

    public void setPoint(java.lang.Integer value) {
        this.point = value;
    }

    public java.lang.String getLng() {
        return this.lng;
    }

    public void setLng(java.lang.String value) {
        this.lng = value;
    }

    public java.lang.String getLat() {
        return this.lat;
    }

    public void setLat(java.lang.String value) {
        this.lat = value;
    }

    public java.lang.String getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(java.lang.String value) {
        this.updateTime = value;
    }

    public java.lang.String getLogoPicUrl() {
        return this.logoPicUrl;
    }

    public void setLogoPicUrl(java.lang.String value) {
        this.logoPicUrl = value;
    }

    public java.lang.String getDescription() {
        return this.description;
    }

    public void setDescription(java.lang.String value) {
        this.description = value;
    }

    public java.lang.Integer getIsAttention() {
        return this.isAttention;
    }

    public void setIsAttention(java.lang.Integer value) {
        this.isAttention = value;
    }

    public java.lang.String getAndroidUrl() {
        return this.androidUrl;
    }

    public void setAndroidUrl(java.lang.String value) {
        this.androidUrl = value;
    }

    public java.lang.String getIphoneUrl() {
        return this.iphoneUrl;
    }

    public void setIphoneUrl(java.lang.String value) {
        this.iphoneUrl = value;
    }

    public java.lang.Integer getProvinId()
    {
        return provinId;
    }

    public void setProvinId(java.lang.Integer provinId)
    {
        this.provinId = provinId;
    }

    public Page getPage() {
        return page;
    }
    public void setPage(Page page) {
        this.page = page;
    }

    public List<Attachment> getAttachments()
    {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments)
    {
        this.attachments = attachments;
    }

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
        .append("MemberId",getMemberId())
        .append("Busname",getBusname())
        .append("Title",getTitle())
        .append("Username",getUsername())
        .append("ParentId",getParentId())
        .append("Pwd",getPwd())
        .append("Address",getAddress())
        .append("AddressUrl",getAddressUrl())
        .append("WidePic",getWidePic())
        .append("PicUrl",getPicUrl())
        .append("BgPicUrl",getBgPicUrl())
        .append("AttentionUrl",getAttentionUrl())
        .append("Telephone",getTelephone())
        .append("Email",getEmail())
        .append("Qq",getQq())
        .append("CityId",getCityId())
        .append("StationId",getStationId())
        .append("RegionId",getRegionId())
        .append("CgId",getCgId())
        .append("Fcode",getFcode())
        .append("PraiseNum",getPraiseNum())
        .append("CollectNum",getCollectNum())
        .append("DiscussNum",getDiscussNum())
        .append("ComScore",getComScore())
        .append("EnviScore",getEnviScore())
        .append("ServeScore",getServeScore())
        .append("QcdScore",getQcdScore())
        .append("Code",getCode())
        .append("IsSelf",getIsSelf())
        .append("IsChain",getIsChain())
        .append("SeqNo",getSeqNo())
        .append("CreateTime",getCreateTime())
        .append("Point",getPoint())
        .append("Lng",getLng())
        .append("Lat",getLat())
        .append("UpdateTime",getUpdateTime())
        .append("LogoPicUrl",getLogoPicUrl())
        .append("Description",getDescription())
        .append("IsAttention",getIsAttention())
        .append("AndroidUrl",getAndroidUrl())
        .append("IphoneUrl",getIphoneUrl())
        .toString();
    }

    public int hashCode() {
        return new HashCodeBuilder()
        .append(getMemberId())
        .toHashCode();
    }

    public boolean equals(Object obj) {
        if(obj instanceof Member == false) return false;
        if(this == obj) return true;
        Member other = (Member)obj;
        return new EqualsBuilder()
        .append(getMemberId(),other.getMemberId())
        .isEquals();
    }
}

