<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<%@ include file="../static/header.jsp"%>
<style type="text/css">
body {
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #eee;
}

.form-signin {
	max-width: 330px;
	padding: 15px;
	margin: 0 auto;
}

.form-signin .form-signin-heading, .form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin .checkbox {
	font-weight: normal;
}

.form-signin .form-control {
	position: relative;
	height: auto;
	-webkit-box-sizing: border-box;
	box-sizing: border-box;
	padding: 10px;
	font-size: 16px;
}

.form-signin .form-control:focus {
	z-index: 2;
}

.form-signin input[type="email"] {
	margin-bottom: -1px;
	border-bottom-right-radius: 0;
	border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
	margin-bottom: 10px;
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}
</style>
<body>
	<div class="container">
		<form class="form-signin" method="post" action="${config.rootPath}/adminloginsubmit">
			<h2 class="form-signin-heading">管理员登录</h2>
			<label class="sr-only">Email address</label>
			<input type="email" class="form-control" required autofocus name="email">
			<label class="sr-only">Password</label>
			<input type="password" class="form-control" required name="pw">
			<button class="btn btn-lg btn-primary btn-block" type="submit">登入</button>
		</form>
	</div>
</body>
</html>
