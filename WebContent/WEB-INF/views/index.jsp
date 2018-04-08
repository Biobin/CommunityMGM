<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="jquery-easyui/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui/locale/easyui-lang-zh_CN.js" ></script>

<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript">
var basePath='<%=request.getContextPath()%>';
var roleId=${sessionScope.user.getRole().getId()};
</script>
<link rel="stylesheet" type="text/css" href="jquery-easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="jquery-easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="css/index.css" />
<title>文明苑智能小区管理系统</title>
</head>

<body class="easyui-layout">
	
	<div data-options="region:'north',title:'header',split:true,noheader:true" style="height:75px;background:#666;">
		<div class="logo"> 文明苑智能小区管理系统</div>
		<div class="logout">您好，${sessionScope.user.getRole().getName() } &nbsp;|&nbsp; <a href="${pageContext.request.contextPath}">退出</a></div>
	</div>
	
	<div data-options="region:'west',title:'导航',split:true" style="width:180px;">
		<div id="rightAccordion" class="easyui-accordion"></div>
		<ul id="navigation"></ul>  
	</div>
	
	<div data-options="region:'center'" style="overflow:hidden;">
		<div id="tabs">
			<div title="起始页" style="padding:0 10px;display:block;">
				欢迎来到文明苑智能小区管理系统！
			</div>
		</div>
	</div>
</body>
</html>	