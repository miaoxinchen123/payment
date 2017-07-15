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
					<form:form modelAttribute="entity" id="form_addOrUpdate" action="saveUser" method="POST">
						<form:hidden path="id"/>
						<table class="table_list_1" width="100%">
							<tbody>
								<c:choose>
									<c:when test="${null == entity.id || '' == entity.id}">
										<tr>
											<td class="t_l_1_title" width="25%"><span>*</span>登录账号：</td>
											<td>
												<form:input path="count" validate="{type:'maxLength[30]',config:{allowBlank:false}}" id="count"/>
											</td>
										</tr>
										<tr>
											<td class="t_l_1_title"><span>*</span>登录密码：</td>
											<td>
												<input type="password" name="password" value="${tools:defaultPwd() }" title="默认密码${tools:defaultPwd() }"
													validate="{type:'password',config:{allowBlank:false}}">
												&nbsp;<span><font color="red">(默认密码:${tools:defaultPwd() })</font></span>
											</td>
										</tr>
									</c:when>
									<c:otherwise>
										<tr>
											<td class="t_l_1_title" width="25%">用户Key：</td>
											<td>
												${entity.id }
											</td>
										</tr>
										<tr>
											<td class="t_l_1_title" width="25%">登录账号：</td>
											<td>
												${entity.count }
												<form:hidden path="count"/>
											</td>
										</tr>
									</c:otherwise>
								</c:choose>
								<tr>
									<td class="t_l_1_title"><span>*</span>手机号码：</td>
									<td>
										<form:input path="phone" validate="{type:'maxLength[11]',config:{allowBlank:false}}"/>
									</td>
								</tr>
								<tr>
									<td class="t_l_1_title">QQ：</td>
									<td>
										<form:input path="qq" validate="{type:'maxLength[15]'}"/>
									</td>
								</tr>
								<tr>
									<td class="t_l_1_title">账户类型：</td>
									<td>
										<form:select path="type">
											<form:options items="${userTypeMap }"/>
										</form:select>
									</td>
								</tr>
								<tr>
									<td class="t_l_1_title">支付网站：</td>
									<td>
										<form:input path="hosts" validate="{type:'maxLength[120]'}"/>
									</td>
								</tr>
								<tr>
									<td class="t_l_1_title">提现类型：</td>
									<td>
										<form:select path="balanceType">
											<form:option value="">--请选择--</form:option>
											<form:options items="${balanceTypeMap }"/>
										</form:select>
									</td>
								</tr>
								<tr>
									<td class="t_l_1_title">状态：</td>
									<td>
										<form:select path="status">
											<form:options items="${userStatuMap }"/>
										</form:select>
									</td>
								</tr>
								<tr>
									<td class="t_l_1_title">客服QQ：</td>
									<td>
										<form:select path="serverQQ">
											<form:option value="">--请选择--</form:option>
											<form:options items="${customerServiceMap }"/>
										</form:select>
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