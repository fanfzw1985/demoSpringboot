/**
 * 
 */
package cn.fzw.vo;

import java.io.Serializable;

/*** <p>Title:SysRolePermission </p>
* <p>Description: </p>
* <p>Company: </p> 
* @author 樊志文
* @date 2018年5月23日*/
public class SysRolePermission implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer permissionid; // 编号
	private Integer roleid; // 编号
	public Integer getPermissionid() {
		return permissionid;
	}
	public void setPermissionid(Integer permissionid) {
		this.permissionid = permissionid;
	}
	public Integer getRoleid() {
		return roleid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
}
