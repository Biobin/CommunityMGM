/**
 * 业主管理
 */
$(function(){
	obj = {
		// 用于记录提交表单的连接，新增或者添加的请求地址
		submitUrl:'',	
		
		search:function(){
			$('#ownerTb').datagrid('load',{
				name:$.trim($('#name_search').val()),
				Username:$.trim($('#username_search').val()),
				phone:$.trim($('#phone_search').val()),
			});
		},
		add:function(){
			$('#owner_dialog').panel('setTitle','新增业主信息');
			$('#owner_dialog').dialog('open');
			this.submitUrl = basePath + "/owner/addOwner";
		},
		edit:function(){
			var row = $('#ownerTb').datagrid('getSelected');
			if(row!=null){
				var id=row.id;
				$('#owner_dialog').panel('setTitle','修改业主信息');
				$('#owner_dialog').dialog('open');
				// 加载表单数据,默认请求方式为GET
				$('#owner_form').form('load',basePath+'/owner/getOwnerById/'+id);
				this.submitUrl=basePath+'/owner/updateOwner/'+id;
			} else {
				$.messager.alert('警告操作！', '请选择一条记录！', 'info');
			}
		},
		remove:function(){
			var row = $('#ownerTb').datagrid('getSelected');
			if(row!=null){
				$.messager.confirm('确定操作', '您要删除所选的记录吗？', function (flag) {
					if (flag) {
						var id=row.id;
						$.ajax({
							type : 'POST',
							url :basePath+'/owner/deleteOwner/'+id,
							success : function (data) {
								if (data=="delete") {
									$.messager.alert('提示信息', '删除成功！');
									$('#ownerTb').datagrid('reload');
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
			$('#owner_form').form('submit',{
				url:obj.submitUrl,
				onSubmit:function(){
					return $(this).form('enableValidation').form('validate');   //检验各个编辑框是否满足设置的条件
				},
				success:function(data){
			        if(data=='add' || data=='update'){
			        	$.messager.alert('提示信息','保存成功！','info',function(){
			        		obj.clearForm();
			        		$('#ownerTb').datagrid('reload');
			        	});
			        } else {
			        	$.messager.alert('提示信息','保存失败！','info');
			        }
			    }
			});
		},
		clearForm:function(){
			$('#owner_dialog').dialog('close');
			$('#owner_form').form('clear');
			$('#owner_dialog').dialog('center');
		}
	};
	
	$("#ownerTb").datagrid({
		url:basePath+"/owner/getOwners",
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
		toolbar:'#search_owner',
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
				field : 'IDNumber',
				title : '身份证号码',
				width : 120
			},
			{
				field : 'address',
				title : '住址',
				width : 120
			},
			{
				field : 'startTime',
				title : '入住时间',
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