<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎来到爱之家</title>
<style type="text/css">
*{
	margin: 0;
	padding: 0;
}
body{
	font-family: "微软雅黑";
	font-size: 14px;
	background: url(../imags/login.jpg) fixed center center;
}
.logo_box{
	width: 280px;
	height: 490px;
	padding: 35px;
	color: #EEE;
	position: absolute;
	left: 50%;
	top:30px;
	margin-left: -175px;
}
.logo_box h3{
	text-align: center;
	height: 20px;
	font: 20px "microsoft yahei",Helvetica,Tahoma,Arial,"Microsoft jhengHei",sans-serif;
	color: #FFFFFF;
	height: 20px;
	line-height: 20px;
	padding:0 0 35px 0;
}	
.input_outer{
	height: 46px;
	padding: 0 5px;
	margin-bottom: 20px;
	border-radius: 50px;
	position: relative;
	border: rgba(255,255,255,0.2) 2px solid !important;
}
.u_user{
	width: 25px;
	height: 25px;
	background: url(../imags/login_ico.png);
	background-position:  -125px 0;
	position: absolute;
	margin: 12px 13px;
}
.uc_user{
	width: 25px;
	height: 25px;
	background: url(../imags/login_ico.png);
	background-position:  -86px -68px;
	position: absolute;
	margin: 12px 13px;
}
.us_uer{
	width: 25px;
	height: 25px;
	background-image: url(../imags/login_ico.png);
	background-position: -125px -34px;
	position: absolute;
	margin: 12px 13px;
}
.l-login{
	position: absolute;
	z-index: 1;
	left: 50px;
	top: 0;
	height: 46px;
	font: 14px "microsoft yahei",Helvetica,Tahoma,Arial,"Microsoft jhengHei";
	line-height: 46px;
}

.text{
	width: 220px;
	height: 46px;
	outline: none;
	display: inline-block;
	font: 14px "microsoft yahei",Helvetica,Tahoma,Arial,"Microsoft jhengHei";
	margin-left: 50px;
	border: none;
	background: none;
	line-height: 46px;
}
/*///*/
.mb2{
	margin-bottom: 20px
}
.mb2 a{
	text-decoration: none;
	outline: none;
}
.mb2 input{
	text-decoration: none;
	outline: none;
	width: 280px;
	height: 50px;
	color: #FFFFFF;
	text-align: center;
	line-height: 18px;
	text-decoration: none;
	border:none;
	font-size: 23px
}
.submit {
	padding: 15px;
	margin-top: 20px;
	display: block;
}
.act-but{
	height: 20px;
	line-height: 20px;
	text-align: center;
	font-size: 20px;
	border-radius: 50px;
	background: #0096e6;
}


/*////*/
.check{
	width: 280px;
	height: 22px;
}
.clearfix::before{
	content: "";
	display: table;
}
.clearfix::after{
	display: block;
	clear: both;
	content: "";
	visibility: hidden;
	height: 0;
}
.login-rm{
	float: left;
}
.login-fgetpwd {
	float: right;
}
.checkbox{
	vertical-align: middle;
	margin: 0 5px 0 0;
}
</style>
<script type="text/javascript" src="../util/jquery-3.3.1.min.js"></script>
<script type="text/javascript">  
var codeFlag;
function checkCode(){
$.ajax({
			url:"/utils/checkCode.action",
			type:"post",
			data:{"code":$("#code").val()},
			success:function(result){
				if(result==0){
					changCode();
					alert("验证码输入不正确，请重新输入！");
					codeFlag=true;
				}else{
				codeFlag=false;	}				
			},
			error:function() { 
						codeFlag=true;}

		});
}
function check(){
	if(document.getElementById("username").value.length<6){
		alert("帐号位数必需大于6位");
		return false;
		}else if(document.getElementById("password").value.length<6 ){
		alert("密码位数必需大于6位");
		return false;
		}else if(document.getElementById("code").value.length==0){
  			alert("验证码输入不正确，请确认再提交！");
  			return false;
    		}else if(codeFlag){
    		alert("验证码输入不正确！");
    		return false;
 			}else
 		{return true;}
	}
  
  function changCode(){
	  	var codeSrc=$("#codeObj");
  		var newSrc=codeSrc.attr("src");
  		codeSrc.attr("src",changUrl(newSrc));
}
  // 时间戳
  function changUrl(url){
  	var timeStamp=(new Date()).valueOf();	
  	if(url.indexOf("?")>=0){
  		var num=url.indexOf("?")+1;
  	  	url=url.substring(0,num);
  	url=url+"timetamp="+timeStamp;
  	}else{
  	url=url+"?timeStamp="+timeStamp;
  	}
  	return url;
  }
  
  function getPass(){
  	alert("忘记密码请用注册邮箱发送帐号到252672855@qq.com,48小时内系统会发送密码到注册邮箱，谢谢！");
  	return false;
  }
</script>  
</head>
<body>
	 <center><h3><font style="楷体" color="yellow">${msg}</font></h3></center>
<div class="logo_box">
	<h3>我们的家欢迎你</h3>
	<form action="/login/login.action" name="/login/login.action" method="post">
	用户名：
		<div class="input_outer">
			<span class="u_user"></span>
			<input name="username" id="username"  class="text" onFocus=" if(this.value=='输入用户名登录') this.value=''" onBlur="if(this.value=='') this.value='输入用户名登录'" value="输入用户名登录" style="color: #FFFFFF !important" type="text">
		</div>
	密码：
		<div class="input_outer">
			<span class="us_uer"></span>
			<input name="password" id="password" class="text" onFocus=" if(this.value=='输入正确密码') {this.value='';this.type='password';}" onBlur="if(this.value=='') {this.value='输入正确密码';this.type='text';}" value="输入正确密码" style="color: #FFFFFF !important; position:absolute; z-index:100;"  type="text">
		</div>
	请输入验证码：
		<div class="input_outer">
			<span class="uc_user"></span>
			<input name="code" id="code"  class="text" onFocus=" if(this.value=='输入验证码') this.value=''" value="输入验证码" onblur="checkCode()" style="color: #FFFFFF !important" type="text">
		</div>
		
	 <img id="codeObj" alt="验证码"  style="width: 130px;height: 40px" src="${pageContext.request.contextPath}/utils/getCode.action" onclick="changCode()">
	  <a href="#" onclick="changCode()">看不清楚，换一张</a><br/>
	<!--  <input type="checkbox" name="rememberMe" value="自动登陆"/> -->
	<div class="mb2">	
	<input type="submit" value="登陆" onclick="return check()" class="act-but submit" style="color: #FFFFFF"/>
	</div>	
	<div class="mb2">
	<a href="/login/register.action" class="act-but submit" style="color: #FFFFFF">注册</a>
	</div>
	</form>
	<a href="#" class="login-fgetpwd" style="color: #FFFFFF" onclick="getPass();">忘记密码？</a>
</div>
</body>
</html>