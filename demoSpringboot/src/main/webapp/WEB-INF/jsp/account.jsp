<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>日常开支</title>
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
        background:#EEEED1;
        text-align:center;
        width:50px;
        height:30px;
        line-height: 8px;
        outline:0;
}
.button1:hover{
        border:1px solid #4f90fb;
        color:#4f90fb;
    }
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
span{
font-size: 20px;
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
<script type="text/javascript" src="../util/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="../util/My97DatePicker/WdatePicker.js"></script>
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
	window.location.href="/account/showAccount.action?pageNum="+goId;}
}
function goGoGo1(){
	var goId=document.getElementById("goPage").value;
	var totlPage=$("#goSpanId").text();
	if(goId<0||goId>totlPage){
		alert("页码错误,请重新输入！");
	}else{
	selectByAll(goId);
}}
//yyyy-MM-dd HH:mm:SS
function getDateTime(date) {
    var year = date.getFullYear();
    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
    var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate(); 
    var hh = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
    var mm = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
    var ss = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
    return year + "-" + month + "-" + day + " " + hh + ":" + mm + ":" + ss;
}
function selectByAll(pageNum) {
	 $.ajax({
	      url:"/account/selectByAll.action?pageNum="+pageNum,
	      type:"post",
	      data:{"flag":$("#flag").val(),"item":$("#item").val(),"account":$("#account").val(),"time1":$("#time1").val(),"time2":$("#time2").val()},
	      success:function(result){
	      if(result==null){
	      	alert("没有数据，请重新查询！");
	      }else{
	       $("#detailTable tr:gt(0)").remove();//删除之前的数据
	       $("#pageUl li").remove(); 
	       		var json=result.accountList;
                var s = "";
                var pageUl="";
				 for (var i = 0; i<json.length; i++)  {
				 var flag1="";
				 if(json[i].flag==1){
				 flag1="收入";
				 }else{flag1="支出";}
				 s+= "<tr><td>" + json[i].item + "</td><td>"  + json[i].account + "</td><td>"  + json[i].money + "</td>"
                    + "<td>" + json[i].item_desc + "</td><td>"+flag1 +"</td><td>"+getDateTime(new Date(json[i].time))+"</td><td><a href='/account/updateAccount.action?id="+json[i].id+"&flag="+json[i].flag+"'><input type='button' value='修改' class='button1'></a>"+
                    "<a href='/account/deleteAccountById.action?id="+json[i].id+"'>|<input type='button' value='删除' onclick='return deleteAgain();' class='button1'></a>"
                    +"</td></tr>";}
                $("#detailTable").append(s);
                var pageNum = result.pageNum;
                var totalPage = result.totalPage;
                if(pageNum==1){
                	var prePage="<button class='page_btn' style='width:100px' id='prePage'>上一页</button>";
                }else{
                   var pageNum1=Number(pageNum)-1;
                	var prePage="<button class='page_btn' onclick='selectByAll("+pageNum1+");' style='width:100px' id='prePage'>上一页</button>";
                }
                if(pageNum==totalPage){
                	var sufPage="<button class='page_btn' style='width:100px' id='sufPage'>下一页</button>";
                }else{
               		 var pageNum2=Number(pageNum)+1;
                	var sufPage="<button class='page_btn' onclick='selectByAll("+pageNum2+");' style='width:100px' id='sufPage'>下一页</button></a>";
                }
                pageUl+="<li class='page_li' style='line-height:40px;float:left;'><button class='page_btn' onclick='selectByAll(1);' style='width:100px'>首页</button></a></li>"+
            	"<li class='page_li' style='line-height:40px;float:left;'>"+prePage+	 
                "</li>"+
                "<li class='page_li' style='line-height:40px;float:left;'>"+sufPage+
               		"</li>"+
            	" <li class='page_li' style='line-height:40px;float:left;'>"+
                   " <button class='page_btn' onclick='selectByAll("+totalPage+");' style='width:100px'>尾页</button></a></li>"+
               	 " <li class='page_li' style='line-height:40px;float:left;'>"+
                 " <center> <input type='text' style='width: 25px;height: 30px;' id='goPage' name='goPage' value='"+pageNum+"'>/<span id='goSpanId'>"+totalPage+"</span><button class='page_btn' style='width:30px' onclick='goGoGo1();'>Go</button></center> "+
               	 "</li>";
                $("#pageUl").append(pageUl);
	      };}, 
	      error:function() { 
	    	alert("系统错误，请重新查询！");
	      }
	      });
}
function itemChang() {
	if($("#flag").val()==1){
		$("#item option:gt(0)").remove();
		$("#item").append("<option value='工资收入'>------工资收入------</option><option value='资金收入'>------奖金收入------</option><option value='其他收入'>------其他收入------</option>");
	}else if($("#flag").val()==2){
		$("#item option:gt(0)").remove();
		$("#item").append("<option value='食品酒水'>------食品酒水------</option>"+
				"<option value='日常家居'>------日常家居------</option>"+
				"<option value='衣服服饰'>------衣服服饰------</option>"+
				"<option value='医疗保健'>------医疗保健------</option>"+
				"<option value='人情往来'>------人情往来------</option>"+
				"<option value='交流交通'>------交流交通------</option>");
	}
}
function flagChang() {
	if($("#flag").val()==0){
		alert("请先选择'收入/开支',再选择事项分类！");
	}
}
</script>
<body> 
<center>
<h1><font style="楷体" color="yellow">${msg}</font></h1></center> 

	<c:if test="${empty accountList}">
		无日常开销信息！
	</c:if>
<input type="text"  readonly="readonly" class="page_btn"  style="width:240px;font-size: 22px;height: 25px;margin-left: 100px;" id="prePage" value="本月收入：${accountSelect.sumFlag1 }">
<input type="text"  readonly="readonly" class="page_btn"  style="width:240px;font-size: 22px;height: 25px;" id="prePage" value="本月支出：${accountSelect.sumFlag2 }">
	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 20px;">收入/开支:</span>&nbsp;&nbsp;<select name="flag" ID="flag" style="font-size: 18px;" onchange="itemChang();">
	 	<option value="0">------请选择------</option>
		<option value="1">------日常收入------</option>
		<option value="2">------日常开支------</option>
		</select>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 20px;">事项分类:</span>&nbsp;&nbsp;<select name="item" ID="item" style="font-size: 18px;" onclick="flagChang();">
		<option value="">------请选择------</option>
		</select><br>
	<div style="margin-left: 100px;font-size: 20px;margin-top: 10px">
	账户分类:&nbsp;&nbsp;<select name="account" ID="account" style="font-size: 18px;">
		<option value="">------请选择------</option>
		<option value="现金账户">------现金账户------</option>
		<option value="支付宝">-------支付宝-------</option>
		<option value="微信账户">------微信账户------</option>
		<option value="银行存款">------银行存款------</option> 
		</select>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;时间：从<input id="time1" style="vertical-align:middle;" class="Wdate" type="text" onclick="WdatePicker({maxDate:'#F{$dp.$D(\'time2\')||\'2099-10-01\'}'})"/>到 
		<input id="time2" style="vertical-align:middle;" class="Wdate" type="text" onclick="WdatePicker({minDate:'#F{$dp.$D(\'time1\')}',maxDate:'2099-10-01'})"/>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" onclick="selectByAll(1);" value="查询" style="font-size: 18px;width: 100px" class="button1">
	</div>
	<c:if test="${not empty accountList}">
	<table  align="center" width="85%" id="detailTable">
			<tr>
				<td><span>事项分类</span></td>
				<td><span>账户分类</span></td>
				<td><span>发生金额</span></td>
				<td><span>事件摘要</span></td>
				<td><span>收入/支出</span></td>
				<td><span>发生时间</span></td>
				<td><span>操作</span></td>
			</tr>
		<c:forEach items="${accountList}" var="c">	
				
					<tr>
					<td>${c.item }</td>
					<td>${c.account }</td>
					<td>${c.money}</td>
					<td>${c.item_desc }</td>
					<td><c:if test="${c.flag eq 1}">收入</c:if><c:if test="${c.flag eq 2}">支出</c:if></td>
					<td><fmt:formatDate value="${c.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td><a href="/account/updateAccount.action?id=${c.id}&flag=${c.flag}"><input type="button" value="修改" class="button1"></a>&nbsp;
						<a href="/account/deleteAccountById.action?id=${c.id}"><input type="button" value="删除" onclick="return deleteAgain();" class="button1"></a>
					</td>
					</tr>
						
		</c:forEach>
		</table>
	</c:if>

<center>
<div align="center" style="position: relative;" id="pageDiv">
<ul style=" height:100%;
  		width:800px;
        list-style-type:none;
        margin:0 auto" id="pageUl">
	<li class="page_li" style="line-height:40px;float:left;"> 
	<a href="/account/showAccount.action?pageNum=1"><button class="page_btn" style="width:100px">首页</button></a>
	</li>
	<li class="page_li" style="line-height:40px;float:left;">	 
    <c:if test="${pageNum==1}">
		<button class="page_btn" style="width:100px" id="prePage">上一页</button>
	</c:if> 
	<c:if test="${pageNum!=1}">
		<a href="/account/showAccount.action?pageNum=${pageNum-1}"><button class="page_btn" style="width:100px" id="prePage">上一页</button></a>
	</c:if>   
    </li>
    <li class="page_li" style="line-height:40px;float:left;">
   		<c:if test="${pageNum==totalPage}">
				<button class="page_btn" style="width:100px" id="sufPage">下一页</button>
		</c:if> 
		<c:if test="${pageNum!=totalPage}"><a href="/account/showAccount.action?pageNum=${pageNum+1 }"><button class="page_btn" style="width:100px" id="sufPage">下一页</button></a>
		</c:if>					
	 </li>
	 <li class="page_li" style="line-height:40px;float:left;">
        <a href="/account/showAccount.action?pageNum=${totalPage}"><button class="page_btn" style="width:100px">尾页</button></a>
   	 </li>
   	  <li class="page_li" style="line-height:40px;float:left;">
      <center> <input type="text" style="width: 25px;height: 30px;" id="goPage" value="${pageNum}">/${totalPage}<button class="page_btn" style="width:30px" onclick="goGoGo();">Go</button></center> 
   	 </li>
</ul>
</div><br/><br/><div align="right"> 
<br></div>
	<ul style=" height:100%;
  		width:800px;
        list-style-type:none;
        margin:0 auto">
	<li class="page_li" style="line-height:40px;float:left;">
		<a href="/account/account2.action"><button class="page_btn" style="width:200px" id="prePage"><span>我要记一笔</span></button></a></li>
	<li class="page_li" style="line-height:40px;float:left;">
		<a href="/account/reportForm.action"><button class="page_btn" style="width:200px" id="prePage"><span>查看更多报表</span></button></a></li>
	<li class="page_li" style="line-height:40px;float:left;"><a href="/account/selectOver.action"><button class="page_btn" style="width:200px" id="prePage"><span>查询账户余额</span></button></a></li>
	</ul>
</center> 
</body>
</html>