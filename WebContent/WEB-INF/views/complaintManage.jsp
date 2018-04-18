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
<script type="text/javascript" src="<%=basePath%>/js/complaintManage.js"></script>
</head>
<body class="easyui-layout">
	<table id="complaintTb"></table>
		<div id="search_complaint"  style="font-size:14px;padding:10px;">
			<div style="margin-bottom:5px;">
				<span>投诉日期：<input id="beginTime" class="easyui-datetimebox" style="width:100px;">&nbsp;&nbsp;——&nbsp;&nbsp;
					<input id="endTime" class="easyui-datetimebox" style="width:100px;"></span>
				<span>&nbsp;&nbsp;&nbsp;&nbsp;缴费状态：<input id="stateId_search" class="easyui-textbox" style="width:100px;">&nbsp;&nbsp;</span>
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="obj.search();">查询</a>
			</div>
			<div>
				<a id="complaint_add" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="obj.add();">添加</a>
				<a id="complaint_edit" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="obj.edit();">修改</a>
				<a id="complaint_delete" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="obj.remove();">删除</a>
				<a id="complaint_show" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-tip',plain:true," disabled="disabled" onclick="obj.show();">查看详情</a>
			</div>
		</div>
	<div id="complaint_dialog_add" class="easyui-dialog" data-options="closed:true,modal:true,closable:false,buttons:'#btn'">
	 	<div data-options="buttons:'#dlg-buttons'" style="width:800px;background-color:rgb(199, 225, 245);height:390px">
			<div style="font-size: 20px;font-weight: bold;padding: 5px;text-align: center;margin-bottom: 10px;border-bottom: 1px solid #ccc;">投诉信息</div>
			<form id="complaint_form_add" method="post">
				<input id="id" name="id" type="hidden"/>
				<table style="width:800px;height:340px">
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;投诉人受理人： <input id="propertyManagerId" name="propertyManagerId" data-options="required:true" style="width:180px;"/></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电话： <input  name="propertyManagerPhone" class="easyui-textbox" style="width:180px;"/></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮箱： <input  name="propertyManagerEmail" class="easyui-textbox" style="width:180px;"/></td>
						<td>提交日期： <input name="createTime" class="easyui-datetimebox" data-options="required:true" style="width:180px;" /></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;投诉详情： <input name="content" class="easyui-textbox" style="width:330px;height:120px;" data-options="multiline:true"/></td>
					</tr>
				</table>
			</form>
			 <div id="btn" style="text-align:center;">
				<span><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save',width:60,height:25" onclick="obj.submitForm();">保存</a></span>
				<span><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',width:60,height:25" onclick="obj.clearForm()">取消</a></span>
			</div>
		</div> 
	</div>
	<div id="complaint_dialog_edit" class="easyui-dialog" data-options="closed:true,modal:true,closable:false,buttons:'#btn'">
	 	<div data-options="buttons:'#dlg-buttons'" style="width:1040px;background-color:rgb(199, 225, 245);height:400px">
			<div style="font-size: 20px;font-weight: bold;padding: 5px;text-align: center;margin-bottom: 10px;border-bottom: 1px solid #ccc;">投诉信息</div>
			<form id="complaint_form_edit" method="post">
				<input id="id" name="id" type="hidden"/>
				<table style="width:1000px;height:350px">
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;投诉人： <input id="ownerId" name="ownerId" data-options="required:true" style="width:180px;" readonly="readonly" /></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电话： <input  name="ownerPhone" class="easyui-textbox" style="width:180px;" readonly="readonly" /></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮箱： <input  name="ownerEmail" class="easyui-textbox" style="width:180px;" readonly="readonly" /></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;处理状态： <input id="stateId" name="stateId" class="easyui-textbox" data-options="required:true" style="width:180px;"/></td>
						<td>提交日期： <input name="createTime" class="easyui-datetimebox" data-options="required:true" style="width:180px;" /></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;解决日期： <input name="finishTime" class="easyui-datetimebox" style="width:180px;" /></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;投诉详情： <input name="content" class="easyui-textbox" style="width:330px;height:120px;" data-options="multiline:true" readonly="readonly" /></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;处理回复： <input name="returnContent" class="easyui-textbox" style="width:330px;height:120px;" data-options="multiline:true"/></td>
					</tr>
				</table>
			</form>
			 <div id="btn" style="text-align:center;">
				<span><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save',width:60,height:25" onclick="obj.submitForm();">保存</a></span>
				<span><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',width:60,height:25" onclick="obj.clearForm()">取消</a></span>
			</div>
		</div> 
	</div>
	<div id="complaint_dialog_show" class="easyui-dialog" data-options="closed:true,modal:true,closable:true">
	 	<div data-options="buttons:'#dlg-buttons'" style="width:1040px;background-color:rgb(199, 225, 245);height:400px">
			<div style="font-size: 20px;font-weight: bold;padding: 5px;text-align: center;margin-bottom: 10px;border-bottom: 1px solid #ccc;">投诉信息</div>
			<form id="complaint_form_show" method="post">
				<input id="id" name="id" type="hidden"/>
				<table style="width:1000px;height:350px">
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;投诉人： <input id="ownerId_show" name="ownerId" data-options="required:true" style="width:180px;" readonly="readonly" /></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电话： <input  name="ownerPhone" class="easyui-textbox" style="width:180px;" readonly="readonly" /></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮箱： <input  name="ownerEmail" class="easyui-textbox" style="width:180px;" readonly="readonly" /></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;投诉人受理人： <input id="propertyManagerId_show" name="propertyManagerId" data-options="required:true" style="width:180px;"/></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电话： <input  name="propertyManagerPhone" class="easyui-textbox" style="width:180px;"/></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮箱： <input  name="propertyManagerEmail" class="easyui-textbox" style="width:180px;"/></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;处理状态： <input id="stateId_show" name="stateId" class="easyui-textbox" data-options="required:true" style="width:180px;"/></td>
						<td>提交日期： <input name="createTime" class="easyui-datetimebox" data-options="required:true" style="width:180px;" /></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;解决日期： <input name="finishTime" class="easyui-datetimebox" style="width:180px;" /></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;投诉详情： <input name="content" class="easyui-textbox" style="width:330px;height:120px;" data-options="multiline:true"/></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;处理回复： <input name="returnContent" class="easyui-textbox" style="width:330px;height:120px;" data-options="multiline:true"/></td>
					</tr>
				</table>
			</form>
		</div> 
	</div>
</body>
</html>