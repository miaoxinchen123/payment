<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<title>联付宝 | 您身边的支付管家</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/merchant/css/identify.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/merchant/css/layout.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/merchant/css/account.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/merchant/css/style.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/merchant/css/control_index.css">

<link rel="stylesheet" type="text/css" href="<c:url value='/resources/js/easyui/themes/default/easyui.css'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/js/easyui/themes/icon.css'/>"/>

<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/themes/default/style.css'/>"/>

<script type="text/javascript" src="<%=request.getContextPath() %>/merchant/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/merchant/js/haidao.offcial.general.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/merchant/js/select.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/merchant/js/haidao.validate.js"></script>
<%-- 第三方js加载 --%>
<script type="text/javascript" src="<c:url value='/resources/js/easyui/jquery.easyui.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/easyui/locale/easyui-lang-zh_CN.js'/>"></script>

<script>
	$(function(){
		/**
		 * 退出登录
		 */
		$('#loginOut').click(function() {
	        $.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {
	            if (r) {
	                location.href = '<%=request.getContextPath()%>/logout';
	            }
	        });
	    });
		
		$(".sidebar-title").live('click', function() {
			if ($(this).parent(".sidebar-nav").hasClass("sidebar-nav-fold")) {
				$(this).next().slideDown(200);
				$(this).parent(".sidebar-nav").removeClass("sidebar-nav-fold");
			} else {
				$(this).next().slideUp(200);
				$(this).parent(".sidebar-nav").addClass("sidebar-nav-fold");
			}
		});
  	})
</script>
