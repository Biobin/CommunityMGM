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
<script type="text/javascript" src="<%=basePath%>/js/carAndOwnerInfo.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'center'">
	 	<div data-options="buttons:'#dlg-buttons'" style="width:550px;background-color:rgb(199, 225, 245);height:450px">
			<div style="font-size: 20px;font-weight: bold;padding: 5px;text-align: center;margin-bottom: 10px;border-bottom: 1px solid #ccc;">车辆信息</div>
			<form id="car_form" method="post">
				<table style="width:520px;height:350px">
					<span style="text-align: center;display:block;">(选择车牌号查看车辆信息)</span>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;车牌号： <input id="carId" name="carId" data-options="required:true" style="width:150px;"/></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;车型： <input name="carStyleName" class="easyui-textbox" style="width:150px;"/></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;车主： <input name="ownerName" class="easyui-textbox"  style="width:150px;"/></td>
						<td>开始停放时间： <input name="createTime" class="easyui-datetimebox" style="width:150px;"/></td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电话： <input name="ownerPhone" class="easyui-textbox" style="width:150px;"/></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮箱： <input name="ownerEmail" class="easyui-textbox" style="width:150px;"/></td>
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