package cn.fzw.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import cn.fzw.vo.AccountOver;

public interface AccountOverMapper {

	public ArrayList<AccountOver> selectOverAjax(@Param("username")String username)throws Exception;

	
}
