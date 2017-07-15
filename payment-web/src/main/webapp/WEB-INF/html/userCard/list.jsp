<!DOCTYPE html>
<%@page import="com.tengfei.payment.util.Tools"%>
<%@page import="com.tengfei.payment.system.UserStatu"%>
<%@page import="com.tengfei.payment.util.UserUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tools" uri="/WEB-INF/tlds/tools.tld"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/WEB-INF/html/merchant_head.jsp"%>
    <%-- 第三方css加载 --%>
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/js/loadmask/jquery.loadmask.css'/>"/>
	
	<%-- 第三方js加载 --%>
	<script type="text/javascript" src="<c:url value='/resources/js/loadmask/jquery.loadmask.min.js'/>"></script>
	
	<%-- 本地js加载 --%>
	<script type="text/javascript" src="<c:url value='/resources/js/validateForm.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/utils.js'/>"></script>
    <script type="text/javascript">
    
    	function ajaxDel(id) {
    		$.messager.confirm("提示", "确认删除该银行卡?", function(btn) {//ajax批处理
		       if(btn) {
		    	   $.ajax({
		             type: "POST",
		             url: "<%=request.getContextPath()%>/payment/user_card/del?id=" + id,
		             dataType: "json",
		             timeout: 900000,
		             success: function(data){
		            	if("OK" == data.status) {
		            		$.messager.alert("提示", "删除成功!", "info", function() {
		            			refreshPage();
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
    	
    	function refreshPage() {
    		$('#cardManageSpan').click();
    	}
    
    	$(function(){
    		$('#userCenterDiv').click();
      	})
    </script>
</head>

<body>
	<%@ include file="/WEB-INF/html/merchant_body.jsp" %>	
	<div class="view-product">
			<div class="indentify clearfix">
				<div class="manage-head">
					<h6 class="padding-left manage-head-con text-sub">银行卡管理</h6>
				</div>
				<div class="indentify-class padding-lr clearfix">
					<p class="margin-big-tb text-sub text-default">
					</p>
					<ul class="class-content clearfix">
						<c:forEach items="${page.res }" var="res" varStatus="s">
							<li class="fl margin-large-35" style="margin-bottom: 20px;margin-right: 1%">
								<div class="class-detail fl " href="javascript:void(0)">
									<div class="class-detail-top">
										<div class="text-center indentify-icon">
											<img src="<%=request.getContextPath() %>/${res.bank.picPath}" style="width: 280px; height: 64px;">
										</div>
										<ul class="class-detail-has margin-top text-lh-big">
											<li style="margin-bottom: 5px; text-align: center;">
												<span class="text-gray-white">${tools:formatCardNo(res.cardNo) }</span>
											</li>
											<li style="text-align: center;">
												<span class="text-gray-white">${tools:formatName(res.cardName) }</span>
											</li>
										</ul>
									</div>
									<p class="continue text-big" style="cursor: pointer;" onclick="ajaxDel('${res.id}')">
										删除
									</p>
								</div>
								<br>
							</li>
						</c:forEach>
						<li class="fl margin-large-35" style="margin-bottom: 20px;margin-right: 1%"">
							<a class="class-detail fl " href="javascript:void(0)" 
								onclick="javascript:EOpenWindow('<c:url value='/payment/user_card/add'/>', {title:'新增收款账户',height:500,width:900}, refreshPage)">
								<div class="class-detail-top">
									<div class="text-center indentify-icon">
										<img src="<%=request.getContextPath() %>/resources/img/add.png"
										 style="width: 90px; height: 90px; margin-top: 20px;">
									</div>
								</div>
								<p class="continue text-big">
									新增收款账户
								</p>
							</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>