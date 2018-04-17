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
	var userId=${sessionScope.user.getId()};
</script>
<script type="text/javascript" src="<%=basePath%>/js/contactManage.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'center'">
	 	<div data-options="buttons:'#dlg-buttons'" style="width:760px;background-color:rgb(199, 225, 245);height:495px">
			<div style="font-size: 20px;font-weight: bold;padding: 5px;text-align: center;margin-bottom: 10px;border-bottom: 1px solid #ccc;">联系人详细信息</div>
			<form id="contact_form" method="post">
				<table style="width:520px;height:350px">
					<span style="text-align: center;display:block;">(新增联系人请点击右侧添加按钮)</span>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名： <input  name="name" class="easyui-textbox" data-options="required:true" style="width:180px;"/></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电话：  <input name="phone" class="easyui-textbox" style="width:220px;"/></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮箱： <input name="email" class="easyui-textbox" style="width:220px;"/></td>
					</tr>
				</table>
			</form>
			 <div id="dlg-buttons" style="text-align: center;">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="obj.submitForm();" iconcls="icon-save">保存</a> 
			</div>
		</div> 
	 </div>
	 <div data-options="region:'east',title:'通讯录',split:true" style="width:420px;">
	 		<table id="contactTb"></table>
			<div id="search_contact"  style="font-size:14px;padding:10px;">
				<div style="margin-bottom:5px;">
					<span>姓名：<input id="name" class="easyui-textbox" style="width:100px">&nbsp;&nbsp;</span>
					<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="obj.search();">查询</a>
				</div>
				<div>
					<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="obj.add();">添加</a>
					<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="obj.edit();">查看详情</a>
					<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="obj.remove();">删除</a>			
				</div>
			</div>
	 </div>
</body>
</html>