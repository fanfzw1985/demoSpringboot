package cn.fzw.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.fzw.service.RegisterService;
import cn.fzw.utils.GetMd5Code;
import cn.fzw.vo.Users;

@Controller
@RequestMapping("/register")
public class RegisterController {
	@Autowired
	private RegisterService registerservice;
	
	@RequestMapping("/showAlterpassword")
	public String  showAlterpassword() {
		return "/alterpassword";
	}
	@RequestMapping("/showAlterName")
	public String  showAlterName() {
		return "/alterName";
	}
	@RequestMapping("/friendlyLink")
	public String  friendlyLink() {
		return "/friendlyLink";
	}
	
	
	@RequestMapping("register")
	public String register(Model model,String username,String password,String password1,String name,String email,
			HttpServletRequest request,HttpServletResponse response)throws Exception{
		request.removeAttribute("msg");
		String msg = null;
		int checkInt=new UtilController().checkCode(request, response);
		if(username.length()==0||password.length()==0||!password.equals(password1)){	
			msg="密码输入不正确，请重新输入！";
			model.addAttribute("msg",msg);
			return "/register";}
			else if(checkInt==0||username.length()==0||email.length()==0){
				msg="帐号、邮箱或验证码输入不正确，请重新输入！";
				model.addAttribute("msg",msg);
				return "/register";
			}else{
				if(name.length()==0){name=username;}
				password=GetMd5Code.getMd5Code(password);
				int num=registerservice.register(username,password,name,email);
				if(num==-1){
					msg="账号已存在，请重新输入！";
					request.setAttribute("msg", msg);
					return "/register";
				}else if(num==0){
					msg="注册不成功，请重新注册！";
					request.setAttribute("msg", msg);
					return "/register";
				}else if(num==1){   
					msg="注册成功，请登陆！";
					request.setAttribute("msg", msg);
					return "/login";}
				else{		
						msg="系统异常，请重新注册";
						request.setAttribute("msg", msg);
						return "register";}
				
					}}
	@RequestMapping("alterpassword")
	public String alterpassword(HttpServletResponse response,String password,String password1,String password2,
			HttpServletRequest request)throws Exception{
		request.removeAttribute("msg");
		String msg=null;
		if(password.length()==0||password1.length()==0||!password1.equals(password2)){	
			msg="密码输入不正确，请重新输入！";
			request.setAttribute("msg", msg);
			return "/alterpassword";
		}else {
			Users user=(Users) request.getSession().getAttribute("user");
			int id=user.getId();
			String username=user.getUsername();
			password1=GetMd5Code.getMd5Code(password1);
			int num=registerservice.alterById(id,username,password,password1);
			if(num==0){
				msg="原始密码错误，请重新输入！";
				request.setAttribute("msg", msg);
				return "/alterpassword";
			}else if(num==-1){
				msg="修改不成功，请重新输入!";
				request.setAttribute("msg", msg);
				return "/alterpassword";
			}else{
				msg="密码修改成功!";
				request.setAttribute("msg", msg);
				return "/personal";
			}
		
	}}
	@RequestMapping("alterName")
	public String alterName(HttpServletResponse response,String name,String name1,
			HttpServletRequest request)throws Exception{			
		request.removeAttribute("msg");
		String msg=null;
		if(name.length()==0||!name.equals(name1)){	
			msg="昵称输入不正确，请重新输入！";
			request.setAttribute("msg", msg);
			return "/alterName";			
		}else {
			Users user=(Users) request.getSession().getAttribute("user");
			int id=user.getId();
			int num=registerservice.alterNameById(id,name);
			if(num==1){
				msg="昵称修改成功!";
				request.setAttribute("msg", msg);
				return "/personal";
			}else{
				msg="修改不成功，请重新输入!";
				request.setAttribute("msg", msg);
				return "/alterName";
			}
		}
	}
	}
