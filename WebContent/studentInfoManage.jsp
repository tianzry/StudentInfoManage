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
	<script type="text/javascript">
		// 显示搜索结果
		function searchStudentName() {
			$('#dg').datagrid('load', {
				name:$('#s_studentName').val()
			});
		}
		
		// 删除函数
		function deleteStudentInfo() {
			var selectedRows = $('#dg').datagrid('getSelections');
			if (selectedRows.length == 0) {
				$.messager.alert("提示","请先选择要删除的数据！");
				return;
			}
			
			// 装载删除的学生学号
			var strIds=[];
			for (var i = 0; i < selectedRows.length; i++) {
				strIds.push(selectedRows[i].id);
			}
			
			// 加入逗号
			var ids = strIds.join(",");
			
			$.messager.confirm("提示","您确定要删除这<font color=red>" + selectedRows.length + "</font>条数据吗？",
					function(r) {
						if (r){
							// Ajax代码，infoDelete是web.xml中指定的url字段
							$.post("infoDelete", {deleteIds:ids},function(result) {
								if (result.success) {
									$.messager.alert("提示","您已成功删除<font color=red>"+result.deleteNum+"</font>条数据！");
									$("#dg").datagrid("reload"); // 删除成功就刷新页面数据表
								} else {
									$.messager.alert("错误",result.errorMsg);
								}
							},"json"); // 这里的Jason参数不要漏，请求都是以Json格式发送过去的
						}
					});
		}
	</script>
</head>
<body>
	<table id="dg" title="学籍信息" class="easyui-datagrid" fitColumns="true" 
		pagination="true" singleSelect=false rownumbers="true" url="infoQuery" fit="true" toolbar="#tb">
		<thead>
			<tr>
				<!-- 这里的数据字段名称必须和数据库列名相同才能显示出来-->
				<th field="cb" checkbox="true"></th>
				<th field="id" width="60">学号</th>
				<th field="name" width="60">姓名</th>
				<th field="phone" width="100">电话</th>
				<th field="address" width="150">户籍</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb">
		<div>
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" >添加</a>
			<a href="javascript:deleteStudentInfo()" class="easyui-linkbutton" iconCls="icon-remove" plain="true" >删除</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" >修改</a>
			&nbsp 姓名：&nbsp<input type="text" name="s_studentName" id="s_studentName"/> <a href="javascript:searchStudentName()" class="easyui-linkbutton" iconCls="icon-search" plain="true" >查找</a>
		</div>
	</div>
	
</body>
</html>