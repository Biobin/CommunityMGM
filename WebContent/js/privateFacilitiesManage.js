/**
 * 私人设施信息js
 */
$(function(){
	obj = {
		// 用于记录提交表单的连接，新增或者添加的请求地址
		submitUrl:'',	
		
		search:function(){
			$('#communalFacilitiesTb').datagrid('load',{
				code:$.trim($('#code').val()),
				name:$.trim($('#name').val()),
			});
		},
		add:function(){
			$('#communalFacilities_form').form('clear');
			this.submitUrl = basePath + "/communalFacilities/addCommunalFacilities";
		},
		edit:function(){
			var row = $('#communalFacilitiesTb').datagrid('getSelected');
				var id=row.id;
				// 加载表单数据,默认请求方式为GET
				$('#communalFacilities_form').form('load',basePath+'/communalFacilities/getCommunalFacilitiesById/'+id);
				this.submitUrl=basePath+'/communalFacilities/updateCommunalFacilities/'+id;
		},
		remove:function(){
			var row = $('#communalFacilitiesTb').datagrid('getSelected');
			if(row!=null){
				$.messager.confirm('确定操作', '您要删除所选的记录吗？', function (flag) {
					if (flag) {
						var id=row.id;
						$.ajax({
							type : 'POST',
							url :basePath+'/communalFacilities/deleteCommunalFacilities/'+id,
							success : function (data) {
								if (data=="delete") {
									$.messager.alert('提示信息', '删除成功！');
									$('#communalFacilitiesTb').datagrid('reload');
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
			$('#communalFacilities_form').form('submit',{
				url:obj.submitUrl,
				onSubmit:function(){
					return $(this).form('enableValidation').form('validate');   //检验各个编辑框是否满足设置的条件
				},
				success:function(data){
			        if(data=='add' || data=='update'){
			        	$.messager.alert('提示信息','保存成功！','info',function(){
			        		obj.clearForm();
			        		$('#communalFacilitiesTb').datagrid('reload');
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
			$('#communalFacilities_form').form('clear');
		}
	};
	
	$("#communalFacilitiesTb").datagrid({
		url:basePath+"/communalFacilities/getPrivateFacilities",
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
		toolbar:'#search_communalFacilities',
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
				field : 'name',
				title : '名称',
				width : 120,
			},
			
		]]
	});
	
	$('#communalFaStyleId').combobox({
		valueField : 'id',
		textField : 'name',
		limitToList : true,
		url : basePath+'/communalFaStyle/privateFaStyleList',
	});
	
});