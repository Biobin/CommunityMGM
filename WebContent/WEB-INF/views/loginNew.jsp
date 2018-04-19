<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
		<script type="text/javascript" src="<%=basePath%>/jquery-easyui/jquery.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>/jquery-easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
		
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/jquery-easyui/themes/default/easyui.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/jquery-easyui/themes/icon.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/jquery-easyui/demo/demo.css"/>
		<script type="text/javascript" src="<%=basePath%>/js/tool/md5.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/login.css" />
		
		<script type="text/javascript">
		    var basePath="<%=basePath%>";
		</script>
		<script type="text/javascript" src="<%=basePath%>/js/loginNew.js"></script>
		
		<title>文明苑智能小区管理系统</title>
	</head>
	<body>
		<div class="login">
		    <div class="box png">
				<div class="logo"></div>
				<div class="input">
					<div class="log">
						<div class="name">
							<label>用户名</label><input type="text" class="text" id="username" placeholder="用户名" name="value_1" tabindex="1">
						</div>
						<div class="pwd">
							<label>密　码</label><input type="password" class="text" id="password" placeholder="密码" name="value_2" tabindex="2">
							<input id="btnLogin" type="button" class="submit" tabindex="3" value="登录">
							<div class="check"></div>
						</div>
						<div class="tip"></div>
					</div>
				</div>
			</div>
			<div style="text-align:center;">
		        <h3>©2018 SoulBio All Rights Reserved</h3>
		    </div>
			<div class="air-balloon ab-1 png"></div>
			<div class="air-balloon ab-2 png"></div>
		    <div class="air-balloon ab-1 png"></div>
		    <div class="footer"></div>
		</div>
	</body>
</html>