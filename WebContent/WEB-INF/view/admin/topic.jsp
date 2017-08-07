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
				<div class="card text-center">
					<div class="card-header">
						<ul class="nav nav-tabs card-header-tabs">
							<c:forEach var="item" items="${cache.titleCache}" varStatus="i">
							<li class="nav-item"><a class="nav-link" href="#tab${item.value.BM_ID}" data-toggle="tab" role="tab">${item.value.name}</a></li>
							</c:forEach>
							<li class="nav-item"><a class="nav-link active" href="#tab_add" data-toggle="tab" role="tab">添加</a></li>
						</ul>
					</div>
					<div class="tab-content">
						<c:forEach var="item" items="${cache.titleCache}" varStatus="i">
							<div class="tab-pane fade" id="tab${item.value.BM_ID}" role="tabpanel">
								<div class="card-block">
									<p class="card-text">已有管理员</p>
									<p class="card-text">
										<c:forEach var="manager" items="${item.value.manager}" varStatus="ii">
											<c:if test="${ii.first}">
											${manager.value.username}
											</c:if>
											<c:if test="${not ii.first}">
											 , ${manager.value.username}
											</c:if>
										</c:forEach>
									</p>
									<form method="post" action="${config.rootPath}/admin/topic/addmanager">
										<div class="form-group row">
											<label class="col-2 col-form-label">账户</label>
											<div class="col-10">
												<input type="hidden" name="bmid" value="${item.value.BM_ID}"/>
												<input class="form-control" type="email" name="email" />
											</div>
										</div>
										<button type="submit" class="btn btn-primary btn-sm">提交</button>
									</form>
									<p class="card-text">${item.value.intro}</p>
									<p class="card-text">设置</p>
									<form method="post" action="${config.rootPath}/admin/topic/auth/update">
										<input type="hidden" name="bmid" value="${item.value.BM_ID}"/>
										<div class="form-group row">
											<fieldset class="col-12 form-group">
												<div class="form-check">
													<label class="form-check-label">
													<input type="radio" class="form-check-input" name="loginvisible" value="0" <c:if test="${item.value.auth.loginVisible==0}">checked</c:if>> 任意可见
													</label>
												</div>
												<div class="form-check">
													<label class="form-check-label">
													<input type="radio" class="form-check-input" name="loginvisible" value="1" <c:if test="${item.value.auth.loginVisible==1}">checked</c:if>> 仅登录可见
													</label>
												</div>
											</fieldset>
										</div>
										<!-- 
										<div class="form-group row">
											<label class="col-2 col-form-label">可阅读最低等级</label>
											<div class="col-10">
												<input class="form-control" type="number" name="lv" min=0 max="${adminAuth.admin.lv}" value="${item.value.auth.lv}"/>
											</div>
										</div>
										<div class="form-group row">
											<label class="col-2 col-form-label">可见最低等级</label>
											<div class="col-10">
												<input class="form-control" type="number" name="visible" min=0 max="${adminAuth.admin.visible}" value="${item.value.auth.visible}"/>
											</div>
										</div>
										 -->
										<button type="submit" class="btn btn-primary btn-sm">提交</button>
									</form>
									<hr/>
									<a class="btn btn-danger btn-sm" role="button" href="${config.rootPath}/admin/topic/remove?bmid=${item.value.BM_ID}">删除</a>
								</div>
							</div>
						</c:forEach>
						<div class="tab-pane fade show active" id="tab_add" role="tabpanel">
							<div class="card-block">
							<form method="post" action="${config.rootPath}/admin/topic/add">
								<div class="form-group row">
									<label class="col-2 col-form-label">添加</label>
									<div class="col-10">
										<input class="form-control" type="text" name="name" />
									</div>
								</div>
								<button type="submit" class="btn btn-primary btn-sm">提交</button>
							</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>