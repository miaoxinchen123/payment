$.extend($.fn.validatebox.defaults.rules, {   
    minLength: {//验证最小输入长度  
        validator: function(value, param){   
            return value.length >= param[0];   
        },   
        message: 'Please enter at least {0} characters.'  
    },
    maxLength: {//验证最大输入长度  
        validator: function(value, param){   
            return value.length <= param[0];   
        },   
        message: 'Please enter at max {0} characters.'  
    },
	noff: {//可见字符验证(防止脚本注入)
	    validator: function(value){
	    	var reg=/^[^`%^+=|\\\][\]{\}:;<>\/?]*$/i;
	        return reg.test(value);   
	    },   
	    message: 'Input illegal characters.'  
	},
	cardNum:{//身份证号码验证
		 validator: function(value){
		    	var reg15=/^[\d]{6}[\d]{2}((0[1-9])|(11|12))([012][\d]|(30|31))[\d]{3}$/i;
		    	var reg18=/^[\d]{6}(19|20){1}[\d]{2}((0[1-9])|(11|12))([012][\d]|(30|31))[\d]{3}[x|X|\d]{1}$/i;
		        return reg15.test(value) || reg18.test(value) ;   
		    },   
		    message: 'Input illegal id card.'  
	},
	chinese: {//中文验证   
	    validator: function(value){
	    	var reg=/^[\u2E80-\u9FFF]+$/i;
	        return reg.test(value);   
	    },   
	    message: 'Please Input Chinese characters。'  
	},
	password: {//密码验证(6到20位由数字和大小写字母组成的密码)
	    validator: function(value){
	    	var reg=/^[\w\W]{6,20}$/i;
	        return reg.test(value);   
	    },   
	    message: 'Input illegal password characters.'  
	},
	string: {//数字和字母验证 
	    validator: function(value){
	    	var reg=/^[0-9a-zA-Z]*$/i;
	        return reg.test(value);   
	    },   
	    message: 'Input illegal characters.'  
	},
	mobile: {//手机号码验证  
	    validator: function(value){
	    	var reg=/^0{0,1}(13[0-9]?|15[0-9]?|18[0-9])[0-9]{8}$/i;
	        return reg.test(value);   
	    },   
	    message: 'Input Error Mobile Phone Number.'  
	},
	upperCase: {//大写英文字母验证   
	    validator: function(value){
	    	var reg=/^[A-Z]*$/g;
	        return reg.test(value);   
	    },   
	    message: 'Input uppercase.'  
	},
	postCode: {//邮政编码验证
	    validator: function(value){
	    	var reg=/^[0-9]{6}$/i;
	        return reg.test(value);   
	    },   
	    message: 'Input Error Post Code'  
	},
	serverValidate: {//服务端验证(param数组:[验证url,验证字段name,其他验证参数])
	    validator : function(value, param) {  
	    	var data = {};  
	        data[param[1]] = value;     //当前value所对应的name  
            var paramName = null;  
            var paramValue = null;  
            for(var i = 2; i < param.length; i ++){ 
            	paramName = $("#" + param[i]).attr("name");  
            	paramValue = $("#" + param[i]).attr("value");  
		        if(paramValue == ""){           //默认空值为"-1",防止参数传递转换成Numbe可能出现的异常  
		        	paramValue = "-1";  
		        } 
		        data[paramName] = paramValue;  
		    }  
		    var isValidate = $.ajax({  
                url : param[0],
                dataType : "json",  
                data : data,  
                async : false,  
                cache : false,  
                type : "post"  
            }).responseText;  
		    return isValidate == "true";  
		},  
		message : "请修正该字段"  
	}
});
if ($.fn.pagination){
	$.fn.pagination.defaults.beforePageText = '第';
	$.fn.pagination.defaults.afterPageText = '共{pages}页';
	$.fn.pagination.defaults.displayMsg = '显示{from}到{to},共{total}记录';
}
if ($.fn.datagrid){
	$.fn.datagrid.defaults.loadMsg = '正在处理, 请稍待...';
}
if ($.fn.treegrid && $.fn.datagrid){
	$.fn.treegrid.defaults.loadMsg = $.fn.datagrid.defaults.loadMsg;
}
if ($.messager){
	$.messager.defaults.ok = '确定';
	$.messager.defaults.cancel = '取消';
}
if ($.fn.validatebox){
	$.fn.validatebox.defaults.missingMessage = '该输入项为必输项';
	$.fn.validatebox.defaults.rules.email.message = '请输入有效的电子邮件地址';
	$.fn.validatebox.defaults.rules.url.message = '请输入有效的URL地址';
	$.fn.validatebox.defaults.rules.length.message = '输入内容长度必须介于{0}和{1}之间';
	$.fn.validatebox.defaults.rules.serverValidate.message = '请修正该字段';
	$.fn.validatebox.defaults.rules.minLength.message = '请输入至少{0}个字符';
	$.fn.validatebox.defaults.rules.maxLength.message = '请输入至多{0}个字符';
	$.fn.validatebox.defaults.rules.noff.message = '输入内容包含不合法的字符';
	$.fn.validatebox.defaults.rules.chinese.message = '输入内容包含非中文字符';
	$.fn.validatebox.defaults.rules.mobile.message = '输入手机号码不正确';
	$.fn.validatebox.defaults.rules.upperCase.message = '输入内容必须是大写英文字母';
	$.fn.validatebox.defaults.rules.postCode.message = '输入邮政编码不正确';
	$.fn.validatebox.defaults.rules.cardNum.message = '输入身份证号码不正确';
	$.fn.validatebox.defaults.rules.string.message = '请输入数字或英文字符';
	$.fn.validatebox.defaults.rules.password.message = '密码为数字、字母和符号任意组成，长度为6到20';
}
if ($.fn.numberbox){
	$.fn.numberbox.defaults.missingMessage = '该输入项为必输项';
}
if ($.fn.combobox){
	$.fn.combobox.defaults.missingMessage = '该输入项为必输项';
}
if ($.fn.combotree){
	$.fn.combotree.defaults.missingMessage = '该输入项为必输项';
}
if ($.fn.combogrid){
	$.fn.combogrid.defaults.missingMessage = '该输入项为必输项';
}
if ($.fn.calendar){
	$.fn.calendar.defaults.weeks = ['日','一','二','三','四','五','六'];
	$.fn.calendar.defaults.months = ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'];
}
if ($.fn.datebox){
	$.fn.datebox.defaults.currentText = '今天';
	$.fn.datebox.defaults.closeText = '关闭';
	$.fn.datebox.defaults.okText = '确定';
	$.fn.datebox.defaults.missingMessage = '该输入项为必输项';
	$.fn.datebox.defaults.formatter = function(date){
		var y = date.getFullYear();
		var m = date.getMonth()+1;
		var d = date.getDate();
		return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
	};
	$.fn.datebox.defaults.parser = function(s){
		if (!s) return new Date();
		var ss = s.split('-');
		var y = parseInt(ss[0],10);
		var m = parseInt(ss[1],10);
		var d = parseInt(ss[2],10);
		if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
			return new Date(y,m-1,d);
		} else {
			return new Date();
		}
	};
}
if ($.fn.datetimebox && $.fn.datebox){
	$.extend($.fn.datetimebox.defaults,{
		currentText: $.fn.datebox.defaults.currentText,
		closeText: $.fn.datebox.defaults.closeText,
		okText: $.fn.datebox.defaults.okText,
		missingMessage: $.fn.datebox.defaults.missingMessage
	});
}
if ($.fn.datetimespinner){
	$.fn.datetimespinner.defaults.selections = [[0,4],[5,7],[8,10],[11,13],[14,16],[17,19]]
}
