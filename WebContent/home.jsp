<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>学籍管理系统</title>
	<%
		//检查用户是否登录了 session和response是内置对象，不用定义
		if (session.getAttribute("currentUser") == null){
			response.sendRedirect("index.jsp");
			return;
		}
	%>
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.2/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.5.2/themes/icon.css">
	<script type="text/javascript" src="jquery-easyui-1.5.2/jquery.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.5.2/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.5.2/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
	
	    function exit(){
	    	session.invalidate(); 
	   		window.open('../StudentInfoManage/index.jsp','_top')
	    } 
	    
		//这里的函数在页面加载完毕之后才会执行
		$(function () {
			var treeData = [{
				text:"导航",
				children:[{
					text:"学籍管理",
					attributes:{
						url:"studentInfoManage.jsp"
					}
				}]
			}];
			
			$("#tree").tree({
				data:treeData,
				lines:true,
				onClick:function(node) {
					if (node.attributes) {
						// 打开选项卡
						openTab(node.text, node.attributes.url);
					}
				}
			})
			
			function openTab(text, url) {
				// 如果选项卡存在了，则选中
				if ($("#tabs").tabs('exists', text)) {
					$("#tabs").tabs('select', text);
				} else {
					// 不存在，则打开它
					var content="<iframe frameborder = '0' scrolling='auto' style='width:100%; height:100%' src="+url+"></iframe>"
					$("#tabs").tabs('add',{
						title:text,
						closable:true,
						content:content
					});
				}
			}
		});
		

	</script>
</head>
<body class="easyui-layout">
	<!-- 布局 -->
	<div region="north" style="height: 80px; background-color: #acd598">
		<div float:left style="margin-top: 15px;margin-left: 50px;"> <font size="6" >学籍管理系统</font> </div>
		<div float:right align="right" style="padding-top: 1px; padding-right: 50px;">当前用户：${currentUser.userName } 
			<a onclick="exit()" href="#" style="color: blue; margin-left: 5px;text-decoration: underline;">注销</a>
		</div>
		
	</div>
	
	<div region="west" style="width:150px;" title="菜单" split="true" background-color: #00ff00">
		<ul id="tree"></ul>
	</div>
	
	<div region="center">
		<div class="easyui-tabs" fit="true" border="false" id="tabs">
			<div title="首页">
				<div align="center" >
					<p><font size="5">欢迎使用学籍管理系统</font></p>
					<p><font size="3">使用说明：请点击左侧的导航栏项目进行选择操作！</font></p>
				</div>
			</div>
		</div>
	</div>
	
	<div region="south" style="height: 25px;" align="center">版权所有 ©<a href="http://tianzry.uicp.cn">Tianzry</a></div>
</body>
</html>