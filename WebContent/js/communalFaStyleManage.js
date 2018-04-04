/**
 * 小区公共设施类型
 */
$(function(){
    $('#dg').edatagrid({
        url: basePath+'/communalFaStyle/getCommunalFaStyle',
        saveUrl: basePath+'/communalFaStyle/addOrUpdateCommunalFaStyle',
        updateUrl: basePath+'/communalFaStyle/addOrUpdateCommunalFaStyle',
        onDestroy: function (index, row) {
        	$.post(basePath+'/communalFaStyle/deleteCommunalFaStyle',{id:row.id},function(candelete){
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