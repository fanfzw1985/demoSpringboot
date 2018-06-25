package cn.fzw.weixin.vo;
/**
 * 消息内容实体
 * @author 小樊
 *
 */
public class WxMsgText extends WxBaseMsg {
	private String Content;
	private String MsgId;
	public WxMsgText() {
		
	}
	public WxMsgText(String toUserName,String fromUserName,long createTime,
			String msgType,String content,String msgId) {
		super();
		ToUserName=toUserName;
		FromUserName=fromUserName;
		CreateTime=createTime;
		MsgType=msgType;
		Content=content;
		MsgId=msgId;
		
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
	
}
