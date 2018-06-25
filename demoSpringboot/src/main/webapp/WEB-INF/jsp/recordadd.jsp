<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>点滴记录</title>
<style type="text/css">
textarea { background-color: #00FFFF; 
font-size: 7;}
</style>
<script type="text/javascript">  
function check(){
	if(document.getElementById("record").value.length<6){
	alert("输入内容必需大于六位");
	return false;
	}else  {return true;} }
</script> 
</head>
<body>
 <center>
<h1><font style="楷体" color="yellow">${msg}</font></h1>
<form action="/record/recordadd.action?flag=1" method="post">
请输入心情记录内容：（至少输入六位内容）<br/><textarea id="record" rows="16" cols="40" STYLE="font-size:15pt;font-family:verdana" name="recordname" onfocus="if(value=='请输入内容：'){value='';}" onblur="if (value ==''){value='请输入内容：';}">请输入内容：</textarea><br/><br/>
<input type="submit" value="点击提交" onclick="return check()"> &nbsp;&nbsp;&nbsp;&nbsp;
<a href="/record/showRecord.action?flag=1"><input type="button" value="查看记录"></a>
</form>
</center>
</body>
</html>