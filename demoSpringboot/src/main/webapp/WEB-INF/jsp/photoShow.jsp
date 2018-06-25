<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>爱之家</title>
<style type="text/css">
#tooplate_middle { 
	clear: both;
	width: 1000px;
	height: 750px;
	padding: 10px -10px 10px 10px;
	overflow: hidden;
}
    .scroll_left{width:64px;height:96px;background:url(../imags/images/feature_left.gif) no-repeat;
    		float:left;}
    .scroll_right{width:64px;height:96px;background:url(../imags/images/feature_right.gif) left no-repeat;
    		float:right;} 
.scrollPic{  
    padding-bottom:100%;  
}  
.scrollPic img{  
    position:absolute;  
    top:0;  
    bottom:0;  
    left:0;  
    right:0;  
    max-width:100%;  
    margin:auto;  
}  
</style>
</head>
<body> 
<center> 
<h2><font style="楷体" color="yellow">${msg}</font></h2>
<div id="tooplate_middle" style="float: left;">
	 	<p style="width: 150px;height: 600px;float: left;"></p>
        <div style="margin: auto;position:relative;">
       <a href="/photo/selectSidePhoto?id=${photoUpLoad.id}&flag=0"> <button class="scroll_left" style="position: absolute;top: 260px; left: 80px; bottom: 0; right: 0;"></button></a>
        </div>        
        <div class="pic" id="scrollPic" style="width: 700px;height: 600px">
         <center> 	 <img id="${photoUpLoad.id}" src="/${photoUpLoad.photoPath}"   BORDER="0" ALT=""/></center>
         	<!--  <img alt="" src="../imags/images/bg-featurebox.jpg"  width="800px" height="100px"> -->
        </div>
       <div style="margin: auto;float: left">
         <a href="/photo/selectSidePhoto?id=${photoUpLoad.id}&flag=1"> <button  class="scroll_right" style="position: absolute;top: 260px; left: 1010px; bottom: 0; right: 0;"></button></a>
       </div>
    </div> <!-- end of middle --> 
</center>   
    
</body>
</html>