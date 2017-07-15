<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tools" uri="/WEB-INF/tlds/tools.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>银行卡添加</title>
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
				if(!isAtLeastOneCheck("bank.id")) {
					$.messager.alert("提示", "请选择开户行!", "info");
					
					return;
				}
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
			
			$(function(){
		        $.extend($.fn.validatebox.defaults.rules, {
		            comboVry: {
		                validator:function(value, param) {
		                	return value != param;
		                },
		                message: '请选择开户行所在地'
		            }
		        });
				
				 $('#province').combobox({   
		               editable:false, //不可编辑状态  
		               cache: false,
		               validType:"comboVry['--省--']",
		               required: true,
		               onSelect: function(){  
		                  $("#city").combobox("clear");
		                  $('#city').combobox('setValue','--市--');
		                  $("#county").combobox("clear");
		                  $('#county').combobox('setValue','--区/县--');
		                  var province = $('#province').combobox('getValue');  
		                  $('#city').combobox('reload',"<%=request.getContextPath()%>/payment/area?parentId="+province);    
		               }  
		            });   
			      
				    $('#city').combobox({  
				        editable:false, //不可编辑状态  
				        cache: false,  
				        required: true,
				        valueField:'id', 
				        validType:"comboVry['--市--']",
				        textField:'name',  
				        onSelect: function(){  
				            $("#county").combobox("clear");
				            $('#county').combobox('setValue','--区/县--');
				            var city = $('#city').combobox('getValue');   
				             $('#county').combobox('reload',"<%=request.getContextPath()%>/payment/area?parentId="+ city);  
				         }  
				    });
				    
			        $('#county').combobox({  
			            editable : false, //不可编辑状态  
			            cache : false,
			            required: true,
			            validType:"comboVry['--区/县--']",
			            valueField : 'id',  
			            textField : 'name',  
			        });
			        
	      	})
		</script>
	</head>
	
	<body style="width:100%;height:100%;">
		<div class="panel" style="display: block; width: 100%">
			<div class="panel-body panel-body-noheader panel-body-noborder" style="width: 100%; height: 100%;">
				<div class="dlg_content"> 
					<form:form modelAttribute="entity" id="form_addOrUpdate" action="save" method="POST">
						<form:hidden path="id"/>
						<table class="table_list_1" width="100%">
							<tbody>
								<tr>
									<td class="t_l_1_title" width="20%"><span>*</span>开户行：</td>
									<td>
										<c:forEach items="${cardList }" var="res" varStatus="s">
											<span style="text-align: center;vertical-align:top;">
												<input type="radio" name="bank.id" value="${res.id }" style="margin-top: -22px">
												<img src="<%=request.getContextPath() %>/${res.picPath}" 
													style="width: 120px; height: 30px;">
											</span>
											&nbsp;&nbsp;
											<c:if test="${0 == s.count % 4 }">
												<br>
											</c:if>
										</c:forEach>
									</td>
								</tr>
								<tr>
									<td class="t_l_1_title"><span>*</span>开户行所在地：</td>
									<td>
										<form:select path="province" id="province">
											<form:option value="">--省--</form:option>
											<form:options items="${provinces }" itemLabel="name" itemValue="id"/>
										</form:select>
										<form:select path="city" id="city">
											<form:option value="">--市--</form:option>
										</form:select>
										<form:select path="county" id="county">
											<form:option value="">--区/县--</form:option>
										</form:select>
									</td>
								</tr>
								<tr>
									<td class="t_l_1_title"><span>*</span>支行名称：</td>
									<td>
										<form:input path="banckName" size="45" validate="{type:'maxLength[150]',config:{allowBlank:false}}"/>
										<font color="red">(如果开户行的省份和市区不正确将会被拒绝或者延迟几天到账!)</font>
									</td>
								</tr>
								<tr>
									<td class="t_l_1_title"><span>*</span>收款人姓名：</td>
									<td>
										<form:input path="cardName" size="45" validate="{type:'maxLength[30]',config:{allowBlank:false}}"/>
									</td>
								</tr>
								<tr>
									<td class="t_l_1_title"><span>*</span>银行账号：</td>
									<td>
										<form:input path="cardNo" size="45" validate="{type:'maxLength[30]',config:{allowBlank:false}}"/>
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