/**
 * 表单验证js(集成jquery easyui中validatebox)
 */
function initValidateForm(){
	$("form input[validate]").each(function() {//表单标签验证(input)
		if(!$(this).attr("data-options")) {
			var vstr = $(this).attr("validate");
			var conf = eval("(" + vstr + ")");
			var type = conf.type;
			var confs = conf.config || {};
			
			var isRequired = false;
			if ("undefined" != $.type(confs.allowBlank)) {
				isRequired = !confs.allowBlank;
			}
			
			if(type == 'required'){//必填项
				$(this).validatebox({  
					required: true
				}); 
			} else if(type == 'int'){//整型
				confs.precision = 0;
				$(this).numberbox(confs);  
				$(this).validatebox({  
					required: isRequired
				});
			}else if(type == 'thousandFloat'){//浮点型
				confs.precision = 2;
				confs.min=0.00;
				confs.max=1000;
				$(this).numberbox(confs);   
				$(this).validatebox({  
					required: isRequired
				});
			} else if(type == 'float'){//浮点型
				confs.precision = 2;
				confs.min=0.00;
				confs.max=100;
				$(this).numberbox(confs);   
				$(this).validatebox({  
					required: isRequired
				});
			} else if(type =='number') {//数字
				$(this).numberbox(confs);  
				$(this).validatebox({  
					required: isRequired
				});
			} else if(type == 'date') {//日期
				if(confs.format != null){
					var formatstr = confs.format;// Y-m-d H:i
					if(formatstr == 'Y-m') {
						$(this).datebox({
							required: isRequired,
							formatter: function(date){
								var y = date.getFullYear();
								var m = date.getMonth()+1;
								return y+'-'+(m<10?('0'+m):m);
							},
							parser: function(s){
								if (!s) return new Date();
								var ss = s.split('-');
								var y = parseInt(ss[0],10);
								var m = parseInt(ss[1],10);
								var d = parseInt(1,10);
								if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
									return new Date(y,m-1,d);
								}else {
									return new Date();
								}
							}
						});
					}else if(formatstr == 'Y-m-d H:i') {
						$(this).datetimebox({
							required: isRequired,
							showSeconds:false  
						}); 
					} else {
						$(this).datebox({
							required: isRequired
						}); 
					}
				} else {
					$(this).datebox({
						required: isRequired
					}); 
				}
			} else if(type == 'datetime') {
				$(this).datetimebox({
					required: isRequired,
					showSeconds:false  
				}); 
			} else {
				$(this).validatebox({  
					required: isRequired,
					validType: type
				}); 
			}
		}
	});
	
	$("form select[validate]").each(function(){//下拉框验证(目前只有必填验证)
		if(!$(this).attr("data-options")){
			var vstr = $(this).attr("validate");
			var conf = eval("(" + vstr + ")");
			var type = conf.type;
			var confs = conf.config || {};
			if(type == 'required'){
				$(this).validatebox({  
					required: true
				}); 
			}
		}	
	});
	
	$("form textarea[validate]").each(function(){//文本框验证
		if(!$(this).attr("data-options")){
			var vstr = $(this).attr("validate");
			var conf = eval("(" + vstr + ")");
			var type = conf.type;
			var confs = conf.config || {};
			
			var isRequired = false;
			if("undefined" != $.type(confs.allowBlank)) {
				isRequired = !confs.allowBlank;
			}
			
			if(type == 'required') {
				$(this).validatebox({  
					required: true
				}); 
			} else {
				$(this).validatebox({  
					required: isRequired,
					validType: type
				}); 
			}
			
		}	
	});
}

/**
 * 手动调用表单验证
 * @param formId	:form表单id
 */
function validateFormById(formId){
	$("#" + formId + " input[validate]").each(function() {//表单标签验证(input)
		if(!$(this).attr("data-options")) {
			var vstr = $(this).attr("validate");
			var conf = eval("(" + vstr + ")");
			var type = conf.type;
			var confs = conf.config || {};
			var min =conf.min;
			
			var isRequired = false;
			if ("undefined" != $.type(confs.allowBlank)) {
				isRequired = !confs.allowBlank;
			}
			
			
			if(type == 'required'){//必填项
				$(this).validatebox({  
					required: true
				}); 
			} else if(type == 'int'){//整型
				$(this).numberbox({   
				    precision:0   
				});  
				$(this).validatebox({  
					required: isRequired
				});
			} else if(type == 'float'){//浮点型
				$(this).numberbox({   
				    precision:2   
				    
				});  
				$(this).validatebox({  
					required: isRequired
				});
			} else if(type =='number') {//数字
				$(this).numberbox({});  
				$(this).validatebox({  
					required: isRequired
				});
			} else if(type == 'date') {//日期
				if(confs.format != null){
					var formatstr = confs.format;// Y-m-d H:i
					if(formatstr == 'Y-m') {
						$(this).datebox({
							required: isRequired,
							formatter: function(date){
								var y = date.getFullYear();
								var m = date.getMonth()+1;
								return y+'-'+(m<10?('0'+m):m);
							},
							parser: function(s){
								if (!s) return new Date();
								var ss = s.split('-');
								var y = parseInt(ss[0],10);
								var m = parseInt(ss[1],10);
								var d = parseInt(1,10);
								if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
									return new Date(y,m-1,d);
								}else {
									return new Date();
								}
							}
						});
					}else if(formatstr == 'Y-m-d H:i') {
						$(this).datetimebox({
							required: isRequired,
							showSeconds:false  
						}); 
					} else {
						$(this).datebox({
							required: isRequired
						}); 
					}
				} else {
					$(this).datebox({
						required: isRequired
					}); 
				}
			} else if(type == 'datetime') {
				$(this).datetimebox({
					required: isRequired,
					showSeconds:false  
				}); 
			} else {
				$(this).validatebox({  
					required: isRequired,
					validType: type
				}); 
			}
		}
	});
	
	$("#" + formId + " select[validate]").each(function(){//下拉框验证(目前只有必填验证)
		if(!$(this).attr("data-options")){
			var vstr = $(this).attr("validate");
			var conf = eval("(" + vstr + ")");
			var type = conf.type;
			var confs = conf.config || {};
			if(type == 'required'){
				$(this).validatebox({  
					required: true
				}); 
			}
		}	
	});
	
	$("#" + formId + " textarea[validate]").each(function(){//文本框验证
		if(!$(this).attr("data-options")){
			var vstr = $(this).attr("validate");
			var conf = eval("(" + vstr + ")");
			var type = conf.type;
			var confs = conf.config || {};
			
			var isRequired = false;
			if("undefined" != $.type(confs.allowBlank)) {
				isRequired = !confs.allowBlank;
			}
			
			if(type == 'required') {
				$(this).validatebox({  
					required: true
				}); 
			} else {
				$(this).validatebox({  
					required: isRequired,
					validType: type
				}); 
			}
				
		}	
	});
}

/**
 * 绑定submit自动验证表单
 */
function validateFormBySubmit(){
	$("form").each(function(){
		var submit = $(this).find(":submit");
		submit.on('click',function(){
			if($(this).parents("form").form('validate')){
		        return true;
		    }
			
			return false;
		});
	});	
}

/**
 * 界面渲染后相关配置
 */
$(document).ready(function(){
	if($(document).height() < $(window).height()){
		$(document).height($(window).height());
	}
	initValidateForm();
	validateFormBySubmit();
	$.ajaxSetup({timeout:900000}); 
	
	if($("#pageTag")) {//设置分页框自适应
		var parentDiv = $("#pageTag").parent();
		if(parentDiv) {
			parentDiv.attr("style", "border-top-width: 0px;");
			if(parentDiv.parent()) {
				parentDiv.parent().attr("style", "border-top-width: 0px;");
			}
			
			parentDiv = null;
		}
	}
	
});

