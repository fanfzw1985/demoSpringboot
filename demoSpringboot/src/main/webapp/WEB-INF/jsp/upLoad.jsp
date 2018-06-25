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
.bar {  
    height: 18px;  
    background: blue;  
} 
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/util/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/util/jquery-ui.js"></script>
<script type="text/javascript" src="../util/jquery.fileupload.js"></script>
<script type="text/javascript" src="../util/jquery.iframe-transport.js"></script> 
<script type="text/javascript">
 $(function () {  
    $('#multiFiles').fileupload({          
        url:"/upload/up.action",  
        dataType: 'json',  
        progressall: function (e, data) {  
            var progress = parseInt(data.loaded / data.total * 100, 10); 
            $('#progress .bar').text('已经上传：'+progress+'%');
            $('#progress .bar').css(  
                'width',  
                progress + '%'  
            ); 
        },  
        add: function (e, data) {  
            data.context = $('#upBtn').click(function () {  
                    data.context = $('<p/>').text('上传中，请等待...').appendTo(document.body);  
                    data.submit();  
                });  
        },  

       done: function (e, data) {
            if(data.eval()=="success"){ 
            	
             $.each(data.files, function (index, file) {
    			data.context=$('<p/>').text( file.name+'上传完成').appendTo(document.body);
             });
             }
             },  
        change : function (e, data) {  
            $.each(data.files, function (index, file) {  
                data.context=$('<p/>').text('选择文件: ' + file.name).appendTo(document.body);
            });  
        }
    });  
});  
/* function fileUpload(){      
    var formData = new FormData();  
    formData.append("file", $("#multiFiles")[0].files[0]);      
    $.ajax({  
        url: "/upload/up.action",  
        type: "POST",  
        cache: false,  
        data: formData,  
        processData: false,  
        contentType: false  
    }).done(function(res) { alert("成功"); 
    }).fail(function(res) {alert("失败");});  
}  */
/* function fileUpload() {
    var formData = new FormData();  
    formData.append("file", $("#multiFiles")[0].files[0]); 
	 $.ajax({
	      url:"/upload/up.action",
	      type:"post",
	      data:formData,
	      success:function(result){	     
	    		alert("上传成功");
	    		}, 
	      error:function() { 
	    	alert("系统错误，请重新上传 ！");
	      }
	      });
} */
 </script>
</head>
<body>
<center>
${msg}
	<h2>注意：</h2>可以一次上传多张，但上传之后请重新刷新页面再进行下一轮上传，谢谢！<br><br><br>
	请选择上传图片：<input type="file" id="multiFiles" name="multiFiles"><input type="button" id="upBtn" value="上传" onclick="fileUpload()">
	<div id="progress" style="width: 30%">  
    	<div class="bar" style="width: 0%;"></div>  
	</div> 
</center> 
</body>
</html>