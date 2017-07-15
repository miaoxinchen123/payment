<%-- 标签引入 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>  

<%-- 第三方css加载 --%>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/js/easyui/themes/default/easyui.css'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/js/easyui/themes/icon.css'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/js/loadmask/jquery.loadmask.css'/>"/>

<%-- 本地css加载 --%>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/themes/default/message_icon.css'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/themes/default/button_style.css'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/themes/default/style.css'/>"/>

<%-- 第三方js加载 --%>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/loadmask/jquery.loadmask.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/easyui/jquery.easyui.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/easyui/locale/easyui-lang-zh_CN.js'/>"></script>

<%-- 本地js加载 --%>
<script type="text/javascript" src="<c:url value='/resources/js/validateForm.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/utils.js'/>"></script>