/**
 * 账单管理js
 */
$(function(){
	obj = {
		// 用于记录提交表单的连接，新增或者添加的请求地址
		submitUrl:'',	
		
		search:function(){
			$('#paymentTb').datagrid('load',{
				code:$.trim($('#code').val()),
				name:$.trim($('#name').val()),
			});
		},
		add:function(){
			$('#payment_form').form('clear');
			this.submitUrl = basePath + "/payment/addPayment";
		},
		edit:function(){
			var row = $('#paymentTb').datagrid('getSelected');
				var id=row.id;
				// 加载表单数据,默认请求方式为GET
				$('#payment_form').form('load',basePath+'/payment/getPayment/'+id);
				this.submitUrl=basePath+'/payment/updatePayment/'+id;
		},
		remove:function(){
			var row = $('#paymentTb').datagrid('getSelected');
			if(row!=null){
				$.messager.confirm('确定操作', '您要删除所选的记录吗？', function (flag) {
					if (flag) {
						var id=row.id;
						$.ajax({
							type : 'POST',
							url :basePath+'/payment/deletePayment/'+id,
							success : function (data) {
								if (data=="delete") {
									$.messager.alert('提示信息', '删除成功！');
									$('#paymentTb').datagrid('reload');
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
			$('#payment_form').form('submit',{
				url:obj.submitUrl,
				onSubmit:function(){
					return $(this).form('enableValidation').form('validate');   //检验各个编辑框是否满足设置的条件
				},
				success:function(data){
			        if(data=='add' || data=='update'){
			        	$.messager.alert('提示信息','保存成功！','info',function(){
			        		obj.clearForm();
			        		$('#paymentTb').datagrid('reload');
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
			$('#payment_form').form('clear');
		}
	};
	
	$("#paymentTb").datagrid({
		url:basePath+"/payment/getPayments",
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
		toolbar:'#search_payment',
		columns:[[
			{
				field : 'id',
				title : 'id',
				width : 100,
				hidden:true
			},
			{
				field : 'chargingItem',
				title : '收费项目',
				width : 120,
			},
			{
				field : 'createTime',
				title : '账单日期',
				width : 200,
			},
			
		]]
	});
	
	$('#ownerId').combobox({
		valueField : 'id',
		textField : 'name',
		limitToList : true,
		url : basePath+'/payment/ownerList',
		onChange:function(newValue,oldValue) {
			if(id.value == ""){
				$("#payment_form").form('load', basePath+ '/payment/showOwnerInfo/'+newValue);
			}
		}
	});
	
	$('#stateId').combobox({
		valueField : 'stateId',
		textField : 'state',
		limitToList : true,
		data : [{
			stateId: '6',
			state: '待缴费'
		},{
			stateId: '7',
			state: '欠费中'
		},{
			stateId: '8',
			state: '已缴费'
		}]
	});

	$('#stateId_search').combobox({
		valueField : 'stateId',
		textField : 'state',
		limitToList : true,
		data : [{
			stateId: '6',
			state: '待缴费'
		},{
			stateId: '7',
			state: '欠费中'
		},{
			stateId: '8',
			state: '已缴费'
		}]
	});
	
	 //业主不能操作
	if(roleId==2) {
		$('#payment_add').linkbutton('disable');
		$('#payment_delete').linkbutton('disable');
		$('#payment_save').linkbutton('disable');
		$('#payment_add').hide();
		$('#payment_delete').hide();
		$('#payment_save').hide();
	};
	
	
});