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
								<c:if test="${i.first}">
									<li class="nav-item"><a class="nav-link active" href="#tab${item.BM_ID}" data-toggle="tab" role="tab">${item.name}</a></li>
								</c:if>
								<c:if test="${not i.first}">
									<li class="nav-item"><a class="nav-link" href="#tab${item.BM_ID}" data-toggle="tab" role="tab">${item.name}</a></li>
								</c:if>
							</c:forEach>
						</ul>
					</div>
					<div class="tab-content">
						<c:forEach var="item" items="${cache.titleCache}" varStatus="i">
							<c:if test="${i.first}">
								<div class="tab-pane fade show active" id="tab${item.BM_ID}" role="tabpanel">
									<div class="card-block">
										<p class="card-text">已有管理员</p>
										<p class="card-text">
											<c:forEach var="manager" items="${item.manager}" varStatus="ii">
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
													<input type="hidden" name="bmid" value="${item.BM_ID}"/>
													<input class="form-control" type="email" name="email" />
												</div>
											</div>
											<button type="submit" class="btn btn-primary btn-sm">提交</button>
										</form>
										<p class="card-text">${item.intro}</p>
									</div>
								</div>
							</c:if>
							<c:if test="${not i.first}">
								<div class="tab-pane fade" id="tab${item.BM_ID}" role="tabpanel">
									<div class="card-block">
										<p class="card-text">已有管理员</p>
										<p class="card-text">
											<c:forEach var="manager" items="${item.manager}" varStatus="ii">
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
													<input type="hidden" name="bmid" value="${item.BM_ID}"/>
													<input class="form-control" type="email" name="email" />
												</div>
											</div>
											<button type="submit" class="btn btn-primary btn-sm">提交</button>
										</form>
										<p class="card-text">${item.intro}</p>
									</div>
								</div>
							</c:if>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
		<br />
		<div class="row">
			<div class="col col-12"></div>
		</div>
	</div>
</body>
</html>