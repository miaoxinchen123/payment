<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tools" uri="/WEB-INF/tlds/tools.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>银行卡添加或修改</title>
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
			
			function upload(){
				 $.ajax({
					   url: '/payment-web/payment/cardService/upload',
					  	data:new FormData($('#from_upload')[0]),
					  	type:'POST',
					  	dataType: "json",
					  	processData: false,
					  	contentType: false,
					  	success: function(data){
		            		$.messager.alert("提示", data.status, "info", function() {
		            			$("#picPath").val(data.message);
			            	});
				   		},
					   	error: function() {
					   		$.messager.alert("提示", "操作失败(可能网络问题)! ", "error");
			            }
				  });
			}
			
		</script>
	</head>
	
	<body style="width:100%;height:100%;">
		<div class="panel" style="display: block; width: 100%">
			<div class="panel-body panel-body-noheader panel-body-noborder" style="width: 100%; height: 100%;">
				<div class="dlg_content"> 
					<form:form modelAttribute="entity" id="form_addOrUpdate" action="saveCard" method="POST">
						<form:hidden path="id"/>
						<table class="table_list_1" width="100%">
							<tbody>
								<tr>
									<td class="t_l_1_title"><span>*</span>银行名称：</td>
									<td>
										<form:input path="bankName" validate="{type:'maxLength[50]',config:{allowBlank:false}}"/>
									</td>
								</tr>
								<tr>
									<td class="t_l_1_title"  height=50px;><span>*</span>图片路径：</td>
									<td>
										<form:input path="picPath" id="picPath" readonly="true" validate="{type:'maxLength[50]',config:{allowBlank:false}}"/>
									</td>
								</tr>
								
							</tbody>
						</table>
					</form:form>
					
					<form action="upload" id="from_upload"  method="POST" enctype="multipart/form-data">  
						<table class="table_list_1" width="100%">
							<tbody>
								<tr>
									<td class="t_l_1_title"><input type="button"  onclick="upload()" value="提交图片" /></td>
									<td>
										<input type="file" name="file"/> 
									</td>
								</tr>
								
							</tbody>
						</table>
					</form>
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