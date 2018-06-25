package cn.fzw.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.fzw.shiro.MyShiroRealm;
import cn.fzw.vo.Users;

@Controller
@RequestMapping("/login")
public class LoginController {	
		@RequestMapping("/homepage")
		public String  homepage() {
			return "/homepage";
		}
		@RequestMapping("/alterRecord")
		public String  alterRecord() {
			return "/alterRecord";
		}
		@RequestMapping("/photowall")
		public String  showPhotowall() {
			return "/photowall";
		}
		@RequestMapping("/register")
		public String  showRegister() {
			return "/register";
		}		
		@RequestMapping("/personal")
		public String  showPersonal() {
			return "/personal";
		}
		@RequestMapping("logout")
		public String logout(Model model,HttpServletRequest request)throws Exception{
			new MyShiroRealm().clearCached();
			SecurityUtils.getSubject().logout();
			return "/login";
			}
		@RequestMapping("login")
		public String login(String username,String password,String code,HttpServletRequest request)throws Exception{
		    // 登录失败从request中获取shiro处理的异常信息。
		    // shiroLoginFailure:就是shiro异常类的全类名.								
			request.removeAttribute("msg");
	        String msg = "";			
	        String validateCode = (String) request.getSession().getAttribute("code");       
	        if (code == null || validateCode == null || !code.equalsIgnoreCase(validateCode)) {    
	        	msg="kaptchaValidateFailed-->验证码错误！";
	        	request.setAttribute("msg", msg);
	            return "login";
	        } 
			UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(username,password);
	        Subject subject = SecurityUtils.getSubject();
	        try {
	            subject.login(usernamePasswordToken);   //完成登录
	            Users user=(Users) subject.getPrincipal();
	            request.getSession().setAttribute("user", user);
	            return "homepage";
	        } 
/*		    String exception = (String) request.getAttribute("shiroLoginFailure");
		    System.out.println("exception=" + exception);
		    String msg = "";
		    if (exception != null) {
		        if (UnknownAccountException.class.getName().equals(exception)) {
		            System.out.println("UnknownAccountException -- > 账号不存在：");
		            msg = "UnknownAccountException -- > 账号不存在：";
		        } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
		            System.out.println("IncorrectCredentialsException -- > 密码不正确：");
		            msg = "IncorrectCredentialsException -- > 密码不正确：";
		        } else if ("kaptchaValidateFailed".equals(exception)) {
		            System.out.println("kaptchaValidateFailed -- > 验证码错误");
		            msg = "kaptchaValidateFailed -- > 验证码错误";
		        } else {
		            msg = "else >> "+exception;
		            System.out.println("else -- >" + exception);
		        }		        
		    }
		    request.setAttribute("msg", msg);
		    // 此方法不处理登录成功,由shiro进行处理
		    return "/login";*/
		    
			/*request.removeAttribute("msg");
	        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
	        String msg = "";
	        try {
	      	  SecurityUtils.getSubject().login(token);
	          }	*/	    
		    	catch (UnknownAccountException e) { 
		            msg = "UnknownAccountException -- > 账号不存在：";
		        } catch (IncorrectCredentialsException e1) { 
		            msg = "IncorrectCredentialsException -- > 密码不正确：";
		        } catch (AuthenticationException e2) { 
		        	if ("kaptchaValidateFailed".equals((String) request.getAttribute("shiroLoginFailure"))) { 
			            msg = "kaptchaValidateFailed -- > 验证码错误";
			        }else { 
		            msg = "AuthenticationException -- > 登录失败";  
		        }
		    }
            request.setAttribute("msg", msg);
            return "login"; 
		}
		/*public String login(Model model,String username,String password,
				HttpServletRequest request,HttpServletResponse response)throws Exception{
			request.removeAttribute("msg");
			int checkInt=new UtilController().checkCode(request, response);
			if(checkInt==0||null == username || null == password ||username.length()==0||password.length()==0){	
				String msg="请正确填写各项内容！";
				request.setAttribute("msg", msg);
				return "/login";
				}else{
					password=GetMd5Code.getMd5Code(password);
					Users user=loginservice.selectByusernameandpassword(username,password);
					if(null==user){
					String msg="账号或密码错误，请重新登陆！";
					request.setAttribute("msg", msg);
					return "/login"; }
				String name=user.getName();
				if(name==null){
					name=user.getUsername();
					user.setName(name);
						}
				request.getSession().setAttribute("user", user);
			
					return "/homepage";
				}	
		}	*/

}
