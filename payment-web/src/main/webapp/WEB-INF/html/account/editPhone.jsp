<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tools" uri="/WEB-INF/tlds/tools.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>手机号码修改</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<style type="text/css">
			html { 
				overflow-x: hidden; 
				overflow-y: hidden; 
			}
		</style>
		<script type="text/javascript">
			/**
			* 提交表单
			*/
			function submitForm(){
				initValidateForm();
				formAjaxSubmit('form_addOrUpdate', closeWin);
			}
			
			var timer = null;
			function ajaxVerifyCode() {
				if(null != timer) clearInterval(timer);
				var countdown = 60; 
				timer = setInterval(function() {
					if(0 == countdown) { 
				        $("#verifyCodeBtn").removeAttr("disabled");
				        $("#verifyCodeBtn").removeAttr("style").attr("style", "background: #3eacde;color: white;height: 25px;cursor: pointer;"); 
				        $("#verifyCodeBtn").val("获取短信验证码"); 
				        clearInterval(timer);
				    } else {
				    	$("#verifyCodeBtn").removeAttr("style").attr("style", "height: 25px;");
				    	$("#verifyCodeBtn").attr("disabled", true); 
				    	$("#verifyCodeBtn").val("重新发送(" + countdown + "s)"); 
				        countdown--;
				    }
				}, 1000);
				
				$.ajax({
				  	url: "<%=request.getContextPath()%>/payment/messageService/send",
				  	data:"phoneNum=${user.phone }",
				  	type:'POST',
				  	dataType: "json",
				  	success: function(data){
			   		},
				   	error: function() {
				   		$.messager.alert("提示", "操作失败(可能网络问题)! ", "error");
		            }
			  });

				
			}
			
			/**
			* 关闭窗体
			* @param data			:回调信息
			* @param isTriggerByBtn :是否是关闭按钮触发
			*/
			function closeWin(data, isTriggerByBtn) {
				if(data && "OK" == data.status
						|| isTriggerByBtn) {
					window.parent.$('#open_window').window('close', true) 
				}
			}
		</script>
	</head>
	
	<body style="width:100%;height:100%;">
		<div class="panel" style="display: block; width: 100%">
			<div class="panel-body panel-body-noheader panel-body-noborder" style="width: 100%; height: 100%;">
				<div class="dlg_content"> 
					<form:form modelAttribute="user" id="form_addOrUpdate" action='updatePhone' method="POST">
						<form:hidden path="id"/>
						<table class="table_list_1" width="100%">
							<tbody>
								<tr>
									<td class="t_l_1_title" width="25%">登录账号：</td>
									<td>
										${user.count }
									</td>
								</tr>
								<tr>
									<td class="t_l_1_title">已绑定手机号码：</td>
									<td>    
										${user.phone }&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="button" class="linkbutton-style-2" style="background: #3eacde;color: white;height: 25px;cursor: pointer;" 
											id="verifyCodeBtn" 
											onclick="ajaxVerifyCode()" value="获取短信验证码">
									</td>
								</tr>
								<tr>
									<td class="t_l_1_title"><span>*</span>短信验证码：</td>
									<td>
										<input type="text" name="verifyCode" validate="{type:'required'}">
									</td>
								</tr>
								<tr>
									<td class="t_l_1_title"><span>*</span>新手机号码：</td>
									<td>
										<input type="text" name="updatePhone" validate="{type:'required'}">
									</td>
								</tr>
							</tbody>
						</table>
					</form:form>
					<div align="center" style="margin-top:20px;">
				    	<a href="#" class="easyui-linkbutton" onclick="submitForm()">保存</a>
				   		&nbsp;&nbsp;&nbsp;&nbsp;
				    	<a href="#" class="linkbutton-style-2" onclick="javascript:closeWin(null, true)">关闭</a>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>