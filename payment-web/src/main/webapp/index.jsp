<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>支付系统</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/login.css"/>
<style>
	*{ margin: 0px; padding: 0px; }
	body { font-size: 12px; font-family: Arial, Helvetica, sans-serif; }
</style>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery.min.js"></script>
<script>
	  function passwordFocus(){
			var ipt = $('<input type="password" class="password" id="_pwd" title="密码" name="password" value="">').css("color","#000");
			$(".password").after(ipt).remove();
			ipt.focus();
			ipt.blur(function (){
				if(this.value != "") return;
				var ipt = $('<input type="text" class="password" id="_pwd" title="密码" name="password" value="密码">');
				$(".password").after(ipt).remove();
				ipt.focus(passwordFocus);
			});
		}
		$(function (){
			$(".password").focus(passwordFocus);	
			$(".username").focus(function () {
				if(this.value == '用户名') {
					this.value = '';
					$(this).css("color","#000");
				}
			}).blur(function (){
				if(this.value == '') {
					this.value = '用户名';
					$(this).css("color","#999");
				}
			});	
		});
		
		/**
		* 验证表单
		*/
		function validateForm() {
			if("" == $("#_name").val() 
					|| $("#_name").val() == $("#_name").attr("title")) {
				$("#_name").focus();
				$("#errTip").html("请输入用户名");
				return false;
			} else if ("" == $("#_pwd").val()
					|| $("#_pwd").val() == $("#_pwd").attr("title")) {
				$("#_pwd").focus();
				$("#errTip").html("请输入密码");
				return false;
			} else if ($("#_code") && "" == $("#_code").val()) {
				$("#_code").focus();
				$("#errTip").html("请输入验证码");
				return false;
			}
			
			return true;
		}
		
		if(top != this) {//在iframe里
			parent.window.location = window.location;
		}
</script>
</head>

<body id="login">
	<div id="login_div">
		<div id="login_content">
		  <form action="login" method="post" onsubmit="return validateForm()">
			    <input type="text" name="username" class="username" id="_name" title="用户名"
			    	value='<c:out value="${sessionScope.SPRING_SECURITY_LAST_USERNAME}" default="用户名"/>'>
			    &nbsp;&nbsp;&nbsp;&nbsp;
			    <input type="text" class="password" value="密码" id="_pwd" title="密码" name="password">&nbsp;&nbsp;&nbsp;&nbsp;
			    <input type="submit" value="">
		   </form>
		   <c:choose>
		   		<c:when test='${null != sessionScope.SPRING_SECURITY_LAST_CUSTOME_EXCEPTION 
						&& "" != sessionScope.SPRING_SECURITY_LAST_CUSTOME_EXCEPTION}'>
			   		<div class="error" id="errTip">
						友情提示：${sessionScope.SPRING_SECURITY_LAST_CUSTOME_EXCEPTION  }
						<c:remove var="SPRING_SECURITY_LAST_CUSTOME_EXCEPTION" scope="session"/>
					</div>
		   		</c:when>
		   		<c:otherwise>
		   			<div class="error" id="errTip">
					</div>
		   		</c:otherwise>
		   </c:choose>
		</div>
	</div>
	<div id="login_footer"> ©2017  联付宝支付公司版权所有 </div>
</body>
</html>
