<%@page import="com.tengfei.payment.system.UserType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta charset="utf-8">
<title>支付系统-找回密码</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/register.css"/>
<%-- 第三方css加载 --%>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/js/easyui/themes/default/easyui.css'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/js/easyui/themes/icon.css'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/js/loadmask/jquery.loadmask.css'/>"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="<c:url value='/resources/js/loadmask/jquery.loadmask.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/easyui/jquery.easyui.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/easyui/locale/easyui-lang-zh_CN.js'/>"></script>
<style>
	input[type=text]{
		height: 40px;
	}
</style>
<script>
		/**
		* 验证表单
		*/
		var reg=/^(1[3, 4, 5, 7, 8][0-9])[0-9]{8}$/i;
		function submitForm() {
			if(!validateEmpty(["_phone"])) return false;
			if(!reg.test($("#_phone").val())) {
				showTip("手机号码输入有误");
				$("#_phone").focus();
				return;
			}
			if(!validateEmpty(["_code"])) return false;
			
			var aform = $("#registerForm");
           	$("#submitBtn").attr("disabled",true);
			 $.ajax({
			  	url: aform.attr("action"),
			  	data:aform.serialize(),
			  	type:'POST',
			  	dataType: "json",
			  	success: function(data){
			  		$("#submitBtn").attr("disabled", false);
			  		if(data && "OK" == data.status) {
			  			$("#firstStep").attr("style", "display: none;");
			  			$("#secondStep").attr("style", "display: inline;");
			  			$("#registerForm").attr("action", "payment/forgetPwd/updatePwd");
			  			return;
			  		}
			  		showTip(data.message);
		   		},
			   	error: function() {
			   		$("#submitBtn").attr("disabled", false);
			   		showTip("系统发生未知错误,请联系管理员");
	            }
		  });
		}
		function validateEmpty(textIds) {
			if(null == textIds || 0 == textIds.length) return true;
			
			for(var i = 0; i < textIds.length; i++) {
				if("" == $("#" + textIds[i]).val() 
						|| $("#" + textIds[i]).val() == $("#" + textIds[i]).attr("title")) {
					$("#" + textIds[i]).focus();
					showTip("请输入" + $("#" + textIds[i]).attr("title"));
					return false;
				}
			}
			
			return true;
		}
		
		function confirmForm() {
			if(!validateEmpty(["_pwd", "_confirmPwd"])) return false;
			if($("#_pwd").val() != $("#_confirmPwd").val()) {
				showTip("两次输入密码不一致");
				$("#_confirmPwd").focus();
				return false;
			}
			
			var aform = $("#registerForm");
           	$("#confirmBtn").attr("disabled",true);
			 $.ajax({
			  	url: aform.attr("action"),
			  	data:aform.serialize(),
			  	type:'POST',
			  	dataType: "json",
			  	success: function(data){
			  		$("#confirmBtn").attr("disabled", false);
			  		if(data && "OK" == data.status) {
			  			$.messager.confirm('提示', "密码修改成功,是否跳转到登录页面?", function(btn) {
			  				if(btn) $("#loginBtn").click();
			  			});
			  			return;
			  		}
			  		showTip(data.message);
		   		},
			   	error: function() {
			   		$("#confirmBtn").attr("disabled", false);
			   		showTip("注册失败,请联系管理员");
	            }
		  });
		}
		
		var timer = null;
		function ajaxVerifyCode() {
			if("" == $("#_phone").val()) {
				showTip("手机号码不能为空");
				$("#_phone").focus();
				delayCleanTip();
				return;
			}
			
			if(!reg.test($("#_phone").val())) {
				showTip("手机号码输入有误");
				$("#_phone").focus();
				return;
			}
			
			if(null != timer) clearInterval(timer);
			var countdown = 60; 
			timer = setInterval(function() {
				if(0 == countdown) { 
			        $("#verifyCodeBtn").removeAttr("disabled");
			        $("#verifyCodeBtn").removeAttr("style").attr("style", "font-size: 14px;padding: 12px 24px 10px 0%;padding-right: 0px;background-color: #27A9E3;cursor: pointer;"); 
			        $("#verifyCodeBtn").val("获取短信验证码");
			        clearInterval(timer);
			        return;
			    } else {
			    	$("#verifyCodeBtn").removeAttr("style").attr("style", "font-size: 14px;padding: 12px 24px 10px 0%;padding-right: 0px;background-color: #ccc;cursor: auto;");
			    	$("#verifyCodeBtn").attr("disabled", true); 
			    	$("#verifyCodeBtn").val("重新发送(" + countdown + "s)"); 
			        countdown--;
			    }
			}, 1000);
			
			$.ajax({
			  	url: "<%=request.getContextPath()%>/payment/forgetPwd/sendCode",
			  	data:"phone=" + $("#_phone").val(),
			  	type:'POST',
			  	dataType: "json",
			  	success: function(data){
			  		if(data && "OK" == data.status) {
			  			$("#_token").val(data.token);
			  			$("#_codes").val(data.code);
			  			return;
			  		}
			  		
			  		showTip(data.message);
		   		},
			   	error: function() {
			   		$.messager.alert("提示", "操作失败(可能网络问题)! ", "error");
	            }
		  });
		}
		
		function showTip(message) {
			$("#errTip").html(message);
			window.setTimeout(function() {
				$("#errTip").html("");
			},3000);
		}
		
		function passwordFocus() {
			var ipt = $('<input type="password" id="_pwd" title="密码" value="" name="password" placeholder="密码" maxlength="20" style="height: 40px;">').css("color","#000");
			$("#_pwd").after(ipt).remove();
			ipt.focus();
			ipt.blur(function (){
				if(this.value != "") return;
				var ipt = $('<input type="text" id="_pwd" title="密码" value="密码" name="password" placeholder="密码" maxlength="20" style="height: 40px;">');
				$("#_pwd").after(ipt).remove();
				ipt.focus(passwordFocus);
			});
		}
		
		function confirmPasswordFocus() {
			var ipt = $('<input type="password" id="_confirmPwd" title="确认密码" value="" placeholder="确认密码" maxlength="20" style="height: 40px;">').css("color","#000");
			$("#_confirmPwd").after(ipt).remove();
			ipt.focus();
			ipt.blur(function (){
				if(this.value != "") return;
				var ipt = $('<input type="text" id="_confirmPwd" title="确认密码" value="确认密码" placeholder="确认密码" maxlength="20" style="height: 40px;">');
				$("#_confirmPwd").after(ipt).remove();
				ipt.focus(confirmPasswordFocus);
			});
		}
		
		$(function (){
			$("#_pwd").focus(passwordFocus);
			$("#_confirmPwd").focus(confirmPasswordFocus);
			$("input").not("#_pwd, #_confirmPwd, #verifyCodeBtn").each(
				function() {
					$(this).focus(function () {
						if(this.value == this.title) {
							this.value = '';
							$(this).css("color","#000");
						}
					}).blur(function (){
						if(this.value == '') {
							this.value = this.title;
							$(this).css("color","#999");
						}
					});	
				}
			);
		});
		
		if(top != this) {//在iframe里
			parent.window.location = window.location;
		}
</script>
</head>

<body id="login">
	<div class="login" id="_login" style="min-height:380px;margin: 8% auto 0 auto;">
		<div class="message" style="width: 100%;margin: 30px 0 0 -28px;">
			<span style="margin-left: 3%;font-size: 16px;">找回密码</span>
		</div>
    	<div id="darkbannerwrap"></div>
		  <form action="payment/forgetPwd/validateCode" method="post" id="registerForm">
		  		<input type="hidden" name="id" id="_token">
		  		<input type="hidden" name="qq" id="_codes">
		  		<span style="display: inline;" id="firstStep">
			  		<table style="width: 94%">
			  			<tr>
			  				<td width="70%">
			  					<input type="text" id="_phone" title="手机号码" value="手机号码" name="phone" placeholder="手机号码" 
				    				maxlength="11"><font color="red">*</font>
			  				</td>
			  				<td>
			  					<input type="button" value="获取短信验证码" onclick="ajaxVerifyCode()" id="verifyCodeBtn" 
			  						style="font-size: 14px;padding: 12px 24px 10px 0%;padding-right: 0px;" >
			  				</td>
			  			</tr>
			  		</table>
				    <hr class="hr20">
				    <input type="text" id="_code" title="手机短信验证码" value="手机短信验证码" name="code" placeholder="手机短信验证码" 
				       style="width: 57%;" maxlength="6"><font color="red">*</font>
				    <hr class="hr20">
				    <input value="下一步" style="width: 90%;margin-left: 3%; line-height: 20px;" type="button" onclick="submitForm()" id="submitBtn">
			    </span>
			    <span style="display: none;" id="secondStep">
				    <input type="text" id="_pwd" title="密码" name="password" value="密码" placeholder="密码" maxlength="20" style="height: 40px;"><font color="red">*</font>
				    <hr class="hr15">
				    <input type="text" id="_confirmPwd" title="确认密码" value="确认密码" placeholder="确认密码" maxlength="20" style="height: 40px;"><font color="red">*</font>
				    <hr class="hr15">
				    <input value="确认" style="width: 90%;margin-left: 3%; line-height: 20px;" type="button" onclick="confirmForm()" id="confirmBtn">
			    </span>
			    <hr class="hr20">
			    <div style="text-align: right; margin-right: 7%;"><a href="login.jsp"><div id="loginBtn">用户登录</div></a></div>
			    <div class="error" id="errTip" style="margin-left: 3%;"></div>
		   </form>
	</div>
	<div id="login_footer"> ©2017  联付宝支付公司版权所有 </div>
</body>
</html>
