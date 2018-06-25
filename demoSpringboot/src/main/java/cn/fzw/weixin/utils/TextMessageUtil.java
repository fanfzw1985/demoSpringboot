package cn.fzw.weixin.utils;

import java.util.Date;

import com.thoughtworks.xstream.XStream;

import cn.fzw.weixin.vo.WxMsgNews;
import cn.fzw.weixin.vo.WxMsgText;

/**
 * 将发送的消息封装成XML格式
 * @author 小樊
 *
 */
public class TextMessageUtil {
	public static  String messageToXml(WxMsgText message) {
		XStream xstream=new XStream();
		xstream.alias("xml", message.getClass());
		return xstream.toXML(message);		
	}
	public static  String messageToXml(WxMsgNews message) {
		XStream xstream=new XStream();
		xstream.alias("xml", message.getClass());
		return xstream.toXML(message);		
	}
	/**
	 * 封装发发送消息对象，需要调换发送者与接收者的关系
	 * @param FromUserName
	 * @param ToUserName
	 * @param contents 
	 * @return
	 */
	public static String initMessage(String FromUserName,String ToUserName,String MsgId, String Content) {
		WxMsgText text=new WxMsgText();
		text.setToUserName(FromUserName);
		text.setFromUserName(ToUserName);
		text.setContent(Content);
		text.setCreateTime(new Date().getTime());
		text.setMsgType("text");
		text.setMsgId(MsgId);
		return messageToXml(text);
		
	}
	public static String initMessage(String ToUserName,String FromUserName,String ArticleCount, 
			String Articles, String Title, String Description, String PicUrl, String Url,String MsgId) {
		WxMsgNews news=new WxMsgNews();
		news.setToUserName(FromUserName);
		news.setFromUserName(ToUserName);
		news.setCreateTime(new Date().getTime());
		news.setMsgType("news");
		news.setArticleCount(ArticleCount);
		news.setArticles(Articles);
		news.setTitle(Title);
		news.setDescription(Description);
		news.setPicUrl(PicUrl);
		news.setUrl(Url);
		news.setMsgId(MsgId);
		return messageToXml(news);
		
	}
}
