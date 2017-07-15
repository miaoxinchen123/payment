/**
 * 后台菜单管理js
 */
var index = 1;
var M_Tabscount = -1;
$(function(){
	/**
	 * 添加事件监听程序
	 */
	$('#tab_div').tabs({   
		onBeforeClose:function(title, i){//onClose调用时页面已经关了，需要用onBeforeClose
	    	/*if(i == 0){	//第一个tab为首页，避免命名冲突
	    		return false;
	    	}*/
	    	
    		index--;
    		return true;
	    }
	}); 
	
	/**
	 * 顶部一级菜单点击
	 */
	$("#north_top_nav a").click(function() {
		if(!$(this).hasClass("active")) {//一级菜单切换
			$(this).siblings("a").removeClass("active");
			$(this).addClass("active");
			if($("#_subTitle_")) {//修改左panel标题
				$("#_subTitle_").html($(this).html());
			}
			
			var menuDiv = $("#_menu_" + $(this).attr("id"));
			menuDiv.css('display','');
			menuDiv.siblings("div").css('display','none');
			//slideUpSiblingMenus(menuDiv)
			
			//closeAllTabsExcluHomePage($('#tab_div')); //关闭tabs,除了首页
		 }
	});
	
	/**
	 * 左边二级菜单点击
	 */
	$("#west_menu .accordion-header").click(function() {
		if(!$(this).hasClass("accordion-header-selected")) {//二级菜单切换
			$(".west_second_menu li").each(function() {//取消三级菜单选中状态
				$(this).removeClass("second-menu-selected");
				$(this).find("i").removeClass("icon-style-20-20-on");
			});
			
			$(this).siblings("div").removeClass("accordion-header-selected");
			$(this).addClass("accordion-header-selected");
			$(this).siblings("div").find("div:nth-child(2)").removeClass("icon-style-20-20-on");
			$(this).find("div:nth-child(2)").addClass("icon-style-20-20-on");
		 }
		
		if($(this).next("ul").length > 0) {//三级菜单切换
			$(this).siblings("div").next("ul").slideUp();
			$(this).next("ul").slideDown();
		} else {
			showSubTabs($(this).attr("title"), $(this).find("div:nth-child(1)").attr("menuUrl"));
		}
		
	});
	
	/**
	 * 左边三级菜单点击
	 */
	$(".west_second_menu li").click(function() {
		if(!$(this).parent().prev().hasClass("accordion-header-selected")) {//父菜单未选中
			$("#west_menu .accordion-header").each(function(){
				$(this).removeClass("accordion-header-selected");
				$(this).siblings("div").find("div:nth-child(2)").removeClass("icon-style-20-20-on");
			});
			
			$(this).parent().prev().addClass("accordion-header-selected");
			$(this).parent().prev().find("div:nth-child(2)").addClass("icon-style-20-20-on");
		}
		
		if(!$(this).hasClass("second-menu-selected")) {//三级菜单切换
			$(this).siblings().removeClass("second-menu-selected");
			$(this).siblings().find("i").removeClass("icon-style-20-20-on");
			$(this).find("i").addClass("icon-style-20-20-on");
			$(this).addClass("second-menu-selected");
		}
		
		showSubTabs($(this).attr("title"), $(this).find("a").attr("menuUrl"));
	
	});
	
	/**
	 * 刷新
	 */
	$('#mm-tabupdate').click(function() {
		var currTab = $('#tab_div').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		var currTabIframe = $("iframe", currTab);
		if(currTabIframe) {
			currTabIframe.attr("src", url);
		} else {
			$('#tab_div').tabs('update',{
				tab:currTab,
				options:{
					content:createFrame(url)
				}
			})
		}
	})
	
	/**
	 * 关闭当前tab
	 */
	$('#mm-tabclose').click(function() {
		var currtab_title = $('#mm').data("currtab");
		$('#tab_div').tabs('close',currtab_title);
	})
	
	/**
	 * 关闭全部tab
	 */
	$('#mm-tabcloseall').click(function() {
		$('.tabs-inner span').each(function(i,n){
			var t = $(n).text();
			$('#tab_div').tabs('close',t);
		});
	});
	
	/**
	 * 关闭除当前tab之外的所有tab
	 */
	$('#mm-tabcloseother').click(function() {
		$('#mm-tabcloseright').click();
		$('#mm-tabcloseleft').click();
	});
	
	/**
	 * 关闭当前所有右侧tab
	 */
	$('#mm-tabcloseright').click(function() {
		var nextall = $('.tabs-selected').nextAll();
		if(nextall.length == 0){
			return false;
		}
		nextall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tab_div').tabs('close',t);
		});
		return false;
	});
	
	/**
	 * 关闭当前所有左侧tab
	 */
	$('#mm-tabcloseleft').click(function() {
		var prevall = $('.tabs-selected').prevAll();
		if(prevall.length == 0){
			return false;
		}
		prevall.each(function(i,n){
			var t=$('a:eq(0) span',$(n)).text();
			$('#tab_div').tabs('close',t);
		});
		return false;
	});
	
	/**
	 * 退出菜单
	 */
	$("#mm-exit").click(function() {
		$('#mm').menu('hide');
	})

	/**
	 * 关闭tab标签释放iframe内存(有待改进)
	 */
	$.fn.panel.defaults.onBeforeDestroy = function() {
		var frame = $('iframe', this);
		if (frame && frame.length > 0) {
			try { 
				for(var i = 0; i < frame.length; i++) {
					frame[i].contentWindow.document.write('');
					frame[i].src = "about:blank";
					frame[i].contentWindow.close();
				}
			} catch(e) {}
			frame.remove();
			if(navigator.userAgent.indexOf("MSIE") > 0) {
				CollectGarbage();
			}
		}
		
		frame = null;
		return true;
	};
	
	bindTabCloseMenu(); //首页绑定菜单
});


/**
 * 收起menuDiv兄弟下拉菜单
 * @param menuDiv		:点击一级菜单
 */
function slideUpSiblingMenus(menuDiv) {
	if(menuDiv.siblings("div")) {
		menuDiv.siblings("div").find(".accordion-header").each(function() {
			$(this).removeClass("accordion-header-selected");
			$(this).find("div:nth-child(2)").removeClass("icon-style-20-20-on");
			
			$(this).find(".west_second_menu li").each(function() {
				$(this).removeClass("second-menu-selected");
				$(this).find("i").removeClass("icon-style-20-20-on");
			});
			
			if($(this).next("ul").length > 0) {
				$(this).next("ul").slideUp();
			}
				
		});
	}
}

/**
 * 关闭所有tabs,除了配置notColsed为true标签
 * @param tabDiv		:tab标签
 */
function closeAllTabsExcluHomePage(tabDiv) {
	if("undefined" == typeof(tabDiv)) return;
	var tabsLength = tabDiv.tabs("tabs").length;
	var closeTabTitle = new Array(tabsLength);
	var startPos = 0;
	var tmpTab = null;
	for(var i = 0; i < tabsLength; i++) {
		tmpTab = tabDiv.tabs("tabs")[i];
		if(!tmpTab.panel('options').notColsed) {
			closeTabTitle[startPos] = tmpTab.panel('options').title;
			startPos++;
		}
		tmpTab = null;
	}
	
	for(var i = 0; i < startPos; i++) {
		tabDiv.tabs("close", closeTabTitle[i]);
	}
	
	closeTabTitle = null;
}
	
/**
 * 根据标题和url显示tabs(如果存在则选中,不存在则创建)
 * @param subTabTitle		:tab标题
 * @param subTabUrl			:tab链接地址
 */
function showSubTabs(subTabTitle, subTabUrl) {
	 if(!$('#tab_div').tabs('exists', subTabTitle)) {
		 if(-1 == M_Tabscount || index <= M_Tabscount){
			 $('#tab_div').tabs('add', {
				 title: subTabTitle,
				 content: createFrame(omitExcessBackslash(subTabUrl)),
				 closable: true
			 });
			 
			 if($("iframe") && $("iframe").parent(".panel-body")) {//去掉tab滚动条
				 $("iframe").parent(".panel-body").css("overflow-y","hidden");
			 }
			 
			 bindTabCloseMenu();
			 index++;
		 } else {
			 $.messager.alert('提示','打开页面过多!','info');
		 }

    } else {
	   $('#tab_div').tabs('select',subTabTitle);
    }
}

/**
 * 去掉多余反斜杠
 * @param url	:待处理地址
 */
function omitExcessBackslash(url) {
	if(url && 0 == url.indexOf("//")) {
		url = url.substring(1);
	}
	
	return url;
}

/**
 * 创建frame
 */
function createFrame(url){
	var s = '<iframe id="tabs-'+index+'" scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
	return s;
}

/**
 * tab关闭菜单绑定
 */
function bindTabCloseMenu() {
	/**
	 * 双击关闭tab选项卡
	 */
	$(".tabs-inner").dblclick(function(){
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tab_div').tabs('close',subtitle);
	})
	/**
	 * 选项卡绑定右键
	 */
	$(".tabs-inner").bind('contextmenu',function(e){
		$('#mm').menu('show', {
			left: e.pageX,
			top: e.pageY
		});

		var subtitle =$(this).children(".tabs-closable").text();

		$('#mm').data("currtab",subtitle);
		$('#tab_div').tabs('select',subtitle);
		return false;
	});
}