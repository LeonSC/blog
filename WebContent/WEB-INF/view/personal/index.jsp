<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="../static/header.jsp"%>
<body>
	<%@ include file="../static/nav.jsp"%>
	<div class="container">
		<%@ include file="../static/navFunction.jsp"%>
		<div class="row">
			<div class="col col-9">
				<div class="row">
					<div class="col col-12">
						<h3>${memAuth.username}</h3>
					</div>
					<div class="col col-12">
						<form enctype="multipart/form-data" method="post" action="${config.rootPath}/personal/headericon/update">
							<div class="form-group">
								<small class="form-text text-muted">头像设置,图片会被强制修改到 <mark>200</mark> X <mark>200</mark>.
								</small> <input type="file" class="form-control-file" name="img">
							</div>
							<button type="submit" class="btn btn-primary">提交</button>
						</form>
					</div>
				</div>
				<br />
				<div class="row">
					<div class="col col-12">
						<form method="post" action="${config.rootPath}/personal/info/update">
							<div class="form-group">
								<label>昵称</label> 
								<input type="text" class="form-control" name="nickname" value="${memAuth.nickname}"> 
							</div>
							<fieldset class="form-group">
								<legend>性别设定</legend>
								<div class="form-check">
									<c:if test="${memAuth.gender==0}">
									<label class="form-check-label"> <input type="radio" class="form-check-input" name="gender" value="0" checked> 保密</label>
									</c:if>
									<c:if test="${memAuth.gender!=0}">
									<label class="form-check-label"> <input type="radio" class="form-check-input" name="gender" value="0"> 保密</label>
									</c:if>
								</div>
								<div class="form-check">
									<c:if test="${memAuth.gender==1}">
									<label class="form-check-label"> <input type="radio" class="form-check-input" name="gender" value="1" checked> 男</label>
									</c:if>
									<c:if test="${memAuth.gender!=1}">
									<label class="form-check-label"> <input type="radio" class="form-check-input" name="gender" value="1"> 男</label>
									</c:if>
								</div>
								<div class="form-check">
									<c:if test="${memAuth.gender==2}">
									<label class="form-check-label"> <input type="radio" class="form-check-input" name="gender" value="2" checked> 女</label>
									</c:if>
									<c:if test="${memAuth.gender!=2}">
									<label class="form-check-label"> <input type="radio" class="form-check-input" name="gender" value="2"> 女</label>
									</c:if>
								</div>
							</fieldset>
							<div class="form-group">
								<label>签名</label> 
								<input type="text" class="form-control" name="sign" value="${memAuth.sign}"> 
							</div>
							<button type="submit" class="btn btn-primary">提交</button>
						</form>
					</div>
				</div>
				<c:if test="${not empty memAuth.email}">
				<br />
				<div class="row">
					<div class="col col-12">绑定邮箱 <i class="fa fa-address-card" aria-hidden="true"></i> ${memAuth.email}</div>
				</div>
				</c:if>
				<c:if test="${empty memAuth.email}">
				<br />
				<div class="row">
					<div class="col col-12">
						<form method="post" action="${config.rootPath}/personal/email/send">
							<div class="form-group">
								<label>绑定邮箱</label> 
								<input type="text" class="form-control" name="email" value="${memAuth.username}"> 
							</div>
							<button type="submit" class="btn btn-primary">发送验证邮件</button>
						</form>
					</div>
				</div>
				</c:if>
			</div>
			<div class="col col-3">
				<%@ include file="../static/myself.jsp"%>
			</div>
		</div>
	</div>
	<%@ include file="../static/footer.jsp"%>
</body>
</html>