<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tools" uri="/WEB-INF/tlds/tools.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>密码修改</title>
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
			
			function formAjaxSubmit(formId, callBackFun){
				var aform = $("#"+formId);
				if(aform.form('validate')){
					if($("#pwd").val() != $("#confirmPwd").val()) {
						$.messager.alert("提示", "两次输入密码不一致", "info", function() {
							$("#confirmPwd").focus();
						});
						return false;
					}
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
					            		$.messager.alert("提示", data.message, "info", function() {
					            			if(callBackFun) {
					            				callBackFun(data);
					            			}
						            	});
							   		},
								   	error: function() {
								   		$("#submitBtn").attr("disabled", false);
								   		$(document.body).unmask();
								   		$.messager.alert("提示", "操作失败(可能网络问题)! ", "error");
						            }
							  });
						 }
					});
				}
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
					<form:form modelAttribute="user" id="form_addOrUpdate" action='<%=request.getContextPath() +  "/payment/user/updateUserPwdById"%>' method="POST">
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
									<td class="t_l_1_title"><span>*</span>密码：</td>
									<td>
										<input type="password" name="password" id="pwd" validate="{type:'password',config:{allowBlank:false}}">
									</td>
								</tr>
								<tr>
									<td class="t_l_1_title"><span>*</span>确认密码：</td>
									<td>
										<input type="password" name="confirmPwd" id="confirmPwd" validate="{type:'password',config:{allowBlank:false}}">
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