package cn.fzw.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.fzw.service.AccountService;
import cn.fzw.utils.SelectAccount;
import cn.fzw.vo.Account;
import cn.fzw.vo.AccountOver;
import cn.fzw.vo.AccountSelect;
import cn.fzw.vo.Users;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private AccountService accountService; 
	@Value("${accountPageNum}")  
	private int accountPageNum;
	@Value("${web.upload-path}")
    private String uploadPath;
	/**
	 * 修改已记录流水
	 * @param request
	 * @param id
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping("/account2")
	public String  showAccount2() {
		return "/account2";
	}
	@RequestMapping("/account1")
	public String  showAccount1() {
		return "/account1";
	}
	@RequestMapping("/account")
	public String  showAccount() {
		return "/account";
	}
	@RequestMapping("/selectByAll")
	@ResponseBody
		public Object selectByAll(HttpServletRequest request,String item,String account,String time1,String time2,
       HttpServletResponse response) throws Exception {
		ArrayList<Account> accountList=SelectAccount.selectAccount(accountService, request, item, account, time1, time2, accountPageNum);
	     String totalPage=request.getSession().getAttribute("totalPage").toString();
	     String pageNum=request.getSession().getAttribute("pageNum").toString(); 
	     Map<String, Object> map=new HashMap<String, Object>();
	     map.put("pageNum", pageNum);
	     map.put("totalPage", totalPage);
	     map.put("accountList", accountList);
	     //String jsonMap=JSONObject.valueToString(map);
		return map;		
	}
	
	@RequestMapping("/updateAccountById")
	public String updateAccountById(Model model,HttpServletRequest request,int id,String item,String account,String item_desc,String time,
       Double money,HttpServletResponse response) throws Exception {
	response.setContentType("text/html;charset=utf-8");
	request.removeAttribute("msg");
	int num=accountService.updateAccountById(id,item,account,item_desc,time, money);
	String msg;
	if(num==1){
		msg="修改成功！";
		request.setAttribute("msg", msg);
	}else{
		msg="系统异常，修改不成功！";
		request.setAttribute("msg", msg);
	}
	return showAccount(model,request, null, null, null, null, response);	
	}
	
	
	
		@RequestMapping("/updateAccount")
	public String updateAccount(HttpServletRequest request,int id,int flag,
	        HttpServletResponse response, ModelMap modelMap) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.removeAttribute("msg");
		Account account=accountService.selectAccountById(id);
		request.getSession().setAttribute("account", account);
		//if(flag==1){return "updateAccount1";}else{
		return "updateAccount"+flag;
		}
		
	@RequestMapping("/deleteAccountById")
	public String deleteAccountById(Model model,HttpServletRequest request,int id,
	        HttpServletResponse response, ModelMap modelMap) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.removeAttribute("msg");
		int num=accountService.deleteAccountById(id);
		String msg=null;
		if(num==1){
			msg="删除成功！";
			request.setAttribute("msg", msg);
		}else{
			msg="系统异常，删除不成功！";
			request.setAttribute("msg", msg);
		}
		return showAccount(model,request, null, null, null, null, response);
	}
	
	@RequestMapping("/selectOver")
	public String selectOverAjax(HttpServletRequest request,
	        HttpServletResponse response, ModelMap modelMap) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		Users user=(Users) request.getSession().getAttribute("user");
		ArrayList<AccountOver> accountOverList=accountService.selectOverAjax(user.getUsername());
		request.getSession().setAttribute("accountOverList", accountOverList);
		return "selectOver";
	}
	
	@RequestMapping("/reportFormAjax")
	@ResponseBody
	public String reportFormAjax(HttpServletRequest request,
	        HttpServletResponse response, ModelMap modelMap) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.removeAttribute("msg");
		String time = request.getParameter("time");
		String _time = request.getParameter("_time");
		Users user=(Users) request.getSession().getAttribute("user");
		AccountSelect accountSelectByTime= accountService.selectByTime(user.getUsername(),time,_time);
//		request.getSession().setAttribute("accountSelectByTime", accountSelectByTime);
		
		return accountSelectByTime.getSumFlag1()+","+accountSelectByTime.getSumFlag2();

	}
	
	@RequestMapping("/reportForm")
	public ModelAndView getColumnChart(HttpServletRequest request,
	        HttpServletResponse response, ModelMap modelMap) throws Exception {
		Users user=(Users) request.getSession().getAttribute("user");
		String username=user.getUsername();
		// 在业务层获取创建的拆线图（此时折线图已经创建完成的）
	    JFreeChart jfreeChart = accountService.createPieTools(username);
	    // 将图形转换为图片传到reportForm.jsp
//	    String fileName = ServletUtilities.saveChartAsJPEG(jfreeChart, 800, 500,
//	    		 request.getSession());	  
	    String fileName=System.currentTimeMillis()+".jpeg";
	    	File targetFile=new File(uploadPath+"jfreejpeg/",fileName);
		  if(!targetFile.getParentFile().exists()){ //判断文件父目录是否存在  
			  targetFile.getParentFile().mkdir();  
	        } 
	    ChartUtilities.saveChartAsJPEG(targetFile, jfreeChart, 800, 500);
	    String chartURL = "../jfreejpeg/"+ fileName;
	    modelMap.put("chartColumnURL", chartURL);
	    return new ModelAndView("reportForm", modelMap);
	}
	
	@RequestMapping("/showAccount")
	public String showAccount(Model model, HttpServletRequest request,String item,String account,String time1,String time2,
		       HttpServletResponse response)throws Exception{
		ArrayList<Account> accountList=SelectAccount.selectAccount(accountService, request, item, account, time1, time2, accountPageNum);
		if(accountList==null){
			return "/account2";			
		}else{
			request.setAttribute("accountList", accountList);
			return "account";
		}		
	}
	
	@RequestMapping("/accountAdd")
	public String accountAdd(Model model,String item,String account,String item_desc,String time,
			HttpServletRequest request)throws Exception{
		String _flag =request.getParameter("flag");
		int flag = Integer.parseInt(_flag);
		int num=accountAddMethod(flag,item,account,item_desc,time,request);
		if(num==1)	{
			String msg="添加成功！";
			String accountShow=showAccount(model, request, null, null, null, null, null);
			request.setAttribute("msg", msg);
			return accountShow;
		}else{	
			String msg="系统出现问题，请重新添加！";
			request.setAttribute("msg", msg);			
			return "/account"+flag;}
	}
	@RequestMapping("/accountAdd2")
	public String accountAdd2(Model model,String item,String account,String item_desc,String time,
			HttpServletRequest request)throws Exception{
		String _flag =request.getParameter("flag");
		int flag = Integer.parseInt(_flag);
		int num=accountAddMethod(flag,item,account,item_desc,time,request);
		if(num==1)	{
			String msg="添加成功！";
			request.setAttribute("msg", msg);
		}else{	
			String msg="系统出现问题，请重新添加！";
			request.setAttribute("msg", msg);}			
		return "/account"+flag;
	}	
	
	
	
	public int accountAddMethod(int flag,String item,String account,String item_desc,String time,HttpServletRequest request) throws Exception
	{
		request.removeAttribute("msg");
		Double money = Double.parseDouble(request.getParameter("money"));
		Users user=(Users) request.getSession().getAttribute("user");
		String username=user.getUsername();
		String name=user.getName();		
		return accountService.insertInto(flag,username,name,item,account,money,item_desc,time);		
	}
}
