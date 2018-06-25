package cn.fzw.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import cn.fzw.vo.Account;
import cn.fzw.vo.AccountSelect;

public interface AccountMapper {

	public int insertInto(@Param("flag")int flag, @Param("username")String username,@Param("name") String name, @Param("item")String item,@Param("account")String account,
			@Param("money")Double money, @Param("item_desc")String item_desc, @Param("time")String time)throws Exception;

	public AccountSelect selectCountInAccount(@Param("username")String username)throws Exception;

	public ArrayList<Account> selectAllByPage(@Param("username")String username,@Param("pageStart")int pageStart, @Param("pageEnd")int pageEnd)throws Exception;

	public AccountSelect selectByTime(@Param("username")String username,@Param("time")String time, @Param("_time")String _time)throws Exception;

	public Account selectById(@Param("id")int id)throws Exception;

	public int deleteAccountById(@Param("id")int id)throws Exception;

	public int updateAccountById(@Param("id")int id,@Param("item")String item,@Param("account")String account,
			@Param("money")Double money, @Param("item_desc")String item_desc, @Param("time")String time)throws Exception;

	public AccountSelect selectCountInAccount2(@Param("username")String username, @Param("flag")int flag,
			@Param("item")String item, @Param("account")String account, @Param("time1")String time1, @Param("time2")String time2)throws Exception;

	public ArrayList<Account> selectAllByPage2(@Param("username")String username, @Param("flag")int flag,
			@Param("item")String item, @Param("account")String account, @Param("time1")String time1, @Param("time2")String time2, @Param("pageStart")int pageStart, @Param("accountPageNum")int accountPageNum)throws Exception;



}
