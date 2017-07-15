/**
 * 获取特定name属性值的控件
 * @param str
 * @returns {Array}
 */
function getElementsByName(str){
	str=new String(str);
	var myMatches = new Array();
	var allEls = document.getElementsByTagName("*")
	var len = allEls.length;
	for(var i = 0; i < len; i++){
		if(allEls[i].name == str || allEls[i].getAttribute("name") == str) {
			myMatches[myMatches.length] = allEls[i];
		}
	}
	
	return myMatches;
}

/**
 * 通过name获取表单
 * @param str
 * @returns {Array}
 */
function getInputElementsByName(str){
	str = new String(str);
	var myMatches=new Array();
	var allEls = document.getElementsByTagName("input")
	var len = allEls.length;
	for(var i = 0; i < len; i++) {
		if(allEls[i].name == str || allEls[i].getAttribute("name") == str) {
			myMatches[myMatches.length] = allEls[i];
		}
	}
	
	return myMatches;
}

/**
 * 全选
 * @param name
 */
function allCheck(name){
	var names = getInputElementsByName(name);
	var len = names.length;
	if(len > 0){
		for(var i = 0; i < len; i++){
			names[i].checked=true;
		} 
	}
}

/**
 * 全不选
 * @param name
 */
function allNoCheck(name){
	var names = getInputElementsByName(name);
	var len = names.length;
	if(len > 0){
		for(var i = 0; i < len;i++){
			names[i].checked=false;
		} 
	}
}

/**
 * 反选
 * @param name
 */
function reserveCheck(name){
	var names = getInputElementsByName(name);
	var len = names.length;
	if(len > 0){
		for(var i = 0; i < len;i++){
			if(names[i].checked)
		    	names[i].checked=false;
		    else
		    	names[i].checked=true;
	   }
	} 
}

/**
 * 是否只选中一条
 * @param name
 * @returns {Boolean}
 */
function isOnlyOneCheck(name){
	var items = getInputElementsByName(name);
    if(null == items) return false;
    if(items.length > 0){
    	var j = 0;
	    for(var i = 0; i < items.length; i++){
	        if (items[i].checked){
	        	j++;
	        }
	    }
	    
	    if(1 == j) return true;
	} else if(items.checked) {
		return true;
	}
    
    return false;
}

/**
 * 是否只选中一条datagrid
 * @param dgId
 * @returns {Boolean}
 */
function isOnlyOneCheckDG(dgId){
	if($("#" + dgId)) {
		var dgCheckedRows = $("#" + dgId).datagrid('getChecked');
		if(dgCheckedRows && 1 == dgCheckedRows.length) {
			return true;
		}
	}
    
    return false;
}

/**
 * 只选中一条记录值
 * @param name
 * @returns
 */
function onlyOneCheckValue(name){
	var items = getInputElementsByName(name);
    if(items == null) return '';
    
    if(items.length>0){
	    for (var i = 0; i < items.length; i++){
	        if (items[i].checked){
	        	return items[i].value;
	        }
	    }
	} else if(items.checked) {
	    	return items.value
	}
    
    return '';
}

/**
 * 只选中一条dataGrid时idField值
 * @param dgId
 * @returns
 */
function onlyOneCheckDGIdFieldVal(dgId){
	if($("#" + dgId)) {
  		var options = $("#"+dgId).datagrid("options");
  		if(options) {
  			var idField = options.idField;
  			if(idField) {
  				var dgCheckedRows = $("#" + dgId).datagrid('getChecked');
  				if(dgCheckedRows && 1 == dgCheckedRows.length) {
  					return $(dgCheckedRows).attr(idField);;
  				}
  			}
  		}
	}
    
    return '';
}

/**
 * 判断是否至少选中一条
 * @param name
 * @returns {Boolean}
 */
function isAtLeastOneCheck(name){
	var names = getInputElementsByName(name);
	var len = names.length;
	if(len > 0){
		var i=0;
		for(i=0;i<len;i++){
			if(names[i].checked){
				return true;
			} 
		}
	}
	
    return false;
}

/**
 * 判断是否至少选中一条
 * @param dgId
 * @returns {Boolean}
 */
function isAtLeastOneCheckDG(dgId){
	if($("#" + dgId)) {
		var dgCheckedRows = $("#" + dgId).datagrid('getChecked');
		if(dgCheckedRows && dgCheckedRows.length >= 1) {
			return true;
		}
	}
    
    return false;
}


/**
 * 导出Excel
 * @param queryFormId	 :查询表单ID
 * @param expUrl		 :导出Url
 * @param expTitle		 :导出窗口标题
 * @param expTip		 :导出窗口提示
 */
function exportExcel(queryFormId, expUrl, expTitle, expTip){
	if(!expTitle || "" == expTitle) expTitle = "提示";
	if(!expTip || "" == expTip) expTip = "是否确认导出?";
	
	$.messager.confirm(expTitle, expTip, function(btn) {
		if(btn) {
			var form = document.getElementById(queryFormId);
			var formurl = form.action;
			form.action = expUrl;
			form.target = "_blank";
			document.getElementById(queryFormId).submit();
			form.target = "_self";
			form.action = formurl;
		}
	});
	
}

/**
 * 返回字符串的副本,忽略前导空白和尾部空白
 * @param str	:待截取字符串
 * @returns
 */
function trim(str){
    return str.replace(/(^\s*)|(\s*$)/g, "");
}

/**
 * 选中一条记录并弹出操作窗体
 * @param inputName		:表单控件name
 * @param popupUrl		:弹出url
 * @param popupConfigs	:弹出窗口配置(仅支持json格式)
 * @param callBackFun   :回调函数
 * @param paramName		:参数name
 * @param errTip		:错误提示
 */
function checkAndPopup(inputName, popupUrl, popupConfigs, callBackFun, paramName, errTip){
	if(isOnlyOneCheck(inputName)){
		var value = onlyOneCheckValue(inputName);
		if(!paramName || "" == paramName) paramName = "id";
		EOpenWindow(popupUrl+"?"+paramName+'='+value, popupConfigs, callBackFun);
		
	} else {
		if(!errTip || "" == errTip) errTip = "请选中一条!";
		$.messager.alert("提示", errTip, "info");
	}
}

/**
 * 选中一条DataGrid复选框记录并弹出操作窗体
 * @param dgId			:dataGrid的id
 * @param popupUrl		:弹出url
 * @param popupConfigs	:弹出窗口配置(仅支持json格式)
 * @param callBackFun   :回调函数
 * @param paramName		:参数name
 * @param errTip		:错误提示
 */
function checkDGAndPopup(dgId, popupUrl, popupConfigs, callBackFun, paramName, errTip){
	if(isOnlyOneCheckDG(dgId)){
		var value = onlyOneCheckDGIdFieldVal(dgId);
		if(!paramName || "" == paramName) paramName = "id";
		EOpenWindow(popupUrl+"?"+paramName+'='+value, popupConfigs, callBackFun);
		
	} else {
		if(!errTip || "" == errTip) errTip = "请选中一条!";
		$.messager.alert("提示", errTip, "info");
	}
}

/**
 * 选中一条DataGrid复选框记录在新页面中显示
 * @param dgId			:dataGrid的id
 * @param popupUrl		:弹出url
 * @param popupConfigs	:弹出窗口配置(仅支持json格式)
 * @param callBackFun   :回调函数
 * @param paramName		:参数name
 * @param errTip		:错误提示
 */
function checkDGAndHrefup(dgId, popupUrl, popupConfigs, callBackFun, paramName, errTip){
	if(isOnlyOneCheckDG(dgId)){
		var value = onlyOneCheckDGIdFieldVal(dgId);
		if(!paramName || "" == paramName) paramName = "id";
		window.location.href=popupUrl+"?"+paramName+'='+value
	} else {
		if(!errTip || "" == errTip) errTip = "请选中一条!";
		$.messager.alert("提示", errTip, "info");
	}
}

/**
 * easyui弹出窗体
 * @param url		        :窗体url
 * @param propertyConfig	:窗体属性配置(仅支持json格式,{title:"测试",isMax:true})
 * @param callBackFun       :回调函数
 */
function EOpenWindow(url, propertyConfig, callBackFun){
	var defaultProperty = {
		title:  	   "&nbsp;",
		width: 	   	   "800",
		height: 	   "600",
		top:		   "0",
	    winId:  	   "open_window",
	    iconCls:       "panel-with-icon",
	    loadingMessage:"正在加载中...",
	    isMax:   	   false,
	    minimizable:   false,
	    maximizable:   false,
	    collapsible:   false,
	    isAutoDestroy: true
	};
	
	if("undefined" != typeof(propertyConfig)) {//窗体属性配置
		if("undefined" != typeof(propertyConfig.title)) defaultProperty.title = propertyConfig.title;
		if("undefined" != typeof(propertyConfig.width)) defaultProperty.width = propertyConfig.width;
		if("undefined" != typeof(propertyConfig.height)) defaultProperty.height = propertyConfig.height;
		if("undefined" != typeof(propertyConfig.top)) {//top设置
			defaultProperty.top = propertyConfig.top;
		} else if($(document) && $(document).height()) {
			defaultProperty.top = ($(document).height() - defaultProperty.height) / 2 - 20;
			if(0 > defaultProperty.top) defaultProperty.top = 0;
			if(defaultProperty.top > 200) defaultProperty.top = 200;
		}
		if("undefined" != typeof(propertyConfig.winId)) defaultProperty.winId = propertyConfig.winId;
		if("undefined" != typeof(propertyConfig.iconCls)) defaultProperty.iconCls = propertyConfig.iconCls;
		if("undefined" != typeof(propertyConfig.loadingMessage)) defaultProperty.loadingMessage = propertyConfig.loadingMessage;
		if("undefined" != typeof(propertyConfig.minimizable)) defaultProperty.minimizable = propertyConfig.minimizable;
		if("undefined" != typeof(propertyConfig.maximizable)) defaultProperty.maximizable = propertyConfig.maximizable;
		if("undefined" != typeof(propertyConfig.collapsible)) defaultProperty.collapsible = propertyConfig.collapsible;
		if("undefined" != typeof(propertyConfig.isMax)) defaultProperty.isMax = propertyConfig.isMax;
		if("undefined" != typeof(propertyConfig.isAutoDestroy)) defaultProperty.isAutoDestroy = propertyConfig.isAutoDestroy;
	}
	
	var $win = window.$("<div id=\"" + defaultProperty.winId  + "\"></div>").appendTo(window.top.document.body);
	
	$win.window({
		content: createFrame(url),
		loadingMessage: defaultProperty.loadingMessage,
	    title: defaultProperty.title,
	    modal: true,
	    height: defaultProperty.height,
	    width: defaultProperty.width,
	    cache: false,
	    top: defaultProperty.top,
	    iconCls: defaultProperty.iconCls,
	    minimizable: defaultProperty.minimizable,
	    maximizable: defaultProperty.maximizable,
	    collapsible: defaultProperty.collapsible,
	    closed: true,
	    onClose: function() {
	    	if(callBackFun) callBackFun();
	    	if(defaultProperty.isAutoDestroy) {//销毁窗体(不然窗体关闭会出问题)
	    		$(this).window("destroy"); 
	    	}
	    }
	});
	
	if(defaultProperty.maximizable && defaultProperty.isMax){
		$win.window('maximize');
	}
	
	$win.window('open');
	
	 if($("iframe") && $("iframe").parent(".panel-body")) {//去掉tab滚动条
		 $("iframe").parent(".panel-body").css("overflow-y","hidden");
	 }
}

/**
 * 创建frame
 * @param url			:请求Url
 * @param frameId		:frameId
 * @returns {String}
 */
function createFrame(url,frameId){
	var s = '<iframe ';
	if(frameId){
		s += 'id="'+ frameId +'"';
	}
	s += 'scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;" onload="Javascript:setIFrameHeight(this);setIFrameWidth(this);"></iframe>';
	
	return s;
}

/**
 * 自动设置iframe高度
 * @param obj	:iframe对象
 */
function setIFrameHeight(obj) { 
	var win = obj; 
	if(document.getElementById) { 
		if(win && !window.opera) { 
			if(win.contentDocument && win.contentDocument.body.offsetHeight) {
				win.height = win.contentDocument.body.offsetHeight; 
			} else if(win.Document && win.Document.body.scrollHeight) {
				win.height = win.Document.body.scrollHeight; 
			}
		} 
	} 
} 

/**
 * 自动设置iframe宽度
 * @param obj	:iframe对象
 */
function setIFrameWidth(obj) {
	var win = obj; 
	if(document.getElementById) { 
		if(win && !window.opera) { 
			if(win.contentDocument && win.contentDocument.body.offsetWidth) {
				win.width = win.contentDocument.body.offsetWidth; 
			} else if(win.Document && win.Document.body.scrollWidth) {
				win.width = win.Document.body.scrollWidth; 
			}
		} 
	} 

}

/**
 * 通过表单id刷新页面,并添加遮罩
 * @param formId		:表单id
 * @param maskConfig	:遮罩参数配置(isShow,showTips)
 */
function refreshPageByFormId(formId, maskConfig) {
	if("undefined" == typeof(formId) || "" == formId) {
		formId = "queryForm";
	}
	
	if(!$("#" + formId))	return;
	$("#" + formId).submit();
	
	var maskConfigParams = {isShow:true, showTips:"正在加载中..."};
	if("undefined" != typeof(maskConfig)) {
		if("undefined" != typeof(maskConfig.isShow)
				&& "" != maskConfig.isShow) {
			maskConfigParams.isShow = maskConfig.isShow;
		}
		
		if("undefined" != typeof(maskConfig.showTips)
				&& "" != maskConfig.showTips) {
			maskConfigParams.showTips = maskConfig.showTips;
		}
	}
	
	if(true == maskConfigParams.isShow) {
		$(document.body).height($(document).height());
		$(document.body).mask(maskConfigParams.showTips);	
	}
	
	maskConfigParams = null;
}

/**
 * 清空表单(主要用于查询界面重置按钮)
 * @param formId		:表单id
 */
function emptyForm(formId) {//重置
	if("undefined" == typeof(formId) || "" == formId) {
		formId = "queryForm";
	}
	$(':input','#' + formId)
	 .not(':button, :submit, :reset, :hidden').val('')  
	 .removeAttr('checked')  
	 .removeAttr('selected');
}

/**
 * 表单异步提交
 * @param formId      :表单ID
 * @param callBackFun :回调函数
 */
function formAjaxSubmit(formId, callBackFun){
	var aform = $("#"+formId);
	if(aform.form('validate')){
		$.messager.confirm('提示', '您确定要提交吗?', function(btn) {
			 if(btn) {
				$(document.body).height($(document).height());
				$(document.body).mask('正在处理...');
             	$("#submitBtn").attr("disabled",true);
				 $.ajax({
					  	url: aform.attr("action"),
					  	data:aform.serialize(),
					  	type:'POST',
					  	dataType: "json",
					  	success: function(data){
					  		$("#submitBtn").attr("disabled", false);
					  		$(document.body).unmask();
		            		$.messager.alert("提示", data.message, "info", function() {
		            			if(callBackFun) {
		            				callBackFun(data);
		            			}
			            	});
				   		},
					   	error: function() {
					   		$("#submitBtn").attr("disabled", false);
					   		$(document.body).unmask();
					   		$.messager.alert("提示", "操作失败(可能网络问题)! ", "error");
			            }
				  });
			 }
		});
	}
}