/**
 * 报修功能js
 */
$(function(){
	obj = {
		// 用于记录提交表单的连接，新增或者添加的请求地址
		submitUrl:'',	
		
		search:function(){
			$('#maintenanceTb').datagrid('load',{
				beginTime:$.trim($('#beginTime').val()),
				endTime:$.trim($('#endTime').val()),
				stateId:$.trim($('#stateId_search').val()),
			});
		},
		add:function(){
			$('#maintenance_form_add').form('clear');
			$('#maintenance_dialog_add').panel('setTitle','新增报修信息');
			$('#maintenance_dialog_add').dialog('open');
			this.submitUrl = basePath + "/maintenance/addMaintenance";
		},
		edit:function(){
			var row = $('#maintenanceTb').datagrid('getSelected');
			var id=row.id;
			$('#maintenance_dialog_edit').panel('setTitle','修改报修信息');
			$('#maintenance_dialog_edit').dialog('open');
			// 加载表单数据,默认请求方式为GET
			$('#maintenance_form_edit').form('load',basePath+'/maintenance/getMaintenance/'+id);
			this.submitUrl=basePath+'/maintenance/updateMaintenance/'+id;
		},
		remove:function(){
			var row = $('#maintenanceTb').datagrid('getSelected');
			if(row!=null){
				$.messager.confirm('确定操作', '您要删除所选的记录吗？', function (flag) {
					if (flag) {
						var id=row.id;
						$.ajax({
							type : 'POST',
							url :basePath+'/maintenance/deleteMaintenance/'+id,
							success : function (data) {
								if (data=="delete") {
									$.messager.alert('提示信息', '删除成功！');
									$('#maintenanceTb').datagrid('reload');
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
		show:function(){
			var row = $('#maintenanceTb').datagrid('getSelected');
			var id=row.id;
			$('#maintenance_dialog_show').panel('setTitle','修改投诉信息');
			$('#maintenance_dialog_show').dialog('open');
			// 加载表单数据,默认请求方式为GET
			$('#maintenance_form_show').form('load',basePath+'/maintenance/getMaintenance/'+id);
		},
		submitForm:function(){
			$('#maintenance_form_add').form('submit',{
				url:obj.submitUrl,
				onSubmit:function(){
					return $(this).form('enableValidation').form('validate');   //检验各个编辑框是否满足设置的条件
				},
				success:function(data){
			        if(data=='add'){
			        	$.messager.alert('提示信息','保存成功！','info',function(){
			        		obj.clearForm();
			        		$('#maintenanceTb').datagrid('reload');
			        	});
			        } else {
			        	$.messager.alert('提示信息','保存失败！','info');
			        	console.log(data);
			        	console.log(obj.submitUrl);
			        }
			    }
			});
			$('#maintenance_form_edit').form('submit',{
				url:obj.submitUrl,
				onSubmit:function(){
					return $(this).form('enableValidation').form('validate');   //检验各个编辑框是否满足设置的条件
				},
				success:function(data){
			        if(data=='update'){
			        	$.messager.alert('提示信息','保存成功！','info',function(){
			        		obj.clearForm();
			        		$('#maintenanceTb').datagrid('reload');
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
			$('#maintenance_dialog_add').dialog('close');
			$('#maintenance_form_add').form('clear');
			$('#maintenance_dialog_add').dialog('center');
			$('#maintenance_dialog_edit').dialog('close');
			$('#maintenance_form_edit').form('clear');
			$('#maintenance_dialog_edit').dialog('center');
		}
	};
	
	$("#maintenanceTb").datagrid({
		url:basePath+"/maintenance/getMaintenances",
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
		toolbar:'#search_maintenance',
		columns:[[
			{
				field : 'id',
				title : 'id',
				width : 100,
				hidden:true
			},
			{
				field : 'details',
				title : '报修详情',
				width : 200,
			},
			{
				field : 'createTime',
				title : '提交日期',
				width : 140,
			},
			{
				field : 'stateName',
				title : '处理状态',
				width : 100,
				styler: function(value,row,index){
					if (value == '待受理'){
						return 'background-color:#ffee00;color:red;';
					}
				}
			},
		]],
		rowStyler: function(index,row){
			if (row.stateName == '已处理'){
				return 'color:green;';
			}
		}
	});
	
	$('#propertyManagerId').combobox({
		valueField : 'id',
		textField : 'name',
		limitToList : true,
//		url : basePath+'/maintenance/propertyManagerList',
//		onChange:function(newValue,oldValue) {
//			if(id.value == ""){
//				$("#maintenance_form_add").form('load', basePath+ '/maintenance/showPropertyManagerInfo/'+newValue);
//			}
//		}
	});
	
	$('#propertyManagerId_show').combobox({
		valueField : 'id',
		textField : 'name',
		limitToList : true,
//		url : basePath+'/maintenance/propertyManagerList',
	});
	
	$('#stateId').combobox({
		valueField : 'stateId',
		textField : 'state',
		limitToList : true,
		data : [{
			stateId: '1',
			state: '待受理'
		},{
			stateId: '2',
			state: '处理中'
		},{
			stateId: '3',
			state: '已处理'
		}]
	});

	$('#stateId_search').combobox({
		valueField : 'stateId',
		textField : 'state',
		limitToList : true,
		data : [{
			stateId: '1',
			state: '待受理'
		},{
			stateId: '2',
			state: '处理中'
		},{
			stateId: '3',
			state: '已处理'
		}],
		onChange:function(newValue,oldValue){
			//已处理状态下才能查看详情
			if(newValue==1||newValue==2||newValue=='') {
				$('#maintenance_show').linkbutton('disable');
				$('#maintenance_show').hide();
			} else {
				$('#maintenance_show').linkbutton('enable');
				$('#maintenance_show').show();
			}
		}
	});
	
	$('#stateId_show').combobox({
		valueField : 'stateId',
		textField : 'state',
		limitToList : true,
		data : [{
			stateId: '1',
			state: '待受理'
		},{
			stateId: '2',
			state: '处理中'
		},{
			stateId: '3',
			state: '已处理'
		}]
	});
	
	$('#ownerId').combobox({
		valueField : 'id',
		textField : 'name',
		limitToList : true,
		url : basePath+'/maintenance/ownerList',
	});
	
	$('#ownerId_show').combobox({
		valueField : 'id',
		textField : 'name',
		limitToList : true,
		url : basePath+'/maintenance/ownerList',
	});

	$('#communalFaStyleId').combobox({
		valueField : 'id',
		textField : 'name',
		limitToList : true,
		url : basePath+'/maintenance/communalFaStyleList',
		onChange:function(newValue,oldValue) {
			$('#communalFacilitiesId').combobox({
				url:basePath+"/maintenance/communalFacilitiesList",
				queryParams: {
					"communalFaStyleId" :newValue
				},
				valueField : 'id',
				textField :  'name',
				limitToList:true,
			});
		}
	});

	$('#communalFaStyleId_edit').combobox({
		valueField : 'id',
		textField : 'name',
		limitToList : true,
		url : basePath+'/maintenance/communalFaStyleList',
	});

	$('#communalFaStyleId_show').combobox({
		valueField : 'id',
		textField : 'name',
		limitToList : true,
		url : basePath+'/maintenance/communalFaStyleList',
	});

	$('#communalFacilitiesId').combobox({
		valueField : 'id',
		textField : 'name',
		limitToList : true,
		onChange:function(newValue,oldValue) {
			$('#propertyManagerId').combobox({
				url:basePath+"/maintenance/propertyManagerList",
				queryParams: {
					"communalFacilitiesId" :newValue
				},
				valueField : 'id',
				textField :  'name',
				limitToList:true,
			});
		}
	});
	
//	$('#communalFacilitiesId_edit').combobox({
//		valueField : 'id',
//		textField : 'name',
//		limitToList : true,
//		url:basePath+"/maintenance/communalFacilitiesListRead",
//	});
	
//	$('#communalFacilitiesId_show').combobox({
//		valueField : 'id',
//		textField : 'name',
//		limitToList : true,
//		onChange:function(newValue,oldValue) {
//			$('#propertyManagerId_show').combobox({
//				url:basePath+"/maintenance/propertyManagerList",
//				queryParams: {
//					"communalFacilitiesId" :newValue
//				},
//				valueField : 'id',
//				textField :  'name',
//				limitToList:true,
//			});
//		}
//	});
	
	//物业管理员不能操作
	if(roleId==1) {
		$('#maintenance_add').linkbutton('disable');
		$('#maintenance_delete').linkbutton('disable');
		$('#maintenance_add').hide();
		$('#maintenance_delete').hide();
	}
	
	 //业主不能操作
	if(roleId==2) {
		$('#maintenance_edit').linkbutton('disable');
		$('#maintenance_edit').hide();
	};
	
});
