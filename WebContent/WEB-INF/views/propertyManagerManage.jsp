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
<script type="text/javascript" src="<%=basePath%>/js/tool/md5.js" ></script>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/jquery-easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/jquery-easyui/themes/icon.css" />

<script type="text/javascript">
	var basePath="<%=basePath%>";
	var propertyManagerId = ${sessionScope.user.getPropertyManager().getId()};
</script>
<script type="text/javascript" src="<%=basePath%>/js/propertyManagerManage.js"></script>
</head>
<body class="easyui-layout">
	<table id="propertyManagerTb"></table>
		<div id="search_propertyManager"  style="font-size:14px;padding:10px;">
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
		<div id="propertyManager_dialog" class="easyui-dialog" data-options="closed:true,modal:true,closable:false,buttons:'#btn'">
			<form id="propertyManager_form" method="post">
				<table style="width:480px;padding:8px;height:200px;margin-left:30px;">
					<tr>
						<td>姓名： <input name="name" class="easyui-textbox" data-options="required:true" /></td>
						<td>账号： <input name="username" class="easyui-textbox" data-options="required:true" /></td>
					</tr>
					<tr>
						<td>电话： <input name="phone" class="easyui-textbox" /></td>
						<td>邮箱： <input name="email" class="easyui-textbox" /></td>
					</tr>
					<tr>
						<td>密码： <input id="password" name="password" class="easyui-passwordbox" data-options="required:true" /></td>
						<td>再次确认密码： <input id="rePassword" name="rePassword" class="easyui-passwordbox" data-options="required:true" style="width:100px;"/></td>
					</tr>
					<tr style="height:20px;">
						<td><div id="errorTip" style="font-size:15px"></div></td>
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