<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>爱之家</title>
<script type="text/javascript" src="../util/jquery-3.3.1.min.js"></script>
<script src="../util/scrollPic.js"></script>
<style type="text/css">
body {
	margin: 0;
	padding: 0;
	color: #262e34;
	font-family: Tahoma, Geneva, sans-serif;
	font-size: 13px;
	line-height: 1.5em; 
	background-color: #262e34;
	background-image: url(../imags/images/tooplate_body.jpg);
	background-position: center top;
	background-repeat: no-repeat;
}

a, a:link, a:visited { 
	color: #1b85d7; 
	font-weight: normal; 
	text-decoration: none; 
	font-size: 12px; 
}

a:hover { 
	text-decoration: underline; 
}

a.more { 
	clear: both;  
	text-decoration: none; 
	padding-left: 20px; 
	font-weight: 700; 
	font-size: 14px; 
	background: url(../imags/images/tooplate_arrow.png) no-repeat scroll 0 6px; 
	color: #1b85d7; 
}

a.more:hover { 
	color: #d99e01; 
}

p { 
	margin: 0 0 10px 0; 
	padding: 0; 
}

img { 
	border: none; 
}

blockquote { 
	font-style: italic; 
	margin: 0 0 15px 10px;
}

cite { 
	font-weight: bold; 
	color:#1b85d7; 
	font-size: 12px; 
}

cite span { 
	color: #1b85d7;
}

em { color: #ff9e05; }

h1, h2, h3, h4, h5, h6 { color: #063c65; font-weight: normal; }
h1 { font-size: 34px; margin: 0 0 20px; padding: 5px 0 }
h2 { font-size: 28px; margin: 0 0 25px; padding: 5px 0; }
h3 { font-size: 22px; margin: 0 0 15px; padding: 0; }
h4 { font-size: 12px; color: #666; margin: 0 0 15px; padding: 0; }
h4 span { display: block; color: #063c65;  font-size: 18px }
h5 { font-size: 16px; margin: 0 0 10px; padding: 0;  }
h6 { font-size: 14px; margin: 0 0 5px; padding: 0; }

.cleaner { clear: both }
.h10 { height: 10px }
.h20 { height: 20px }
.h30 { height: 30px }
.h40 { height: 40px }
.h50 { height: 50px }
.h60 { height: 60px }

.float_l { float: left }
.float_r { float: right }

.image_wrapper { 
	display: inline-block; 
	position: relative; 
	width: 804px; 
	height: 154px; 
}

.image_wrapper span { 
	position: absolute;  
	bottom: 0; 
	right: 0; 
	background:url(../imags/images/tooplate_image_frame.png) no-repeat; 
	width: 1204px; 
	height: 154px; 
}

.image_wrapper img { 
	position: absolute; 
	width: 1000px; 
	height: 150px; 
}

.image_fl { 
	float: left; 
	margin: 3px 30px 0 0; 
}

.image_fr { 
	float: right; 
	margin: 3px 0 0 30px; 
}

.tooplate_list { 
	margin: 20px 0 20px 15px; 
	padding: 0; 
	list-style: none; 
}

.tooplate_list li { 
	color:#ff9e05; 
	margin: 0; 
	padding: 0 0 5px 20px; 
	background: url(../imags/images/tooplate_list.png) no-repeat scroll 0 7px;  
}

.tooplate_list li a { 
	color: #ff9e05; 
	font-weight: normal; 
	font-size: 12px; 
	text-decoration: none; 
}

.tooplate_list li a:hover { 
	text-decoration: underline; 
}

#tooplate_wrapper {
	width: 1320px;
	padding: 30px;
	margin: 0 auto;
	background: url(../imags/images/tooplate_body.jpg) top center no-repeat;
}

#tooplate_header {
	width: 1220px;
	height: 50px;
	padding: 0 340px 0 20px;
	background: url(../imags/images/tooplate_header1.png) no-repeat; 
}

#site_title { 
	float: left; 
}

#site_title h1 { 
	margin: 0; 
	padding: 0; 
}

#site_title h1 a { 
	display: block; 
	width: 180px; 
	height: 54px; 
	color: #fff; 
	text-indent: -10000px; 
	background: url(../imags/images/tooplate_logo.png) no-repeat center center; 
}

/* menu */
#tooplate_menu1 {
	float: right;
	margin: 17px;
	padding-left: 20px;
}
#tooplate_menu {
	float: right;
	margin: 0 auto;
}

#tooplate_menu ul {
	margin: 0;
	padding: 0;
	list-style: none;
}

#tooplate_menu ul li {
	padding: 0;
	margin: 0;
	display: inline;
}

#tooplate_menu ul li a {
	float: left;
	display: block;
	width: 100px;
	height: 30px;
	padding-top: 24px;
	margin-left: 10px;
	font-size: 14px;
	color: #333;
	text-align: center;
	text-decoration: none;
	font-weight: 400;
	outline: none;
	border: none;
	background: url(../imags/images/tooplate_menu.jpg) bottom no-repeat;
}

#tooplate_menu ul li a:hover, #tooplate_menu ul .current {
	height: 34px;
	padding-top: 20px;
	color: #fff;
	background:  url(../imags/images/tooplate_menu_hover.jpg) bottom no-repeat;
}

/* end of menu */

#tooplate_middle { 
	clear: both;
	width: 1320px;
	height: 150px;
	padding: 10px -10px 10px 10px;
	overflow: hidden;
	background: url(../imags/images/tooplate_slider1.png) no-repeat bottom center;
}

#tooplate_content {
	clear: both;
	position: relative;
	width: 1320px;
	height:700px;
	padding: 10px 10px 10px 0px;
	background: url(../imags/images/tooplate_content_repeat1.png) repeat-y;
}

#tooplate_content span.content_top { 
	position: absolute; 
	background: url(../imags/images/tooplate_content_top.jpg) no-repeat; 
	width: 1320px; 
	height: 20px; 
	top: 0; 
	left: 0; 
}

.box_w220 { 
	float: left;
	width: 220px;
	margin-right: 30px;
}

.box_w330 {
	width: 330px;
}

.rmb { 
	margin-right: 0; 
}

.gallery_box {
	float: left;
	width: 220px;
	margin: 0 30px 30px 0;
}

.post_box { 
	clear: both; 
	margin-bottom: 30px; 
	padding-bottom: 20px; 
	border-bottom: 1px dashed #ccc; 
}

.post_header { 
	border-top: 3px solid #999; 
	border-bottom: 1px solid #ccc; 
	padding: 5px; 
	margin-bottom: 20px; 
}

.post_box h2 { 
	font-size: 34px; 
	margin-bottom: 30px; 
}

.post_box p.post_meta { 
	margin-bottom: 0; 
}

.post_box .comment {
	float: left;
	width: 70px;
    height: 82px;
	line-height: 70px;
	margin-right: 40px;
	color: #fff;
	text-align: center;
	background: url(../imags/images/tooplate_comment.jpg) no-repeat top left;
}

.post_box .comment a { 
	color: #fff; 
	font-size: 34px; 
	text-decoration: none; 
}

.post_box .comment a:hover { 
	color: #000; 
}

.gallery_box h6 { 
	font-weight: bold; 
}

.gb_rmb { 
	margin-right: 0; 
}

.gallery_img { 
	display: inline-block; 
	position: relative; 
	width: 220px; 
	height: 110px; 
	margin-bottom: 10px; 
}

.gallery_img span { 
	position: absolute;  
	bottom: 0; 
	right: 0; 
	background: url(../imags/images/tooplate_image_frame_220_110.png) no-repeat; 
	width: 220px; 
	height: 110px; 
}

.gallery_img img { 
	position: absolute; 
	width: 215px; 
	height: 105px; 
}

#contact_form { 
	padding: 0; 
	width: 330px; 
}

#contact_form form { 
	margin: 0px; 
	padding: 0px; 
}

#contact_form form .input_field { 
	width: 250px; 
	padding: 5px; 
	color: #333; 
	border: 1px solid #d7d9d2;  
	background: #f9ffec;
	font-family: Tahoma, Geneva, sans-serif;
	font-size: 12px;
}

#contact_form form label { 
	display: block; 
	width: 100px; 
	margin-right: 10px; 
	font-size: 13px; 
}

#contact_form form textarea { 
	width: 320px; 
	height: 200px; 
	padding: 5px; 
	border: 1px solid #d7d9d2;  
	background: #f9ffec; 
	color: #333;
	font-family: Tahoma, Geneva, sans-serif;
	font-size: 12px;
}

#contact_form form .submit_btn {
	margin: 10px 0px;
	padding: 5px 14px;
	border: 1px solid #d7d9d2;  
	background: #f9ffec; 
	font-size: 14px; 
}

#tooplate_footer {
	clear: both;
	width: 1320px;
	height: 47px;
	text-align: center; 
	color: #000;
	padding: 10px -10px 0 40px;
	background: url(../imags/images/tooplate_footer1.png) no-repeat top;
}
#tooplate_footer a { color: #000; text-decoration: none }
    .scroll_left{width:20px;height:150px;background:url(../imags/images/btn_left.jpg) no-repeat;float:left;}
    .scroll_right{width:20px;height:150px;background:url(../imags/images/btn_right.jpg) left no-repeat;float:left;}
    .pic{width:1280px;height:150px;float:left;margin-top: -6px;}
    .pic ul{display:block;height:150px;}
    .pic li{float:left;display:inline;width:280px;height:150px;text-align:center;}
</style>
<script type="text/javascript">
window.onload = function(){
    scrollPic();
   /*  $.ajax({
		url:"/upload/selectPhotoPath.action",
			type:"post",
			data:{},
			success:function(result){
			var json=eval(result);
			var s = "";
			 for (var i = 0; i<json.length; i++)  {
			 //var sysName="${pageContext.request.contextPath}";
			 //sysName=sysName.substring(1);
			 var photoPath=json[i].photoPath;
			 //var index=photoPath.indexOf(sysName);
			 //var photoPath=photoPath.substring(index);
			 s+="<li><a href='#' target='menuFrame' title=''><img src='../"+photoPath+"'width='280px' height='150px' alt='' /></a></li>";
             }
             $("#photoScroll").append(s);	
			},
		}); */
    $.ajax({
		url:"/photo/selectPhotoPath.action",
			type:"post",
			data:{},
			success:function(result){
			var json=eval(result);
			var s = "";
			 for (var i = 0,j=0; i<json.length; i++,j++)  {
			 var photoPath=json[i].photoPath;
			 var id=json[i].id;
			 s="<li><a href='/photo/showPhotoDetail.action?id="+id+"' target='menuFrame'><img src='../"+"/"+photoPath+"'  width='200px' height='150px' alt='' /></a></li>";
			 $("#photoScroll").append(s);
			 if(i==json.length-1){
				i=-1;				
			}
			if(j==10){
				j--;
				var lid=document.getElementById("photoli"+(k+1));
				 $("#photoScroll").removeChild(lid);//
			}
			
			 
			 }            	
			},
		}); 
		
	};
	
function scrollPic() {
    var scrollPic = new ScrollPic();
    scrollPic.scrollContId   = "scrollPic"; //内容容器ID
    scrollPic.arrLeftId      = "RightArr";//左箭头ID
    scrollPic.arrRightId     = "LeftArr"; //右箭头ID

    scrollPic.frameWidth     = 1280;//显示框宽度
    scrollPic.pageWidth      = 260; //翻页宽度

    scrollPic.speed          = 50; //移动速度(单位毫秒，越小越快)
    scrollPic.space          = 1280; //每次移动像素(单位px，越大越快)
    scrollPic.autoPlay       = true; //自动播放
    scrollPic.autoPlayTime   = 1; //自动播放间隔时间(秒)

    scrollPic.initialize(); //初始化
}
</script>
</head>
<body>
<div id="tooplate_wrapper">
	<div id="tooplate_header">	
        <div id="site_title"><h1><a href="/login/homepage.actionp">爱之家</a></h1></div>
       		 <div id="tooplate_menu1"><a href="/login/personal.action" target="menuFrame" style="color:yellow;font-size: 15px ">${user.name},欢迎你！</a>&nbsp;&nbsp;<a href="/login/logout.action" style="color:red;font-size: 15px ">注销登陆</a></div>
              <div id="tooplate_menu">
            <ul>
                <li><a href="/record/showRecord.action?flag=1" target="menuFrame">点滴记录</a></li>
                <li><a href="/photo/selectPhotoPathInThum.action" target="menuFrame">甜美记忆</a></li>
                <li><a href="/messageBoard/showMessageBoard.action?flag=2" target="menuFrame">留言板</a></li>
                <li><a href="/account/showAccount.action" target="menuFrame">日常开支</a></li>
                <li><a href="/login/personal.action" target="menuFrame">账户管理</a></li>
                <li><a href="/register/friendlyLink.action"  target="menuFrame">友情链接</a></li>
            </ul>  	
        </div> <!-- end of tooplate_menu -->       
    </div> <!-- end of forever header -->
    
    <div id="tooplate_middle">
        <div class="scroll_left" id="LeftArr"></div>
        <div class="pic" id="scrollPic">
        <ul id="photoScroll">
		 </ul>
        </div>
       <div class="scroll_right" id="RightArr"></div>
    </div> <!-- end of middle -->   
    <div id="tooplate_content">
<iframe src="/record/showRecord.action?flag=1" name="menuFrame" width="100%" height="100%" frameborder="0"  marginwidth="0" marginheight="0" scrolling="yes"></iframe><br>
</div> <!-- end of content -->   
    <div id="tooplate_footer">
    	----------------------------------我是有下线的----------------------------------<br>
    	    	Copyright © 2018 <a href="#">FZW</a>
        <div class="cleaner"></div>
	</div>
</div>
</body>
</html>