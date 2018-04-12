/**
 * 通讯录管理js
 */
$(function(){
	obj = {
		// 用于记录提交表单的连接，新增或者添加的请求地址
		submitUrl:'',	
		
		search:function(){
			$('#contactTb').datagrid('load',{
				name:$.trim($('#name').val()),
			});
		},
		add:function(){
			$('#contact_form').form('clear');
			this.submitUrl = basePath + "/contact/addContact";
		},
		edit:function(){
			var row = $('#contactTb').datagrid('getSelected');
				var id=row.id;
				// 加载表单数据,默认请求方式为GET
				$('#contact_form').form('load',basePath+'/contact/getContact/'+id);
				this.submitUrl=basePath+'/contact/updateContact/'+id;
		},
		remove:function(){
			var row = $('#contactTb').datagrid('getSelected');
			if(row!=null){
				$.messager.confirm('确定操作', '您要删除所选的记录吗？', function (flag) {
					if (flag) {
						var id=row.id;
						$.ajax({
							type : 'POST',
							url :basePath+'/contact/deleteContact/'+id,
							success : function (data) {
								if (data=="delete") {
									$.messager.alert('提示信息', '删除成功！');
									$('#contactTb').datagrid('reload');
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
			$('#contact_form').form('submit',{
				url:obj.submitUrl,
				onSubmit:function(){
					return $(this).form('enableValidation').form('validate');   //检验各个编辑框是否满足设置的条件
				},
				success:function(data){
			        if(data=='add' || data=='update'){
			        	$.messager.alert('提示信息','保存成功！','info',function(){
			        		obj.clearForm();
			        		$('#contactTb').datagrid('reload');
			        	});
			        } else {
			        	$.messager.alert('提示信息','保存失败！','info');
			        }
			    }
			});
		},
		clearForm:function(){
			$('#contact_form').form('clear');
		}
	};
	
	$("#contactTb").datagrid({
		url:basePath+"/contact/getContacts",
		fit:true,
		striped:true,
		rownumbers:true,
		border:false,
		pagination:true,
		pagePosition:'bottom',
		singleSelect:true,
		pageSize:20,
		pageList:[10,20,30],
		pageNumber:1,
		sortName:'id',
		sortOrder:'asc',
		//remoteSort:true,
		toolbar:'#search_contact',
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
			
		]]
	});
	
});