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
	var roleId=${sessionScope.user.getRole().getId()};
</script>
<script type="text/javascript" src="<%=basePath%>/js/carAndOwnerInfo.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'center'">
	 	<div data-options="buttons:'#dlg-buttons'" style="width:550px;background-color:rgb(199, 225, 245);height:450px">
			<div style="font-size: 20px;font-weight: bold;padding: 5px;text-align: center;margin-bottom: 10px;border-bottom: 1px solid #ccc;">个人信息</div>
			<form id="owner_form" method="post">
				<table style="width:520px;height:350px">
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姓名： <input  name="name" class="easyui-textbox" data-options="required:true" style="width:150px;"/></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;账号：  <input name="username" class="easyui-textbox" style="width:150px;"/></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;住址： <input name="address" class="easyui-textbox"  style="width:150px;"/></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;入住时间：<input name="startTime" class="easyui-datetimebox" style="width:150px;"/></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电话：  <input name="phone" class="easyui-textbox" style="width:150px;"/></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮箱： <input name="email" class="easyui-textbox" style="width:150px;"/></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;身份证号： <input name="idnumber" class="easyui-textbox" style="width: 190px;" data-options="required:true" /></td>
					</tr>
				</table>
			</form>
			 <div id="dlg-buttons" style="text-align: center;">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="saveStuInfo()" iconcls="icon-save">确认无误</a> 
			</div>
		</div> 
	 </div>
</body>
</html>