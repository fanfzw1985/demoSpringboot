<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta  http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询余额</title>
<script type="text/javascript" src="../util/My97DatePicker/WdatePicker.js"></script>
<style type="text/css">
table{
    margin:10px auto;
    border-right:1px solid blue;border-bottom:1px solid blue
}

td{
    text-align:center;
    font-size:20px;
    padding:0 5px;
    border-left:1px solid blue;border-top:1px solid blue;
    width:60px; height:30px;
}
 .button1{
 		border-radius:4px;
        border:1px solid #e5e9ef;
        background:#EECBAD;
        text-align:center;
        width:150px;
        height:50px;
        line-height: 8px;
        outline:0;
        font-size: 20px;
}
.button1:hover{
        border:1px solid #4f90fb;
        color:#4f90fb;
    }
span{
font-size: 20px;
}
</style>
</head>
<script type="text/javascript" src="../util/jquery-3.3.1.min.js"></script>
<body>
<center>
<c:if test="${empty accountList}">
		无日常开销信息！
	</c:if>
<ul style=" height:100%;
  		width:800PX;
        list-style-type:none;
        margin:0 auto">
<li class="page_li" style="line-height:40px;float:left;">
<button class="page_btn" style="width:200px" id="prePage"><span>本月收入：${accountSelect.sumFlag1 }</span></button></li>
<li class="page_li" style="line-height:40px;float:left;">
<button class="page_btn" style="width:200px" id="prePage"><span>本月支出：${accountSelect.sumFlag2 }</span></button></li>
</ul>
<br/><br/>
	<c:if test="${not empty accountOverList}">
	<table  align="center" width="80%" height="50%">
			<tr>
				<td style="width: 50% "><span>账户分类</span></td>
				<td><span>账户金额（元）</span></td>
			</tr>
		<c:forEach items="${accountOverList}" var="c">	
				<tr>
					<td>${c.accountName }</td>
					<td>${c.sum }</td>
				</tr>		
		</c:forEach>
		</table>
	</c:if>
	<a href="/account/showAccount.action"><input type="button" value="回到明细" class="button1"></a>
</center>
</body>
</html>