<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:url value="/stusv/module/common/excel/field/downloadTemplate.action" var="downloadTemplateUrl">
	<c:param name="tableId" value="${tableId}" />
</c:url>
<c:url value="/stusv/module/common/excel/table/delTempTable.action" var="delTempTableUrl">
	<c:param name="tableId" value="${tableId}" />
</c:url>
<c:url value="/stusv/module/common/excel/table/delVerificationRes.action" var="delVerificationRes">
	<c:param name="tableId" value="${tableId}" />
</c:url>
<c:url value="/stusv/module/common/excel/table/tableToTempTable.action" var="tableToTempTableUrl">
	<c:param name="tableId" value="${tableId}" />
</c:url>
<c:url value="/stusv/module/common/excel/verification/selectPverification.action" var="selectCheckUrl">
	<c:param name="tableId" value="${tableId}" />
</c:url>
<c:url value="/stusv/module/common/excel/his/selectHis.action" var="selectHisUrl">
	<c:param name="tableId" value="${tableId}" />
</c:url>
<c:url value="/stusv/module/common/excel/table/likeField.action" var="likeFieldUrl">
	<c:param name="tableId" value="${tableId}" />
	<c:param name="saveFName" value="${saveFName}" />
	<c:param name="saveProperties" value="${saveProperties}" />
</c:url>
<c:url value="/stusv/module/common/excel/verification/checkPverificationResult.action" var="checkPverificationResultUrl">
	<c:param name="tableId" value="${tableId}" />
</c:url>
<c:url value="/stusv/module/common/excel/verification/couldTempToFormalUrl.action" var="couldTempToFormalUrl">
	<c:param name="tableId" value="${tableId}" />
</c:url>
<c:url value="/stusv/module/common/excel/verification/viewPverificationResult.action" var="verificationResultUrl">
	<c:param name="tableId" value="${tableId}" />
</c:url>
<c:url value="/stusv/module/common/excel/verification/viewPverificationByOneResult.action" var="verificationResultByOneUrl"></c:url>

<table class="table_list_1" width="100%">
	<tr>
		<td class="t_l_1_title"><span>*</span> 导入方式：</td>
		<td>
			<select id="inputType" name="mapBean['inputType']" style="width: 200px;">
				<option value="0">清空导入</option>
				<option value="1">追加导入</option>
			</select>
		</td>
		<td class="t_l_1_title">备注说明：</td>
		<td><input id="fielddesc" name="mapBean['fielddesc']" type="text" style="width: 200px;"></td>
		
	</tr>
	<tr>
		<td class="t_l_1_title" width="15%"><span>*</span> Excel文件：</td>
		<td>
			<input class="easyui-filebox upload-btn-style" id="excelFile" name="excelFile" data-options="prompt:'选择文件',height:'22',buttonText:'浏览'" style="width: 200px;"> 
			<a href="#" class="easyui-linkbutton pipeijieguo_tck" style="margin-left: 65px;" onclick="submitFileForm()">上传</a></td>
		<td class="t_l_1_title">模版下载：</td>
		<td colspan="2">
			<a href="${downloadTemplateUrl}">${requestScope.tableName}Excel模版</a>
		</td>
	</tr>
</table>
<div class="clear-1" style="border-bottom: 1px #ccc solid;"></div>
<h1 class="page-title-h1">验证表信息</h1>
<div class="button_div">
	<a href="#" class="linkbutton add_xinshengxinxi_linkbutton" onclick="oneKeyVerification()"><i class="check-linkbutton"></i>一键校验</a> 
	<a href="#" class="linkbutton add_xinshengxinxi_linkbutton" onclick="viewVerificationResult();"><i class="view-linkbutton"></i>查看全部结果</a> 
	<a href="#" class="linkbutton add_xinshengxinxi_linkbutton" onclick="delTempTableData()"><i class="del-linkbutton"></i>清空临时表</a> 
	<a href="#" class="linkbutton input_xinshengxinxi_linkbutton" onclick="tempTableToTable()"><i class="input-linkbutton"></i>转入正式表</a>
</div>
<table id="dgcheck" class="easyui-datagrid" style="width: 100%; height: auto;" data-options="singleSelect:true,url:'${selectCheckUrl}',method:'get',multiSort:true,autoRowHeight:true,striped: true,collapsible:true">
	<thead>
		<tr>
			<th data-options="field:'rownum',width:40,align:'center'">序号</th>
			<th data-options="field:'viewname',width:250,align:'center'">名称</th>
			<th data-options="field:'remark',width:300,align:'center'">说明</th>
			<th data-options="field:'cstate',width:100,align:'center'">验证结果</th>
			<th data-options="field:'iscrucial',width:150,align:'center'">是否关键验证</th>
			<th data-options="field:'id',width:100,align:'center', formatter: changeFun">操作</th>
		</tr>
	</thead>
</table>
<h1 class="page-title-h1">导入历史记录</h1>
<table id="dghis" class="easyui-datagrid" style="width: 100%; height: auto;" data-options="singleSelect:true,url:'${selectHisUrl}',method:'get',multiSort:true,autoRowHeight:true,striped: true, collapsible:true">
	<thead>
		<tr>
			<th data-options="field:'rownum',width:40,align:'center'">序号</th>
			<th data-options="field:'createtime',width:200,align:'center'">导入时间
			</th>
			<th data-options="field:'viewname',width:200,align:'center'">导入名称
			</th>
			<th data-options="field:'rowcount',width:100,align:'center'">导入数量
			</th>
			<th data-options="field:'createid',width:100,align:'center'">操作员</th>
			<th data-options="field:'remark',width:300,align:'center'">备注</th>
		</tr>
	</thead>
</table>
<!--弹出窗口-->
<div id="dlg_jieguo" class="easyui-dialog" title="Fluid Dialog"
	style="width: 80%; height: 500px; max-width: 800px; padding: 20px"
	data-options="iconCls:'icon-save',closed: true,modal: true,cache: false">
</div>

<script type="text/javascript">
	window.aaa="";
	$(function() {
		setTimeout(function() {
			//修改上传文件输入框样式
			if ($("span.textbox filebox")) {
				$(".filebox").addClass("upload-btn-style");
				$(".upload-btn-style").css("height", "24");
				$(".upload-btn-style a").css({
					"right" : "-30%",
					"top" : "1px"
				});
			}
			<c:if test="${noxls!=null}">
				alert('${noxls}');
			</c:if>
			
			<c:if test="${saveFName!=null && saveProperties!=null}">
				EOpenWindow('${likeFieldUrl}',{height:480,title:'匹配结果'},
						function(){
							if(window.aaa!=null&&window.aaa!=""){
								EOpenWindow(window.aaa,{height:480,title:'校验结果'},
										function(){
											if('${forwordpath}'!=''){
					                			window.location.href="<c:url value='${forwordpath}?tableId=${tableId}'/>";
					                		}else{
					                			window.location.reload();
					                		}
								}); 
							}							
						});
			</c:if>
			
		}, 500);
		
		
	});
	/*清空临时表数据，并删除校验结果*/
	function delTempTableData(){
		$.ajax({     
		    type:'POST',
		    url:'${delTempTableUrl }',
            dataType: "json",
            timeout: 900000,
		    error:function(){     
		       alert('系统出错！');     
		    },     
		    success:function(data){     
		    	if("OK" == data.status) {
		    		$.ajax({     
		    		    type:'POST',
		    		    url:'${delVerificationRes}',
		                dataType: "json",
		                timeout: 900000,
		    		    error:function(){     
		    		       alert('系统出错！');     
		    		    },     
		    		    success:function(data){     
		    		    	if("OK" == data.status) {		                		
		                		$.messager.alert("提示", "删除成功! ", "info", function() {
		                			if('${forwordpath}'!=''){
			                			window.location.href="<c:url value='${forwordpath}?tableId=${tableId}'/>";
			                		}else{
			                			window.location.reload();
			                		}
		                    	});
		                		//window.location.href="<c:url value='/stusv/newstudents/listsafeguard/toUploadNewstudentsListSafeguard.action?tableId=${tableId}'/>";
		                		return;
		                	}else{
		                		$.messager.alert("提示", "删除失败! ");
		                	}		                	
		    		    }  
		    		});  
            	}else{
            		$.messager.alert("提示", "删除失败! ");
            	}  	
		    }  
		});  
	}
	/*临时表转入正式表*/
	function tempTableToTable(){
		$.ajax({     
		    type:'POST',
		    url:'${couldTempToFormalUrl }',
            dataType: "json",
            timeout: 900000,
		    error:function(){     
		       alert('系统出错！');     
		    },     
		    success:function(data){     
		    	if("OK" == data.status) {
		    		$.ajax({     
		    		    type:'POST',
		    		    url:'${tableToTempTableUrl }',
		                dataType: "json",
		                timeout: 900000,
		    		    error:function(){     
		    		       alert('系统出错！');     
		    		    },     
		    		    success:function(data){     
		    		    	if("OK" == data.status) {
		                		$.messager.alert("提示", data.message);
		                		return;
		                	}
		                	$.messager.alert("提示", data.message);
		    		    }  
		    		}); 
            	}else{
            		$.messager.alert("提示", "校验未全部通过,不能执行转入正式表操作! ");
            	}         	
		    }  
		}); 
	}
	/*一键校验*/
	function oneKeyVerification(){
		$.ajax({     
		    type:'POST',
		    url:'${checkPverificationResultUrl }',
            dataType: "json",
            timeout: 900000,
		    error:function(){     
		       alert('系统出错！');     
		    },     
		    success:function(data){     
		    	if("OK" == data.status) {
            		$.messager.alert("提示", "校验成功! ");
            		if('${forwordpath}'!=''){
            			window.location.href="<c:url value='${forwordpath}?tableId=${tableId}'/>";
            		}else{
            			window.location.reload();
            		}
            		//return;
            	}
            	//$.messager.alert("提示", "校验失败! ");
            	
		    }  
		}); 
	}
	/*查看全部结果*/
	function viewVerificationResult(){
		EOpenWindow('${verificationResultUrl}',{height:480,title:'验证结果'});
	}
	/*查看单个结果*/
	function viewVerificationByOneResult(verificationId){
		EOpenWindow('${verificationResultByOneUrl}?verificationId='+verificationId,{height:480,title:'验证结果'});
	}
	/*上传附件到服务器打开匹配字段对话框*/
	function submitFileForm(){
		if(isExitsFunction("checkParameter")){
			if(checkParameter()){
				return;
			}
		}		    		
		//获取配置字段的参数值
		var inputType=$("#inputType").val();
		var tempexcelFile=$("input[name='excelFile']").val();
		if(inputType==undefined || inputType==null || inputType==""){
			$.messager.alert("提示", "请选择导入方式！");
			return;
		}
		if(tempexcelFile==undefined || tempexcelFile==null || tempexcelFile==""){
			$.messager.alert("提示", "请选择上传文件！");
			return;
		}
		var tempexcelFiles = tempexcelFile.split(".");
		var xxx = tempexcelFiles[tempexcelFiles.length-1];
		if(xxx.toUpperCase()!="XLS" && xxx.toUpperCase()!="XLSX"){
			$.messager.alert("提示", "请选择EXCEL文件！");
			return;
		}		    		
		if($('#fileUploadForm').form('validate')){
			
			$.ajax({     
			    type:'POST',
			    url:'${delVerificationRes}',
	            dataType: "json",
	            timeout: 900000, 
	            error:function(){     
	 		       alert('系统出错！'); 
	            	if('${forwordpath}'!=''){
            			window.location.href="<c:url value='${forwordpath}?tableId=${tableId}'/>";
            		}else{
            			window.location.reload();
            		}
	 		      //window.location.href="<c:url value='/stusv/newstudents/listsafeguard/toUploadNewstudentsListSafeguard.action?tableId=${tableId}'/>";
	 		    }, 
			    success:function(data){     
			    	if("OK" == data.status) {
			    		$('#fileUploadForm').submit();
	            		return;
	            	}
	            	//$.messager.alert("提示", "删除失败! ");
			    }  
			});  
		}	
	}
	//ajax清空上次校验结果
	function deleteVerificationRes(){	
		$.ajax({     
		    type:'POST',
		    url:'${delVerificationRes}',
            dataType: "json",
            timeout: 900000, 
            error:function(){     
 		       //alert('系统出错！'); 
            	if('${forwordpath}'!=''){
        			window.location.href="<c:url value='${forwordpath}?tableId=${tableId}'/>";
        		}else{
        			window.location.reload();
        		}
 		      //window.location.href="<c:url value='/stusv/newstudents/listsafeguard/toUploadNewstudentsListSafeguard.action?tableId=${tableId}'/>";
 		    }, 
		    success:function(data){     
		    	if("OK" == data.status) {
		    		if('${forwordpath}'!=''){
            			window.location.href="<c:url value='${forwordpath}?tableId=${tableId}'/>";
            		}else{
            			window.location.reload();
            		}  		
		    		//window.location.href="<c:url value='/stusv/newstudents/listsafeguard/toUploadNewstudentsListSafeguard.action?tableId=${tableId}'/>";
            		return;
            	}
            	//$.messager.alert("提示", "删除失败! ");
		    }  
		});  
	}
	
	function changeFun (val) {
		return "<a href='#' onclick='viewVerificationByOneResult(\""+val+"\")'>查看结果</a>";
	}
	
	//是否存在指定函数 
	function isExitsFunction(funcName) {
	    try {
	        if (typeof(eval(funcName)) == "function") {
	            return true;
	        }
	    } catch(e) {}
	    return false;
	}

</script>

