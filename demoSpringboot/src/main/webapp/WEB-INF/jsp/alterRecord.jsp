<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改记录</title>
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
<script type="text/javascript" src="/util/jquery-3.3.1.min.js"></script>
<script type="text/javascript"> 

</script> 
</head>
<body>
<center>
	<h1>修改用户留言记录</h1><br/><br/>
	<form action="/record/alterRecord.action" method="post">
		请输入昵称：<input type="text" name="name" id="name"/><br/><br/><br/>
		请输入查询内容：<input type="text" name="updatMsg" id="updatMsg"/><br>
		<input type="submit" value="查询" />
	</form>
</center>
</body>
</html>