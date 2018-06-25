package cn.fzw.weixin.vo;

public class AccessToken {
	private String acces_token;
	private int expires_in;
	public String getAcces_token() {
		return acces_token;
	}
	public void setAcces_token(String acces_token) {
		this.acces_token = acces_token;
	}
	public int getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}
}
