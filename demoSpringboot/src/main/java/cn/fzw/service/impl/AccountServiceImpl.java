package cn.fzw.service.impl;

import java.util.ArrayList;

import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fzw.mapper.AccountMapper;
import cn.fzw.mapper.AccountOverMapper;
import cn.fzw.mapper.AccountSelectMapper;
import cn.fzw.service.AccountService;
import cn.fzw.utils.FoldLineTools;
import cn.fzw.vo.Account;
import cn.fzw.vo.AccountOver;
import cn.fzw.vo.AccountSelect;
@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private  AccountSelectMapper accountSelectMapper;
	@Autowired
	private  AccountOverMapper accountOverMapper;
	
	public int insertInto(int flag, String username, String name, String item,String account,
			Double money, String item_desc, String time) throws Exception {
		// TODO Auto-generated method stub
		return	accountMapper.insertInto(flag,username,name,item,account,money,item_desc,time);
	}
	
	public AccountSelect selectCountInAccount(String username) throws Exception {
		// TODO Auto-generated method stub
		return accountMapper.selectCountInAccount(username);
	}
	
	public ArrayList<Account> selectAllByPage(String username,int pageStart, int pageEnd) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Account> accountList=accountMapper.selectAllByPage(username,pageStart,pageEnd);
		return accountList;

	}

		 /**
		 * 从折线图工具类中获取创建完成的折线图
		 */
		public JFreeChart createPieTools(String username) throws Exception {
		    // TODO Auto-generated method stub
		    //获取折线图数据源
		    DefaultCategoryDataset dataset=new DefaultCategoryDataset(); 
		    ArrayList<AccountSelect> accountSelectMonthArrayList=accountSelectMapper.selectMonthByUsername(username);
		    
		    int monthNum=accountSelectMonthArrayList.size()-1;
		    for(int i=monthNum;i>=0;i--){
		    	   String monthTime=String.valueOf(accountSelectMonthArrayList.get(i).getCount());
		    	   StringBuilder sb = new StringBuilder(monthTime);//构造一个StringBuilder对象
		           sb.insert(4, "年");//在指定的位置1，插入指定的字符串
		           monthTime = sb.toString()+"月";
		    	   dataset.addValue(accountSelectMonthArrayList.get(i).getSumFlag1(),"日常收入",monthTime);
		    	   dataset.addValue(accountSelectMonthArrayList.get(i).getSumFlag2(),"日常支出",monthTime);
		    	   }
		    //从折线图工具类中获取创建完成的折线图
		    JFreeChart chart=FoldLineTools.createFoldLine(dataset);
		    return chart;
	}
		
		public AccountSelect selectByTime(String username,String time, String _time) throws Exception {
			// TODO Auto-generated method stub
			return accountMapper.selectByTime(username,time,_time);
		}

		public ArrayList<AccountOver> selectOverAjax(String username) throws Exception {
			// TODO Auto-generated method stub
			ArrayList<AccountOver> accountOverList=accountOverMapper.selectOverAjax(username);
			return accountOverList;
		}

		public int deleteAccountById(int id) throws Exception {
			// TODO Auto-generated method stub
			int num=accountMapper.deleteAccountById(id);
			return num;
		}

		public Account selectAccountById(int id) throws Exception {
			Account account=accountMapper.selectById(id);
			return account;
		}

		public int updateAccountById(int id, String item, String account,
				String item_desc, String time, Double money) throws Exception {
			// TODO Auto-generated method stub
			return accountMapper.updateAccountById(id,item,account,money,item_desc,time);
		}

		public AccountSelect selectCountInAccount(String username, int flag,
				String item, String account, String time1, String time2)
				throws Exception {
			// 条件查询
			return accountMapper.selectCountInAccount2(username,flag,item,account,time1,time2);
		}

		public ArrayList<Account> selectAllByPage2(String username, int flag,
				String item, String account, String time1, String time2, int pageStart, int accountPageNum) throws Exception {
			// 根据条件查询明细
			ArrayList<Account> accountList=accountMapper.selectAllByPage2(username,flag,item,account,time1,time2,pageStart,accountPageNum);
			return accountList;
		
		}
	
}
