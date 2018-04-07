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
<script type="text/javascript" src="<%=basePath%>/js/ownerManage.js"></script>
</head>
<body class="easyui-layout">
	<table id="ownerTb"></table>
		<div id="search_owner"  style="font-size:14px;padding:10px;">
			<div style="margin-bottom:5px;">
				<span>账号：<input id="username_search" class="easyui-textbox" style="width:100px" >&nbsp;&nbsp;</span>
				<span>姓名：<input id="name_search" class="easyui-textbox" style="width:100px">&nbsp;&nbsp;</span>
				<span>电话：<input id="phone_search" class="easyui-textbox" name="phone_search" style="width:100px">&nbsp;&nbsp;</span>
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="obj.search();">查询</a>
			</div>
			<div>
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="obj.add();">添加</a>
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="obj.edit();">修改</a>
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="obj.remove();">删除</a>			
			</div>
		</div>
		<div id="owner_dialog" class="easyui-dialog" data-options="closed:true,modal:true,closable:false,buttons:'#btn'">
			<form id="owner_form" method="post">
				<table style="width:520px;padding:8px;height:200px;">
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名： <input name="name" class="easyui-textbox" data-options="required:true" /></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;账号： <input name="username" class="easyui-textbox" data-options="required:true" /></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;住址： <input name="address" class="easyui-textbox" data-options="required:true" /></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;入住时间： <input name="startTime" class="easyui-datetimebox" data-options="required:true" /></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电话： <input name="phone" class="easyui-textbox" /></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮箱： <input name="email" class="easyui-textbox" /></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;密码： <input name="password" class="easyui-passwordbox" data-options="required:true" /></td>
						<td>再次确认密码： <input name="rePassword" class="easyui-passwordbox" data-options="required:true" /></td>
					</tr>
					<tr>
						<td>身份证号： <input name="IDNumber" class="easyui-textbox" style="width: 190px;" data-options="required:true" /></td>
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