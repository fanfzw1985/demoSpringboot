package cn.fzw.mapper;

import org.apache.ibatis.annotations.Param;

import cn.fzw.vo.Users;

public interface RegisterMapper {

	public Users selectByUsername(@Param("username")String username)throws Exception;

	public int insertinto(@Param("username")String username, @Param("password")String password,@Param("name")String name,@Param("email")String email)throws Exception;

	public int alterById(@Param("id")int id, @Param("password1")String password1)throws Exception;

	public int alterNameById(@Param("id")int id,@Param("name")String name)throws Exception;

}
