<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册页面</title>
      <script type="text/javascript" src="../util/jquery-3.3.1.min.js"></script>
      <style type="text/css" >
      /*-- reset --*/
      html,body,div,span,applet,object,iframe,h1,h2,h3,h4,h5,h6,p,blockquote,pre,a,abbr,acronym,address,big,cite,code,del,dfn,em,img,ins,kbd,q,s,samp,small,strike,strong,sub,sup,tt,var,b,u,i,dl,dt,dd,ol,nav ul,nav li,fieldset,form,label,legend,table,caption,tbody,tfoot,thead,tr,th,td,article,aside,canvas,details,embed,figure,figcaption,footer,header,hgroup,menu,nav,output,ruby,section,summary,time,mark,audio,video{margin:0;padding:0;border:0;font-size:100%;font:inheritvertical-;align:baseline}
      article, aside, details, figcaption, figure,footer, header, hgroup, menu, nav, section {display: block}
      ol,ul{list-style:none;margin:0px;padding:0px}
      blockquote,q{quotes:none}
      blockquote:before,blockquote:after,q:before,q:after{content:''content:none}
      table{border-collapse:collapseborder-spacing:0}
      /*-- start editing from here --*/
      a{text-decoration:none}
      .txt-rt{text-align:right}/* text align right */
      .txt-lt{text-align:left}/* text align left */
      .txt-center{text-align:center}/* text align center */
      .float-rt{float:right}/* float right */
      .float-lt{float:left}/* float left */
      .clear{clear:both}/* clear float */
      .pos-relative{position:relative}/* Position Relative */
      .pos-absolute{position:absolute}/* Position Absolute */
      .vertical-base{vertical-align:baseline}/* vertical align baseline */
      .vertical-top{vertical-align:top}/* vertical align top */
      nav.vertical ul li{display:block}/* vertical menu */
      nav.horizontal ul li{display: inline-block}/* horizontal menu */
      img{max-width:100%}
      /*-- end reset --*/
      body {
          font-family: 'Open Sans', sans-serif;
          background: url(../imags/regist.jpg)no-repeat center 0px;
          background-attachment: fixed;
      -webkit-background-size: cover;
      -moz-background-size: cover;
          background-size: cover;
      }
      h1 {
          font-size: 2.8em;
          text-align: center;
          color: #fff;
          font-weight: 100;
      }
      /*-- main --*/
      .main {
          padding: 2em 0 0;
      
      }
      .login-form {
          width: 25%;
          margin: 0.1em auto;
      background-color:#FFFAF0;
          background-size: cover
      }
      .login-form input[type="text"], .login-form input[type="password"] {
          outline: none;
          font-size: 1em;
          color: #000;
          padding: .8em 1em;
          margin: 0;
          width: 93.5%;
          border: none;
          -webkit-appearance: none;
          display: block;
          background: rgba(255, 255, 255, 0.67);
      -webkit-border-radius: 3px;
      -moz-border-radius: 3px; 
          border-radius: 3px;
      }
      .login-form p {
          font-size: 1em;
          color: #fff;
          margin: 1em 0 .5em;
      }
      /*-- checkbox --*/
      .login-agileits-top input[type="checkbox"] {
          display: none;
      }
      .login-agileits-top input[type="checkbox"]+label {
          position: relative;
          padding-left: 1.8em;
          border: none;
          outline: none;
          font-size: 1em;
          color: #fff;
          cursor: pointer;
          display: -webkit-inline-box;
          margin: 1.5em 0;
      }
      .login-agileits-top input[type="checkbox"]+label span:first-child {
          width: 14px;
          height: 14px;
          border: 2px solid #C8C8C8;
          position: absolute;
          left: 0;
      top: 3px
      }
      .login-agileits-top input[type="checkbox"]:checked+label span:first-child:before {
          content: "";
          background: url(../imags/images/tooplate_list.png)no-repeat;
          position: absolute;
          left: 2px;
          top: 2px;
          font-size: 10px;
          width: 10px;
          height: 10px;
      }
      /*-- //checkbox --*/
      .login-form input[type="submit"],.login-form input[type="button"]{
          font-size: 1em;
          color: #fff;
          background: #1c4eda;
          border: 3px solid #1c4eda;
          outline: none;
          cursor: pointer;
          padding: .6em 1em;
          -webkit-appearance: none;
          width: 100%;
          -webkit-border-radius: 3px;
      -moz-border-radius: 3px ;
          border-radius: 3px;
      }
      .login-form input[type="submit"]:hover,.login-form input[type="button"]:hover {
      color: #990000;
      }
      
      .login-agileits-bottom {
          margin-top: 3.5em;
          text-align: center;
          padding: 1.5em 1em 0;
          border-top: 1px dotted rgb(188, 188, 223);
      }
      .login-agileits-bottom p {
          margin-bottom: 0;
      }
      .login-agileits-bottom p a{
          color: #fff;
      transition:.5s all;
      }
      .login-agileits-bottom p a:hover {
          color: #517fff;
      }
      .login-form .login-agileits-top.sign-up input[type="submit"] {
          margin-bottom: 2.1em;
      }
      /*-- //main --*/
      /*-- SAP --*/
      .sap_tabs,.w3ls-tabs {
          clear: both;
          padding: 3em;
          background-color:#9F79EE;
      }
      .tab_box{
      background:#fd926d;
      padding: 2em;
      }
      .top1{
      margin-top: 2%;
      }
      .resp-tabs-list {
          list-style: none;
          text-align: left;
          margin-bottom: 2em;
      }
      .resp-tab-item {
          color: #fff;
          font-size: 1.3em;
          cursor: pointer;
          padding: 0;
          display: inline-block;
          text-align: center;
          list-style: none;
          outline: none;
          -webkit-transition: all 0.3s;
          -moz-transition: all 0.3s;
          -ms-transition: all 0.3s;
          -o-transition: all 0.3s;
          transition: all 0.3s; 
          opacity: .7 ;
      }
      .resp-tab-active {
          text-shadow: none;
          color: #fff;
          opacity: 1;
          border-color: #fff;
      }
      .resp-tab-item span {
          border-bottom: 3px solid transparent;
      padding: 0 3px 0.4em;
      }
      .resp-tab-active span {
          border-bottom-color: #fff;
      }
      .resp-tabs-container {
          padding: 0px;
          background: none;
          clear: left;
      }
      h2.resp-accordion {
      padding: 5px;
      display: none;
      }
      .resp-tab-content {
      display: none;
      }
      .resp-tab-item label {
          margin: 0 0.6em;
          font-size: 1.2em;
          vertical-align: middle;
      }
      /*-- //SAP --*/
      /*-- copyright --*/
      .copyright {
          margin: 2em 0;
          text-align: center;
      }
      .copyright p {
          font-size: 1em;
          color: #fff;
      line-height:1.8em;
      }
      .copyright p a{
          color: #fff ;
      -webkit-transition: 0.5s all;
      -moz-transition: 0.5s all;
      -o-transition: 0.5s all;
      -ms-transition: 0.5s all;
      transition: 0.5s all;
      }
      .copyright p a:hover{
          color: #000;
      }
      /*-- //copyright --*/
      /*-- responsive-design --*/
      @media(max-width:1280px){
      .login-form {
          width: 40% ;
      }
      .login-agileits-bottom {
          margin-top: 3em ;
      padding: 1em 1em 0;
      }
      h1 {
          font-size: 2.6em ;
      }
      }
      @media(max-width:1080px){
      h1 {
          font-size: 2.4em;
      }
      .login-form {
          width: 50%;
          margin: 1.5em auto;
      }
      .sap_tabs,.w3ls-tabs {
          padding: 2.5em 
      }
      }
      @media(max-width:1024px){
      .login-form .login-agileits-top.sign-up input[type="submit"] {
          margin-bottom: 1em;
      }
      .login-form input[type="text"], .login-form input[type="password"] { 
          width: 92.5% ;
      }
      }
      @media(max-width:991px){
      .login-form {
          width: 54% ;
      }
      }
      @media(max-width:900px){
      h1 {
          font-size: 2.2em;
      }
      .login-form input[type="text"], .login-form input[type="password"] { 
          padding: 0.6em 1em ;
      width: 91.5%
      }
      .login-form input[type="submit"] {
          font-size: 0.9em ;
          padding: .5em 1em 
      }
      .login-agileits-bottom {
          margin-top: 2.5em;
          padding: 0.6em 1em 0;
      }
      }
      @media(max-width:800px){
      .login-form input[type="text"], .login-form input[type="password"] { 
          width: 90.5%;
      }
      .login-form .login-agileits-top.sign-up input[type="submit"] {
          margin-bottom: 0.8em;
      }
      }
      @media(max-width:736px){
      .login-form {
          width: 61%;
      }
      }
      @media(max-width:640px){
      h1 {
          font-size: 2em;
      }
      .sap_tabs,.w3ls-tabs {
          padding: 2em;
      }
      .login-form {
          width: 65%;
      }
      }
      @media(max-width:600px){
      .login-form input[type="text"], .login-form input[type="password"] {
          width: 89.5%;
      }
      }
      @media(max-width:568px){
      .copyright p { 
          padding: 0 1em;
      }
      }
      @media(max-width:480px){
      h1 {
          font-size: 1.85em;
      }
      .sap_tabs,.w3ls-tabs {
          padding: 1.8em;
      }
      .login-form {
          width: 80%;
      }
      h1 {
          font-size: 1.8em;
      }
      .main {
          padding: 1.5em 0 0;
      }
      .resp-tab-item { 
          font-size: 1.2em ;
      }
      .login-agileits-bottom {
          margin-top: 2em;
          padding: 0.4em 1em 0;
      }
      .copyright p {
          font-size: 0.9em ;
      }
      }
      @media(max-width:414px){
      h1 {
          font-size: 1.6em;
      }
      .sap_tabs,.w3ls-tabs {
          padding: 1.5em;
      }
      .login-form input[type="text"], .login-form input[type="password"] { 
          font-size: 0.9em ;
      }
      .login-form input[type="submit"] {
          font-size: 0.8em; 
      }
      .login-form p {
          font-size: 0.9em ;
      }
      .login-form {
          width: 85%;
      }
      .login-agileits-top input[type="checkbox"]+label { 
          font-size: 0.9em ;
      }
      }
      @media(max-width:384px){
      .login-form .login-agileits-top.sign-up input[type="submit"] {
          margin-bottom: 0;
      }
      .copyright {
          margin: 1.5em 0 ;
      }
      }
      @media(max-width:375px){
      .login-form {
          width: 90%;
      }
      }
      @media(max-width:320px){
      .main {
          padding: 1em 0 0;
      }
      h1 {
          font-size: 1.5em;
      }
      .login-form { 
          margin: 1em auto;
      }
      .resp-tab-item {
          font-size: 1.1em;
      }
      .login-form p { 
          margin: 0.8em 0 .4em;
      }
      .login-agileits-top input[type="checkbox"]+label { 
          margin: 1em 0;
      }
      .login-agileits-bottom {
          margin-top: 1.5em ;
      }
      }
      /*-- //responsive-design --*/
       </style>
      <script type="text/javascript">  
      var codeFlag;
      function checkCode(){
      $.ajax({
      url:"/utils/checkCode.action",
      type:"post",
      data:{"code":$("#code").val()},
      success:function(result){
      if(result==0){
      changCode();
      alert("验证码输入不正确，请重新输入！");
      codeFlag=true;
      }else{
      codeFlag=false;}
      },
      error:function() { 
      codeFlag=true;}
      
      });
      }
        function changCode(){
        var codeSrc=$("#codeObj");
        var newSrc=codeSrc.attr("src");
        codeSrc.attr("src",changUrl(newSrc));
      }
        // 时间戳
        function changUrl(url){
        var timeStamp=(new Date()).valueOf();
        if(url.indexOf("?")>=0){
        var num=url.indexOf("?")+1;
          url=url.substring(0,num);
        url=url+"timetamp="+timeStamp;
        }else{
        url=url+"?timeStamp="+timeStamp;
        }
        return url;
        }
      
      function check(){
      var emailReg=/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(.[a-zA-Z0-9_-]+)+$/;
      var email=document.getElementById("email").value;
      var emailResult=emailReg.exec(email);
      if(document.getElementById("username").value.length<6){
      alert("帐号位数必需大于6位");
      return false;
      }
      else if(document.getElementById("password").value.length<6 ){
      alert("密码位数必需大于6位");
      return false;
        }else if((document.getElementById("password").value)!=(document.getElementById("password1").value)){
       alert("两次输入的密码不一致");
       return false;
       }else if(!emailResult){
      alert("邮箱格式不正确！");
      return false;
      }else if(codeFlag){
          alert("验证码输入不正确！");
          return false;
       }else{return true;}  }
      </script>  
      </head>
      <body class="login_bj" >
      	<center><h1 style="color: yellow">  ${msg} </h1></center>
      <div class="main">
      	<div class="login-form">
      		<div class="sap_tabs w3ls-tabs">
     			 <div id="horizontalTab" style="display: block width: 100% margin: 0px">
      				<div class="clear"> </div>
      				<div class="resp-tabs-container">
     					 <ul class="resp-tabs-list" style="text-align:center">
     					<li class="resp-tab-item resp-tab-active" >请正确填写注册</li>
      					</ul>
      <div class="tab-1 resp-tab-content resp-tab-content-active"  style="display:block">
      <div class="login-agileits-top sign-up"> 
      <form action="/register/register.action"  method="post" name="regisname">
      <p>*账号：</p>
      <input type="text" name="username" id="username"/>
      <p>昵称：</p>
      <input type="text" name="name" id="name"/>
      <p>*密码：（*密码必需大于六位!）</p>
      <input type="password" name="password" id="password"/>
      <p>*再次输入密码：（*两次密码输入必需一致!）</p>
      <input type="password" name="password1" id="password1"/>
      <p>*邮箱：（请输入正确邮箱，这是你找回密码的唯一方式）</p>
      <input type="text" name="email" id="email"/>
      <p>请输入验证码：</p>
      <input type="text"  class="codeClass" name="code" id="code" onblur="checkCode()">
       <img id="codeObj" alt="验证码"  style="width: 100pxheight: 30px" src="${pageContext.request.contextPath}/utils/getCode.action" onclick="changCode()">
       <a href="#" onclick="changCode()">看不清楚，换一张</a><br>
      <input type="checkbox" id="brand1" value="">
      <input type="submit" value="创建账号，点击注册" onclick="return check()">
      <a href="/login.jsp"><input type="button" value="已有账号，点击登陆"></a>
      </form>  
      </div>
      </div>
      </div>
      </div> 
      </div> 
      </div>
      </div>
      </body>
      </html>
    
