<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String basePath=request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=basePath%>/jquery-easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/jquery-easyui/locale/easyui-lang-zh_CN.js" ></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/jquery-easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/jquery-easyui/themes/icon.css" />

<script type="text/javascript">
	var basePath="<%=basePath%>";
	var roleList='${requestScope.roleList}';
	var menuList='${requestScope.menuList}';
</script>
<script type="text/javascript" src="<%=basePath%>/js/menuManage.js"></script>
</head>
<body class="easyui-layout">
	<table id="menuTb"></table>
		<div id="search_menu"  style="font-size:14px;padding:10px;">
			<div style="margin-bottom:5px;">
				<span>名称：<input id="text_search" class="easyui-textbox" style="width:100px">&nbsp;&nbsp;</span>
				<span>角色：<input id="role_search" name="role_search" style="width:100px">&nbsp;&nbsp;</span>
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="obj.search();">查询</a>
			</div>
			<div>
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="obj.add();">添加</a>
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="obj.edit();">修改</a>
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="obj.remove();">删除</a>			
			</div>
		</div>
		<div id="menu_dialog" class="easyui-dialog" data-options="closed:true,modal:true,closable:false,buttons:'#btn'">
			<form id="menu_form" method="post">
				<table style="width:520px;padding:8px;height:200px;">
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名称： <input name="text" class="easyui-textbox" data-options="required:true" /></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;父节点： <input id="pid" name="pid" style="width:150px;"/></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;url： <input name="url" class="easyui-textbox" /></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;状态： 
							<input id="stateId" name="stateId" style="width:150px;" />
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;图标： <input name="iconCls" class="easyui-textbox" /></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;权限： <input id="rolesIds" name="rolesIds" style="width:150px;"/></td>
					</tr>
				</table>
			</form>
			<div id="btn" style="text-align:center;">
				<span><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save',width:60,height:25" onclick="obj.submitForm();">保存</a></span>
				<span><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',width:60,height:25" onclick="obj.clearForm()">取消</a></span>
			</div>
		</div>
</body>
</html>