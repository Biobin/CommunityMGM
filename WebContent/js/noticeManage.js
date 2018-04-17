/**
 * 公告管理js
 */
$(function(){
	obj = {
		// 用于记录提交表单的连接，新增或者添加的请求地址
		submitUrl:'',	
		
		search:function(){
			$('#noticeTb').datagrid('load',{
				code:$.trim($('#code').val()),
				name:$.trim($('#name').val()),
			});
		},
		add:function(){
			$('#notice_form').form('clear');
			this.submitUrl = basePath + "/notice/addNotice";
		},
		edit:function(){
			var row = $('#noticeTb').datagrid('getSelected');
				var id=row.id;
				// 加载表单数据,默认请求方式为GET
				$('#notice_form').form('load',basePath+'/notice/getNotice/'+id);
				this.submitUrl=basePath+'/notice/updateNotice/'+id;
		},
		remove:function(){
			var row = $('#noticeTb').datagrid('getSelected');
			if(row!=null){
				$.messager.confirm('确定操作', '您要删除所选的记录吗？', function (flag) {
					if (flag) {
						var id=row.id;
						$.ajax({
							type : 'POST',
							url :basePath+'/notice/deleteNotice/'+id,
							success : function (data) {
								if (data=="delete") {
									$.messager.alert('提示信息', '删除成功！');
									$('#noticeTb').datagrid('reload');
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
			$('#notice_form').form('submit',{
				url:obj.submitUrl,
				onSubmit:function(){
					return $(this).form('enableValidation').form('validate');   //检验各个编辑框是否满足设置的条件
				},
				success:function(data){
			        if(data=='add' || data=='update'){
			        	$.messager.alert('提示信息','保存成功！','info',function(){
			        		obj.clearForm();
			        		$('#noticeTb').datagrid('reload');
			        	});
			        } else {
			        	$.messager.alert('提示信息','保存失败！','info');
			        	console.log(data);
			        	console.log(obj.submitUrl);
			        }
			    }
			});
		},
		clearForm:function(){
			$('#notice_form').form('clear');
		}
	};
	
	$("#noticeTb").datagrid({
		url:basePath+"/notice/getNotices",
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
		toolbar:'#search_notice',
		columns:[[
			{
				field : 'id',
				title : 'id',
				width : 100,
				hidden:true
			},
			{
				field : 'code',
				title : '编号',
				width : 120,
			},
			{
				field : 'title',
				title : '标题',
				width : 200,
			},
			
		]]
	});
	
	 //业主不能操作
	if(roleId==2) {
		$('#notice_add').linkbutton('disable');
		$('#notice_delete').linkbutton('disable');
		$('#notice_save').linkbutton('disable');
		$('#notice_add').hide();
		$('#notice_delete').hide();
		$('#notice_save').hide();
	};
	
	
});