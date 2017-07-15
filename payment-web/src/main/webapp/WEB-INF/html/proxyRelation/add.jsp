<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="tools" uri="/WEB-INF/tlds/tools.tld"%>
<html>
	<head>
	  	<title>添加</title>
	  	
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="用户管理">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="description" content="用户管理">
		<style type="text/css">
			th{text-align: center;}
		</style>
		<script type="text/javascript">
			function addProxyRelation(userId, merchantId) {
				$.messager.confirm("提示", "您确定要提交吗? ", function(btn) {
					if(btn) {
						var paramStr = "userId=" + userId + "&merchantId=" + merchantId
									   + "&rate=" + $('#' + merchantId).numberspinner('getValue');
						$.ajax({//动态保存资源
				             type: "POST",
				             url: "add",
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
						<form:form action="pageProxyRelation" modelAttribute="page" id="queryForm">
							<form:hidden path="pageSize"/>
							<form:hidden path="pageNo"/>
							<form:hidden path="mapBean['userId']"/>
							<table class="table_list_3">
								<tr>
									<td class="t_l_3_title">账号：</td>
									<td><form:input path="mapBean['count']" maxlength="40" size="15"/></td>
									<td class="t_l_3_title">手机号码：</td>
									<td><form:input path="mapBean['phone']" maxlength="15" size="15"/></td>
									<td class="t_l_3_title">QQ：</td>
									<td><form:input path="mapBean['qq']" maxlength="15" size="15"/></td>
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
								<th>账号</th>
								<th>手机号码</th>
								<th>QQ</th>
								<th>提成率(%)</th>
								<th>操作</th>
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
									<td>
										<input id="${res.id }" class="easyui-numberspinner" style="width:60px;" 
											value="${tools:defaultRate() * 100 }" 
											data-options="min:1,max:50,editable:true">
									</td>
									<td>
										<a href="#" onclick="addProxyRelation('${page.mapBean['userId'] }', '${res.id }')"><span>添加</span></a>
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