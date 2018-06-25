package cn.fzw.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.fzw.utils.CodeUtil;

@Controller
@RequestMapping("/utils")
public class UtilController {
	@RequestMapping("getCode")
	public void getCode(Model model,HttpServletRequest request,HttpServletResponse response)throws Exception{
		new CodeUtil().getCode(request, response);
		}
	
	@RequestMapping("checkCode")
	@ResponseBody
	public int checkCode(HttpServletRequest request,HttpServletResponse response){
		response.setContentType("text/html;charset=utf-8");
		String code=request.getParameter("code");
		Object 	_sessionCode=request.getSession().getAttribute("code");
		if(null==code||null==_sessionCode){
			return 0;
		}else{
		String sessionCode =_sessionCode.toString();
		if (code != null && !"".equals(code) && sessionCode != null && !"".equals(sessionCode)){
			if(code.equalsIgnoreCase(sessionCode)){
				return 1;
			}else{
				return 0;
			}		
	}else{
		return 0;	}

	}}
}

