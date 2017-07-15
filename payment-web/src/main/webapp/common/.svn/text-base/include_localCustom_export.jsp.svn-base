<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<a href='#' onclick="openPageZdyExpWin('${param.loadUrl}')" class="linkbutton"><i class="input-linkbutton"></i>自定义导出</a>
<div id="win_zdy_export" class="easyui-window" title="自定义导出" data-options="modal:true,closed:true" style="padding:5px;">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center'" style="">
			<ul id="pagezyd_export_tree"></ul>
		</div>
		<div data-options="region:'south',border:false" style="text-align:right;padding:5px 0 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="###" onclick="javascript:exportZdy('${param.formId}','${param.expUrl}');">导出Excel</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-undo'" href="###" onclick="javascript:closeZdy_export();">关闭</a>
		</div>
	</div>
</div>
<script type="text/javascript">
	/**
	* 自定义导出列表弹出框
	* @param loadUrl	:加载自定义导出字段url
	*/
	function openPageZdyExpWin(loadUrl){
		var top = ($(document).height() - 400) / 2;
		var left = ($(document).width() - 400) / 2;
		if(0 > top) top = 50;
		if(top > 200) top = 200;
		if(0 > left) left = 100;
		$("#win_zdy_export").window({left:left,top:top,width:400,height:400});
		$("#win_zdy_export").window('open');
		var loading = false;
		$("#pagezyd_export_tree").tree({
			url: loadUrl,
			animate: true,
			checkbox:true,
			onBeforeLoad:function(node, param){ 
			    loading = true; 
			}, 
			onLoadSuccess:function(node, data){ 
			    loading = false; 
			}, 
			onClick: function(node){
				if(node.checked){
					node.checked=false;
					$("#pagezyd_export_tree").tree('uncheck',node.target);
				}else{
					node.checked=true;
					$("#pagezyd_export_tree").tree('check',node.target);
				}
			}	
		});
	}
	
	/**
	* 自定义列导出
	* @param formId		 :form表单ID
	* @param expUrl		 :导出url
	*/
	function exportZdy(formId, expUrl){
		var chkNodes = $('#pagezyd_export_tree').tree('getChecked');
		var queryForm = $("#" + formId);
		if(queryForm) {
			if(chkNodes && chkNodes.length > 0) {
				$("#" + formId + " input[name=expKeys]").each(function() {//清除导出字段
					$(this).remove();
				});
				
				for(var i = 0; i < chkNodes.length; i++) {//导出字段Key赋值
					queryForm.append("<input type='hidden' name='expKeys' value='" + chkNodes[i].id + "'>");
				}
				
				exportExcel(formId, expUrl);
			} else {
				$.messager.alert("提示", "请选择要导出字段?", "info");
			}
		} else {
			$.messager.alert("提示", "不存在form表单id为" + formId + "!", "info");
		}
	}
	
	function closeZdy_export(){
		$("#win_zdy_export").window('close');
	}
</script>
