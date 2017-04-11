<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="col col-xl-12">
		<c:if test="${empty memAuth}">
		<div class="card">
			<img class="card-img-top" src="holder.js/100px200?theme=simple"
				alt="Card image cap">
			<div class="card-block">
				<form method="post" action="${config.rootPath}/memloginsubmit">
					<div class="form-group">
						<label for="inputEmail">Email address</label>
						<input type="email" class="form-control" placeholder="Enter email" name="email"/>
					</div>
					<c:if test="${empty error_wrongpw}">
					<div class="form-group">
						<label for="inputPassword">Password</label>
						<input type="password" class="form-control" name="pw"/>
					</div>
					</c:if>
					<c:if test="${error_wrongpw == 'wrongpw'}">
					<div class="form-group has-danger">
						<label for="inputPassword">Password</label>
						<input type="password" class="form-control form-control-danger" name="pw"/>
					</div>
					</c:if>
					<a class="btn btn-default" href="${config.rootPath}/memregister">注册</a>
					<button type="submit" class="btn btn-primary">登录</button>
				</form>
			</div>
		</div>
		</c:if>
		<c:if test="${not empty memAuth}">
		<div class="card">
			<img class="card-img-top" src="holder.js/100px200?theme=simple"
				alt="Card image cap">
			<div class="card-block">
				<h4 class="card-title">${memAuth.nickname}</h4>
				<p class="card-text">Some quick example text to build on the
					card title and make up the bulk of the card's content.</p>
			</div>
			<div class="card-footer">
				<ul class="nav nav-pills card-header-pills">
					<li class="nav-item"><a class="nav-link" href="${config.rootPath}/write">写</a></li>
					<li class="nav-item"><a class="nav-link" href="#">信</a></li>
					<li class="nav-item"><a class="nav-link" href="#">收</a></li>
					<li class="nav-item"><a class="nav-link" href="${config.rootPath}/memlogoutsubmit">出</a></li>
				</ul>
			</div>
		</div>
		</c:if>
	</div>
</div>