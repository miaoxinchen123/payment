<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
	  	<title>系统后台管理</title>
	  	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/js/easyui/themes/default/easyui.css'/>"/>
	  	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/themes/default/style.css'/>"/>
	  	<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js'/>"></script>
	  	<script type="text/javascript" src="<c:url value='/resources/js/easyui/jquery.easyui.min.js'/>"></script>
	  	<script type="text/javascript" src="<c:url value='/resources/js/easyui/locale/easyui-lang-zh_CN.js'/>"></script>
	  	<script type="text/javascript" src="<c:url value='/resources/js/backGroundMenu.js'/>"></script>
	  	<style type="text/css">
	  		#_subTitle_{
	  			margin-left: -10px;
	  		}
	  	</style>
	  	<script type="text/javascript">
	  		var M_Tabscount = 8;
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
				
				/**
				 * 修改密码
				 */
			 	$('#editpass').click(function() {
			 		showSubTabs("修改密码", "<%=request.getContextPath()%>/payment/user/chgPwd");
			    });
				
			 	$("#west_menu .accordion-header")[0].click();
		  		
		  	})
		  	
		  	/**
		  	* 创建首页
		  	*/
		  	function createHomeTab(subTabTitle, subTabUrl) {
		  		if(!$('#tab_div').tabs('exists', subTabTitle)) {
					 $('#tab_div').tabs('add', {
						 title: subTabTitle,
						 content: createFrame(omitExcessBackslash(subTabUrl))
					 });
					 
					 if($("iframe") && $("iframe").parent(".panel-body")) {//去掉tab滚动条
						 $("iframe").parent(".panel-body").css("overflow-y","hidden");
					 }
			    } else {
				   $('#tab_div').tabs('select',subTabTitle);
			    }
		  	}
	  	</script>
	</head>
	
	<body class="easyui-layout">
		<div data-options="region:'north'" class="north_top" style="height:100px">
		  <a id="logo_small">
		  	<img style="left: -70px;position: relative;width: 280px;" src="<c:url value='/resources/css/themes/default/logo_small.png'/>">
		  </a>
		  <a id="north_top_user_name" style="margin-left: -40px;">
		  	<img src="<c:url value='/resources/css/themes/default/basic_header.png'/>">
		  	${logOnUserName }
		  </a>
		  <div id="north_top_right">
		   <a href="#" id="noA">
		    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    </a>
		    <a href="#" id="editpass">
		    	<i class="icon-my-date icon-style-24-24"></i><br>
		    	&nbsp;&nbsp;&nbsp;&nbsp;修改密码&nbsp;&nbsp;&nbsp;
		    </a>
		    <a href="javascript:void(0)" id="loginOut">
		    	<i class="icon-quit icon-style-24-24"></i><br>
		   		&nbsp;&nbsp;&nbsp;&nbsp; 安全退出&nbsp;&nbsp;&nbsp;&nbsp;
		    </a>
		  </div>
		  <nav id="north_top_nav">
		  	<c:forEach items="${menuList }" var="topMenu" varStatus="topSta">
				<a href="javascript:void(0)" <c:if test="${topSta.isFirst() }"> class="active" </c:if> 
					id="${topMenu.menuId }"> ${topMenu.menuName }
			    </a>
			</c:forEach>
		  </nav>
		</div>
		<%--右键菜单 --%>
		<div id="mm" class="easyui-menu" style="width:150px;display: none">
			<div id="mm-tabupdate">刷新页面</div>
			<div class="menu-sep"></div>
			<div id="mm-tabclose">关闭页面</div>
			<div id="mm-tabcloseall">关闭全部页面</div>
			<div id="mm-tabcloseother">关闭其它页面</div>
			<div class="menu-sep" style="display: none"></div>
			<div id="mm-tabcloseright" style="display: none" >右侧全部关闭</div>
			<div id="mm-tabcloseleft" style="display: none">左侧全部关闭</div>
			<div class="menu-sep" style="display: none"></div>
			<div id="mm-exit" style="display: none">退出</div>
		</div>
		
		<div id="west_div" data-options="region:'west',split:false" title="<div id='_subTitle_'>${selTopMenuTitle }</div>"  style="width:180px;">
		  <div id="west_menu"  class="accordion accordion-noborder easyui-fluid">
		  	<c:forEach items="${menuList }" var="topMenu" varStatus="topSta">
		  		<div id="_menu_${topMenu.menuId }" <c:if test="${!topSta.isFirst() }"> style="display:none" </c:if> >
	  			    <%-- 判断是否存在二级菜单--%>
				    <c:if test="${null != topMenu.childrenMenus && topMenu.childrenMenus.size() > 0}">
			  			<c:forEach items="${topMenu.childrenMenus }" var="secondMenu">
			  				<c:choose>
			  					<%-- 二级菜单是叶子 --%>
			  					<c:when test="${null != secondMenu.leaf && secondMenu.leaf}">
			  						<div class="panel-header accordion-header" title="${secondMenu.menuName }">
								      <div class="panel-title panel-with-icon " style="margin-left: 15px;" menuUrl="<%=request.getContextPath() %>/${secondMenu.menuUrl }">
								      	${secondMenu.menuName }
								      </div>
								      <div class="panel-icon icon-style-20-20"></div>
								    </div>
			  					</c:when>
			  					<%-- 二级菜单非叶子 --%>
			  					<c:otherwise>
			  						<div class="panel-header accordion-header" >
								      <div class="panel-title panel-with-icon " style="margin-left: 15px;">
								      	${secondMenu.menuName }
								      </div>
								      <div class="panel-icon icon-style-20-20"></div>
								    </div>
								    <%-- 判断是否存在三级菜单--%>
								    <c:if test="${null != secondMenu.childrenMenus && secondMenu.childrenMenus.size() > 0}">
								    	<ul class="west_second_menu">
								    		<c:forEach items="${secondMenu.childrenMenus }" var="threeMenu">
									    		<li class="second-menu-selected" title="${threeMenu.menuName }">
									        		<a href="javascript:void(0)" menuUrl="<%=request.getContextPath() %>/${threeMenu.menuUrl }">
									        			<i class="icon-style-20-20 icon-second-out"></i>
									        			${threeMenu.menuName }
									        		</a>
									      		</li>
								    		</c:forEach>
								    	</ul>
								    </c:if>
			  					</c:otherwise>
			  				</c:choose>
			  			</c:forEach>
		  			</c:if>
		  		</div>
		  	</c:forEach>
		  </div>
		</div>
	
		<div data-options="region:'center',iconCls:'icon-ok'">
		  <div id="tab_div" class="easyui-tabs" data-options="fit:true,border:false,plain:true">
		  	<%--如果首页需要显示内容,共有三种途径(推荐使用第三种)：
		  	    1.直接在下面div中做相关显示操作
		  	    2.在下面data-options中配置href(注意:只能针对本地页面或者ajax提取页面)
		  	    3.在下面data-options中配置content(内部可以嵌套iframe,比如:content:createFrame('<c:url value='/stusv/security/ext/user/queryUser.action'/>'))
		  	--%>
		  	<!-- <div title="首页" data-options="notColsed:true" style="padding:20px; color:#606060">
		  	</div> -->
		  </div>
		</div>
		
		<div id="south_footer" data-options="region:'south',split:false" style="height:50px;"> ©2017  联付宝支付公司版权所有</div>
	</body>
</html>