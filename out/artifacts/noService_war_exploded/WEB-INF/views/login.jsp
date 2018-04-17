<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en" class="no-js">
<head>
<meta charset="utf-8">
<title>NO团网运维平台</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<link rel="stylesheet" href="<c:url value="/resources/js/login/css/supersized.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/js/login/css/login.css"/>">
<link rel="stylesheet" href="<c:url value="/resources/js/login/css/bootstrap.min.css"/>" >
<script type="text/javascript">
	var SITE_BASE_PATH = '<c:url value="/"/>';
</script>
<script src="<c:url value="/resources/js/login/js/jquery-1.8.2.min.js"/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.cookie.js'/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/login/js/jquery.form.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/login/js/tooltips.js"/>"></script>
<script src="<c:url value="/resources/js/login/js/supersized.3.2.7.min.js"/>"></script>
<script src="<c:url value="/resources/js/login/js/supersized-init.js"/>"></script>
<script src="<c:url value="/resources/js/login/js/scripts.js"/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/login/js/checkCode.js'/>"></script>
<script type="text/javascript">
document.onkeydown = function(e){
			if($(".bac").length==0)
			{
				if(!e) e = window.event;
				if((e.keyCode || e.which) == 13){
					var obtnLogin=document.getElementById("submit_btn")
					obtnLogin.focus();
				}
			}
}
	
  	$(function(){
  		createCode();
			//提交表单
			$('#submit_btn').click(function(){
				validate();
				/* show_loading();
				var myReg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/; //邮件正则
				if($('#email').val() == ''){
					show_err_msg('邮箱还没填呢！');	
					$('#email').focus();
				}else if(!myReg.test($('#email').val())){
					show_err_msg('您的邮箱格式错咯！');
					$('#email').focus();
				}else if($('#password').val() == ''){
					show_err_msg('密码还没填呢！');
					$('#password').focus();
				}else{
					//ajax提交表单，#login_form为表单的ID。 如：$('#login_form').ajaxSubmit(function(data) { ... });
					show_msg('登录成功咯！  正在为您跳转...','www.baidu.com');	
				} */
				var loginName=$("#loginName").val();
				var passWord=$("#passWord").val();
				if(""==loginName || ""==passWord){
					show_err_msg('请输入用户名或者密码!');
					return ;
				}
				if(validate()) {
					$.ajax({
		                cache: true,
		                async: false,
		                type: "POST",
		                url:SITE_BASE_PATH+"login/checkLogin",
		                data:$('#login').serialize(),// 你的formid
		                error: function(request) {
		                    alert("Connection error");
		                },
		                success: function(data) {
		                    if("ok"==data.msg){
		                    	/* $.cookie('loginName',$("#loginName").val(), { expires: 7, path: "/"}); */
		                    	show_msg('登录成功！  正在为您跳转...',SITE_BASE_PATH+"layout");
		                    	
		                    }else{
		                    	show_err_msg(data.msg);
		                    }
		                }
		            });
				}
				
			});
		});
</script>
	

<body>
	<div class="page-container">
	<div class="main_box">
		<div class="login_box">
			<div class="login_logo">
				NO团网运维平台
			</div>
			<div class="login_form">
				<form action="" id="login" method="post">
					<div class="form-group">
						<label for="j_username" class="t">账　号：</label> 
						<input id="loginName" value="" name="loginName" type="text" class="form-control x319 in" 
						autocomplete="off">
					</div>
					<div class="form-group">
						<label for="j_password" class="t">密　码：</label> 
						<input id="passWord" value="" name="passWord" type="password" 
						class="password form-control x319 in">
					</div>
					<div class="form-group">
						<label for="j_captcha" class="t">验证码：</label>
						 <input id="codeVal" name="j_captcha" type="text" class="form-control x164 in">
						 <input id="code"  type="text" onclick="createCode();" readonly="readonly" class="form-control x164 in">
					</div>
					<div class="form-group space">
						<label class="t"></label>　　　
						<button type="button"  id="submit_btn" 
						class="btn btn-primary btn-lg">&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp </button>
						<input type="reset" value="忘记密码" class="btn btn-default btn-lg">
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>
