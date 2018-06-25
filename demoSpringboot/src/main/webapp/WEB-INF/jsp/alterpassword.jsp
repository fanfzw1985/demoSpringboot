<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码</title>
<style type="text/css">
body{
font－size:25px; 
line－height:16px;
bgcolor:#FFFFFF}
input{
        border-radius:4px;
        border:1px solid #e5e9ef;
        background:#fff;
        margin-right:10px;
        text-align:center;
        width:300px;
        height:40px;
        line-height: 8px;
        margin-top:6px;
        outline:0;
        font-size: 25px
}
form{
font-size: 25px;
}
</style>
<script type="text/javascript">  
function check(){
	if(document.getElementById("password").value.length<6){
	alert("密码位数必需大于6位");
	return false;
	}
	else if(document.getElementById("password1").value.length<6 ){
	alert("密码位数必需大于6位");
	return false;
  }else if((document.getElementById("password1").value)!=(document.getElementById("password2").value)){
 	alert("两次输入的密码不一致");
 	return false;
 	}else  {return true;}  }
</script> 
</head>
<body>
<center>
	<br/><h1>${msg }</h1><br/>
	<h1>修改用户密码</h1><br/><br/>
	<form action="/register/alterpassword.action" method="post">
		请输入你原来密码：<input type="password" name="password" id="password"/><br/><br/>
		请输入你的新密码：<input type="password" name="password1" id="password1"/><br/><span>*密码必需大于六位!</span><br/><br/>
		请再次输入新密码：<input type="password" name="password2" id="password2"/><br><span>*两次密码输入必需一致!</span> <br>
		<input type="submit" value="确认修改" onclick="return check()">
	</form>
</center>
</body>
</html>