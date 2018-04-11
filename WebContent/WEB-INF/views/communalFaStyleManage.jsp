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
<script type="text/javascript" src="<%=basePath%>/js/communalFaStyleManage.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'center'">
		<table id="dg"toolbar="#toolbar" pagination="true" idField="id" rownumbers="true" fit="true" singleSelect="true">
			<thead>
				<tr>
					<th field="name" width="150" editor="{type:'validatebox',options:{required:true}}">公共设施类型名称</th>
				</tr>
			</thead>
		</table>
		<div id="toolbar">
			<div style="padding: 5px 0 5px 5px">
				编码：<input id="code" class="easyui-textbox" style="width:100px">&nbsp;&nbsp;
				名称：<input id="name" class="easyui-textbox" style="width:100px">&nbsp;&nbsp;
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="searchOwnership()">查询</a>
			</div>
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:$('#dg').edatagrid('addRow')">新建</a>
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:$('#dg').edatagrid('destroyRow')">删除</a>
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="javascript:$('#dg').edatagrid('saveRow')">保存</a>
		</div>
	</div>
</body>
</html>