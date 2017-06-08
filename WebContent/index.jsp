<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学籍管理系统登录</title>
<style type="text/css">
	*{
	margin: 0;
	padding: 0;
	}
	body{
		background-image: url("login.jpg");
		background-repeat: no-repeat;
		background-size: 100%;
		min-height: 600px;
	}
	.login_container{
		width: 40%;
		height: 300px;
		margin-top: 100px;
		margin-left: auto;
		margin-right: auto;
		background-color:#333;
		border: 1px solid white;
		border-radius: 5px;
	}

	.login_bar{
		width: 100%;
		height: 50px;
		display: block;
		background-color: green;
		float: left;
		border-bottom: 1px solid white;
		border-radius: 5px;
		/*text-align: center;*/
	}
	.login_bar p{
		margin-top: 10px;
		font-size: 25px;
		color: white;
		/*text-align: left;*/
		margin-left: 50px;
	}
	.login_ctrl{
		text-align: center;
	}
	.label{
		color: white;
		font-size: 20px;
	}
	.input{
		display: inline-block;
		height: 30px;
		width: 200px;
		margin-top: 20px;

	}
	.input.first{
		margin-top: 50px;
	}
	.btn_login{
		display: inline-block;
		width: 70px;
		height: 30px;
		margin-top: 20px;
		font-size: 16px;

	}
	.btn_login a{
		text-decoration: none;
		color: black;
	}
	#btn_login_first{
		margin-left: 85px;
		margin-right: 25px;
	}
	.login_ctrl p{
		margin-top: 5px;
		margin-left: -5px;
	}
	#error_id {
		margin-left: 80px;
	}
</style>
<script type="text/javascript">
	function resetValues() {
		document.getElementById("btn_login_first").value="";
		document.getElementById("btn_reset").value="";
	}
</script>
</head>
<body>
	<div class="login_container">
		<div class="login_bar">
			<p>登陆</p>
		</div>
		<div class="login_ctrl">
			<form class="form_login" action="login" method="post">
				<label class="label" for="username">用户名：</label>
				<input class="input first" type="text" name="userName"  value="${userName }">
				<br>
				<label class="label" for="password">密&nbsp&nbsp&nbsp&nbsp码：</label>
				<input class="input" type="password" name="password"  value="${password }">
				<br>
				<button class="btn_login" id="btn_login_first" name="login_btn">
					登陆
				</button>
				<button class="btn_login" id="btn_reset" name="login_cancel_btn" onclick="resetValues()">
					<a href="#">重置</a>
				</button>
				<p id="error_id"><font color="red">${error }</font></p>
			</form>
		</div>
	</div>
</body>
</html>