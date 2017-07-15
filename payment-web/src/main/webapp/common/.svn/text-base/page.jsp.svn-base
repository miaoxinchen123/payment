<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.supwisdom.stusv.eplatform.core.api.framework.Page" %>
<%
	/*参数初始化*/
	String pageName = request.getParameter("pageName");
	String formId = request.getParameter("formId");
	String pageList = request.getParameter("pageList");
	if(null == pageName || "" == pageName) pageName = "page";
	if(null == formId || "" == formId) formId = "queryForm";
	if(null == pageList || "" == pageList) pageList = "[10,20,30,50]";
	
	/*分页参数获取*/
	Page pageInfo = (Page)request.getAttribute(pageName);
	long count = 0;
	int pageSize = 0;
	int pageNo = 1;
	if(null != pageInfo) {
		count = pageInfo.getCount();
		pageSize = pageInfo.getPageSize();
		pageNo = pageInfo.getPageNo();
	}
	
	if(count > pageSize) {
%>
	<div class="easyui-panel" style="width: 100%; border-top-width: 0px;">
		<div id="pageTag"></div>
	</div>
	<script type="text/javascript">
		$(function() {//分页标签
			$("#pageTag").pagination({
				total:<%=count%>,
				pageSize:<%=pageSize%>,
				pageNumber:<%=pageNo%>,
				pageList:<%=pageList%>,
				layout:["list","sep","first","prev","sep","links","sep","next","last","sep","manual"],
				onSelectPage:function(pageNo, pageSize) {//页码调用
					var queryForm = $('<%="#" + formId%>');
					if(queryForm) {
						/*pageSize记录*/
						var pageSizeTag = queryForm.find("input[name='pageSize']:first");
						if(pageSizeTag && 1 == pageSizeTag.size()) {
							pageSizeTag.attr("value", pageSize);
						} else {
							queryForm.append("<input type='hidden' name='pageSize' value='" + pageSize + "'>")
						}
						/*pageNo记录*/
						var pageNoTag = queryForm.find("input[name='pageNo']:first");
						if(pageNoTag && 1 == pageNoTag.size()) {
							pageNoTag.attr("value", pageNo);
						} else {
							queryForm.append("<input type='hidden' name='pageNo' value='" + pageNo + "'>")
						}
						$(document.body).height($(document).height());
						$(document.body).mask("正在加载中...");	
						queryForm.submit();
					}
				},
				onChangePageSize:function(pageSize) {
					$(this).pageNumber = 1;
				}
			});
		});
	</script>
<%} else if(0 == count) {%>
	<div class="easyui-panel" style="width: 100%;border-top-width: 0px;">
		<div id="pageTag" style="text-align: center; font-size: 15px;">没有符合条件的数据!</div>
	</div>
<%} %>
