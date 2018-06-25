package cn.fzw.service;

import java.util.ArrayList;

import org.jfree.chart.JFreeChart;

import cn.fzw.vo.Account;
import cn.fzw.vo.AccountOver;
import cn.fzw.vo.AccountSelect;


public interface AccountService {

	public int insertInto(int flag, String username, String name, String item,
			String account, Double money, String item_desc, String time)throws Exception;

	public AccountSelect selectCountInAccount(String username)throws Exception;

	public ArrayList<Account> selectAllByPage(String username,int pageStart, int pageEnd)throws Exception;

	public JFreeChart createPieTools(String username)throws Exception;

	public AccountSelect selectByTime(String username,String time, String _time)throws Exception;

	public ArrayList<AccountOver> selectOverAjax(String username)throws Exception;

	public int deleteAccountById(int id)throws Exception;

	public Account selectAccountById(int id)throws Exception;

	public int updateAccountById(int id, String item, String account, String item_desc,
			String time, Double money)throws Exception;

	public AccountSelect selectCountInAccount(String username, int flag,
			String item, String account, String time1, String time2)throws Exception;

	public ArrayList<Account> selectAllByPage2(String username, int flag,
			String item, String account, String time1, String time2, int pageStart, int accountPageNum)throws Exception;



}
