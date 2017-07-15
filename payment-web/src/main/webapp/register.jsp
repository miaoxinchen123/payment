<%@page import="com.tengfei.payment.system.UserType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta charset="utf-8">
<title>支付系统-用户注册</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/register.css"/>
<%-- 第三方css加载 --%>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/js/easyui/themes/default/easyui.css'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/js/easyui/themes/icon.css'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/js/loadmask/jquery.loadmask.css'/>"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="<c:url value='/resources/js/loadmask/jquery.loadmask.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/easyui/jquery.easyui.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/easyui/locale/easyui-lang-zh_CN.js'/>"></script>
<script>
		/**
		* 验证表单
		*/
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
		
		function submitForm() {
			if(!validateEmpty(["_count", "_pwd", "_confirmPwd"])) return false;
			if($("#_pwd").val() != $("#_confirmPwd").val()) {
				showTip("两次输入密码不一致");
				$("#_confirmPwd").focus();
				return false;
			}
			
			if(!validateEmpty(["_phone"])) return false;
			var reg=/^(1[3, 4, 5, 7, 8][0-9])[0-9]{8}$/i;
			if(!reg.test($("#_phone").val())) {
				showTip("手机号码输入有误");
				$("#_phone").focus();
				return;
			}
			
			if(!validateEmpty(["_qq"])) return false;
			
			if("" == $('#_type option:selected').val()) {
				showTip("请选择" + $('#_type').attr("title"));
				$("#_type").focus();
				return false;
			} else if('<%=UserType.MERCHANT.code()%>' == $('#_type option:selected').val()) {
			/* 	if("" == $("#_proxyPhone").val() 
						|| $("#_proxyPhone").val() == $("#_proxyPhone").attr("title")) {
					$("#_proxyPhone").focus();
					showTip("请输入" + $("#_proxyPhone").attr("title"));
					return false;
				}
				 */
				var reg=/^0{0,1}(13[0-9]?|15[0-9]?|18[0-9])[0-9]{8}$/i;
				if(!reg.test($("#_proxyPhone").val())) {
					showTip($("#_proxyPhone").attr("title") + "输入有误");
					$("#_proxyPhone").focus();
					return;
				}
			}
			
		/* 	if(!validateEmpty(["_hosts"])) return false; */
			
			formAjaxSubmit();
		}
		
		function formAjaxSubmit() {
			var aform = $("#registerForm");
			$.messager.confirm('提示', '您确定要提交吗?', function(btn) {
				 if(btn) {
					$(document.body).height($(document).height());
					$(document.body).mask('正在处理...');
	             	$("#submitBtn").attr("disabled",true);
					 $.ajax({
						  	url: aform.attr("action"),
						  	data:aform.serialize(),
						  	type:'POST',
						  	dataType: "json",
						  	success: function(data){
						  		$("#submitBtn").attr("disabled", false);
						  		$(document.body).unmask();
						  		if(data && "OK" == data.status) {
						  			$.messager.confirm('提示', "注册成功,是否跳转到登录页面?", function(btn) {
						  				if(btn) $("#loginBtn").click();
						  			});
						  			
						  			return;
						  		}
						  		showTip(data.message);
					   		},
						   	error: function() {
						   		$("#submitBtn").attr("disabled", false);
						   		$(document.body).unmask();
						   		showTip("注册失败,请联系管理员");
				            }
					  });
				 }
			});
		}
		
		function showTip(message) {
			$("#errTip").html(message);
			window.setTimeout(function() {
				$("#errTip").html("");
			},3000);
		}
		
		function dynamicShowProxyPhone(val) {
			if("<%=UserType.MERCHANT.code()%>" == val) {
				$("#proxyPhoneText").show();
				$("#_login").attr("style", "min-height: 590px;margin: 2% auto 0 auto;");
				return;
			}
			
			$("#proxyPhoneText").hide();
			$("#_login").attr("style", "min-height: 550px;margin: 4% auto 0 auto;");
		}
		
		if(top != this) {//在iframe里
			parent.window.location = window.location;
		}
		
		function passwordFocus() {
			var ipt = $('<input type="password" id="_pwd" title="密码" value="" name="password" placeholder="密码" maxlength="20">').css("color","#000");
			$("#_pwd").after(ipt).remove();
			ipt.focus();
			ipt.blur(function (){
				if(this.value != "") return;
				var ipt = $('<input type="text" id="_pwd" title="密码" value="密码" name="password" placeholder="密码" maxlength="20">');
				$("#_pwd").after(ipt).remove();
				ipt.focus(passwordFocus);
			});
		}
		
		function confirmPasswordFocus() {
			var ipt = $('<input type="password" id="_confirmPwd" title="确认密码" value="" placeholder="确认密码" maxlength="20">').css("color","#000");
			$("#_confirmPwd").after(ipt).remove();
			ipt.focus();
			ipt.blur(function (){
				if(this.value != "") return;
				var ipt = $('<input type="text" id="_confirmPwd" title="确认密码" value="确认密码" placeholder="确认密码" maxlength="20">');
				$("#_confirmPwd").after(ipt).remove();
				ipt.focus(confirmPasswordFocus);
			});
		}
		
		$(function (){
			$("#_pwd").focus(passwordFocus);
			$("#_confirmPwd").focus(confirmPasswordFocus);
			$("input").not("#_pwd, #_confirmPwd").each(
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
</script>
</head>

<body id="login">
	<div class="login" id="_login" style="min-height:550px;margin: 4% auto 0 auto;">
		<div class="message" style="width: 100%;">
			<span style="margin-left: 3%;font-size: 16px;">用户注册</span>
		</div>
    	<div id="darkbannerwrap"></div>
		  <form action="payment/register" method="post" id="registerForm">
			    <input type="text" name="count" id="_count" title="账号" value="账号" placeholder="账号" maxlength="20"/><font color="red">*</font>
			    <hr class="hr15">
			    <input type="text" id="_pwd" title="密码" value="密码" name="password" placeholder="密码" maxlength="20"><font color="red">*</font>
			    <hr class="hr15">
			    <input type="text" id="_confirmPwd" title="确认密码" value="确认密码" placeholder="确认密码" maxlength="20"><font color="red">*</font>
			    <hr class="hr15">
			    <input type="text" id="_phone" title="手机号码" name="phone" value="手机号码" placeholder="手机号码" maxlength="11"><font color="red">*</font>
			    <hr class="hr15">
			    <input type="text" id="_qq" title="QQ" name="qq" placeholder="QQ" value="QQ" maxlength="20"><font color="red">*</font>
			    <hr class="hr15">
			    <select id="_type" name="type" title="账号类型(商户或代理商)" onchange="dynamicShowProxyPhone(this.value)">
			    	<option value="">--请选择商户类型--</option>
			    	<%
			    		out.println("<option value='" + UserType.PROXY.code() + "'>" + UserType.PROXY.val() + "</option>");
			    		out.println("<option value='" + UserType.MERCHANT.code() + "'>" + UserType.MERCHANT.val() + "</option>");
			    	%>
			    </select><font color="red">*</font>
			    <hr class="hr15">
			    <span style="display:none;" id="proxyPhoneText">
			    	<input type="text" id="_proxyPhone" title="推荐代理商户手机号码" value="推荐代理商户手机号码" name="proxyPhone" placeholder="推荐代理商户手机号码" maxlength="11">
			    	<font color="red"></font>
			    	<hr class="hr15">
			    </span>
			    <input type="text" id="_hosts" title="支付网站" name="hosts" value="支付网站" placeholder="支付网站" maxlength="400">
			    <font color="red"></font>
			    <hr class="hr15">
			    <input value="注册" style="width: 90%;margin-left: 3%;" type="button" onclick="submitForm()" id="submitBtn">
			     <div style="text-align: right; margin-right: 7%;"><a href="login.jsp"><div id="loginBtn">已有账号</div></a></div>
			    <div class="error" id="errTip"></div>
		   </form>
	</div>
	<div id="login_footer"> ©2017  联付宝支付公司版权所有 </div>
</body>
</html>
