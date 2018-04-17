package com.suyin.weChat.model.response;

import java.util.List;
/**
 * 微信图文消息相应类
 * @author madara
 *
 */
public class NewsResponseMessage extends BaseResponseMessage {
	private static final long serialVersionUID = -8076591963899085448L;
	// 图文消息个数，限制为10条以内
	private int ArticleCount;
	// 多条图文消息信息，默认第一个item为大图
	private List<Article> Articles;

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<Article> getArticles() {
		return Articles;
	}

	public void setArticles(List<Article> articles) {
		Articles = articles;
	}

}
