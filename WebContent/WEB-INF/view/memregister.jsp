<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="static/header.jsp"%>
<body>
	<%@ include file="static/nav.jsp"%>
	<div class="container">
		<%@ include file="static/navFunction.jsp"%>
		<div class="row">
			<div class="col col-12">
				<form method="post" action="${config.rootPath}/memregistersubmit">
					<div class="form-group">
						<label for="inputEmail">Email address</label>
						<input type="email" class="form-control" placeholder="Enter email" name="email"/>
						<small class="form-text text-muted">请填写您的邮件.</small>
					</div>
					<div class="form-group">
						<label for="inputEmail">Nick Name</label>
						<input type="text" class="form-control" placeholder="匿名大佬" name="nickname"/>
						<small class="form-text text-muted">请填写您的昵称.</small>
					</div>
					<div class="form-group">
						<label for="inputPassword">Password</label>
						<input type="password" class="form-control" id="inputPassword" name="pw"/>
					</div>
					<div class="form-group">
						<label for="inputPassword">re-Password</label>
						<input type="password" class="form-control" id="reInputPassword"/>
						<small class="form-text text-muted">再次输入您的密码.</small>
					</div>
					<button type="submit" class="btn btn-primary">注册</button>
				</form>
			</div>
		</div>
	</div>
<script>
$(document).ready(function(){
	$("form").submit(function(){
		if($("input[type='email']").val()=="")
		{
			return false;
		}
		if($("#reInputPassword").val()!==$("#inputPassword").val())
		{
			$("#reInputPassword").parent().addClass("has-danger");
			$("#reInputPassword").addClass("form-control-danger");
			return false;
		}
		return true;
	});
});
</script>
<%@ include file="static/footer.jsp" %>
</body>
</html>