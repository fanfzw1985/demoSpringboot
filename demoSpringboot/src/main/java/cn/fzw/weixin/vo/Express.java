package cn.fzw.weixin.vo;

public class Express {
	private String date;
	private String statusdescription;
	private String details;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStatusdescription() {
		return statusdescription;
	}
	public void setStatusdescription(String statusdescription) {
		this.statusdescription = statusdescription;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getCheckpoint_status() {
		return checkpoint_status;
	}
	public void setCheckpoint_status(String checkpoint_status) {
		this.checkpoint_status = checkpoint_status;
	}
	private String checkpoint_status;
}
