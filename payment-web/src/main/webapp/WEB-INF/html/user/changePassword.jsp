<!DOCTYPE html>
<%@page import="com.tengfei.payment.util.UserUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include.jsp"%>
<html>
<head>
<meta charset="utf-8">
<title>修改密码</title>
<script type="text/javascript">
	/**
	* 提交表单
	*/
	function submitForm(){
		if($("#form_addOrUpdate").form('validate')){
			if($("#newPassword").val() != $("#confirmPassword").val()) {
				$("#errTip").attr("class", "tip_error");
				$.messager.alert("提示", "新密码和确认密码输入不一致", "info", function() {
					$("#confirmPassword").focus();
				});
		  		return;
			}
			$.messager.confirm('提示', '您确定要修改密码吗?', function(btn) {
				 if(btn) {
					$(document.body).height($(document).height());
					$(document.body).mask('正在处理...');
					 $.ajax({
						  	url: $("#form_addOrUpdate").attr("action"),
						  	data:$("#form_addOrUpdate").serialize(),
						  	type:'POST',
						  	dataType: "json",
						  	success: function(data){
						  		$(document.body).unmask();
					  			$.messager.alert("提示", data.message, "info");
			            		return;
					   		},
						   	error: function() {
						   		$(document.body).unmask();
						   		$.messager.alert("提示", "操作失败(可能网络问题)! ", "error");
				            }
					  });
				 }
			});
		}
	}
</script>
</head>

<body style="width:90%;height:100%;">
		<div class="panel" style="display: block; width: 100%">
			<div class="panel-body panel-body-noheader panel-body-noborder" style="width: 100%; height: 100%;">
				<div class="dlg_content"> 
					<form action="<%=request.getContextPath() %>/payment/user/updateUserPwdById" id="form_addOrUpdate">
						<input type="hidden" name="id" value="<%= UserUtil.getCurrentUserId()%>">
						<table class="table_list_1" width="100%">
							<tr>
								<td class="t_l_1_title ">新密码：</td>
								<td><input type="password" name="password" id="newPassword" validate="{type:'password',config:{allowBlank:false}}"></td>
							</tr>
							<tr>
								<td class="t_l_1_title ">确认密码：</td>
								<td><input type="password" name="confirmPassword" id="confirmPassword" validate="{type:'password',config:{allowBlank:false}}"></td>
							</tr>
							<tr>
								<td></td>
								<td>
									<a href="#" class="easyui-linkbutton" onclick="submitForm()">保存</a>
								</td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>
