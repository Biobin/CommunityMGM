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
<script type="text/javascript" src="<%=basePath%>/js/carManage.js"></script>
</head>
<body class="easyui-layout">
	<table id="carTb"></table>
		<div id="search_car"  style="font-size:14px;padding:10px;">
			<div style="margin-bottom:5px;">
				<span>车主：<input id="owner_search" class="easyui-textbox" style="width:100px" >&nbsp;&nbsp;</span>
				<span>车牌号：<input id="plateNumber_search" class="easyui-textbox" style="width:100px">&nbsp;&nbsp;</span>
				<span>车型：<input id="carStyle_search" class="easyui-textbox" name="carStyle_search" style="width:100px">&nbsp;&nbsp;</span>
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="obj.search();">查询</a>
			</div>
			<div>
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="obj.add();">添加</a>
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="obj.edit();">修改</a>
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="obj.remove();">删除</a>			
			</div>
		</div>
		<div id="car_dialog" class="easyui-dialog" data-options="closed:true,modal:true,closable:false,buttons:'#btn'">
			<form id="car_form" method="post">
				<table style="width:520px;padding:8px;height:200px;">
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;车主： <input id="ownerId" name="ownerId" data-options="required:true" style="width:150px;"/></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;车型： <input id="carStyleId" name="carStyleId" data-options="required:true" style="width:150px;"/></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;车牌号： <input name="plateNumber" class="easyui-textbox" data-options="required:true" /></td>
						<td>开始停放时间： <input name="createTime" class="easyui-datetimebox" data-options="required:true" /></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电话： <input name="ownerPhone" class="easyui-textbox" /></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮箱： <input name="ownerEmail" class="easyui-textbox" /></td>
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