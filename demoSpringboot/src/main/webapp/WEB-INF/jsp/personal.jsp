<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信心中心</title>
<style type="text/css">
body{font-size:25px; line－height:16px;bgcolor:"#FFFFFF"}
    input{
        border-radius:4px;
        border:1px solid #e5e9ef;
        background:#fff;
        margin-right:10px;
        text-align:center;
        width:150px;
        height:50px;
        line-height: 8px;
        margin-top:6px;
        outline:0;
        font-size: 25px
    }
</style>
</head>
<body>
<center>
	 	<h1><font style="楷体" color="yellow">${msg}</font></h1>
	<br/><br/>
	<h3>个人信息中心</h3><br/><br/>
	帐号：${user.username }<br/><br/>
	昵称：${user.name }<br/><br/><br/>
	<a href="/register/showAlterName.action"><input type="button" value="修改昵称"></a>
	<a href="/register/showAlterpassword.action"><input type="button" value="修改密码"></a>
</center>
</body>
</html>