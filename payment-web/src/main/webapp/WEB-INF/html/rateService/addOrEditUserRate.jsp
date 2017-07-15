<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tools" uri="/WEB-INF/tlds/tools.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>用户添加或修改</title>
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
		</script>
	</head>
	
	<body style="width:100%;height:100%;">
		<div class="panel" style="display: block; width: 100%">
			<div class="panel-body panel-body-noheader panel-body-noborder" style="width: 100%; height: 100%;">
				<div class="dlg_content"> 
					<form:form modelAttribute="userRate" id="form_addOrUpdate" action="saveUserRate" method="POST">
						<form:hidden path="id"/>
						<form:hidden path="userId"/>
						<table class="table_list_1" width="100%">
							<tbody>
								<tr>
									<td class="t_l_1_title"><span>*</span>支付宝PC费率(‰)：</td>
									<td>
										<form:input path="alipayPc" value="${tools:formatThousandRate(userRate.alipayPc) }"  validate="{type:'thousandFloat',config:{allowBlank:false}}"/>
									</td>
								</tr>
								<tr>
									<td class="t_l_1_title"><span>*</span>支付wap费率(‰)：</td>
									<td>
										<form:input path="alipayWap" value="${tools:formatThousandRate(userRate.alipayWap) }" validate="{type:'thousandFloat',config:{allowBlank:false}}"/>
									</td>
								</tr>
								<tr>
									<td class="t_l_1_title"><span>*</span>微信PC费率(‰)：</td>
									<td>
										<form:input path="wechatPc" value="${tools:formatThousandRate(userRate.wechatPc) }" validate="{type:'thousandFloat',config:{allowBlank:false}}"/>
									</td>
								</tr>
								<tr>
									<td class="t_l_1_title"><span>*</span>微信wap费率(‰)：</td>
									<td>
										<form:input path="wechatWap" value="${tools:formatThousandRate(userRate.wechatWap) }" validate="{type:'thousandFloat',config:{allowBlank:false}}"/>
									</td>
								</tr>
								<tr>
									<td class="t_l_1_title"><span>*</span>网银PC费率(‰)：</td>
									<td>
										<form:input path="cardPc" value="${tools:formatThousandRate(userRate.cardPc) }" validate="{type:'thousandFloat',config:{allowBlank:false}}"/>
									</td>
								</tr>
								<tr>
									<td class="t_l_1_title"><span>*</span>网银wap费率(‰)：</td>
									<td>
										<form:input path="cardWap" value="${tools:formatThousandRate(userRate.cardWap) }" validate="{type:'thousandFloat',config:{allowBlank:false}}"/>
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