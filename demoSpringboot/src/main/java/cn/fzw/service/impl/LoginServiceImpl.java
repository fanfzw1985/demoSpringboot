package cn.fzw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fzw.mapper.LoginMapper;
import cn.fzw.service.LoginService;
import cn.fzw.vo.SysPermission;
import cn.fzw.vo.SysRole;
import cn.fzw.vo.Users;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginMapper loginmapper;
	public Users selectByusernameandpassword(String username, String password)
			throws Exception {
		
		
		return loginmapper.selectByUsernameAndPassword(username,password);
	}
	public int selectAllInRecordes(int flag) throws Exception {
		// TODO Auto-generated method stub
		
		return loginmapper.selectAllInRecordes(flag);
	}
	/* (non-Javadoc)
	 * @see cn.fzw.service.LoginService#selectByUsername(java.lang.String)
	 */
	@Override
	public Users selectByUsername(String username) throws Exception {
		// TODO Auto-generated method stub
		return loginmapper.selectByUsername(username);
	}
	/* (non-Javadoc)
	 * @see cn.fzw.service.LoginService#findByUsername(java.lang.String)
	 */
	@Override
	public Users findByUsername(String username) throws Exception {
		return loginmapper.findByUsername(username);
	}
	/* (non-Javadoc)
	 * @see cn.fzw.service.LoginService#selectRoleByUserId(int)
	 */
	@Override
	public List<SysRole> selectRoleByUserId(int id) {
		return loginmapper.selectRoleByUserId(id);
	}
	/* (non-Javadoc)
	 * @see cn.fzw.service.LoginService#selectPermissionListByRoleId(java.lang.Integer)
	 */
	@Override
	public List<SysPermission> selectPermissionListByRoleId(Integer id) {
		return loginmapper.selectPermissionListByRoleId(id);
	}

}
