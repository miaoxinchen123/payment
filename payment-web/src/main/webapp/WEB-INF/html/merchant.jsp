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
</head>

<body>
	<%@ include file="/WEB-INF/html/merchant_body.jsp" %>	
	<div class="view-product background-color"  id="overView">
		<div class="padding-big background-color">
				<div class="account-info clearfix">
					<div class="text-box-main min-width-300 fl">
						<dl>
							<dt class="padding-big-left lists-border-list clearfix">
								<div class="title fl padding-big-top padding-big-bottom">
									<p>你好，<%=com.tengfei.payment.util.UserUtil.getCurrentUserName() %></p>
									<p class="margin-small-top clearfix">
										<span class="fl">账户状态：</span>
										<!--没有实名认证-->
										<%
											UserStatu us = UserUtil.getCurrentUserStatu();
											if(UserStatu.OK == us) {
												out.println("<a class='button bg-green-deep icon icon-button-green fl margin-left' href='javascript:void(0)'>已认证</a>");
											} else if(UserStatu.WAIT_FOR_AUDIT == us) {
												out.println("<a class='button bg-blue-deep icon icon-button-blue fl margin-left' href='javascript:void(0)'>待审核</a>");
											} else {
												out.println("<a class='fl margin-left button btn-red text-white' href='javascript:void(0)'>账户异常</a>");
											}
										%>
									</p>
								</div>
								<span class="fr icon-head">
									<img src="<%=request.getContextPath() %>/resources/img/avatar.php" alt="账户头像">
								</span>
							</dt>
							<dd class="padding-big clearfix">
								<p class="w50 fl">
									<i class="fl icon icon-mobile"></i>
									<span class="fl margin-left">手机：已绑定</span>
								</p>
								<p class="w50 fl">
									<i class="fl icon icon-email"></i>
									<span class="fl margin-left">邮箱：已绑定</span>
								</p>
							</dd>
						</dl>
					</div>
					<div class="text-box-main min-width-360 fl">
						<dl>
							<dt class="padding-big lists-border-list clearfix">
								<div class="fl w50 title">
									<p>账户余额</p>
									<p class="margin-small-top clearfix">
										<span class="fl"><em class="h3 text-red-deep"><td>${tools:formatMoney(user.balance) }</td> </em>元</span>
										<%
											us = UserUtil.getCurrentUserStatu();
											if(UserStatu.ABNORMAL == us) {
												out.println("<a class='fl margin-left button btn-grey text-white' style='cursor: text;' href='javascript:void(0)'>提现</a>");
											} else {
												out.println("<a class='fl margin-left button btn-red text-white' href='" + request.getContextPath() 
												+ "/payment/withDrawService/withDraw'>提现</a>");
											}
										%>
									</p>
								</div>
								<div class="fl w50 padding-left title">
									<p>已绑定银行卡</p>
									<p class="margin-small-top clearfix">
										<span class="fl"><em class="h3 text-blue-deep">${cardCount } </em>个</span>
										<a class="fl margin-left button btn-red text-white" target="_self" href="<%=request.getContextPath() %>/payment/user_card/page">绑定</a>
									</p>
								</div>
							</dt>
							<dd class="padding-big">
								<div class="account-class text-hidden">
									<a href="javascript:void(0)">未支付订单</a>
									<a href="javascript:void(0)">已支付订单</a>
									<a href="javascript:void(0)">充值记录</a>
								</div>
							</dd>
						</dl>
					</div>
					<div class="text-box-main min-width-300 fl margin-right-none">
						<dl>
							<dt class="padding-big lists-border-list clearfix">
								<div class="fl title">
									<p>QQ客服：</p>
									<p class="text-sliver text-default margin-small-top">
										<em class="h3 text-golden margin-big-right">QQ：</em>${user.serverQQ }
									</p>
								</div>
								<div class="fr">
									<a class="button btn-orange text-white" href="http://wpa.qq.com/msgrd?v=3&uin=${user.serverQQ }&site=qq&menu=yes" target="_blank">联系客服</a>
								</div>
							</dt>
							<dd class="padding-big">
								<div class="account-class text-hidden">
									<p>您好，我是您的QQ客服：，在使用中有任何问题，欢迎随时联系！</p>
								</div>
							</dd>
						</dl>
					</div>
				</div>
				
				<c:choose>
					<c:when test="${tools:isProxy(user.type) }">
						<div class="account-product margin-big-top clearfix" style="width: 100%;">
							<div class="text-box-main padding-big fl mine-product" style="width: 100%;height: 90px;">
								<h2 class="h6 margin-big-bottom" style="margin-bottom: 5px;">今日实时数据（<%=Tools.getCurrentDate() %>）</h2>
								<div class="mine-product-content clearfix">
									<ul class="w50 fl lists" style="width: 33%;">
										<li class="w70 lists-border-list" style="border-bottom: 0px solid #e6e7e9;">
											<p class="border-list-text">今日提成：&nbsp;&nbsp;&nbsp;&nbsp;<em class="orange">${tools:formatMoney(order.proxyExpectMoney) }</em>
											</p>
										</li>
										<br>
									</ul>
									<ul class="w50 fl lists" style="width: 33%;">
										<li class="w70 lists-border-list" style="border-bottom: 0px solid #e6e7e9;padding: 0 -20px;">
											<p class="border-list-text">成功支付：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<em class="orange">${tools:formatMoney(order.proxySuccessMoney) }</em>
											</p>
										</li>
										<br>
									</ul>
									<ul class="w50 fl lists" style="width: 34%;">
										<li class="w70 lists-border-list" style="border-bottom: 0px solid #e6e7e9;margin-left: 12%">
											<p class="border-list-text">成功笔数：&nbsp;&nbsp;&nbsp;&nbsp;<em class="orange">${order.proxySuccessCount}</em>
											</p>
										</li>
										<br>
									</ul>
								</div>
							</div>
						</div>
						
						<div class="account-product margin-big-top clearfix" style="width: 100%;">
							<div class="text-box-main padding-big fl mine-product" style="width: 100%;height: 65px;padding: 8px;">
								<div class="mine-product-content clearfix">
									<ul class="w50 fl lists" style="width: 33%;">
										<li class="w70 lists-border-list" style="border-bottom: 0px solid #e6e7e9;padding: 0 16px;">
											<p class="border-list-text">昨日提成：&nbsp;&nbsp;&nbsp;&nbsp;<em class="orange">${tools:formatMoney(order.proxyYestodayMoney) }</em>
											</p>
										</li>
										<br>
									</ul>
									<ul class="w50 fl lists" style="width: 33%;">
										<li class="w70 lists-border-list" style="border-bottom: 0px solid #e6e7e9;padding: 0 -20px;">
											<p class="border-list-text">最近七天提成：&nbsp;&nbsp;&nbsp;&nbsp;<em class="orange">${tools:formatMoney(order.proxySevenMoney) }</em>
											</p>
										</li>
										<br>
									</ul>
									<ul class="w50 fl lists" style="width: 34%;">
										<li class="w70 lists-border-list" style="border-bottom: 0px solid #e6e7e9;margin-left: 12%">
											<p class="border-list-text">所有提成：&nbsp;&nbsp;&nbsp;&nbsp;<em class="orange">${tools:formatMoney(order.proxyTotalMoney) }</em>
											</p>
										</li>
										<br>
									</ul>
								</div>
							</div>
						</div>
						
						<div class="account-product margin-big-top clearfix" style="width: 100%;">
							<div class="text-box-main padding-big fl mine-product" style="width: 100%;">
								<h2 class="h6 margin-big-bottom" style="margin-bottom: 5px;">所代理商户：<em class="h4 text-blue-deep">${merchantList.size() }家</em></h2>
								<div class="mine-product-content clearfix">
									<c:forEach items="${merchantList }" var="res" varStatus="s">
										<ul class="fl lists" style="width: 20%">
											<li class="w70 lists-border-list" style="border-bottom: 0px solid #e6e7e9;">
												<table style="margin-bottom: 10px;">
													<tr>
														<td rowspan="2">
															<img src="<%=request.getContextPath() %>/resources/img/avatar.php" width="50px;" height="50px;">
														</td>
														<td>账号：<em class="text-blue-deep">${res.merchant.count }</em></td>
													</tr>
													<tr>
														<td>QQ：${res.merchant.qq}</td>
													</tr>
												</table>
											</li>
										</ul>
									</c:forEach>
								</div>
							</div>
						</div>
						
					</c:when>
					<c:otherwise>
						<!--产品-->
						<div class="account-product margin-big-top clearfix">
							<div class="text-box-main padding-big fl mine-product" style="margin-right:1%">
								<h2 class="h6 margin-big-bottom">今日实时数据（<%=Tools.getCurrentDate() %>）</h2>
								<div class="mine-product-content clearfix">
									<ul class="w50 fl lists">
										<li class="w70 lists-border-list">
											<p class="border-list-text">成功支付：&nbsp;&nbsp;&nbsp;&nbsp;<em class="orange">${tools:formatMoney(order.successMoney) }</em>
											</p>
										</li>
										<br>
										<li class="w70 lists-border-list">
											<p class="border-list-text">处理中支付：&nbsp;<em class="orange">${tools:formatMoney(order.dealingMoney) }</em>
											</p>
										</li>
										<br>
										<li class="w70 lists-border-list">
											<p class="border-list-text">失败支付：&nbsp;&nbsp;&nbsp;&nbsp;<em class="orange">${tools:formatMoney(order.failMoney) }</em>
											</p>
										</li>
										<br>
									</ul>
									<ul class="w50 fl lists">
										<li class="w80 lists-border-list">
											<p class="border-list-text">成功笔数：&nbsp;&nbsp;&nbsp;&nbsp;<em class="orange">${order.successCount }</em>
											</p>
										</li>
										<br>
										<li class="w80 lists-border-list">
											<p class="border-list-text">处理中笔数：&nbsp;<em class="orange">${order.dealingCount }</em>
											</p>
										</li>
										<br>
										<li class="w80 lists-border-list">
											<p class="border-list-text">失败笔数：&nbsp;&nbsp;&nbsp;&nbsp;<em class="orange">${order.failCount }</em>
											</p>
										</li>
										<br>
									</ul>
								</div>
							</div>
							
							<div class="text-box-main padding-big fl w31">
								<h2 class="h6 margin-big-bottom">统计数据</h2>
								<ul class="w95 fl lists">
								<li class="w95 lists-border-list">
									<p class="border-list-text">昨日订单：&nbsp;&nbsp;&nbsp;&nbsp;<em class="orange">${tools:formatMoney(order.yestodaySuccessMoney) }</em>
									</p>
								</li>
								<br>
								<li class="w95 lists-border-list">
									<p class="border-list-text">最近七天订单：&nbsp;<em class="orange">${tools:formatMoney(order.sevenSuccessMoney) }</em>
									</p>
								</li>
								<br>
								<li class="w95 lists-border-list">
									<p class="border-list-text">所有订单：&nbsp;&nbsp;&nbsp;&nbsp;<em class="orange">${tools:formatMoney(order.totalSuccessMoney) }</em>
									</p>
								</li>
								<br>
							</ul>
							</div>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</body>
</html>