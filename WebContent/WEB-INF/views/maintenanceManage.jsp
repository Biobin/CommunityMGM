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
<script type="text/javascript" src="<%=basePath%>/js/maintenanceManage.js"></script>
</head>
<body class="easyui-layout">
	<table id="maintenanceTb"></table>
		<div id="search_maintenance"  style="font-size:14px;padding:10px;">
			<div style="margin-bottom:5px;">
				<span>报修日期：<input id="beginTime" class="easyui-datetimebox" style="width:100px;">&nbsp;&nbsp;——&nbsp;&nbsp;
					<input id="endTime" class="easyui-datetimebox" style="width:100px;"></span>
				<span>&nbsp;&nbsp;&nbsp;&nbsp;维修状态：<input id="stateId_search" class="easyui-textbox" style="width:100px;">&nbsp;&nbsp;</span>
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="obj.search();">查询</a>
			</div>
			<div>
				<a id="maintenance_add" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="obj.add();">报修</a>
				<a id="maintenance_edit" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="obj.edit();">处理</a>
				<a id="maintenance_delete" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="obj.remove();">删除</a>
				<a id="maintenance_show" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-tip',plain:true," disabled="disabled" onclick="obj.show();">查看维修单</a>
			</div>
		</div>
	<div id="maintenance_dialog_add" class="easyui-dialog" data-options="closed:true,modal:true,closable:false,buttons:'#btn'">
	 	<div data-options="buttons:'#dlg-buttons'" style="width:650px;background-color:rgb(199, 225, 245);height:480px">
			<div style="font-size: 20px;font-weight: bold;padding: 5px;text-align: center;margin-bottom: 10px;border-bottom: 1px solid #ccc;">报修单</div>
			<form id="maintenance_form_add" method="post">
				<input id="id" name="id" type="hidden"/>
				<table style="width:650px;height:410px">
					<span style="text-align: center;display:block;">(报修单号格式为：设备类型缩写加日期(例如：公服20180404))</span>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;报修单号： <input name="code" class="easyui-textbox" data-options="required:true" style="width:130px;"/></td>
						<td>&nbsp;&nbsp;&nbsp;设施类型： <input id="communalFaStyleId" name="communalFaStyleId" data-options="required:true" style="width:130px;"/></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;设施名称： <input id="communalFacilitiesId" name="communalFacilitiesId" data-options="required:true" style="width:130px;"/>&nbsp;&nbsp;</td>
						<td>维修负责人： <input id="propertyManagerId" name="propertyManagerId" data-options="required:true" style="width:130px;"/></td>
						<!-- <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电话： <input  name="propertyManagerPhone" class="easyui-textbox" style="width:130px;"/></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮箱： <input  name="propertyManagerEmail" class="easyui-textbox" style="width:130px;"/></td> -->
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;报修日期： <input name="createTime" class="easyui-datetimebox" data-options="required:true" style="width:130px;" /></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;报修详情： <input name="details" class="easyui-textbox" style="width:260px;height:80px;" data-options="multiline:true"/></td>
					</tr>
				</table>
			</form>
			 <div id="btn" style="text-align:center;">
				<span><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save',width:60,height:25" onclick="obj.submitForm();">保存</a></span>
				<span><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',width:60,height:25" onclick="obj.clearForm()">取消</a></span>
			</div>
		</div> 
	</div>
	<div id="maintenance_dialog_edit" class="easyui-dialog" data-options="closed:true,modal:true,closable:false,buttons:'#btn'">
	 	<div data-options="buttons:'#dlg-buttons'" style="width:840px;background-color:rgb(199, 225, 245);height:420px">
			<div style="font-size: 20px;font-weight: bold;padding: 5px;text-align: center;margin-bottom: 10px;border-bottom: 1px solid #ccc;">维修单</div>
			<form id="maintenance_form_edit" method="post">
				<input id="id" name="id" type="hidden"/>
				<table style="width:800px;height:350px">
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;报修单号： <input name="code" class="easyui-textbox" data-options="required:true" style="width:130px;" readonly="readonly" /></td>
						<td>设施类型： <input id="communalFaStyleId_edit" name="communalFaStyleId" data-options="required:true" style="width:130px;" readonly="readonly" /></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;设施名称： <input class="easyui-textbox" name="communalFacilitiesNames" data-options="required:true" style="width:130px;" readonly="readonly" /></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;报修人： <input id="ownerId" name="ownerId" data-options="required:true" style="width:130px;" readonly="readonly" /></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电话： <input  name="ownerPhone" class="easyui-textbox" style="width:130px;" readonly="readonly" /></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮箱： <input  name="ownerEmail" class="easyui-textbox" style="width:130px;" readonly="readonly" /></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;维修状态： <input id="stateId" name="stateId" class="easyui-textbox" data-options="required:true" style="width:130px;"/></td>
						<td>提交日期： <input name="createTime" class="easyui-datetimebox" data-options="required:true" style="width:130px;" readonly="readonly" /></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;维修日期： <input name="repairTime" class="easyui-datetimebox" style="width:130px;" /></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;维修人员： <input name="repairPersonnel" class="easyui-textbox" data-options="required:true" style="width:130px;" /></td>
						<td>维修电话： <input  name="repairPerPhone" class="easyui-textbox"  style="width:130px;" /></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;报修详情： <input name="details" class="easyui-textbox" style="width:260px;height:80px;" data-options="multiline:true" readonly="readonly" /></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;维修备注： <input name="repairRemarks" class="easyui-textbox" style="width:260px;height:80px;" data-options="multiline:true"/></td>
					</tr>
				</table>
			</form>
			 <div id="btn" style="text-align:center;">
				<span><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save',width:60,height:25" onclick="obj.submitForm();">保存</a></span>
				<span><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',width:60,height:25" onclick="obj.clearForm()">取消</a></span>
			</div>
		</div> 
	</div>
	<div id="maintenance_dialog_show" class="easyui-dialog" data-options="closed:true,modal:true,closable:true">
	 	<div data-options="buttons:'#dlg-buttons'" style="width:940px;background-color:rgb(199, 225, 245);height:420px">
			<div style="font-size: 20px;font-weight: bold;padding: 5px;text-align: center;margin-bottom: 10px;border-bottom: 1px solid #ccc;">维修单</div>
			<form id="maintenance_form_show" method="post">
				<input id="id" name="id" type="hidden"/>
				<table style="width:900px;height:350px">
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;报修单号： <input name="code" class="easyui-textbox" data-options="required:true" style="width:130px;" readonly="readonly" /></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;设施类型： <input id="communalFaStyleId_show" name="communalFaStyleId" data-options="required:true" style="width:130px;" readonly="readonly" /></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;设施名称： <input class="easyui-textbox" name="communalFacilitiesNames" data-options="required:true" style="width:130px;" readonly="readonly" /></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;设施编号： <input name="communalFacilitiesCode" class="easyui-textbox" data-options="required:true" style="width:130px;"/></td>
						<td>开始使用时间： <input name="beginUsingTime" class="easyui-datetimebox" style="width:130px;"/></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备注信息： <input name="communalFacilitiesDetails" class="easyui-textbox" style="width:130px;"/></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;报修人： <input name="ownerName" class="easyui-textbox" data-options="required:true" style="width:130px;" readonly="readonly" /></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电话： <input  name="ownerPhone" class="easyui-textbox" style="width:130px;" readonly="readonly" /></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮箱： <input  name="ownerEmail" class="easyui-textbox" style="width:130px;" readonly="readonly" /></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;维修状态： <input id="stateId_show" name="stateId" class="easyui-textbox" data-options="required:true" style="width:130px;"/></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;提交日期： <input name="createTime" class="easyui-datetimebox" data-options="required:true" style="width:130px;" readonly="readonly" /></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;维修日期： <input name="repairTime" class="easyui-datetimebox" style="width:130px;" /></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;维修人员： <input name="repairPersonnel" class="easyui-textbox" data-options="required:true" style="width:130px;" /></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;维修电话： <input  name="repairPerPhone" class="easyui-textbox"  style="width:130px;" /></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;报修详情： <input name="details" class="easyui-textbox" style="width:260px;height:80px;" data-options="multiline:true" readonly="readonly" /></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;维修备注： <input name="repairRemarks" class="easyui-textbox" style="width:260px;height:80px;" data-options="multiline:true"/></td>
					</tr>
				</table>
			</form>
		</div> 
	</div>
</body>
</html>