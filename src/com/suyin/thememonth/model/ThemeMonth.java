package com.suyin.thememonth.model;

import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.suyin.system.model.Attachment;
import com.suyin.system.model.Page;

public class ThemeMonth  implements java.io.Serializable{

    private static final long serialVersionUID = 5454155825314635342L;
    public static final String TABLE_ALIAS = "ThemeMonth";
    public static final String ALIAS_THEME_ID = "themeId";
    public static final String ALIAS_THEME_TITLE = "主题月标题";
    public static final String ALIAS_THEME_LOGO = "主题logo";
    public static final String ALIAS_THEME_PIC = "主题图";
    public static final String ALIAS_BOTTOM_PIC = "bottomPic";
    public static final String ALIAS_CREATE_TIME = "创建时间";
    public static final String ALIAS_UPDATE_TIME = "更新时间";
    public static final String ALIAS_COLOR = "color";
    public static final String ALIAS_MONTH = "选择月份";

    private Page page;//分页插件


    //columns start

    /**
     * themeId       db_column: theme_id 
     */ 	
    private java.lang.Integer themeId;
    /**
     * 主题月标题       db_column: theme_title 
     */ 	
    private java.lang.String themeTitle;
    /**
     * 主题logo       db_column: theme_logo 
     */ 	
    private java.lang.String themeLogo;
    /**
     * 主题图       db_column: theme_pic 
     */ 	
    private java.lang.String themePic;
    /**
     * bottomPic       db_column: bottom_pic 
     */ 	
    private java.lang.String bottomPic;
    /**
     * 创建时间       db_column: create_time 
     */ 	
    private java.lang.String createTime;
    /**
     * 更新时间       db_column: update_time 
     */ 	
    private java.lang.String updateTime;
    /**
     * color       db_column: color 
     */ 	
    private java.lang.String color;
    /**
     * 选择月份       db_column: month 
     */ 	
    private java.lang.String months;

    //columns end

    /**
     * 上传图片应用
     */
    private List<Attachment> attachments;
    public ThemeMonth(){
    }

    public ThemeMonth(
                      java.lang.Integer themeId
        ){
        this.themeId = themeId;
    }



    public void setThemeId(java.lang.Integer value) {
        this.themeId = value;
    }


    public java.lang.Integer getThemeId() {
        return this.themeId;
    }

    public java.lang.String getThemeTitle() {
        return this.themeTitle;
    }

    public void setThemeTitle(java.lang.String value) {
        this.themeTitle = value;
    }

    public java.lang.String getThemeLogo() {
        return this.themeLogo;
    }

    public void setThemeLogo(java.lang.String value) {
        this.themeLogo = value;
    }

    public java.lang.String getThemePic() {
        return this.themePic;
    }

    public void setThemePic(java.lang.String value) {
        this.themePic = value;
    }

    public java.lang.String getBottomPic() {
        return this.bottomPic;
    }

    public void setBottomPic(java.lang.String value) {
        this.bottomPic = value;
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

    public java.lang.String getColor() {
        return this.color;
    }

    public void setColor(java.lang.String value) {
        this.color = value;
    }


    public java.lang.String getMonths()
    {
        return months;
    }

    public void setMonths(java.lang.String months)
    {
        this.months = months;
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

}

