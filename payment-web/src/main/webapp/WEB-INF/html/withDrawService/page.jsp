<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="tools" uri="/WEB-INF/tlds/tools.tld"%>
<html>
	<head>
	  	<title>银行卡管理</title>
	  	
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
			             url: "<%=request.getContextPath()%>/payment/cardService/delCardById?id=" + idVal,
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
			
		</script>
	
	</head>
	
	<body style="height: 100%">
		<div class="panel" style="display: block; width: 100%">
			<div class="panel-body panel-body-noheader panel-body-noborder" style="width: 100%; height: 100%;">
				<div class="p_20">
					<div class="search_div">
						<form:form action="adminPage" modelAttribute="page" id="queryForm">
							<form:hidden path="pageSize"/>
							<form:hidden path="pageNo"/>
							<table class="table_list_3">
								<tr>
									<td class="t_l_3_title">账户号：</td>
									<td><form:input path="mapBean['count']" maxlength="40"/></td>
									<td>
										<form:select path="mapBean['status']">
											<form:option value="">--请选择--</form:option>
											<form:options items="${withDrawStatus}"/>
										</form:select>
									</td>
									<td align="left">
										<a href="#" class="easyui-linkbutton" onclick="refreshPageByFormId();" id="queryBtn">查询</a>
									</td>
								</tr>
							</table>
						</form:form>
					</div>
					
		    		<table id="row" border="0" cellspacing="0" cellpadding="0" class="qq-datagrid" style="width:100%;height:auto">
						<thead>
							<tr>
								<th width="50" >序号</th>
								<th>用户账号</th>
								<th>金额</th>
								<th>省</th>
								<th>市</th>
								<th>县</th>
								<th>支行名称</th>
								<th>卡号</th>
								<th>提现状态</th>
								<th>最近修改日期</th>
								<th colspan="6">操作</th>
							</tr>
						</thead>
						<form id="pageList">
							<c:forEach items="${page.res }" var="res" varStatus="s">
								<tr>
									<td>
										${s.count + page.pageSize*(page.pageNo-1)}
									</td>	
									<td>${res.userAccount}</td>
									<td>${res.money}</td>
									<td>${res.province}</td>
									<td>${res.city}</td>
									<td>${res.county}</td>
									<td>${res.bankName}</td>
									<td>${res.cardNo}</td>
									<td>${tools:toWithDrawStatusName(res.status) }</td>
									<td>
										<fmt:formatDate value="${res.updateDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
									</td>
									<c:choose>
										<c:when test="${res.status ==2}">
											<td>
												<a href="#" onclick="javascript:EOpenWindow('<c:url value='/payment/withDrawService/editWithdraw?id=${res.id }'/>',{title:'${res.userAccount}[修改]',height:200,width:400}, refreshPageByFormId)"><span>确认提现</span></a>
											</td>
										</c:when>
										<c:otherwise>
											<td></td>
										</c:otherwise>
									</c:choose>
										
									
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