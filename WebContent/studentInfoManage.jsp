<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学籍信息</title>
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.2/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.2/themes/icon.css">
	<script type="text/javascript" src="jquery-easyui-1.5.2/jquery.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.5.2/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.5.2/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
	<table id="dg" title="学籍信息" class="easyui-datagrid" fitColumns="true" 
		pagination="true" rownumbers="true" url="studentInfoList" fit="true">
		<thead>
			<tr>
				<th field="id" width="60">学号</th>
				<th field="studentName" width="60">姓名</th>
				<th field="studentPhone" width="100">电话</th>
				<th field="studentAddr" width="150">户籍</th>
			</tr>
		</thead>
	</table>
</body>
</html>