/**
 * 物业管理人员维护
 */
$(function(){
	obj = {
		// 用于记录提交表单的连接，新增或者添加的请求地址
		submitUrl:'',	
		
		search:function(){
			$('#propertyManagerTb').datagrid('load',{
				name:$.trim($('#name_search').val()),
				Username:$.trim($('#username_search').val()),
				phone:$.trim($('#phone_search').val()),
			});
		},
		add:function(){
			$('#propertyManager_dialog').panel('setTitle','新增统保方案');
			$('#propertyManager_dialog').dialog('open');
			this.submitUrl = basePath + "/propertyManager/addpropertyManager";
		},
		edit:function(){
			var row = $('#propertyManagerTb').datagrid('getSelected');
			if(row!=null){
				var id=row.id;
				$('#propertyManager_dialog').panel('setTitle','修改材料清单');
				$('#propertyManager_dialog').dialog('open');
				// 加载表单数据,默认请求方式为GET
				$('#propertyManager_form').form('load',basePath+'/propertyManager/getpropertyManager/'+id);
				this.submitUrl=basePath+'/propertyManager/updatepropertyManager/'+id;
			} else {
				$.messager.alert('警告操作！', '请选择一条记录！', 'info');
			}
		},
		remove:function(){
			var row = $('#propertyManagerTb').datagrid('getSelected');
			if(row!=null){
				$.messager.confirm('确定操作', '您要删除所选的记录吗？', function (flag) {
					if (flag) {
						var id=row.id;
						$.ajax({
							type : 'POST',
							url :basePath+'/propertyManager/deletepropertyManager/'+id,
							success : function (data) {
								if (data=="delete") {
									$.messager.alert('提示信息', '删除成功！');
									$('#propertyManagerTb').datagrid('reload');
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
			$('#propertyManager_form').form('submit',{
				url:obj.submitUrl,
				onSubmit:function(){
					return $(this).form('enableValidation').form('validate');   //检验各个编辑框是否满足设置的条件
				},
				success:function(data){
			        if(data=='add' || data=='update'){
			        	$.messager.alert('提示信息','保存成功！','info',function(){
			        		obj.clearForm();
			        		$('#propertyManagerTb').datagrid('reload');
			        	});
			        } else {
			        	$.messager.alert('提示信息','保存失败！','info');
			        }
			    }
			});
		},
		clearForm:function(){
			$('#propertyManager_dialog').dialog('close');
			$('#propertyManager_form').form('clear');
			$('#propertyManager_dialog').dialog('center');
		}
	};
	
	$("#propertyManagerTb").datagrid({
		url:basePath+"/propertyManager/getpropertyManagers",
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
		toolbar:'#search_propertyManager',
		columns:[[
			{
				field : 'id',
				title : 'id',
				width : 100,
				hidden:true
			},
			{
				field : 'name',
				title : '姓名',
				width : 120,
			},
			{
				field : 'username',
				title : '账号',
				width : 120
			},
			{
				field : 'phone',
				title : '电话',
				width : 160
			},
			{
				field : 'email',
				title : '邮箱',
				width : 160
			},
		]]
	});
	
});