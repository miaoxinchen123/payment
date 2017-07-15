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
	<style type="text/css">
		.offcial-table table td {
			text-align: left;
		}
	</style>
	
    <script type="text/javascript">
    	function refreshPage() {
    		$('#accountManageSpan').click();
    	}
    
    	$(function(){
    		$('#userCenterDiv').click();
      	})
    </script>
</head>

<body>
	<%@ include file="/WEB-INF/html/merchant_body.jsp" %>	
		<div class="view-product">
				<div class="manage account-manage">
					<div class="manage-head">
						<h6 class="layout padding-left manage-head-con">&nbsp;账号管理</h6>
					</div>
					<dl class="account-basic clearfix" style="border-bottom:0px dashed #dedede;">
						<dt class="fl">
							<p class="account-head">
								<img src="<%=request.getContextPath() %>/resources/img/avatar.php">
							</p>
						</dt>
						<dd class="fl margin-large-left margin-small-top">
							<p class="text-small">
								<span class="show fl base-name">用户Key</span>:<span class="margin-left">${user.id } </span>
							</p>
							<p class="text-small">
								<span class="show fl base-name">登录账号</span>:<span class="margin-left">${user.count } </span>
							</p>
							<p class="text-small">
								<span class="show fl base-name">QQ</span>:<span class="margin-left">${user.qq }</span>
							</p>
							<p class="text-small">
								<span class="show fl base-name">支付网站</span>:<span class="margin-left">${user.hosts }</span>
							</p>
						</dd>
					</dl>
					<ul class="accound-bund">
						<li style="padding: 20px 20px;margin-top: -30px;">
							<span class="bund-class"></span>
							<span class="w45"></span>
							<span class="fr margin-large-right">
								<i class="icon icon-been-set fl"></i>
								<a href="javascript:void(0)" class="button-word1 margin-left"
									onclick="javascript:EOpenWindow('<c:url value='/payment/account/editBasicInfo'/>', {title:'修改基本信息',height:230,width:420}, refreshPage)">修改</a>
							</span>
						</li>
					</ul>
					<ul class="accound-bund">
						<li class="clearfix">
							<span class="bund-class">登录密码</span>
							<span class="w45">安全性高的密码可以使账号更安全，建议您定期更换密码，设置一个包含字母，符号或数字中至少两项且长度超过6位的密码。</span>
							<span class="fr margin-large-right">
								<i class="icon icon-been-set fl"></i>
								<a href="javascript:void(0)" class="button-word1 margin-left"
									onclick="javascript:EOpenWindow('<c:url value='/payment/account/editPwd'/>', {title:'修改密码',height:230,width:420}, refreshPage)">修改</a>
							</span>
						</li>
						<li class="clearfix">
							<span class="bund-class">手机绑定</span>
							<span class="w45">您已绑定了手机：<em class="text-green-deep">${user.phone }</em></span>
							<span class="fr margin-large-right">
								<i class="icon icon-been-set fl"></i>
								<a href="javascript:void(0)" class="button-word1 margin-left"
									onclick="javascript:EOpenWindow('<c:url value='/payment/account/editPhone'/>', {title:'修改手机号码',height:250,width:500}, refreshPage)">修改</a>
							</span>
						</li>
						<c:if test="${proxyUser!=null}">
							<li class="clearfix">
								<span class="bund-class">代理商户</span>
								<span class="w45">代理商名称：<em class="text-green-deep">${proxyUser.count }</em></span>
								<span class="w45">代理商手机号码：<em class="text-green-deep">${proxyUser.phone }</em></span>
							</li>
						</c:if>
					
					</ul>
			</div>
			
			<div class="info-center" style="height: 110px;">
	            <div class="manage-head">
	                <h6 class="padding-left manage-head-con" style="border-left: 0px solid #0098cd;">
	                	<span class="fl padding-large-right manage-title">支付费率</span>
	                </h6>
	            </div>
	            <div class="offcial-table input-table table-margin clearfix"
	            	style="border:0px solid #e8e8e9;font-size:12px;position:relative;line-height: 40px;">
		            <table style="width: 100%">
		            	<tr>
		            		<td style="padding-left: 10px;">
		            			支付宝支付(PC)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${tools:formatThousandRate(userRate.alipayPc) }‰
		            		</td>
		            		<td>支付宝支付(WAP)&nbsp;&nbsp;&nbsp;&nbsp;${tools:formatThousandRate(userRate.alipayWap) }‰</td>
		            	</tr>
		            	<tr>
		            		<td style="padding-left: 10px;">
		            			微信支付(PC)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${tools:formatThousandRate(userRate.wechatPc) }‰
		            		</td>
		            		<td>微信支付(WAP)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${tools:formatThousandRate(userRate.wechatWap) }‰</td>
		            	</tr>
		            	<tr>
		            		<td style="padding-left: 10px;">
		            			银行卡支付(PC)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${tools:formatThousandRate(userRate.cardPc) }‰
		            		</td>
		            		<td>银行卡支付(WAP)&nbsp;&nbsp;&nbsp;&nbsp;${tools:formatThousandRate(userRate.cardWap) }‰</td>
		            	</tr>
		            </table>
	            </div>
		    </div>
		</div>
	</div>
</body>
</html>