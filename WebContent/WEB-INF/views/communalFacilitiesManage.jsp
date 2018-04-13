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
<script type="text/javascript" src="<%=basePath%>/js/communalFacilitiesManage.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'center'">
		<table id="communalFacilitiesTb"></table>
			<div id="search_communalFacilities"  style="font-size:14px;padding:10px;">
				<div style="margin-bottom:5px;">
					<span>编号：<input id="code" class="easyui-textbox" style="width:100px">&nbsp;&nbsp;</span>
					<span>名称：<input id="name" class="easyui-textbox" style="width:100px">&nbsp;&nbsp;</span>
					<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="obj.search();">查询</a>
				</div>
				<div>
					<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="obj.add();">添加</a>
					<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="obj.edit();">查看详情</a>
					<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="obj.remove();">删除</a>			
				</div>
			</div>
	 </div>
	 <div data-options="region:'south',title:'小区设施',split:true" style="height:450px;">
	 	<div data-options="buttons:'#dlg-buttons'" style="width:1000px;background-color:rgb(199, 225, 245);height:415px">
			<div style="font-size: 20px;font-weight: bold;padding: 5px;text-align: center;margin-bottom: 10px;border-bottom: 1px solid #ccc;">设施详细信息</div>
			<form id="communalFacilities_form" method="post">
				<input id="id" name="id" type="hidden"/>
				<table style="width:950px;height:300px">
					<span style="text-align: center;display:block;">(新增设施请点击先点击上方添加按钮)</span>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;维修负责人：  <input id="propertyManagerId" name="propertyManagerId" style="width:180px;"/></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电话：  <input name="propertyManagerPhone" class="easyui-textbox" style="width:180px;"/></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮箱： <input name="propertyManagerEmail" class="easyui-textbox" style="width:180px;"/></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;编号： <input  name="code" class="easyui-textbox" data-options="required:true" style="width:180px;"/></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名称： <input  name="name" class="easyui-textbox" data-options="required:true" style="width:180px;"/></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;设施类型： <input id="communalFaStyleId" name="communalFaStyleId" class="easyui-textbox" data-options="required:true" style="width:180px;"/></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;开始使用时间： <input name="beginUsingTime" class="easyui-datetimebox" data-options="required:true" style="width:180px;"/></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备注： <input name="details" class="easyui-textbox" style="width:300px;height:70px;" data-options="multiline:true"/></td>
					</tr>
				</table>
			</form>
			 <div id="dlg-buttons" style="text-align: center;">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="obj.submitForm();" iconcls="icon-save">保存</a> 
			</div>
		</div> 
	 		
	 </div>
</body>
</html>