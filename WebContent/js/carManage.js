/**
 * 车辆管理js
 */
$(function(){
	obj = {
		// 用于记录提交表单的连接，新增或者添加的请求地址
		submitUrl:'',	
		
		search:function(){
			$('#carTb').datagrid('load',{
				Ownername:$.trim($('#ownerName_search').val()),
				plateNumber:$.trim($('#plateNumber_search').val()),
				carStyleId:$.trim($('#carStyle_search').val()),
			});
		},
		add:function(){
			$('#car_dialog').panel('setTitle','新增车辆信息');
			$('#car_dialog').dialog('open');
			this.submitUrl = basePath + "/car/addCar";
		},
		edit:function(){
			var row = $('#carTb').datagrid('getSelected');
			if(row!=null){
				var id=row.id;
				$('#car_dialog').panel('setTitle','修改车辆信息');
				$('#car_dialog').dialog('open');
				// 加载表单数据,默认请求方式为GET
				$('#car_form').form('load',basePath+'/car/getCar/'+id);
				this.submitUrl=basePath+'/car/updateCAr/'+id;
			} else {
				$.messager.alert('警告操作！', '请选择一条记录！', 'info');
			}
		},
		remove:function(){
			var row = $('#carTb').datagrid('getSelected');
			if(row!=null){
				$.messager.confirm('确定操作', '您要删除所选的记录吗？', function (flag) {
					if (flag) {
						var id=row.id;
						$.ajax({
							type : 'POST',
							url :basePath+'/car/deleteCar/'+id,
							success : function (data) {
								if (data=="delete") {
									$.messager.alert('提示信息', '删除成功！');
									$('#carTb').datagrid('reload');
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
			$('#car_form').form('submit',{
				url:obj.submitUrl,
				onSubmit:function(){
					return $(this).form('enableValidation').form('validate');   //检验各个编辑框是否满足设置的条件
				},
				success:function(data){
			        if(data=='add' || data=='update'){
			        	$.messager.alert('提示信息','保存成功！','info',function(){
			        		obj.clearForm();
			        		$('#carTb').datagrid('reload');
			        	});
			        } else {
			        	$.messager.alert('提示信息','保存失败！','info');
			        }
			    }
			});
		},
		clearForm:function(){
			$('#car_dialog').dialog('close');
			$('#car_form').form('clear');
			$('#car_dialog').dialog('center');
		}
	};
	
	$("#carTb").datagrid({
		url:basePath+"/car/getCars",
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
		toolbar:'#search_car',
		columns:[[
			{
				field : 'id',
				title : 'id',
				width : 100,
				hidden:true
			},
			{
				field : 'ownerName',
				title : '车主',
				width : 100,
			},
			{
				field : 'plateNumber',
				title : '车牌号',
				width : 120
			},
			{
				field : 'ownerPhone',
				title : '电话',
				width : 150
			},
			{
				field : 'ownerEmail',
				title : '邮箱',
				width : 150
			},
			{
				field : 'createTime',
				title : '开始停放时间',
				width : 160
			}
		]]
	});
	
	$('#owner_search').combobox({
		valueField : 'id',
		textField : 'name',
		limitToList : true,
		url : basePath+'/car/ownerList',
	});
	
	$('#ownerId').combobox({
		valueField : 'id',
		textField : 'name',
		limitToList : true,
		url : basePath+'/car/ownerList',
		onChange:function(newValue,oldValue) {
			$("#car_form").form('load', basePath+ '/car/showOwnerInfo/'+newValue);
		}
	});
	
	$('#carStyle_search').combobox({
		valueField : 'id',
		textField : 'name',
		limitToList : true,
		url : basePath+'/car/carStyleList',
	});

	$('#carStyleId').combobox({
		valueField : 'id',
		textField : 'name',
		limitToList : true,
		url : basePath+'/car/carStyleList',
	});
	
	
});