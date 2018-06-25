<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>甜美回忆（照片墙）</title>
<head>
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
    .picDiv{  
    padding-bottom:100%;  
}  
.picDiv img{  
    position:absolute;  
    top:0;  
    bottom:0;  
    left:0;  
    right:0;  
    max-width:100%;  
    margin:auto;  
}  
</style>
<script type="text/javascript" src="../util/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
function deleteAgain(){
	if (window.confirm("确认删除吗?")) {
		return true;
	} else {
	return false;
	}
	}
function goGoGo(){
	var goId=document.getElementById("goPage").value;
	var totlPage="${totalPage}";
	if(goId<0||goId>totlPage){
		alert("页码错误,请重新输入！");
	}else{
	window.location.href="/photo/selectPhotoPathInThum.action?pageNum="+goId;}
}
    </script>
</head>
<body>
<center>
<br/>
<div style="width: 1200px;height: 460px">
<c:if test="${not empty photoThumList}">
	<c:forEach items="${photoThumList}" var="c">
	<div style="float: left;" >
	<div style="width: 230px;height: 220px" id="picDiv">
	 <a href="/photo/showPhotoDetail.action?id=${c.id }">
	 <img id="${c.id}" src="/${ c.photoPath}"  width=80% height=80% BORDER="0" ALT=""/>
	 </a><br/>
	 <a href="/photo/deletePhotoById.action?id=${c.id}">
	 <input class="page_btn" style="width:100px" type="button" value="点击删除" onclick='return deleteAgain();'>
	 </a>	
	 </div>	
    	<!-- <p style="width: 30px;height: 350px;float: left;"></p>	 -->		
	 </div>	
		</c:forEach>
	</c:if> 	
</div> 	
    </center><br/>
    <center>
<div align="center" style="position: relative;">
<ul style=" height:100%;
  		width:800PX;
        list-style-type:none;
        margin:0 auto">
	<li class="page_li" style="line-height:40px;float:left;"> 
	<a href="/photo/selectPhotoPathInThum.action?pageNum=1"><button class="page_btn" style="width:100px">首页</button></a>
	</li>
	<li class="page_li" style="line-height:40px;float:left;">	 
    <c:if test="${pageNum==1}">
		<button class="page_btn" style="width:100px" id="prePage">上一页</button>
	</c:if> 
	<c:if test="${pageNum!=1}">
		<a href="/photo/selectPhotoPathInThum.action?pageNum=${pageNum-1}"><button class="page_btn" style="width:100px" id="prePage">上一页</button></a>
	</c:if>   
    </li>
    <li class="page_li" style="line-height:40px;float:left;">
   		<c:if test="${pageNum==totalPage}">
				<button class="page_btn" style="width:100px" id="sufPage">下一页</button>
		</c:if> 
		<c:if test="${pageNum!=totalPage}"><a href="/photo/selectPhotoPathInThum.action?pageNum=${pageNum+1 }"><button class="page_btn" style="width:100px" id="sufPage">下一页</button></a>
		</c:if>					
	 </li>
	 <li class="page_li" style="line-height:40px;float:left;">
        <a href="/photo/selectPhotoPathInThum.action?pageNum=${totalPage}"><button class="page_btn" style="width:100px">尾页</button></a>
   	 </li>
   	 <li class="page_li" style="line-height:40px;float:left;">
      <center> <input type="text" style="width: 25px;height: 30px;" id="goPage" value="${pageNum}">/${totalPage}<button class="page_btn" style="width:30px" onclick="goGoGo();">Go</button></center> 
   	 </li>
</ul>
</div><br/><br/><div align="right"> 
<br></div> <a href="/upload/upLoad.action"><input class="page_btn" type="button" style="width:100px" value="我要上传" /></a>  
</center>
</body>
</html>