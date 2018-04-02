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
</script>
<script type="text/javascript" src="<%=basePath%>/js/tool/jquery.edatagrid.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/carStyleManage.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north'" style="height:50px"></div>
    <div data-options="region:'south'" style="height:50px;"></div>
    <div data-options="region:'west'" style="width:50px;"></div>
    <div data-options="region:'east'" style="width:50px"></div>
	<div data-options="region:'center'">
		<table id="dg"toolbar="#toolbar" title="车辆类型字典维护" pagination="true" idField="id" rownumbers="true" fit="true" singleSelect="true">
			<thead>
				<tr>
					<th field="name" width="100" editor="{type:'validatebox',options:{required:true}}">车辆类型名称</th>
				</tr>
			</thead>
		</table>
		<div id="toolbar">
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:$('#dg').edatagrid('addRow')">新建</a>
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:$('#dg').edatagrid('destroyRow')">删除</a>
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="javascript:$('#dg').edatagrid('saveRow')">保存</a>
		</div>
	</div>
</body>
</html>