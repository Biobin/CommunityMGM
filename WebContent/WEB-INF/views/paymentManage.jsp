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
<script type="text/javascript" src="<%=basePath%>/js/paymentManage.js"></script>
</head>
<body class="easyui-layout">
	 <div data-options="region:'west',title:'账单记录',split:true" style="width:390px;">
		<table id="paymentTb"></table>
			<div id="search_payment"  style="font-size:14px;padding:10px;">
				<div style="margin-bottom:5px;">
					<span>账单产生日期：<input id="beginTime" class="easyui-datetimebox" style="width:100px;">&nbsp;&nbsp;——&nbsp;&nbsp;
						<input id="endTime" class="easyui-datetimebox" style="width:100px;"></span>
					<span>缴费状态：<input id="stateId_search" class="easyui-textbox" style="width:100px;">&nbsp;&nbsp;</span>
					<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="obj.search();">查询</a>
				</div>
				<div>
					<a id="payment_add" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="obj.add();">添加</a>
					<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="obj.edit();">查看详情</a>
					<a id="payment_delete" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="obj.remove();">删除</a>			
				</div>
			</div>
	 </div>
	<div data-options="region:'center'">
	 	<div data-options="buttons:'#dlg-buttons'" style="width:1100px;background-color:rgb(199, 225, 245);height:600px">
			<div style="font-size: 20px;font-weight: bold;padding: 5px;text-align: center;margin-bottom: 10px;border-bottom: 1px solid #ccc;">账单详细信息</div>
			<form id="payment_form" method="post">
				<input id="id" name="id" type="hidden"/>
				<table style="width:1000px;height:400px">
					<span style="text-align: center;display:block;">(新增账单请点击先点击右侧添加按钮)</span>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;缴费业主： <input id="ownerId" name="ownerId" data-options="required:true" style="width:180px;"/></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电话： <input  name="ownerPhone" class="easyui-textbox" style="width:180px;"/></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮箱： <input  name="ownerEmail" class="easyui-textbox" style="width:180px;"/></td>
					</tr>
					<tr>
					</tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;缴费状态： <input id="stateId" name="stateId" class="easyui-textbox" data-options="required:true" style="width:180px;"/></td>
						<td>收费项目： <input  name="chargingItem" class="easyui-textbox" data-options="required:true" style="width:180px;"/></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;账单日期： <input name="createTime" class="easyui-datetimebox" data-options="required:true" style="width:180px;"/></td>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;应收金额： <input  name="receivableFee" class="easyui-textbox" data-options="required:true" style="width:180px;"/></td>
						<td>实收金额： <input  name="collectFee" class="easyui-textbox" style="width:180px;"/></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;欠款金额： <input  name="owedFee" class="easyui-textbox" style="width:180px;"/></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;收费详情： <input name="details" class="easyui-textbox" style="width:300px;height:120px;" data-options="multiline:true"/></td>
					</tr>
				</table>
			</form>
			 <div id="dlg-buttons" style="text-align: center;">
				<a id="payment_save" href="javascript:void(0)" class="easyui-linkbutton"
					onclick="obj.submitForm();" iconcls="icon-save">保存</a> 
			</div>
		</div> 
	 		
	 </div>
</body>
</html>