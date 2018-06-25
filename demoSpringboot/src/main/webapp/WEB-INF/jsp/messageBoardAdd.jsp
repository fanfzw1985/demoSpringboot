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
<script type="text/javascript" src="/util/jquery-3.3.1.min.js"></script>
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
function check(){
	if(document.getElementById("messageBoardAdd").value.length<6){
	alert("输入内容必需大于六位");
	return false;
	}else  if(codeFlag){
    		alert("验证码输入不正确！");
    		return false;
 			}else{return true;} }
</script> 
</head>
<body>
 <center>
<h1><font style="楷体" color="yellow">${msg}</font></h1>
<form action="/messageBoard/messageBoardAdd.action?flag=2" method="post">
请输入留言内容：（至少输入六位留言内容）<br/><textarea id="messageBoardAdd" rows="16" cols="40" STYLE="font-size:15pt;font-family:verdana" name="messageBoardAdd" onfocus="if(value=='请输入内容：'){value='';}" onblur="if (value ==''){value='请输入内容：';}">请输入内容：</textarea><br/><br/>
请输入验证码：<br><br><input type="text" name="code" id="code" onblur="checkCode()"><br/>
		 <img id="codeObj" alt="验证码"  style="width: 100px;height: 30px" src="${pageContext.request.contextPath}/utils/getCode.action" onclick="changCode()">
		 <a href="#" onclick="changCode()">看不清楚，换一张</a><br/><br/>
<input type="submit" value="点击提交" onclick="return check()"> &nbsp;&nbsp;&nbsp;&nbsp;
<a href="/messageBoard/showMessageBoard.action?flag=2"><input type="button" value="查看记录"></a>
</form>
</center>
</body>
</html>