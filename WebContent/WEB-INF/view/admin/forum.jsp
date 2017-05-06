<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="header.jsp"%>
<body>
	<div class="container-fluid">
		<%@ include file="nav.jsp"%>
		<div class="row">
			<div class="col col-12">
				<form>
					<div class="form-group">
						<label>标题</label> <input type="text" class="form-control" name="name" placeholder="总标题" />
					</div>
					<fieldset class="form-group">
						<legend>可见设置</legend>
						<div class="form-check">
							<label class="form-check-label">
								<input type="radio" class="form-check-input" name="loginvisible" value="0" <c:if test="${item.value.auth.loginVisible==0}">checked</c:if>> 任意可见
							</label>
						</div>
						<div class="form-check">
							<label class="form-check-label">
								<input type="radio" class="form-check-input" name="loginvisible" value="0" <c:if test="${item.value.auth.loginVisible==0}">checked</c:if>> 登录可见
							</label>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
</body>
</html>