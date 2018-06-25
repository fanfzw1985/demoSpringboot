package cn.fzw.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
@RequestMapping("/messageBoard")
public class MessageBoardController {
	@Autowired
	private RecordService recordservice;
	@Autowired
	private LoginService loginservice;
	@Value("${messagePageNum}")  
	private int messagePageNum;
	
	
	@RequestMapping("/messageBoardInsert")
	public String  messageBoardInsert() {
		return "/messageBoardAdd";
	}
	
	@RequestMapping("/updateBoardById")
	public String updateBoardById(int id,int flag,String messageBoardUpdate,HttpServletRequest request)throws Exception{
		request.removeAttribute("msg");
		int num=recordservice.updateBoardById(id,messageBoardUpdate);
		if(num==1){
			String msg="修改成功！";
			request.setAttribute("msg", msg);
			if(flag==1){
				return "/record";	
			}else{
			return "/messageBoard";	}
		}else{
			String msg="系统出现故障，修改不成功！";
			request.setAttribute("msg", msg);
		return "/showRecords";	}
	}
	
	@RequestMapping("/showMessageBoardById")
	public String showMessageBoardById(int id,HttpServletRequest request)throws Exception{
		Records records=recordservice.showMessageBoardById(id);
		request.getSession().setAttribute("records", records);	
		return "/showRecords";	
	}
	@RequestMapping("/messageBoardAdd")
	public String messageBoardAdd(Model model,String messageBoardAdd,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		request.removeAttribute("msg");
		String recordmsg =request.getParameter("messageBoardAdd");
		String _flag =request.getParameter("flag");
			int flag = Integer.parseInt(_flag);
			Users user=(Users) request.getSession().getAttribute("user");
			int checkInt=new UtilController().checkCode(request, response);
			if(checkInt==0){
				String msg="验证码输入不正确！";
				request.setAttribute("msg", msg);
				return "/messageBoardAdd";
			}else{
			int num=recordservice.insertInto(user.getUsername(),user.getName(),recordmsg,flag);
				if(num==1){	
					String msg="添加成功！";
					request.setAttribute("msg", msg);
					request.setAttribute("flag", 1);
					return showMessageBoard(model,request);
					}else{
						String msg="添加失败，请重新添加！";
						request.setAttribute("msg", msg);
						return "/messageBoardAdd";
						}
			}
					
		 
		
}

	@RequestMapping("/showMessageBoard")
	public String showMessageBoard(Model model,HttpServletRequest request)throws Exception{
		request.removeAttribute("msg");
		int pageNum = 1;
		String _pageNum = request.getParameter("pageNum");		
		if (_pageNum != null) {
			pageNum = Integer.parseInt(_pageNum);
		}
		String _flag =request.getParameter("flag");
		int flag = Integer.parseInt(_flag);
		int num=loginservice.selectAllInRecordes(flag);
		int totalPage = (num%messagePageNum==0)?(num/messagePageNum):num/messagePageNum+1;
		request.setAttribute("totalPage", totalPage);
		request.getSession().setAttribute("pageNum", pageNum);	
		int pageStart=(pageNum-1)*messagePageNum;
		ArrayList<Records> recordList=recordservice.selectAllByPage(pageStart,messagePageNum,flag);
		request.getSession().setAttribute("recordList", recordList);
		return "/messageBoard";	
	}
	
}