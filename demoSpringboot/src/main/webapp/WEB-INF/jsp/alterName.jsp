<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改昵称</title>
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
	if((document.getElementById("name").value.length)!=(document.getElementById("name1").value.length)){alert("两次昵称输入必需一致!");
	return false;}else
	if((document.getElementById("name").value)!=(document.getElementById("name1").value)){
	alert("两次昵称输入必需一致!");
	return false;
 	}else  {return true;}  }
</script> 
</head>
<body>
<center>
	<br/><h1>${msg }</h1><br/>
	<h1>修改用户昵称</h1><br/><br/>
	<form action="/register/alterName.action" method="post">
		请输入你的新昵称：<input type="text" name="name" id="name"/><br/><br/><br/>
		请再次输入新昵称：<input type="text" name="name1" id="name1"/><br><span>*两次昵称输入必需一致!</span> <br>
		<input type="submit" value="确认修改" onclick="return check()">
	</form>
</center>
</body>
</html>