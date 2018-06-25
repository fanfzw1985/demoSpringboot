package cn.fzw.service;


import java.util.List;

import cn.fzw.vo.SysPermission;
import cn.fzw.vo.SysRole;
import cn.fzw.vo.Users;

public interface LoginService {

	public Users selectByusernameandpassword(String username, String password)throws Exception;

	public int selectAllInRecordes(int flag)throws Exception;

	/**
	 * @param username
	 * @return
	 */
	public Users selectByUsername(String username)throws Exception;

	/**
	 * @param username
	 * @return
	 */
	public Users findByUsername(String username) throws Exception;

	/**
	 * @param id
	 * @return
	 */
	public List<SysRole> selectRoleByUserId(int id);

	/**
	 * @param id
	 * @return
	 */
	public List<SysPermission> selectPermissionListByRoleId(Integer id);

}
