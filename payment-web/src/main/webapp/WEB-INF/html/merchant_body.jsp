<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="view-topbar">
<div class="topbar-console">
	<div class="tobar-head fl">
		<a href="javascript:void(0)" class="topbar-logo fl">
		<span><img src="<%=request.getContextPath() %>/merchant/img/logo.png" width="50" height="50"></span>
		</a>
		<a href="javascript:void(0)" class="topbar-home-link topbar-btn text-center fl">
			<span>联付宝（原闪付）</span></a>
	</div>
</div>
<div class="topbar-info">
	<ul class="fr">
		<li class="fl topbar-info-item">
		<div class="dropdown">
			<a href="javascript:void(0)" class="topbar-btn">
				<span class="fl text-normal">你好，<%=com.tengfei.payment.util.UserUtil.getCurrentUserName() %></span>
					<span class="icon-arrow-down"></span>
				</a>
			</div>
			</li>
			<li class="fl topbar-info-item">
			<div class="dropdown">
				<a href="javascript:void(0)" class="topbar-btn" id="loginOut">
					<span class="fl text-normal">退出</span>
					<span class="icon-arrow-down"></span>
				</a>
			</div>
			</li>
		</ul>
	</div>
</div>
<div class="view-body">
	<div class="view-sidebar">
		<div class="sidebar-content">
			<div class="sidebar-nav sidebar-nav-fold">
				<div class="sidebar-title">
					<a href="<%=request.getContextPath() %>/payment/logon">
						<span class="icon"><b class="fl icon-arrow-down"></b></span>
						<span class="text-normal">信息概览</span>
					</a>
				</div>
			</div>
			<div class="sidebar-nav sidebar-nav-fold">
				<div class="sidebar-title">
					<a href="<%=request.getContextPath() %>/payment/withDrawService/page">
						<span class="icon"><b class="fl icon-arrow-down"></b></span>
						<span class="text-normal">结算中心</span>
					</a>
				</div>
			</div>
			<div class="sidebar-nav sidebar-nav-fold">
				<div class="sidebar-title">
					<a href="<%=request.getContextPath() %>/payment/orderService/page">
						<span class="icon"><b class="fl icon-arrow-down"></b></span>
						<span class="text-normal">订单中心</span>
					</a>
				</div>
			</div>
			<div class="sidebar-nav sidebar-nav-fold">
				<div class="sidebar-title" id="userCenterDiv">
					<a href="javascript:void(0)">
						<span class="icon"><b class="fl icon-arrow-down"></b></span>
						<span class="text-normal">用户中心</span>
					</a>
				</div>
				<ul class="sidebar-trans" style="overflow: hidden; display: none;">
				<li>
					<a href="<%=request.getContextPath() %>/payment/account/edit">
						<b class="sidebar-icon"><img src="<%=request.getContextPath() %>/merchant/img/icon_cost.png" width="16" height="16"></b>
						<span class="text-normal" id="accountManageSpan">账号管理</span>
					</a>
				</li>
				<li>
					<a href="<%=request.getContextPath() %>/payment/user_card/page">
						<b class="sidebar-icon"><img src="<%=request.getContextPath() %>/merchant/img/icon_authentication.png" width="16" height="16"></b>
						<span class="text-normal" id="cardManageSpan">银行卡管理</span>
					</a>
				</li>
			</ul>
		</div>
	</div>
</div>
