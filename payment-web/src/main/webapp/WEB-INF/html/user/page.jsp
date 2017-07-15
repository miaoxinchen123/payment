<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="tools" uri="/WEB-INF/tlds/tools.tld"%>
<html>
	<head>
	  	<title>用户管理</title>
	  	
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="用户管理">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="description" content="用户管理">
		<style type="text/css">
			th{text-align: center;}
		</style>
		<script type="text/javascript">
			/**
			 * ajax单个删除
			 * @param idVal			:删除id
			 * @param refreshBtnId	:刷新按钮Id
			 */
			function ajaxDel(idVal, refreshBtnId) {
				$.messager.confirm("提示", "确认删除该记录?", function(btn) {//ajax批处理
			       if(btn) {
			    	   $.ajax({
			             type: "POST",
			             url: "<%=request.getContextPath()%>/payment/user/delUserById?id=" + idVal,
			             dataType: "json",
			             timeout: 900000,
			             success: function(data){
			            	if("OK" == data.status) {
			            		$.messager.alert("提示", data.message, "info", function() {
			            			$('#' + refreshBtnId).click();
				            	});
			            		return;
			            	}
			            	
			            	$.messager.alert("提示", "操作失败(可能网络问题)", "error");
			               },
				           error: function() {
				        	   $.messager.alert("提示", "操作失败(可能网络问题)! ", "error");
				           }
				       });
			       }
			    });
			}
			
			/**
			* 重置用户密码
			* @param userId		:用户Id
			* @param userName	:登录账号
			*/
			function ajaxresetUserPwd(userId, userName) {
				$.messager.confirm("提示", "是否确认重置(默认:${tools:defaultPwd() })用户<font color='red'>" + userName + "</font>密码? ", function(btn) {
					if(btn) {
						var paramStr = "id=" + userId;
						$.ajax({//动态保存资源
				             type: "POST",
				             url: "updateUserPwdById",
				             data: paramStr,
				             dataType: "json",
				             timeout: 900000,
				             success: function(data){
			            		$.messager.alert("提示", data.message, "info", function() {
			            			$('#queryBtn').click();
			            		});
				             },
					         error: function() {
					        	$.messager.alert("提示", "操作失败(可能网络问题)! ", "error");
					         }
				         });
					} 
				});
			}
			
		</script>
	
	</head>
	
	<body style="height: 100%">
		<div class="panel" style="display: block; width: 100%">
			<div class="panel-body panel-body-noheader panel-body-noborder" style="width: 100%; height: 100%;">
				<div class="p_20">
					<div class="search_div">
						<form:form action="page" modelAttribute="page" id="queryForm">
							<form:hidden path="pageSize"/>
							<form:hidden path="pageNo"/>
							<table class="table_list_3">
								<tr>
									<td class="t_l_3_title">账号：</td>
									<td><form:input path="mapBean['count']" maxlength="40"/></td>
									<td class="t_l_3_title">手机号码：</td>
									<td><form:input path="mapBean['phone']" maxlength="15"/></td>
									<td class="t_l_3_title">QQ：</td>
									<td><form:input path="mapBean['qq']" maxlength="15"/></td>
								</tr>
								<tr>
									<td class="t_l_3_title">账户类型：</td>
									<td align="left">
										<form:select path="mapBean['type']">
											<form:option value="">--请选择--</form:option>
											<form:options items="${userTypeMap }"/>
										</form:select>
									</td>
									<td class="t_l_3_title">状态：</td>
									<td>
										<form:select path="mapBean['status']">
											<form:option value="">--请选择--</form:option>
											<form:options items="${userStatuMap }"/>
										</form:select>
									</td>
									<td class="t_l_3_title"></td>
									<td>
									</td>
									<td align="left">
										<a href="#" class="easyui-linkbutton" onclick="refreshPageByFormId();" id="queryBtn">查询</a>
									</td>
								</tr>
							</table>
						</form:form>
					</div>
					
		    		<div class="button_div">
						<a href="#" onclick="javascript:EOpenWindow('<c:url value='/payment/user/addOrEditUser'/>', {title:'添加',height:420}, refreshPageByFormId)" 
							class="linkbutton  add_shixiangleibie_linkbutton"><i class="add-linkbutton"></i>添加</a>
				    </div>
				    
		    		<table id="row" border="0" cellspacing="0" cellpadding="0" class="qq-datagrid" style="width:100%;height:auto">
						<thead>
							<tr>
								<th width="50" >序号</th>
								<th>账号</th>
								<th>手机号码</th>
								<th>QQ</th>
								<th>账户类型</th>
								<th>支付网站</th>
								<th>账户余额</th>
								<th>提现类型</th>
								<th>状态</th>
								<th>客户QQ</th>
								<th>最近更新日期</th>
								<th colspan="6">操作</th>
							</tr>
						</thead>
						<form id="pageList">
							
							<c:forEach items="${page.res }" var="res" varStatus="s">
								<tr>
									<td>
										${s.count + page.pageSize*(page.pageNo-1)}
									</td>	
									<td>${res.count }</td>
									<td>${res.phone }</td>
									<td>${res.qq }</td>
									<td>${tools:toUserTypeName(res.type) }</td>
									<td>${res.hosts }</td>
									<td>${tools:formatMoney(res.balance) }</td>
									<td>${tools:toBalanceTypeName(res.balanceType) }</td>
									<td>${tools:toUserStatuName(res.status) }</td>
									<td>${res.serverQQ }</td>
									<td>
										<fmt:formatDate value="${res.updateDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
									</td>
									<td>
										<a href="#" onclick="javascript:EOpenWindow('<c:url value='/payment/user/view?id=${res.id }'/>', {title:'${res.count }[查看]',height:460})"><span>查看</span></a>
									</td>
									<td>
										<a href="#" onclick="javascript:EOpenWindow('<c:url value='/payment/user/addOrEditUser?id=${res.id }'/>', {title:'${res.count }[修改]',height:420}, refreshPageByFormId)"><span>修改</span></a>
									</td>
									<td>
										<a href="#" onclick="ajaxDel('${res.id }', 'queryBtn')"><span>删除</span></a>
									</td>
									<td>
										<c:choose>
											<c:when test="${tools:isProxy(res.type) }">
												<a href="#" onclick="javascript:EOpenWindow('<c:url value='/payment/proxy_relation/${res.id }'/>'
													, {title:'${res.count }[商户管理]',height:450,width:950})"><span>商户管理</span></a>
											</c:when>
											<c:when test="${tools:isMerchant(res.type) }">
												<a href="#" onclick="javascript:EOpenWindow('<c:url value='/payment/rateService/${res.id}?id=${res.id}'/>'
													, {title:'${res.count }[费率管理]',height:400,width:900})"><span>费率管理</span></a>
											</c:when>
											<c:otherwise>
												&nbsp;&nbsp;
											</c:otherwise>
										</c:choose>
									</td>
									<td>
										<a href="#" onclick="ajaxresetUserPwd('${res.id }', '${res.count }')"><span>重置密码</span></a>
									</td>
								</tr>
							</c:forEach>
						</form>
					</table>
					<jsp:include page="/common/page.jsp"/>
				</div>
		    </div>
		</div>		
	</body>
</html>