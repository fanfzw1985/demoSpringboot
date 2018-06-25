package cn.fzw.utils;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import cn.fzw.service.AccountService;
import cn.fzw.vo.Account;
import cn.fzw.vo.AccountSelect;
import cn.fzw.vo.Users;

public class SelectAccount {
 public static ArrayList<Account> selectAccount(AccountService accountService,HttpServletRequest request,String item,String account,String time1,String time2,int accountPageNum) throws Exception{
	 	request.getSession().removeAttribute("msg");
	 	request.removeAttribute("msg");
		int pageNum = 1;
		String _pageNum = request.getParameter("pageNum");		
		if (_pageNum != null) {
			pageNum = Integer.parseInt(_pageNum);
		}
		int flag=0;
		String _flag = request.getParameter("flag");	
		if (_flag != null) {
			flag = Integer.parseInt(_flag);
		}
		Users user=(Users) request.getSession().getAttribute("user");
		String username=user.getUsername();
		//AccountSelect accountSelect=accountService.selectCountInAccount(username);  
		AccountSelect accountSelect=accountService.selectCountInAccount(username,flag,item,account,time1,time2); 
		request.getSession().setAttribute("accountSelect", accountSelect);
		int count=accountSelect.getCount();
		if(count==0){
			String msg="还没有日常开销记录，赶快去记一笔吧！";
			request.setAttribute("msg", msg);
			return null;
		}else{
		int totalPage = (count%accountPageNum==0)?(count/accountPageNum):count/accountPageNum+1;
		request.getSession().setAttribute("pageNum", pageNum);
		request.getSession().setAttribute("totalPage", totalPage);
		int pageStart=(pageNum-1)*accountPageNum;
		ArrayList<Account> accountList=accountService.selectAllByPage2(username,flag,item,account,time1,time2,pageStart,accountPageNum);
		request.getSession().setAttribute("accountList", accountList);	
		return accountList;}
 }
}
