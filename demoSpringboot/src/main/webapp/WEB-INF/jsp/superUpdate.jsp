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
</style>
</head>
<script type="text/javascript" src="/util/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
function selectAll(e, itemName)
    {    
        var aa = document.getElementsByName(itemName);    //获取全选复选框
        for (var i=0; i<aa.length; i++){
         aa[i].checked = e.checked;    //改变所有复选框的状态为全选复选框的状态
        }
    }

    function checkItem(e, allName)
    {
        var all = document.getElementsByName(allName)[0];    //获取全选复选框
        if(!e.checked){
            //没被选中全选复选框置为false;
            all.checked = false;
        } else {
            //选中，遍历数组
            var aa = document.getElementsByName(e.name);
            for (var i=0; i<aa.length; i++)
                //只要数组中有一个没有选中返回。假如所有的都是选中状态就将全选复选框选中;
             if(!aa[i].checked) return;    
            all.checked = true;
        }
    }
function check(){
 var checkeds=$("#checkbox:checked:checked");
 if(checkeds.length==0){
 	alert("请选择要删除的内容！");
 	return false;
 }
 var checkedId="";
 for(var i=0;i<checkeds.length;i++){
 	var id=checkeds[i].value;
 	checkedId+=id+",";
 }
checkedId=checkedId.substring(0,checkedId.length-1);
$.ajax({
			url:"/record/deleteRecordById.action",
			type:"post",
			data:{"id":checkedId},
			success:function(result){				
			window.location.href="/jsp/"+result+".jsp";
			},
			error:function() { 
				alert("请重新提交");}
		});
		return true;
}
</script>
<body> 
<center>

	<c:if test="${not empty recordList}">
	<table  align="center" width="85%" id="tbody">
			<tr>
				<td><input type="checkbox" style="width: 20px; height: 20px;" name="selectAll" onclick="selectAll(this,'checkbox')">是否全选</td>
				<td><span>记录作者</span></td>
				<td><span>记录内容</span></td>
				<td><span>记录时间</span></td>
				<td><span>是否修改</span></td>
			</tr>
		<c:forEach items="${recordList}" var="c">	
				<tr>
					<td><input type="checkbox" style="width: 20px; height: 20px;" id="checkbox" name="checkbox" value="${c.id }" onclick="checkItem(this, 'selectAll')"></td>
					<td>${c.name }</td>
					<td>${c.message}</td>
					<td><fmt:formatDate value="${c.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td><a href="/messageBoard/showMessageBoardById.action?id=${c.id } "><input  type="button"  value="点击修改"/></a></td>
				</tr>		
		</c:forEach>
		</table>
	</c:if>
</center>
<center>
		<br>
	<ul style=" height:100%;
  		width:800PX;
        list-style-type:none;
        margin:20 auto">
		<li class="page_li" style="line-height:40px;float:left;">
			<input type="button"   class="page_btn" style="width:200px" id="prePage" onclick="return check()" value="删除所有选中项">
		</li>
	</ul>
</center> 
</body>
</html>