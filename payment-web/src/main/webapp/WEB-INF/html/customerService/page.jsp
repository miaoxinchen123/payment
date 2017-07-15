<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include.jsp"%>
<html>
	<head>
	  	<title>客服管理</title>
	  	
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="客服管理">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="description" content="客服管理">
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
			             url: "<%=request.getContextPath()%>/payment/customerService/delByQQ?qq=" + idVal,
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
						<form:form action="page" modelAttribute="page" id="queryForm">
							<form:hidden path="pageSize"/>
							<form:hidden path="pageNo"/>
							<table class="table_list_3">
								<tr>
									<td class="t_l_3_title">QQ：</td>
									<td><form:input path="mapBean['qq']" maxlength="15"/></td>
									<td class="t_l_3_title">姓名：</td>
									<td><form:input path="mapBean['name']" maxlength="40"/></td>
									<td class="t_l_3_title">手机号码：</td>
									<td><form:input path="mapBean['phone']" maxlength="15"/></td>
									<td align="left">
										<a href="#" class="easyui-linkbutton" onclick="refreshPageByFormId();" id="queryBtn">查询</a>
									</td>
								</tr>
							</table>
						</form:form>
					</div>
					
		    		<div class="button_div">
						<a href="#" onclick="javascript:EOpenWindow('<c:url value='/payment/customerService/addOrEdit'/>', {title:'添加',height:250,width:500}, refreshPageByFormId)" 
							class="linkbutton  add_shixiangleibie_linkbutton"><i class="add-linkbutton"></i>添加</a>
				    </div>
				    
		    		<table id="row" border="0" cellspacing="0" cellpadding="0" class="qq-datagrid" style="width:100%;height:auto">
						<thead>
							<tr>
								<th width="50" >序号</th>
								<th>QQ</th>
								<th>姓名</th>
								<th>手机号码</th>
								<th colspan="5">操作</th>
							</tr>
						</thead>
						<form id="pageList">
							
							<c:forEach items="${page.res }" var="res" varStatus="s">
								<tr>
									<td>
										${s.count + page.pageSize*(page.pageNo-1)}
									</td>
									<td>${res.qq }</td>
									<td>${res.name }</td>
									<td>${res.phone }</td>
									<td>
										<a href="#" onclick="javascript:EOpenWindow('<c:url value='/payment/customerService/view?qq=${res.qq }'/>', {title:'${res.name }[查看]',height:250,width:500})"><span>查看</span></a>
									</td>
									<td>
										<a href="#" onclick="javascript:EOpenWindow('<c:url value='/payment/customerService/addOrEdit?qq=${res.qq }'/>', {title:'${res.name }[修改]',height:250,width:500}, refreshPageByFormId)"><span>修改</span></a>
									</td>
									<td>
										<a href="#" onclick="ajaxDel('${res.qq }', 'queryBtn')"><span>删除</span></a>
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