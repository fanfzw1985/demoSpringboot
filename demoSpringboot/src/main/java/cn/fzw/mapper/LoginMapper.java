package cn.fzw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.fzw.vo.SysPermission;
import cn.fzw.vo.SysRole;
import cn.fzw.vo.Users;

public interface LoginMapper {

	Users selectByUsernameAndPassword(@Param("username")String username, @Param("password")String password)throws Exception;

	int selectAllInRecordes(@Param("flag")int flag)throws Exception;

	/**
	 * @param username
	 * @return
	 */
	Users selectByUsername(@Param("username")String username)throws Exception;

	/**
	 * @param username
	 * @return
	 */
	Users findByUsername(@Param("username")String username)throws Exception;

	/**
	 * @param id
	 * @return
	 */
	List<SysRole> selectRoleByUserId(@Param("id")int id);

	/**
	 * @param id
	 * @return
	 */
	List<SysPermission> selectPermissionListByRoleId(@Param("id")Integer id);

}
