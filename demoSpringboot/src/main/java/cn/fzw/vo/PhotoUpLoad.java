package cn.fzw.vo;

import java.util.Date;

public class PhotoUpLoad {
	private int id;
	private String username;
	private String photoRealName;
	public String getPhotoRealName() {
		return photoRealName;
	}
	public void setPhotoRealName(String photoRealName) {
		this.photoRealName = photoRealName;
	}
	private String photoName;
	private String photoPath;
	private Date time;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPhotoName() {
		return photoName;
	}
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}
	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
}
