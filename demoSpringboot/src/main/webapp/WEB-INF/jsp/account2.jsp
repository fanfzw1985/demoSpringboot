<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>日常开支</title>
<script type="text/javascript" src="../util/My97DatePicker/WdatePicker.js"></script>
<style>
div{
    background:#009FCC;
    font-size:24px;
    padding:5px;
    color:white;
}
form{
    background: #F8F8FF ;
    border:#357FC4 solid 1px;
    color:#575454;  
    width:520px;
    margin:20px auto;
    font-size:15px;
}
table{
    margin:10px auto;
}
a{
    text-decoration:none;
}
input[type="button"]{
    background:#228B22;
    color:white;
    font-size:15px;
    font-weight:bold;
    width:120px;
    height:40px;
}
input[type="submit"]{
    background:#228B22;
    color:white;
    font-size:15px;
    font-weight:bold;
    width:120px;
    height:40px;
}
td:first-child{
    text-align:right;
    padding:0 5px;
}
td:only-child{
    text-align:center;
    font-size:12px;
}
span:before{
    content:"* ";
    color:red;
}
input[type="text"]:read-only{
    border:#888484 solid 2px;
    background:#888484;
    font-weight:bold;   
}
input[type="text"]:hover{
    background:#EFD9AC;
}
</style>
<script type="text/javascript">  
function check(){
	var moneyReg=/^[0-9]{0}([0-9]|[.])+$/;
	var moneyNum=document.getElementById("money").value;
	var moneyResult=moneyReg.exec(moneyNum);
	var timeString=document.getElementById("time").value;
	if(moneyNum==0){alert("发生金额不能为零！");
	return false;}
	else if(!moneyResult){
	alert("发生金额必需为数字和小数点！");
	return false;
	}else if(timeString.length==0){
	alert("时间格式不正确且不能为空，正确格式为：2018-01-18 12:00:00 ");
	return false;
	}else{
	document.getElementById("formId").submit();
	return true;}
	}
function checkesed(){
	var moneyReg=/^[0-9]{0}([0-9]|[.])+$/;
	var moneyNum=document.getElementById("money").value;
	var moneyResult=moneyReg.exec(moneyNum);
	var timeString=document.getElementById("time").value;
	if(moneyNum==0){alert("发生金额不能为零！");
	return false;}
	else if(!moneyResult){
	alert("发生金额必需为数字和小数点！");
	return false;
	}else if(timeString.length==0){
	alert("时间格式不正确且不能为空，正确格式为：2018-01-18 12:00:00 ");
	return false;
	}else{
	var to=document.getElementById("formId");
	 	to.action="/account/accountAdd2.action?flag=2";
   		to.submit();
	return true;}
	}	
</script>  

</head>
<body>
<form action="/account/accountAdd.action?flag=2" method="post" id="formId">
<div>日常开支</div>
<table>
	<tr><td colspan="2" style="text-align:center;"><a href="/account/account1.action"><input type="button"  value="日常收入"   /></a></td>
	<td colspan="2" style="text-align:center;"><a href="/account/account2.action"><input type="button"  value="日常支出" /></a></td></tr>
	<tr><td>&nbsp;</td> </tr>
    <tr><td><span>事项分类</span></td><td><select name="item" ID="item">
		<option value="食品酒水">------食品酒水------</option>
		<option value="日常家居">------日常家居------</option>
		<option value="衣服服饰">------衣服服饰------</option>
		<option value="医疗保健">------医疗保健------</option>
		<option value="人情往来">------人情往来------</option>
		<option value="交流交通">------交流交通------</option>
		</select></td></tr>
		<tr><td>&nbsp;</td> </tr>
	<tr><td><span>账户分类</span></td><td><select name="account" ID="account">
		<option value="现金账户">------现金账户------</option>
	<option value="支付宝">-------支付宝-------</option>
	<option value="微信账户">------微信账户------</option>
	<option value="银行存款">------银行存款------</option>
	</select></td></tr>
	<tr><td>&nbsp;</td> </tr>
    <tr><td><span>发生金额</span></td><td>
   		 <input type="text" name="money" id="money" value="0.00" 
   		 onfocus="if (value =='0.00'){value ='';}" onblur="if (value ==''){value='0.00';}" /></td></tr>
      <tr><td>&nbsp;</td> </tr>
      <tr><td><span>发生时间</span></td><td><input type="text" name="time1" id="time1"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH：mm：ss',vel:'time'})"/>
      <img onclick="WdatePicker({el:'time1',dateFmt:'yyyy-MM-dd HH：mm：ss',vel:'time'})" src="../util/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="middle"><input id="time" name="time" type="hidden" /></td></tr>
	<tr><td>&nbsp;</td> </tr>
	<tr><td><span>事件摘要</span></td><td>
    	<input type="text" name="item_desc" id="item_desc" value="备注..."
    	onfocus="if (value =='备注...'){value ='';}" onblur="if (value ==''){value='备注...';}" /></td></tr>
    <tr><td>&nbsp;</td> </tr>
    <tr><td colspan="2" style="text-align:center;"><input type="button" name="submitbutton" value="保存" onclick="check()"/></td><td colspan="2"><input type="button" name="submitButton1" id="submitButton1" value="再记一笔" onclick="checkesed();" /></td></tr>
</table>
</form>
</body>
</html>