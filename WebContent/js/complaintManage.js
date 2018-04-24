/**
 * 投诉处理js
 */
$(function(){
	obj = {
		// 用于记录提交表单的连接，新增或者添加的请求地址
		submitUrl:'',	
		
		search:function(){
			$('#complaint_show').linkbutton('enable');
			$('#complaintTb').datagrid('load',{
				beginTime:$.trim($('#beginTime').val()),
				endTime:$.trim($('#endTime').val()),
				stateId:$.trim($('#stateId_search').val()),
			});
		},
		add:function(){
			$('#complaint_form_add').form('clear');
			$('#complaint_dialog_add').panel('setTitle','新增投诉(意见)信息');
			$('#complaint_dialog_add').dialog('open');
			this.submitUrl = basePath + "/complaint/addComplaint";
		},
		edit:function(){
			var row = $('#complaintTb').datagrid('getSelected');
			if(row!=null){
				var id=row.id;
				$('#complaint_dialog_edit').panel('setTitle','处理投诉(意见)信息');
				$('#complaint_dialog_edit').dialog('open');
				// 加载表单数据,默认请求方式为GET
				$('#complaint_form_edit').form('load',basePath+'/complaint/getComplaint/'+id);
				this.submitUrl=basePath+'/complaint/updateComplaint/'+id;
			} else {
				$.messager.alert('警告操作！', '请选择一条投诉信息！', 'info');
			}	
		},
		remove:function(){
			var row = $('#complaintTb').datagrid('getSelected');
			if(row!=null){
				$.messager.confirm('确定操作', '您要删除所选的记录吗？', function (flag) {
					if (flag) {
						var id=row.id;
						$.ajax({
							type : 'POST',
							url :basePath+'/complaint/deleteComplaint/'+id,
							success : function (data) {
								if (data=="delete") {
									$.messager.alert('提示信息', '删除成功！');
									$('#complaintTb').datagrid('reload');
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
			var row = $('#complaintTb').datagrid('getSelected');
			var id=row.id;
			$('#complaint_dialog_show').panel('setTitle','投诉(意见)单');
			$('#complaint_dialog_show').dialog('open');
			// 加载表单数据,默认请求方式为GET
			$('#complaint_form_show').form('load',basePath+'/complaint/getComplaint/'+id);
		},
		submitForm:function(){
			$('#complaint_form_add').form('submit',{
				url:obj.submitUrl,
				onSubmit:function(){
					return $(this).form('enableValidation').form('validate');   //检验各个编辑框是否满足设置的条件
				},
				success:function(data){
			        if(data=='add'){
			        	$.messager.alert('提示信息','保存成功！','info',function(){
			        		obj.clearForm();
			        		$('#complaintTb').datagrid('reload');
			        	});
			        } else {
			        	$.messager.alert('提示信息','保存失败！','info');
			        	console.log(data);
			        	console.log(obj.submitUrl);
			        }
			    }
			});
			$('#complaint_form_edit').form('submit',{
				url:obj.submitUrl,
				onSubmit:function(){
					return $(this).form('enableValidation').form('validate');   //检验各个编辑框是否满足设置的条件
				},
				success:function(data){
			        if(data=='update'){
			        	$.messager.alert('提示信息','保存成功！','info',function(){
			        		obj.clearForm();
			        		$('#complaintTb').datagrid('reload');
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
			$('#complaint_dialog_add').dialog('close');
			$('#complaint_form_add').form('clear');
			$('#complaint_dialog_add').dialog('center');
			$('#complaint_dialog_edit').dialog('close');
			$('#complaint_form_edit').form('clear');
			$('#complaint_dialog_edit').dialog('center');
		}
	};
	
	$("#complaintTb").datagrid({
		url:basePath+"/complaint/getComplaints",
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
		toolbar:'#search_complaint',
		columns:[[
			{
				field : 'id',
				title : 'id',
				width : 100,
				hidden:true
			},
			{
				field : 'content',
				title : '投诉内容',
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
			},
		]],
		rowStyler: function(index,row){
			if (row.stateName == '已处理'){
				return 'color:green;';
			} else if (row.stateName == '待受理') {
				return 'background-color:#ffee00;color:red';
			} else if (row.stateName == '处理中') {
				return 'color:blue';
			}
		}
	});
	
	$('#propertyManagerId').combobox({
		valueField : 'id',
		textField : 'name',
		limitToList : true,
		url : basePath+'/complaint/propertyManagerList',
		onChange:function(newValue,oldValue) {
			if(id.value == ""){
				$("#complaint_form_add").form('load', basePath+ '/complaint/showPropertyManagerInfo/'+newValue);
			}
		}
	});
	
	$('#propertyManagerId_show').combobox({
		valueField : 'id',
		textField : 'name',
		limitToList : true,
		url : basePath+'/complaint/propertyManagerList',
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
				$('#complaint_show').hide();
				$('#complaint_show').linkbutton('disable');
			} else {
				
				$('#complaint_show').show();
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
		url : basePath+'/complaint/ownerList',
	});
	
	$('#ownerId_show').combobox({
		valueField : 'id',
		textField : 'name',
		limitToList : true,
		url : basePath+'/complaint/ownerList',
	});
	
	//默认状态为不可用
	$('#complaint_show').hide();
	$('#complaint_show').linkbutton('disable');
	
	//物业管理员不能操作
	if(roleId==1) {
		$('#complaint_add').linkbutton('disable');
		$('#complaint_delete').linkbutton('disable');
		$('#complaint_add').hide();
		$('#complaint_delete').hide();
	}
	
	 //业主不能操作
	if(roleId==2) {
		$('#complaint_edit').linkbutton('disable');
		$('#complaint_edit').hide();
	};
	
});
