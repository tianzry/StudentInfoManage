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
		
		var url; //这个参数用于分辨是修改还是新增学籍信息
		
		// 添加学籍信息函数
		function openStudentInfoAddDialog() {
			$("#dlg").dialog("open").dialog("setTitle","添加学籍信息");
			url="infoSave";
		}
		
		function closeStudentInfoAddDialog() {
			$("#dlg").dialog("close");
			resetValues();
		}
		
		function saveStudentInfo() {
			$("#fm").form("submit", {
				url:url,
				onSubmit:function() {
					return $(this).form("validate");
				},
				success:function(result) {
					// 如果出错，则提示
					if(result.errorMsg) {
						$.messager.alert("错误", result.errorMsg);
						return;
					} else {
						$.messager.alert("提示","保存成功！");
						resetValues();
						$("#dlg").dialog("close");
						$("#dg").datagrid("reload");
					}
				}
			});
		}
		
		//修改学籍信息
		function openStudentInfoModifyDialog() {
			var selectedRows = $('#dg').datagrid('getSelections');
			if (selectedRows.length != 1) {
				$.messager.alert("提示","请选择一条要修改的数据！");
				return;
			}
			
			var row = selectedRows[0];
			$("#dlg").dialog("open").dialog("setTitle", "编辑学籍信息");
			$("#fm").form("load", row);
			url="infoModify";
		}
		function resetValues() {
			$("#id").val("");
			$("#name").val("");
			$("#phone").val("");
			$("#address").val("");
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
				<th field="id" width="30">学号</th>
				<th field="name" width="20">姓名</th>
				<th field="sex" width="10">性别</th>
				<th field="birthday" width="20">出生日期</th>
				<th field="grade" width="20">成绩均分</th>
				<th field="phone" width="30">电话</th>
				<th field="address" width="150">户籍</th>
				
			</tr>
		</thead>
	</table>
	
	<div id="tb">
		<div>
			<a href="javascript:openStudentInfoAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true" >添加</a>
			<a href="javascript:deleteStudentInfo()" class="easyui-linkbutton" iconCls="icon-remove" plain="true" >删除</a>
			<a href="javascript:openStudentInfoModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true" >修改</a>
			&nbsp 姓名：&nbsp<input type="text" name="s_studentName" id="s_studentName"/> <a href="javascript:searchStudentName()" class="easyui-linkbutton" iconCls="icon-search" plain="true" >查找</a>
		</div>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width:300px; height: 260px; padding: 10px 20px"
	 closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table>
				<tr>
					<td>学号：</td>
					<td><input type="text" name="id" id="id" class="easyui-validatebox" required="ture"/></td>
				</tr>
				<tr>
					<td>姓名：</td>
					<td><input type="text" name="name" id="name" class="easyui-validatebox" required="ture"/></td>
				</tr>
				<tr>
					<td>电话：</td>
					<td><input type="text" name="phone" id="phone" class="easyui-validatebox" required="ture"/></td>
				</tr>
				<tr>
					<td>户籍：</td>
					<td><textarea rows="1" clos="1" name="address" id="address" class="easyui-validatebox" required="ture"></textarea></td>
				</tr>
			</table>
		</form>
		
	</div>
	
	<div id="dlg-buttons">
		<a href="javascript:saveStudentInfo()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeStudentInfoAddDialog()" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
	</div>
	
</body>
</html>