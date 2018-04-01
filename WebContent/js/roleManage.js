/**
 * 角色字典维护
 */
$(function(){
    $('#dg').edatagrid({
        url: basePath+'/role/getRoles',
        saveUrl: basePath+'/role/addOrUpdateRole',
        updateUrl: basePath+'/role/addOrUpdateRole',
        //destroyUrl: basePath+'/role/deleteRole',
        onDestroy: function (index, row) {
        	$.post(basePath+'/role/deleteRole',{id:row.id},function(candelete){
        		if(candelete == "false"){
        			$.messager.alert('提示信息','该角色已关联了用户！','info');
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