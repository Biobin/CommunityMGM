/**
 * 状态维护字典
 */
$(function(){
    $('#dg').edatagrid({
        url: basePath+'/state/getState',
        saveUrl: basePath+'/state/addOrUpdateState',
        updateUrl: basePath+'/state/addOrUpdateState',
        onDestroy: function (index, row) {
        	$.post(basePath+'/state/deleteState',{id:row.id},function(candelete){
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