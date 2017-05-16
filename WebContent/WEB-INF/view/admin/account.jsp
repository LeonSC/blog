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
				<form method="post" action="${config.rootPath}/admin/account">
					<div class="form-group row">
						<label class="col-2 col-form-label">账户</label>
						<div class="col-10">
							<input class="form-control" type="email" name="email" />
						</div>
					</div>
					<button type="submit" class="btn btn-primary">提交</button>
				</form>
			</div>
		</div>
		<br />
		<div class="row">
			<div class="col col-12">
				<c:if test="${not empty user}">
					<form method="post" action="${config.rootPath}/admin/account/update">
						<div class="form-group row">
							<label class="col-12 col-form-label">${user.username} / ${user.nickname}</label>
							<input type="hidden" value="${user.BM_ID}" name="bmid"/>
						</div>
						<div class="form-group row">
							<label class="col-2 col-form-label">会员等级</label>
							<div class="col-10">
								<input class="form-control" type="number" value="${user.lv}" name="lv">
							</div>
						</div>
						<div class="form-group row">
							<label class="col-2 col-form-label">管理员</label>
							<div class="col-10">
								<div class="form-check">
									<label class="form-check-label">
									<c:if test="${empty user.admin}"><input class="form-check-input" type="checkbox" name="auth" value="1" /> 设置成管理员</c:if> 
									<c:if test="${not empty user.admin}"><input class="form-check-input" type="checkbox" name="auth" value="0" /> 取消管理员</c:if>
									</label>
								</div>
							</div>
						</div>
						<c:if test="${not empty user.admin}">
							<div class="form-group row">
								<label class="col-2 col-form-label">管理员等级</label>
								<div class="col-10">
									<input class="form-control" type="number" value="${user.admin.lv}" name="adminlv">
								</div>
							</div>
							<div class="form-group row">
								<label class="col-2 col-form-label">可视等级</label>
								<div class="col-10">
									<input class="form-control" type="number" value="${user.admin.visible}" name="adminvisible">
								</div>
							</div>
							<div class="form-group row">
								<label class="col-2 col-form-label">创建等级</label>
								<div class="col-10">
									<input class="form-control" type="number" value="${user.admin.create}" name="admincreate">
								</div>
							</div>
							<div class="form-group row">
								<label class="col-2 col-form-label">删除等级</label>
								<div class="col-10">
									<input class="form-control" type="number" value="${user.admin.delete}" name="admindelete">
								</div>
							</div>
							<div class="form-group row">
								<label class="col-2 col-form-label">修改等级</label>
								<div class="col-10">
									<input class="form-control" type="number" value="${user.admin.modify}" name="adminmodify">
								</div>
							</div>
							<div class="form-group row">
								<label class="col-2 col-form-label">查找等级</label>
								<div class="col-10">
									<input class="form-control" type="number" value="${user.admin.find}" name="adminfind">
								</div>
							</div>
						</c:if>
						<button type="submit" class="btn btn-primary">修改</button>
					</form>
					<canvas id="infoChart" width="90%" height="20"></canvas>
					<script>
					var data = ${data};
					var ctx = document.getElementById("infoChart");
					var myLineChart = Chart.Line(ctx, {
					    data: data
					});
					</script>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>