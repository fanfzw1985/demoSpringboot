package cn.fzw.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.fzw.service.LoginService;
import cn.fzw.service.RecordService;
import cn.fzw.vo.Records;
import cn.fzw.vo.Users;

@Controller
@RequestMapping("/record")
public class RecordController {
	@Autowired
	private RecordService recordservice;
	@Autowired
	private LoginService loginservice;
	@Value("${recordPageNum}")  
	private int recordPageNum;
	@Value("${superuser3}")  
	private String superuser3;
	@Value("${superadmin1}")  
	private String superadmin1;
	@Value("${superadmin2}")  
	private String superadmin2;
	
	@RequestMapping("/deleteRecordById")
	@RequiresPermissions("record:query")
	public String deleteRecordById(HttpServletRequest request,HttpServletResponse response)throws Exception{
		request.removeAttribute("msg");
		String checkedId=request.getParameter("id");
		String msg=null;
			int num=recordservice.deleteRecordById(checkedId);
			if(num>0){
				msg="删除成功！";	
		}else{msg="系统故障，删除不成功！";	}
		request.setAttribute("msg", msg);
		return "record";
	}


	@RequestMapping("/alterRecord")
	@RequiresPermissions("record:query")
	public String alterRecord(String name,String updatMsg,String code,
			HttpServletRequest request)throws Exception{
		request.removeAttribute("msg");
			ArrayList<Records> recordList=recordservice.selectByNameAndMessage(name,updatMsg);
					request.setAttribute("recordList", recordList);
					return "/superUpdate";
	}
	
	
	@RequestMapping("/recordadd")
	public String recordadd(Model model,String recordname,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		request.removeAttribute("msg");
		String recordmsg =request.getParameter("recordname");
		String _flag =request.getParameter("flag");
			int flag = Integer.parseInt(_flag);
			Users user=(Users) request.getSession().getAttribute("user");
			String username=user.getUsername();
			if(!(superadmin2.equals(username)||superadmin1.equals(username)))
			{flag=2;}
			int num=recordservice.insertInto(user.getUsername(),user.getName(),recordmsg,flag);
				if(num==1){	
					String msg="添加成功！";
					request.getSession().setAttribute("msg1", msg);
					// request.getRequestDispatcher("/record/showRecord.action").forward(request, response);
					// return "";
					request.setAttribute("flag", 1);
					return showRrecord(model,request);
					}else{
						String msg="添加失败，请重新添加！";
						request.setAttribute("msg", msg);
						return "/recordadd";
						}
	
					
		 
		
}
	@RequestMapping("/recordIsAdd")
	public String recordIsAdd(Model model,HttpServletRequest request)throws Exception{
		request.removeAttribute("msg");
		Users user=(Users) request.getSession().getAttribute("user");
		String username=user.getUsername();
		if(superadmin2.equals(username)||superadmin1.equals(username)){
			return "/recordadd";
		}else{
			String msg="对不起，添加心情记录功能只对特定用户开发！请使用其他功能，谢谢！";
			request.setAttribute("msg", msg);
			return "/record";
		}
	}
	@RequestMapping("/showRecord")
	public String showRrecord(Model model,HttpServletRequest request)throws Exception{
		request.removeAttribute("msg");
		int pageNum = 1;
		String _pageNum = request.getParameter("pageNum");		
		if (_pageNum != null) {
			pageNum = Integer.parseInt(_pageNum);
		}
		String _flag =request.getParameter("flag");
		int flag = Integer.parseInt(_flag);
		int num=loginservice.selectAllInRecordes(flag);
		int totalPage = (num%recordPageNum==0)?(num/recordPageNum):num/recordPageNum+1;
		request.setAttribute("totalPage", totalPage);
		request.getSession().setAttribute("pageNum", pageNum);	
		int pageStart=(pageNum-1)*recordPageNum;
		ArrayList<Records> recordList=recordservice.selectAllByPage(pageStart,recordPageNum,flag);
		request.getSession().setAttribute("recordList", recordList);
		return "/record";	
	}
	
}