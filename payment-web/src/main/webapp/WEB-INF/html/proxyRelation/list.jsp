<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include.jsp"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="tools" uri="/WEB-INF/tlds/tools.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>商户管理</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<style type="text/css">
			html { 
				overflow-x: hidden; 
				overflow-y: hidden; 
			}
		</style>
		<script type="text/javascript">
		/**
		 * ajax单个删除
		 * @param idVal			:删除id
		 * @param refreshBtnId	:刷新按钮Id
		 */
		function ajaxDel(idVal) {
			$.messager.confirm("提示", "确认删除该记录?", function(btn) {//ajax批处理
				var paramStr = "id=" + idVal;
		       if(btn) {
		    	   $.ajax({
		             type: "POST",
		             url: "del",
		             data:paramStr,
		             dataType: "json",
		             timeout: 900000,
		             success: function(data){
		            	if("OK" == data.status) {
		            		$.messager.alert("提示", data.message, "info", function() {
		            			$('#queryForm').submit();
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
				<div class="p_20">
					<form:form action="${userId }" id="queryForm">
					</form:form>
					<div class="button_div">
						<a href="#" onclick="javascript:EOpenWindow('<c:url value='/payment/proxy_relation/pageProxyRelation?mapBean[userId]=${userId }&pageSize=10'/>', {title:'添加',height:350}, refreshPageByFormId)" 
							class="linkbutton  add_shixiangleibie_linkbutton"><i class="add-linkbutton"></i>添加</a>
				    </div>
					<table id="row" border="0" cellspacing="0" cellpadding="0" class="qq-datagrid" style="width:100%;height:auto">
						<thead>
							<tr>
								<th width="50" >序号</th>
								<th>商户</th>
								<th>手机号码</th>
								<th>QQ</th>
								<th>状态</th>
								<th>提成率(%)</th>
								<th>最近更新日期</th>
								<th colspan="2">操作</th>
							</tr>
						</thead>
						<form id="pageList">
							<c:forEach items="${merchantList }" var="res" varStatus="s">
								<tr>
									<td>
										${s.count + page.pageSize*(page.pageNo-1)}
									</td>	
									<td>${res.merchant.count }</td>
									<td>${res.merchant.phone }</td>
									<td>${res.merchant.qq }</td>
									<td>${tools:toUserStatuName(res.merchant.status) }</td>
									<td>${tools:formatRate(res.rate) }</td>
									<td>
										<fmt:formatDate value="${res.updateDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
									</td>
									<td>
										<a href="#" onclick="javascript:EOpenWindow('<c:url value='/payment/proxy_relation/editRate?id=${res.id }&merchant.count=${res.merchant.count }&rate=${res.rate }'/>'
											, {title:'${res.merchant.count }[修改提成率]',height:250,width:400}, refreshPageByFormId)"><span>修改</span></a>
									</td>
									<td>
										<a href="#" onclick="ajaxDel('${res.id }', 'queryBtn')"><span>删除</span></a>
									</td>
								</tr>
							</c:forEach>
						</form>
					</table>
					<div align="center" style="margin-top:20px;">
				    	<a href="#" class="linkbutton-style-2" onclick="javascript:closeWin(null, true)">关闭</a>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>