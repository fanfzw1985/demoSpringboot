/**
 * 
 */
package cn.fzw.vo;

import java.io.Serializable;
import java.util.List;

/*** <p>Title:SysRole </p>
* <p>Description: </p>
* <p>Company: </p> 
* @author 樊志文
* @date 2018年5月23日*/
public class SysRole implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Integer id; // 编号
    private String role; // 角色标识程序中判断使用,如"admin",这个是唯一的:
    private String description; // 角色描述,UI界面显示使用
    private Boolean available = Boolean.FALSE; // 是否可用,如果不可用将不会添加给用户

    //角色 -- 权限关系：多对多关系;
  private List<SysPermission> permissions;

    public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getRole() {
	return role;
}

public void setRole(String role) {
	this.role = role;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public Boolean getAvailable() {
	return available;
}

public void setAvailable(Boolean available) {
	this.available = available;
}

public List<SysPermission> getPermissions() {
	return permissions;
}

public void setPermissions(List<SysPermission> permissions) {
	this.permissions = permissions;
}

public List<Users> getUserInfos() {
	return userInfos;
}

public void setUserInfos(List<Users> userInfos) {
	this.userInfos = userInfos;
}

// 用户 - 角色关系定义;
   private List<Users> userInfos;// 一个角色对应多个用户
}
