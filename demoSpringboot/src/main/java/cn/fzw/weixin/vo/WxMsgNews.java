package cn.fzw.weixin.vo;
/**
 * 消息内容实体
 * @author 小樊
 *
 */
public class WxMsgNews extends WxBaseMsg {
	private String ArticleCount;
	private String Articles;
	private String Title;
	private String Description;
	private String PicUrl;
	private String Url;
	private String MsgId;
	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
	public WxMsgNews() {
		
	}
	public WxMsgNews(String toUserName,String fromUserName,long createTime,
			String msgType,String articleCount, String articles, String title,
				String description, String picUrl, String url,String msgId) 
	{
		super();
		ToUserName=toUserName;
		FromUserName=fromUserName;
		CreateTime=createTime;
		MsgType=msgType;
		ArticleCount= articleCount; 
		Articles= articles; 
		Title= title;
		Description= description;
		PicUrl= picUrl; 
		Url= url; 	
		MsgId=msgId;
	}
	public String getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(String articleCount) {
		ArticleCount = articleCount;
	}
	public String getArticles() {
		return Articles;
	}
	public void setArticles(String articles) {
		Articles = articles;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}

}
