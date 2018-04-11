/**
 * 菜单维护字典
 */
$(function(){
	obj = {
		// 用于记录提交表单的连接，新增或者添加的请求地址
		submitUrl:'',	
		
		search:function(){
			$('#menuTb').datagrid('load',{
				name:$.trim($('#text_search').val()),
				role:$.trim($('#role_search').val()),
			});
		},
		add:function(){
			$('#menu_dialog').panel('setTitle','新增菜单');
			$('#menu_dialog').dialog('open');
			this.submitUrl = basePath + "/menuManage/addMenu";
		},
		edit:function(){
			var row = $('#menuTb').datagrid('getSelected');
			if(row!=null){
				var id=row.id;
				$('#menu_dialog').panel('setTitle','修改菜单信息');
				$('#menu_dialog').dialog('open');
				// 加载表单数据,默认请求方式为GET
				$('#menu_form').form('load',basePath+'/menuManage/getMenuById/'+id);
				this.submitUrl=basePath+'/menuManage/updateMenu/'+id;
			} else {
				$.messager.alert('警告操作！', '请选择一条记录！', 'info');
			}
		},
		remove:function(){
			var row = $('#menuTb').datagrid('getSelected');
			if(row!=null){
				$.messager.confirm('确定操作', '您要删除所选的记录吗？', function (flag) {
					if (flag) {
						var id=row.id;
						$.ajax({
							type : 'POST',
							url :basePath+'/menuManage/deleteMenu/'+id,
							success : function (data) {
								if (data=="delete") {
									$.messager.alert('提示信息', '删除成功！');
									$('#menuTb').datagrid('reload');
								} else {
									$.messager.alert('提示信息', '删除失败！','info');
								}
							}
						});
					}
				});
			} else {
				$.messager.alert('警告操作！', '请选择一条记录！', 'info');
			}
		},
		submitForm:function(){
			$('#menu_form').form('submit',{
				url:obj.submitUrl,
				onSubmit:function(){
					return $(this).form('enableValidation').form('validate');   //检验各个编辑框是否满足设置的条件
				},
				success:function(data){
			        if(data=='add' || data=='update'){
			        	$.messager.alert('提示信息','保存成功！','info',function(){
			        		obj.clearForm();
			        		$('#menuTb').datagrid('reload');
			        	});
			        } else {
			        	$.messager.alert('提示信息','保存失败！','info');
			        }
			    }
			});
		},
		clearForm:function(){
			$('#menu_dialog').dialog('close');
			$('#menu_form').form('clear');
			$('#menu_dialog').dialog('center');
		}
	};
	
	$("#menuTb").datagrid({
		url:basePath+"/menuManage/getMenus",
		fit:true,
		striped:true,
		rownumbers:true,
		border:false,
		pagination:true,
		singleSelect:true,
		pageSize:20,
		pageList:[10,20,30],
		pageNumber:1,
		sortName:'id',
		sortOrder:'asc',
		//remoteSort:true,
		toolbar:'#search_menu',
		columns:[[
			{
				field : 'id',
				title : 'id',
				width : 100,
				hidden:true
			},
			{
				field : 'text',
				title : '名称',
				width : 120,
			},
			{
				field : 'iconCls',
				title : '图标',
				width : 80
			},
			{
				field : 'url',
				title : 'url',
				width : 150
			},
			{
				field : 'pname',
				title : '父节点',
				width : 150
			},
			{
				field : 'stateName',
				title : '状态',
				width : 160
			},
			{
				field : 'roleNames',
				title : '权限',
				width : 160
			},
		]]
	});
	
	$('#role_search').combobox({
		valueField : 'id',
		textField : 'name',
		limitToList : true,
		url : basePath+'/menuManage/roleList',
	});
	
	$('#rolesIds').combobox({
		valueField : 'id',
		textField : 'name',
		limitToList : true,
		multiple:true,
		url : basePath+'/menuManage/roleList',
	});
	
	$('#pid').combobox({
		valueField : 'menuId',
		textField : 'text',
		limitToList : true,
		url : basePath+'/menuManage/menuList',
	});
	
	$('#stateId').combobox({
		valueField : 'stateId',
		textField : 'state',
		limitToList : true,
		data : [{
			stateId: '4',
			state: 'open'
		},{
			stateId: '5',
			state: 'closed'
		}]
	});

	
});