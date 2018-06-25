package cn.fzw.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import cn.fzw.vo.AccountSelect;

public interface AccountSelectMapper {

	public ArrayList<AccountSelect> selectMonthByUsername(@Param("username")String username)throws Exception;


}
