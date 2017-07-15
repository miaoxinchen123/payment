<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib prefix="tools" uri="/WEB-INF/tlds/tools.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
	<head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>联付宝 | 您身边的支付管家</title>
		<%@ include file="/WEB-INF/html/merchant_head.jsp"%>
		<link rel="stylesheet" type="text/css" href="<c:url value='/resources/js/loadmask/jquery.loadmask.css'/>"/>
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
			   var money = $('#money').val();
			   if(parseFloat(money)>0){
					var charge = (money*(5)/1000).toFixed(2);
           	   		if(charge<5){
           	   			charge =5 
           	   		}else if(charge>50){
           	   			charge =50
           	   		}
				   $('#charge').text(charge);
			   }
		   });
               
           function countCharge(value){
        	   var reg = /^\d+(?=\.{0,1}\d+$|$)/
           	   if(reg.exec(value)){
           	   		var charge = (value*(5)/1000).toFixed(2);
           	   		if(charge<5){
           	   			charge =5 
           	   		}else if(charge>50){
           	   			charge =50
           	   		}
           	   		var userBalance = $('#userBalance').val();
           	   		if(parseFloat(charge)+parseFloat(value)>parseFloat(userBalance)){
           	   			alert("已超过最大提现金额");
           	   			$('#money').val("");
           	   			$('#charge').text("");
           	   		}else{
           	   			$('#charge').text(charge);
           	   		}
          		   } else {
           		   alert("请输入大于0的数字");
           		   $('#money').val("");
          		   }
              }
               
           	var timer = null;
			function ajaxVerifyCode() {
				if(null != timer) clearInterval(timer);
				var countdown = 60; 
				timer = setInterval(function() {
					if(0 == countdown) { 
				    	$("#verifyCodeBtn").attr('onclick','ajaxVerifyCode();'); 
				        $("#verifyCodeBtn").text("获取短信验证码"); 
				        clearInterval(timer);
				    } else {
				    	$('#verifyCode').val("");
				    	$("#verifyCodeBtn").removeAttr('onclick');
				    	$("#verifyCodeBtn").text("重新发送(" + countdown + "s)"); 
				        countdown--;
				    }
				}, 1000);
				
				$.ajax({
				  	url: "<%=request.getContextPath()%>/payment/messageService/send",
				  	data:"phoneNum=${user.phone}",
				  	type:'POST',
				  	dataType: "json",
				  	success: function(data){
			   		},
				   	error: function() {
				   		$.messager.alert("提示", "操作失败(可能网络问题)! ", "error");
		            }
			  });
			}
			
			function submit() {
				var code = $('#verifyCode').val();
			    if (code ==""){
			    	   alert("请输入验证码");
			    }else{
			    	$.ajax({
					  	url: "<%=request.getContextPath()%>/payment/messageService/validate",
					  	data:"phoneNum=${user.phone}&code="+code,
					  	type:'POST',
					  	dataType: "json",
					  	success: function(data){
					  		if(data.status == "ERROR"){
					  			alert(data.message);
					  		}else{
					  			$('#form_addWithDraw').submit();
					  		}
					  		
				   		},
					   	error: function() {
					   		$.messager.alert("提示", "操作失败(可能网络问题)! ", "error");
			            }
				  });
			    }
				
			}
               
			function submitForm(){
				formAjaxSubmit('form_addWithDraw', closeWin);
			}
			
               
         </script>
		
	</head>
	
	<body>
		<%@ include file="/WEB-INF/html/merchant_body.jsp" %>	
		<div class="view-product">
			<div class="authority">
				<div class="authority-head">
		            <div class="manage-head">
		                <h6 class="padding-left manage-head-con">余额提现</h6>
		            </div>
				</div>
				<form:form id="form_addWithDraw" modelAttribute="entity"  action='<%=request.getContextPath() +  "/payment/withDrawService/saveWithDraw"%>' method="POST">	
				<div class="authority-content" style="width:1200px;">
						<div class="list-content show">
							<div class="offcial-table tr-border margin-big-top clearfix">
								<div class="tr-th clearfix">
									<div class="th1 w15">
										<p class="p1">收款账户  :</p>
									</div>
									<div class="th1 w85">
										<c:forEach items="${cardList }" var="res" varStatus="s">
											<span style="float:left; margin-left:10px">
												<input type="radio" name="bank.id" value="${res.id }">
												<img src="<%=request.getContextPath() %>/${res.picPath}" 
													style="width: 235px; height: 55px;">
											</span>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<c:if test="${0 == s.count % 4 }">
												<br>
											</c:if>
										</c:forEach>
									</div>
								</div>
								<div class="tr clearfix border-bottom-none">
									<div class="td1 w15">
										<p class="p1">结算方式  :</p>
									</div>
									<div class="td1 w85">
										<p class="p2">
										  网银 (${tools:toBalanceTypeName(user.balanceType)}) &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										  支付宝(${tools:toBalanceTypeName(user.balanceType)})  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										   微信 (${tools:toBalanceTypeName(user.balanceType)})</p>
									</div>
								</div>
								<div class="tr-th clearfix">
									<div class="th1 w15">
										<p class="p1">账户余额  :</p>
									</div>
									<div class="th1 w85">
									<input type="hidden" id="userBalance" value="${user.balance}"/>
										<p class="p2"><em class="text-red-deep">${tools:formatMoney(user.balance) }</em>&nbsp;元</p>
									</div>
								</div>
								<div class="tr clearfix border-bottom-none">
									<div class="td1 w15">
										<p class="p1">可提现余额  :</p>
									</div>
									<div class="td1 w85">
										<p class="p2"><em class="text-red-deep">${tools:formatMoney(user.balance) }</em>&nbsp;元</p>
									</div>
								</div>
								<div class="tr-th clearfix">
									<div class="th1 w15">
											<p class="p1">提现金额  :</p>
									</div>
									<div class="th1 w85">
										<p class="p2"><form:input path="money" size="15"  onblur="countCharge(value)"/>&nbsp;元</p>
									</div>
								</div>
								<div class="tr clearfix border-bottom-none">
									<div class="td1 w15">
										<p class="p1">手续费  :</p>
									</div>
									<div class="td1 w85">
										<p class="p2"><em class="text-red-deep" id="charge"></em>&nbsp;元</p>
									</div>
								</div>
								<div class="tr-th clearfix">
									<div class="th1 w15">
										<p class="p1">手机验证码  :</p>
									</div>
									<div class="th1 w85" >
										<p class="p2"><form:input path="verifyCode" size="15" validate="{type:'required'}"/>&nbsp;<a class="button bg-green-deep" onclick="ajaxVerifyCode();" id="verifyCodeBtn">获取验证码</a></p>
									</div>
								</div>
								<div class="tr clearfix border-bottom-none">
									<div class="td1 w15">
									</div>
									<div class="td1 w85">
										<p class="p2"><a class="margin-left button btn-red text-white" onclick="submit();" id="verifyCodeBtn">立即提现</a></p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form:form>
			</div>
		</div>			

</body></html>