package cn.fzw.weixin.service;

import org.apache.http.client.ClientProtocolException;

public interface TextService {

	public String selectByContent(String FromUserName, String ToUserName, String MsgId,String Content) throws Exception;

	public String selectUserInfoFromWx(String FromUserName, String ToUserName, String MsgId)throws ClientProtocolException, Exception;

	public String initMessage(String fromUserName, String toUserName, String msgId, String content);

	public String expressSelect(String FromUserName, String ToUserName, String MsgId, String Content);

	public String getJoke(String fromUserName, String toUserName, String msgId, String content);
	
}


