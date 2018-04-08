/**
 * 主界面js
 */
$(function () {
	
	$('#navigation').tree({   
		url : basePath + '/getMenus',
		lines : true,
		onBeforeExpand:function(node){
			$(this).tree('options').url = basePath+'/getMenus?menuId=' + node.id;
		},
		onClick : function(node){
			if(node.url!=null && node.url!=''){
				var tabTitle = node.text;
				var url = basePath+node.url;
				var icon = node.iconCls;
				addTab(tabTitle, url, icon);
			}
		}
	});
	//初始化选项卡
	$('#tabs').tabs({
		fit:true,
		border:false,
	});
	//打开选项卡
	function addTab(title, url, icon){
		if ($('#tabs').tabs('exists', title)){
			$('#tabs').tabs('select', title);
		} else {
			var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:99%;"></iframe>';
			$('#tabs').tabs('add',{
				title:title,
				content:content,
				iconCls:icon,
				closable:true
			});
		}
	}	
	
});
