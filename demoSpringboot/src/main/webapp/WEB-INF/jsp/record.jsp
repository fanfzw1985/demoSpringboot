<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>点滴记录</title>
<style type="text/css">
    .page_btn{
        border-radius:4px;
        border:1px solid #e5e9ef;
        background:#fff;
        margin-right:10px;
        text-align:center;
        width:38px;
        height:38px;
        line-height: 8px;
        margin-top:6px;
        outline:0;
    }

    .page_btn:hover{
        border:1px solid #4f90fb;
        color:#4f90fb;
    }

    span.pages_span{
        margin-right:10px;
        width:38px;
        height:38px;
        position:relative;
        top:10px;
    } 
</style>
</head>
<script type="text/javascript">
function goGoGo(){
	var goId=document.getElementById("goPage").value;
	var totlPage="${totalPage}";
	if(goId<0||goId>totlPage){
		alert("页码错误,请重新输入！");
	}else{
	window.location.href="/record/showRecord.action?pageNum="+goId;}
}
</script>
<body> 
<center>
<h1><font style="楷体" color="yellow">${msg}</font></h1></center> 
<ul>
	<c:if test="${empty recordList}">
		无心情记录信息！
	</c:if>
	<c:if test="${not empty recordList}">
		<c:forEach items="${recordList}" var="c">
			<font size="3" color="#FF4500">WritingBy(记录作者):&nbsp;&nbsp;${c.name}</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<font size="3" color="#FF4500">WritingTime（记录时间）:<fmt:formatDate value="${c.time}" pattern="yyyy-MM-dd HH:mm:ss"/></font><br/><br/>
			<font size="4" color="#C71585">心情记录内容:&nbsp;&nbsp;${c.message}</font><br/>
			<c:if test="${ c.username==user.username}">
					<a href="/messageBoard/showMessageBoardById.action?id=${c.id } "><input  type="button"  value="点击修改"/></a>
				</c:if><br>
			============================================================<br/>		
		</c:forEach>
	</c:if>
</ul>
<center>
<div align="center" style="position: relative;">
<ul style=" height:100%;
  		width:800PX;
        list-style-type:none;
        margin:0 auto">
	<li class="page_li" style="line-height:40px;float:left;"> 
	<a href="/record/showRecord.action?pageNum=1&flag=1"><button class="page_btn" style="width:100px">首页</button></a>
	</li>
	<li class="page_li" style="line-height:40px;float:left;">	 
    <c:if test="${pageNum==1}">
		<button class="page_btn" style="width:100px" id="prePage">上一页</button>
	</c:if> 
	<c:if test="${pageNum!=1}">
		<a href="/record/showRecord.action?pageNum=${pageNum-1}&flag=1"><button class="page_btn" style="width:100px" id="prePage">上一页</button></a>
	</c:if>   
    </li>
    <li class="page_li" style="line-height:40px;float:left;">
   		<c:if test="${pageNum==totalPage}">
				<button class="page_btn" style="width:100px" id="sufPage">下一页</button>
		</c:if> 
		<c:if test="${pageNum!=totalPage}"><a href="/record/showRecord.action?pageNum=${pageNum+1 }&flag=1"><button class="page_btn" style="width:100px" id="sufPage">下一页</button></a>
		</c:if>					
	 </li>
	 <li class="page_li" style="line-height:40px;float:left;">
        <a href="/record/showRecord.action?pageNum=${totalPage}&flag=1"><button class="page_btn" style="width:100px">尾页</button></a>
   	 </li>
   	 <li class="page_li" style="line-height:40px;float:left;">
      <center> <input type="text" style="width: 25px;height: 30px;" id="goPage" value="${pageNum}">/${totalPage}<button class="page_btn" style="width:30px" onclick="goGoGo();">Go</button></center> 
   	 </li>
</ul>
</div><br/><br/><div align="right"> 
<br></div><a href="/record/recordIsAdd.action?flag=1"><button class="page_btn" style="width:100px">添加心情记录</button></a>
</center> 
</body>
</html>