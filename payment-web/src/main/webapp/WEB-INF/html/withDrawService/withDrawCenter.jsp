<!DOCTYPE html>
<%@ page import="com.tengfei.payment.util.Tools"%>
<%@ page import="com.tengfei.payment.system.UserStatu"%>
<%@ page import="com.tengfei.payment.util.UserUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tools" uri="/WEB-INF/tlds/tools.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<html>
	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<title>联付宝 | 您身边的支付管家</title>
			<%@ include file="/WEB-INF/html/merchant_head.jsp"%>
			<link rel="stylesheet" type="text/css" href="<c:url value='/resources/js/loadmask/jquery.loadmask.css'/>"/>
			<script type="text/javascript" src="<%=request.getContextPath() %>/merchant/js/layer.js"></script>
			<script type="text/javascript" src="<c:url value='/resources/js/loadmask/jquery.loadmask.min.js'/>"></script>
			<%-- 本地js加载 --%>
			<script type="text/javascript" src="<c:url value='/resources/js/validateForm.js'/>"></script>
			<script type="text/javascript" src="<c:url value='/resources/js/utils.js'/>"></script>
			
			<%-- 时间选择器插件--%>
			<script type="text/javascript" src="<%=request.getContextPath() %>/merchant/js/bootstrap.min.js"></script>
			<script type="text/javascript" src="<%=request.getContextPath() %>/merchant/js/moment.js"></script>
			<script type="text/javascript" src="<%=request.getContextPath() %>/merchant/js/daterangepicker.js"></script>
			
			<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/merchant/css/bootstrap.min.css">
			<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/merchant/css/font-awesome.min.css">
			<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/merchant/css/daterangepicker-bs3.css">
		
		
		 <script type="text/javascript">
               $(document).ready(function() {
                  $('#reservationtime').daterangepicker({
                    timePicker: true,
                    timePickerIncrement: 30,
                  }, function(start, end) {
                	  $('#startDate').val(start.toDate());
                	  $('#endDate').val(end.toDate());
                  });
               });
               
         </script>
		
	</head>
	
	<body>
		<%@ include file="/WEB-INF/html/merchant_body.jsp" %>	
		<div class="view-product">
				<div class="info-center">
		            <div class="manage-head">
		              		<h6 class="padding-left manage-head-con">结算中心</h6>
		            </div>
		            <div class="p_20">
	            	<div class="search_div_merchant">
						<form:form action="page" modelAttribute="page" id="queryForm">
							<form:hidden path="pageSize"/>
							<form:hidden path="pageNo"/>
							<form:hidden path="mapBean['userId']"/>
							<table class="table_list_3">
								<tr>
									<td class="t_l_3_title">交易时间：</td>
									<td><form:input path="" name="reservation" id="reservationtime" value="请选择交易时间"  readonly="true"/></td>
									<td class="t_l_3_title">提现状态：</td>
									<td align="left">
										<form:select path="mapBean['withDrawStatus']">
											<form:option value="">--请选择--</form:option>
											<form:options items="${withDrawStatus }"/>
										</form:select>
									</td>
									<td class="t_l_3_title"></td>
									<td/>
									<td align="left">
										<a href="#" class="button bg-green-deep icon icon-button-green fl margin-left" onclick="refreshPageByFormId();" id="queryBtn">查询</a>
										<a href="<%=request.getContextPath() %>/payment/withDrawService/withDraw" class="button bg-green-deep icon icon-button-green fl margin-left" onclick="withDraw();" id="withDrawBtn">提现</a>
									</td>
									<td><form:hidden path="mapBean['startDate']" id="startDate" maxlength="40"/></td>
									<td><form:hidden path="mapBean['endDate']" id="endDate"  maxlength="40"/></td>
								</tr>
							</table>
						</form:form>
					</div>
				
					
			            <div class="offcial-table tr-border margin-big-top clearfix">
			                <div class="tr-th clearfix">
			                    <div class="th w25">平台交易号</div>
			                    <div class="th w15">提现时间</div>
			                    <div class="th w15">提现金额</div>
			                    <div class="th w15">提现卡号</div>
			                    <div class="th w15">平台手续费</div>
			                    <div class="th w15">提现状态</div>
			                </div>
		                	<form id="pageList">
		                		<c:forEach items="${page.res }" var="res" varStatus="s">
			               			 <div class="tr clearfix border-bottom-none">
											<div class="td w25">
												${res.id}
											</div>
											<div class="td w15">
												<fmt:formatDate value="${res.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
											</div>
											<div class="td w15">
												${tools:formatMoney(res.money) }
											</div>
											<div class="td w15">
												${res.cardNo}
											</div>
											<div class="td w15">
												${tools:formatMoney(res.platformCharge) }
											</div>
											<div class="td w15">
												${tools:toWithDrawStatusName(res.status) }
											</div>
										</div>
									</c:forEach>
				                </form>
				             </div>
							<jsp:include page="/common/page.jsp"/>
				        </div>
					</div>
				</div>
</body></html>