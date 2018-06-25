<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta  http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>收入、支出折线统计图</title>
<script type="text/javascript" src="../util/My97DatePicker/WdatePicker.js"></script>
<style type="text/css">
.img{
width:800px;
height:600px;
border: 0;
color:#DDDDFF;
} 
* {margin:0px; padding:0px;}
            .topDiv {position:absolute; top:6%; left:10%;margin-left:-20px; margin-top:-3px;}
            .div1 { float:center;}
           .div2 {top:5%; width:80px; height:100px; float:right;font-size:30px;}
div{
display:inline;}            
            
form{
font-size: 22px;
 float:left;margin-left:100px;
}
input[type="submit"]{
    background:#228B22;
    font-size:20px;
    font-weight:bold;
    width:120px;
    height:40px;
}
input[type="button"]{
    background:#228B22;
    font-size:20px;
    font-weight:bold;
    width:120px;
    height:40px;
}
input[type="text"]:read-only{
    border:#888484 solid 2px;
    background:#888484;
    font-weight:bold;  
    width:150px;
    height:50px; 
    font-size:40px;
}
input[type="text"]:hover{
    background:#EFD9AC;
}
</style>
</head>

<script type="text/javascript" src="../util/jquery-3.3.1.min.js"></script>
<script type="text/javascript">



function on(){
	
	$.ajax({
		url:"/account/reportFormAjax.action",
			type:"post",
			data:{"time":$("#time").val(),"_time":$("#_time").val()},
			success:function(result){
				
				if (result == null){
					alert("没有结果");
				}
				
				var ss  = result.split(",");
				
				//alert("结果为："+result);
				$("#intPage").val(ss[0]);
				$("#outPage").val(ss[1]);
				
			},
			error:function() { alert("异常"); }
		});
}






</script>
<body>
<div class="topDiv">
<div  style="text-align:center" class="div1">  
     <img src="${chartColumnURL}" >   
  </div>
<div class="div2">
 <form action="${pageContext.request.contextPath}/account/reportFormAjax.action">
查询时间段从：<br/><input type="text" name="time1" id="time1" style="width:220px;height:30px;;font-size: 25px" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',vel:'time'})"/>
      <img onclick="WdatePicker({el:'time1',dateFmt:'yyyy-MM-dd',vel:'time'})" src="../util/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="middle">
      <input id="time" name="time" type="hidden" /><br/>到：<br/><input type="text" name="time2" id="time2" style="width:220px;height:30px;;font-size: 25px"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',vel:'_time'})"/>
      <img onclick="WdatePicker({el:'time2',dateFmt:'yyyy-MM-dd',vel:'_time'})" src="../util/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="middle">
      <input id="_time" name="_time" type="hidden" /><br/>的总账 <br/><br/><center>	 <input type="button" value="点击查询" onclick="on();" id="selectAjax"></center><br/>
<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;查询结果</span> 
  <ul style=" height:100%;
  		width:120px;
        list-style-type:none; ">
  
<li class="page_li" style="line-height:40px;float:left;">
共计收入：<input class="page_btn" style="width:200px;height: 35px;font-size: 25px" id="intPage" readonly="readonly"></input>
</li>
<li class="page_li" style="line-height:40px;float:left;">
共计支出：<input class="page_btn" style="width:200px;height: 35px;font-size: 25px" id="outPage" readonly="readonly"/>
</li>
</ul>
 </form> 
  <center><a href="/account/showAccount.action"><input type="button" value="回到明细"></a> </center> 
</div>
</div>
</body>
</html>