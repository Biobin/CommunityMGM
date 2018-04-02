/**
 * 车辆类型字典维护
 */
$(function(){
    $('#dg').edatagrid({
        url: basePath+'/carStyle/getCarStyle',
        saveUrl: basePath+'/carStyle/addOrUpdateCarStyle',
        updateUrl: basePath+'/carStyle/addOrUpdateCarStyle',
        onDestroy: function (index, row) {
        	$.post(basePath+'/carStyle/deleteCarStyle',{id:row.id},function(candelete){
        		if(candelete == "false"){
        			$.messager.alert('提示信息','该车辆类型已关联了车！','info');
        		}else if(candelete == "true"){
        			$.messager.alert('提示信息','删除成功！','info');
        		}else{
        			$.messager.alert('提示信息','删除失败！','info');
        		}
        		$('#dg').edatagrid('reload');
        	});
        }
    });
});