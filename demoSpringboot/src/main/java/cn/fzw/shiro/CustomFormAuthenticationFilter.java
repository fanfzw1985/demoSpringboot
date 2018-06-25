/**
 * 
 */
package cn.fzw.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import cn.fzw.vo.Users;

/*** <p>Title:CustomFormAuthenticationFilter </p>
* <p>Description: </p>
* <p>Company: </p> 
* @author 樊志文
* @date 2018年5月25日*/
public class CustomFormAuthenticationFilter  extends FormAuthenticationFilter{
	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
        System.out.println("验证码");
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession session =httpServletRequest.getSession();
		  // 取出验证码    
        String validateCode = (String) session.getAttribute("code");    
        // 取出页面的验证码    
        // 输入的验证和session中的验证进行对比    
        String randomcode = httpServletRequest.getParameter("code");    
        if (randomcode != null && validateCode != null && !randomcode.equalsIgnoreCase(validateCode)) {    
            // 如果校验失败，将验证码错误失败信息，通过shiroLoginFailure设置到request中    
            httpServletRequest.setAttribute("shiroLoginFailure", "kaptchaValidateFailed");//自定义登录异常    
            // 拒绝访问，不再校验账号和密码    
            return true;    
        }   
		return super.onAccessDenied(request, response);
		
	}
		@Override
	    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
	            ServletResponse response) throws Exception {
	        //获取已登录的用户信息
	        Users user = (Users)subject.getPrincipal(); 
	        //获取session
	        HttpServletRequest httpServletRequest = WebUtils.toHttp(request); 
	        HttpSession session = httpServletRequest.getSession();
	        //把用户信息保存到session
	        session.setAttribute("user", user);
	        return super.onLoginSuccess(token, subject, request, response);
	    }
}
