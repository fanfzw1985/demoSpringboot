package cn.fzw.vo;

import java.io.Serializable;
import java.util.List;

import cn.fzw.vo.SysRole;

public class Users implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private int id;
	private String username;
	private String password;
	private String name;
	private String email;
	private List<SysRole> roleList;// 一个用户具有多个角色
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<SysRole> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<SysRole> roleList) {
		this.roleList = roleList;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
}
