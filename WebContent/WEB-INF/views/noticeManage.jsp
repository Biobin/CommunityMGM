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
<script type="text/javascript" src="<%=basePath%>/js/noticeManage.js"></script>
</head>
<body class="easyui-layout">
	 <div data-options="region:'west',title:'公告列表',split:true" style="width:400px;">
		<table id="noticeTb"></table>
			<div id="search_notice"  style="font-size:14px;padding:10px;">
				<div style="margin-bottom:5px;">
					<span>编号：<input id="code" class="easyui-textbox" style="width:100px">&nbsp;&nbsp;</span>
					<span>标题：<input id="title" class="easyui-textbox" style="width:100px">&nbsp;&nbsp;</span>
					<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="obj.search();">查询</a>
				</div>
				<div>
					<a id="notice_add" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="obj.add();">发布</a>
					<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="obj.edit();">查看详情</a>
					<a id="notice_delete" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="obj.remove();">删除</a>			
				</div>
			</div>
	 </div>
	<div data-options="region:'center'">
	 	<div data-options="buttons:'#dlg-buttons'" style="width:780px;background-color:rgb(199, 225, 245);height:515px">
			<div style="font-size: 20px;font-weight: bold;padding: 5px;text-align: center;margin-bottom: 10px;border-bottom: 1px solid #ccc;">公告详细信息</div>
			<form id="notice_form" method="post">
				<input id="id" name="id" type="hidden"/>
				<table style="width:780px;height:400px">
					<span style="text-align: center;display:block;">(新增公告请点击先点击右侧添加按钮)</span>
					<tr style="text-align: center;">
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;编号： <input  name="code" class="easyui-textbox" data-options="required:true" style="width:180px;"/></td>
					</tr>
					<tr style="text-align: center;">
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;标题： <input  name="title" class="easyui-textbox" data-options="required:true" style="width:180px;"/></td>
					</tr>
					<tr style="text-align: center;">
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发布时间： <input name="createTime" class="easyui-datetimebox" data-options="required:true" style="width:180px;"/></td>
					</tr>
					<tr style="text-align: center;">
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;内容： <input name="content" class="easyui-textbox" style="width:400px;height:120px;" data-options="multiline:true"/></td>
					</tr>
				</table>
			</form>
			 <div id="dlg-buttons" style="text-align: center;">
				<a id="notice_save" href="javascript:void(0)" class="easyui-linkbutton"
					onclick="obj.submitForm();" iconcls="icon-save">保存</a> 
			</div>
		</div> 
	 		
	 </div>
</body>
</html>